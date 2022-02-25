package com.example.go4lunch;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.go4lunch.databinding.ActivityAuthMethodPickerBinding;
import com.example.go4lunch.databinding.ActivityRootBindingBinding;
import com.firebase.ui.auth.AuthMethodPickerLayout;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.FirebaseUiException;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
public class AuthActivity extends AppCompatActivity {

    private final int AUTH_CODE = 123;
    private ActivityAuthMethodPickerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAuthMethodPickerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void startSignInActivity(){

        // Choose authentication providers
        List<AuthUI.IdpConfig> providers =
                Collections.singletonList(new AuthUI.IdpConfig.EmailBuilder().build());

        // Launch the activity
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setTheme(R.style.LoginTheme)
                        .setAvailableProviders(providers)
                        .setIsSmartLockEnabled(false, true)
                        .setLogo(R.drawable.ic_logout_24)
                        .build(),
                AUTH_CODE);
    }
    private AuthMethodPickerLayout getCustomAuthLayout() {
        return new AuthMethodPickerLayout.Builder(R.layout.activity_auth_method_picker)
                .setGoogleButtonId(R.id.google_login_btn)
                .setFacebookButtonId(R.id.fb_login_btn)
                .build();
    }


    private void startMainActivity() {
        final Intent startMainActivityIntent = new Intent(this, AuthActivity.class);
        startActivity(startMainActivityIntent);
        finish();
    }

    private void connectionStatement(){

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();

            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            String uid = user.getUid();
        }
        else{
        }
    }
}