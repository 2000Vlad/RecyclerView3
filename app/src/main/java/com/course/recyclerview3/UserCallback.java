package com.course.recyclerview3;

public interface UserCallback extends ItemCallback {
    String getUserName();

    void setUserName(String string);

    int getImageResource();

    void setImageResource(int res);

    String getEmail();

    void setEmail(String string);

    String getPhone();

    void setPhone(String string);

}
