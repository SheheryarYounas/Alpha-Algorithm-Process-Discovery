import java.util.ArrayList;

public class Trace {
    
    private String caseID;
    private Event startEvent;
    private Event endEvent;
    private ArrayList<Event> eventsList;
    private ArrayList<String> statusList; //This is for process log, we will differentiate each trace by its status list

    public Trace(String caseID, Event startEvent, Event endEvent)
    {
        this.caseID = caseID;
        this.startEvent = startEvent;
        this.endEvent = endEvent;
        this.eventsList = new ArrayList<Event>();
        this.statusList = new ArrayList<String>();
    }

    public void printEvents()
    {
        for (int i = 0; i < eventsList.size(); i++)
        {
            System.out.println("Event " + (i + 1) + ": " + eventsList.get(i).getStatus() + " at " + eventsList.get(i).getTime() + " by " + eventsList.get(i).getOwner() + " in " + eventsList.get(i).getGroupName());
        }
    }

    public void populateStatusList()
    {
        for (int i = 0; i < eventsList.size(); i++)
        {
            statusList.add(eventsList.get(i).getStatus());
        }
    }

    public ArrayList<String> getStatusList()
    {
        return statusList;
    }

    public ArrayList<Event> getEventsList()
    {
        return eventsList;
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
