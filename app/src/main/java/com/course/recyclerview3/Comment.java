package com.course.recyclerview3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Comment extends Item {
    public static final int COMMENT_FLAG = 2;
    private String mUserName;
    private String mText;

    public Comment(DataInputStream stream) {
        super(stream);
        try {
            mUserName = stream.readUTF();
            mText = stream.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Comment() {

    }

    @Override
    public void serialize(DataOutputStream storage) {
        try {
            storage.writeInt(COMMENT_FLAG);
            storage.writeUTF(mUserName);
            storage.writeUTF(mText);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }
}
