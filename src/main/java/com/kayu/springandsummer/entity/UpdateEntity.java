package com.kayu.springandsummer.entity;

import java.util.Objects;

import org.springframework.lang.NonNull;

public class UpdateEntity {
    
    @NonNull
    private String name;
    private int balance;

    public UpdateEntity() {
    }

    public UpdateEntity(String name, int balance) {
        this.name = name;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateEntity that = (UpdateEntity) o;
        return balance == that.balance && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, balance);
    }

    @Override
    public String toString() {
        return "UpdateEntity{" +
                "name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }
}
