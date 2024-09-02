package kr.co.wizbrain.tbn.comm;
import java.util.Enumeration;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.co.wizbrain.tbn.comm.ParamsDto;
import kr.co.wizbrain.tbn.comm.ResultDto;

/**기본 컨트롤 클래스이다.
 * @author hjyoon
 *
 */
@Controller
public class BaseController {
    /**
     * 로그를 기록한다.
     * 
     * @param log 로그
     * @param message 메시지
     */
    protected void trace(Log log, Object message) {
        if (log.isTraceEnabled()) {
            log.trace(message);
        }
    }
    
    /**
     * 로그를 기록한다.
     * 
     * @param log 로그
     * @param message 메시지
     * @param throwable 발생오류
     */
    protected void trace(Log log, Object message, Throwable throwable) {
        if (log.isTraceEnabled()) {
            log.trace(message, throwable);
        }
    }
    
    /**
     * 로그를 기록한다.
     * 
     * @param log 로그
     * @param message 메시지
     */
    protected void debug(Log log, Object message) {
        if (log.isDebugEnabled()) {
            log.debug(message);
        }
    }
    
    /**
     * 로그를 기록한다.
     * 
     * @param log 로그
     * @param message 메시지
     * @param throwable 발생오류
     */
    protected void debug(Log log, Object message, Throwable throwable) {
        if (log.isDebugEnabled()) {
            log.debug(message, throwable);
        }
    }
    
    /**
     * 로그를 기록한다.
     * 
     * @param log 로그
     * @param message 메시지
     */
    protected void info(Log log, Object message) {
        if (log.isInfoEnabled()) {
            log.info(message);
        }
    }
    
    /**
     * 로그를 기록한다.
     * 
     * @param log 로그
     * @param message 메시지
     * @param throwable 발생오류
     */
    protected void info(Log log, Object message, Throwable throwable) {
        if (log.isInfoEnabled()) {
            log.info(message, throwable);
        }
    }
    
    /**
     * 로그를 기록한다.
     * 
     * @param log 로그
     * @param message 메시지
     */
    protected void warn(Log log, Object message) {
        if (log.isWarnEnabled()) {
            log.warn(message);
        }
    }
    
    /**
     * 로그를 기록한다.
     * 
     * @param log 로그
     * @param message 메시지
     * @param throwable 발생오류
     */
    protected void warn(Log log, Object message, Throwable throwable) {
        if (log.isWarnEnabled()) {
            log.warn(message, throwable);
        }
    }
    
    /**
     * 로그를 기록한다.
     * 
     * @param log 로그
     * @param message 메시지
     */
    protected void error(Log log, Object message) {
        if (log.isErrorEnabled()) {
            log.error(message);
        }
    }
    
    /**
     * 로그를 기록한다.
     * 
     * @param log 로그
     * @param message 메시지
     * @param throwable 발생오류
     */
    protected void error(Log log, Object message, Throwable throwable) {
        if (log.isErrorEnabled()) {
            log.error(message, throwable);
        }
    }
    
    /**
     * 로그를 기록한다.
     * 
     * @param log 로그
     * @param message 메시지
     */
    protected void fatal(Log log, Object message) {
        if (log.isFatalEnabled()) {
            log.fatal(message);
        }
    }
    
    /**
     * 로그를 기록한다.
     * 
     * @param log 로그
     * @param message 메시지
     * @param throwable 발생오류
     */
    protected void fatal(Log log, Object message, Throwable throwable) {
        if (log.isFatalEnabled()) {
            log.fatal(message, throwable);
        }
    }
    
    /**
     * 요청을 반환한다.
     * 
     * @return 요청
     */
    protected HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }
    
    /**
     * 세션을 반환한다.
     * 
     * @return 세션
     */
    protected HttpSession getSession() {
        return getRequest().getSession(false);
    }
    
    
    /**
     * 사용자를 반환한다.
     * 
     * @return 사용자
     */
