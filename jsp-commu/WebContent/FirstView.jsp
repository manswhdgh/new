<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<html>
	<head>
		<title>ùȭ��</title>
	</head>
	<body>		
			<!-- �α��� �ȵǾ��� ��� -->
	<c:if test="${sessionScope.sessionID==null}">
		<br><br><br>
		<br><br><br>
		<img width = "450px" height = "450px" src="img/welcome2.jpg">
		<br><br>	
	</c:if>	
			<!-- �α��� -->
	<c:if test="${sessionScope.sessionID!=null}">	
		<br><br><br>
		<font size=6 color="skyblue">${sessionScope.sessionID}</font>
		<font size=6>�� ȯ���մϴ�.</font>
	</c:if>	
	
	</body>
</html>



