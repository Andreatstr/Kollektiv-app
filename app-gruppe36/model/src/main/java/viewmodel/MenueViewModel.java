package viewmodel;

import model.HouseManager;
import restapi.ServerApi;

/**
 * The MenueViewModel class manages house-related operations such as setting a collective, getting a
 * proposed house ID, generating a house, and logging out.
 */
public class MenueViewModel {

    private String proposedHouseId;
    private static MenueViewModel instance;
    private static final Object lock = new Object();
    private HouseManager houseManager;

  private MenueViewModel() {
    houseManager = HouseManager.getInstance();
  }

  public void setTestApi() {
    houseManager.setTestApi();
  }

  public void setServerApi()
  {
    houseManager.setServerApi();
  }

    /**
     * The getInstance method returns the singleton instance of MenueViewModel,
     * creating it if it doesn't already exist.
     *
     * @return An instance of the MenueViewModel class is being returned.
     */
    public static MenueViewModel getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new MenueViewModel(); // Only create once
                }
            }
        }
        return instance;
    }

    /**
     * The function `setCollective` in Java checks if the input `id` is valid and then
     * sets a house using the `HouseManager` class.
     *
     * @param id The `id` parameter is a String that represents the identifier of a
     * collective or house.
     *
     * @return The method `setCollective(String id)` returns a `Boolean` value.
     */
    public Boolean setCollective(String id) {
        if (id == null || id.length() == 0) {
            return false;
        }
        return HouseManager.getInstance().setHouse(id);
    }

    public String getProposedHouseId() {
        proposedHouseId = houseManager.getNewId();
        return proposedHouseId;
    }

    public void generateHouse() {
        houseManager.createHouse(proposedHouseId);
    }

    public void logOut() {
        houseManager.logOut();
    }
}
