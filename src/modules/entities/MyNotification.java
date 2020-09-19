package modules.entities;

import modules.Data;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public abstract class MyNotification extends Data {
    private enum States {
        INITIAL_STATE,
        OPENED,
        DELETED
    }

    private String title;
    private LocalDateTime dateTime;
    private States state;


    public MyNotification(String title) {
        this.title = title;
        this.dateTime = LocalDateTime.now();
        this.state = States.INITIAL_STATE;
    }

    public MyNotification(int id, String title, LocalDateTime dateTime, String state) {
        super(id);
        this.title = title;
        this.dateTime = dateTime;
        this.state = States.valueOf(state);
    }

    public States[] getStates() {
        return States.values();
    }

    public String getTitle() {
        return title;
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

    public Map<String, Object> getParameters() {
        Map<String, Object> retVal = new HashMap<>();
        retVal.put("Naslov:", title);
        retVal.put("Datum i vreme:", dateTime);
        retVal.put("Stanje:", state);
        return retVal;
    }

    @Override
    public String toString() {
        return "naslov=" + title + ", datum i vreme=" + dateTime + ", stanje=" + state;
    }

    @Override
    public String toFileString() {
        return super.toFileString() + "," + title + "," + dateTime.toString() + "," + state.toString();
    }
}
