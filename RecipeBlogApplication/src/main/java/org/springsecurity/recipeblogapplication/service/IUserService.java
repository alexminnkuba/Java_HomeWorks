package org.springsecurity.recipeblogapplication.service;

import org.springsecurity.recipeblogapplication.entity.User;

public interface IUserService {
    User saveUser(User user);
    void removeSessionMessage();
}
