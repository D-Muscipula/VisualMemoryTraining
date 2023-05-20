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

import java.util.List;

public class ExerciseButtonAdapter extends RecyclerView.Adapter<ExerciseButtonAdapter.ExerciseButtonViewHolder> implements View.OnClickListener{
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
        //holder.buttonExercise.setText(button .getName());
        int margin = (int) (Math.sin((Math.PI)/5 * position) * 100);
        // получаем отступ в пикселях для dp
        int marginInDp = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, margin, holder.buttonExercise.getContext().getResources().getDisplayMetrics());
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams
                (ConstraintLayout.LayoutParams.WRAP_CONTENT , ConstraintLayout.LayoutParams.WRAP_CONTENT);
        if(position == 0) layoutParams.setMargins(0,(int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 40, holder.buttonExercise.getContext().getResources().getDisplayMetrics()),0,0);
        //TODO для последнего убрать margin (если это из-за него внизу margin)
        layoutParams.setMarginEnd(marginInDp);
        layoutParams.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
        layoutParams.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
        layoutParams.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID;
        layoutParams.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;
        holder.buttonExercise.setLayoutParams(layoutParams);
        /*Drawable image = ContextCompat.getDrawable(holder.buttonExercise.getContext(), R.drawable.circle_button__3_);
        int h = image.getIntrinsicHeight();
        int w = image.getIntrinsicWidth();
        image.setBounds( 0, 0, w, h );
        holder.buttonExercise.setCompoundDrawables( image, null, null, null );*/
        int currentNightMode = holder.buttonExercise.getContext().getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        switch (currentNightMode) {
            case Configuration.UI_MODE_NIGHT_NO:
                // Night mode is not active, we're using the light theme
                Log.i("Theme", "Light");
                break;
            case Configuration.UI_MODE_NIGHT_YES:
                // Night mode is active, we're using dark theme
                holder.buttonExercise.setImageResource(R.drawable.circle_button__3_night);
                Log.i("Theme", "Dark");
                break;
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
            //TODO
            //правильно ли брать контексти у view
            try {
                Task task = Tasks.getTasks().get(Integer.parseInt(button.getName()) - 1);
                Bundle bundle = new Bundle();
                bundle.putSerializable("task", button.getName());
                Navigation.findNavController(v)
                        .navigate(R.id.action_mainFragment_to_imageFragment, bundle);
                //Toast.makeText(v.getContext(), "It just works "+button.getName(), Toast.LENGTH_SHORT).show();}
            }
            catch (IndexOutOfBoundsException e){
                Toast.makeText(v.getContext(), "В разработке",Toast.LENGTH_LONG).show();
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

}
