<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:choose>
		<c:when test="${sessionScope.account==null }">
			<div class="col-sm-6">
				<ul class="list-inline right-topbar pull-right">
					<li><a href="${pageContext.request.contextPath}/login">Dang
							nhap</a> | <a href="${pageContext.request.contextPath }/register">Dang
							ky</a></li>
					<li><i class="search fa fa-search search-button"></i></li>
				</ul>
			</div>
		</c:when>
		<c:otherwise>
			<div class="col-sm-6">
				<ul class="list-inline right-topbar pull-right">
					<li><a
						herf="${pageContext.request.contextPath }/member/myaccount">${sessionScope.account.fullName}</a>
						/ <a href="${pageContext.request.contextPath }/logout">Dang
							Xuat</a></li>
					<li><i class="search fa fa-search search-button"></i></li>
				</ul>
			</div>
		</c:otherwise>
	</c:choose>
</body>
</html>