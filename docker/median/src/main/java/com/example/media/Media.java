package com.example.media;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Comparator;

@NoArgsConstructor
@Data
public class Media {
    private double media;
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

    public double media(String marks[]) {
        double media = 0;
        ArrayList<Integer> newMarks = new ArrayList<Integer>();
        for (int i = 0; i < marks.length; i++) {
            if (!marks[i].isEmpty()) {
                newMarks.add(Integer.parseInt(marks[i]));
            }
        }
        newMarks.sort(Comparator.naturalOrder());
        if (newMarks.size() % 2 == 0) {
            int j = newMarks.size() / 2 - 1;
            media=(newMarks.get(j) + newMarks.get(j + 1)) /2;
        }
        else{
            media=newMarks.get((newMarks.size()-1)/2);
        }
        this.media = media;
        return media;
    }

}