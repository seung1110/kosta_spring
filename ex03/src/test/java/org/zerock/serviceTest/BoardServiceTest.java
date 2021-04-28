package org.zerock.serviceTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.service.BoardService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardServiceTest {
	@Setter(onMethod_=@Autowired)
	private BoardService service;
	
//	@Test
//	public void serviceTest(){
//		log.info(service);
//		service.getList().forEach(board->{log.info(board);});
//	}
//	@Test
//	public void serviceRegisterTest(){
//		BoardVO board = new BoardVO();
//		board.setTitle("service test");
//		board.setContent("service insert");
//		board.setWriter("service");
//		
//		service.register(board);
//		
//		log.info("생성된 게시물의 번호 : " + board.getBno());
//	}
	
//	@Test
//	public void serviceRegisterTest(){
//		log.info(service.get(8L));
//	}
	
	@Test
	public void paginTest(){
		service.getList(new Criteria(2,10)).forEach(board->{
			log.info(board);
		});
	}
}
