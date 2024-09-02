package kr.co.wizbrain.tbn.event.service;

import java.util.List;

import kr.co.wizbrain.tbn.comm.ParamsDto;
import kr.co.wizbrain.tbn.event.vo.EventVO;
import kr.co.wizbrain.tbn.infrm.vo.InfrmVO;

public interface EventService{

	List<EventVO> getEventList(EventVO paramVO);
	List<EventVO> getEvtList(EventVO paramVO);
	
	List getEventList(ParamsDto params);

	int getAllEventListSize(EventVO paramVO);

	List<EventVO> getAttendanceList(EventVO paramVO);

	List<InfrmVO> getInformerList4Event(EventVO paramVO);

	int saveEvent(EventVO paramVO);

	int saveAttendance(EventVO paramVO, List<String> alist);
	
	int deleteEvent(EventVO paramVO);
	
	int deleteAttendance(EventVO paramVO);

	int updateEvent(EventVO paramVO);
}