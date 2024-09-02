/*
 * @(#)exportPoiHssfView.java 1.0 2012/03/09
 * 
 * COPYRIGHT (C) 2011 NEONEXSOFT CO., LTD.
 * ALL RIGHTS RESERVED.
 */
package kr.co.wizbrain.tbn.comm.excel;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.wizbrain.tbn.comm.RecordDto;
import kr.co.wizbrain.tbn.comm.excel.support.ExportView;

/** 
 * POI HSSF 엑셀 뷰 클래스이다.
 * 
 * 경고:
 * 
 * 이 엑셀 뷰는 대용량 데이터를 처리하는데 적합하지 않다.
 * @author hjyoon
 *
 */
public class ExportPoiHssfView extends ExportView {
    /**
     * 파일이름을 반환한다.
     * 
     * @param model 모델
     * @return 파일이름
     */
    private String getFileName(Map<?, ?> model) {
        String name = (String) model.get("fileName");
        
        try {
            return URLEncoder.encode(name, "UTF-8").replace('+', ' ');
        }
        catch (UnsupportedEncodingException uee) {
            return name;
        }
    }
    
    /**
     * 컬럼제목을 반환한다.
     * 
     * @param model 모델
     * @return 컬럼제목
     */
    private List<String> getColumnTitles(Map<?, ?> model) {
        String[] titles = (String[]) model.get("columnTitles");
        
        List<String> list = new ArrayList<String>();
        
        for (int i = 0; i < titles.length; i++) {
            list.add(titles[i]);
        }
        
        return list;
    }
    
    /**
     * 데이터를 반환한다.
     * 
     * @param model 모델
     * @return 데이터
     */
    private List<List<String>> getExportData(Map<?, ?> model) {
        String[] names = (String[]) model.get("columnNames");
        
        List<?> data = (List<?>) model.get("exportData");
        
        List<List<String>> list = new ArrayList<List<String>>();
        
        for (int i = 0; i < data.size(); i++) {
            RecordDto record = (RecordDto) data.get(i);
            
            List<String> item = new ArrayList<String>();
            
            for (int n = 0; n < names.length; n++) {
            	item.add(record.getString(names[n]));
            }
            
            list.add(item);
        }
        
        return list;
    }
    
    /**
     * 컬럼제목을 반환한다.
     * 
     * @param model 모델
     * @return 컬럼제목
     */
    @SuppressWarnings({ "unchecked", "unused" })
	private List[] getColumnTitles(String[][] columnTitles) {
        
    	List[] list = new ArrayList[columnTitles.length];
    	
    	for(int i = 0 ; i < list.length ; i++){
	        
    		Map map = new HashMap();
    		map.put("columnTitles" , columnTitles[i]);
    		
    		list[i] = getColumnTitles(map);
    	}
	        

        
        return list;
    }
    
    /**
     * 데이터를 반환한다.
     * 
     * @param model 모델
     * @return 데이터
     */
    @SuppressWarnings({ "unused", "unchecked" })
	private List[] getExportData(List[] exportData , String[][] columnNames) {

    	List[] list = new ArrayList[exportData.length];
    	
    	for(int i = 0 ; i < list.length ; i++){
	        
    		Map map = new HashMap();
    		map.put("columnNames" , columnNames[i]);
    		map.put("exportData" , exportData[i]);
    		
    		list[i] = getExportData(map);
    	}
               
        return list;
    }    
    
    /* 
     * (non-Javadoc)
     * @see com.nx.uni.base.support.exportView#renderMergedOutputModel(java.util.Map, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
	@SuppressWarnings({ "unused", "unchecked" })
    protected void renderMergedOutputModel(Map model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(model.get("mapping")==null){
	    	if("1".equals(model.get("isMultiSheet"))){
				String[] sheetNames = (String[])model.get("sheetNames");
				String[] sheetTitle = (String[])model.get("sheetTitle");
	    		String[][] columnTitles = (String[][])model.get("columnTitles");
	    		String[][] columnNames = (String[][])model.get("columnNames");
	    		List[] exportData = (List[])model.get("exportData");
	    		
	    		buildExcelDocument(getFileName(model), sheetNames, sheetTitle, getColumnTitles(columnTitles) , getExportData(exportData , columnNames), request, response);
	    	}else{
	    		buildExcelDocument(getFileName(model), getColumnTitles(model), getExportData(model), request, response);
	    	}  
		}else{//통계시작
			if(model.get("mapping").equals("statsInformer")){	//교통정보 수집원 현황
				
				
			}
		}

    }
	
}