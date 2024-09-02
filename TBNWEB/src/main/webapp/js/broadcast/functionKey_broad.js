function functionKeyEvent_braod(key){
	if(key == 112) shortCutF1_broad();
	if(key == 113) shortCutF2_broad();
	if(key == 114) shortCutF3_broad();
	
//	if(key == 117) shortCutF6();
//	if(key == 118) shortCutF7();
//	if(key == 119) shortCutF8();
//	
//	if(key == 120) shortCutF9();
//	if(key == 121) shortCutF10();
//	if(key == 122) shortCutF11();
}

function shortCutF1_broad(){
	console.log("authCode: " + authCode);
	doBroadcast(authCode);
}

function shortCutF2_broad(){
	
}

function shortCutF3_broad(){
	removeAllFromStudio();
}