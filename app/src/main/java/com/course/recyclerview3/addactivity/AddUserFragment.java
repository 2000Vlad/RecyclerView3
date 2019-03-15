package com.course.recyclerview3.addactivity;


import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class AddUserFragment extends Fragment {
    public static final String USER_NAME_KEY = "userName";
    public static final String USER_EMAIL_KEY = "userEmail";
    public static final String USER_PHONE_KEY = "userPhone";
    public static final String USER_AVATAR_KEY = "userAvatar";

    public AddUserFragment() {

        // Required empty public constructor
    }

    private AddUserListener mListener;
    private EditText mUserNameEditText;
    private EditText mEmailEditText;
    private EditText mPhoneEditText;
    private Spinner mSpinner;
    private RadioButton mGreenRadioButton;
    private RadioButton mPinkRadioButton;
    private RadioButton mDarkBlueRadioButton;
    private RadioButton mOrangeRadioButton;
    private RadioButton mBlackRadioButton;
    private Button mButton;

    public AddUserListener getListener() {
        return mListener;
    }

    private int mPosition;

    public void setListener(AddUserListener listener) {
        mListener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_user, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mSpinner = view.findViewById(R.id.spinner_add_user);
        mUserNameEditText = view.findViewById(R.id.edittext_add_user_username);
        mEmailEditText = view.findViewById(R.id.edittext_email);
        mPhoneEditText = view.findViewById(R.id.edittext_phone);
        mGreenRadioButton = view.findViewById(R.id.radiobutton_add_user_green);
        mPinkRadioButton = view.findViewById(R.id.radiobutton_add_user_pink);
        mDarkBlueRadioButton = view.findViewById(R.id.radiobutton_add_user_darkblue);
        mOrangeRadioButton = view.findViewById(R.id.radiobutton_add_user_orange);
        mBlackRadioButton = view.findViewById(R.id.radiobutton_add_user_black);
        mButton = view.findViewById(R.id.button_add_user_finish);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAdd(v);
            }
        });


        //TODO This object will be provided by activity
        mSpinner.setAdapter(getAdapter());
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String pos = (String) parent.getSelectedItem();
                mPosition = Integer.parseInt(pos);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mPosition = -1;
            }
        });
        mPosition = -1;
    }

    private ArrayAdapter<String> getAdapter() {
        ArrayList<String> positions = getPositions();
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, positions);
        return adapter;
    }

    private ArrayList<String> getPositions() {
        ArrayList<String> result = new ArrayList<>();
        int count = mListener.getItemCount();
        for (Integer i = 1; i <= count + 1; i++)
            result.add((i).toString());
        return result;
    }

    public interface AddUserListener {
        void receiveUserData(String userName, String email, String phone, int avatarResource, int position);

        int getItemCount();
    }

    private int getSelectedAvatar() {
        if (mGreenRadioButton.isChecked()) return R.drawable.ic_user1;
        if (mPinkRadioButton.isChecked()) return R.drawable.ic_user2;
        if (mDarkBlueRadioButton.isChecked()) return R.drawable.ic_user3;
        if (mOrangeRadioButton.isChecked()) return R.drawable.ic_user4;
        if (mBlackRadioButton.isChecked()) return R.drawable.ic_user5;
        return -1;
    }

    public void finishAdd(View view) {
        mListener.receiveUserData
                (
                        mUserNameEditText.getText().toString(),
                        mEmailEditText.getText().toString(),
                        mPhoneEditText.getText().toString(),
                        getSelectedAvatar(),
                        mPosition
                );
    }

}
