$(document).on('click', '#cencle', function(e) {
	e.preventDefault();
	window.open("about:blank", "_self").close();
});

$(document).on('click', '#orga_modify', function(e) {

	if (!checkOrganame(form.orga_name.value)) {
		form.orga_name.focus();
            return false;
        } else if (!checkUrl(form.orga_url.value)) {
		form.orga_url.focus();
			return false;
}

	var confirmflag = confirm("수정하시겠습니까?");

	if (confirmflag) {
		/*var bid = document.querySelector('#bid').value;*/
		var bid = document.getElementById('bid').value;
		var orga_name = document.getElementById('orga_name').value;
		var orga_url = document.getElementById('orga_url').value;

		$.ajax({
			url: 'conditionModify',
			type: 'post',
			data: {
				bid: bid,
				orga_name: orga_name,
				orga_url: orga_url
			},
			success: function(result) {
				opener.parent.location.reload();
				window.close();
			},
			error: function(){
			}
		})
		return true;
	} else {
		return false;
	}
});

$(document).on('click', '#orga_delete', function(e) {

	var bid = document.getElementById('bid').value;
	var confirmflag = confirm("삭제하시겠습니까?");
	if (confirmflag) {
		$.ajax({
			url: 'conditionDelete',
			type: 'post',
			data: {
				bid: bid
			},
			success: function(result) {
				opener.parent.location.reload();
				window.close();
			},
			error: function() {
				consol.log("error");
			}
		});
		return true;
	} else {
		return false;
	}
});

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
	if (!checkExistData(url, "URL을")){
		return false;
	}
	if (url.indexOf("http://") == 0 || url.indexOf("https://") == 0) {
		return true;  //확인이 완료되었을 때
	} else {
		alert("http:// 또는 https:// 를 입력해주세요")
		form.orga_url.focus();
		return false;
	}
}