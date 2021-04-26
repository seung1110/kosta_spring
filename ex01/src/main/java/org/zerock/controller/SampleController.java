package org.zerock.controller;

import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.TodoDTO;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/sample")
@Log4j
public class SampleController {
	
	@RequestMapping("")
	public void basic(){
		log.info("basic.........................");
	}
	
	@RequestMapping("/ex01")
	public String ex01(SampleDTO dto){ // get 요청 방식으로 들어온 데이터가 자동으로 paramter의 객체 안에 들어간다.
		log.info(dto.toString());
		
		return "ex01";
	}
	
	@RequestMapping("/ex02")
	public String ex02(@RequestParam("name") String name, @RequestParam("age") int age){
		
		log.info("name : " + name);
		log.info("age : " + age);
		
		return "ex02";
	}
	
	@RequestMapping("/ex03")
	public String ex03(TodoDTO dto){
		log.info(dto.toString());
		
		return "ex03";
	}
	
	@RequestMapping("/ex04")
	public String ex04(SampleDTO dto,@ModelAttribute("page") int page){
		log.info("dto : " + dto);
		log.info("page : " + page);
		
		return "/sample/ex04";
	}
	
	@RequestMapping("/ex05")
	public void ex05(){
		log.info("/ex05...........................");
	}
	
	@GetMapping("/ex06")
	public @ResponseBody SampleDTO ex06(){
		SampleDTO dto = new SampleDTO();
		dto.setAge(20);
		dto.setName("홍길동");
		log.info("/ex06...................");
		return dto;
	}

	@GetMapping("/ex07")
	public ResponseEntity<String>ex07(){
		log.info("/ex07...................");
		
		String msg = "{\"name\":\"홍길동\"}";
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "application/json;charset=UTF-8");
		
		return new ResponseEntity<String>(msg,header,HttpStatus.OK);
	}
	
	@GetMapping("/exUpload")
	public void exUpload(){
		log.info("/exUpload........................");
	}
	
	@PostMapping("/exUploadPost")
	public void exUploadPost(List<MultipartFile> files){
		log.info("exUploadPost.......................");
		files.forEach(file->{
			log.info("name : " + file.getOriginalFilename());
			log.info("size : " + file.getSize());
			
		});
	}
}
