package com.example.criminalIntent_20;

import java.sql.Time;
import java.util.Date;
import java.util.UUID;

public class Crime {

    private UUID mId;
    private String mTitle;
    private Date mDate;
    private Time mTime;
    private String mDetails;
    private String mSolveDesc;
    private boolean mSolved;
    private String mSuspect;

    public Crime() {
        this(UUID.randomUUID());
    }

    public Crime(UUID id) {
        mId = id;
        mDate = new Date();
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public Time getTime() {
        return mTime;
    }

    public void setTime(Time time) {
        mTime = time;
    }

    public String getDetails() {
        return mDetails;
    }

    public void setDetails(String details) {
        mDetails = details;
    }

    public String getSolveDesc() {
        return mSolveDesc;
    }

    public void setSolveDesc(String solveDesc) {
        mSolveDesc = solveDesc;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    public String getSuspect(){ return mSuspect; }

    public void setSuspect(String suspect){ mSuspect = suspect; }

    public String getPhotoFilename() {
        return "IMG_" + getId().toString() + ".jpg";
    }
}
