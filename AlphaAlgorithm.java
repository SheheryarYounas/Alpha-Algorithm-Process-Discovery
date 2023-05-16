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

    public void findUniqueActivities() //To generate set TL (Task Log) which is second requirement of assignment, this will store unique activities
    {
        for (int i = 0; i < eventsList.size(); i++)
        {
            String activity = eventsList.get(i).getStatus();

            if (!uniqueActivities.contains(activity))
            {
                uniqueActivities.add(activity);
            }
        }
        System.out.println("Successfully generated: " + uniqueActivities.size() + " unique activities");
    }

    public void generateTraces() //First requirement of the assignment is multi-set of traces L which means duplicate events are allowed. This is the method.
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
            Event startEvent = startEvents.get(ticketNumber); //Start event is TI: Task Initiation
            Event endEvent = endEvents.get(ticketNumber); //End event is TO: Task Output

            Trace trace = new Trace(ticketNumber, startEvent, endEvent);
            tracesList.add(trace);
        }

        for (int i = 0; i < eventsList.size(); i++)
        {
            String ticketNumber = eventsList.get(i).getTicketNumber();

            for (int j = 0; j < tracesList.size(); j++)
            {
                if (tracesList.get(j).getCaseID().equals(ticketNumber))
                {
                    tracesList.get(j).addEvent(eventsList.get(i));
                }
            }
        }

        System.out.println("Successfully generated: " + tracesList.size() + " traces");
        
    }

    public void printTIAndTO() //This is third requirement of assignment. This will print out the start and end events of each trace (TI and TO)
    {
        for (int i = 0; i < tracesList.size(); i++)
        {
            System.out.println("Case ID: " + tracesList.get(i).getCaseID());
            System.out.println("Start Event: " + tracesList.get(i).getStartEvent().getStatus());
            System.out.println("End Event: " + tracesList.get(i).getEndEvent().getStatus());
            System.out.println();
        }
    }
    
}
