/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: UserServiceImpl
 * Author:   JG
 * Date:     2019/3/3 15:55
 * Description: ddd
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package cn.jerome.account.service.impl;

import cn.jerome.account.entity.User;
import cn.jerome.account.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 〈一句话功能简述〉<br> 
 * 〈ddd〉
 *
 * @author JG
 * @create 2019/3/3
 * @since 1.0.0
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService<User> {
    @Override
    public User findById(Long id) {
        Assert.notNull(id,"ID must not be null");
        return null;
    }
    @Override
    public Page<User> findByPage(Pageable pageable) {
        Assert.notNull(pageable, "pageable must not be null");
        return null;
    }
}
