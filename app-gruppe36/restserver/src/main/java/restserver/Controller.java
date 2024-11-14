package restserver;

import core.HouseController;
import core.ShoppingListController;
import core.WashingPlanController;
import data.House;
import data.requests.CreateWashingPlanRequest;
import data.requests.ItemListRequest;
import data.requests.ItemRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for handling requests related to houses, shopping lists, and washing plans.
 * This controller includes endpoints for creating houses, retrieving house data, managing
 * shopping list items, and generating washing plans for a house.
 */
@RestController
@CrossOrigin
public class Controller {
    
    @GetMapping("/newvalidid")
    public String getNewValidId() {
        return HouseController.getInstance().getNewId();
    }

    @PostMapping ("/createnewhouse")
    public House createNewHouse(@RequestBody String id) {
        return HouseController.getInstance().createHouse(id);
    }
    
    // Log in to house
    @PostMapping ("/gethouse")
    public House getHouse(@RequestBody String id) {
        return HouseController.getInstance().getHouse(id);
    }

    // Used to check connection to the server
    @GetMapping ("/health")
    public String health() {
        return "yehaw";
    }

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
        return WashingPlanController.getInstance().generateWashingPlan(request.getPersons(),
        request.getTasks(), request.getFromWeek(), request.getToWeek(), request.getId());
    }
}
