package data;

public class HistoryShoppingListTable {
  private String item;
  private int count;
  private String when;

  public HistoryShoppingListTable() {
  }

  public HistoryShoppingListTable(String item, int count, String when) {
    this.item = item;
    this.count = count;
    this.when = when; // Skal vi bruke en int som data??
  }

  public String getItem() {
    return item;
  }

  public void setItem(String item) {
    this.item = item;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public String getWhen() {
    return when;
  }

  public void setWhen(String when) {
    this.when = when;
  }
}
