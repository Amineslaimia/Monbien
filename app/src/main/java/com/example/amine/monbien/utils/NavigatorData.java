package com.example.amine.monbien.utils;

import com.example.amine.monbien.entities.User;

public class NavigatorData {
    private final static NavigatorData instance = new NavigatorData();
    public static NavigatorData getInstance() {
        return instance;
    }
    private boolean update = false;
    private boolean god = false;
    private User user;
    private int a=-1;

    public boolean isGod() {
        return god;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public void setGod(boolean god) {
        this.god = god;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
