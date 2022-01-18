$(document).ready(function() {
	var connection = "n";

	$.ajax({
		url: "serverCurl",
		type: 'post',
		/*dataType: "JSON",*/
		data: {
			connection: connection
		},
		success: function(result) {
		},
		error: function() {
			alert("error");
		}
	});
	$('#loading').hide();
});

if (performance.navigation.type == 1) {
	console.info("aaaa");
} else {
	console.info("bbbb");
}

// condition.jsp
function email() {
	let popUrl = "/email";
	let popOption = "width = 650px, height=550px, top=300px, left=300px, scrollbars=yes";
	window.open(popUrl, "e-mail 등록", popOption);
}

function insert() {
	let popUrl = "/orgainsert";
	let popOption = "width = 650px, height=300px, top=300px, left=300px, scrollbars=yes";
	window.open(popUrl, "기관 등록", popOption);
}

function modify(bid) {
	var url = "/condition/content";
	url = url + "?bid=" + bid;
	let popUrl = url
	let popOption = "width = 650px, height=300px, top=300px, left=300px, scrollbars=yes";
	window.open(popUrl, "기관 수정", popOption);
}

function openPopup() {
	/*  첫 번째 파라미터는 팝업창의 url 주소. 두 번째 파라미터는 팝업창의 이름. 마지막 파라미터는 팝업창에 대한 설정 */
	window.open("aa", popOpen, ['fullscreen=no,menubar=no,status=no,toolbar=no,titlebar=no,location=no,scrollbar=auto,width=650,height=550']);
	iniform.target = "popOpen";
	iniform.action = " URL명";
	iniform.submit();
}

//CURL 단일버튼
function kdw(kdwthis) {
	var bid = kdwthis.value
	var checked = 0;

	if (kdwthis.checked) {
		checked = 1;
		$('#loading').show();
	} else {
		checked = 0;
	}
	$.ajax({
		url: "conditionCurl",
		type: 'post',
		data: {
			values: checked,
			bid: bid
		},
		success: function(result) {
			console.log(bid)
			$("#condition" + bid).html(result);
			/*	hideLoading();*/
			$('#loading').hide();
		},
		error: function() {
			alert("error");
		}
	});
}

//CURL 전체연결 버튼
function checkall() {
	// 2. #checkAll이 체크되었을 때,
	// chk라는 name을 가진 input태그를 찾아 checked를 true로 정의

	if ($("#checkAll").prop("checked")) {
		$('#loading').show();
		/*timerId = setTimeout(checkall, 5000);*/
		$("input[name=checkBoxId]").prop("checked", true)

		var chkbox = $(".chkselect");
		/*var chkall = document.getElementById('checkAll');*/

		for (i = 0; i < chkbox.length; i++) {
			chkbox[i].checked = checkAll.checked;
		}

		//버튼 활성화시 Chk()로 이동
		Chk();
		// 3. #checkAll이 체크되지 않았을 때,
		// chk라는 name을 가진 input태그를 찾아 checked를 false로 정의
	} else {
		$("input[name=checkBoxId]").prop("checked", false)
		/*hideLoadingAll();*/
		$('#loading').hide();
		//버튼 해제시 ChkOff()로 이동
		ChkOff();
	}
}

function ChkOff() {
	var result = Array();
	var cnt = 0;
	var chkbox = $(".chkselect");

	for (i = 0; i < chkbox.length; i++) {
		if (chkbox[i].checked == false) {
			result[cnt] = chkbox[i].value;
			cnt++;
		}
	}
	var values = 0;
	var bidArray = result;

	$.ajax({
		url: "conditionCurlArray",
		type: 'post',
		dataType: "JSON",
		traditional: true, //필수
		data: {
			values: values,
			bidArray: bidArray
		},
		success: function(result) {
			for (var i = 0; i < bidArray.length; i++) {
				$("#condition" + bidArray[i]).html(result[i]);
				console.log(bidArray[i] + "  " + result[i]);
			}
		},
		error: function() {
			alert("error");
		}
	});
	location.reload();
}

function Chk() {
	var result = Array();
	var cnt = 0;
	var chkbox = $(".chkselect");
	for (i = 0; i < chkbox.length; i++) {
		if (chkbox[i].checked == true) {
			result[cnt] = chkbox[i].value;
			cnt++;
		}
	}
	var bidArray = result;
	var values = 1;

	$.ajax({
		url: "conditionCurlArray",
		type: 'post',
		dataType: "JSON",
		traditional: true, //필수
		data: {
			bidArray: bidArray,
			values: values
		},
		success: function(result) {
			for (var i = 0; i < bidArray.length; i++) {
				$("#condition" + bidArray[i]).html(result[i]);
				console.log(bidArray[i] + "  " + result[i]);
			}
			/*hideLoadingAll();*/
			$('#loading').hide();
		},
		error: function() {
			alert("error");
		}
	});
}

//이전 버튼 이벤트
function fn_prev(page, range, rangeSize) {
	var page = ((range - 2) * rangeSize) + 1;
	var range = range - 1;
	var url = "/condition";
	url = url + "?page=" + page;
	url = url + "&range=" + range;
	location.href = url;
}

//페이지 번호 클릭
function fn_pagination(page, range, rangeSize, searchType, keyword) {
	var url = "/condition";
	url = url + "?page=" + page;
	url = url + "&range=" + range;
	location.href = url;
}

//다음 버튼 이벤트
function fn_next(page, range, rangeSize) {
	var page = parseInt((range * rangeSize)) + 1;
	var range = parseInt(range) + 1;
	var url = "/condition";
	url = url + "?page=" + page;
	url = url + "&range=" + range;
	alert(url)
	location.href = url;
}

/* 로딩화면 */
//$('#loading').show();
//$('#loading').hide();

//서버버튼 클릭

function serverCurl(e) {
	const btnCurl = document.getElementById('serverCurl');

	if ($("#serverCurl").prop("checked")) {
		/*btnCurl.value = "연결취소"*/
		timerId = setTimeout(serverCurl, 10000);
		var connection = "y";

		$.ajax({
			url: "serverCurl",
			type: 'post',
			/*dataType: "JSON",*/
			data: {
				connection: connection
			},
			success: function(result) {
				
				if (result == "") {

				} else {
					$("#widget-heading").html(result.join(' / '));
				}

			},
			error: function() {

			}
		});


	} else {
		/*btnCurl.value = "서버연결"*/
		var connection = "n";

		$.ajax({
			url: "serverCurl",
			type: 'post',
			/*dataType: "JSON",*/
			data: {
				connection: connection
			},
			success: function(result) {
			},
			error: function() {
				alert("error");
			}
		});

	}

}

function serverCurldfd() {
	const btnCurl = document.getElementById('serverCurl');

	if (btnCurl.value === "서버연결") {
		/*btnCurl.value = "연결취소"*/
		/*timerId = setTimeout(checkall, 5000);*/
		var connection = "n";

		$.ajax({
			url: "serverCurl",
			type: 'post',
			/*dataType: "JSON",*/
			data: {
				connection: connection
			},
			success: function(result) {
				alert(result)
			},
			error: function() {
				alert("error");
			}
		});


	} else {
		btnCurl.value = "서버연결"
		var connection = "n";

		$.ajax({
			url: "serverCurl",
			type: 'post',
			/*dataType: "JSON",*/
			data: {
				connection: connection
			},
			success: function(result) {
			},
			error: function() {
				alert("error");
			}
		});

	}
}

