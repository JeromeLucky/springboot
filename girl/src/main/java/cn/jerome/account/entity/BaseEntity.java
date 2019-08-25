/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: BaseEntity
 * Author:   JG
 * Date:     2019/8/25 13:50
 * Description: 基础类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package cn.jerome.account.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * 〈一句话功能简述〉<br> 
 * 〈基础类〉
 *
 * @author JG
 * @create 2019/8/25
 * @since 1.0.0
 */
/*
配置 创建时间个 更新时间为不传时取系统是见
 1.类上
       @MappedSuperclass
       @EntityListeners(AuditingEntityListener.class)

   字段上
    @CreatedDate、@LastModifiedDate、@CreatedBy、@LastModifiedBy前两个注解就是起这个作用的，后两个是设置修改人和创建人的

    启动类上
    AuditingEntityListener标签开启后，下面的时间标签才会生效
    @EnableJpaAuditing注解
 */

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @CreatedDate
    private Long createTime;

    @LastModifiedDate
    private Long updateTime;

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    /*
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name = "create_time",nullable=true,columnDefinition="timestamp default current_timestamp")
    */
      public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

}
