package com.demo.userservice.controller;

import com.demo.userservice.service.UserService;
import com.demo.vo.user.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public Object getUserList() throws Exception{
        this.userService.urerList();
        UserVo userVo=new UserVo(1,"Lisa",18);
        return userVo;
    }

    @GetMapping("/create")
    public Object createUser() throws Exception{
        long start = System.currentTimeMillis();
        String str = this.userService.createUser();
        System.out.println(String.format("新增用户{%s}功功，耗时：%S",str,System.currentTimeMillis()-start));
        return str;
    }

}
