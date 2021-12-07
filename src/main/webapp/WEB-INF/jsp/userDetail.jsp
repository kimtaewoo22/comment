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
	<title>유저상세</title>
<script type="text/javascript">
	$(document).ready(function(){
		$("#modifyBtn").on("click",function(){
			var userId = this.dataset.userid;
			$.ajax ({
				url : "${pageContext.request.contextPath}/admin/userDetail/"+userId
				,method : "post"
				,data : $("form").serialize()
				,success : function(result){
					alert("수정완료");
					location.href="${pageContext.request.contextPath}/admin/main";
				}
			});
		});
		$("#deleteBtn").on("click",function(){
			var userId = this.dataset.userid;
			$.ajax({
				url : "${pageContext.request.contextPath}/admin/userDelete/"+userId
				,method : "post"
				,data : $("form").serialize()
				,success : function(result){
					alert("삭제완료");
					location.href="${pageContext.request.contextPath}/admin/main";
				}
			})
		})
	});
</script>
</head>
<body>
	<div class="col-md-7 col-lg-8">
        <h4 class="mb-3">유저 상세정보</h4>
        <form class="needs-validation" method="post" enctype="application/x-www-form-urlencoded">
          <div>
            <div>
              <label for="userId" class="form-label">userId</label>
              <input type="text" class="form-control" id="userId" name="userId" placeholder="" readonly="readonly" value="${userMap.userId}" required="">
            </div>

            <div>
              <label for="userName" class="form-label">userName</label>
              <input type="text" class="form-control" id="userNm" name="userNm" placeholder="" readonly="readonly" value="${userMap.userNm}" required="">
            </div>
			
			<div>
              <label for="adminYn" class="form-label">유저등급</label>
              <select class="form-select" id="adminYn" name="adminYn" required="">
                <option disabled="disabled">유저등급</option>
                <option value="Y" ${userMap.adminYn == 'Y' ? 'selected' : '' }>관리자</option>
                <option value="N" ${userMap.adminYn != 'Y' ? 'selected' : '' }>일반</option>
              </select>
            </div>
          </div>
        </form>
        <button type="button" class="btn btn-primary" onclick="history.go(-1)" id="listBtn">목록</button>
        <button type="button" class="btn btn-danger" data-userId="${userMap.userId}" id="deleteBtn">삭제</button>
        <button type="button" class="btn btn-primary" data-userId="${userMap.userId}" id="modifyBtn">수정</button>
    </div>	
</body>
</html>
