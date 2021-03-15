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
<script type="text/javascript">
	function formCheck() {
			var board_title = $("#board_title").val();
			var board_context = $("#board_context").val();

			if(board_title == '' || board_title.length == 0) {
					alert("제목을 입력해 주세요.");
					return false;
				}
			if(board_context == '' || board_context.length == 0){
					alert("내용을 입력해 주세요.");
					return false;
				}
				return true;
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
		<div class="row">
			<form action="/board/boardWrite" method="post" onsubmit="return formCheck()" enctype="multipart/form-data">
				<table class="table table-striped" style="text-align: center; boder: 1px solid #dddddd ">
					<thead>
						<tr>
							<th colspan="2" style="background-color: #eeeeee; text-align: center;">게시판 글쓰기 </th>
						
						</tr>
					</thead>
					<tbody>
							<tr>
								<td><input type="text" class="form-control" placeholder="글 제목" id="board_title" name="board_title" maxlength="50" ></td>
							</tr>
							<tr>
								<td><textarea class="form-control" placeholder="글 내용" id="board_context" name="board_context" maxlength="2000" style="height: 350px;"></textarea></td>
							</tr>
					</tbody>
				</table>
				<input type="file" name="upload" >  
				<input type="submit" class="btn btn-primary pull-right" value="글등록">
			</form>
		</div>
	</div>
	
	
	
	<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="/resources/js/bootstrap.js"> </script>	 

</body>
</html>