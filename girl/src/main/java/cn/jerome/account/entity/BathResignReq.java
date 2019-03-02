/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: BathResignReq
 * Author:   JG
 * Date:     2019/3/1 20:24
 * Description: 批量注册
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package cn.jerome.account.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈批量注册〉
 *
 * @author JG
 * @create 2019/3/1
 * @since 1.0.0
 */

public class BathResignReq {
    private long orderId;
    private List<User> acctArray;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public List<User> getAcctArray() {
        return acctArray;
    }

    public void setAcctArray(List<User> acctArray) {
        this.acctArray = acctArray;
    }
}
