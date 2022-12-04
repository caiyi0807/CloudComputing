package com.qub.total;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
@NoArgsConstructor
@Data
public class Total {

    private Integer id=0;
    private int total_marks;
    private String[] marks;
    private String[] modules;
    private boolean error;
    private ArrayList<String> errorInformation=new ArrayList<>();
    public Total(String[] marks, String[] modules, boolean error, ArrayList<String> errorInformation, int i) {
        this.marks=marks;
        this.modules=modules;
        this.error=error;
        this.errorInformation=errorInformation;
        this.total_marks=i;
    }
    public int total(String mark[]){
        int totalMarks=0;
        for(int i=0;i<mark.length;i++){
            if(marks[i].isEmpty())
                totalMarks+=0;
            else totalMarks+=Integer.parseInt(marks[i]);
        }
        this.total_marks=totalMarks;
        return totalMarks;
    }

}