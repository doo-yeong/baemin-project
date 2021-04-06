package com.dy.baeminclone.service;

import com.dy.baeminclone.domain.Cart;
import com.dy.baeminclone.domain.User;
import com.dy.baeminclone.repository.CartRepository;
import com.dy.baeminclone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final UserRepository userRepository;

    public void loadUsers() {
        User user = User.builder()
                .email("user01@test.com")
                .username("user01")
                .password("user01")
                .account(100000L)
                .regdate(LocalDateTime.now())
                .build();
        try {
            userRepository.save(user);
        } catch (Throwable t){
            t.printStackTrace();
        }
    }

    public User signUp(User user) {
        user.setRegdate(LocalDateTime.now());
        try {
            Cart cart = new Cart();
            cart.setUser(user);
            userRepository.save(user);
        } catch (Throwable e){
            logger.error(e.getMessage());
            return null;
        }

        return user;
    }

    public boolean signIn(String email, String password) {
        return userRepository.existsByUser(email, password);
    }

    public User getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public Long pay(String userEmail, Long totalPrice) {
        User user = getUserByEmail(userEmail);
        Long totalAccount = user.getAccount();
        Long afterAccount = totalAccount - totalPrice;

        if(afterAccount < 0){
            return null;
        }

        user.setAccount(afterAccount);

        return afterAccount;
    }


}
