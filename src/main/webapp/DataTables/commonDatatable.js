/**
 * 공통 핸들링이 필요한 데이터테이블 셋팅 javascript 파일임
 */

const DT_LANG_KO = {
  decimal: "", thousands: ",",
  processing: "처리 중...",
  emptyTable: "표시할 데이터가 없습니다.",
  info: "총 _TOTAL_건 중 _START_–_END_ 표시",
  infoEmpty: "총 0건 중 0–0 표시",
  //infoFiltered: "(전체 _MAX_건에서 필터링됨)",
  infoFiltered: "",
  infoPostFix: "",
  lengthMenu: "페이지 당 _MENU_ 건 표시",
  loadingRecords: "불러오는 중...",
  zeroRecords: "일치하는 데이터가 없습니다.",
  paginate: { first: "처음", previous: "이전", next: "다음", last: "마지막" },
  aria: { sortAscending: ": 오름차순 정렬", sortDescending: ": 내림차순 정렬" }
};

// 공통 기본 옵션 팩토리
function dtBaseOptions() {
  return {
    // 공통 UI 정책
    searching: false,      // 기본 검색창 숨김
    dom: 'rtip',           // l,f 제거
    language: DT_LANG_KO,
    dom: '<"top"i>rtp',// ★ info 위로
    // 스크롤형 서버사이드 기본
    serverSide: true,
    processing: true,
    deferRender: true,
    scrollY: '50vh',
    scrollCollapse: true,
    scroller: { loadingIndicator: true, displayBuffer: 12 },

    // 정렬 기본 (화면에서 덮어쓰기 가능)
    ordering: true,
    order: [],             // 기본 정렬 미지정(화면에서 지정)
  };
}

/**
 * 공통 생성 함수
 * @param {string|HTMLElement|jQuery} tableSel  - 테이블 셀렉터/노드
 * @param {object} pageOptions                  - 화면별 옵션(ajax, columns, order 등)
 * @returns DataTable 인스턴스
 */
function createDataTable(tableSel, pageOptions) {
  const base = dtBaseOptions();
  // 깊은 병합: base <- pageOptions
  const opts = $.extend(true, {}, base, pageOptions || {});
  return $(tableSel).DataTable(opts);
}
