package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import data.Person;
import data.Task;
import data.WashingPlan;
import data.WashingPlanEntry;
import viewmodel.WashingPlanViewModel;
import javafx.collections.ObservableList;
import restapi.DummyApi;

import java.util.ArrayList;
import java.util.List;

public class WashingPlanViewModelTest {

    private WashingPlanViewModel viewModel;

    @BeforeEach
    public void setUp() {
        viewModel = WashingPlanViewModel.getInstance();
        HouseManager.getInstance().api = new DummyApi();
    }

    
}
