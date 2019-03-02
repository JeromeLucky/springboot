/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: User
 * Author:   JG
 * Date:     2019/3/1 20:22
 * Description: 用户信息
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package cn.jerome.account.entity;

import java.io.Serializable;

/**
 * 〈一句话功能简述〉<br> 
 * 〈用户信息〉
 *
 * @author JG
 * @create 2019/3/1
 * @since 1.0.0
 */

public class User implements Serializable{
        private long userId;
        private String acctName;
        private String pwd;
        private String email;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getAcctName() {
        return acctName;
    }

    public void setAcctName(String acctName) {
        this.acctName = acctName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
