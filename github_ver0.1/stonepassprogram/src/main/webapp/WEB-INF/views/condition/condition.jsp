<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>

<link rel="icon" type="image/png" href="http://example.com/myicon.png">

<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>


<meta charset="UTF-8">

<!-- common CSS -->
<link rel="stylesheet"
	href="<c:url value='/resources/common/css/commona.css'/>">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">

<title>스톤패스 연결확인 프로그램</title>
<style>
body {
	padding-top: 70px;
	padding-bottom: 30px;
}
</style>

<script>
	$(document)
			.on(
					'click',
					'#btnInsert',
					function(e) {
						e.preventDefault();

						let popUrl = "/condition/organization";
						let popOption = "width = 650px, height=550px, top=300px, left=300px, scrollbars=yes";

						window.open(popUrl, "기관 등록", popOption);
					});

	function openPopup() {

		/*  첫 번째 파라미터는 팝업창의 url 주소. 두 번째 파라미터는 팝업창의 이름. 마지막 파라미터는 팝업창에 대한 설정 */
		window
				.open(
						"aa",
						popOpen,
						[ 'fullscreen=no,menubar=no,status=no,toolbar=no,titlebar=no,location=no,scrollbar=auto,width=650,height=550' ]);

		iniform.target = "popOpen";

		iniform.action = " URL명";

		iniform.submit();
	}

	
/* 	$(document).ready(function() { */
		
		/* const a = $("#aaa").val();
		const b = $("#bbb").val();
		const c = (a + b)
		console.log(c);
		
			
		
		$("#"+c).change(function() {
			if ($("#"+c).is(":checked")) {

				$.ajax({
					url : "conditionCurl",
					type : 'post',
					data : {arr : "o"},
					success : function(data) {
					
					},
					error : function() {
						alert("error");
					}

				});

			} else {

				alert("체크박스 체크 해제!");
			}
		});
	}); */
	
	
	
function kdw(kdwthis){
		var bid = kdwthis.value
		
		var values = 0;
		if(kdwthis.checked){
			values=1
		}else{
			values=0
		}
		
		$.ajax({
			url : "conditionCurl",
			type : 'post',
			data : {arr : values, bid : bid},
			success : function(data) {
				
			},
			error : function() {
				alert("error");
			}
		});
		}
</script>
</head>

<body>
	<article>
		<div class="container">
			<div>
				<button type="button" class="btn btn-sm btn-primary" id="btnInsert">기관
					등록</button>
			</div>

			<div class="table-responsive">
				<table class="table table-striped table-sm">
					<colgroup>
						<col style="width: 5%;" />
						<col style="width: 20%;" />
						<col style="width: 20%;" />
						<col style="width: 10%;" />
						<col style="width: 10%;" />
					</colgroup>

					<thead>

						<tr>

							<th>NO</th>

							<th>기관이름</th>

							<th>기관주소</th>

							<th>CURL</th>

							<th>연결상태</th>

						</tr>

					</thead>

					<tbody>

						<c:choose>

							<c:when test="${empty boardList }">

								<tr>
									<td colspan="5" align="center">데이터가 없습니다.</td>
								</tr>

							</c:when>

							<c:when test="${!empty boardList}">
							
								<c:forEach var="list" items="${boardList}" >

						      		<tr>
							
										<td><c:out value="${list.bid}" /></td>

										<td><c:out value="${list.orga_name}" /></td>

										<td><c:out value="${list.orga_url}" /></td>

										<td>
										<c:out value="${list.bid}"></c:out>
										
										
									<%-- <input type="hidden" id="aaa" value="checkBoxId${list.bid}" /> --%>
										
																						
											<label class="switch-button">
											
											kdw(this)
											 <input value="${list.bid}" type="checkbox" onchange="kdw(this)" id="checkBoxId" name="checkBoxId" />
								
											 <span class="onoff-switch"></span>
											 
											</label>
											
										</td>
										<td><c:out value="" /></td>
									</tr>
								</c:forEach>
							</c:when>
						</c:choose>
					</tbody>
				</table>
			</div>
		</div>
	</article>
</body>
</html>
