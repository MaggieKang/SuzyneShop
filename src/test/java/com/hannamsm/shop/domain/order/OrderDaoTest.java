package com.hannamsm.shop.domain.order;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hannamsm.shop.domain.order.dao.OrderDao;
import com.hannamsm.shop.domain.order.vo.Order;
import com.hannamsm.shop.domain.order.vo.OrderSearch;
import com.hannamsm.shop.global.BaseDaoTest;

public class OrderDaoTest extends BaseDaoTest {

	@Autowired
	private OrderDao orderDao;

	@Test
	@DisplayName("주문 목록 조회 테스트")
	public void findAll() throws Exception {
		OrderSearch orderSearch = new OrderSearch(1, 10);

		List<Order> list = this.orderDao.findAll(orderSearch);
		System.out.println(list.toString());

		assertAll("list",
				()->assertNotNull(list));
	}

	@Test
	@DisplayName("주문 목록 건수 조회 테스트")
	public void findAllCount() throws Exception {
		OrderSearch orderSearch = new OrderSearch(1, 10);

		int count = this.orderDao.findAllCount(orderSearch);
		System.out.println(count);

		assertAll("count",
				()->assertNotNull(count));
	}
}
