package model;

import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import data.House;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class WasteModel {
  public static WasteModel wasteModel = null;
  private House collective;
  private Map<Integer, List<String>> wastePlan = new HashMap<>();
  
  public WasteModel() {
    readWastePlanFromFile();
  }

  private void readWastePlanFromFile() {
    collective = HouseManager.getInstance().getHouse();
    wastePlan= collective.getWastePlan();
  }

  private void storeToFile() {
    collective.setWastePlan(wastePlan);
  }

  public static WasteModel getInstance() {
    if (wasteModel != null) {
      return wasteModel;
    }
    wasteModel = new WasteModel();
    return wasteModel;
  }

  public Map<Integer, List<String>> getWastePlan() {
    if (wastePlan.isEmpty()) {
      return new HashMap<>();
    }
    return wastePlan;
  }

  public List<String> getWasteForWeek(int week) {
    return wastePlan.getOrDefault(week, new ArrayList<>());
  }

  public void addWasteForWeek(int week, String wasteType) {
    wastePlan.putIfAbsent(week, new ArrayList<>());
    wastePlan.get(week).add(wasteType);
    storeToFile();
  }

  public void scrapeWasteCollection() {
    String url = "https://trv.no/plan/8eca2e31-c262-46fe-911c-770e219d4ba8/";

    try {
      Document document = Jsoup.connect(url).get();
      Elements rows = document.select("table tbody tr");

      wastePlan.clear();

      //Go through rows in scraped table
      for (Element row : rows) {
        String weekText = row.select("td.week").text();
        Elements wasteTypesElements = row.select("td.wastetype_container ul li");
        
        try {
          int week = Integer.parseInt(weekText);
          for (Element wasteElement : wasteTypesElements) {
            String wasteType = wasteElement.text();
            addWasteForWeek(week, wasteType.trim());
        }
        }
        catch (NumberFormatException e) {
          System.out.println("Ugyldig ukeformat for " + weekText);
        }
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
