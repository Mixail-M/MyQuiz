package com.example.myquiz.Profile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myquiz.CreateTest.App;
import com.example.myquiz.CreateTest.CreateTest;
import com.example.myquiz.CreateTest.QuestionDto;
import com.example.myquiz.R;

import java.util.List;

public class MyTestAdapter extends RecyclerView.Adapter<MyTestAdapter.ViewHolder>{

    public static List<QuizProfile> listQuestionDto;

    //private static int numberCheck;
    public MyTestAdapter(List<QuizProfile> listQuestionDto) {
        this.listQuestionDto = listQuestionDto;

       // this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyTestAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       // View view = inflater.from(parent.getContext()).inflate(R.layout.tusur_register_recycle, parent, false);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_test_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyTestAdapter.ViewHolder holder, final int position) {
   //     final FacultiesTusur facultiesTusur = facultiesTusurList.get(position);

        holder.title.setText(listQuestionDto.get(position).title);
        holder.description.setText(listQuestionDto.get(position).description);
        holder.code.setText(Integer.toString (listQuestionDto.get(position).id));
        /*holder.name.setText("Вопрос "+(position+1));
        holder.question.setText(listQuestionDto.get(position).title);
        holder.quest_image.setImageResource(R.drawable.def_question_image);*/
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
        TextView title;
        TextView description;
        TextView code;
        CardView card;
        ImageView quest_image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.textViewMyTitle);
            description = (TextView) itemView.findViewById(R.id.textViewMyDescription);
            code = (TextView) itemView.findViewById(R.id.textViewMyCode);
            card = (CardView)itemView.findViewById(R.id.card_myTest);
            quest_image = (ImageView)itemView.findViewById(R.id.imageView3);
        }
    }
}
