package com.choubao.www.activity;

import java.io.Serializable;

/**
 * Created by choubao on 17/3/25.
 */

//表示该类可以序列化
public class Cat implements Serializable{
    String name;
    int age;
    String type;

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", type='" + type + '\'' +
                '}';
    }
}
