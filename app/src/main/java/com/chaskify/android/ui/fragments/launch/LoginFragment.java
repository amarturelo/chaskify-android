package com.chaskify.android.ui.fragments.launch;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.chaskify.android.LoginHandler;
import com.chaskify.android.R;
import com.chaskify.android.navigation.Navigator;
import com.chaskify.android.ui.base.BaseFragment;
import com.chaskify.android.ui.model.ProfileItemModel;

import static com.chaskify.android.ui.activities.LaunchActivity.ARG_ACCOUNT_NAME;

public class LoginFragment extends BaseFragment implements LoginContract.View {

    public static final String PROFILE_ITEM = "arg_profile_item";


    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mEmailLoginFormView;
    private View mSignInButton;

    private View mFormUsername;

    private LoginPresenter mLoginPresenter;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public static LoginFragment newInstance(ProfileItemModel profileItemModel) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putParcelable(PROFILE_ITEM, profileItemModel);
        fragment.setArguments(args);
        return fragment;
    }

    public ProfileItemModel getArgProfile() {
        return getArguments().getParcelable(PROFILE_ITEM);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLoginPresenter = new LoginPresenter(
                new LoginHandler()
        );
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_login;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mLoginPresenter.release();
    }

    @Override
    public void loginComplete() {
        goToMain();
    }

    private void goToMain() {
        getActivity().finish();
        Navigator.goToMainActivity(getContext());
    }

    @Override
    public void showError(Exception e) {
        Toast.makeText(getContext(), e.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgress() {
        showProgress(true);
    }

    @Override
    public void hideProgress() {
        showProgress(false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mLoginPresenter.bindView(this);
        initView(view);
        init();
    }

    private void init() {
        if (getArgProfile() != null) {
            mFormUsername.setVisibility(View.VISIBLE);
            mEmailView.setVisibility(View.GONE);
            mEmailView.setText(getArgProfile().getDriverUsername());
        } else {
            mFormUsername.setVisibility(View.GONE);
            mEmailView.setVisibility(View.VISIBLE);
        }
    }

    private void initView(View view) {
        // Set up the login form.
        mEmailView = view.findViewById(R.id.email);
        mFormUsername = view.findViewById(R.id.form_username);
        mPasswordView = view.findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener((textView, id, keyEvent) -> {
            if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                attemptLogin();
                return true;
            }
            return false;
        });

        mSignInButton = view.findViewById(R.id.email_sign_in_button);
        mSignInButton.setOnClickListener(v -> login());

        mEmailLoginFormView = view.findViewById(R.id.email_login_form);
        mProgressView = view.findViewById(R.id.login_progress);
    }

    private void login() {
        if (!attemptLogin()) {
            mLoginPresenter.login(mEmailView.getText().toString(), mPasswordView.getText().toString());
        }
    }

    private boolean attemptLogin() {

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }
        if (cancel)
            focusView.requestFocus();

        return cancel;

    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        //return email.contains("@");
        return true;
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() >= 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mEmailLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mEmailLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mEmailLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mEmailLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
}
