package com.example.visualmemorytraining.ui.adapter;

import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.visualmemorytraining.R;
import com.example.visualmemorytraining.data.Data;
import com.example.visualmemorytraining.data.Task;
import com.example.visualmemorytraining.data.Tasks;
import com.example.visualmemorytraining.databinding.ExerciseButtonListItemBinding;
import com.example.visualmemorytraining.databinding.FragmentMainBinding;

import java.util.List;
import java.util.Objects;

public class ExerciseButtonAdapter extends RecyclerView.Adapter<ExerciseButtonAdapter.ExerciseButtonViewHolder> implements View.OnClickListener{
    private static final int VIEW_TYPE_ITEM_TYPE_1 = 1;
    private static final int VIEW_TYPE_ITEM_TYPE_2 = 2;
    private static final int VIEW_TYPE_ITEM_TYPE_3 = 3;
    private static final int VIEW_TYPE_ITEM_TYPE_4 = 4;
    private static final int VIEW_TYPE_ITEM_TYPE_5 = 5;
    private final List<Data> data;
    public ExerciseButtonAdapter(List<Data> data) {
        this.data = data;
    }
    @NonNull
    @Override

    public ExerciseButtonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ExerciseButtonListItemBinding binding = ExerciseButtonListItemBinding
                .inflate(LayoutInflater.from(parent.getContext()),parent,
                        false);

        binding.itemButton.setOnClickListener(this);
        return new ExerciseButtonViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseButtonViewHolder holder, int position) {
        Data button = data.get(position);
        holder.buttonExercise.setTag(button);
        holder.itemView.setTag(button);
        //Определение отступа для отрисовки кнопок по синусоиде
        int margin = (int) (Math.sin((Math.PI)/5 * position) * 100);
        // получаем отступ в пикселях для dp
        int marginInDp = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, margin, holder.buttonExercise.getContext().getResources().getDisplayMetrics());
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams
                (ConstraintLayout.LayoutParams.WRAP_CONTENT , ConstraintLayout.LayoutParams.WRAP_CONTENT);
        if(position == 0) layoutParams.setMargins(0,(int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 40, holder.buttonExercise.getContext().getResources().getDisplayMetrics()),0,0);

        layoutParams.setMarginEnd(marginInDp);
        layoutParams.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
        layoutParams.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
        layoutParams.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID;
        layoutParams.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;
        holder.buttonExercise.setLayoutParams(layoutParams);
        int type = getItemViewType(position);
        if(type == 2){
            holder.buttonExercise.setImageResource(R.drawable.circle_button__3_r);

        }
        else if (type == 1){
            holder.buttonExercise.setImageResource(R.drawable.circle_button__3_green);
        }
        else if (type == 3){
            holder.buttonExercise.setImageResource(R.drawable.circle_button__3_rr);
        }
        else if (type == 4){
            holder.buttonExercise.setImageResource(R.drawable.circle_button__3_unk);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();

    }

    @Override
    public void onClick(View v) {
        Data button = (Data) v.getTag();
        if (v.getId() == R.id.item_button){
            try {
                Task task = Tasks.getTasks().get(Integer.parseInt(button.getName()) - 1);
                Bundle bundle = new Bundle();
                bundle.putSerializable("task", button.getName());
                Navigation.findNavController(v)
                        .navigate(R.id.action_mainFragment_to_imageFragment, bundle);
            }
            //Если к кнопке нет вопроса
            catch (IndexOutOfBoundsException e){
                Toast.makeText(v.getContext(), "В разработке, дождитесь обновления :-)",Toast.LENGTH_LONG).show();
            }

        }
    }

    public static class ExerciseButtonViewHolder extends RecyclerView.ViewHolder{
        ImageButton buttonExercise;
        public ExerciseButtonViewHolder(ExerciseButtonListItemBinding binding) {
            super(binding.getRoot());
            buttonExercise = binding.itemButton;
            
        }
    }

    @Override
    //Нужен для правильной отрисовки кнопок разных цветов
    public int getItemViewType(int position) {
        if (position > 4 && position < 10) {
            return VIEW_TYPE_ITEM_TYPE_1;
        } else if (position > 9 && position < 15){
            return VIEW_TYPE_ITEM_TYPE_2;
        }
     else if (position > 14 && position < 20){
        return VIEW_TYPE_ITEM_TYPE_3;
    }
        else if (position > 19 && position < 25){
            return VIEW_TYPE_ITEM_TYPE_4;
        }
        else
            return VIEW_TYPE_ITEM_TYPE_5;
    }

}
