package com.course.recyclerview3;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

public class CommentViewHolder extends ItemViewHolder implements CommentCallback {
    private CommentListener mCommentListener;
    private TextView mUserNameTextView;
    private TextView mContentTextView;
    private int mIndex;

    public CommentViewHolder(@NonNull View itemView) {
        super(itemView);
        mUserNameTextView = itemView.findViewById(R.id.text_comment_user_name);
        mContentTextView = itemView.findViewById(R.id.text_comment);
        setHandlers();
    }

    private void setHandlers() {
        mUserNameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCommentListener.onUserNameClicked(mUserNameTextView, CommentViewHolder.this);
            }
        });
        mContentTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCommentListener.onCommentTextClicked(mContentTextView, CommentViewHolder.this);
            }
        });
    }

    public void setIndex(int index) {
        mIndex = index;
    }

    @Override
    public int getIndex() {
        return mIndex;
    }

    @Override
    public String getText() {
        return mContentTextView.getText().toString();
    }

    @Override
    public void setText(String string) {
        mContentTextView.setText(string);
    }

    @Override
    public String getUserName() {
        return mUserNameTextView.getText().toString();
    }

    @Override
    public void setUserName(String string) {
        mUserNameTextView.setText(string);
    }

    public CommentListener getCommentListener() {
        return mCommentListener;
    }

    public void setCommentListener(CommentListener commentListener) {
        mCommentListener = commentListener;
    }

    public TextView getUserNameTextView() {
        return mUserNameTextView;
    }

    public void setUserNameTextView(TextView userNameTextView) {
        mUserNameTextView = userNameTextView;
    }

    public TextView getContentTextView() {
        return mContentTextView;
    }

    public void setContentTextView(TextView contentTextView) {
        mContentTextView = contentTextView;
    }


}
