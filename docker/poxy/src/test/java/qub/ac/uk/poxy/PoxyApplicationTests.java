package qub.ac.uk.poxy;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RunWith(SpringRunner.class)
@SpringBootTest
class PoxyApplicationTests {
    @Autowired
    private PoxyUrl poxyUrl;

    @Test
    void contextLoads() {
        System.out.println(poxyUrl.getServices().get(4));
    }

    @Autowired
    private ProtConfiguration protConfiguration;
    @Test
    void testport(){
        int port = protConfiguration.getPort();
        System.out.println(port);
    }

    @Test
    void num(){
        System.out.println(poxyUrl.getServices().size());
    }

    @Test
    void add(){
        System.out.println(poxyUrl.getServices().size());
    }
}

