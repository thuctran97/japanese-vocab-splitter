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
		<div class="row">
			<div class="col-sm-12">
				<header class="nn-header row">
					<img src="images/header.png" />
				</header>
				<jsp:include page="home/header.jsp" />
				<jsp:include page="${param.view}" />
				<jsp:include page="home/footer.jsp" />
			</div>
		</div>
	</div>
</body>
</html>