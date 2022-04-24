<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="../css/style.css" />
<link rel="stylesheet" href="../css/index.css" />
<link rel="stylesheet" href="../css/team_members.css" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="../js/main.js"></script>

<style>
.header-menu {
	text-decoration: none;
	color: black;
}
</style>

<!-- 상단 Header Start -->
<header>
	<nav>
		<c:if test="${empty loginInfo}">
		<div class="header-nav-member">
			<div class="header-nav-login">
				<a href="#">Login</a> &nbsp; <a href="#">Join</a>
			</div>
		</div>
		</c:if>
		<c:if test="${not empty loginInfo}">
		<div class="header-nav-member">
			<div class="header-nav-logout">
				<a href="#">MyPage</a> &nbsp; <a href="#">Logout</a>
			</div>
		</div>
		</c:if>
		
		<div class="header-nav-menu">
			<div id="header-menu-img-area">
				<a href="${root}/index.jsp"><img id="header-menu-img"
					src="img/dog_logo.png" alt="logo"></a>
			</div>
			<div class="header-menu-list">
				<a class="header-menu" href="#">CLUB INFO</a> <a class="header-menu"
					href="${root}team_members.jsp">TEAM MEMBERS</a> <a class="header-menu"
					href="#">MANAGEMENT</a>
			</div>
			<div class="header-menu-search">
				<form action="#">
					<input class="search-text" type="text">
					<!-- <input class="search-btn" type="submit"> -->
					<input class="search-btn" type="image" src="img/btn_search.png"
						name="submit" value="submit">
				</form>
			</div>
		</div>
	</nav>
</header>
<!-- 상단 Header End -->