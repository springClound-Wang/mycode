package com.itzooedu.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.itzooedu.pojo.UserPo;
import com.itzooedu.service.UserService;
import com.itzooedu.utils.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @PAGENAME com.itzooedu.controller
 * @PROJECTNAME test-github-login
 * @DESCRIPTION 登陆
 * @AOUTH Jeff
 * @createtime 2018/12/919:39
 * @QQ 1142013568
 * @URL http://www.itzooedu.xyz
 */
@RequestMapping("/login")
@Controller
public class LoginController {
    @Autowired
    UserService userService;
    @Value("${github_authorize_url}")
    String GITHUB_AUTHORIZE_URL;
    @Value("${github_client_id}")
    String GITHUB_CLIENT_ID;
    @Value("${github_redirect_url}")
    String GITHUB_REDIRECT_URL;
    @Value("${github_client_secret}")
    String GITHUB_CLIENT_SECRET;
    @Value("${github_access_token_url}")
    String GITHUB_ACCESS_TOKEN_URL;
    @Value("${github_user_url}")
    String GITHUB_USER_URL;
    @GetMapping("/toLoginPage")
    public String toLoginPage(){
        return "login";
    }
    @PostMapping("/doLogin")
    @ResponseBody
    public String doLogin(@RequestParam("phone")String phone, @RequestParam("pwd") String pwd, HttpSession session){
       UserPo user= userService.selectUserByPhone(phone);
       if(user==null){
           return  "账户或密码错误";
       }
       if(!user.getPwd().equals(pwd)){
           return  "账户或密码错误";
       }
        String githubOpenid=(String) session.getAttribute("githubOpenid");
       System.out.println("doLogin()git账户:"+githubOpenid);
       if(!StringUtils.isEmpty(githubOpenid)){
           //修改关联github账户编号
         boolean b=  userService.updateGitIdByPhone(phone,githubOpenid);
           System.out.println("doLogin()修改的返回结果:"+b);
       }
       session.setAttribute("name",user.getPhone());
        return "登陆成功";
    }
    /**
     * @Description:    跳转到github授权链接
     * @Author:         Jeff
     * @CreateDate:     2018/12/9 20:40
     * @param [httpSession]
     * @return java.lang.String
     * @QQ 1142013568
     * @URL http://www.itzooedu.xyz
     * @Version: 1.0
     */
    @GetMapping("/localGithub")
    public String localGithub(HttpSession httpSession){
        String state= UUID.randomUUID().toString().replace("-","");
        httpSession.setAttribute("githubState",state);
        String address=GITHUB_AUTHORIZE_URL+"?client_id="+GITHUB_CLIENT_ID+"&redirect_uri="+GITHUB_REDIRECT_URL+"&response_type=code&state="+state;
        return "redirect:"+address;
    }
    @RequestMapping("/githubCallback")
    public ModelAndView githubCallback(String code,String state,HttpSession session){
        ModelAndView mv=new ModelAndView();
        if(StringUtils.isEmpty(code)){
            mv.setViewName("error");
            mv.addObject("error","code校验失败");
            return mv;
        }
      String sessionState= (String) session.getAttribute("githubState");
        if(StringUtils.isEmpty(state)||!state.equals(sessionState)){
            mv.setViewName("error");
            mv.addObject("error","state校验失败");
            return mv;
        }
        //用code 获取access_token
        Map<String,String> param=new HashMap<>();
        param.put("client_id",GITHUB_CLIENT_ID);
        param.put("client_secret",GITHUB_CLIENT_SECRET);
        param.put("redirect_uri",GITHUB_REDIRECT_URL);
        param.put("grant_type","authorization_code");
        param.put("code",code);
        String res= HttpClientUtil.doPost(GITHUB_ACCESS_TOKEN_URL,param);
        //json 字符串{"access_token":"056ecabd1cd640083a3cd3ded130ddbff214b8a4","token_type":"bearer","scope":""}
        JSONObject json= JSON.parseObject(res);
       String accessToken= json.getString("access_token");
       if(StringUtils.isEmpty(accessToken)){
           mv.setViewName("error");
           mv.addObject("error","access_token校验失败");
           return mv;
       }
        //带上access_token使用访问令牌访问API
       param=new HashMap<>();
        param.put("access_token",accessToken);
        String userApi= HttpClientUtil.doGet(GITHUB_USER_URL,param);
        if(StringUtils.isEmpty(userApi)){
            mv.setViewName("error");
            mv.addObject("error","用户信息校验失败");
            return mv;
        }
        //json 字符串
        JSONObject jsonApi= JSON.parseObject(userApi);
        //{"login":"springClound-Wang","id":34030156,"node_id":"MDQ6VXNlcjM0MDMwMTU2","avatar_url":"https://avatars0.githubusercontent.com/u/34030156?v=4",
        //         "gravatar_id":"","url":"https://api.github.com/users/springClound-Wang","html_url":"https://github.com/springClound-Wang",
        //         "followers_url":"https://api.github.com/users/springClound-Wang/followers",
        //         "following_url":"https://api.github.com/users/springClound-Wang/following{/other_user}",
        //         "gists_url":"https://api.github.com/users/springClound-Wang/gists{/gist_id}","
        //         starred_url":"https://api.github.com/users/springClound-Wang/starred{/owner}{/repo}",
        //         "subscriptions_url":"https://api.github.com/users/springClound-Wang/subscriptions",
        //         "organizations_url":"https://api.github.com/users/springClound-Wang/orgs","repos_url":"https://api.github.com/users/springClound-Wang/repos",
        //         "events_url":"https://api.github.com/users/springClound-Wang/events{/privacy}",
        //         "received_events_url":"https://api.github.com/users/springClound-Wang/received_events","type":"User",
        //         "site_admin":false,"name":null,"company":null,"blog":"","location":null,"email":null,"hireable":null,"bio":null,"public_repos":2,"public_gists":0,"followers":0,"
        //         following":0,"created_at":"2017-11-27T12:50:09Z","updated_at":"2018-11-02T08:50:18Z"}
        String id=jsonApi.getString("id");

      //用github关联的用户id去数据库查找用户是否存在
       UserPo u= userService.selectUserBygithubId(id);
        //1.存在就跳到首页
        if(u!=null){
            session.setAttribute("name",u.getPhone());
            mv.setViewName("index");
            return mv;
        }
        //2.不存在及关联账户
        session.setAttribute("githubOpenid",id);
        mv.setViewName("relation");
        return mv;
    }
}

