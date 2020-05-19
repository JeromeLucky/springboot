/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: AccountUser
 * Author:   JG
 * Date:     2019/3/1 20:22
 * Description: 用户信息
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package cn.jerome.module.account.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 〈一句话功能简述〉<br> 
 * 〈用户信息〉
 *
 * @author JG
 * @create 2019/3/1
 * @since 1.0.0
 */
@Entity
/*
//手动定义 映射的table 和table的 主键和索引
@Table(name="USER",uniqueConstraints = {
        @UniqueConstraint(name="",columnNames = {""})
},
indexes = {
        @Index(name="",columnList = "")
})*/
public class AccountUser  extends BaseEntity implements Serializable{
   /*
    //主键id的oracle生成方式
    @Id
    @SequenceGenerator(name = "user_generator", sequenceName = "user_sequence", initialValue = 23)
    @GeneratedValue(generator = "user_sequence")*/
    /*
    //主键id的值 生成方式 system-uuid
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "ID", unique = true, nullable = false, length = 32)
    */
   //ID 根据customer_gen 主键生成表获取
   @TableGenerator(name = "customer_gen",

           table="tb_generator",

           pkColumnName="gen_name",

           valueColumnName="gen_value",

           pkColumnValue="CUSTOMER_PK",

           allocationSize=1,

           initialValue = 100

   )
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="customer_gen")
    private long userId;
    @Column(nullable = false)
    private String acctName;
    @Column(nullable = false)
    private String pwd;
    @Column(nullable = false)
    private String email;

    public AccountUser(String acctName, String pwd, String email) {
        this.acctName = acctName;
        this.pwd = pwd;
        this.email = email;
    }
    public AccountUser() {
        super();
    }
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
