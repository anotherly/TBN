package kr.co.wizbrain.tbn.comm;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;

import kr.co.wizbrain.tbn.map.vo.ImsVO;
import kr.co.wizbrain.tbn.receipt.web.ReceiptController;


public class RSSParser {
	public static final Logger logger = LoggerFactory.getLogger(ReceiptController.class);

	@SuppressWarnings("unchecked")
	public List<ImsVO> imsParser() throws Exception {
		logger.debug("imsParser진입");
		List<ImsVO> rssList = new ArrayList<>();
		
		URL url = null;
		Document doc = null;
		SAXBuilder parser = new SAXBuilder();
		parser.setValidation(false);
		parser.setIgnoringElementContentWhitespace(true);
		logger.debug("url생성전");
		url = new URL("https://www.utic.go.kr:449/guide/imsOpenData.do?key=YKBLs3CFHnfvkbjrFEiplDgIQIUgnXcjyEe41qpKEHWDm6P0LRn1GZWcTp7a7");
		logger.debug("url: " + url);
		InputSource is = new InputSource(url.openStream());
		doc = parser.build(is);
		Element root = doc.getRootElement();
		List<Element> list = root.getChildren("record");
		logger.debug("Size: " + list.size());
		System.out.println("Size : " + list.size());
		
		for(int i=0; i<list.size(); i++) {
			Element el = (Element) list.get(i);
			ImsVO imsVO = createImsVO(el);
			rssList.add(imsVO);
		}
		return rssList;
	}
	
	public List<ImsVO> parseXmlFromJS(Document doc){
		
		
		return null;
	}
	
	public ImsVO createImsVO(Element el) {
		ImsVO imsVO = new ImsVO();
		
		//address data
		imsVO.setIncidentId(el.getChildText("incidentId"));
		imsVO.setAddressJibun(el.getChildText("addressJibun"));
		imsVO.setAddressJibunCd(el.getChildText("addressJibunCd"));
		imsVO.setAddressNew(el.getChildText("addressNew"));
		imsVO.setLinkId(el.getChildText("linkId"));
		
		String incidentRegionCd = incidentRegionCdToRealValue(el.getChildText("incidentRegionCd"));
		imsVO.setIncidentRegionCd(incidentRegionCd);
		//imsVO.setIncidentRegionCd(el.getChildText("incidentRegionCd"));
		
		imsVO.setLineLinkId(el.getChildText("lineLinkId"));
		
		//marker data
		String incidenteTypeCd = incidenteTypeCdToRealValue(el.getChildText("incidenteTypeCd"));
		imsVO.setIncidenteTypeCd(incidenteTypeCd);
		//imsVO.setIncidenteTypeCd(el.getChildText("incidenteTypeCd"));
		
		String incidenteSubTypeCd = incidenteSubTypeCdToRealValue(el.getChildText("incidenteSubTypeCd"), el.getChildText("incidenteTypeCd"));
		imsVO.setIncidenteSubTypeCd(incidenteSubTypeCd);
		//imsVO.setIncidenteSubTypeCd(el.getChildText("incidenteSubTypeCd"));

		imsVO.setLocationDataX
				(new Double(String.valueOf(el.getChildText("locationDataX"))));
		imsVO.setLocationDataY
				(new Double(String.valueOf(el.getChildText("locationDataY"))));
		imsVO.setLocationTypeCd(el.getChildText("locationTypeCd"));
		imsVO.setLocationData(el.getChildText("locationData"));
		
		String incidenteTrafficCd = incidenteTrafficCdToRealValue(el.getChildText("incidenteTrafficCd"));
		imsVO.setIncidenteTrafficCd(incidenteTrafficCd);
		//imsVO.setIncidenteTrafficCd(el.getChildText("incidenteTrafficCd"));
		
		String incidenteGradeCd = incidenteGradeCdToRealValue(el.getChildText("incidenteGradeCd"));
		imsVO.setIncidenteGradeCd(incidenteGradeCd);
		//imsVO.setIncidenteGradeCd(el.getChildText("incidenteGradeCd"));
		
		imsVO.setImportant(el.getChildText("important"));
		
		//infoWindow data
		imsVO.setIncidentTitle(el.getChildText("incidentTitle"));
		imsVO.setStartDate(el.getChildText("startDate"));
		imsVO.setEndDate(el.getChildText("endDate"));
		imsVO.setLane(el.getChildText("lane"));
		imsVO.setRoadName(el.getChildText("roadName"));
		imsVO.setSourceCode(el.getChildText("sourceCode"));
		imsVO.setControlType(el.getChildText("controlType"));
		imsVO.setUpdateDate(el.getChildText("updateDate"));
		
		return imsVO;
	}
	
	private String incidenteTypeCdToRealValue(String code) {		//유형(대)
		String result = "";
		
		switch(code) {
		case "1" : result = "사고";
			break;
		case "2" : result = "공사";
			break;
		case "3" : result = "행사";
			break;
		case "4" : result = "기상";
			break;
		case "5" : result = "통제";
			break;
		default: result = code;
			break;
		}
		return result;
	}
	
	private String incidenteSubTypeCdToRealValue(String code, String type) {	//유형(소)
		String result = "";
		
		if(type.equals("1")) {
			result = subValueFor1(type);
		} else if(type.equals("2")) {
			result = subValueFor2(type);
		} else if(type.equals("3")) {
			result = subValueFor3(type);
		} else if(type.equals("4")) {
			result = subValueFor4(type);
		} else if(type.equals("5")) {
			result = subValueFor5(type);
		} else {
			result = code;
		}
		return result;
	}
	
