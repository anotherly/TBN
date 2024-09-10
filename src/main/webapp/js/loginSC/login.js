/*
 * 최초 index 페이지 진입 시
 * 세션 및 로그인 여부에 따라 로그인화면으로 전송할지
 * 메인화면으로 전송할 지 판별 
 * */

function stMainIdx(sessionVo){
	//세션 체크기능 -> 향후 복구 필요
	window.onunload = function() {
	   ////console.log('unload');
	    reloadOrKill(false);
	}
	
	$(function() {
		//console.log("페이지 로드시 체크");
		reloadOrKill(true);
	});
	
	//세션 받아오는 꺽쇠 넣을것 향후
	
	if(sessionVo==''){
		////console.log("로그인 세션X");//로그인 안되있음
		//로그인 페이지로 이동
		$("#changeBody").load("/common/login.do");
	}else{
		//메인 화면으로 이동
		$("#changeBody").load("/common/main.do");	
	}
	
	//탭이나 창 닫기시 로그아웃 처리
	$(window).bind("beforeunload", function (e){
		//console.log("언로드됨 : "+rkFlag);
		//reloadOrKill(true);
		if(!rkFlag){//false일 경우만 실행
			$("#changeBody").load("/user/reloadOrKill.do");
		}else{
			rkFlag=false;
		}
	});
}

/*
 * 로그인 처리 및 불완전 접속 종료 시 
 * 기존 세션을 끊고 신규 세션 생성
 * */
function inputLogin(inputVal){
	//console.log("입력값기준 로그인 처리함수");
	$.ajax({
		url: "/login/login.do",
		type: "POST",
		dataType: "json",
		data: inputVal,
		// ajax 통신 성공 시 로직 수행
		success: function(json){
			//서버측으로 부터 받은 별도의 에러메시지가 없을 경우 로그인 처리
			if(json.msg=="" || typeof json.msg ==="undefined"){
				$("#changeBody").empty();
				$("#changeBody").load("/common/main.do");
			}else{
				if(json.msg=="중복로그인"){
					//console.log(json.msg);
					var con_test = confirm("현재 로그인 사용자입니다. 재 접속하시겠습니까?");
					if(con_test == true){
						ajaxMethod("/login/login.do?relgn=1",inputVal,"/common/main.do","","changeBody");
					}
				}else{
					alert(json.msg);
				}
			}
		},
		error : function() {
			////console.log("에러가 발생하였습니다."+json.msg);
		},
		//finally 기능 수행
		complete : function() {
			////console.log("파이널리.");
		}
	});
}

/*
 * 로그인 처리 및 불완전 접속 종료 시 
 * 기존 세션을 끊고 신규 세션 생성
 * */
function pwChkInput(obj){
	//특수문자 정규식
	var regExp = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/gi;
	if (str_space.exec(obj.value)){ //공백체크
        obj.focus();
        obj.value = obj.value.replace(' ',''); // 공백제거
        return false;	
	}
}