package com.example.visualmemorytraining.ui.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class BackDialogFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Подтвердите выход из приложения");
        builder.setMessage("Вы хотите выйти?");
        builder.setPositiveButton("Да", (dialog, id) -> {
            //TODO все ли ок?
            //moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);

        });
        builder.setNegativeButton("Нет", (dialog, id) -> dialog.cancel());
        return builder.create();
    }
}
