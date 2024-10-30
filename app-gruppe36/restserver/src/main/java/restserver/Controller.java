package restserver;
import java.util.List;
import data.*;
import core.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;




@RestController
@CrossOrigin
public class Controller {
    
    @GetMapping("/newvalidid")
    public String getNewValidId()
    {
        return null;
    }

    @PostMapping ("/createnewhouse")
    public House CreateNewHouse(@RequestBody String id)
    {
        return null;
    }
    

    //logge inn i ett hus
    @PostMapping ("/gethouse")
    public House GetHouse(@RequestBody String id)
    {
        return new House(id);
    }

    //ShoppingList

    @PostMapping ("/additem")
    public House additem(@RequestBody Item item, @RequestBody String id)
    {
        System.out.println(item.getItemName());
        return new House(id);
    }

    @PostMapping ("/buyItems")
    public House buyItem(@RequestBody List<Item> items, @RequestBody String id)
    {
        return null;
    }

    @PostMapping ("/deleteItems")
    public House deleteItems(@RequestBody List<Item> items, @RequestBody String id)
    {
        return null;
    }

    //WashingPlan

    @PostMapping ("/generateWashingplan")
    public House generateWashingplan()
    {
        return null;
    }
    


}
