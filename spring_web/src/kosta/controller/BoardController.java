package kosta.controller;

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

import kosta.model.Board;
import kosta.model.BoardDao;


@Controller
public class BoardController {
	@Autowired
	private BoardDao dao;

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
	
	
//	public ModelAndView insertForm(){
//		//url ��� : /board_insert.do => insert_form.jsp ����
//		ModelAndView mav = new ModelAndView();
//		mav.setViewName("insert_form");
//		
//		return mav;
//	}
}