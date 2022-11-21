package com.example.media;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class MediaApplicationTests {

	@Test
	void contextLoads() {
		String marks[]={"50","60","90","20","40"};
		Media a=new Media();
		a.media(marks);
		assertEquals(a.getMedia(), 50.0);
	}

}
