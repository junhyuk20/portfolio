<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<meta name="viewport" content="width=device-width", initial-scale="1">
<link rel = "stylesheet" href="/resources/css/bootstrap.css">
<title>[개인정보]</title>
<script type="text/javascript">
	function updateForm(){
			var member_id = document.getElementById('member_id').value;
			location.href ="/member/updateForm?member_id="+member_id;
		}
</script>
</head>
<body>
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
						<li class="active"><a href="/member/memberInfo?member_id=${sessionScope.loginId }">개인정보</a></li>
					</ul>
				</li>
			</ul>
		</div>
	</nav>
	<div class="container">
		<div class="col-lg-4"></div>
		<div class="col-lg-4">
			<div class="jumbotron" style="padding-top: 20px;">
				<h3 style="text-align: center:">개인정보 </h3>
				<div class="form-group">
					<input type="text" class="form-control" value="${info.member_id }" readonly="readonly" maxlength="20" >
				</div>
				<div class="form-group">
					<input type="password" class="form-control"  value="${info.member_pw }" readonly="readonly" maxlength="20">
				</div>
				<div class="form-group">
					<input type="text" class="form-control" value="${info.member_nm }" readonly="readonly" maxlength="20">
				</div>
				<input type="hidden" id="member_id" value="${info.member_id }">
				<input type="button" class="btn btn-primary form-control" value="개인정보 변경" onclick="updateForm()">
			</div>
		</div>
	</div>
	<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="/resources/js/bootstrap.js"> </script>	 

</body>
</html>