	private String subValueFor1(String code) {
		String result = "";
		
		switch(code) {
		case "1" : result = "구조작업";
			break;
		case "10" : result = "도로파손";
			break;
		case "2" : result = "사고로 차량파손";
			break;
		case "255" : result = "교통사고";
			break;
		case "3" : result = "전복";
			break;
		case "4" : result = "화재";
			break;
		case "6" : result = "기름유출";
			break;
		case "7" : result = "차도 이탈";
			break;
		case "8" : result = "고장 차량";
			break;
		case "9" : result = "적재물 낙하";
			break;
		default: result = code;
			break;
		}
		return result;
	}
	
	private String subValueFor2(String code) {
		String result = "";
		
		switch(code) {
		case "1" : result = "재포장";
			break;
		case "13" : result = "노변 유지보수";
			break;
		case "14" : result = "배수거 청소";
			break;
		case "15" : result = "방책 유지보수";
			break;
		case "18" : result = "가로등 작업";
			break;
		case "19" : result = "표지판 작업";
			break;
		case "2" : result = "지하 시설 작업";
			break;
		case "255" : result = "도로공사";
			break;
		case "3" : result = "고가 시설 작업";
			break;
		case "4" : result = "하수도/배수로 작업";
			break;
		case "6" : result = "유지보수";
			break;
		case "7" : result = "도로도색";
			break;
		case "8" : result = "가로수 벌목";
			break;
		default: result = code;
			break;
		}
		return result;
	}
	
	private String subValueFor3(String code) {
		String result = "";
		
		switch(code) {
		case "1" : result = "복합행사";
			break;
		case "2" : result = "축제";
			break;
		case "255" : result = "행사";
			break;
		case "3" : result = "집회";
			break;
		case "4" : result = "스포츠 행사";
			break;
		case "5" : result = "국가 행사";
			break;
		case "6" : result = "문화 행사";
			break;
		default: result = code;
			break;
		}
		
		return result;
	}
	
	private String subValueFor4(String code) {
		String result = "";
		
		switch(code) {
		case "1" : result = "강풍";
			break;
		case "2" : result = "강우";
			break;
		case "255" : result = "기상";
			break;
		case "3" : result = "강설";
			break;
		case "4" : result = "지진 행사";
			break;
		default: result = code;
			break;
		}
		return result;
	}
	
	private String subValueFor5(String code) {
		String result = "";
		
		switch(code) {
		case "1" : result = "재해";
			break;
		case "10" : result = "공사";
			break;
		case "20" : result = "행사";
			break;
		case "255" : result = "기타";
			break;
		default: result = code;
			break;
		}
		return result;
	}
	
	private String incidenteTrafficCdToRealValue(String code) {	//소통현황
		String result = "";
		
		switch(code) {
		case "A0501" : result = "원활";
			break;
		case "A0502" : result = "서행";
			break;
		case "A0503" : result = "지체";
			break;
		default: result = code;
			break;
		}
		return result;
	}
	
	private String incidenteGradeCdToRealValue(String code) {		//등급
		String result = "";
		
		switch(code) {
		case "A0401" : result = "A";
			break;
		case "A0402" : result = "B";
			break;
		case "A0403" : result = "C";
			break;
		default: result = code;
			break;
		}
		return result;
	}
	
	private String incidentRegionCdToRealValue(String code) {		//지역
		String result = "";
		
		switch(code) {
		case "01" : result = "서울";
			break;
		case "02" : result = "인천";
			break;
		case "03" : result = "부천";
			break;
		case "04" : result = "광명";
			break;
		case "05" : result = "안양";
			break;
		case "06" : result = "과천";
			break;
		case "07" : result = "안산";
			break;
		case "08" : result = "용인";
			break;
		case "09" : result = "성남";
			break;
		case "10" : result = "고양";
			break;
		case "11" : result = "시흥";
			break;
		case "12" : result = "파주";
			break;
		case "13" : result = "양주";
			break;
		case "14" : result = "의정부";
			break;
		case "15" : result = "김포";
			break;
		case "16" : result = "의왕";
			break;
		case "17" : result = "군포";
			break;
		case "18" : result = "남양주";
			break;
		case "19" : result = "수원";
			break;
		case "20" : result = "광주";		//경기도 광주
			break;
		case "21" : result = "구리";
			break;
		case "22" : result = "하남";
			break;
		case "23" : result = "부산";
			break;
		case "24" : result = "양산";
			break;
		case "25" : result = "창원";
			break;
		case "26" : result = "김해";
			break;
		case "27" : result = "진주";
			break;
		case "28" : result = "거제";
			break;
		case "29" : result = "대구";
			break;
		case "30" : result = "대전";
			break;
		case "31" : result = "광주";		//광주광역시
			break;
		case "32" : result = "울산";
			break;
		case "33" : result = "전주";
			break;
		case "34" : result = "원주";
			break;
		case "35" : result = "청주";
			break;
		case "36" : result = "천안";
			break;
		case "37" : result = "포항";
			break;
		case "38" : result = "제주";
			break;
		default: result = code;
			break;
		}
		return result;
	}
}


















