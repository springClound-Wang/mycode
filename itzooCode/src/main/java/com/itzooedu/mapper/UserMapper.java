package com.itzooedu.mapper;

import com.itzooedu.pojo.UserPo;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @PAGENAME com.itzooedu.mapper
 * @PROJECTNAME test-github-login
 * @DESCRIPTION 用户数据链接层
 * @AOUTH Jeff
 * @createtime 2018/12/919:34
 * @QQ 1142013568
 * @URL http://www.itzooedu.xyz
 */
@Mapper
public interface UserMapper {
    //根据用户名查询账号信息
    @Select("select *from user where phone=#{phone}")
    UserPo selectUserByPhone(@Param("phone") String phone);
    //根据githubid查询用户信息
    @Select("select *from user where github_openid=#{openid}")
    UserPo selectUserBygithubId(@Param("openid") String openid);
    @Update(" update user set github_openid=#{openid} where phone=#{phone} ")
    int updateGitIdByPhone(@Param("phone") String phone,@Param("openid") String openid);
}
