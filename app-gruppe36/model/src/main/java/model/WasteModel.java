package model;

import data.House;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * The WasteModel class manages waste plans for a house, allowing users to add, retrieve,
 * and scrape waste collection data from a specified URL.
 */
public class WasteModel {
    private static WasteModel wasteModel = null;
    private House collective;
    private Map<Integer, List<String>> wastePlan = new HashMap<>();

    public WasteModel() {
        readWastePlanFromFile();
    }

    private void readWastePlanFromFile() {
        collective = HouseManager.getInstance().getHouse();
        wastePlan = collective.getWastePlan();
    }

    private void storeToFile() {
        collective.setWastePlan(wastePlan);
    }

    /**
     * The function `getInstance` returns an instance of `WasteModel` if it already exists,
     * otherwise creates a new instance and returns it.
     *
     * @return The `WasteModel` instance is being returned.
     */
    public static WasteModel getInstance() {
        if (wasteModel != null) {
            return wasteModel;
        }
        wasteModel = new WasteModel();
        return wasteModel;
    }

    /**
     * The function `getWastePlan` returns a Map of Integer keys and List of String values,
     * or an empty HashMap if the wastePlan is empty.
     *
     * @return A {@code Map<Integer, List<String>>} containing the waste plan. If the `wastePlan` 
     * is empty, an empty {@code HashMap} is returned.
     */
    public Map<Integer, List<String>> getWastePlan() {
        if (wastePlan.isEmpty()) {
            return new HashMap<>();
        }
        return wastePlan;
    }

    public List<String> getWasteForWeek(int week) {
        return wastePlan.getOrDefault(week, new ArrayList<>());
    }

    /**
     * The function adds a waste type for a specific week to a waste plan and stores the updated
     * plan to a file.
     *
     * @param week The `week` parameter represents the week number for which waste is being added to
     * the waste plan.
     *
     * @param wasteType The `wasteType` parameter in the `addWasteForWeek` method represents the
     * type of waste that is being added for a specific week. It could be any category or
     * classification of waste such as plastic, paper, glass, organic, etc.
     */
    public void addWasteForWeek(int week, String wasteType) {
        wastePlan.putIfAbsent(week, new ArrayList<>());
        wastePlan.get(week).add(wasteType);
        storeToFile();
    }

    /**
     * The `scrapeWasteCollection` function scrapes waste collection data from a specific URL,
     * parses the information, and stores it in a data structure.
     */
    public void scrapeWasteCollection() {
        String url = "https://trv.no/plan/8eca2e31-c262-46fe-911c-770e219d4ba8/";

        try {
            Document document = Jsoup.connect(url).get();
            Elements rows = document.select("table tbody tr");

            wastePlan.clear();

            // Go through rows in scraped table
            for (Element row : rows) {
                String weekText = row.select("td.week").text();
                Elements wasteTypesElements = row.select("td.wastetype_container ul li");

                try {
                    int week = Integer.parseInt(weekText);
                    for (Element wasteElement : wasteTypesElements) {
                        String wasteType = wasteElement.text();
                        addWasteForWeek(week, wasteType.trim());
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Ugyldig ukeformat for " + weekText);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
