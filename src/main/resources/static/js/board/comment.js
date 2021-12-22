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
	
	
	// 답댓글 등록
	$(".replyBtn").click(function() {
	
		var cmGrp = $(this).attr('id');
		alert(cmGrp);
		
		//var tableElement = "<tr>"; 
		
		//$("#cmtListTable").append(tableElement);
	})


})