package model;

/**
 * The `UpdateEvent` interface defines actions that can be performed in response to specific events,
 * including updating an event and logging out.
 */
public interface UpdateEvent {

    // Is called when the client recives data
    public void updateEvent();

    // Is called when the client logs out
    public void logoutEvent();
}