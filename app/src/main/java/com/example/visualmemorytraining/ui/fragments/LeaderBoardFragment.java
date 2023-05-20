package com.example.visualmemorytraining.ui.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.visualmemorytraining.R;
import com.example.visualmemorytraining.data.User;
import com.example.visualmemorytraining.databinding.LeaderBoardFragmentBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LeaderBoardFragment extends Fragment {
    private LeaderBoardFragmentBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = LeaderBoardFragmentBinding.inflate(inflater, container, false);
        binding.progressBar.setVisibility(View.VISIBLE);
        List<User> ten = new ArrayList<>();
        List<TextView> views= new ArrayList<>();
        addView(views);
        DatabaseReference mDataBase = FirebaseDatabase.getInstance().getReference("User");
        Query query = mDataBase.orderByChild("scores");
        for (int i = 0; i < 10; i++) {
            ten.add(new User("User","",0));
        }
        final int[] i = {0};
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Обработка полученных данных
                // Обработка полученных данных в обратном порядке
                Iterable<DataSnapshot> snapshotIterator = dataSnapshot.getChildren();
                List<DataSnapshot> snapshotList = new ArrayList<>();
                for (DataSnapshot snapshot : snapshotIterator) {
                    snapshotList.add(snapshot);
                }

                Collections.reverse(snapshotList);

                for (DataSnapshot userSnapshot : snapshotList) {
                    User user = userSnapshot.getValue(User.class);

                    ten.set(i[0],user);
                    i[0] ++ ;
                    //if (i[0] == 10) break;
                    // Обработка каждого пользователя
                }
                for (int j = 0; j < 10; j++) {
                    TextView temp = views.get(j);
                    User tempUser = ten.get(j);
                    String str = (j+1) + ". " +tempUser.email+" "+tempUser.scores+"exp";
                    temp.setText(str);
                }
                // dataSnapshot содержит снимок данных с отсортированными пользователями
                binding.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Обработка ошибок при получении данных
                i[0] ++;
                for (int j = 0; j < 10; j++) {
                    TextView temp = views.get(j);
                    User tempUser = ten.get(j);
                    String str = (j+1) + ". " +tempUser.email+" "+tempUser.scores;
                    temp.setText(str);
                }
                binding.progressBar.setVisibility(View.GONE);
            }
        });


        binding.toTasks.setOnClickListener(v -> {
            Navigation.findNavController(v)
                    .navigate(R.id.action_leaderBoardFragment_to_mainFragment);
        });

        binding.logOut.setOnClickListener(v -> {
            DialogFragment newFragment = new LogOutDialogFragmentFromLeaderBoard();
            newFragment.show(getParentFragmentManager(), "exit");
        });
        return binding.getRoot();
    }

    public void addView(List<TextView> views){
        views.add(binding.textView2);
        views.add(binding.textView3);
        views.add(binding.textView4);
        views.add(binding.textView5);
        views.add(binding.textView6);
        views.add(binding.textView7);
        views.add(binding.textView8);
        views.add(binding.textView9);
        views.add(binding.textView10);
        views.add(binding.textView11);
    }
}
