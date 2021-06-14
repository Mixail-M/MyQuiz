package com.example.myquiz.CreateTest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.example.myquiz.R;

import java.util.List;

public class CreateTestAdapter extends RecyclerView.Adapter<CreateTestAdapter.ViewHolder>{
    private static LayoutInflater inflater;
    private static Context context = App.getAppContext();
    private CreateTest frg;
    public static List<QuestionDto> listQuestionDto;

    //private static int numberCheck;
    public CreateTestAdapter(List<QuestionDto> listQuestionDto, CreateTest frg) {
        this.listQuestionDto = listQuestionDto;
        this.frg=frg;
       // this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CreateTestAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       // View view = inflater.from(parent.getContext()).inflate(R.layout.tusur_register_recycle, parent, false);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_create_question, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final CreateTestAdapter.ViewHolder holder, final int position) {
   //     final FacultiesTusur facultiesTusur = facultiesTusurList.get(position);
        holder.name.setText("Вопрос "+(position+1));
        holder.question.setText(listQuestionDto.get(position).title);
        holder.quest_image.setImageResource(R.drawable.def_question_image);
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frg.to_create_question(position);
                /*if(checkCheckBox()!=3||checkBoxes[position]){
                    if(checkBoxes[position]){
                        holder.checkBox.setBackgroundColor(ContextCompat.getColor(context, R.color.colorWhite));
                    }else{
                        holder.checkBox.setBackgroundColor(ContextCompat.getColor(context, R.color.colorBackGround));
                    }
                    Toast.makeText(
                            v.getContext(),
                            "Clicked on Checkbox: " + facultiesTusurList.get(position).getNameFaculties() + " is "
                                    + holder.checkBox.isChecked(), Toast.LENGTH_LONG).show();
                    checkBoxes[position] = !checkBoxes[position];
                    facultiesTusurList.get(position).setCheck(checkBoxes[position]);
                }
                else{
                    holder.checkBox.setChecked(false);
                    Toast.makeText(
                            v.getContext(),
                            "Ограничение" , Toast.LENGTH_LONG).show();
                }*/
            }
        });
    }


    @Override
    public int getItemCount() {
        return listQuestionDto.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView question;
        CardView card;
        ImageView quest_image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.card_create_test_title);
            question = (TextView) itemView.findViewById(R.id.card_create_test_question);
            card = (CardView)itemView.findViewById(R.id.question_card);
            quest_image = (ImageView)itemView.findViewById(R.id.create_test_quest_image);
        }
    }
}
