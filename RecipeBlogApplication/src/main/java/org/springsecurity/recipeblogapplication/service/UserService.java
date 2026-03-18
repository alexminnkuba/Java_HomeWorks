package org.springsecurity.recipeblogapplication.service;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springsecurity.recipeblogapplication.entity.User;
import org.springsecurity.recipeblogapplication.repository.UserRepo;

@Service
public class UserService implements IUserService{
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user) {
       user.setPassword(passwordEncoder.encode(user.getPassword()));
       user.setRole("ROLE_USER");
       return userRepo.save(user);
    }

    @Override
    public void removeSessionMessage() {
        HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes()))
                .getRequest().getSession();
        session.removeAttribute("msg");
    }
}
