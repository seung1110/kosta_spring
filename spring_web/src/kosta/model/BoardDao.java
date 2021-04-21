package kosta.model;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kosta.mapper.BoardMapper;

@Repository
public class BoardDao {
	@Autowired
	private SqlSessionTemplate sqlTemplate; // 의존 관계 주입
	
	public int insertBoard(Board board){
		int result = sqlTemplate.getMapper(BoardMapper.class).insertBoard(board);
		return result;
	}
	
	public List<Board> boardList(){
		return (List<Board>)sqlTemplate.getMapper(BoardMapper.class).boardList();
	}
	
	public Board boardDetail(int seq){
		return sqlTemplate.getMapper(BoardMapper.class).boardDetail(seq);
	}
}
