package com.jsw.mult.service;

import com.jsw.mult.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class OrderService {

    @Resource
    @Qualifier("orderTemplate")
    private JdbcTemplate orderJdbcTemplate;

    @Resource
    @Qualifier("customerTemplate")
    private JdbcTemplate customerJdbcTemplate;

    //更新客户账户
    private static final String DEPOSIT_UPDATE = "UPDATE customer SET deposit=deposit-? where id=?";

    //生成新订单
    private static final String ORDER_INSERT = "INSERT INTO t_order (customerId, title, amount) VALUES  (?, ?, ?)";

    //创建订单S
    @Transactional(propagation = Propagation.REQUIRED)
    public void createOrder(Order order) throws Exception {
        customerJdbcTemplate.update(DEPOSIT_UPDATE, order.getAmount(), order.getCustomerId());

        error(order.getError());


        orderJdbcTemplate.update(ORDER_INSERT, order.getCustomerId(), order.getTitle(), order.getAmount());

        error(order.getError());
    }


    private void error(String type) throws Exception {
        switch (type){
            case "error1":
                error1();
                break;
            case "error2":
                error2();
                break;
            default:
                break;
        }
    }

    private void error1()  {
        throw new RuntimeException();
    }

    private void error2() throws Exception {
        throw new RuntimeException();
    }

}
