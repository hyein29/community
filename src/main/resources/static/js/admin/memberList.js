/*$(document).ready(function(){
	
	$("#checkAll").change(function(){
		var chk = $(this).prop("checked");
		
		if(chk){
			$(".chkBox").prop("checked", true);
		}else{
			$(".chkBox").prop("checked", false);
		}
	
	});
	
	
	$("#deleteBtn").click(function(e){
		e.preventDefault();
		var cnt = $(".chkBox:checked").length;
		var arr = new Array();
		
		$(".chkBox:checked").each(function(){
			arr.push($(this).closest("td").attr("id"));
		});
		
		if(cnt == 0){
			alert("삭제할 회원을 선택해 주세요.");
			return;
		}else{
			var data = {
				arr : arr
			}

			$.ajax({
				url: "/admin/member",
				type: "DELETE",
				data: data,
				success: function(result){
					
					if(result == "success"){
						alert("삭제 성공");
					}else{
						alert("삭제 실패");
					}
				},
				error: function(request) {
					alert("에러");
					alert("code:" + request.status);
				}
			});
		}
		
		
	
	
	});
	


});*/