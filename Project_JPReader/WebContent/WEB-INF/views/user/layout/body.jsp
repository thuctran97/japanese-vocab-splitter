<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<div class="container">
	<div class="row">
		<div class="col-sm-2"></div>
		<div class="col-sm-8">
			<ul class="nav nav-tabs">
				<li class="active"><a data-toggle="tab" href="#home">Text</a></li>
				<li><a data-toggle="tab" href="#menu1">URL</a></li>
			</ul>
			<form action="result.php" method="post">
				<div class="tab-content">
					<div id="home" class="tab-pane fade in active">
						<textarea id="content" name="content" rows="5" style="width: 100%"
							placeholder="Enter a text"></textarea>
					</div>
					<div id="menu1" class="tab-pane fade">
						<input type="text" id="url" placeholder="Enter a URL"
							style="width: 100%; height: 30px">
					</div>
				</div>
				<input type="submit" value="Phân đoạn từ" class="btn btn-default">
			</form>
		</div>
		<div class="col-sm-2"></div>
	</div>
</div>
<!-- <hr> -->
<!-- <form action="result.php" method="post"> -->
<!-- 	<textarea id="content" rows="10" cols="100" name="content" -->
<!-- 		placeholder="Nhập văn bản.."></textarea> -->
<!-- 	<input type="submit" value="Phân đoạn từ" class="btn btn-default"> -->
<!-- 
<!-- <br> -->
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

