package view;

import data.Waste;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import viewmodel.WasteViewModel;

/**
 * The WasteView class in Java sets up a user interface for displaying waste collection data with
 * buttons for navigation and image views for house and trash icons.
 */
public class WasteView {

    private WasteViewModel wasteViewModel;

    public WasteView() {
        wasteViewModel = WasteViewModel.getInstance();
    }

    @FXML
    private ImageView houseImage;

    @FXML
    private Button backFromTrash;

    @FXML
    private Button homeButton;

    @FXML
    private TableView<Waste> table;

    @FXML
    private ImageView trashPhoto;

    @FXML
    private TableColumn<Waste, String> wasteColumn;

    @FXML
    private TableColumn<Waste, Integer> weekColumn;

    @FXML
    void buttonHome(ActionEvent event) throws IOException {
        SceneSwitcher.switchToScene(event, "ChoiceScreen.fxml");
    }

    @FXML
    void handleBackFromTrash(ActionEvent event) throws IOException {
        SceneSwitcher.switchToScene(event, "ChoiceScreen.fxml");
    }

    /**
     * The `initialize` function sets up image views for house and trash photos, scrapes waste
     * collection data, and customizes the display of week numbers in a table.
     */
    public void initialize() {
        // House photo
        Image imgHouse = new Image(getClass().getResource("/view/img/house.png").toExternalForm());
        ImageView imageViewHouse = new ImageView(imgHouse);
        imageViewHouse.setFitWidth(30);
        imageViewHouse.setFitHeight(30);
        imageViewHouse.setPreserveRatio(true);
        homeButton.setGraphic(imageViewHouse);

        // Trash photo
        Image imgTrash = new Image(getClass().getResource("/view/img/trash.png").toExternalForm());
        trashPhoto.setFitWidth(200);
        trashPhoto.setFitHeight(200);
        trashPhoto.setPreserveRatio(true);
        trashPhoto.setImage(imgTrash);

        // Scrape logic
        wasteViewModel.getWasteCollectionData().clear();
        wasteViewModel.scrapeWasteCollection();
        weekColumn.setCellValueFactory(new PropertyValueFactory<>("week"));
        wasteColumn.setCellValueFactory(new PropertyValueFactory<>("wasteType"));

        final Integer[] previousWeek = { null };

        weekColumn.setCellFactory(column -> new TableCell<Waste, Integer>() {
            @Override
            protected void updateItem(Integer week, boolean empty) {
                super.updateItem(week, empty);
                if (empty || week == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    if (previousWeek[0] != null && week.equals(previousWeek[0])) {
                        setText(""); // Empty if equal to previous week cell
                    } else {
                        setText(week.toString());
                        previousWeek[0] = week;
                    }
                }
            }
        });
        table.setItems(wasteViewModel.getWasteCollectionData());
    }
}