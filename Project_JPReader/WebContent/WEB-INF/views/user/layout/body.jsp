<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<div class="container">
	<div class="row">
		<div class="col-sm-1"></div>
		<div class="col-sm-10">
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
						<input type="text" name="content" placeholder="Enter a URL"
							style="font-size: 15pt; width: 100%; height: 40px">
					</div>
				</div>
				<input type="submit" value="Tokenize" class="btn btn-default">
			</form>
			<hr>
			<div class="result">
				<c:forEach var="p" items="${text}">
					<span class="ttip">${p.getSurfaceForm()} <span
						class="ttiptext"> Reading: ${p.getReading()} <br> Part
							of speech: ${p.getPartOfSpeech()} <br> Base form:
							${p.getBaseForm()} <br> Meaning: ${p.getMeaning()}
							<button class="btn btn-warning" onclick="select(${p.getId()})">+</button>
					</span>
					</span>
					<c:if test="${p.equals('。')}">
						<br>
					</c:if>
				</c:forEach>
			</div>
			<div class="flashcards-result">
				<ul id="flashcards">
				</ul>
			</div>
			<br>
			<button class="btn btn-default">Dowload flashcard</button>
		</div>
		<div class="col-sm-1"></div>
	</div>
</div>

