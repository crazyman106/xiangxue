package com.example.serializable.test;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * author : fenzili
 * e-mail : 291924028@qq.com
 * date   : 2020/5/25 23:06
 * pkn    : com.example.serializable
 * desc   : 使用Parcelable序列化,在android进程中传递对象
 */
public class Demo13 implements Parcelable {
    private String name;
    private int age;


    protected Demo13(Parcel in) {
        name = in.readString();
        age = in.readInt();
    }

    /**
     * 将对象转换成一个 Parcel 对象
     *
     * @param dest  表示要写入的 Parcel 对象
     * @param flags 示这个对象将如何写入
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(age);
    }

    /**
     * 描述当前Parcelable实例的对象类型,
     * 比如说对象中有文件描述符,这个方法就会返回上面的CONTENTS_FILE_DESCRIPTOR
     * 其他情况会返回一个位掩码
     *
     * @return
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * 实现类必须有一个 Creator 属性，用于反序列化，将 Parcel 对象转换为 Parcelable
     *
     * @param <T>
     */
    public static final Creator<Demo13> CREATOR = new Creator<Demo13>() {
        //反序列化的方法，将Parcel还原成Java对象
        @Override
        public Demo13 createFromParcel(Parcel in) {
            return new Demo13(in);
        }

        //提供给外部类反序列化这个数组使用。
        @Override
        public Demo13[] newArray(int size) {
            return new Demo13[size];
        }
    };
}
