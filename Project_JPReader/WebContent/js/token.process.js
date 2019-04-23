function insertFlashcard(surfaceForm, reading, partOfSpeech, baseForm) {
	var url = 'http://www.mazii.net/#!/search?type=w&query=' + surfaceForm;
	var control = '<div class="col-md-3 nn-box">' + '<div class="inner">'
			+ '<center class="word">'
			+ surfaceForm
			+ '</center>'
			+ '<br>'
			+ ' Cách đọc: '
			+ reading
			+ '<br>'
			+ ' Từ loại: '
			+ partOfSpeech
			+ '<br>'
			+ ' Nguyên mẫu: '
			+ baseForm
			+ '<br>'
			+ ' Mức độ khó: '
			+ '<select>'
			+ '<option value="N5">N5</option>'
			+ '<option value="N4">N4</option>'
			+ '<option value="N3">N3</option>'
			+ '<option value="N2">N2</option>'
			+ '<option value="N1">N1</option>'
			+ '</select>'
			+ '<button onclick=window.open("'
			+ url
			+ '")>?</button'
			+ '</div>'
			+ '</div>';
	$("ul").append(control);
}
function select(id) {

	$.ajax({
		url : "select.php",
		data : {
			id : id
		},
		success : function(response) {
			var r = response.reading;
			var s = response.surfaceForm;
			var p = response.partOfSpeech;
			var b = response.baseForm;
			insertFlashcard(s, r, p, b);
		},
		error : function() {
			alert("Error");
		},
		dataType : "json"
	});
}