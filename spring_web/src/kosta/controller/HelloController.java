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
		//1.����Ͻ� ���� ȣ��
		//2. ����� �����͸� ����
		//3. � ��� ���� ����
		ModelAndView mav = new ModelAndView();

		mav.addObject("message", service.getMessage());  // ������ �߰�
		mav.setViewName("hello");  // view �̸� ����
		return mav;
	}
}
