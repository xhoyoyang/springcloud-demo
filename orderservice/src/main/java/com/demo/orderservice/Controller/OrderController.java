package com.demo.orderservice.Controller;

import com.demo.vo.user.OrderVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/order")
public class OrderController {

    @GetMapping("/list")
    public Object gttOrderList(){
        System.out.println("收到请求订单信息");
        OrderVo orderVo = new OrderVo(2021011411,1280.05,new Date());
        return orderVo;
    }
}
