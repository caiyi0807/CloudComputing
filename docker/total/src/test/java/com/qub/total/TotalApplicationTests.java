package com.qub.total;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TotalApplicationTests {

	@Test
	void contextLoads() {
		String marks[]={"50","60","90","20","40"};
		Total a=new Total();
		a.setMarks(marks);
		int atotal = a.total(marks);
		assertEquals(atotal, 260);
	}

//	@Autowired
//    MongoTemplate mongoTemplate;
//	@Test
//	void databaseConnection(){
//		boolean exists = mongoTemplate.collectionExists("TotalFunction");
//		assertEquals(exists,true);
//	}

}
