package com.devsu.hackerearth.backend.client.model;

import javax.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.Data;

@Entity
@Data()
@EqualsAndHashCode(callSuper = true)
public class Client extends Person {

	private String password;
	private boolean active;

}
