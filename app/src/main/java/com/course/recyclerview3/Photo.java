package com.course.recyclerview3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Photo extends Item {
    public static final int PHOTO_FLAG = 3;
    private int mImageResource;
    private String mDescription;

    public Photo(DataInputStream stream) {
        super(stream);
        try {
            mImageResource = stream.readInt();
            mDescription = stream.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Photo() {

    }

    @Override
    public void serialize(DataOutputStream storage) {
        try {
            storage.writeInt(PHOTO_FLAG);
            storage.writeInt(mImageResource);
            storage.writeUTF(mDescription);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getImageResource() {
        return mImageResource;
    }

    public void setImageResource(int imageResource) {
        mImageResource = imageResource;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }
}
