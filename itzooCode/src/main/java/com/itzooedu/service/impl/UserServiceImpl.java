package com.itzooedu.service.impl;

import com.itzooedu.mapper.UserMapper;
import com.itzooedu.pojo.UserPo;
import com.itzooedu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @PAGENAME com.itzooedu.service.impl
 * @PROJECTNAME test-github-login
 * @DESCRIPTION 用户实现类
 * @AOUTH Jeff
 * @createtime 2018/12/919:40
 * @QQ 1142013568
 * @URL http://www.itzooedu.xyz
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    /**
     * @Description:    根据手机号查询用户信息
     * @Author:         Jeff
     * @CreateDate:     2018/12/9 19:57
     * @param [phone]
     * @return com.itzooedu.pojo.UserPo
     * @QQ 1142013568
     * @URL http://www.itzooedu.xyz
     * @Version: 1.0
     */
    @Override
    public UserPo selectUserByPhone(String phone) {

        return  userMapper.selectUserByPhone(phone);
    }
    /**
     * @Description:    根据手机号修改openid
     * @Author:         Jeff
     * @CreateDate:     2018/12/9 21:47
     * @param [phone, openid]
     * @return boolean
     * @QQ 1142013568
     * @URL http://www.itzooedu.xyz
     * @Version: 1.0
     */
    @Override
    public boolean updateGitIdByPhone(String phone, String openid) {
        return userMapper.updateGitIdByPhone(phone,openid)>0;
    }

    /**
     * @Description:    根据githubid查询用户信息
     * @Author:         Jeff
     * @CreateDate:     2018/12/9 19:57
     * @param [openid]
     * @return com.itzooedu.pojo.UserPo
     * @QQ 1142013568
     * @URL http://www.itzooedu.xyz
     * @Version: 1.0
     */
    @Override
    public UserPo selectUserBygithubId(String openid) {

        return userMapper.selectUserBygithubId(openid);
    }
}
