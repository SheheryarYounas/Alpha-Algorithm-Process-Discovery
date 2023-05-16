import java.util.ArrayList;

public class Trace {
    
    private String caseID;
    private Event startEvent;
    private Event endEvent;
    private ArrayList<Event> eventsList;

    public Trace(String caseID, Event startEvent, Event endEvent)
    {
        this.caseID = caseID;
        this.startEvent = startEvent;
        this.endEvent = endEvent;
        this.eventsList = new ArrayList<Event>();
    }

    public void addEvent(Event event)
    {
        eventsList.add(event);
    }

    public String getCaseID()
    {
        return caseID;
    }

    public Event getStartEvent()
    {
        return startEvent;
    }

    public Event getEndEvent()
    {
        return endEvent;
    }

    public void setCaseID(String caseID)
    {
        this.caseID = caseID;
    }

    public void setStartEvent(Event startEvent)
    {
        this.startEvent = startEvent;
    }

    public void setEndEvent(Event endEvent)
    {
        this.endEvent = endEvent;
    }

}
