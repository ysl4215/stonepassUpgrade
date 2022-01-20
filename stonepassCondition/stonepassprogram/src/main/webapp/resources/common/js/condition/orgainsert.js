$(document).on('click', '#orga_insert', function(e) {

	if (!checkOrganame(form.orga_name.value)) {
		form.orga_name.focus();
		return false;
	} else if (!checkUrl(form.orga_url.value)) {
		form.orga_url.focus();
		return false;
	}

	var orga_name = document.getElementById('orga_name').value;
	var orga_url = document.getElementById('orga_url').value;

	$.ajax({
		url: "condition",
		data: {
			orga_name: orga_name,
			orga_url: orga_url
		},
		type: "post",
		success: function(result) {
			opener.parent.location.reload();
			window.close();
		},
		error: function() {
			alert("error");
		}
	});
});

function checkAll() {
	if (!checkUserId(form.userId.value)) {
		return false;
	} else if (!checkPassword(form.userId.value, form.password1.value,
		form.password2.value)) {
		return false;
	} else if (!checkMail(form.mail.value)) {
		return false;
	} else if (!checkName(form.name.value)) {
		return false;
	} else if (!checkBirth(form.identi1.value, form.identi2.value)) {
		return false;
	} else if (!checkFavorite()) {
		return false;
	} else if (!checkIntro()) {
		return false;
	}
	return true;
}

// 공백확인 함수
function checkExistData(value, dataName) {
	if (value == "") {
		alert(dataName + " 입력해주세요!");
		return false;
	}
	return true;
}
function checkOrganame(id) {
	//Id가 입력되었는지 확인하기
	if (!checkExistData(id, "기관이름을")) {
		return false;
	}
	return true; //확인이 완료되었을 때
}

function checkUrl(url) {
	//mail이 입력되었는지 확인하기
	if (!checkExistData(url, "URL을"))
		return false;

	if (url.indexOf("http://") == 0 || url.indexOf("https://") == 0) {

		return true;  //확인이 완료되었을 때
	} else {
		alert("http:// 또는 https:// 를 입력해주세요")
		form.orga_url.focus();
		return false;
	}
}

/*$(document).on('click', '#cancle', function(e) {


	e.preventDefault();
	window.open("about:blank", "_self").close();
});
*/
/*function cancle(){
	e.preventDefault();
	window.open("about:blank", "_self").close();
}*/