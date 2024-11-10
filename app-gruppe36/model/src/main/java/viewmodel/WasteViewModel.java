package viewmodel;

import data.Waste;
import java.util.List;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.WasteModel;

/**
 * The WasteViewModel class manages waste data by updating and scraping
 * waste collection information.
 */
public class WasteViewModel {

    public static WasteViewModel wasteViewModel;
    private WasteModel wasteModel;
    private ObservableList<Waste> wasteCollectionData = FXCollections.observableArrayList();

    public WasteViewModel() {
        wasteModel = WasteModel.getInstance();
    }

    public ObservableList<Waste> getWasteCollectionData() {
        return wasteCollectionData;
    }

    /**
     * The function `getInstance` returns an instance of `WasteViewModel` if 
     * it already exists, otherwise creates a new instance and returns it.
     *
     * @return An instance of the WasteViewModel class is being returned.
     */
    public static WasteViewModel getInstance() {
        if (wasteViewModel != null) {
            return wasteViewModel;
        }
        wasteViewModel = new WasteViewModel();
        return wasteViewModel;
    }

    /**
     * The `updateWasteTable` function retrieves waste plan data, clears existing waste collection 
     * data, and populates it with new waste entries based on the retrieved plan.
     */
    public void updateWasteTable() {
        Map<Integer, List<String>> wastePlan = wasteModel.getWastePlan();
        wasteCollectionData.clear();

        if (wastePlan != null && !wastePlan.isEmpty()) {
            wastePlan.keySet().stream()
                    .sorted()
                    .forEach(week -> {
                        List<String> wasteTypes = wastePlan.get(week);
                        for (String wasteType : wasteTypes) {
                            wasteCollectionData.add(new Waste(week, wasteType));
                        }
                    });
        } else {
            System.out.println("WastePlan is empty or not initialized");
        }
    }

    /**
     * The `scrapeWasteCollection` method clears existing waste collection data, 
     * scrapes new data using the `wasteModel`, updates the waste table, and prints 
     * a message indicating the process is complete.
     */
    public void scrapeWasteCollection() {
        wasteCollectionData.clear();
        wasteModel.scrapeWasteCollection();
        updateWasteTable();
        System.out.println("Scraped and updated");
    }
}