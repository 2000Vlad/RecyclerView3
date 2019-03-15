package com.course.recyclerview3;

public interface CommentCallback extends ItemCallback {
    String getUserName();

    void setUserName(String string);

    String getText();

    void setText(String string);


}
