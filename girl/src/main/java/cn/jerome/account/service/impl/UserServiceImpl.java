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

import cn.jerome.account.dao.UserRepository;
import cn.jerome.account.entity.AccountUser;
import cn.jerome.account.entity.AccoutQueryCons;
import cn.jerome.account.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;


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
public class UserServiceImpl implements IUserService<AccountUser> {

    @Autowired
    private UserRepository userRepository;
    @Override
    public AccountUser findById(Long id) {
        Assert.notNull(id,"ID must not be null");
        AccountUser u = userRepository.findByUserId(id);
        return u;
    }

    @Override
    public Page<AccountUser> findByPage(AccoutQueryCons queryCons) {
        Assert.notNull(queryCons, "queryCons must not be null");

        Sort.Direction sort_direction = Sort.Direction.ASC.toString().equalsIgnoreCase(queryCons.getPage().getSort()) ? Sort.Direction.ASC : Sort.Direction.DESC;
        //设置排序对象参数
        Sort sort = new Sort(sort_direction, queryCons.getPage().getSdix()); //设置排序字段和顺序
        //创建分页对象,从第一页开始，此处user.getPage()-1要减一
        PageRequest pageRequest = new PageRequest(queryCons.getPage().getPage() - 1, queryCons.getPage().getSize(), sort);
        Page<AccountUser> users = userRepository.findAll(pageRequest);
        return users;
    }

    @Override
    public AccountUser saveAcctuser(AccountUser accountUser) {
        Assert.notNull(accountUser,"user must not be null");
        AccountUser acctUser = userRepository.save(accountUser);
        return acctUser;
    }

    @Override
    public List<AccountUser> findBySort(AccoutQueryCons queryCons) {
        Assert.notNull(queryCons,"sort must not be null");
        Sort.Direction sort_direction = Sort.Direction.ASC.toString().equalsIgnoreCase(queryCons.getPage().getSort()) ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort1 = new Sort(sort_direction, queryCons.getPage().getSdix()); //设置排序字段和顺序
        List<AccountUser> userlsit = userRepository.findAll(sort1);
        return userlsit;
    }

    @Override
    public List<AccountUser> saveAll(List<AccountUser> list) {
        List<AccountUser> accountUsers = userRepository.save(list);
        return accountUsers;
    }

    @Override
    public int updateName(String name, Long userId) {

        int n = userRepository.updateName(name,userId);

        return n;
    }
}
