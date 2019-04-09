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
</head>
<script type="text/javascript">
	function insertFlashcard(surfaceForm,reading, partOfSpeech,baseForm) {
		var url = 'http://www.mazii.net/#!/search?type=w&query='+surfaceForm;
		var control = '<div class="col-md-3 nn-box">'+'<div class="inner">'
					+ '<center class="word">' +  surfaceForm + '</center>'
					+  '<br>' 
					+ ' Cách đọc: '+ reading + '<br>' 
					+ ' Từ loại: '+ partOfSpeech + '<br>' 
					+  ' Nguyên mẫu: ' + baseForm + '<br>' 
					+ ' Mức độ khó: ' 
					+ '<select>'
					+ '<option value="N5">N5</option>'
					+ '<option value="N4">N4</option>'
					+ '<option value="N3">N3</option>'
					+ '<option value="N2">N2</option>'
					+ '<option value="N1">N1</option>'
					+ '</select>'
					+ '<button onclick=window.open("' + url + '")>?</button'
					+ '</div>' + '</div>';
		$("ul").append(control);
	}
	function select(id) {

		  $.ajax({
				url:"select.php",
				data:{id: id}, 
				success: function(response){
					var r = response.reading;
					var s = response.surfaceForm;
					var p = response.partOfSpeech;
					var b = response.baseForm;
					insertFlashcard(s,r,p,b);
				},
				error: function() {
				     alert("Error");
				},
				dataType:"json"
			});
	}
</script>
<body>
	<!-- 	<div class="col-sm-6">  </div> -->

	<div class="container">
		<header class="nn-header row">
			<img class="pull-left" src="images/logo-rif-lg.png" />
			<div class="dropdown  pull-right account">
				<a href="#" class="dropdown-toggle " data-toggle="dropdown">
				<span class="glyphicon glyphicon-user"> </span> Account <span
					class="caret"></span>
				</a>
				<ul class="dropdown-menu">
					<c:choose>
						<c:when test="${empty sessionScope.user}">
							<li><a href="account/login.php"> Login </a></li>
							<li><a href="account/forgot.php"> Forgot Password </a></li>
							<li><a href="account/register.php"> Register </a></li>
						</c:when>
						<c:otherwise>
							<li><a href="account/logoff.php"> Logoff </a></li>
							<li><a href="account/change.php"> Change Password </a></li>
							<li><a href="account/edit.php"> Edit Profile </a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</header>
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