package com.example.admin.thoikhoabieu;

public class Week {
    private String StartDate;
    private String EndDate;

    public Week(String startDate, String endDate) {
        StartDate = startDate;
        EndDate = endDate;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }

    @Override
    public String toString() {
        return StartDate +" đến " + EndDate ;
    }
}
