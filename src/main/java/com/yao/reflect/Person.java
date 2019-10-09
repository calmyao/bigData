package com.yao.reflect;

import java.io.Serializable;

/**
 * Created by Calm on 2018/11/21
 */
public class Person implements Serializable,TestInterface{
    private Long id;
    public String name;
    public Person(){
        this.id = 100L;
        this.name = "afsdfasd";
    }

    public Person(Long id,String name){
        this.id = id;
        this.name = name;
    }

    public Person(Long id){
        super();
        this.id = id;
    }

    private Person(String name){
        super();
        this.name = name + "=====";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    private String getSometing(){
        return "private getSomething";
    }

    private void testPrivate(){
        System.out.println("this is a private method");
    }
}
