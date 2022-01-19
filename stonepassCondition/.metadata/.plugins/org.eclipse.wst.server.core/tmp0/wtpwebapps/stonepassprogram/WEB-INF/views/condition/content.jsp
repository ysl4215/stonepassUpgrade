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
<link rel="stylesheet"
	href="/resources/common/css/common.css">
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
	
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	

		<!-- <form target="popup_window" id="form_orga_modify"
			name="form_orga_modify" method="post" action="/conditionModify"> -->
<%-- 			<form name="form">
			<div class="form-group has-feedback">
				<label class="control-label" for="orgaName">기관이름</label> <input
					class="form-control" type="text" id="orga_name" name="orga_name"
					value="${contentboard.orga_name}" />
			</div>
			<div class="form-group has-feedback">
				<label class="control-label" for="orgaURL">기관주소</label> <input
					class="form-control" type="text" id="orga_url" name="orga_url"
					value="${contentboard.orga_url}" />
			</div>
			<input type="hidden" id ="bid" value="${contentboard.bid}" name="bid">
			<div class="form-group has-feedback">
				<button class="btn btn-success" type="button" id="orga_modify">수정</button>
				<button class="cencle btn btn-danger" type="button" id="cencle">취소</button>
				<button class="btn btn-success" type="button" id="orga_delete">삭제</button>
			</div>
			</form> --%>
		<!-- </form> -->
	<form name=form>
		<div class="container">
			<h2 class="form-signin-heading">Please organization in</h2>
			기관이름 <label for="orga_name" class="sr-only">Email address</label>
			
			 <input	type="text" id="orga_name" name="orga_name" class="form-control"
				placeholder="Organization" value="${contentboard.orga_name}" autofocus>
				
				 <br>
				  URL 주소 <label for="orga_url" class="sr-only">URL</label>
				
				 <input type="text" value="${contentboard.orga_url}" name="orga_url"
				 id="orga_url" class="form-control" placeholder="URL" required>		
				  
				  	<input type="hidden" id ="bid" value="${contentboard.bid}" name="bid">
			<br>
			<div class="form-group has-feedback">
				<button class="btn btn-success" type="button" id="orga_modify">수정</button>
				<button class="cencle btn btn-danger" id="cencle" type="button">취소</button>
				<button class="btn btn-success" type="button" id="orga_delete">삭제</button>
			</div>
		</div>
	</form>



<script src="/resources/common/js/condition/content.js"></script>
</body>

</html>