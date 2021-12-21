$(document).ready(function(){


})

$("#insertCmtBtn").click(function() {
	var comment = new Object();
	var member = new Object();
	var board = new Object();
	var cmWriter = $("#cmWriter").val();
	var cmContent = $("#cmContent").val();
	var bNo = $("#bNo").val();
	
	member.username = cmWriter;
	board.bNo = bNo;
	comment.member = member;
	comment.board = board; 
	comment.cmContent = cmContent; 
	
	
			
	if(confirm("댓글을 작성하시겠습니까?")){
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
