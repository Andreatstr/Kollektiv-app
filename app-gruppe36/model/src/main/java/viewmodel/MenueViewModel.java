package viewmodel;

import model.HouseManager;

public class MenueViewModel {

  private String proposedHouseId;
  private static MenueViewModel instance;
  private HouseManager houseManager;

  private MenueViewModel() {
    houseManager = HouseManager.getInstance();
  }

  public static MenueViewModel getInstance() {
    if (instance == null) {
      instance = new MenueViewModel();
    }
    return instance;
  }

  public Boolean setCollective(String id) {
    if (id == null) {
      return false;
    }
    if (id.length() == 0) {
      return false;
    }
    return HouseManager.getInstance().setHouse(id);
  }

    public String getProposedHouseId()
    {
        proposedHouseId = houseManager.getNewId();
        return proposedHouseId;
    }

    public void generateHouse()
    {
        houseManager.CreateHouse(proposedHouseId);
    }

    public void logOut()
    {
        houseManager.logOut();
    }



}
