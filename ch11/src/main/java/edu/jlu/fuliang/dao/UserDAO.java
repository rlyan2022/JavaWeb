package edu.jlu.fuliang.dao;

import edu.jlu.fuliang.domain.User;

import java.util.List;

public interface UserDAO {
    public User findById(long id);

    public void save(User user);

    public List<User> findByUserNameAndPassword(String userName, String password);

    public List<User> findByUserName(String userName);
}
