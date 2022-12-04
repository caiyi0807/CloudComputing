package qub.ac.uk.poxy;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "poxyurl")
@Data
public class PoxyUrl {
    private List<String> target_url;
    private List<String> services;
}


