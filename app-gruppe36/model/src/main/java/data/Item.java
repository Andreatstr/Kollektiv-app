package data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * The `Item` class represents an item with properties such as name, count,
 * purchase date,
 * and active status, along with methods to manipulate these properties.
 */
public class Item {
    private String itemName;
    private int itemCount;
    private String bougthDate;
    private final BooleanProperty active = new SimpleBooleanProperty(this, "active");

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Item() {
    }

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

    public void setBoughtDate(String bougthDate) {
        this.bougthDate = bougthDate;
    }

    public void setBoughtDate() {
        this.bougthDate = formatter.format(LocalDate.now());
    }

    /**
     * The function `getBoughtDate` returns the bought date if it is not null or
     * empty, otherwise it
     * returns "?".
     */
    public String getBoughtDate() {
        if (bougthDate == null) {
            return "?";
        }
        if (bougthDate.isEmpty()) {
            return "?";
        }
        return bougthDate;
    }

    /**
     * The function `timePassed` checks if a certain number of days have passed
     * since a specified
     * date.
     *
     * @param days The `days` parameter in the `timePassed` method represents the
     *             number of days to
     *             compare with the difference between the current date and the date
     *             stored in the `boughtDate`
     *             variable. The method calculates the difference in days between
     *             the current date and the
     *             `boughtDate`, and then checks
     *
     * @return The method `timePassed` returns a boolean value indicating whether
     *         the number
     *         of days passed since the `boughtDate` is greater than the input
     *         parameter `days`. If the
     *         number of days passed is greater than `days`, it returns `true`,
     *         otherwise it returns `false`
     */
    public boolean timePassed(Integer days) {
        if (bougthDate == null) {
            String message = "The date for this object has not been set, boughtDate is null";
            throw new IllegalStateException(message);
        }
        LocalDate date = LocalDate.parse(bougthDate);
        long daysBetween = ChronoUnit.DAYS.between(date, LocalDate.now());
        return daysBetween > days;
    }
}
