package com.holl.wechat.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "users")
public class User {

    @Id
    @Column(nullable = false)
    private String id;

    @Column(nullable = false)
    private String unionId;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Long credit;

    public User(){ }

    public User(String id, String unionId, String name, Long credit) {
        this.id = id;
        this.unionId = unionId;
        this.name = name;
        this.credit = credit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCredit() {
        return credit;
    }

    public void setCredit(Long credit) {
        this.credit = credit;
    }
}
