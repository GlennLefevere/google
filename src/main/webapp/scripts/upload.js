var token = "";
var apikey = "";
$(window).load(function() {
	$.getJSON("apikey", function (result){
		apikey = result.apikey;
		token = result.token;
	})
	$("#file").on("change", function(evt) {
		gapi.auth.init(callback);
		gapi.auth.setToken(token);
		gapi.client.setApiKey(apikey);
		gapi.client.load('drive', 'v2', function() {
			var file = evt.target.files[0];
			insertFile(file);
		});
	});
});

function insertFile(fileData, callback) {
	const
	boundary = '-------314159265358979323846';
	const
	delimiter = "\r\n--" + boundary + "\r\n";
	const
	close_delim = "\r\n--" + boundary + "--";

	var reader = new FileReader();
	reader.readAsBinaryString(fileData);
	reader.onload = function(e) {
		var contentType = fileData.type || 'application/octet-stream';
		var metadata = {
			'title' : fileData.name,
			'mimeType' : contentType
		};

		var base64Data = btoa(reader.result);
		var multipartRequestBody = delimiter
				+ 'Content-Type: application/json\r\n\r\n'
				+ JSON.stringify(metadata) + delimiter + 'Content-Type: '
				+ contentType + '\r\n'
				+ 'Content-Transfer-Encoding: base64\r\n' + '\r\n' + base64Data
				+ close_delim;

		var request = gapi.client.request({
			'path' : '/upload/drive/v2/files',
			'method' : 'POST',
			'params' : {
				'uploadType' : 'multipart',
				'access_token' : token
			},
			'headers' : {
				'Content-Type' : 'multipart/mixed; boundary="' + boundary + '"'
			},
			'body' : multipartRequestBody
		});
		if (!callback) {
			callback = function(file) {
				console.log(file)
			};
		}
		request.execute(callback);
	}
}
function callback(file){
	console.log(file);
}