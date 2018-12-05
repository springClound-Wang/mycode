package com.itzooedu.springbootmybatis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 汪正章
 * @ClassName IndexController
 * @create 2018-12-05 15:01
 * @desc index
 * @Version 1.0V
 **/
@Controller
@RequestMapping("/index")
public class IndexController {

    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        return "hello-word";
    }
}
