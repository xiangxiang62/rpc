package com.xiang.example.common.model;

import java.io.Serializable;

/**
 * @Author kekai-wurongwei
 * @Date 2024 07 24 19 48
 **/
public class User implements Serializable {

    private String name;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

}
