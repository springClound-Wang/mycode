package com.itzooedu.pojo;

/**
 * @PAGENAME com.itzooedu.pojo
 * @PROJECTNAME test-github-login
 * @DESCRIPTION 用户类
 * @AOUTH Jeff
 * @createtime 2018/12/919:33
 * @QQ 1142013568
 * @URL http://www.itzooedu.xyz
 */
public class UserPo {
     String phone;
     String pwd;
     String githubOpenid;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getGithubOpenid() {
        return githubOpenid;
    }

    public void setGithubOpenid(String githubOpenid) {
        this.githubOpenid = githubOpenid;
    }

    @Override
    public String toString() {
        return "UserPo{" +
                "phone='" + phone + '\'' +
                ", pwd='" + pwd + '\'' +
                ", githubOpenid='" + githubOpenid + '\'' +
                '}';
    }
}
