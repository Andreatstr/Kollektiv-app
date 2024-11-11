package viewmodel;

import model.HouseManager;

/**
 * The MenueViewModel class manages house-related operations such as setting a
 * collective, getting a
 * proposed house ID, generating a house, and logging out.
 */
public class MenueViewModel {

    private String proposedHouseId;
    private static MenueViewModel instance;
    private HouseManager houseManager;

    private MenueViewModel() {
        houseManager = HouseManager.getInstance();
    }

    public void setTestApi() {
        houseManager.setTestApi();
    }

    /**
     * The getInstance method returns the singleton instance of MenueViewModel,
     * creating it if it doesn't already exist.
     *
     * @return An instance of the MenueViewModel class is being returned.
     */
    public static MenueViewModel getInstance() {
        if (instance == null) {
            instance = new MenueViewModel(); 
            }
        return instance;
    }

    /**
     * The function `setCollective` in Java checks if the input `id` is valid and
     * then
     * sets a house using the `HouseManager` class.
     *
     * @param id The `id` parameter is a String that represents the identifier of a
     *           collective or house.
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
