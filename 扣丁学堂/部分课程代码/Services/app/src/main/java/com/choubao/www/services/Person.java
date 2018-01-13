package com.choubao.www.services;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by choubao on 17/4/25.
 */

public class Person implements Parcelable{
    String name;
    String work;

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            Person p=new Person();
            p.name=in.readString();
            p.work=in.readString();
            return p;
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", work='" + work + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(work);
    }
}
