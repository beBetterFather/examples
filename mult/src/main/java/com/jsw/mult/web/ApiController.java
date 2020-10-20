package com.jsw.mult.web;

import com.jsw.mult.domain.Order;
import com.jsw.mult.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class ApiController {

    @Autowired
    OrderService orderService;

    @RequestMapping("order")
    public void order(@RequestBody Order order){
        orderService.createOrder(order);
    }
}
