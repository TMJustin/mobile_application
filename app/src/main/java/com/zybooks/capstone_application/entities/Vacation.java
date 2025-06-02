package com.zybooks.capstone_application.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "VACATIONS")
public class Vacation {

    @PrimaryKey(autoGenerate = true)
    public int vacationID;
    private String vacationTitle;
    private String accommodation;
    private String startDate;
    private String endDate;

    public Vacation(int vacationID, String vacationTitle, String accommodation, String startDate, String endDate) {
        this.vacationID = vacationID;
        this.vacationTitle = vacationTitle;
        this.accommodation = accommodation;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getVacationID() {
        return vacationID;
    }

    public void setVacationID(int vacationID) {
        this.vacationID = vacationID;
    }

    public String getVacationTitle() {
        return vacationTitle;
    }

    public void setVacationTitle(String vacationTitle) {
        this.vacationTitle = vacationTitle;
    }

    public String getAccommodation() {
        return accommodation;
    }

    public void setAccommodation(String accommodation) {
        this.accommodation = accommodation;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
