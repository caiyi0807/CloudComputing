package com.qub.total;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("inc")
@Data
public class IncInfo {
    @Id
    private String id;
    @Field
    private String TotalFunction;
    @Field
    private Integer incId;
}
