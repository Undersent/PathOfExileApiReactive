package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.Objects;

//@Data
//@Getter
//@Setter
//@AllArgsConstructor
public class Account {

    @Id
    private String id;

    private String name;

    private int age;

    public Account(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return getAge() == account.getAge() &&
                Objects.equals(getId(), account.getId()) &&
                Objects.equals(getName(), account.getName());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getName(), getAge());
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
