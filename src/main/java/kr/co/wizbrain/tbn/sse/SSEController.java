package kr.co.wizbrain.tbn.sse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.faces.application.Application;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Controller
@RequestMapping("/sse")
public class SSEController {
	private static final Map<String, SseEmitter> CLIENTS = new ConcurrentHashMap<>();
	private ExecutorService executor = Executors.newCachedThreadPool();
	
	@GetMapping("/subscribe.do")
	public SseEmitter subscribe(String id) {
		System.out.println("subscribe");
		System.out.println("id: " + id);
		SseEmitter emitter = new SseEmitter(-1L);
		CLIENTS.put(id, emitter);
		System.out.println("Clients size: " + CLIENTS.size());
		emitter.onCompletion(() -> CLIENTS.remove(id));
		return emitter;
	}
	
	@GetMapping(path="/publish.do")
	public void publish(String message) {
		System.out.println("publish");
		System.out.println("message: " + message);
		List<String> deadIds = new ArrayList<>();
		
		for(String key : CLIENTS.keySet()) {
			SseEmitter em = CLIENTS.get(key);
			try {
				em.send(SseEmitter.event().name("sse").data("Emitter with Event"));
				System.out.println("data sent");
				em.complete();
				System.out.println("completed");
			} catch (IOException e) {
				deadIds.add(key);
				e.printStackTrace();
			} 
		}
	}
	
}















