package com.example.visualmemorytraining.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.visualmemorytraining.R;
import com.example.visualmemorytraining.data.User;
import com.example.visualmemorytraining.databinding.LoginFragmentBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginFragment extends Fragment {
    private LoginFragmentBinding binding;
    private FirebaseAuth mAuth;
    private DatabaseReference mDataBase;
    private EditText login, password;
    private TextView youAreSigned;
    private Button signUp, enter, toApp, exit;
    private final String USER_KEY = "User";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = LoginFragmentBinding.inflate(inflater, container, false);
        mDataBase = FirebaseDatabase.getInstance().getReference(USER_KEY);
        login = binding.login;
        password = binding.password;
        signUp = binding.toSignUp;
        enter = binding.toEnter;
        toApp = binding.toApp;
        youAreSigned = binding.signIn;
        exit = binding.exit;
        mAuth = FirebaseAuth.getInstance();
        //Регистрация
        signUp.setOnClickListener(v -> {
            if (!login.getText().toString().equals("") && !password.getText().toString().equals("")) {
                if (password.getText().toString().length() < 6) {
                    Toast.makeText(getContext(), "Пароль должен быть больше 5 символов", Toast.LENGTH_SHORT).show();
                } else {
                    mAuth.createUserWithEmailAndPassword(login.getText().toString(), password.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getContext(), "Пользователь успешно зарегистрирован", Toast.LENGTH_SHORT).show();
                                        //User user = new User(mDataBase.getKey(),login.getText().toString(), MainActivity.getPref().getInt("scores",0));
                                        User user = new User(mDataBase.getKey(), login.getText().toString(), 0);
                                        //DatabaseReference ref= FirebaseDatabase.getInstance().getReference();
                                        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                        //ref.child(uid).child("note");
                                        emailVer();
                                        mDataBase.child(uid).setValue(user);

                                    } else {
                                        Toast.makeText(getContext(), "Ошибка регистрации", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            } else {
                Toast.makeText(getContext(), "Пустое поле", Toast.LENGTH_SHORT).show();
            }
        });

        //Вход
        enter.setOnClickListener(v -> {

            if (!login.getText().toString().equals("") && !password.getText().toString().equals("")) {
                mAuth.signInWithEmailAndPassword(login.getText().toString(), password.getText().toString())
                        .addOnCompleteListener(task -> {
                            FirebaseUser user = mAuth.getCurrentUser();
                            if (task.isSuccessful() && user.isEmailVerified()) {
                                FirebaseUser currentUser = mAuth.getCurrentUser();
                                Toast.makeText(getContext(), "Успешный вход", Toast.LENGTH_SHORT).show();
                                String userName = "Вы вошли как\n" + currentUser.getEmail();
                                youAreSigned.setText(userName);
                                youAreSigned.setVisibility(View.VISIBLE);
                                toApp.setVisibility(View.VISIBLE);
                                exit.setVisibility(View.VISIBLE);
                                login.setVisibility(View.GONE);
                                password.setVisibility(View.GONE);
                                signUp.setVisibility(View.GONE);
                                enter.setVisibility(View.GONE);
                            } else if (task.isSuccessful() && user.isEmailVerified() == false) {
                                Toast.makeText(getContext(), "Подтвердите почту", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getContext(), "Ошибка входа", Toast.LENGTH_SHORT).show();
                            }
                        });
            }

        });
        entered();
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        entered();
        //updateUI(currentUser);
    }

    //Верификация по почте
    public void emailVer() {
        FirebaseUser user = mAuth.getCurrentUser();
        assert user != null;
        user.sendEmailVerification().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(getContext(), "Подтвердите почту", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Ошибка подтверждения почты", Toast.LENGTH_SHORT).show();
            }
        });
    }
    //Пользователь вошел
    public void entered() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            youAreSigned.setVisibility(View.GONE);
            toApp.setVisibility(View.GONE);
            exit.setVisibility(View.GONE);

        } else {
            login.setVisibility(View.GONE);
            password.setVisibility(View.GONE);
            signUp.setVisibility(View.GONE);
            enter.setVisibility(View.GONE);
            String userName = "Вы вошли как\n" + currentUser.getEmail();
            youAreSigned.setText(userName);

        }
        exit.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            youAreSigned.setVisibility(View.GONE);
            toApp.setVisibility(View.GONE);
            exit.setVisibility(View.GONE);
            login.setVisibility(View.VISIBLE);
            password.setVisibility(View.VISIBLE);
            signUp.setVisibility(View.VISIBLE);
            enter.setVisibility(View.VISIBLE);
        });

        toApp.setOnClickListener(v -> {
            Navigation.findNavController(binding.getRoot())
                    .navigate(R.id.action_loginFragment_to_mainFragment);
        });
    }
}
