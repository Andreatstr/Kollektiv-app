package gr36.demo1;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@CrossOrigin
public class Controller {
 
    @GetMapping("/health-check")
    public String getHealthCheck()
    {
        return "Yeehaw";
    }


}
