package edu.jlu.fuliang.Service;

import edu.jlu.fuliang.domain.User;

public interface UserManageService {
    public void register(User user);

    public boolean login(User user);
}
