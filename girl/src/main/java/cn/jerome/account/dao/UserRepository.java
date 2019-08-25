package cn.jerome.account.dao;

import cn.jerome.account.entity.AccountUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.lang.Long;
import java.util.List;

/**
 * Created by JG on 2019/3/2.
 */
@Repository
public interface UserRepository extends JpaRepository<AccountUser,Long>{
    AccountUser findByUserId (Long userId);
    List<AccountUser> findAll();
    AccountUser save(AccountUser accountUser);
    List<AccountUser> save(List<AccountUser> accountUsers);

}
