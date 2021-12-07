<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<link rel="icon" href="data:,">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<%-- 	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"> --%>
<!-- 	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"> -->
	<title>관리자 페이지</title>
<script type="text/javascript">
	$(document).ready(function(){
		$("#userBtn").on("click",function(){
			location.href="${pageContext.request.contextPath}/admin/user";
		});
		
		$("#commentBtn").on("click",function(){
			location.href="${pageContext.request.contextPath}/admin/user";
		});
	});
		
</script>
</head>
<body>
		<button type="button" class="btn btn-primary" id="userBtn">유저관리</button>
		<button type="button" class="btn btn-info" id="commentBtn">댓글관리</button>
</body>
</html>
