package modules.entities;

import modules.Data;

import java.time.LocalDateTime;

public class MyNotification extends Data {
    private enum States {
        INITIAL_STATE,
        OPENED,
        DELETED

    }

    private String message;
    private LocalDateTime dateTime;
    private States state;

    public MyNotification(String message) {
        this.message = message;
        this.dateTime = LocalDateTime.now();
        this.state = States.INITIAL_STATE;
    }

    public MyNotification(int id, String message, LocalDateTime dateTime, String state) {
        super(id);
        this.message = message;
        this.dateTime = dateTime;
        this.state = States.valueOf(state);
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


    @Override
    public String toString() {
        return "Notifikacija [poruka=" + message + ", datum i vreme=" + dateTime + ", stanje=" + state + "]";
    }

    @Override
    public String toFileString() {
        return super.toFileString() + "," + message + "," + dateTime.toString() + "," + state.toString();
    }
}
