package com.stevy.contratti.service.email;

import com.stevy.contratti.models.Role;
import com.stevy.contratti.models.User;
import com.stevy.contratti.repository.RoleRepository;
import com.stevy.contratti.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    @Autowired
    PasswordEncoder encoder;

    public UserServiceImpl(UserRepository userRepository,RoleRepository roleRepository) {
        this.userRepository= userRepository;
        this.roleRepository = roleRepository;

    }

    @Override
    public User saveUser(String username, String password, String confirmedPassword) {
        return null;
    }

    @Override
    public User updatePassword(String username, String password, String confirmedPassword, int id) {
        User user = userRepository.findAppUserByid((long) id);
        if(user==null) throw new RuntimeException("User non exists");
        if(!password.equals(confirmedPassword)) throw new RuntimeException("Please confirm your password");
        user.setUsername(username);
        user.setPassword(password);
        user.setPassword(encoder.encode(password));
        userRepository.save(user);
        System.out.println(user.getUsername());
        System.out.println(user.getId());
        return userRepository.save(user);
    }


}
