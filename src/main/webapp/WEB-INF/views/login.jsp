<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>会員認証</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="<c:url value='/resources/js/login.js'/>"></script>
	<style>
        body {
            font-family: "Noto Sans JP", "Meiryo", "Hiragino Kaku Gothic ProN", "Yu Gothic", sans-serif;
        }
    </style>
</head>
<body>
	<div class="w-50 mx-auto d-flex flex-column align-items-center">
		<nav class="navbar bg-body-secondary w-100">
  			<div class="container-fluid d-flex justify-content-center">
    			<span class="navbar-brand mb-0 h1 text-center">会員認証</span>
  			</div>
		</nav>
    	<form id="loginForm" action="/login" method="post">
        	<label class="d-inline-block w-25" for="member_id">会員ID　</label>
        	<input class="mt-4" type="text" id="member_id" name="member_id">
        	<br>
        	<label class="d-inline-block w-25" for="member_password">パスワード　</label>
        	<input class="mt-2" type="password" id="member_password" name="member_password">
        	<br>
        	<button type="submit" class="btn btn-primary mt-4 w-100">ログイン</button>
    	</form>
    	<p id="error_message" class="mt-2" style="color: red;">${login_result}</p>
	</div>
</body>
</html>