package com.oklimenko.authserver.model.oauth;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Getter
@Setter
@Entity
@Table(name="authority", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}, name="AUTHORITY_UNIQUE_NAME"))
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", columnDefinition = "bigint")
    private Integer id;

    @Column(length=20)
    private String name;


}