package com.course.recyclerview3;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.course.recyclerview3.addactivity.AddItemActivity;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.course.recyclerview3.Comment.COMMENT_FLAG;
import static com.course.recyclerview3.Photo.PHOTO_FLAG;
import static com.course.recyclerview3.User.USER_FLAG;
import static com.course.recyclerview3.addactivity.AddCommentFragment.COMMENT_TEXT_KEY;
import static com.course.recyclerview3.addactivity.AddCommentFragment.COMMENT_USERNAME_KEY;
import static com.course.recyclerview3.addactivity.AddItemActivity.ITEM_COUNT_KEY;
import static com.course.recyclerview3.addactivity.AddPhotoFragment.PHOTO_DESCRIPTION_KEY;
import static com.course.recyclerview3.addactivity.AddPhotoFragment.PHOTO_RESOURCE_KEY;
import static com.course.recyclerview3.addactivity.AddUserFragment.USER_AVATAR_KEY;
import static com.course.recyclerview3.addactivity.AddUserFragment.USER_EMAIL_KEY;
import static com.course.recyclerview3.addactivity.AddUserFragment.USER_NAME_KEY;
import static com.course.recyclerview3.addactivity.AddUserFragment.USER_PHONE_KEY;

public class MainActivity extends AppCompatActivity implements
        DataProvider,
        UserListener,
        CommentListener,
        PhotoListener {
    public static final String OPERATION_KEY = "operation";
    public static final int DEFAULT_ITEM_COUNT = 5;
    public static final String FILE_NAME = "data";
    public static final boolean DEBUG = true;
    public static final String TEMP = "temp";
    public static final String ITEM_TYPE_KEY = "itemType";
    public static final int ADD_REQUEST_CODE = 100;
    public static final String POSITION_KEY = "position";
    private RecyclerView mRecyclerView;
    private ItemAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (DEBUG) {
            if (getFileStreamPath(FILE_NAME) != null && getFileStreamPath(FILE_NAME).exists())
                getFileStreamPath(FILE_NAME).delete();
            if (getFileStreamPath(TEMP) != null && getFileStreamPath(TEMP).exists())
                getFileStreamPath(TEMP).delete();
            seed();
        }
        initView();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) return;
        if (data == null) return;
        Bundle bundle = data.getExtras();
        Item item = null;
        int position = -1;
        int itemType = bundle.getInt(ITEM_TYPE_KEY);
        if (itemType == USER_FLAG) {
            String userName = bundle.getString(USER_NAME_KEY);
            String email = bundle.getString(USER_EMAIL_KEY);
            String phone = bundle.getString(USER_PHONE_KEY);
            int avatarResource = bundle.getInt(USER_AVATAR_KEY);
            User user = new User();
            user.setName(userName);
            user.setEmail(email);
            user.setPhone(phone);
            user.setImageResource(avatarResource);
            item = user;

        }
        if (itemType == COMMENT_FLAG) {
            String userName = bundle.getString(COMMENT_USERNAME_KEY);
            String commentText = bundle.getString(COMMENT_TEXT_KEY);
            Comment comment = new Comment();
            comment.setUserName(userName);
            comment.setText(commentText);
            item = comment;
        }
        if (itemType == PHOTO_FLAG) {
            String description = bundle.getString(PHOTO_DESCRIPTION_KEY);
            int imageResource = bundle.getInt(PHOTO_RESOURCE_KEY);
            Photo photo = new Photo();
            photo.setDescription(description);
            photo.setImageResource(imageResource);
            item = photo;
        }
        position = bundle.getInt(POSITION_KEY);
        insertItem(item, position - 1);
        mAdapter.setSource(provideData());
        mAdapter.notifyItemInserted(position - 1);

    }

    private void initView() {
        mRecyclerView = findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        ItemAdapter adapter = new ItemAdapter(this, this, this, this);
        mRecyclerView.setAdapter(adapter);
        mAdapter = adapter;
    }

    @Override
    public void onUserNameClicked(TextView userNameTextView, CommentCallback callback) {
        Toast.makeText(this, "Comment-User Name clicked " + (callback.getIndex() + 1), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCommentTextClicked(TextView commentTextView, CommentCallback callback) {
        Toast.makeText(this, "Comment-Textclicked " + callback.getIndex() + 1, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onImageClicked(ImageView imageView, PhotoCallback callback) {
        Toast.makeText(this, "Photo-Image clicked " + (callback.getIndex() + 1), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDescriptionClicked(TextView descriptionText, PhotoCallback callback) {
        Toast.makeText(this, "Photo-Description clicked " + (callback.getIndex() + 1), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUserAvatarClicked(ImageView avatar, UserCallback callback) {
        Toast.makeText(this, "User-Avatar clicked " + (callback.getIndex() + 1), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUserNameClicked(TextView userName, UserCallback callback) {
        Toast.makeText(this, "User-User Name clicked " + (callback.getIndex() + 1), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUserEmailClicked(TextView email, UserCallback callback) {
        Toast.makeText(this, "User-Email clicked " + (callback.getIndex() + 1), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUserPhoneClicked(TextView phone, UserCallback callback) {
        Toast.makeText(this, "User-Phone clicked " + (callback.getIndex() + 1), Toast.LENGTH_LONG).show();
    }

    @Override
    public List<Item> provideData() {
        ArrayList<Item> items = new ArrayList<>();
        try {
            DataInputStream stream = new DataInputStream(openFileInput(FILE_NAME));
            int count = stream.readInt();
            for (int i = 0; i < count; i++) {
                int itemType = stream.readInt();
                switch (itemType) {
                    case USER_FLAG:
                        items.add(new User(stream));
                        break;
                    case COMMENT_FLAG:
                        items.add(new Comment(stream));
                        break;
                    case PHOTO_FLAG:
                        items.add(new Photo(stream));
                        break;
                }
            }
            stream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return items;
        }

    }

    private void insertItem(Item item, int i) {
        try {
            DataInputStream data = new DataInputStream(openFileInput(FILE_NAME));
            DataOutputStream temp = new DataOutputStream(openFileOutput(TEMP, MODE_PRIVATE));
            int count = data.readInt();
            temp.writeInt(count + 1);
            for (int j = 0; j < i; j++) {
                int itemType = data.readInt();
                Item current = null;
                switch (itemType) {
                    case USER_FLAG:
                        current = new User(data);
                        break;
                    case COMMENT_FLAG:
                        current = new Comment(data);
                        break;
                    case PHOTO_FLAG:
                        current = new Photo(data);
                        break;

                }
                current.serialize(temp);
            }
            item.serialize(temp);
            for (int j = i; j < count; j++) {
                int itemType = data.readInt();
                Item current = null;
                switch (itemType) {
                    case USER_FLAG:
                        current = new User(data);
                        break;
                    case COMMENT_FLAG:
                        current = new Comment(data);
                        break;
                    case PHOTO_FLAG:
                        current = new Photo(data);
                        break;
                }
                current.serialize(temp);
            }
            data.close();
            temp.close();
            File oldFile = getFileStreamPath(FILE_NAME);
            File newFile = getFileStreamPath(TEMP);
            boolean renamed;
            renamed = oldFile.delete();
            if (!renamed) throw new IllegalStateException("Nu s-a sters data cum trebuie");
            renamed = newFile.renameTo(oldFile);
            if (!renamed)
                throw new IllegalStateException("Nu s-a redenumit temp catre data cum trebuie");
        } catch (FileNotFoundException e) {
            Log.e("insertItem", Log.getStackTraceString(e));
        } catch (IOException e) {
            Log.e("insertItem", Log.getStackTraceString(e));
        }
    }

    public void appendItem(Item item) {
        try {
            DataInputStream data = new DataInputStream(openFileInput(FILE_NAME));
            DataOutputStream temp = new DataOutputStream(openFileOutput(TEMP, MODE_PRIVATE));
            int count = data.readInt();
            temp.writeInt(count + 1);
            int itemType;
            Item currentItem = null;
            for (int i = 0; i < count; i++) {
                itemType = data.readInt();
                switch (itemType) {
                    case USER_FLAG:
                        currentItem = new User(data);
                        break;
                    case PHOTO_FLAG:
                        currentItem = new Photo(data);
                        break;
                    case COMMENT_FLAG:
                        currentItem = new Comment(data);
                        break;
                }
                currentItem.serialize(temp);
            }
            item.serialize(temp);
            data.close();
            temp.close();
            File oldFile = getFileStreamPath(FILE_NAME);
            File newFile = getFileStreamPath(TEMP);
            oldFile.delete();
            newFile.renameTo(oldFile);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void seed() {
        try {
            DataOutputStream stream = new DataOutputStream(openFileOutput(FILE_NAME, MODE_PRIVATE));
            stream.writeInt(DEFAULT_ITEM_COUNT);
            seedUser(
                    "Vlad Iancu",
                    "iv2000ro@gmail.com",
                    "0721711423",
                    R.drawable.ic_user1,
                    stream
            );
            seedComment(
                    "Vlad Iancu",
                    "Acesta este un RecyclerView cu articole multiple\nsi adaugare dinamica de elemente",
                    stream
            );
            seedUser(
                    "Avramita Florina",
                    "nu.stiu@gmail.com",
                    "0771145006",
                    R.drawable.ic_user1,
                    stream
            );
            seedPhoto(
                    R.drawable.voyage_2,
                    "Aceasta este o poza din vacanta",
                    stream
            );
            seedPhoto(
                    R.drawable.voyage_1,
                    "Alta poza",
                    stream
            );
            stream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void seedUser(String name, String email, String phone, int avatarResource, DataOutputStream stream) {
        try {
            stream.writeInt(USER_FLAG);
            stream.writeUTF(name);
            stream.writeUTF(email);
            stream.writeUTF(phone);
            stream.writeInt(avatarResource);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void seedComment(String userName, String text, DataOutputStream stream) {
        try {
            stream.writeInt(COMMENT_FLAG);
            stream.writeUTF(userName);
            stream.writeUTF(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void seedPhoto(int imageResource, String description, DataOutputStream stream) {

        try {
            stream.writeInt(PHOTO_FLAG);
            stream.writeInt(imageResource);
            stream.writeUTF(description);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void navigateAddItem(View view) {
        Intent intent = new Intent(MainActivity.this, AddItemActivity.class);
        intent.putExtra(ITEM_COUNT_KEY, mAdapter.getSource().size());
        startActivityForResult(intent, ADD_REQUEST_CODE);
    }
}
