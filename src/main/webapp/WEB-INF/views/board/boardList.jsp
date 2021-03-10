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
<title>[물생활 공유]</title>
<style type="text/css">
	a, a:hover {
		color: #000000;
		text-decoration: none;
	}
</style>
<script type="text/javascript">

	function pagingFormSubmit(currentPage) {

		var form = document.getElementById('pagingForm');
		var page = document.getElementById('page');

		page.value = currentPage;
		form.submit();
	}
	
	function searchBoard(){
		var searchType = document.getElementById('searchType').value;
		var searchText = document.getElementById('searchText').value;

		document.getElementById('type').value = searchType;
		document.getElementById('text').value = searchText;

		document.getElementById('searchForm').submit();
		
	}
</script>
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
						<li><a href="/">메인</a></li>
						<li class="active"><a href="/board/boardList">물생활 커뮤니티</a></li>
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
						<li><a href="/">메인</a></li>
						<li class="active"><a href="/board/boardList">물생활 커뮤니티</a></li>
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
		<div class="row">
			<table class="table table-striped" style="text-align: center; boder: 1px solid #dddddd ">
				<thead>
					<tr>
						<th style="background-color: #eeeeee; text-align: center;">번호</th>
						<th style="background-color: #eeeeee; text-align: center;">제목</th>
						<th style="background-color: #eeeeee; text-align: center;">작성자</th>
						<th style="background-color: #eeeeee; text-align: center;">작성일</th>
						<th style="background-color: #eeeeee; text-align: center;">조회수</th>
					</tr>
				</thead>
				<!-- for문으로 게시판 작성한 거 보여주기 -->
				<tbody>
					<c:forEach var="list" items="${list }" varStatus="status">
						<tr>
							<td>${status.count }</td>
							<td>
								<a href="/board/boardRead?board_no=${list.BOARD_NO }">${list.BOARD_TITLE }</a>	
							</td>
							<td>${list.MEMBER_NM}</td>
							<td>${list.BOARD_INDATE }</td>
							<td>${list.BOARD_HITS }</td>
						</tr>
					</c:forEach>				
				</tbody>
			</table>
			
		<!-- 검색 시작 -->
			<div class="container">
			<div class="col-lg-4"></div>
			<div class="col-lg-4">
				<div class="input-group">	
					<span class="input-group-btn">
						<select class="btn btn-primary" id="searchType" >
							<option value="title">제목</option>
							<option value="name">작성자</option>
						</select>
					</span>
					
					<input type="text" class="form-control" id="searchText" placeholder="Search" aria-label="Search">
					<span class="input-group-btn">	
						<input type="button" class="btn btn-primary" value="검색" onclick="searchBoard()">
				    </span>
				</div>
			</div>      
			</div> 
			<form action="/board/boardList" method="get" id="searchForm">
				<input type="hidden" id="type" name="searchType">
				<input type="hidden" id="text" name="searchText">
			</form>
		<!-- 검색 끝 -->
			
			<!-- 페이징 폼 -->
			<form id="pagingForm" method="get" action="/board/boardList">
				<input type="hidden" name="page" id="page" />
			</form>
			<!-- 페이징 폼 -->
	
			<!-- 페이지 이동 부분 -->   
			<ul class="pager">
			<c:choose>
			<c:when test="${navi.currentPage != 1 }">
			  <li><a href="javascript:pagingFormSubmit(${navi.currentPage - 1})">이전</a></li>&nbsp;&nbsp;
			 </c:when>
			 <c:otherwise> 
			  <li><a href="javascript:pagingFormSubmit(${navi.currentPage + 1})">다음</a></li>&nbsp;&nbsp;
			</c:otherwise>	
			</c:choose>
			</ul>                   
			<!-- /페이지 이동 끝 --> 
		
			<!-- 글쓰기 -->
			<a href="/board/writeForm" class="btn btn-primary pull-right">글쓰기</a>
			<!-- 글쓰기 끝 -->
			
			
		</div>
	</div>
	
	
	
	<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="/resources/js/bootstrap.js"> </script>	 

</body>
</html>