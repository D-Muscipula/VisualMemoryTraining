package com.example.visualmemorytraining.ui.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.navigation.Navigation;

import com.example.visualmemorytraining.R;
import com.google.firebase.auth.FirebaseAuth;
//Диалог выхода из аккаунта из главного фрагмента
public class LogOutDialogFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Подтвердите выход из аккаунта");
        builder.setMessage("Вы хотите выйти из аккаунта?");
        builder.setPositiveButton("Да", (dialog, id) -> {
            FirebaseAuth.getInstance().signOut();
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                    .navigate(R.id.action_mainFragment_to_loginFragment);

        });
        builder.setNegativeButton("Нет", (dialog, id) -> dialog.cancel());
        return builder.create();
    }
}
