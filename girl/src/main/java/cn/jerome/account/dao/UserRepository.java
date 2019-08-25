package cn.jerome.account.dao;

import cn.jerome.account.entity.AccountUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
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


    @Modifying
    @Query("update  AccountUser  a set a.acctName= ?1 where  a.userId = ?2")
    int updateName(String name,Long userId);
}
