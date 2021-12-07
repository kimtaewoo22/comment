<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<!-- 	<link rel="icon" href="data:,"> -->
	<link rel="shortcut icon" href="#">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<%-- 	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"> --%>
<!-- 	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"> -->
	<title>유저리스트</title>
<script type="text/javascript">
	function userModify(userId){
		location.href = "${pageContext.request.contextPath}/admin/userDetail?userId="+userId;
	};
</script>
</head>
<body>
	<input type="hidden" id="pageNo" name="pageNo" value="1"/>
	<table id="userTable" class="table table-condensed" >
		<thead>
			<tr>
			<th>유저이름</th>
			<th>USER ID</th>
			<th>Admin</th>
			<th>등록일</th>
			<th>수정일</th>
			</tr>
		</thead>
		<c:if test="${not empty userList}">
			<c:forEach var="i" items="${userList}" varStatus="status">
				<tbody>
					<tr>
						<td>${i.userNm}</td>
						<td>${i.userId}</td>
						<td>${i.adminYn == 'Y' ? '관리자':'일반'}</td>
						<td>${i.createDate}</td>
						<td>${i.modifyDate}</td>
						<td><button type="button" class="btn btn-info" onclick="javascript:userModify(${i.userId})" id="userModify">수정</button></td>
					</tr>
				</tbody>
			</c:forEach>
		</c:if>
	</table>
	<button type="button" class="btn btn-primary" onclick="location.href='${pageContext.request.contextPath}/admin/main'">목록</button>
	<div id="paging" style="display:${not empty userList ? 'block':'none'};">
		<jsp:include page="/WEB-INF/jsp/paging.jsp"/>
	</div>
</body>
</html>
