var a = 1;

function plusa(){
	console.log(a+a+a);
}

// condition.jsp
function email(){
	let popUrl = "/emailInsert";
		let popOption = "width = 650px, height=550px, top=300px, left=300px, scrollbars=yes";

		window.open(popUrl, "e-mail 등록", popOption);
	
}


//emailinsert.jsp

$(document).on('click', '#emailinsert', function(e){	
	//${pageContext.request.contextPath}
	document.form_mail.submit();
	window.open("about:blank", "_self").close();
});

$(document).on('click', '#emailcancel', function(e){	
	e.preventDefault();
	window.open("about:blank","_self").close();
});


