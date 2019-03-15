package com.course.recyclerview3.addactivity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.course.recyclerview3.R;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


public class AddPhotoFragment extends Fragment {
    public static final String PHOTO_RESOURCE_KEY = "photoResource";
    public static final String PHOTO_DESCRIPTION_KEY = "photoDescriptionKey";


    @Getter
    @Setter
    private AddPhotoListener mListener;
    private EditText mDescriptionText;
    private RadioButton mRadioButton1;
    private RadioButton mRadioButton2;
    private RadioButton mRadioButton3;
    private RadioButton mRadioButton4;
    private RadioButton mRadioButton5;
    private RadioButton mRadioButton6;
    private int mPosition;
    private Button mButton;
    private Spinner mSpinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_photo, container, false);
        initView(view);
        return view;
    }

    public void initView(View view) {
        mDescriptionText = view.findViewById(R.id.edittext_description_add_photo);
        mRadioButton1 = view.findViewById(R.id.radiobutton_voyage_1);
        mRadioButton2 = view.findViewById(R.id.radiobutton_voyage_2);
        mRadioButton3 = view.findViewById(R.id.radiobutton_voyage_3);
        mRadioButton4 = view.findViewById(R.id.radiobutton_voyage_4);
        mRadioButton5 = view.findViewById(R.id.radiobutton_voyage_5);
        mRadioButton6 = view.findViewById(R.id.radiobutton_voyage_6);
        mButton = view.findViewById(R.id.button_add_photo_finish);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAdd(v);
            }
        });
        mSpinner = view.findViewById(R.id.spinner_add_photo);
        mSpinner.setAdapter(getAdapter());
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String pos = (String) parent.getSelectedItem();
                mPosition = Integer.parseInt(pos);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private ArrayAdapter<String> getAdapter() {
        return new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getPositions());
    }

    private ArrayList<String> getPositions() {
        ArrayList<String> result = new ArrayList<>();
        int count = mListener.getItemCount();
        for (Integer i = 1; i <= count + 1; i++)
            result.add(i.toString());
        return result;
    }

    private int getSelectedImage() {
        if (mRadioButton1.isChecked()) return R.drawable.voyage_1;
        if (mRadioButton2.isChecked()) return R.drawable.voyage_2;
        if (mRadioButton3.isChecked()) return R.drawable.voyage_3;
        if (mRadioButton4.isChecked()) return R.drawable.voyage_4;
        if (mRadioButton5.isChecked()) return R.drawable.voyage_5;
        if (mRadioButton6.isChecked()) return R.drawable.voyage_6;
        return -1;
    }

    public interface AddPhotoListener {
        void receivePhotoData(int imageResource, String description, int position);

        int getItemCount();
    }

    private void finishAdd(View view) {
        getListener().receivePhotoData
                (
                        getSelectedImage(),
                        mDescriptionText.getText().toString(),
                        mPosition


                );
    }
}
