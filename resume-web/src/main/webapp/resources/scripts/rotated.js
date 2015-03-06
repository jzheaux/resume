/**
 * 
 */
$(function() {
	$(".rotated").each(function(){
		var rotated = $(this).data("src");
		var fixed = "";
		for ( var i = 0; i < rotated.length; i++ ) {
			var code = rotated.charCodeAt(i);
			if ( code >= 48 && code <= 97  + 25 ) {
				fixed += String.fromCharCode((code - 48 + 74) % 75 + 48);
			} else {
				fixed += String.fromCharCode(code);
			}
		}
		$(this).attr('href', $(this).attr('href') + fixed);
		$(this).html(fixed);
	});
})