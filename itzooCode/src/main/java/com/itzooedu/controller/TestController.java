package com.itzooedu.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @PAGENAME com.itzooedu.controller
 * @PROJECTNAME test-github-login
 * @DESCRIPTION 测试
 * @AOUTH Jeff
 * @createtime 2018/12/919:28
 * @QQ 1142013568
 * @URL http://www.itzooedu.xyz
 */
@RestController
public class TestController {
    @RequestMapping("/test")
    public String test(){
        return "hello-world";
    }

}
