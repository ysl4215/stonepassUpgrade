<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>

<meta charset="UTF-8">

<!-- common CSS -->
<link rel="stylesheet" href="/resources/common/css/common.css">
<link rel="stylesheet"
	href="/resources/common/css/condition/monitor.css">
<%-- <link rel="stylesheet"
	href="<c:url value='/resources/common/css/common.css'/>"> --%>

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">

<title>스톤패스 연결확인 프로그램</title>
<style>
body {
	padding-top: 0px;
	padding-bottom: 30px;
}

.spinner-border {
	position: absolute;
	top: 50%;
	left: 50%;
	z-index: 1;
	width: 3rem;
	height: 3rem;
}
</style>
</head>

<body>
	<nav class="navbar navbar-dark bg-dark">
		<a class="navbar-brand" href="#">스톤패스 연결확인 프로그램</a>
	</nav>

	<article class="all">
		<br> <br>
		<!-- class="board" -->
		<div class="board">
			<div>
				<button type="button" class="btn btn-sm btn-primary"
					onclick="insert()">기관 등록</button>

				<button type="button" class="btn btn-sm btn-primary"
					onclick="email()">E-mail</button>
			</div>

			<br> <!-- <span> <input value="서버연결" type="button"
				onclick="serverCurl()" id="serverCurl"
				name="serverCurl">
			</span> -->
			<label class="switch-button"> <input value="서버연결" type="checkbox" class="chkselect"
				onchange="serverCurl(this)" id="serverCurl"
				name="serverCurl"><span class="onoff-switch">
			</span> </label>
			
			<%-- <label class="switch-button"> <input
												class="chkselect" value="${list.bid}" type="checkbox"
												onchange="kdw(this)" id="checkBoxId" name="checkBoxId" /> <span
												class="onoff-switch"></span>
										</label> --%>


			<!--로딩바-->
			<div id="loading" style="margin-left: 0px;">
				<img src="/resources/common/img/loading.gif">
				<p>연결중입니다..잠시기다려주세요.</p>
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
							<th>
								<div>
									<label class="switch-button"> <input value=""
										type="checkbox" id="checkAll" name="checkall"
										onchange="checkall()" /> <span class="onoff-switch"></span>
									</label> <input type="hidden" id="result" name="result">
								</div> CURL
							</th>
							<th style="text-align: center">연결상태</th>
						</tr>
					</thead>

					<tbody>
						<c:choose>
							<c:when test="${empty conditionlist }">
								<tr>
									<td colspan="5" align="center">데이터가 없습니다.</td>
								</tr>
							</c:when>

							<c:when test="${!empty conditionlist}">
								<c:forEach var="list" items="${conditionlist}">
									<tr>
										<td><c:out value="${list.bid}" /></td>
										<td><c:out value="${list.orga_name}" /></td>

										<td><a href="#" onclick="modify(${list.bid})"><c:out
													value="${list.orga_url}" /></a></td>

										<td><label class="switch-button"> <input
												class="chkselect" value="${list.bid}" type="checkbox"
												onchange="kdw(this)" id="checkBoxId" name="checkBoxId" /> <span
												class="onoff-switch"></span>
										</label></td>
										<td>
											<div id="condition${list.bid}" style="text-align: center"></div>
										</td>
									</tr>
								</c:forEach>
							</c:when>
						</c:choose>
					</tbody>
				</table>

				<!-- pagination{s} -->
				<div id="paginationBox">
					<ul class="pagination">
						<c:if test="${pagination.prev}">
							<li class="page-item"><a class="page-link" href="#"
								onClick="fn_prev('${pagination.page}', '${pagination.range}', '${pagination.rangeSize}')">
									Previous</a></li>
						</c:if>

						<c:forEach begin="${pagination.startPage}"
							end="${pagination.endPage}" var="idx">
							<li
								class="page-item <c:out value="${pagination.page == idx ? 'active' : ''}"/> ">
								<a class="page-link" href="#"
								onClick="fn_pagination('${idx}', '${pagination.range}', '${pagination.rangeSize}')">
									${idx} </a>
							</li>
						</c:forEach>
						<c:if test="${pagination.next}">
							<li class="page-item"><a class="page-link" href="#"
								onClick="fn_next('${pagination.range}', 
				'${pagination.range}', '${pagination.rangeSize}')">Next</a></li>
						</c:if>
					</ul>
					<!-- pagination{e} -->
				</div>
			</div>
		</div>



		<div class="monitor">
			<div class="col-md-12">
				<div class="card-shadow-primary profile-responsive card-border mb-3 card">
					<div class="dropdown-menu-header">
						<div class="dropdown-menu-header-inner bg-danger">
							<div>
								<h5 class="menu-header-title">CURL 연결상태불량 LIST</h5>
							</div>
						</div>
					</div>
					<ul class="list-group list-group-flush">
						<li class="list-group-item">
							<div class="widget-content p-0">
								<div class="widget-content-wrapper">
									<div class="widget-content-left">
										<div class="widget-heading" id="widget-heading"></div>
									</div>
								</div>
							</div>
						</li>
				
					</ul>
				</div>
			</div>
		</div>
	</article>

	<script src="/resources/common/js/condition/condition.js"></script>

</body>
</html>

