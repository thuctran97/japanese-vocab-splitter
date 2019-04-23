<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.request.contextPath}/">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="js/jquery.min.js"></script>
<link href="css/jquery-ui.min.css" rel="stylesheet" />
<script src="js/jquery-ui.min.js"></script>
<link href="css/bootstrap.min.css" rel="stylesheet" />
<script src="js/bootstrap.min.js"></script>
<link href="css/style.css" rel="stylesheet" />
<script src="js/token.process.js"></script>
</head>
<body>
	<div class="container">
		<jsp:include page="user/layout/header.jsp" />
		<hr>
		<form action="result.php" method="post">
			<textarea id="content" rows="10" cols="100" name="content"
				placeholder="Nhập văn bản.."></textarea>
			<input type="submit" value="Phân đoạn từ" class="btn btn-default">
		</form>
		<br>
		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-2"></div>
				<div class="col-sm-8 result">
					<c:forEach var="p" items="${text}">
						<span class="ttip">${p.getSurfaceForm()} <span
							class="ttiptext">${p.getReading()} <br>
								<button class="btn btn-warning" onclick="select(${p.getId()})">+</button>
						</span>
						</span>
						<c:if test="${p.equals('。')}">
							<br>
						</c:if>
					</c:forEach>
				</div>
				<div class="col-sm-2"></div>
			</div>
			<ul>
			</ul>
		</div>
	</div>

</body>
</html>