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
<script>
	$(document).on('click', '#cencle', function(e) {
		e.preventDefault();
		window.open("about:blank", "_self").close();
	});
</script>
</head>

<body>
	<form name=form>
		<div class="container">
			<h2 class="form-signin-heading">Please organization in</h2>
			기관이름 <label for="orga_name" class="sr-only"></label>
			
			 <input	type="text" id="orga_name" name="orga_name" class="form-control"
				placeholder="Organization" autofocus>
				 <br>
				  URL 주소 
				 <label for="orga_url" class="sr-only"></label>
	 
				 <input type="text" name="orga_url"	id="orga_url" class="form-control" placeholder="URL" required>		 
			<br>
			<div class="form-group has-feedback">
				<button class="btn btn-success" type="button" id="orga_insert">기관등록</button>
				<button class="cencle btn btn-danger" id="cencle" type="button">취소</button>
			</div>
		</div>
	</form>
	<script src="/resources/common/js/condition/orgainsert.js"></script>
</body>
</html>