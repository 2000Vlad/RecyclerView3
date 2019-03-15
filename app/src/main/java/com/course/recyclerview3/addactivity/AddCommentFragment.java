package com.course.recyclerview3.addactivity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.course.recyclerview3.R;

import java.util.ArrayList;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class AddCommentFragment extends Fragment {
    public static final String COMMENT_USERNAME_KEY = "commentUsername";
    public static final String COMMENT_TEXT_KEY = "commentText";

    public AddCommentFragment() {
        // Required empty public constructor
    }


    private AddCommentListener mListener;
    private EditText mUserNameText;
    private EditText mText;
    private Button mButton;
    private Spinner mSpinner;
    private int mPosition;

    public AddCommentListener getListener() {
        return mListener;
    }

    public void setListener(AddCommentListener listener) {
        mListener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_comment, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mUserNameText = view.findViewById(R.id.edittext_add_comment_username);
        mText = view.findViewById(R.id.edittext_add_comment_text);
        mButton = view.findViewById(R.id.button_add_comment_finish);
        mSpinner = view.findViewById(R.id.spinner_add_comment);
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
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAdd(v);
            }
        });
    }

    private void finishAdd(View view) {
        mListener.receiveCommentData
                (
                        mUserNameText.getText().toString(),
                        mText.getText().toString(),
                        mPosition
                );
    }

    private ArrayAdapter<String> getAdapter() {
        ArrayList<String> positions = getPositions();
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, positions);
        return adapter;
    }

    private ArrayList<String> getPositions() {
        int count = mListener.getItemCount();
        ArrayList<String> result = new ArrayList<>();
        for (int i = 1; i <= count + 1; i++) {
            result.add(((Integer) i).toString());
        }
        return result;
    }

    public interface AddCommentListener {
        void receiveCommentData(String userName, String commentText, int position);

        int getItemCount();
    }
}
