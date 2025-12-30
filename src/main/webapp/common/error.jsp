<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="ko">
<head>
<meta charset="UTF-8">
<style>
* {
	box-sizing: border-box;
}

body {
	margin: 0;
	padding: 0;
	font-family: -apple-system, BlinkMacSystemFont, "맑은 고딕", system-ui,
		sans-serif;
	background: #f7f9fc;
	display: flex;
	align-items: center;
	justify-content: center;
	min-height: 100vh;
}

.tbn-error-wrap {
	background: #ffffff;
	width: 90%;
	max-width: 720px;
	padding: 48px 36px;
	border-radius: 18px;
	box-shadow: 0 8px 20px rgba(0, 0, 0, 0.08);
	text-align: center;
	position: relative;
}

.tbn-error-wrap::after {
	content: "";
	position: absolute;
	top: -70px;
	right: -70px;
	width: 180px;
	height: 180px;
	background: #e7efff;
	border-radius: 50%;
	opacity: 0.5;
	z-index: -1;
}

.tbn-error-logo {
	margin-bottom: 24px;
}

.tbn-error-logo img {
	max-width: 260px; /* 필요하면 여기서 크기 조절 */
	height: auto;
}

.tbn-error-title {
	margin: 0 0 16px;
	font-size: 26px;
	color: #222;
	font-weight: 700;
}

.tbn-error-msg {
	margin-bottom: 28px;
	font-size: 15px;
	line-height: 1.7;
	color: #555;
}

.tbn-error-highlight {
	color: #0057c3;
	font-weight: 700;
}

.tbn-error-btn-area {
	margin: 12px 0 34px;
}

.tbn-error-main-btn {
	display: inline-block;
	padding: 11px 22px;
	border-radius: 10px;
	background: linear-gradient(90deg, #21C29A 0%, #2BAFC3 50%, #3A7BD5 100%);
	color: #fff;
	text-decoration: none;
	font-size: 14px;
	font-weight: 600;
	transition: 0.15s ease;
	border: none;
}

.tbn-error-main-btn:hover {
	opacity: 0.92;
	transform: translateY(-1px);
	box-shadow: 0 4px 10px rgba(0, 0, 0, 0.15);
}

.tbn-error-region-wrap {
	margin-top: 10px;
	padding-top: 18px;
	border-top: 1px dashed #dde3ef;
}

.tbn-error-region-title {
	font-size: 13px;
	color: #666;
	margin-bottom: 10px;
}

.tbn-error-region-list {
	display: flex;
	flex-wrap: wrap;
	gap: 6px;
	justify-content: center;
	padding: 0;
	margin: 0;
	list-style: none;
}
/* 지역 방송국 리스트 - PC에서는 7개씩 두 줄, 모바일에서는 자동 줄바꿈 */
.tbn-error-region-list {
	display: grid;
	gap: 6px;
	justify-content: center;
	grid-template-columns: repeat(7, auto); /* 넓을 때는 7개 고정 */
}

/* 화면이 좁아지면 컬럼 수를 자동으로 줄이기 */
@media ( max-width : 640px) {
	.tbn-error-region-list {
		grid-template-columns: repeat(auto-fit, minmax(70px, auto));
	}
}

.tbn-error-region-link {
	padding: 6px 12px;
	border-radius: 999px;
	background: #f2f3f5;
	text-decoration: none;
	color: #444;
	font-size: 12px;
}

.tbn-error-region-link:hover {
	background: #e8e9ec;
}

.tbn-error-footer-logo {
	margin-top: 26px;
	text-align: center;
}

.tbn-error-footer-logo img {
	max-width: 200px; /* 필요하면 여기서 크기 조절 */
	height: auto;
	opacity: 0.9;
}
</style>
</head>
<body>
	<div class="tbn-error-wrap">
		<h1 class="tbn-error-title">페이지를 표시할 수 없습니다.</h1>
		<div class="tbn-error-msg">
			요청하신 페이지를 표시하는 중 오류가 발생했습니다.<br> 일시적인 오류이거나 주소가 잘못되었을 수 있습니다.<br>
		</div>
	</div>
</body>
</html>