package com.choubao.www.httpurlconnection_51;

import java.util.ArrayList;

/**
 * Created by choubao on 17/5/6.
 */

public class JsonObject {

    private String login;
    private ArrayList<User> user=new ArrayList<>();

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public ArrayList<User> getUser() {
        return user;
    }

    public void setUser(ArrayList<User> user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "JsonObject [login="+login+", user="+user+"]";
    }
}
