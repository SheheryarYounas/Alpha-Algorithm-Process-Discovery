import java.util.ArrayList;
import java.util.HashMap;

public class AlphaAlgorithm {

    private ArrayList<Event> eventsList;
    private ArrayList<String> uniqueActivities;
    private ArrayList<Trace> tracesList;

    public AlphaAlgorithm(ArrayList<Event> eventsList)
    {
        this.eventsList = eventsList;
        this.uniqueActivities = new ArrayList<String>();
        this.tracesList = new ArrayList<Trace>();
        
    }

    public void findUniqueActivities() //To generate set TL (Task Log), this will store unique activities
    {
        for (int i = 0; i < eventsList.size(); i++)
        {
            String activity = eventsList.get(i).getStatus();

            if (!uniqueActivities.contains(activity))
            {
                uniqueActivities.add(activity);
            }
        }
        System.out.println("Unique Activities: " + uniqueActivities);
    }

    public void generateTraces()
    {
        //Time to find the start and end event of each case. I will assume each ticket is a case.
        //Looking at my csv file, I already know Open and Closed are my start and end events respectively.
        //But there is also a small chance that open and closed may not be start and end events. To be sure, I will make the method generic and just determine what they really are.
        //I am going to use the HashMapUH

        HashMap<String, Event> startEvents = new HashMap<String, Event>(); //Key will be ticket number (case ID or trace)
        HashMap<String, Event> endEvents = new HashMap<String, Event>();

        for (int i = 0; i < eventsList.size(); i++)
        {
            String ticketNumber = eventsList.get(i).getTicketNumber();

            //As soon as I encounter a ticket number, it will check if it exists. If it does not, the start event is found
            startEvents.putIfAbsent(ticketNumber, eventsList.get(i)); //Man this made things so much easier

            endEvents.put(ticketNumber, eventsList.get(i)); //For the specific ticket number, the end event will be updated until the last event is reached

        }

        for (String ticketNumber: startEvents.keySet())
        {
            Event startEvent = startEvents.get(ticketNumber);
            Event endEvent = endEvents.get(ticketNumber);

            Trace trace = new Trace(ticketNumber, startEvent, endEvent);
            tracesList.add(trace);
        }

        System.out.println("Successfully generated: " + tracesList.size() + " traces");
        
    }
    
}
