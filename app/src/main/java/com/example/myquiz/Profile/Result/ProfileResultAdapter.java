package com.example.myquiz.Profile.Result;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myquiz.Profile.QuizProfile;
import com.example.myquiz.R;

import java.util.List;

public class ProfileResultAdapter extends RecyclerView.Adapter<ProfileResultAdapter.ViewHolder>{

    public static List<ProfileRezClass> listQuestionDto;

    //private static int numberCheck;
    public ProfileResultAdapter(List<ProfileRezClass> listQuestionDto) {
        this.listQuestionDto = listQuestionDto;

       // this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ProfileResultAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       // View view = inflater.from(parent.getContext()).inflate(R.layout.tusur_register_recycle, parent, false);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rez_test_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ProfileResultAdapter.ViewHolder holder, final int position) {
   //     final FacultiesTusur facultiesTusur = facultiesTusurList.get(position);

        holder.textResult.setText(listQuestionDto.get(position).id + " | " + listQuestionDto.get(position).title + " | " + listQuestionDto.get(position).fio +" | " + listQuestionDto.get(position).email + " | " + listQuestionDto.get(position).results);

        //holder.description.setText(listQuestionDto.get(position).description);
        //holder.code.setText(Integer.toString (listQuestionDto.get(position).id));
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
        TextView textResult;
        CardView card;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textResult = (TextView) itemView.findViewById(R.id.textViewMyRez);
            card = (CardView)itemView.findViewById(R.id.card_myTest_rez);
        }
    }
}
