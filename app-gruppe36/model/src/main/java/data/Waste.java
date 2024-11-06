package data;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Waste {
  private SimpleIntegerProperty week;
  private SimpleStringProperty wasteType;

  public Waste() {
  }

  public Waste(Integer week, String wasteType) {
    this.week = new SimpleIntegerProperty(week);
    this.wasteType = new SimpleStringProperty(wasteType);
  }

  public Integer getWeek() {
    return week.get();
  }
  
  public SimpleIntegerProperty weekProperty() {
    return week;
  }

  public String getWasteType() {
    return wasteType.get();
  }
}