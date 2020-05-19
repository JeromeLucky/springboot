/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: AccoutQueryCons
 * Author:   JG
 * Date:     2019/8/25 14:37
 * Description: 条件查询账户
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package cn.jerome.module.account.entity;

/**
 * 〈一句话功能简述〉<br> 
 * 〈条件查询账户〉
 *
 * @author JG
 * @create 2019/8/25
 * @since 1.0.0
 */

public class AccoutQueryCons {
    public AccoutQueryCons() {
        super();
    }
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    private Page page;

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
