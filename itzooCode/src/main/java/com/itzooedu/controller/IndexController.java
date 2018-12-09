package com.itzooedu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @PAGENAME com.itzooedu.controller
 * @PROJECTNAME test-github-login
 * @DESCRIPTION index
 * @AOUTH Jeff
 * @createtime 2018/12/920:14
 * @QQ 1142013568
 * @URL http://www.itzooedu.xyz
 */
@Controller
public class IndexController {
    @RequestMapping("/index")
    public String index(){
        return "index";
    }
}
