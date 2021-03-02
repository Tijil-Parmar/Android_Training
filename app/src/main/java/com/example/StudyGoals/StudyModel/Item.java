package com.example.StudyGoals.StudyModel;

public class Item {
    private int type;
    private String string;
    private boolean switchValue;

    public Item(int type, String string) {
        this.type = type;
        this.string = string;
    }

    public Item(int type, Boolean enableReminder) {
        this.type = type;
        this.switchValue = enableReminder;
    }

    public int getType() {
        return type;
    }

    public Object getString() {
        return string;
    }

    public boolean isSwitchValue() {
        return switchValue;
    }
}
