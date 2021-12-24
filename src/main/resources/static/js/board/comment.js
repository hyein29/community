$(document).ready(function(){

	// 댓글 등록
	$("#insertCmtBtn").click(function() {
		var comment = new Object();
		var cmWriter = $("#cmWriter").text();
		var cmContent = $("#cmContent").val();
		var bNo = $("#bNo").val();
	
		comment.bNo = bNo; 
		comment.cmContent = cmContent; 
		comment.username = cmWriter;
		
		console.log(comment);
		
				
		if(confirm("댓글을 등록하시겠습니까?")){
			$.ajax({
				url : "/comment",
				type : "POST",
				data : JSON.stringify(comment),
				contentType: "application/json; charset=UTF-8",
				success : function(data) {
					alert("댓글이 등록되었습니다.");
					location.reload();
				},
				error : function(request, status, error) {
					alert("에러");
					alert("code:"+request.status);
				}
			});
					
		}else{
			alert("취소되었습니다.");
		}
	
	})
	
	//  cmGrp 전역 변수로 지정
	var cmGrp = "";
	
	// 답댓글 작성란 나타내기
	$(".replyBtn").click(function() {
	
		hideReplyInput();
	
		// 답글 버튼 클릭 시 cmGrp에 해당 댓글 grp값 담기
		cmGrp = $(this).attr("id");
		
		alert(cmGrp);
		
		var replyInput = "<tr class='replyInput'>"
						+ "<td colspan='6'><div style='margin-left: 40px'>"
						+ "<input type='text' size='40' id='rpContent'> "
						+ "<button class='insertReplyBtn'>등록</button> "
						+ "<button class='cancelReplyBtn'>취소</button>"
						+ "</div></td>"
						+ "</tr>"; 
	
		$(this).closest("tr").after(replyInput);
	})
	
	// 답댓글 작성란 숨기기
	function hideReplyInput() {
		$(".replyInput").hide();
	}
	
	// 취소 버튼 클릭 시 답댓글 작성란 사라지게
	$(document).on('click', '.cancelReplyBtn', function () {
		hideReplyInput();
	});
	
	
	// 답댓글 등록
	$(document).on('click', '.insertReplyBtn', function () {
		var comment = new Object();
		var cmContent = $("#rpContent").val();
		var bNo = $("#bNo").val();
	
		comment.bNo = bNo; 
		comment.cmContent = cmContent; 
		comment.cmGrp = cmGrp;
		
		if(confirm("댓글을 등록하시겠습니까?")){
			$.ajax({
				url : "/comment/reply",
				type : "POST",
				data : JSON.stringify(comment),
				contentType: "application/json; charset=UTF-8",
				success : function(data) {
					alert("대대대대댓글이 등록되었습니다.");
					location.reload();
				},
				error : function(request, status, error) {
					alert("에러");
					alert("code:"+request.status);
				}
			});
					
		}else{
			alert("취소되었습니다.");
		}
		
	})
	
	// 댓글 삭제
	$(".deleteReplyBtn").on('click', function () {
		
		var bNo = $("#bNo").val();
		var cmNo = $(this).attr("id");
		var cmGrp = $(this).attr("data-grp");
		var cmSeq = $(this).attr("data-seq");
		
		var comment = new Object();
		
		comment.bNo = bNo;
		comment.cmNo = cmNo;
		comment.cmGrp = cmGrp;
		comment.cmSeq = cmSeq;
		
	
		if(confirm("댓글을 삭제하시겠습니까?")){
			$.ajax({
				url : "/comment",
				type : "DELETE",
				data : JSON.stringify(comment),
				contentType: "application/json; charset=UTF-8",
				success : function(data) {
					alert("댓글이 삭제되었습니다.");
					location.reload();
				},
				error : function(request, status, error) {
					alert("에러");
					alert("code:"+request.status);
				}
			});
					
		}else{
			return;
		}
		
	})
	


})