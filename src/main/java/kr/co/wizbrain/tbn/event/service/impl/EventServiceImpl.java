package kr.co.wizbrain.tbn.event.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.wizbrain.tbn.comm.ParamsDto;
import kr.co.wizbrain.tbn.event.mapper.EventMapper;
import kr.co.wizbrain.tbn.event.service.EventService;
import kr.co.wizbrain.tbn.event.vo.EventVO;
import kr.co.wizbrain.tbn.event.vo.eFileVO;
import kr.co.wizbrain.tbn.infrm.vo.InfrmVO;


@Service("eventService")
public class EventServiceImpl implements EventService{
	@Resource(name="eventMapper")
	private EventMapper eventMapper;

	@Override
	public List<EventVO> getEventList(EventVO paramVO) {
		return eventMapper.getEventList(paramVO);
	}
	

	@Override
	public List<EventVO> getFileList(EventVO paramVO) {
		return eventMapper.getFileList(paramVO);
	}
	
	@Override
	public List getEventList(ParamsDto params) {
		return eventMapper.getEventList2(params);
	}

	@Override
	public int getAllEventListSize(EventVO paramVO) {
		return eventMapper.getAllEventListSize(paramVO);
	}

	@Override
	public List<EventVO> getAttendanceList(EventVO paramVO) {
		return eventMapper.getAttendanceList(paramVO);
	}

	@Override
	public List<InfrmVO> getInformerList4Event(EventVO paramVO) {
		return eventMapper.getInformerList4Event(paramVO);
	}

	@Override
	public int saveEvent(EventVO paramVO) {
		int cnt = 0;
		cnt= eventMapper.updateEvent(paramVO);
		if(cnt == 0){
			cnt = eventMapper.saveEvent(paramVO);
		}
		return cnt;
	}

	@Override
	public String selectEventId() {
		return eventMapper.selectEventId();
	}
	
	@Override
	public void insertFile(List<eFileVO> inputVo) {
		eventMapper.insertFile(inputVo);
	}
	
	
	@Override
	public int saveAttendance(EventVO thvo, List<String> alist) {
		int cnt = 0;
		cnt = eventMapper.saveAttendance(thvo,alist);
//		cnt= eventMapper.updateAttendance(thvo,alist);
//		if(cnt == 0){
//			cnt = eventMapper.saveAttendance(thvo,alist);
//		}
		return cnt;
	}

	@Override
	public int deleteEvent(EventVO paramVO) {
		int cnt = eventMapper.deleteAttendance(paramVO); // 이벤트 참석자 삭제
		cnt += eventMapper.deleteEvent(paramVO); // 이벤트 삭제
		
		return cnt;
	}
	
	@Override
	public void deleteFileOne(String fileId) {
		eventMapper.deleteFileOne(fileId);
	}

	@Override
	public String selectFileName(String fileId) {
		return eventMapper.selectFileName(fileId);
	}
	
	@Override
	public int deleteAttendance(EventVO paramVO) {
		int cnt = eventMapper.deleteAttendance(paramVO); // 이벤트 참석자 삭제
		return cnt;
	}


	@Override
	public int updateEvent(EventVO paramVO) {
		int cnt = eventMapper.updateEvent(paramVO); // 이벤트 참석자 삭제
		return cnt;
	}

	@Override
	public List<EventVO> getEvtList(EventVO paramVO) {
		return eventMapper.getEvtList(paramVO);
	}

	
	@Override
	public List<EventVO> selectFileList(EventVO paramVO){
		return eventMapper.selectFileList(paramVO);
	}
	
	@Override
	public List<EventVO> selectFileList2(EventVO paramVO){
		return eventMapper.selectFileList2(paramVO);
	}
	
	@Override
	public void deleteFile(EventVO paramVO) {
		eventMapper.deleteFile(paramVO);
	}
}