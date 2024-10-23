package view;

import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;

public class WasteCollectionScraper {

    public void scrapeWasteCollection() {
        String url = "https://trv.no/plan/8eca2e31-c262-46fe-911c-770e219d4ba8/";

        try {
            // Henter hele siden med Jsoup
            Document document = Jsoup.connect(url).get();

            // Velger tabellrader som inneholder avfallstype og dato
            Elements rows = document.select("table tbody tr");

            // Går gjennom radene i tabellen
            for (Element row : rows) {
                // Henter data for uke, avfallstype og dato
                String week = row.select("td.week").text();
                String wasteType = row.select("td.wastetype_container").text();
                String date = row.select("td.date").text();

                // Skriver ut informasjonen
                System.out.println("Uke: " + week + " - Avfallstype: " + wasteType + " - Dato: " + date);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
