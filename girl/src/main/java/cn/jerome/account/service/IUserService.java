package cn.jerome.account.service;
import cn.jerome.account.entity.AccoutQueryCons;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.lang.Long;
import java.util.List;

/**
 * Created by JG on 2019/3/3.
 */
public interface IUserService<T> {
    public T findById(Long id);
    public Page<T> findByPage(AccoutQueryCons queryCons);
    public T saveAcctuser(T t);
    public List<T> findBySort(AccoutQueryCons queryCons);
    public List<T> saveAll(List<T> list);
 }
