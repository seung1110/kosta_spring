package kosta.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kosta.model.Board;
import kosta.model.BoardDao;
import kosta.model.Member;

@RestController
public class JsonController {
	@Autowired
	private BoardDao dao;
	
	static List<Member> memberList = new ArrayList<>();
	
	static{
		memberList.add(new Member("박길동",20,"부산"));
		memberList.add(new Member("김길동",30,"대전"));
		memberList.add(new Member("최길동",40,"대구"));
	}
	
	@RequestMapping("board_list_json")
	public List<Board> board_list_json(){
		return dao.boardList();
	}
	
	@RequestMapping("member_json")
	public Member member_json(){
		return new Member("홍길동",20,"서울");
	}
	
	@RequestMapping("member_list_json")
	public List<Member> member_list_json(){
		return memberList;
	}
	
	@PostMapping("member_save")
	public void member_save(@RequestBody Member m){
		memberList.add(m);
	}
}
