package com.course.recyclerview3;

import android.widget.TextView;

public interface CommentListener {
    void onUserNameClicked(TextView userNameTextView, CommentCallback callback);

    void onCommentTextClicked(TextView commentTextView, CommentCallback callback);
}
