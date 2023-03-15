package com.timedeal_server.timedeal.domain.user.domain;

import lombok.Getter;

import javax.persistence.Embeddable;


@Embeddable
@Getter
public class Address {
    private String address1;
    private String address2;
    private String zipCode;

}
