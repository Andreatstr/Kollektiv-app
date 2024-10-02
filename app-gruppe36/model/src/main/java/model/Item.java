package model;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import java.time.Period;
import java.time.LocalDate;


public class Item {
    private String itemName;
    private int itemCount;
    private LocalDate bougthDate;
    private final BooleanProperty active = new SimpleBooleanProperty(this, "active");

    public Item(){}

    public Item(String itemName, int itemCount) {
        this.itemName = itemName;
        this.itemCount = itemCount;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }
    
    public void setActive(Boolean active) {
        this.active.set(active);
    }

    public BooleanProperty activeProperty() {
        return active;
    }

    public Boolean getActive() {
        return active.getValue();
    }

    public void setBoughtDate() {
        bougthDate = LocalDate.now();
    }

    public boolean timePassed(Integer days) {
        if (bougthDate == null) throw new IllegalStateException("The date for this object has not been set, boughtDate is null");
        Period period = Period.between(LocalDate.now(),bougthDate);
        if (period.getDays() > days) return true;
        return false;
    }
}
