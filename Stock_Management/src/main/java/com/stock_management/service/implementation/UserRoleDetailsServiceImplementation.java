//package com.stock_management.service.implementation;
//
//import com.stock_management.entity.User;
//import com.stock_management.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Objects;
//
//@Service
//public class UserRoleDetailsServiceImplementation implements UserDetailsService {
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username);
//        UserRoleDetails userRoleDetails;
//        if (Objects.nonNull(user)) {
//            userRoleDetails = new UserRoleDetails();
//            userRoleDetails.setUser(user);
//        } else {
//            throw new UsernameNotFoundException("User does not exist");
//        }
//        return userRoleDetails;
//    }
//}
