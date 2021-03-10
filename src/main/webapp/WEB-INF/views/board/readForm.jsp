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
<title>[게시글 읽기]</title>
<style type="text/css">
	a, a:hover {
		color: #000000;
		text-decoration: none;
	}
</style>
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="/resources/js/bootstrap.js"> </script>	 
<script type="text/javascript">
	function boardUpdateForm(){
			var board_no = document.getElementById('board_no').value;
			location.href="/board/updateForm?board_no="+board_no;
		}
	
	// 댓글 등록 
	 function cmt_submit(){
		 var comment_contents = $("#comment_contents").val()
		 
		 if(comment_contents == '') {
				alert("댓글을 작성 해주세요");
				return false;
			 }
		 if(${empty sessionScope.loginId}){
				alert("댓글을 작성하시려면 로그인 후 이용해 주세요");
				return false;
			 }

		 $.ajax({
				url: "/comment/commentWrite",
				type: "post",
				data: {
						comment_contents: $("#comment_contents").val(),
						board_no: ${read.BOARD_NO}
						
					},
				success: function(){
						alert("댓글 등록 성공");
						setTimeout(function(){
							location.reload();
							},10);
					},
				erorr: function(e) {
						alert("댓글 입력 실패");
						console,log(e);
					}
			});
	
		}

 	function commentUpdateForm(comment_no, comment_contents) {

		
 		//해당 리플번호를 붙여 생성한 <div>태그에 접근
		var form = document.createElement("form");

		form.setAttribute("action","/board/commentUpdate");
		form.setAttribute("method","post");

		documnet.body.appendChild(form);

		var input_id = document.createElement("input");

		input_id.setAttribute("type", "hidden");
		input_id.setAttribute("name", "comment_no");
		input_id.setAttribute("value", comment_no);
		input_id.setAttribute("type", "text");
		input_id.setAttribute("name", "comment_contents");
		input_id.setAttribute("value", comment_contents);

		form.appendChild(input_id);
		form.submit();		
		
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
						<li  class="active"><a href="/board/boardList">물생활 커뮤니티</a></li>
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
	
	
	
	<div class="container">
		<div class="row">
			<table class="table table-striped" style="text-align: center; boder: 1px solid #dddddd ">
				<thead>
					<tr>
						<th colspan="3" style="background-color: #eeeeee; text-align: center;">게시판 글보기 </th>
					
					</tr>
				</thead>
				<tbody>
						<tr>
							<td style="width: 20%">글 제목</td>
							<td colspan="2">${read.BOARD_TITLE }</td>
						</tr>
						<tr>
							<td>작성자</td>
							<td colspan="2">${read.MEMBER_NM }</td>
						</tr>
						<tr>
							<td>작성일자</td>
							<td colspan="2">${read.BOARD_INDATE }</td>
						</tr>
						<tr>
							<td>내용</td>
							<td colspan="2" style="min-height: 200px; text-align: center;">${read.BOARD_CONTEXT }</td>
						</tr>
						<tr>
							<td>첨부파일</td>
							<td colspan="2">
								<a href="/board/download?board_no=${read.BOARD_NO }">${read.ORIGINALFILE }</a> 
							</td>
						</tr>
				</tbody>
			</table>
			
			<!-- 게시글 수정 삭제 목록 버튼 -->
			<c:if test="${sessionScope.loginId == read.MEMBER_ID }">
				<input type="hidden"  id="board_no" value="${read.BOARD_NO }">
				<input type="button" class="btn btn-primary" value="글 수정" onclick="boardUpdateForm()" > 
				<a class="btn btn-primary" href="/board/boardDelete?board_no=${read.BOARD_NO }" onclick="return confirm('정말로 삭제 하시겠습니까?')" >글 삭제</a>
			</c:if>
			<a href="/board/boardList" class="btn btn-primary" >목록이동</a>
			<!-- 게시글 수정 삭제 목록 버튼 끝-->
			
			
			<!-- 댓글 작성 -->

			<div class="my-3 p-3 bg-white rounded shadow-sm" style="padding-top: 10px">
				<div class="row">
					<div class="col-sm-10">
						<textarea rows="1" path="content" id="comment_contents"  class="form-control" placeholder="댓글을 입력 해 주세요." ></textarea>
					</div>
					<div class="col-sm-2">
						<button onclick="cmt_submit()" id="btnReplySave" class="btn btn-sm btn-primary"  style="width: 100%; margin-top: 10px"> 댓글 등록 </button>
					</div>
				</div>
			</div>
			<!-- 댓글 작성 끝 ->
			

		<!-- 댓글 보여 주기  -->
		<label>댓글 목록</label>
		<br>
			<table border="1" id="comment" class="table table-striped" style="text-align: center; boder: 1px solid #dddddd ">
					<c:forEach var="list" items="${list }">
					<tr>
						<td class="td1">${list.member_nm }</td>
						<td class="td2">${list.comment_contents }</td>
						<td class="td3">${list.comment_indate }</td>
						<c:if test="${sessionScope.loginId == list.member_id }">
							
							<td>
								<a href="/comment/commentDelete?comment_no=${list.comment_no }&board_no=${list.board_no}">삭제</a>
							</td>
						</c:if>
					</tr>
				</c:forEach>
			</table>				
		<!-- 댓글 보여 주기 끝 --> 
			
			<div id="div">
			
			</div>
			
		</div>
	</div>
</body>
</html>