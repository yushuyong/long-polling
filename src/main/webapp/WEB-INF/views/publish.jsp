<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
	<head>
		<meta charset="utf-8">
		<title>Spring Async Future example</title>
		<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
	</head> 
	<body>
		Publish text
		<form action="publish" method="post">
			<input type="text" name="publish" />
			<input type="submit">
		</form>
	</body>
</html>