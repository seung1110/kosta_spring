package kosta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kosta.service.HelloService;

@Controller
public class HelloController {
	@Autowired
	private HelloService service;

	@RequestMapping("/hello.do")
	public ModelAndView hello(){
		//1.비즈니스 로직 호출
		//2. 실행된 데이터를 저장
		//3. 어떤 뷰로 갈지 결정
		ModelAndView mav = new ModelAndView();

		mav.addObject("message", service.getMessage());  // 데이터 추가
		mav.setViewName("hello");  // view 이름 지정
		return mav;
	}
}
