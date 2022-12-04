package com.qub.total;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
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

}
