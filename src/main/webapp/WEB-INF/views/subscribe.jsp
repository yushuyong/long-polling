<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
	<head>
		<meta charset="utf-8">
		<title>Spring Async Future example</title>
		<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
		
		<spring:url var="updateUrl" value="/subscribe"/>
		
		<script>
		  $(document).ready(function(){
				function update(e){
					
				  	$.ajax({
			            contentType: "application/json;charset=UTF-8",
			            type: 'POST',
						url: '${updateUrl}'
			        }).success(function(data, textStatus, jqXHR){
			        	$('#jobStatus').html(data);
			        }).fail(function( jqXHR, textStatus ) {
			        	console.log(jqXHR.status);
			        	console.log( "Request failed: " + textStatus );
			        });
				}
				
		  		$('#update').on('click', function(e){
		  			e.preventDefault();
		  			update();
		  		});
		  });
	    </script>
	</head> 
	<body>
		<a href="#" id="update">Start job</a>
		<br/>
		<div>Job done? <span id="jobStatus"></span></div>
		<ul id="results"></ul>
	</body>
</html>