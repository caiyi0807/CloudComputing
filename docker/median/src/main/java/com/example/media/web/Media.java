package com.example.media.web;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Comparator;

@NoArgsConstructor
@Data
public class Media {
    private double median;
    private String[] marks;
    private String[] modules;
    private boolean error;
    private ArrayList<String> errorInformation = new ArrayList<>();

    public Media(String[] marks, String[] modules, boolean error, ArrayList<String> errorInformation) {
        this.marks = marks;
        this.modules = modules;
        this.error = error;
        this.errorInformation = errorInformation;
    }

    public double media(String marks[],int len) {
        double median = 0;
        ArrayList<Integer> newMarks = new ArrayList<Integer>();
        for (int i = 0; i < len; i++) {
            if (!marks[i].isEmpty()) {
                newMarks.add(Integer.parseInt(marks[i]));
            }
        }
        newMarks.sort(Comparator.naturalOrder());
        if(newMarks.size()!=0){
            if (newMarks.size() % 2 == 0) {
                int j = newMarks.size() / 2 - 1;
                median=(newMarks.get(j) + newMarks.get(j + 1)) /2;
            }
            else{
                median=newMarks.get((newMarks.size()-1)/2);
            }
        }
        this.median = median;
        return median;
    }

}