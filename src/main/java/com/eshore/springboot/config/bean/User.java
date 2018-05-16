package com.eshore.springboot.config.bean;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Blaze on 2018/5/16.
 */
@Entity
@Table(name = "tb_user")
public class User implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "user_name", length = 50)
    private String userName;

    @Column(name = "age")
    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
