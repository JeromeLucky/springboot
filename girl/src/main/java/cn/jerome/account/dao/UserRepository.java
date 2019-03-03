package cn.jerome.account.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import java.lang.Long;
import cn.jerome.account.entity.User;

/**
 * Created by JG on 2019/3/2.
 */
public interface UserRepository extends JpaRepository<User,Long>{

}
