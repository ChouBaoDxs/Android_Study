package com.choubao.www.activity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by choubao on 17/3/25.
 */

public class Dog implements Parcelable{
    String name;
    int age;
    String type;

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(age);
        parcel.writeString(type);
    }

    //对象的创建器
    public static final Parcelable.Creator<Dog> CREATOR= new Creator<Dog>() {
        @Override
        public Dog createFromParcel(Parcel parcel) {
            Dog dog=new Dog();
            dog.name=parcel.readString();
            dog.age=parcel.readInt();
            dog.type=parcel.readString();
            return dog;
        }

        @Override
        public Dog[] newArray(int i) {
            return new Dog[i];
        }
    };
}
