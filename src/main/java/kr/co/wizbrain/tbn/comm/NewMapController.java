package kr.co.wizbrain.tbn.comm;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

//해당 컨트롤러의 모든 요청에 대한 접근 허용(아래 도메인 두개에 대해서만..)
@RestController
@CrossOrigin(origins = {"http://localhost:8087", "http://192.168.117.25:8084" }) 
public class NewMapController {

	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/mapTest/test1.do")
	public ModelAndView greeting(@RequestParam(required = false, defaultValue = "World") String name) {
		ModelAndView mv = new ModelAndView("jsonView");
		System.out.println("==== get greeting ====");
		mv.addObject("page", "https://map.kakao.com");
		return mv;
	}
}
