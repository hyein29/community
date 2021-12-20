// 썸머노트에 값넣기
var content = $("#content").val();
$('#summernote').summernote('code', content);

// 위와 같이 값을 먼저 넣어준 후 초기화를 시킨다. 그럼 아래와 같이 입력이 된다.
// 초기화
$('#summernote').summernote({
	height : 400, // set editor height
	minHeight : null, // set minimum height of editor
	maxHeight : null, // set maximum height of editor
	focus : true,
	lang : 'ko-KR' ,// 기본 메뉴언어 US->KR로 변경
	placeholder: '최대 2048자까지 쓸 수 있습니다',   // placeholder 설정
	callbacks: {   // 여기 부분이 이미지를 첨부하는 부분
		onImageUpload : function(files) {
			uploadSummernoteImageFile(files[0],this);
		},
		onPaste: function (e) {
			var clipboardData = e.originalEvent.clipboardData;
			if (clipboardData && clipboardData.items && clipboardData.items.length) {
				var item = clipboardData.items[0];
				if (item.kind === 'file' && item.type.indexOf('image/') !== -1) {
					e.preventDefault();
				}
			}
		}
	}
});


// 이미지 파일 업로드
function uploadSummernoteImageFile(file, editor) {
	data = new FormData();
	data.append("file", file);
	
	$.ajax({
		data : data,
		type : "POST",
		url : "/board/uploadSummernoteImageFile",
		contentType : false,
		processData : false,
		success : function(data) {
			// 항상 업로드된 파일의 url이 있어야 한다.
			$(editor).summernote('insertImage', data.url);
 		}
	});
}


// 저장버튼 클릭
$(document).on('click', '#save_btn', function () {
    saveContent();
});

var board = new Object();
var category = new Object();

// 데이터 저장
function saveContent() {

	let boardInputs = document.getElementsByClassName("board_input");
	
	for (let i of boardInputs) {
	
		if(i.value != "") {
			board[i.name] = i.value
		}
	}
	console.log("for문 밖");
	console.log(board);

	// 값 가져오기
	var summernoteContent = $('#summernote').summernote('code');        //썸머노트(설명)
	console.log("summernoteContent : " + summernoteContent);
	board['bContent'] = summernoteContent;

	console.log(board);

	$.ajax({
		url: '/board/'+ board['bNo'],
        type : 'POST',
        data : board,
        success : function(data) {
			alert("ajax 성공");
			console.log(data);
			location.href = "/board/" + board['bNo'];
		},
		error : function(request, status, error){
			alert("ajax 실패");
			console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
}