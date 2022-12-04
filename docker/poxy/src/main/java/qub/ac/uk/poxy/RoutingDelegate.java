package qub.ac.uk.poxy;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;

@Service
public class RoutingDelegate {
    public ResponseEntity<String> redirect(HttpServletRequest request, HttpServletResponse response, String routeUrl, String prefix) {
        try {
            String redirectUrl = createRedictUrl(request, routeUrl, prefix);
            RequestEntity requestEntity = createRequestEntity(request, redirectUrl);
            return route(requestEntity);
        } catch (Exception e) {
            return new ResponseEntity("REDIRECT ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    private String createRedictUrl(HttpServletRequest request, String routeUrl, String prefix) {
        String queryString = request.getQueryString();
        String s = "";
        System.out.println(request.getRequestURI());
        s = routeUrl + request.getRequestURI().replace(prefix, "") +
                (queryString != null ? "?" + queryString : "");
        System.out.println(s);
        return s;
    }
    private RequestEntity createRequestEntity(HttpServletRequest request, String url) throws URISyntaxException, IOException {
        HttpMethod httpMethod = HttpMethod.GET;
        MultiValueMap<String, String> headers = parseRequestHeader(request);
        byte[] body = parseRequestBody(request);
        return new RequestEntity<>(body, headers, httpMethod, new URI(url));
    }
    private ResponseEntity<String> route(RequestEntity requestEntity) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(requestEntity, String.class);
    }
    private byte[] parseRequestBody(HttpServletRequest request) throws IOException {
        InputStream inputStream = request.getInputStream();
        return StreamUtils.copyToByteArray(inputStream);
    }
    private MultiValueMap<String, String> parseRequestHeader(HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();
        String headervalue = "";
        Boolean judge = false;
        List<String> headerNames = Collections.list(request.getHeaderNames());
        int i = 0;
        for (String headerName : headerNames) {
            if (headerName == "Cookie") {
                List<String> headerValues = Collections.list(request.getHeaders(headerName));
                headervalue = headerValues.get(i);
                break;
            }
            i++;
        }
        headers.clear();
        if(judge)
            headers.set("Cookie",headervalue);
        headers.set("Access-Control-Allow-Origin", "*");
        headers.set("Connection", "keep-alive");
        headers.set("Content-Type", "application/json");
        return headers;
    }

}