/*    protected UserDto getUser() {
        UserDto user = new UserDto();
        
        HttpSession session = getSession();
        
        user.put("drvfirm_id", session.getAttribute("drvfirm_id"));
        user.put("emp_id",     session.getAttribute("emp_id"));
        user.put("auth_id",    session.getAttribute("auth_id"));
        
        return user;
    }*/
    
    /**
     * 파라메터를 반환한다.
     * 
     * @return 파라메터
     */
    protected ParamsDto getParams() {
        return getParams(getRequest(), false);
    }
    
    /**
     * 파라메터를 반환한다.
     * 
     * @param sessionScope 세션 스코프
     * @return 파라메터
     */
    protected ParamsDto getParams(boolean sessionScope) {
        return getParams(getRequest(), sessionScope);
    }
    
    /**
     * 파라메터를 반환한다.
     * 
     * @param request HTTP 요청
     * @return 파라메터
     */
    protected ParamsDto getParams(HttpServletRequest request) {
        return getParams(request, false);
    }
    
    /**
     * 파라메터를 반환한다.
     * 
     * @param request HTTP 요청
     * @param sessionScope 세션 스코프
     * @return 파라메터
     */
    protected ParamsDto getParams(HttpServletRequest request, boolean sessionScope) {
        ParamsDto params = new ParamsDto();
        
        Enumeration<?> enumeration = request.getParameterNames();
        
        while (enumeration.hasMoreElements()) {
            String name = (String) enumeration.nextElement();
            
            String rename = name;
            
            if (name.endsWith("[]")) {
                rename = name.substring(0, name.lastIndexOf("[]"));
            }
            
            params.add(rename, request.getParameterValues(name));
        }
        
        if (request instanceof MultipartHttpServletRequest) {
            MultipartHttpServletRequest multipart = (MultipartHttpServletRequest) request;
            
            Iterator<String> iterator = multipart.getFileNames();
            
            while (iterator.hasNext()) {
                String name = iterator.next();
                
                String rename = name;
                
                if (name.endsWith("[]")) {
                    rename = name.substring(0, name.lastIndexOf("[]"));
                }
                
                Object[] source = multipart.getFiles(name).toArray();
                
                MultipartFile[] destination = new MultipartFile[source.length];
                
                System.arraycopy(source, 0, destination, 0, source.length);
                
                params.add(rename, destination);
            }
        }
        
        if (sessionScope) {
            HttpSession session = request.getSession(false);
            
            params.put("session_user_id", 		session.getAttribute("USER_ID"));
            params.put("session_user_name",     session.getAttribute("USER_NAME"));
            params.put("session_user_tel",     	session.getAttribute("USER_TEL"));
            params.put("session_user_region",   session.getAttribute("USER_REGION"));
            params.put("session_user_region_name",   session.getAttribute("USER_REGION_NAME"));
            params.put("session_user_auth",    	session.getAttribute("USER_AUTH"));
            params.put("session_user_extension",    	session.getAttribute("USER_EXTENSION"));
            
       //     params.put("session_auth_id",    session.getAttribute("MENULIST"));
        }
        
        return params;
    }
    
    /**
     * 모델에 객체를 추가한다.
     * 
     * @param model 모델
     * @param object 객체
     */
    protected void addObject(Model model, Object object) {
        if (object instanceof ResultDto) {
            ResultDto result = (ResultDto) object;
            
            model.addAttribute("page",    result.getPage());
            model.addAttribute("total",   result.getPages());
            model.addAttribute("records", result.getCount());
            model.addAttribute("rows",    result.getData());
        }
        else {
            model.addAttribute("data",    object);
        }
    }
    
    /**
     * 모델에 결과를 추가한다.
     * 
     * @param model 모델
     * @param result 결과
     */
    protected void addResult(Model model, ResultDto result) {
        // 처리가 완료된 경우
        if (result.getSuccess()) {
            model.addAttribute("success", result.getMessages());
        }
        // 오류가 발생한 경우
        else {
            model.addAttribute("error",   result.getMessages());
        }
    }
}