package com.course.recyclerview3.addactivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.course.recyclerview3.MainActivity;
import com.course.recyclerview3.R;
import com.course.recyclerview3.UserListener;

import java.nio.Buffer;

import static com.course.recyclerview3.Comment.COMMENT_FLAG;
import static com.course.recyclerview3.MainActivity.ITEM_TYPE_KEY;
import static com.course.recyclerview3.MainActivity.OPERATION_KEY;

import static com.course.recyclerview3.MainActivity.POSITION_KEY;
import static com.course.recyclerview3.Photo.PHOTO_FLAG;
import static com.course.recyclerview3.User.USER_FLAG;
import static com.course.recyclerview3.addactivity.AddCommentFragment.COMMENT_TEXT_KEY;
import static com.course.recyclerview3.addactivity.AddCommentFragment.COMMENT_USERNAME_KEY;
import static com.course.recyclerview3.addactivity.AddPhotoFragment.PHOTO_DESCRIPTION_KEY;
import static com.course.recyclerview3.addactivity.AddPhotoFragment.PHOTO_RESOURCE_KEY;
import static com.course.recyclerview3.addactivity.AddUserFragment.USER_AVATAR_KEY;
import static com.course.recyclerview3.addactivity.AddUserFragment.USER_EMAIL_KEY;
import static com.course.recyclerview3.addactivity.AddUserFragment.USER_NAME_KEY;
import static com.course.recyclerview3.addactivity.AddUserFragment.USER_PHONE_KEY;

public class AddItemActivity extends AppCompatActivity implements AddUserFragment.AddUserListener, AddCommentFragment.AddCommentListener, AddPhotoFragment.AddPhotoListener {

    public static final int PAGE_COUNT = 3;
    public static final String ITEM_COUNT_KEY = "itemCount";

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ItemPagerAdapter mPagerAdapter;


    private int mCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        mTabLayout = findViewById(R.id.tab_layout);
        mViewPager = findViewById(R.id.view_pager);
        ItemPagerAdapter adapter = new ItemPagerAdapter(getSupportFragmentManager());
        mPagerAdapter = adapter;
        mPagerAdapter.setUserListener(this);
        mPagerAdapter.setCommentListener(this);
        mPagerAdapter.setPhotoListener(this);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mCount = getIntent().getExtras().getInt(ITEM_COUNT_KEY);


    }

    @Override
    public void receiveUserData(String userName, String email, String phone, int avatarResource, int position) {
        Intent dataIntent = new Intent(AddItemActivity.this, MainActivity.class);
        Bundle bundle = new Bundle();

        bundle.putInt(ITEM_TYPE_KEY, USER_FLAG);
        bundle.putString(USER_NAME_KEY, userName);
        bundle.putString(USER_EMAIL_KEY, email);
        bundle.putString(USER_PHONE_KEY, phone);
        bundle.putInt(USER_AVATAR_KEY, avatarResource);
        bundle.putInt(POSITION_KEY, position);

        dataIntent.putExtras(bundle);
        setResult(RESULT_OK, dataIntent);
        finish();
    }

    @Override
    public void receiveCommentData(String userName, String commentText, int position) {
        Intent dataIntent = new Intent(AddItemActivity.this, MainActivity.class);
        Bundle bundle = new Bundle();

        bundle.putInt(ITEM_TYPE_KEY, COMMENT_FLAG);
        bundle.putString(COMMENT_USERNAME_KEY, userName);
        bundle.putString(COMMENT_TEXT_KEY, commentText);
        bundle.putInt(POSITION_KEY, position);

        dataIntent.putExtras(bundle);
        setResult(RESULT_OK, dataIntent);
        finish();

    }

    @Override
    public void receivePhotoData(int imageResource, String description, int position) {
        Intent dataIntent = new Intent(AddItemActivity.this, MainActivity.class);
        Bundle bundle = new Bundle();

        bundle.putInt(ITEM_TYPE_KEY, PHOTO_FLAG);
        bundle.putInt(PHOTO_RESOURCE_KEY, imageResource);
        bundle.putString(PHOTO_DESCRIPTION_KEY, description);
        bundle.putInt(POSITION_KEY, position);

        dataIntent.putExtras(bundle);
        setResult(RESULT_OK, dataIntent);
        finish();

    }

    @Override
    public int getItemCount() {
        return mCount;
    }


}
