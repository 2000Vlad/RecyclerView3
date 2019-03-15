package com.course.recyclerview3;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class UserViewHolder extends ItemViewHolder implements UserCallback {
    private UserListener mUserListener;
    private ImageView mAvatarImageView;
    private TextView mUserNameTextView;
    private TextView mEmailTextView;
    private TextView mPhoneTextView;
    private int mImageResource;
    private int mIndex;

    @Override
    public int getIndex() {
        return mIndex;
    }

    @Override
    public int getImageResource() {
        return mImageResource;
    }

    @Override
    public void setImageResource(int imageResource) {
        mImageResource = imageResource;
        mAvatarImageView.setImageResource(imageResource);

    }

    @Override
    public String getUserName() {
        return mUserNameTextView.getText().toString();
    }

    @Override
    public void setUserName(String string) {
        mUserNameTextView.setText(string);
    }

    @Override
    public String getEmail() {
        return mEmailTextView.getText().toString();
    }

    @Override
    public void setEmail(String string) {
        mEmailTextView.setText(string);
    }

    @Override
    public String getPhone() {
        return mPhoneTextView.getText().toString();
    }

    @Override
    public void setPhone(String string) {
        mPhoneTextView.setText(string);
    }

    public void setIndex(int index) {
        mIndex = index;
    }

    public UserListener getUserListener() {
        return mUserListener;
    }

    public void setUserListener(UserListener userListener) {
        mUserListener = userListener;
    }

    public ImageView getAvatarImageView() {
        return mAvatarImageView;
    }

    public void setAvatarImageView(ImageView avatarImageView) {
        mAvatarImageView = avatarImageView;
    }

    public TextView getUserNameTextView() {
        return mUserNameTextView;
    }

    public void setUserNameTextView(TextView userNameTextView) {
        mUserNameTextView = userNameTextView;
    }

    public TextView getEmailTextView() {
        return mEmailTextView;
    }

    public void setEmailTextView(TextView emailTextView) {
        mEmailTextView = emailTextView;
    }

    public TextView getPhoneTextView() {
        return mPhoneTextView;
    }

    public void setPhoneTextView(TextView phoneTextView) {
        mPhoneTextView = phoneTextView;
    }

    private void setHandlers() {
        mAvatarImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserListener.onUserAvatarClicked(mAvatarImageView, UserViewHolder.this);
            }
        });
        mPhoneTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserListener.onUserPhoneClicked(mPhoneTextView, UserViewHolder.this);
            }
        });
        mEmailTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserListener.onUserEmailClicked(mEmailTextView, UserViewHolder.this);
            }
        });
        mUserNameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserListener.onUserNameClicked(mUserNameTextView, UserViewHolder.this);
            }
        });
    }

    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
        mAvatarImageView = itemView.findViewById(R.id.image_user_avatar);
        mUserNameTextView = itemView.findViewById(R.id.text_user_name);
        mEmailTextView = itemView.findViewById(R.id.text_user_email);
        mPhoneTextView = itemView.findViewById(R.id.text_user_phone);
        setHandlers();
    }
}
