package com.course.recyclerview3;

import android.widget.ImageView;
import android.widget.TextView;

public interface PhotoListener {
    void onImageClicked(ImageView imageView, PhotoCallback callback);

    void onDescriptionClicked(TextView descriptionText, PhotoCallback callback);
}
