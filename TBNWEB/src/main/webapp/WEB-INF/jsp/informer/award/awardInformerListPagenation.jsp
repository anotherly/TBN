<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"        prefix="c"%>
<!-- contents -->
<div id="Pagination" style="align: center;"></div>
<!-- contents -->
<script type="text/javascript">
$(document).ready(function(){

    var total = '${awardInformerListSize}';
    var optInit = getOptionsFromForm();
    $("#Pagination").pagination(total, optInit);
    
    if(total <= 0)
        $("#Pagination").hide();
});
function pageselectCallback(page_index, jq){
    // Get number of elements per pagionation page from form
    var items_per_page = 11; //한페이지에 10개씩 출력
    var total = '${awardInformerListSize}';
    var max_elem = Math.min((page_index+1) * items_per_page, total);
    var i = page_index*items_per_page;
    
    //$('#listDiv').load('<c:url value="/user/informerList.do"/>?MIN=' + i);
    var options = {
            url: '<c:url value="/informer/award/awardInformerList.do"/>?MIN='+i,
            target:"#listDiv"
    };
    $('#searchFrm').ajaxSubmit(options);
   
    return false;
}

function getOptionsFromForm(){
    var opt = {callback: pageselectCallback};
    // Collect options from the text fields - the fields are named like their option counterparts
    opt["prev_text"] = "&lsaquo; Prev";
    opt["next_text"] = "Next &rsaquo;";
    /* opt["prev_text"] = "<img src='<c:url value="/images/paging/btn_pre01.gif"/>'/>";
    opt["next_text"] = "<img src='<c:url value="/images/paging/btn_next01.gif"/>'/>"; */
    opt["items_per_page"] = parseInt(11);
    opt["num_display_entries"] = parseInt(10);
    opt["num_edge_entries"] = parseInt(1);
    return opt;
}
</script>