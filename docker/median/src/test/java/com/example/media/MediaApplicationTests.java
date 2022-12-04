package com.example.media;

import com.example.media.web.Media;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class MediaApplicationTests {

	@Test
	void contextLoads() {
		String marks[]={"50","60","90","20","40"};
		Media a=new Media();
		a.media(marks,5);
		assertEquals(a.getMedian(), 50.0);
	}

}
