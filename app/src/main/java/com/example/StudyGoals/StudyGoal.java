package com.example.StudyGoals;

public class StudyGoal {
    String examDate = "";
    String notificationReminderTime = "Set Time";
    int numberOfQuestions = 20;
    public int studyDuration = 30;
    Boolean isReminder = false;
    static StudyGoal studyGoal;

    private StudyGoal() {
        StudyGoalManager studyGoalManagerObject = StudyGoalManager.getSGMobject();
        examDate = studyGoalManagerObject.getExamDate();
        notificationReminderTime = studyGoalManagerObject.getNotificationReminderTime();
        numberOfQuestions = studyGoalManagerObject.getNumberOfQuestions();
        studyDuration = studyGoalManagerObject.getStudyDuration();
        isReminder = studyGoalManagerObject.getReminder();
    }
    StudyGoalManager studyGoalManagerObject2 = StudyGoalManager.getSGMobject();
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
        studyGoalManagerObject2.setExamDate(examDate);
    }

    public String getNotificationReminderTime() {
        return notificationReminderTime;
    }

    public void setNotificationReminderTime(String notificationReminderTime) {
        this.notificationReminderTime = notificationReminderTime;
        studyGoalManagerObject2.setNotificationReminderTime(notificationReminderTime);
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
        studyGoalManagerObject2.setNumberOfQuestions(numberOfQuestions);
    }

    public int getStudyDuration() {
        return studyDuration;
    }

    public void setStudyDuration(int studyDuration) {
        this.studyDuration = studyDuration;
        studyGoalManagerObject2.setStudyDuration(studyDuration);
    }

    public Boolean getReminder() {
        return isReminder;
    }

    public void setReminder(Boolean reminder) {
        isReminder = reminder;
        studyGoalManagerObject2.setReminder(reminder);
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