package model;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class WasteModelTest {

    private WasteModel wasteModel;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        wasteModel = WasteModel.getInstance();
        System.setOut(new PrintStream(outputStreamCaptor));
        HouseManager.getInstance().setTestApi();
    }

    @Test
    public void testScrapeWasteCollection() throws IOException {
        Document mockDocument = Mockito.mock(Document.class);
        Connection mockConnection = Mockito.mock(Connection.class);
        Element mockRow = Mockito.mock(Element.class);
        Elements mockRows = Mockito.mock(Elements.class);
        Elements mockWasteTypesElements = Mockito.mock(Elements.class);
        Element mockWasteTypeElement = Mockito.mock(Element.class);

        // Simuler en gyldig uke og avfallstype som returneres fra nettsiden
        when(mockDocument.select("table tbody tr")).thenReturn(mockRows);
        when(mockRows.iterator()).thenReturn(List.of(mockRow).iterator());
        when(mockRow.select("td.week")).thenReturn(new Elements(new Element("td").text("1"))); // Gyldig uke
        when(mockRow.select("td.wastetype_container ul li")).thenReturn(mockWasteTypesElements);
        when(mockWasteTypesElements.iterator()).thenReturn(List.of(mockWasteTypeElement).iterator());
        when(mockWasteTypeElement.text()).thenReturn("Plastic");

        try (MockedStatic<Jsoup> jsoupMockedStatic = Mockito.mockStatic(Jsoup.class)) {
            jsoupMockedStatic.when(() -> Jsoup.connect(anyString())).thenReturn(mockConnection);
            when(mockConnection.get()).thenReturn(mockDocument);

            wasteModel.scrapeWasteCollection();
            Map<Integer, List<String>> wastePlan = wasteModel.getWastePlan();

            // Sjekk at én uke og én avfallstype ble lagt til i wastePlan
            assertEquals(1, wastePlan.size(), "Forventet at wastePlan inneholder én uke");
            assertEquals("Plastic", wastePlan.get(1).get(0), "Forventet at avfallstype er Plastic for uke 1");
        }
    }

    @Test
    public void testScrapeWasteCollectionWithInvalidWeekFormat() throws IOException {
        Document mockDocument = Mockito.mock(Document.class);
        Connection mockConnection = Mockito.mock(Connection.class);
        Element mockRow = Mockito.mock(Element.class);
        Elements mockRows = Mockito.mock(Elements.class);
        Elements mockWasteTypesElements = Mockito.mock(Elements.class);
        Element mockWasteTypeElement = Mockito.mock(Element.class);

        // Simuler en rad med ugyldig ukeformat
        when(mockDocument.select("table tbody tr")).thenReturn(mockRows);
        when(mockRows.iterator()).thenReturn(List.of(mockRow).iterator());
        when(mockRow.select("td.week")).thenReturn(new Elements(new Element("td").text("InvalidWeek"))); // Ugyldig uke
        when(mockRow.select("td.wastetype_container ul li")).thenReturn(mockWasteTypesElements);
        when(mockWasteTypesElements.iterator()).thenReturn(List.of(mockWasteTypeElement).iterator());
        when(mockWasteTypeElement.text()).thenReturn("Plastic");

        try (MockedStatic<Jsoup> jsoupMockedStatic = Mockito.mockStatic(Jsoup.class)) {
            jsoupMockedStatic.when(() -> Jsoup.connect(anyString())).thenReturn(mockConnection);
            when(mockConnection.get()).thenReturn(mockDocument);

            wasteModel.scrapeWasteCollection();

            Map<Integer, List<String>> wastePlan = wasteModel.getWastePlan();

            // Sjekk at wastePlan er tom på grunn av ugyldig ukeformat
            assertTrue(wastePlan.isEmpty(), "Waste plan should be empty due to invalid week format");

            // Sjekk at riktig feilmelding skrives ut
            assertTrue(outputStreamCaptor.toString().contains("Ugyldig ukeformat for InvalidWeek"),
                    "Expected error message for invalid week format");
        }
    }
}
