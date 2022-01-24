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
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
	integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
	integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13"
	crossorigin="anonymous"></script>

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
		<a class="navbar-brand" href="condition">스톤패스 연결확인 프로그램</a>
	</nav>
	<br>
	<article class="all">
		<span class="insertButton">
			<button type="button" class="btn btn-sm btn-primary"
				onclick="insert()">기관 등록</button>

			<button type="button" class="btn btn-sm btn-primary"
				onclick="email()">E-mail</button>
		</span>
		<div class="col-md-6">
			<label class="switch-button"> <input value="서버연결"
				type="checkbox" onchange="serverCurl(this)" id="serverCurl"
				name="serverCurl"><span class="onoff-switch"> </span>
			</label>

			<div class="card-hover-shadow-2x mb-3 card">
				<div class="card-header">연결상태불량 기관</div>
				<div class="card-body">
					<p class="mb-0">
					<div class="widget-heading" id="widget-heading"></div>
					</p>

				</div>
				<div class="d-block text-right card-footer"></div>
			</div>
		</div>
		<!-- class="board" -->
		<div class="board">
			<!-- <div>
				<button type="button" class="btn btn-sm btn-primary"
					onclick="insert()">기관 등록</button>

				<button type="button" class="btn btn-sm btn-primary"
					onclick="email()">E-mail</button>
			</div> -->
			<br>
			<!-- search{s} -->
			<div class="form-group row justify-content-center">
				<div class="w100">
					<select class="form-control form-control-sm" name="searchType"
						id="searchType">
						<option value="testTitle">기관이름</option>
						<option value="testContent">기관주소</option>
					</select>
				</div>

				<div class="w300">
					<input type="text"
						<%-- value="${pagination.keyword}" --%> class="form-control form-control-sm"
						name="keyword" id="keyword">
				</div>

				<div>
					<button class="btn btn-sm btn-primary" name="btnSearch"
						id="btnSearch">검색</button>
				</div>
			</div>
			<!-- search{e} -->
			<!--로딩바-->
			<div id="loading" style="margin-left: 0px;">
				<img src="/resources/common/img/loading.gif">
				<p>연결중입니다..잠시기다려주세요.</p>
			</div>

			<div class="table-responsive">
				<table class="table table-striped table-sm">
					<label> <select class="dataTable-selector" id="listSize"
						onchange="listSize(this.options[this.selectedIndex].value);">
							<option value="10" id="option1"
								<c:if test="${pagination.getListSize() == 10 }">selected="selected"</c:if>>10</option>
							<option value="30" id="option2"
								<c:if test="${pagination.getListSize() == 30 }">selected="selected"</c:if>>30</option>
							<option value="50" id="option3"
								<c:if test="${pagination.getListSize() == 50 }">selected="selected"</c:if>>50</option>
							<option value="100" id="option4"
								<c:if test="${pagination.getListSize() == 100 }">selected="selected"</c:if>>100</option>
					</select> entries per page
					</label>


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
								<c:forEach varStatus="status" var="list"
									items="${conditionlist}">
									<tr>

										<td><c:out
												value="${(pagination.listCnt-status.index)-((pagination.page-1)*pagination.listSize)}" /></td>

										<%-- <td><c:out value="${list.bid}" /></td> --%>
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
								onClick="fn_prev('${pagination.page}', '${pagination.range}',
								 '${pagination.rangeSize}','${pagination.listSize}',
								 '${search.searchType}', '${search.keyword}')">
									Previous</a></li>
						</c:if>

						<c:forEach begin="${pagination.startPage}"
							end="${pagination.endPage}" var="idx">
							<li
								class="page-item <c:out value="${pagination.page == idx ? 'active' : ''}"/> ">
								<a class="page-link" href="#"
								onClick="fn_pagination('${idx}', '${pagination.range}',
								 '${pagination.rangeSize}', '${pagination.listSize}',
								 '${search.searchType}', '${search.keyword}')">
									${idx} </a>
							</li>
						</c:forEach>

						<c:if test="${pagination.next}">
							<li class="page-item"><a class="page-link" href="#"
								onClick="fn_next('${pagination.range}', 
				'${pagination.range}', '${pagination.rangeSize}','${pagination.listSize}',
				'${search.searchType}', '${search.keyword}')">Next</a></li>
						</c:if>
					</ul>
					<!-- pagination{e} -->
				</div>
			</div>
		</div>

	</article>
	<script src="/resources/common/js/condition/condition.js"></script>
</body>
</html>

