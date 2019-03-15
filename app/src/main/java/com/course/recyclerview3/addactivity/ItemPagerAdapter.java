package com.course.recyclerview3.addactivity;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ItemPagerAdapter extends FragmentPagerAdapter {
    public ItemPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    private AddUserFragment.AddUserListener mUserListener;
    private AddCommentFragment.AddCommentListener mCommentListener;
    private AddPhotoFragment.AddPhotoListener mPhotoListener;

    public AddUserFragment.AddUserListener getUserListener() {
        return mUserListener;
    }

    public void setUserListener(AddUserFragment.AddUserListener userListener) {
        mUserListener = userListener;
    }

    public AddCommentFragment.AddCommentListener getCommentListener() {
        return mCommentListener;
    }

    public void setCommentListener(AddCommentFragment.AddCommentListener commentListener) {
        mCommentListener = commentListener;
    }

    public AddPhotoFragment.AddPhotoListener getPhotoListener() {
        return mPhotoListener;
    }

    public void setPhotoListener(AddPhotoFragment.AddPhotoListener photoListener) {
        mPhotoListener = photoListener;
    }

    @Override
    public Fragment getItem(int i) {
        if (i == 0) {
            AddUserFragment fragment = new AddUserFragment();
            fragment.setListener(mUserListener);
            return fragment;
        }
        if (i == 1) {
            AddCommentFragment fragment = new AddCommentFragment();
            fragment.setListener(mCommentListener);
            return fragment;
        }
        if (i == 2) {
            AddPhotoFragment fragment = new AddPhotoFragment();
            fragment.setListener(mPhotoListener);
            return fragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return AddItemActivity.PAGE_COUNT;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Add User";
            case 1:
                return "Add Comment";
            case 2:
                return "Add Photo";
        }
        return super.getPageTitle(position);
    }
}
