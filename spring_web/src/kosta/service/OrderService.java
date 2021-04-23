package kosta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import kosta.model.Item;
import kosta.model.ItemDao;
import kosta.model.Order;
import kosta.model.OrderDao;

@Service
public class OrderService {
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private ItemDao itemDao;
	
	//주문 서비스 구현(주문 등록 + 재고 조절)
	@Transactional(propagation=Propagation.REQUIRED,
			rollbackFor={Exception.class})  //transaction 설정, 옵션 : 없으면 생성 있으면 그냥 사용, rollback 조건 : exception 발생 시
	public void orderAction(Order order)throws Exception{
		//주문 등록
		orderDao.addOrder(order);
		
		//재고 관련 : 재고 수량 확인 -> 재고 부족 시 예외 처리
		if(itemDao.findItem(order.getNo()).getAmount() < order.getAmount()){
			throw new Exception("재고 부족");
		}
		
		//재고 있을 경우 재고 수정
		itemDao.updateItem(order);
		
	}
}
