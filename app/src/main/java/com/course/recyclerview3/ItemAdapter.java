package com.course.recyclerview3;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {
    private DataProvider mProvider;
    private UserListener mUserListener;
    private CommentListener mCommentListener;
    private PhotoListener mPhotoListener;

    public ItemAdapter(DataProvider provider,
                       UserListener userListener,
                       CommentListener commentListener,
                       PhotoListener photoListener) {
        mProvider = provider;
        mUserListener = userListener;
        mCommentListener = commentListener;
        mPhotoListener = photoListener;
        source = provider.provideData();
    }

    private List<Item> source;

    public List<Item> getSource() {
        return source;
    }

    public void setSource(List<Item> source) {
        this.source = source;
    }

    public UserListener getUserListener() {
        return mUserListener;
    }

    public void setUserListener(UserListener userListener) {
        mUserListener = userListener;
    }

    public CommentListener getCommentListener() {
        return mCommentListener;
    }

    public void setCommentListener(CommentListener commentListener) {
        mCommentListener = commentListener;
    }

    public PhotoListener getPhotoListener() {
        return mPhotoListener;
    }

    public void setPhotoListener(PhotoListener photoListener) {
        mPhotoListener = photoListener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View item = null;
        if (i == User.USER_FLAG) {
            item = inflater.inflate(R.layout.user_item, viewGroup, false);
            return new UserViewHolder(item);
        }
        if (i == Comment.COMMENT_FLAG) {
            item = inflater.inflate(R.layout.comment_item, viewGroup, false);
            return new CommentViewHolder(item);
        }
        if (i == Photo.PHOTO_FLAG) {
            item = inflater.inflate(R.layout.photo_item, viewGroup, false);
            return new PhotoViewHolder(item);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int i) {
        Item currentItem = source.get(i);
        if (itemViewHolder instanceof UserViewHolder) {
            User item = (User) currentItem;
            ((UserViewHolder) itemViewHolder).setImageResource(item.getImageResource());
            ((UserViewHolder) itemViewHolder).setUserName(item.getName());
            ((UserViewHolder) itemViewHolder).setEmail(item.getEmail());
            ((UserViewHolder) itemViewHolder).setPhone(item.getPhone());
            ((UserViewHolder) itemViewHolder).setUserListener(mUserListener);
            ((UserViewHolder) itemViewHolder).setIndex(i);
        }
        if (itemViewHolder instanceof CommentViewHolder) {
            Comment item = (Comment) currentItem;
            ((CommentViewHolder) itemViewHolder).setText(item.getText());
            ((CommentViewHolder) itemViewHolder).setUserName(item.getUserName());
            ((CommentViewHolder) itemViewHolder).setCommentListener(mCommentListener);
            ((CommentViewHolder) itemViewHolder).setIndex(i);
        }
        if (itemViewHolder instanceof PhotoViewHolder) {
            Photo item = (Photo) currentItem;
            ((PhotoViewHolder) itemViewHolder).setImageResource(item.getImageResource());
            ((PhotoViewHolder) itemViewHolder).setDescription(item.getDescription());
            ((PhotoViewHolder) itemViewHolder).setPhotoListener(mPhotoListener);
            ((PhotoViewHolder) itemViewHolder).setIndex(i);
        }

    }

    @Override
    public int getItemCount() {
        return source.size();
    }

    @Override
    public int getItemViewType(int position) {
        Item item = source.get(position);
        if (item instanceof User) return User.USER_FLAG;
        if (item instanceof Comment) return Comment.COMMENT_FLAG;
        if (item instanceof Photo) return Photo.PHOTO_FLAG;
        return 0;
    }
}
