package org.zerock.dbtest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.mapper.BoardMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTest {
	@Setter(onMethod_=@Autowired)
	private BoardMapper mapper;
	
	@Test
	public void testGetList(){
		mapper.getList().forEach(board->{log.info(board);});
	}
//	@Test
//	public void insertBoard(){
//		BoardVO board = new BoardVO();
//		board.setTitle("insert test");
//		board.setContent("is test");
//		board.setWriter("tester");
//		
////		mapper.insert(board); // 출력 시 board의 bno값은 null
//		int bno = mapper.insertSelectKey(board);
//		log.info("------------------------------");
//		log.info(bno);
//		log.info(board); // 출력 시 board의 bno값이 null이 아님을 확인할 수 있다.
//		log.info("------------------------------");
//	}
	
//	@Test
//	public void readTest(){
//		BoardVO board = mapper.read(1L);
//		log.info("------------------------------");
//		log.info(board); // 정상적으로 board가 가져왔는지 확인
//		log.info("------------------------------");
//	}
//	@Test
//	public void deleteTest(){
//		log.info("------------------------------");
//		log.info("delete count : " + mapper.delete(1L)); // 정상적으로 삭제 시 1
//		log.info("------------------------------");
//	}
//	@Test
//	public void updateTest(){
//		BoardVO board = new BoardVO();
//		board.setBno(7L);
//		board.setTitle("update test");
//		board.setContent("update is clear");
//		board.setWriter("updater");
//		
//		log.info("------------------------------");
//		log.info("update count : " + mapper.update(board)); // 정상적으로 업데이트 시 1
//		log.info("------------------------------");
//	}
}
