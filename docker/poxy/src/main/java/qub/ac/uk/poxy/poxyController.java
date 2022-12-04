package qub.ac.uk.poxy;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.List;
@RestController
public class poxyController {
    @Autowired
    private PoxyUrl poxyUrl;
    @Autowired
    private RoutingDelegate routingDelegate;
    @GetMapping("1/{service}/**")
    public ResponseEntity catchAllService(HttpServletRequest request, HttpServletResponse response, @PathVariable("service") String service) {
        int i = findService(service);
        if (i >= 0) {
            String DELEGATE_PREFIX = "";
            DELEGATE_PREFIX = '/' + service;
            return routingDelegate.redirect(request, response, poxyUrl.getTarget_url().get(i), DELEGATE_PREFIX);
        }
        return null;
    }
    @GetMapping("/admin/{service}/{newURL}/**")
    public ResponseEntity admin(HttpServletRequest request, HttpServletResponse response, @PathVariable("service") String service,@PathVariable("newURL") String newURL) throws IOException {
        int i = findService(service);
        String DELEGATE_PREFIX = "";
        if (i >= 0) {
            DELEGATE_PREFIX = '/' + service;
            return routingDelegate.redirect(request, response, poxyUrl.getTarget_url().get(i), DELEGATE_PREFIX);
        }
        else {
            String targetURL="http://"+newURL;
            poxyUrl.getTarget_url().add(targetURL);
            poxyUrl.getServices().add(service);
            DELEGATE_PREFIX="/"+"admin"+"/"+service+'/'+newURL;
            int sizeS=poxyUrl.getServices().size()-1;
            int sizeT=poxyUrl.getTarget_url().size()-1;
            writeDataToFile("poxyurl.services["+sizeS+"]="+poxyUrl.getServices().get(sizeS)+'\r');
            writeDataToFile("poxyurl.target_url["+sizeT+"]="+poxyUrl.getTarget_url().get(sizeT)+'\r');
            return routingDelegate.redirect(request, response, targetURL, DELEGATE_PREFIX);
        }
    }
    public int findService(String b) {
        List<String> a = poxyUrl.getServices();
        for (int i = 0; i < a.size(); i++) {
            if(a.get(i).equals(b))
                return i;
        }
        return -1;
    }
    public void writeDataToFile(String s) throws IOException {
        FileOutputStream o = null;
        String path = "src/main/resources/";
        String filename = "application.properties";
        byte[] buff = new byte[]{};
        try {
            File file = new File(path + filename);
            if (!file.exists()) {
                file.createNewFile();
            }
            buff = s.getBytes();
            o = new FileOutputStream(file, true);
            o.write(buff);
            o.flush();
            o.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("2/{service}/**")
    public ResponseEntity catchAllService2(HttpServletRequest request, HttpServletResponse response, @PathVariable("service") String service) {
        int i = findService(service);
        if (i >= 0) {
            String DELEGATE_PREFIX = "";
            DELEGATE_PREFIX = '/' + service;
            return routingDelegate.redirect(request, response, poxyUrl.getTarget_url().get(i), DELEGATE_PREFIX);
        }
        return null;
    }

    @GetMapping("3/{service}/**")
    public ResponseEntity catchAllService3(HttpServletRequest request, HttpServletResponse response, @PathVariable("service") String service) {
        int i = findService(service);
        if (i >= 0) {
            String DELEGATE_PREFIX = "";
            DELEGATE_PREFIX = '/' + service;
            return routingDelegate.redirect(request, response, poxyUrl.getTarget_url().get(i), DELEGATE_PREFIX);
        }
        return null;
    }
}
