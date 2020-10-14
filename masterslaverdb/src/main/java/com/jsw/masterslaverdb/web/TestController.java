package com.jsw.masterslaverdb.web;

import com.jsw.masterslaverdb.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/api")
@Slf4j
public class TestController {


    @Resource
    UserService userService;

    @RequestMapping("query")
    @ResponseBody
    public String queryAll(){
        StringBuffer sb = new StringBuffer(50);
        log.info("主库{}", userService.queryById("1"));
        sb.append("主库").append(userService.queryById("1")).append("\r\n");
        log.info("从库{}", userService.findById("1"));
        sb.append("从库").append(userService.findById("1")).append("\r\n");
        return sb.toString();
    }
}
