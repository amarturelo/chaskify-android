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
import android.widget.Toast;

import com.chaskify.android.Chaskify;
import com.chaskify.android.LoginHandler;
import com.chaskify.android.R;
import com.chaskify.android.store.LoginStorage;
import com.chaskify.android.store.PreferenceStorage;

import timber.log.Timber;

/**
 * <p>A fragment that shows a list of items as a modal bottom sheet.</p>
 * <p>You can show this modal bottom sheet from your activity like this:</p>
 * <pre>
 *     ChangePasswordDialogFragment.newInstance(30).show(getSupportFragmentManager(), "dialog");
 * </pre>
 */
public class ChangePasswordDialogFragment extends BottomSheetDialogFragment implements View.OnClickListener, ChangePasswordDialogContract.View {

    private ChangePasswordDialogPresenter presenter;

    private View mChangePasswordBottom;

    private EditText mCurrentPassword;
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ChangePasswordDialogPresenter(
                Chaskify.getInstance().getDefaultSession().get()
        );

        presenter.bindView(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_change_password_dialog, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mChangePasswordBottom = view.findViewById(R.id.change_password_bottom);
        mCurrentPassword = view.findViewById(R.id.current_password);
        mNewPassword = view.findViewById(R.id.new_password);
        mConfirmNewPassword = view.findViewById(R.id.confirm_new_password);

        mChangePasswordBottom.setOnClickListener(this);
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
        mCurrentPassword.setError(null);
        mConfirmNewPassword.setError(null);

        // Store values at the time of the login attempt.
        String currentPasswod = mCurrentPassword.getText().toString();
        String newPassword = mNewPassword.getText().toString();
        String confirmNewPassword = mConfirmNewPassword.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(currentPasswod)) {
            mCurrentPassword.setError(getString(R.string.error_field_required));
            focusView = mCurrentPassword;
            cancel = true;
        }

        if (TextUtils.isEmpty(newPassword)) {
            mNewPassword.setError(getString(R.string.error_field_required));
            focusView = mNewPassword;
            cancel = true;
        }

        if (TextUtils.isEmpty(confirmNewPassword)) {
            mConfirmNewPassword.setError(getString(R.string.error_field_required));
            focusView = mConfirmNewPassword;
            cancel = true;
        }

        if(!newPassword.equals(confirmNewPassword)){
            mConfirmNewPassword.setError(getString(R.string.error_password_not_match));
            focusView = mConfirmNewPassword;
            cancel = true;
        }


        if (cancel)
            focusView.requestFocus();

        return cancel;
    }

    private void changeClick() {
        if (!attemptChange())
            presenter.changePassword(mCurrentPassword.getText().toString()
                    , mNewPassword.getText().toString()
                    , mConfirmNewPassword.getText().toString());
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(Throwable throwable) {
        Timber.d(throwable);
        Toast.makeText(getContext(), throwable.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void complete() {
        Toast.makeText(getContext(), "Se cambio la contrasenna", Toast.LENGTH_LONG).show();
    }
}
