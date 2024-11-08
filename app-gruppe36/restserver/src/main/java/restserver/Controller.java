package restserver;

import data.*;
import data.requests.*;
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
    public String getNewValidId() {
        return HouseController.getInstance().getNewId();
    }

    @PostMapping ("/createnewhouse")
    public House CreateNewHouse(@RequestBody String id) {
        return HouseController.getInstance().createHouse(id);
    }
    
    // Log in to house
    @PostMapping ("/gethouse")
    public House GetHouse(@RequestBody String id) {
        return HouseController.getInstance().getHouse(id);
    }

    // ShoppingList
    @PostMapping ("/additem")
    public House additem(@RequestBody ItemRequest request) {
        return ShoppingListController.getInstance().addItem(request.getItem(), request.getId());
    }

    @PostMapping ("/buyitems")
    public House buyItem(@RequestBody ItemListRequest request) {
        return ShoppingListController.getInstance().buyItems(request.getItems(), request.getId());
    }

    @PostMapping ("/removeitem")
    public House deleteItems(@RequestBody ItemListRequest request) {
        return ShoppingListController.getInstance().removeItem(request.getItems(), request.getId());
    }

    // WashingPlan
    @PostMapping ("/generateWashingplan")
    public House generateWashingplan(@RequestBody CreateWashingPlanRequest request) {
        return WashingPlanController.getInstance().generateWashingPlan(request.persons, request.tasks, request.fromWeek, request.toWeek,request.id);
    }
}
