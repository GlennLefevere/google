var apikey = "AIzaSyDQ-l7yV5HPowepjn3DFKvkYsSs9UOUYxE";
$(window).load(function (){
	var token = $("#token").val();
	
	$.getJSON('https://www.googleapis.com/drive/v2/files?corpus=DEFAULT&projection=FULL&key=' + apikey + '&access_token='+token, function (data){
		console.log(data);
		$.ajax({
			type: 'GET',
			dataType: 'json',
			url: "http://localhost:8080/google/drive/findall",
			data: JSON.stringify(data)
		})
	});
});