<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<script type="text/javascript">
function pageClick(pageNo){
	location.href="${pageContext.request.contextPath}/admin/user?currentPage="+pageNo;
// 	location.href="${pageContext.request.contextPath}/admin/user";
}

$(document).ready(function(){
	var totalPage = "${pagination.totalPage}";
	var currentPage = "${pagination.currentPage}";
	
	
	pageHtml(totalPage, currentPage);
	
	function pageHtml(totalPage, currentPage){
		var pageHtml = '';
		
		pageHtml +=  '<li class="page-item" id="Previous">';
		pageHtml +=  '<a class="page-link" href="javascript:pageClick(1)">Previous</a>';
		pageHtml +=  '</li>';
		
		for(i=1; i<=totalPage; i++){
			if(i == currentPage){
				pageHtml += '<li class="page-item active" aria-current="page">';
			}else{
				pageHtml += '<li class="page-item" aria-current="page">';
			}
			pageHtml += '<a class="page-link" href="javascript:pageClick('+i+');">'+i+'</a>';
			pageHtml += '</li>';
		}
		pageHtml +='<li class="page-item">';
		pageHtml +='<a class="page-link" href="javascript:pageClick('+totalPage+');">Next</a>';
		pageHtml +='</li>';
		
		$("#pagenation").append(pageHtml);
	}
});
</script>
</head>
	<body>
		<nav aria-label="...">
		  <ul class="pagination" id="pagenation" style="justify-content: center;">
		  </ul>
		</nav>
	</body>
</html>