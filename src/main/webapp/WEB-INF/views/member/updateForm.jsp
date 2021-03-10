<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<meta name="viewport" content="width=device-width", initial-scale="1">
<link rel = "stylesheet" href="/resources/css/bootstrap.css">
<title>[개인정보 변경]</title>
<script type="text/javascript">
	function formCheck(){
			var member_id = document.getElementById('member_id').value;
			var member_pw = document.getElementById('member_pw').value;
			var member_pw_ck = document.getElementById('member_pw_ck').value;
			var member_nm = document.getElementById('member_nm').value;
			if(member_id ==  "" || member_id.length == 0){
					alert('아이디를 입력해 주세요');
					return false;
				}
			if(member_pw == '' || member_pw.length == 0){
					alert('비밀번호를 입력해 주세요');
					return false;
			}
			if(member_pw_ck != member_pw){
					alert('동일한 비밀번호를 입력해 주세요');
					return false;
			}
			if(member_nm == '' || member_nm.length == 0){
					alert('이름을 입력해 주세요');
					return false;
			}
			return true;
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
						<li class="active"><a href="/member/updateForm">개인정보 변경</a></li>
					</ul>
				</li>
			</ul>
		</div>
	</nav>
	<div class="container">
		<div class="col-lg-4"></div>
		<div class="col-lg-4">
			<div class="jumbotron" style="padding-top: 20px;">
				<form action="/member/update" method="post" onsubmit="return formCheck()">
					<h3 style="text-align: center:">개인정보 변경 화면</h3>
					<div class="form-group">
						<input type="text" class="form-control" id="member_id" name="member_id"  value="${info.member_id }" readonly="readonly" maxlength="20" >
					</div>
					<div class="form-group">
						<input type="password" class="form-control" id="member_pw" name="member_pw"  value="${info.member_pw }" maxlength="20">
					</div>
					<div class="form-group">
						<input type="password" class="form-control" placeholder="비밀번호 확인"  id="member_pw_ck"  maxlength="20">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" id="member_nm" name="member_nm"  value="${info.member_nm }" maxlength="20">
					</div>
					<input type="submit" class="btn btn-primary form-control" value="수정">
				</form>
			</div>
		</div>
	</div>
	<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="/resources/js/bootstrap.js"> </script>	 

</body>
</html>