package com.course.recyclerview3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class User extends Item {
    public static final int USER_FLAG = 1;
    private String mName;
    private String mPhone;
    private String mEmail;
    private int mImageResource;

    public User(DataInputStream stream) {
        super(stream);
        try {
            mName = stream.readUTF();
            mEmail = stream.readUTF();
            mPhone = stream.readUTF();
            mImageResource = stream.readInt();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public User() {

    }

    @Override
    public void serialize(DataOutputStream storage) {
        try {
            storage.writeInt(USER_FLAG);
            storage.writeUTF(mName);
            storage.writeUTF(mEmail);
            storage.writeUTF(mPhone);
            storage.writeInt(mImageResource);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        mPhone = phone;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public int getImageResource() {
        return mImageResource;
    }

    public void setImageResource(int resource) {
        mImageResource = resource;
    }

}
