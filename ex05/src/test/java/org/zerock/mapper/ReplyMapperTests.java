package org.zerock.mapper;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@Log4j
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class ReplyMapperTests {
	
	@Setter(onMethod_=@Autowired)
	private ReplyMapper mapper;
	
	private Long[] bnoArr = { 393222L, 393221L, 393220L, 393219L, 393218L, 393217L, 393216L, 393215L, 393214L,
			393213L };
//	@Test
//	public void testMapper(){
//		log.info(mapper);
//	}
	
//	@Test
//	public void testCreate(){
//		IntStream.rangeClosed(1, 10).forEach(i ->{
//			ReplyVO vo = new ReplyVO();
//			
//			vo.setBno(bnoArr[i%5]);
//			vo.setReply("댓글 테스트" + i);
//			vo.setReplyer("replayer" + i);
//			
//			mapper.insert(vo);
//		});
//	}
	
//	@Test
//	public void testRead(){
//		ReplyVO vo = mapper.read(5L);
//		log.info(vo);
//	}
//	@Test
//	public void testDelte(){
//		mapper.delete(1L);
//	}
	
//	@Test
//	public void testUpdate(){
//		ReplyVO vo = mapper.read(10L);
//		vo.setReply("update reply");
//		int count = mapper.update(vo);
//		
//		log.info("Update count : "+ count);
//	}
	
//	@Test
//	public void testList(){
//		Criteria cri = new Criteria();
//		
//		List<ReplyVO> replies = mapper.getListWithPaging(cri, bnoArr[0]);
//		
//		replies.forEach(reply -> log.info(reply));
//	}
	
	@Test
	public void testList2(){
		Criteria cri = new Criteria(2,10);
		
		List<ReplyVO> replies = mapper.getListWithPaging(cri, 393222L);
		
		replies.forEach(reply->{
			log.info(reply);
		});
	}
	
	
}
