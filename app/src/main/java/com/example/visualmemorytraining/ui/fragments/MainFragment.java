package com.example.visualmemorytraining.ui.fragments;

import static com.example.visualmemorytraining.ui.viewmodels.MainFragmentViewModel.getScores;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.visualmemorytraining.ui.viewmodels.MainFragmentViewModel;
import com.example.visualmemorytraining.R;
import com.example.visualmemorytraining.data.User;
import com.example.visualmemorytraining.data.ButtonData;
import com.example.visualmemorytraining.databinding.FragmentMainBinding;
import com.example.visualmemorytraining.ui.adapter.ExerciseButtonAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainFragment extends Fragment {
    public FragmentMainBinding binding;
    private DatabaseReference mDataBase;
    private String USER_KEY = "User";
    private MainFragmentViewModel viewModel;
    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(MainFragmentViewModel.class);
        // Адаптер RecyclerView
        ExerciseButtonAdapter buttonAdapter = new ExerciseButtonAdapter(ButtonData.getSource());
        binding.recyclerView.setLayoutManager(
                new LinearLayoutManager(requireContext())
        );
        binding.recyclerView.setAdapter(buttonAdapter);
        //Работа с базой данных
        mDataBase = FirebaseDatabase.getInstance().getReference(USER_KEY);
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference uidRef = FirebaseDatabase.getInstance().getReference().child(USER_KEY).child(uid);
        binding.scores.setText("Количество очков: " + getScores());
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                User user = dataSnapshot.getValue(User.class);
                viewModel.currentScores(user.scores);
                viewModel.currentEmail(user.email);
                Log.d("Firebase", String.valueOf(user.scores));
                binding.scores.setText("Количество очков: " + user.scores);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("Firebase", databaseError.getMessage());
            }
        };
        uidRef.addListenerForSingleValueEvent(valueEventListener);

        //Кнопка для перехода к лидерборду
        ImageButton toLeaderBoard = binding.toLeaderBoard;
        toLeaderBoard.setOnClickListener(v -> {
            Navigation.findNavController(v)
                    .navigate(R.id.action_mainFragment_to_leaderBoardFragment);
        });
        //Кнопка для выхода из аккаунта
        binding.logOut.setOnClickListener(v -> {
            DialogFragment newFragment = new LogOutDialogFragment();
            newFragment.show(getParentFragmentManager(), "exit");
        });

        //Действие на кнопку назад
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            //при нажатии кнопки назад появляется alertDialog
            public void handleOnBackPressed() {
                // Back is pressed...
                DialogFragment newFragment = new BackDialogFragment();
                newFragment.show(getParentFragmentManager(), "exit");

            }
        });
        return binding.getRoot();


    }

}
