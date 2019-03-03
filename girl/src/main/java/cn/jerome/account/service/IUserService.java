package cn.jerome.account.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.lang.Long;
/**
 * Created by JG on 2019/3/3.
 */
public interface IUserService<T> {
    public T findById(Long id);
    public Page<T> findByPage(Pageable pageable);
}
