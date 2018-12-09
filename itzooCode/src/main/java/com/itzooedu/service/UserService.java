package com.itzooedu.service;

import com.itzooedu.pojo.UserPo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @PAGENAME com.itzooedu.service
 * @PROJECTNAME test-github-login
 * @DESCRIPTION 用户
 * @AOUTH Jeff
 * @createtime 2018/12/919:39
 * @QQ 1142013568
 * @URL http://www.itzooedu.xyz
 */
public interface UserService {

    UserPo selectUserByPhone( String phone);
    //根据githubid查询用户信息
    UserPo selectUserBygithubId(String openid);
    boolean  updateGitIdByPhone(String phone,String openid );
}
