package com.choubao.www.services;

import android.os.RemoteException;

/**
 * Created by choubao on 17/4/25.
 *
 * descreption:业务接口的具体实现类
 *
 */

public class CatImpl extends ICat.Stub {

    private String name;

    @Override
    public Person getPerson() throws RemoteException {
        Person p=new Person();
        p.name="小次郎";
        p.work="火箭队";
        return p;
    }

    @Override
    public void setName(String name) throws RemoteException {
        this.name=name;
    }

    @Override
    public String desc() throws RemoteException {
        return "hello my name is "+name+", I am a cat.";
    }

    @Override
    public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

    }
}
