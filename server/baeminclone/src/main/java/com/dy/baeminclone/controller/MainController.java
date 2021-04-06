package com.dy.baeminclone.controller;

import com.dy.baeminclone.domain.Menu;
import com.dy.baeminclone.domain.Store;
import com.dy.baeminclone.domain.User;
import com.dy.baeminclone.rest.JsonResponse;
import com.dy.baeminclone.rest.RequestPayment;
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
    public void init() {
        userService.loadUsers();
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
    public String test() {
        return "test : " + encoder.encode("test");
    }

    @PostMapping("/users/signup")
    public JsonResponse signUp(@RequestBody RequestUser requestUser) {
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
    public JsonResponse signIn(@RequestBody RequestUser requestUser) {
        JsonResponse response = new JsonResponse();
        User user;
        boolean result;

        result = userService.signIn(requestUser.getEmail(), requestUser.getPassword());

        response.setResult(result);

        if (result) {
            user = userService.getUserByEmail(requestUser.getEmail());
            response.getContent().put("username", user.getUsername());
        }

        response.setResult(result);

        return response;
    }

    @GetMapping("/stores")
    public JsonResponse getStores() {
        JsonResponse response = new JsonResponse();
        List<Store> storeList = storeService.getAll();

        if (storeList == null) {
            response.setResult(false);
            return response;
        }

        response.setResult(true);
        response.getContent().put("stores", storeList);

        return response;
    }

    @GetMapping("/stores/{category}")
    public JsonResponse getStore(@PathVariable String category) {
        JsonResponse response = new JsonResponse();
        List<Store> storeList = storeService.getStoreListByCategory(category);

        if (storeList == null) {
            response.setResult(false);
            return response;
        }

        response.setResult(true);
        response.getContent().put("stores", storeList);

        return response;
    }

    @GetMapping("/menus/{storeId}")
    public JsonResponse getMenus(@PathVariable Long storeId) {
        JsonResponse response = new JsonResponse();
        List<Menu> menuList = storeService.getMenuListByStoreId(storeId);

        if (menuList == null) {
            response.setResult(false);
            return response;
        }

        response.setResult(true);

        response.getContent().put("menus", menuList);

        return response;
    }

    @PostMapping("/payment")
    public JsonResponse pay(@RequestBody RequestPayment requestPayment) {
        JsonResponse response = new JsonResponse();
        Long result = userService.pay(requestPayment.getEmail(), requestPayment.getTotalPrice());

        if (null == result) {
            response.setResult(false);
            return response;
        }

        response.setResult(true);
        response.getContent().put("account", result);

        return response;
    }

}

