package model;

/**
 * The `UpdateEvent` interface defines actions that can be performed in response to specific events,
 * including updating an event and logging out.
 */
public interface UpdateEvent {
    public void updateEvent();

    public void logoutEvent();
}