<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <title>数当てゲーム</title>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="<c:url value='/resources/js/gameForm.js'/>"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="<c:url value="/resources/css/gameForm.css"/>" rel="stylesheet">
</head>
<body>
	<div class="w-50 mx-auto d-flex flex-column align-items-center">
		<nav class="navbar bg-body-secondary w-100">
  			<div class="container-fluid d-flex justify-content-center">
    			<span class="navbar-brand mb-0 h1 text-center">数当てゲーム</span>
  			</div>
		</nav>
	    
	    <p id="current_point" class="w-100 text-end">現在のポイント: ${pointInfo.point}</p>
	    <p class="w-100 text-start">隠れ数字: ***</p>
	
	    <form id="gameForm" class="w-100 text-start">
	    	<input type="hidden" id="hidden_number" name="hidden_number" value="${pointInfo.hidden_number}">
	       	<input type="hidden" id="game_count" name="game_count" value="${pointInfo.game_count}">
	        <input type="hidden" id="point" name="point" value="${pointInfo.point}">
	    	
	        <label>入力: </label>
	        <input type="text" id="result_answer" name="result_answer" maxlength="3">
	        <button type="button" id="button" class="btn btn-primary" ${pointInfo.game_act_fig == 1 ? 'disabled' : ''}>確認</button>
	    </form>
	
	    <table class="w-100 mt-5">
	        <thead>
	            <tr>
	                <th>入力回数</th>
	                <th>入力情報</th>
	                <th>判定結果</th>
	            </tr>
	        </thead>
	        <tbody>
	            <c:forEach var="result" items="${resultList}" varStatus="status" >
	                <tr>
	                    <td>${status.count}回目</td>
	                    <td>${result.result_answer}</td>
	                    <td>${result.result_content}</td>
	                </tr>
	            </c:forEach>
	            <c:forEach var="i" begin="${fn:length(resultList) + 1}" end="10">
	                <tr>
	                    <td>${i}回目</td>
	                    <td id="result_answer_${i}"></td>
	                    <td id="result_content_${i}"></td>
	                </tr>
	            </c:forEach>
	        </tbody>
	    </table>
    </div>
    <jsp:include page="popup.jsp" />
</body>
</html>