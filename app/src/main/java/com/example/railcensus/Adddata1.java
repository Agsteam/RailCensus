 package com.example.railcensus;

 import java.util.Date;

 public class Adddata1
{
   private String  coachtype,date,div,station;
    private int average,coachnum,female,season,senior,trainno,male2,student;
    private float carrying,occupied;
    public Adddata1()
    {}
    public Adddata1(String date, int trainno,String div,String station, String coachtype, int coachnum, int carrying, int male2, int female, int senior, int student, int season, int occupied, int average)
    {
        this.date = date;
        this.trainno=trainno;
        this.coachtype = coachtype;
        this.coachnum = coachnum;
        this.carrying = carrying;
        this.div = div;
        this.station = station;
        this.male2=male2;
        this.female = female;
        this.senior = senior;
        this.student=student;
        this.season = season;
        this.occupied = occupied;
        this.average = average;

    }

    public String getCoachtype() {
        return coachtype;
    }

    public String getDate() {
        return date;
    }

    public String getDiv() {
        return div;
    }

    public String getStation() {
        return station;
    }

    public int getAverage() {
        return average;
    }

    public int getCoachnum() {
        return coachnum;
    }

    public int getFemale() {
        return female;
    }

    public int getSeason() {
        return season;
    }

    public int getSenior() {
        return senior;
    }

    public int getTrainno() {
        return trainno;
    }

    public int getMale2() {
        return male2;
    }

    public int getStudent() {
        return student;
    }

    public float getCarrying() {
        return carrying;
    }

    public float getOccupied() {
        return occupied;
    }
}