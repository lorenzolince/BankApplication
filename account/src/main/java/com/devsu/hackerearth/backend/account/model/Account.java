package com.devsu.hackerearth.backend.account.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.Data;

@Entity
@Data()
@EqualsAndHashCode(callSuper = true)
public class Account extends Base {
    private String number;
    private String type;
    private double initialAmount;
    private boolean active;

    @Column(name = "client_id")
    private Long clientId;
}
