package kosta.controller;

import java.io.File;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kosta.model.Board;
import kosta.model.BoardDao;


@Controller
public class BoardController {
	@Autowired
	private BoardDao dao;
	private String uploadDir = "C:/upload";

	/*@RequestMapping(value="/board_insert",method=RequestMethod.GET)*/
	@GetMapping("/board_insert")
	public String insertForm(@ModelAttribute("boardCommand") Board board){
		return "insert_form";
	}
	
/*	@RequestMapping(value="/board_insert",method=RequestMethod.POST)*/
	@PostMapping("/board_insert")
	public String board_insert(@ModelAttribute("boardCommand") @Valid Board board, BindingResult errors){
								// boardCommnad라는 이름으로 view로 board 객체를 가져가겠다는 의미
		if(errors.hasErrors()){
			System.out.println("error 발생");
			return "insert_form";
		}
		MultipartFile multipartFile = board.getUploadFile();
		if(multipartFile != null){
			String fname = multipartFile.getOriginalFilename();
			board.setFname(fname);
			
			try {
				multipartFile.transferTo(new File(uploadDir,fname)); // file 객체 생성 후 저장
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		int r = dao.insertBoard(board);
		System.out.println(r);
		return "redirect:board_list";
	}
	
	@GetMapping("/board_list")
	public String board_list(Model model){ // view로 데이터 전달
		List<Board>list = dao.boardList();
		model.addAttribute("list", list);
		return "board_list";
	}
	
	@GetMapping("/board_detail{seq}")
	public String board_detail(@PathVariable int seq, Model model){
		Board board = dao.boardDetail(seq);
		model.addAttribute("board", board);
		return "board_detail";
	}
	
	@GetMapping("/board_download{fname}")
	public String board_download(@PathVariable String fname, Model model)throws Exception{
		File file = new File(uploadDir,fname);
		model.addAttribute("downloadFile", file);
		
		return "downloadView"; // DownloadView.class를 bean에 추가 후 가능 => view 위치가 바뀌므로 resolver 설정 추가
	}
	
//	public ModelAndView insertForm(){
//		//url ��� : /board_insert.do => insert_form.jsp ����
//		ModelAndView mav = new ModelAndView();
//		mav.setViewName("insert_form");
//		
//		return mav;
//	}
}