package com.oklimenko.authserver.model.oauth;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="users", uniqueConstraints = @UniqueConstraint(columnNames = {"userName"}, name="USER_UNIQUE_USERNAME"))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", columnDefinition = "bigint")
    private Integer id;

    @Column(length=50)
    private String userName;

    @Column
    private String password;

    @Column
    private Boolean accountExpired;

    @Column
    private Boolean accountLocked;

    @Column
    private Boolean credentialsExpired;

    @Column
    private Boolean enabled;

    @OneToMany(mappedBy = "user", targetEntity = UserAuthority.class, cascade = {
            CascadeType.ALL }, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<UserAuthority> userAuthorities = new HashSet<UserAuthority>();

}