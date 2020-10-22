package com.jsw.mult.web;

import com.jsw.mult.domain.Order;
import com.jsw.mult.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("test")
public class ApiController {

    private static final Logger log = LoggerFactory.getLogger(ApiController.class);

    @Autowired
    OrderService orderService;

    @RequestMapping("/create")
    @ResponseBody
    public String order(@RequestBody Order order) throws Exception {
        try{
            orderService.createOrder(order);
        }catch (Exception e){
            log.error("", e);
        }
        return "ok";
    }

}
