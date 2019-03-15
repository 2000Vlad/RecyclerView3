package com.course.recyclerview3;

import android.widget.ImageView;
import android.widget.TextView;

public interface UserListener {
    void onUserAvatarClicked(ImageView avatar, UserCallback callback);

    void onUserNameClicked(TextView userName, UserCallback callback);

    void onUserEmailClicked(TextView email, UserCallback callback);

    void onUserPhoneClicked(TextView phone, UserCallback callback);
}
