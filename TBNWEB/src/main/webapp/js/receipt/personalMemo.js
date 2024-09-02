
function clearMemo(){
	var doubleCheck = confirm("모든 메모를 지우시겠습니까?");
	if(doubleCheck){
		$("#MEMO").val("");
	}else{
		return;
	}
}

function saveMemo(){
	var memo = $("#MEMO").val();
	var userId = opener.userId;
	
	var result = ajaxMethod("/receipt/updatePersonalMemo.do", {"memo":memo, "userId":userId}).result;
	result == 1 ? alert("변경사항이 저장 되었습니다.") : alert("Error발생: 잠시 후 다시 시도해 주세요.");
}