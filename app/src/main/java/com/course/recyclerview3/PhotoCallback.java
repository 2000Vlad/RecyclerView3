package com.course.recyclerview3;

public interface PhotoCallback extends ItemCallback {
    void setImageResource(int res);

    int getImageResource();

    void setDescription(String string);

    String getDescription();
}
