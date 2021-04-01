package com.dy.baeminclone.rest;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class RequestPayment {
    private String email;

    private Long totalPrice;

    private List<Object> menuList;

}
