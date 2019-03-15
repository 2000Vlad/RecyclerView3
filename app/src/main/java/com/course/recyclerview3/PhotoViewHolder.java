package com.course.recyclerview3;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class PhotoViewHolder extends ItemViewHolder implements PhotoCallback {
    private PhotoListener mPhotoListener;
    private int mIndex;
    private int mImageResource;
    private ImageView mImageView;
    private TextView mDescriptionTextView;

    public PhotoViewHolder(@NonNull View itemView) {
        super(itemView);
        mImageView = itemView.findViewById(R.id.image_photo);
        mDescriptionTextView = itemView.findViewById(R.id.text_photo_description);
        setHandlers();
    }

    private void setHandlers() {
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPhotoListener.onImageClicked(mImageView, PhotoViewHolder.this);
            }
        });
        mDescriptionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPhotoListener.onDescriptionClicked(mDescriptionTextView, PhotoViewHolder.this);
            }
        });
    }


    @Override
    public int getImageResource() {
        return mImageResource;
    }

    @Override
    public void setImageResource(int imageResource) {
        mImageResource = imageResource;
        mImageView.setImageResource(imageResource);
    }

    @Override
    public int getIndex() {
        return mIndex;
    }

    @Override
    public void setDescription(String string) {
        mDescriptionTextView.setText(string);
    }

    @Override
    public String getDescription() {
        return mDescriptionTextView.getText().toString();
    }

    public PhotoListener getPhotoListener() {
        return mPhotoListener;
    }

    public void setPhotoListener(PhotoListener photoListener) {
        mPhotoListener = photoListener;
    }

    public void setIndex(int index) {
        mIndex = index;
    }

    public ImageView getImageView() {
        return mImageView;
    }

    public void setImageView(ImageView imageView) {
        mImageView = imageView;
    }

    public TextView getDescriptionTextView() {
        return mDescriptionTextView;
    }

    public void setDescriptionTextView(TextView descriptionTextView) {
        mDescriptionTextView = descriptionTextView;
    }


}
