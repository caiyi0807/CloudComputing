package com.qub.total;

public class Total {
    private int total_marks;
    private String[] marks;
    private String[] modules;

    public Total(String[] marks, String[] modules) {
        this.marks = marks;
        this.modules = modules;
    }

    public String[] getMarks() {
        return marks;
    }

    public void setMarks(String[] marks) {
        this.marks = marks;
    }

    public String[] getModules() {
        return modules;
    }

    public void setModules(String[] modules) {
        this.modules = modules;
    }

    public int getTotal_marks() {
        return total_marks;
    }

    public void setTotal_marks(int total_marks) {
        this.total_marks = total_marks;
    }

    public int total(String mark[]){
        int totalMarks=0;
        for(int i=0;i<mark.length;i++){
            if(marks[i].isEmpty())
                totalMarks+=0;
            else totalMarks+=Integer.parseInt(marks[i]);
        }
        return  totalMarks;
    }

}