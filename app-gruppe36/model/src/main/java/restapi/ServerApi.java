package restapi;

import java.util.List;


import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import data.House;
import data.Item;
import data.Person;
import data.Task;
import data.requests.CreateWashingPlanRequest;
import data.requests.ItemListRequest;
import data.requests.ItemRequest;

public class ServerApi implements RestApi {

    private RestTemplate restTemplate;
    private String url = "http://localhost:8080/";

    public ServerApi() {
        restTemplate = new RestTemplate();
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(url));
    }

    @Override
    public House addItem(Item item, String id) {
        try {
        ItemRequest request = new ItemRequest(item, id);
        return restTemplate.postForObject(url + "additem", request, House.class);
        }
        catch(RestClientException e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public House CreateNewHouse(String id) {
        try {
            return restTemplate.postForObject(url + "createnewhouse", id, House.class);
            }
            catch(RestClientException e)
            {
                System.out.println(e.getMessage());
                return null;
            }
    }

    @Override
    public House GetHouse(String id) {
        try {
            return restTemplate.postForObject(url + "gethouse", id, House.class);
            }
            catch(RestClientException e)
            {
                System.out.println(e.getMessage());
                return null;
            }
    }

    @Override
    public House buyItem(List<Item> items, String id) {
        try {
            ItemListRequest request = new ItemListRequest(items, id);
            return restTemplate.postForObject(url + "buyitems", request, House.class);
            }
            catch(RestClientException e)
            {
                System.out.println(e.getMessage());
                return null;
            }
    }

    @Override
    public House deleteItems(List<Item> items, String id) {
        try {
            ItemListRequest request = new ItemListRequest(items, id);
            return restTemplate.postForObject(url + "removeitem", request, House.class);
            }
            catch(RestClientException e)
            {
                System.out.println(e.getMessage());
                return null;
            }
    }

    @Override
    public House generateWashingplan(List<Person> persons, List<Task> tasks, int fromWeek,int toWeek,String houseId) {
        try {
            CreateWashingPlanRequest request = new CreateWashingPlanRequest(persons, tasks, fromWeek, toWeek, houseId);
            return restTemplate.postForObject(url + "generateWashingplan", request, House.class);
            }
            catch(RestClientException e)
            {
                System.out.println(e.getMessage());
                return null;
            }
    }

    @Override
    public String getNewValidId() {
        try {
            return restTemplate.getForObject(url + "newvalidid", String.class);
            }
            catch(RestClientException e)
            {
                System.out.println(e.getMessage());
                return "";
            }
    }


    public void setRestTemplate(RestTemplate template)
    {
        this.restTemplate = template;
    }
    @Override
    public String type() {
        return "Server-Api";
    }
    
}
