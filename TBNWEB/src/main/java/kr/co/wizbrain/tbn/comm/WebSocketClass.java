package kr.co.wizbrain.tbn.comm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.wizbrain.tbn.broadcast.web.BroadcastController;
import net.sf.json.JSON;

@ServerEndpoint("/webSocket.do")
public class WebSocketClass {
	public static final Logger logger = LoggerFactory.getLogger(WebSocketClass.class);
	private static List<Session> userList = Collections.synchronizedList(new ArrayList<>());
	
	@OnOpen
	public void handleOpen(Session session) {
		logger.debug("Client entry attemp");
		userList.add(session);
		logger.debug("Session: " + session);
		System.out.println("Session담음 : " + session);
		session.setMaxIdleTimeout(0);
		logger.debug("client[" + session + "] is now connected...");
		System.out.println("client[" + session + "] is now connected...");
	}
	
	@OnClose
	public void handleClose(Session session) {
		logger.debug("client is now disconnected...");
		System.out.println("client is now disconnected...");
		userList.remove(session);
	}

	@OnError
	public void handleError(Throwable e) {
		logger.debug("Error: " + e.toString());
		e.printStackTrace();
	}
	
	@OnMessage
	public void handleMessage(String msg, Session userSession) throws IOException {
		System.out.println("Message(WebSocket Server) : " + msg.toString());
		logger.debug("Message(WebSocket Server) : " + msg.toString());

//		String[] msgParsed = msg.split("\\|");
//		System.out.println("-------------------------------------");
//		System.out.println("수신자번호: " + msgParsed[0]);
//		System.out.println("내선번호: " + msgParsed[1]);
//		System.out.println("발신자번호: " + msgParsed[2]);
//		System.out.println("-------------------------------------");
//		
        userList.forEach(session -> {
            try {
                session.getBasicRemote().sendText(msg);
//            	if(msgParsed[0].equals("2088") && msgParsed[1].equals("154")) {
//            		session.getBasicRemote().sendText(msgParsed[2]);
//            	} else {
//            		System.out.println("다른지역번호");
//            	}

            } catch(IOException e) {
                e.printStackTrace();
            }
        });
		
//			userList.forEach(session -> {
////				if(session == userSession) {
////					return;
////				}
//				try {
//					String response = "";
//					if(message.startsWith("incidentId=")) {
//						response = " 서버로 정보 전송";
//					} else {
//						response = message;
//					}
//					session.getBasicRemote().sendText(response);
//				} catch(IOException e) {
//					e.printStackTrace();
//				}
//			});
	}
	
	@OnMessage
	public void handleMessage(byte[] message, Session session) {
		logger.debug("message 주고받는중");
		if (message.length <= 125) {
			String msg = new String(message);
			System.out.println(msg);
			msg = "echo - " + msg;
			//return msg.getBytes();
		} else {
			String msg = new String(message);
			System.out.println(message.length);
			System.out.println(msg.substring(msg.length() - 10));
			//return message;
		}
	}
	
}
































