<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<meta name="viewport" content="width=device-width", initial-scale="1">
<link rel = "stylesheet" href="/resources/css/bootstrap.css">
<link rel = "stylesheet" href="/resources/css/custom.css">
<title>[슬기로운 물생활]</title>
<style type="text/css">
	
</style>
</head>
<body>

	
	<!-- 로그인 했을 때와 안했을 때 -->
<ul>	
	<c:choose>
		<c:when test="${not empty sessionScope.loginId }">
			${sessionScope.loginId } 님 환영합니다
			<nav class="navbar navbar-default">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
						aria-expanded="false">
						<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="/">슬기로운 물생활</a>
				</div>
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li  class="active"><a href="/">메인</a></li>
						<li><a href="/board/boardList">물생활 커뮤니티</a></li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown">
							<a href="#" class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-haspopup="true"
								aria-expanded="false">접속하기 <span class="caret"></span>
							</a>
							<ul class="dropdown-menu">
								<li><a href="/member/logOut">로그아웃</a></li>
								<li><a href="/member/memberInfo?member_id=${sessionScope.loginId }">개인정보</a></li>
							</ul>
						</li>
					</ul>
				</div>
			</nav>
		</c:when>
		
		<c:otherwise>
		<nav class="navbar navbar-default">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
						aria-expanded="false">
						<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="/">슬기로운 물생활</a>
				</div>
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li  class="active"><a href="/">메인</a></li>
						<li><a href="/board/boardList">물생활 커뮤니티</a></li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown">
							<a href="#" class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-haspopup="true"
								aria-expanded="false">접속하기 <span class="caret"></span>
							</a>
							<ul class="dropdown-menu">
								<li><a href="/member/loginForm">로그인</a></li>
								<li><a href="/member/joinForm">회원가입</a> </li>
							</ul>
						</li>
					</ul>
				</div>
				</nav>
			</c:otherwise>
	</c:choose>
</ul>
<!-- 로그인 했을 때와 안했을 때  end-->

	<div class="container">
		<div class="jumbotron">
			<div class="container">
				<h3>슬기로운 물생활 소개</h3>
				<p>슬기로운 물생활에 오신 것을 환영합니다. 
				      저희 사이트는 열대어를 키우는 모든 분들과 서로의 팁을 공유하여 모두가 즐겁고 슬기롭게 물생활을 이어갈 수 있도록  앞 장서는 커뮤니티 사이트 입니다.</p>
				<p><a class="btn btn-primary btn-pull" href="/board/boardList">커뮤니티 gogo!</a></p>
			</div>
		</div>
	</div>
	<div class="container">
		<div id="myCarousel" class="carousel slide" data-ride="carousel">
			<ol class="carousel-indicators">
				<li data-targer="#myCarousel" data-slide-to="0" class="active"></li>
				<li data-targer="#myCarousel" data-slide-to="1"></li>
				<li data-targer="#myCarousel" data-slide-to="2"></li>			
			</ol>
			<div class="carousel-inner">
				<div class="item active">
					<img src="/resources/images/guppy4.jpg" alt="구피1">
				</div>
				<div class="item">
					<img src="/resources/images/guppy7.jpg" alt="구피7">
				</div>
				<div class="item">
					<img src="/resources/images/guppy8.jpg" alt="구피8">
				</div>
				<div class="item">
					<img src="/resources/images/guppy9.jpg" alt="구피9">
				</div>
			</div>
			<a class="left carousel-control" href="#myCarousel" data-slide="prev">
				<span class="glyphicon glyphicon-chevron-left"></span>
			</a>
			<a class="right carousel-control" href="#myCarousel" data-slide="next">
				<span class="glyphicon glyphicon-chevron-right"></span>
			</a>
		</div>
	</div>
	<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="/resources/js/bootstrap.js"> </script>	 

</body>
</html>