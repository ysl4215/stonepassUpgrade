$(document).on('click', '#cencle', function(e) {
		e.preventDefault();
		window.open("about:blank", "_self").close();
	});

$(document).on('click', '#orga_modify', function(e) {
		$("#form_orga_modify").submit();
		window.open("about:blank", "_self").close();
	});

		
$(document).on('click', '#orga_delete', function(e) {
	
	var bid = document.getElementById('bid').value;
	
$.ajax({
	url : 'conditionDelete',
	type : 'post',
	data: {
		bid : bid
	},
	success: function(result){
		
		opener.parent.location.reload();
		window.close();
	},
	error: function(){
		consol.log("error");
	}
	
});
	
		/*location.href = "/conditionDelete" + "?bid=" + document.getElementById("bid").value;
	
		
		window.close();
		opener.parent.location.reload();
	*/
	});