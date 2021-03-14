package com.dy.baeminclone.controller;

import com.dy.baeminclone.domain.Store;
import com.dy.baeminclone.domain.User;
import com.dy.baeminclone.rest.JsonResponse;
import com.dy.baeminclone.rest.RequestUser;
import com.dy.baeminclone.service.StoreService;
import com.dy.baeminclone.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final PasswordEncoder encoder;
    private final UserService userService;
    private final StoreService storeService;

    @PostConstruct
    public void init(){
        storeService.loadStores();
    }

    private User getUser(RequestUser requestUser) {
        return User.builder()
                .username(requestUser.getUsername())
                .password(requestUser.getPassword())
                .email(requestUser.getEmail())
                .birth(requestUser.getBirth())
                .tel(requestUser.getTel())
                .nickname(requestUser.getNickname())
                .build();
    }

    @GetMapping("/encode-test")
    public String test(){
        return "test : " + encoder.encode("test");
    }

    @PostMapping("/users/signup")
    public JsonResponse signUp(@RequestBody RequestUser requestUser){
        JsonResponse response = new JsonResponse();

        User user = getUser(requestUser);
        try {
            response.setResult(userService.signUp(user) != null);
        } catch (UnexpectedRollbackException e) {
            response.getContent().put("reason", "error");
            logger.warn("Rollback");
        }

        return response;
    }

    @PostMapping("/users/signin")
    public JsonResponse signIn(@RequestBody RequestUser requestUser){
        JsonResponse response = new JsonResponse();
        User user = userService.getUserByEmail(requestUser.getEmail());

        if(user == null){
            response.setResult(false);
            return response;
        }

        boolean result;

        result = userService.signIn(user);

        if(result){
            response.getContent().put("username", user.getUsername());
            logger.info(user.toString());
        }

        response.setResult(result);

        return response;
    }

    @GetMapping("/stores/{category}")
    public JsonResponse getStore(@PathVariable String category){
        JsonResponse response = new JsonResponse();
        List<Store> storeList = storeService.getStoreListByCategory(category);

        if(storeList == null){
            response.setResult(false);
            return response;
        }

        response.setResult(true);
        response.getContent().put("stores", storeList);

        return response;
    }

}
