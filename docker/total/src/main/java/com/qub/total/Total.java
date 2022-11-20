package com.qub.total;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
@NoArgsConstructor
@Data
@Document("TotalFunction")
public class Total {

    //@Indexed
    @Id
    @AutoIncKey
    private Integer id=0;
    @Field("total_marks")
    private int total_marks;
    @Field("marks")
    private String[] marks;
    @Field("modules")
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