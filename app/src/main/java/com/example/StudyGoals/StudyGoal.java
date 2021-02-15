package com.example.StudyGoals;

public class StudyGoal {
    String examDate = "Set Date";
    String notificationReminderTime = "Set Time";
    int numberOfQuestions = 20;
    public int studyDuration = 30;
    Boolean isReminder = false;
    static StudyGoal studyGoal;

    private StudyGoal() {
    }

    public static StudyGoal getStudyGoalobject() {
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

    }

    public String getNotificationReminderTime() {
        return notificationReminderTime;
    }

    public void setNotificationReminderTime(String notificationReminderTime) {
        this.notificationReminderTime = notificationReminderTime;

    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;

    }

    public int getStudyDuration() {
        return studyDuration;
    }

    public void setStudyDuration(int studyDuration) {
        this.studyDuration = studyDuration;

    }

    public Boolean getReminder() {
        return isReminder;
    }

    public void setReminder(Boolean reminder) {
        isReminder = reminder;

    }

    public static StudyGoal getStudyGoal() {
        return studyGoal;
    }

    public static void setStudyGoal(StudyGoal studyGoal) {
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