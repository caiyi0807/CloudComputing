package com.qub.total;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TotalApplicationTests {

	@Test
	void contextLoads() {
		String marks[]={"50","60","90","20","40"};
		String modules[]={"m1","m2","m3","m4","m5"};
		Total total=new Total(marks,modules);
		assertEquals(total.total(marks), 260);
	}
}
