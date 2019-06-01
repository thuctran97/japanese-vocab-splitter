<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<h1>Flashcard page</h1>
<c:forEach var="p" items="${list.flashcards}">
	<span>${p.getSurfaceForm()}</span>
	<span>${p.getId()}</span>
</c:forEach>

<!-- <!DOCTYPE html> -->
<!-- <html> -->
<!-- <head> -->
<!-- 	<meta charset="utf-8"> -->
<!-- 	<title>Lap trinh Java</title> -->
<%-- 	<script src="js/shopping-cart.js"></script> --%>
<!-- </head> -->
<!-- <body> -->
