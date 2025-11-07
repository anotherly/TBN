<%@ page contentType="text/html;charset=UTF-8" %>
<link rel="stylesheet" href="<%= request.getContextPath() %>/DataTables/jquery.dataTables.min.css"/>
<link rel="stylesheet" href="<%= request.getContextPath() %>/DataTables/scroller.dataTables.min.css"/>
<script src="<%= request.getContextPath() %>/DataTables/jquery-3.7.1.min.js"></script>
<script src="<%= request.getContextPath() %>/DataTables/jquery.dataTables.min.js"></script>
<script src="<%= request.getContextPath() %>/DataTables/dataTables.scroller.min.js"></script>

<script>
$(function () {
  console.log("datatable start");
  const $form = $('#searchForm');
  const nameMap = { // 헤더 인덱스 → DB 컬럼명 (정렬용)
    1:'INFORMER_ID', 2:'AREA_NAME', 3:'INFORMER_TYPE_NAME', 4:'ORG_NAME',
    5:'INFORMER_NAME', 6:'PHONE_CELL', 7:'ZIPCODE', 8:'FLAG_ACT', 9:'REG_ORDER'
  };

  dt = $('#informerTable').DataTable({
    serverSide: true,
    processing: true,
    deferRender: true,
    scrollY: '100vh',
    scrollCollapse: true,
    scroller: { loadingIndicator: true, displayBuffer: 12 }, // ★ 누락 보완
    ordering: true,
    order: [[9, 'desc']],
    ajax: {
      url: '/infrm/datatable.do',
      type: 'POST',
      data: function (d) {
    	  // 정렬 정보 계산
    	  let sortName = null, sortDir = null;
    	  if (Array.isArray(d.order) && d.order.length) {
    	    const od  = d.order[0];
    	    const idx = od.column;
    	    sortDir   = od.dir; // 'asc' | 'desc'
    	    sortName  = (d.columns && d.columns[idx] && d.columns[idx].name)
    	                  ? d.columns[idx].name
    	                  : nameMap[idx]; // nameMap은 기존과 동일
    	  }

    	  // (선택) DataTables 원본 search만 제거
    	  delete d.search;

    	  const sendData = {
    	    // ★ 정렬 평면 파라미터 추가
    	    sortName: sortName,
    	    sortDir:  sortDir,

    	    // 검색 파라미터들
    	    areaCode:     $('#areaCodeSel').val(),
    	    informerType: $('#informerTypeSel').val(),
    	    orgId:        $('#orgIdSel').val(),
    	    flagAct:      $('#searchActYn').val(),
    	    searchType:   $('#searchType').val(),
    	    searchValue:  $('#searchValue').val()
    	  };
    	  if ($('#dchker').is(':checked')) {
    	    sendData.sDate = $('#sDate').val();
    	    sendData.eDate = $('#eDate').val();
    	  }
    	  console.log("sortName : "+sortName);
    	  console.log("sortDir : "+sortDir);
    	  return $.extend({}, d, sendData); // draw/start/length는 d에 그대로 유지
    },
    columns: [
      { data:null, orderable:false, name:'CHK',
        render: function(d){ return '<input type="checkbox" value="'+ d.informerId +'">'; } },
      { data:'informerId',       name:'INFORMER_ID' },
      { data:'areaName',         name:'AREA_NAME' },
      { data:'informerTypeName', name:'INFORMER_TYPE_NAME' },
      { data:'orgName',          name:'ORG_NAME' },
      { data:'informerName',     name:'INFORMER_NAME' },
      { data:'phoneCell',        name:'PHONE_CELL' },
      { data:'zipcode',          name:'ZIPCODE', 
    	render: function(d,t,r){
    		    console.log("zipcode:", d, r);
    		    return d || '-';}
    	  
      },
      { data:'flagAct',          name:'FLAG_ACT' },
      { data:'regDate',          name:'REG_ORDER' }
    ],
    createdRow: function(row, data){
      $(row).attr('data-informer-id', data.informerId)
            .css('cursor', 'pointer');
    }
    }
  });

  // 행 클릭 → 팝업 (체크박스/링크 등 제외)
  $('#informerTable tbody').on('click', 'tr', function(e){
    if ($(e.target).is('input, a, button, label, select')) return;
    const row = dt.row(this).data();
    if (row) editInformer(row.informerId);
  });
  
  
});
</script>

<table id="informerTable" class="display" style="width:100%">
  <thead>
  <tr>
    <th>선택</th>
    <th>ID</th>
    <th>방송국</th>
    <th>유형</th>
    <th>소속기관</th>
    <th>이름</th>
    <th>전화</th>
    <th>우편번호</th>
    <th>활동여부</th>
    <th>등록일</th>
  </tr>
  </thead>
  <tbody></tbody>
</table>