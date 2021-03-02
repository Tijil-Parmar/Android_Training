package com.example.StudyGoals.StudyGoalAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.StudyGoals.Components.QuestionGoalViewHolder;
import com.example.StudyGoals.Components.StudyGoalButtonsViewHolder;
import com.example.StudyGoals.Components.StudyGoalDailyReminderViewHolder;
import com.example.StudyGoals.Components.StudyGoalsExamDateViewHolder;
import com.example.StudyGoals.Components.StudyGoalsTimeGoalViewHolder;
import com.example.StudyGoals.R;
import com.example.StudyGoals.StudyModel.Item;
import com.example.StudyGoals.StudyModel.StudyGoal;

import java.util.List;

public class StudyGoalAdapter2 extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{

    enum StudyGoalOrder{
        EXAM_DATE,
        NUMBER_OF_QUESTIONS,
        TIME_GOAL,
        DAILY_REMINDER_SWITCH,
        STUDY_GOAL_BUTTONS
    }
    Context studyGoalActivityContext;
    ViewGroup view;
    StudyGoal studyGoal= StudyGoal.getStudyGoal();

    private List<Item> items;

    public StudyGoalAdapter2(List<Item> items, Context context) {
        this.items=items;
        studyGoalActivityContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = parent;

        StudyGoalOrder order = StudyGoalOrder.values()[viewType];
        switch (order) {
            case EXAM_DATE:
                return new StudyGoalsExamDateViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(
                                R.layout.examdate_studygoal,
                                parent,
                                false
                        )
                );
            case NUMBER_OF_QUESTIONS:
                return new QuestionGoalViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(
                                R.layout.numberofquestions_studygoal,
                                parent,
                                false
                        )
                );
            case TIME_GOAL:
                return new StudyGoalsTimeGoalViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(
                                R.layout.time_goal_studygoal,
                                parent,
                                false
                        )
                );
            case DAILY_REMINDER_SWITCH:
                return new StudyGoalDailyReminderViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(
                                R.layout.dailyreminder_switch_studygoal,
                                parent,
                                false
                        )
                );
            case STUDY_GOAL_BUTTONS:
                return new StudyGoalButtonsViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(
                                R.layout.get_started_review_progress_studygoal,
                                parent,
                                false
                        )
                );
            default:
                return null;
            }
//        if (viewType == EXAMDATE) {
//            return new StudyGoalsExamDateViewHolder(
//                    LayoutInflater.from(parent.getContext()).inflate(
//                            R.layout.examdate_studygoal,
//                            parent,
//                            false
//                    )
//            );
//        } else if (viewType == QUESTION_GOAL) {
//            return new QuestionGoalViewHolder(
//                    LayoutInflater.from(parent.getContext()).inflate(
//                            R.layout.numberofquestions_studygoal,
//                            parent,
//                            false
//                    )
//            );
//        } else if (viewType == TIMEGOAL) {
//            return new StudyGoalsTimeGoalViewHolder(
//                    LayoutInflater.from(parent.getContext()).inflate(
//                            R.layout.time_goal_studygoal,
//                            parent,
//                            false
//                    )
//            );
//        } else if (viewType == DAILY_REMINDER) {
//            return new StudyGoalDailyReminderViewHolder(
//                    LayoutInflater.from(parent.getContext()).inflate(
//                            R.layout.dailyreminder_switch_studygoal,
//                            parent,
//                            false
//                    )
//            );
//        }
//        else if (viewType == STUDY_GOALS_BUTTONS) {
//            return new StudyGoalButtonsViewHolder(
//                    LayoutInflater.from(parent.getContext()).inflate(
//                            R.layout.get_started_review_progress_studygoal,
//                            parent,
//                            false
//                    )
//            );
//        }
//        else {
//            return  null;
//        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position)==0)
        {
            ((StudyGoalsExamDateViewHolder)holder).setExamDateData(studyGoal.getExamDate(), studyGoalActivityContext);
        }
        else if(getItemViewType(position)==1){
            ((QuestionGoalViewHolder)holder).numberOfQuestionsSetData(studyGoal.getNumberOfQuestions());
        }
        else if(getItemViewType(position)==2){
            ((StudyGoalsTimeGoalViewHolder)holder).timeGoalSetData(studyGoal.getStudyDuration(),studyGoalActivityContext);
        }
        else if(getItemViewType(position)==3){
            ((StudyGoalDailyReminderViewHolder)holder).dailyReminderSetData(studyGoal.getNotificationReminderTime(), studyGoalActivityContext);
        }
        else if(getItemViewType(position)==4){
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getType();
    }
}
