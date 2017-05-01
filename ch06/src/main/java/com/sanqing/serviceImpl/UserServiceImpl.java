package com.sanqing.serviceImpl;

import com.sanqing.dao.DaoSupport;
import com.sanqing.po.User;
import com.sanqing.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends DaoSupport<User> implements UserService {

    public boolean login(String username, String password) {
        long count = (Long) em.createQuery("select count(o) from User o where o.username=?1 and o.password=?2")
                .setParameter(1, username).setParameter(2, password).getSingleResult();
        return count > 0;
    }

}
