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

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 〈一句话功能简述〉<br> 
 * 〈用户信息〉
 *
 * @author JG
 * @create 2019/3/1
 * @since 1.0.0
 */
@Entity
/*@Table(name="USER",uniqueConstraints = {
        @UniqueConstraint(name="",columnNames = {""})
},
indexes = {
        @Index(name="",columnList = "")
})*/
public class User implements Serializable{
   /* @Id
    @SequenceGenerator(name = "user_generator", sequenceName = "user_sequence", initialValue = 23)
    @GeneratedValue(generator = "user_sequence")*/
    /*
     @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "ID", unique = true, nullable = false, length = 32)
    */
    private long userId;
    @Column(nullable = false)
    private String acctName;
    @Column(nullable = false)
    private String pwd;
    @Column(nullable = false)
    private String email;
    private Date createTime;

    public User(String acctName, String pwd, String email,Date createTime) {
        this.acctName = acctName;
        this.pwd = pwd;
        this.email = email;
        this.createTime=createTime;
    }
    public User() {
        super();
    }
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
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time",nullable=true,columnDefinition="timestamp default current_timestamp")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
