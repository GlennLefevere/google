var apikey = "AIzaSyDQ-l7yV5HPowepjn3DFKvkYsSs9UOUYxE";
$(window)
		.load(
				function() {
					var token = $("#token").val();
					if (typeof token !== "undefined") {
						$.getJSON(
										'https://www.googleapis.com/drive/v2/files?corpus=DEFAULT&projection=FULL&key='
												+ apikey + '&access_token='
												+ token,
										function(data) {
													console.log(data);
											for (var x = 0; x < data['items'].length; x++) {
												if (data['items'][x]['explicitlyTrashed'] !== true) {
													$('#files')
															.append(
																	"<option value='" + data['items'][x]['id'] + "'>"
																			+ data['items'][x]['title']
																			+ "</option>");
												}
											}
										});
					}
					$("#knop").click(function (){
						var optVals = [];
						$('#files > option:selected').each(function() {
							var val = $(this).val();
							optVals.push(val);
						});
						console.log(JSON.stringify(optVals));
						$.ajax({
							type: 'POST',
							dataType:'json',
							url: "drive/findall",
							contentType: 'application/json',
							data: JSON.stringify(optVals)
						});
						
					});
				});
