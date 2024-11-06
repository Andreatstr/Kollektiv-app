package viewmodel;

import java.util.List;
import java.util.Map;
import data.Waste;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.WasteModel;

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

  public static WasteViewModel getInstance() {
    if (wasteViewModel != null) {
      return wasteViewModel;
    }
    wasteViewModel = new WasteViewModel();
    return wasteViewModel;
  }

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

  public void scrapeWasteCollection() {
    wasteCollectionData.clear();
    wasteModel.scrapeWasteCollection();
    updateWasteTable();
    System.out.println("Scraped and updated");
  }
}