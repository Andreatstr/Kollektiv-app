package model;

import data.House;
import data.Waste;
import javafx.collections.ObservableList;
import viewmodel.WasteViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WasteViewModelTest {

    private WasteViewModel wasteViewModel;
    private WasteModel wasteModel;
    private WasteModel mockWasteModel;
    private House mockHouse;

    @BeforeEach
    public void setUp() {
        mockWasteModel = Mockito.mock(WasteModel.class);
        mockHouse = Mockito.mock(House.class);

        HouseManager mockHouseManager = Mockito.mock(HouseManager.class);
        when(mockHouseManager.getHouse()).thenReturn(mockHouse);

        try {
            var field = HouseManager.class.getDeclaredField("instance");
            field.setAccessible(true);
            field.set(null, mockHouseManager);
        } catch (Exception e) {
            throw new RuntimeException("Failed to set HouseManager instance for testing", e);
        }

        try {
            var wasteModelField = WasteModel.class.getDeclaredField("wasteModel");
            wasteModelField.setAccessible(true);
            wasteModelField.set(null, mockWasteModel);
        } catch (Exception e) {
            throw new RuntimeException("Failed to set WasteModel instance for testing", e);
        }

        WasteViewModel.setWasteViewModel(new WasteViewModel());
        // wasteModel = WasteModel.getInstance();
        wasteViewModel = WasteViewModel.getInstance();
        setWasteModel(wasteViewModel, mockWasteModel);
    }

    @Test
    public void testGetWasteCollectionData() {
        ObservableList<Waste> data = wasteViewModel.getWasteCollectionData();
        assertEquals(0, data.size());
    }

    @Test
    public void testGetWasteForWeekReturnsEmptyListIfNoWaste() {
        when(mockWasteModel.getWasteForWeek(2)).thenReturn(new ArrayList<>());
        List<String> wasteForWeek = wasteModel.getWasteForWeek(2);
        assertTrue(wasteForWeek.isEmpty());
    }

    @Test
    public void testGetWastePlanReturnsCorrectData() {
        Map<Integer, List<String>> wastePlan = new HashMap<>();
        wastePlan.put(1, Arrays.asList("Plastic"));
        wastePlan.put(2, Arrays.asList("Glass"));

        when(mockHouse.getWastePlan()).thenReturn(wastePlan);
        when(mockWasteModel.getWastePlan()).thenReturn(wastePlan);
        // setWasteModel(wasteModel, mockWasteModel);

        Map<Integer, List<String>> returnedWastePlan = mockWasteModel.getWastePlan();
        assertNotNull(returnedWastePlan);
        assertFalse(returnedWastePlan.isEmpty());
        assertEquals(2, returnedWastePlan.size());
        assertTrue(returnedWastePlan.containsKey(1));
        assertTrue(returnedWastePlan.containsKey(2));
        assertEquals("Plastic", returnedWastePlan.get(1).get(0));
        assertEquals("Glass", returnedWastePlan.get(2).get(0));
    }

    @Test
    public void testGetInstanceReturnsSingleton() {
        WasteViewModel instance1 = WasteViewModel.getInstance();
        WasteViewModel instance2 = WasteViewModel.getInstance();
        assertEquals(instance1, instance2);
    }

    @Test
    public void testUpdateWasteTableWithWasteData() {
        Map<Integer, List<String>> wastePlan = new HashMap<>();
        wastePlan.put(1, Arrays.asList("Plastic", "Glass"));
        wastePlan.put(2, Arrays.asList("Organic"));

        Mockito.when(mockWasteModel.getWastePlan()).thenReturn(wastePlan);
        wasteViewModel.updateWasteTable();
        ObservableList<Waste> wasteCollectionData = wasteViewModel.getWasteCollectionData();

        assertEquals(3, wasteCollectionData.size());
        assertEquals(new Waste(1, "Plastic").getWasteType(), wasteCollectionData.get(0).getWasteType());
        assertEquals(new Waste(1, "Glass").getWasteType(), wasteCollectionData.get(1).getWasteType());
        assertEquals(new Waste(2, "Organic").getWasteType(), wasteCollectionData.get(2).getWasteType());
    }

    @Test
    public void testUpdateWasteTableWithEmptyWastePlan() {
        Mockito.when(mockWasteModel.getWastePlan()).thenReturn(new HashMap<>());
        wasteViewModel.updateWasteTable();
        ObservableList<Waste> wasteCollectionData = wasteViewModel.getWasteCollectionData();
        assertEquals(0, wasteCollectionData.size());
    }

    private void setWasteModel(WasteViewModel wasteViewModel, WasteModel mockWasteModel) {
        try {
            var field = WasteViewModel.class.getDeclaredField("wasteModel");
            field.setAccessible(true);
            field.set(wasteViewModel, mockWasteModel);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Map<Integer, List<String>> createSampleWastePlan() {
        Map<Integer, List<String>> wastePlan = new HashMap<>();
        wastePlan.put(1, Arrays.asList("Plastic", "Glass"));
        wastePlan.put(2, Arrays.asList("Organic"));
        return wastePlan;
    }

    // @Test
    // public void testScrapeWasteCollection() {
    // assertDoesNotThrow(() -> wasteViewModel.scrapeWasteCollection());
    // }

    @Test
    public void testScrapeWasteCollectionCallsUpdateWasteTable() {
        doNothing().when(mockWasteModel).scrapeWasteCollection();
        when(mockWasteModel.getWastePlan()).thenReturn(createSampleWastePlan());
        wasteViewModel.scrapeWasteCollection();
        verify(mockWasteModel).scrapeWasteCollection();
        assertEquals(3, wasteViewModel.getWasteCollectionData().size());
    }

    @Test
    public void testScrapeWasteCollection() {
        doNothing().when(mockWasteModel).scrapeWasteCollection();
        when(mockWasteModel.getWastePlan()).thenReturn(createSampleWastePlan());

        wasteViewModel.scrapeWasteCollection();

        verify(mockWasteModel).scrapeWasteCollection();
        assertEquals(3, wasteViewModel.getWasteCollectionData().size());
    }
}