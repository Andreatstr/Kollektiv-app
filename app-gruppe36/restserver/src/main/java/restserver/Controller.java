package restserver;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;




@RestController
@CrossOrigin
public class Controller {
    /* 
    @GetMapping("/health-check")
    public String getHealthCheck()
    {
        return "Yeehaw";
    }*/

    //Get mapping som sender id og får et object

    @PostMapping ("/sethouse")
    public void CreateNewHouse(@RequestBody String id)
    {
        System.out.println(id);
    }

    @PostMapping ("/additem")
    public void getHouse(List<Float> item)
    {
        System.out.print(item.size());
        //core
    }


}
