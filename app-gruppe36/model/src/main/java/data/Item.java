package data;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import java.time.Period;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;



public class Item {
    private String itemName;
    private int itemCount;
    private String bougthDate;
    private final BooleanProperty active = new SimpleBooleanProperty(this, "active");

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Item(){}

    public Item(String itemName, int itemCount) {
        this.itemName = itemName;
        this.itemCount = itemCount;
    }

    public void setBoughtDate(String bougthDate)
    {
        this.bougthDate = bougthDate;
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
        System.out.println("Set bougth date!");
        this.bougthDate = formatter.format(LocalDate.now());
        System.out.println(getBoughtDate());
    }

    public boolean timePassed(Integer days) {
        if (bougthDate == null) throw new IllegalStateException("The date for this object has not been set, boughtDate is null");
        LocalDate date = LocalDate.parse(bougthDate);
        Period period = Period.between(LocalDate.now(),date);
        if (period.getDays() > days) return true;
        return false;
    }

    public String getBoughtDate()
    {
        if (bougthDate == null || bougthDate == "") return "?";
        return bougthDate;
    }
}
