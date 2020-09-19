package modules.entities;

import modules.Data;
import modules.users.Patient;

import java.time.LocalDateTime;

public abstract class MyNotification extends Data {
    private enum States {
        INITIAL_STATE,
        OPENED,
        DELETED

    }

    private String title;
    private String message;
    private LocalDateTime dateTime;
    private States state;


    public MyNotification(String title) {
        this.title = title;
        this.dateTime = LocalDateTime.now();
        this.state = States.INITIAL_STATE;
    }

    public MyNotification(int id, String message, LocalDateTime dateTime, String state) {
        super(id);
        this.message = message;
        this.dateTime = dateTime;
        this.state = States.valueOf(state);
    }

    public States[] getStates() {
        return States.values();
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public States getState() {
        return state;
    }

    public void setState(String state) {
        this.state = States.valueOf(state);
    }

    @Override
    public String toString() {
        return "Notifikacija [poruka=" + message + ", datum i vreme=" + dateTime + ", stanje=" + state + "]";
    }

    @Override
    public String toFileString() {
        return super.toFileString() + "," + message + "," + dateTime.toString() + "," + state.toString();
    }
}
