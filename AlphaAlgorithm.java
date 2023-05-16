import java.util.ArrayList;
import java.util.HashMap;

public class AlphaAlgorithm {

    private ArrayList<Event> eventsList;
    private ArrayList<String> uniqueActivities;
    private HashMap<String, Trace> tracesList;
    private HashMap<ArrayList<String>, Integer> processLog; //The trace arraylist as key and Integer is for telling how many unique traces there are

    public AlphaAlgorithm(ArrayList<Event> eventsList)
    {
        this.eventsList = eventsList;
        this.uniqueActivities = new ArrayList<String>();
        this.tracesList = new HashMap<String, Trace>();
        this.processLog = new HashMap<ArrayList<String>, Integer>();
        
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
        System.out.println("Unique activities are: " + uniqueActivities.toString());
    }

    public void generateTraces() //First requirement of the assignment is multi-set of traces L which means duplicate events are allowed. This is the method.
    {
        //Time to find the start and end event of each case. I will assume each ticket is a case.
        //Looking at my csv file, I already know Open and Closed are my start and end events respectively.
        //But there is also a small chance that open and closed may not be start and end events. To be sure, I will make the method generic and just determine what they really are.
        //I am going to use the HashMapUH

        HashMap<String, Event> startEvents = new HashMap<String, Event>(); //Key will be ticket number (case ID or trace)
        HashMap<String, Event> endEvents = new HashMap<String, Event>();

        System.out.println("Generating traces");

        for (int i = 0; i < eventsList.size(); i++)
        {
            String ticketNumber = eventsList.get(i).getTicketNumber();

            //As soon as I encounter a ticket number, it will check if it exists. If it does not, the start event is found
            startEvents.putIfAbsent(ticketNumber, eventsList.get(i)); //Man this made things so much easier

            endEvents.put(ticketNumber, eventsList.get(i)); //For the specific ticket number, the end event will be updated until the last event is reached

        }

        System.out.println("Generated Start and End Events for Each Trace");

        for (String ticketNumber: startEvents.keySet()) //Creating the trace
        {
            Event startEvent = startEvents.get(ticketNumber); //Start event is TI: Task Initiation
            Event endEvent = endEvents.get(ticketNumber); //End event is TO: Task Output

            Trace trace = new Trace(ticketNumber, startEvent, endEvent);
            // tracesList.add(trace);
            //Instead, I will add all traces to HashMap
            tracesList.put(ticketNumber, trace);
        }

        System.out.println("Used the Case ID, Start and End Events to create traces (objects)");

        //The following two loops managed to be highly inefficient, took minutes to produce an output
        // for (int i = 0; i < eventsList.size(); i++) //Also adding the events to the traces
        // {
        //     System.out.println("hello");
        //     String ticketNumber = eventsList.get(i).getTicketNumber();

        //     for (int j = 0; j < tracesList.size(); j++)
        //     {
        //         if (tracesList.get(j).getCaseID().equals(ticketNumber))
        //         {
        //             tracesList.get(j).addEvent(eventsList.get(i));
        //         }
        //     }
        // }

        //It is HASHMAPUH time, I will eliminate the second loop

        for (int i = 0; i < eventsList.size(); i = i + 1)
        {
            String ticketNumber = eventsList.get(i).getTicketNumber();
            tracesList.get(ticketNumber).addEvent(eventsList.get(i));
        }



        
        System.out.println("The traces are: ");

        for (String ticketNumber: tracesList.keySet())
        {
            System.out.println("For Ticket Number: " + ticketNumber); //I will include a method to print events of traces in trace class
            tracesList.get(ticketNumber).printEvents();
        }

        System.out.println("Successfully generated: " + tracesList.size() + " traces");
        
    }

    public void printTIAndTO() //This is third requirement of assignment. This will print out the start and end events of each trace (TI and TO)
    {
        for (String ticketNumber: tracesList.keySet())
        {
            System.out.println("For Ticket Number: " + ticketNumber);
            System.out.println("TI: " + tracesList.get(ticketNumber).getStartEvent().getStatus() + " at " + tracesList.get(ticketNumber).getStartEvent().getTime() + " by " + tracesList.get(ticketNumber).getStartEvent().getOwner() + " in " + tracesList.get(ticketNumber).getStartEvent().getGroupName());
            System.out.println("TO: " + tracesList.get(ticketNumber).getEndEvent().getStatus() + " at " + tracesList.get(ticketNumber).getEndEvent().getTime() + " by " + tracesList.get(ticketNumber).getEndEvent().getOwner() + " in " + tracesList.get(ticketNumber).getEndEvent().getGroupName());
        }
    }

    public void generateProcessLog() //This is the fourth requirement of the assignment. This will generate the process log
    {
        //I am going to take advantage of the HashMAPUH. Instead of comparing arraylists in each trace, I will just use the arraylist as key. Key is always unique
        //I feel like an evil villain for this one.

        int index_for_process_log = 1;

        for (String TicketNumber: tracesList.keySet())
        {
            tracesList.get(TicketNumber).populateStatusList();
            ArrayList<String> statusList = tracesList.get(TicketNumber).getStatusList();
            System.out.println(statusList.toString());
            processLog.put(statusList, index_for_process_log++);
        }

        
        System.out.println("The Process Log is: ");

        int counter = 0;

        for (ArrayList<String> statusList: processLog.keySet()) //Now, I will print each of the events list
        {
            System.out.println("Unique Trace: " + counter + 1);
            counter++;
            
            for (int i = 0; i < statusList.size(); i++)
            {
                System.out.println("Event " + (i + 1) + ": " + statusList.get(i));
            }
        }

        System.out.println("Successfully generated: " + processLog.size() + " unique traces in the process log");

    }

    
}
