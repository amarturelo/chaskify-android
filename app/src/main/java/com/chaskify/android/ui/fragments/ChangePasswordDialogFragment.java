package com.chaskify.android.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.chaskify.android.R;

/**
 * <p>A fragment that shows a list of items as a modal bottom sheet.</p>
 * <p>You can show this modal bottom sheet from your activity like this:</p>
 * <pre>
 *     ChangePasswordDialogFragment.newInstance(30).show(getSupportFragmentManager(), "dialog");
 * </pre>
 */
public class ChangePasswordDialogFragment extends BottomSheetDialogFragment implements View.OnClickListener {

    private ChangePasswordDialogPresenter presenter;

    private View mLoginBottom;

    private EditText mNewPassword;
    private EditText mConfirmNewPassword;

    private static final String ARG_USERNAME = "username";

    public static ChangePasswordDialogFragment newInstance(String username) {
        final ChangePasswordDialogFragment fragment = new ChangePasswordDialogFragment();
        final Bundle args = new Bundle();
        args.putString(ARG_USERNAME, username);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_change_password_dialog, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mLoginBottom = view.findViewById(R.id.change_password_bottom);
        mNewPassword = view.findViewById(R.id.new_password);
        mConfirmNewPassword = view.findViewById(R.id.confirm_new_password);

        mLoginBottom.setOnClickListener(this);
        //final RecyclerView recyclerView = (RecyclerView) view;
        /*recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new ItemAdapter(getArguments().getInt(ARG_ITEM_COUNT)));*/
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        final Fragment parent = getParentFragment();
/*        if (parent != null) {
            mListener = (Listener) parent;
        } else {
            mListener = (Listener) context;
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.change_password_bottom:
                changeClick();
                break;
        }
    }

    private boolean attemptChange() {

        // Reset errors.
        mNewPassword.setError(null);
        mConfirmNewPassword.setError(null);

        // Store values at the time of the login attempt.
        String newPassword = mNewPassword.getText().toString();
        String confirmNewPassword = mConfirmNewPassword.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(newPassword)) {
            mNewPassword.setError(getString(R.string.error_invalid_password));
            focusView = mNewPassword;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(confirmNewPassword)) {
            mConfirmNewPassword.setError(getString(R.string.error_field_required));
            focusView = mConfirmNewPassword;
            cancel = true;
        }
        if (cancel)
            focusView.requestFocus();

        return cancel;
    }

    private void changeClick() {
        if (!attemptChange())
            presenter.changePassword(mNewPassword.getText().toString()
                    , mConfirmNewPassword.getText().toString());
    }
}
