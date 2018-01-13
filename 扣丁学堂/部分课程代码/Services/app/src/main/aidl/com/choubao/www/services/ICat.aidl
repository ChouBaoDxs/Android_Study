// ICat.aidl
package com.choubao.www.services;

import com.choubao.www.services.Person;
// Declare any non-default types here with import statements

//icp:内部进程通信
//aidl:内部进程通信接口的描述语言

interface ICat {

    Person getPerson();

    void setName(String name);

    String desc();
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
}
