package com.example.StudyGoals;

public class StudyGoal {
    String examDate;
    String notificationReminderTime;
    int numberOfQuestions;
    public int studyDuration;
    Boolean isReminder;
    static StudyGoal studyGoal;
    static StudyGoalManager studyGoalManagerObject;

    private StudyGoal() {
        studyGoalManagerObject = StudyGoalManager.getStudyGoalManagerObject();
        examDate = studyGoalManagerObject.getExamDate();
        notificationReminderTime = studyGoalManagerObject.getDailyReminderTime();
        numberOfQuestions = Integer.parseInt(studyGoalManagerObject.getUserQuestionsGoal());
        studyDuration = Integer.parseInt(studyGoalManagerObject.getUserTimeGoal());
        isReminder = Boolean.valueOf(studyGoalManagerObject.getIsReminder());
    }

    public static StudyGoal getStudyGoalObject() {
        if (studyGoal == null) {
            studyGoal = new StudyGoal();
        }
        return studyGoal;
    }

    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
        studyGoalManagerObject.saveOrUpdateStudyGoalData(StudyGoalManager.EXAM_DATE,examDate);
    }

    public String getNotificationReminderTime() {
        return notificationReminderTime;
    }

    public void setNotificationReminderTime(String notificationReminderTime) {
        this.notificationReminderTime = notificationReminderTime;
        studyGoalManagerObject.saveOrUpdateStudyGoalData(StudyGoalManager.DAILY_REMINDER_TIME,notificationReminderTime);
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
        studyGoalManagerObject.saveOrUpdateStudyGoalData(StudyGoalManager.USER_QUESTIONS_GOAL, String.valueOf(numberOfQuestions));
    }

    public int getStudyDuration() {
        return studyDuration;
    }

    public void setStudyDuration(int studyDuration) {
        this.studyDuration = studyDuration;
        studyGoalManagerObject.saveOrUpdateStudyGoalData(StudyGoalManager.USER_TIME_GOAL, String.valueOf(studyDuration));
    }

    public Boolean enableReminder() {
        return isReminder;
    }

    public void setReminder(Boolean reminder) {
        isReminder = reminder;
        studyGoalManagerObject.saveOrUpdateStudyGoalData(StudyGoalManager.IS_REMINDER, String.valueOf(reminder));
    }

    public static StudyGoal getStudyGoal() {
        return studyGoal;
    }

    public void setStudyGoal(StudyGoal studyGoal) {
        StudyGoal.studyGoal = studyGoal;
    }

    @Override
    public String toString() {
        return "StudyGoal{" +
                "examDate='" + examDate + '\'' +
                ", studyDuration='" + studyDuration + '\'' +
                ", numberOfQuestions='" + numberOfQuestions + '\'' +
                ", notificationReminderTime='" + notificationReminderTime + '\'' +
                ", isReminder=" + isReminder +
                '}';
    }
}