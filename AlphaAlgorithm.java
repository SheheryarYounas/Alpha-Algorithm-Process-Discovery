import java.util.ArrayList;

public class AlphaAlgorithm {

    private ArrayList<Event> eventsList;
    private ArrayList<String> uniqueActivities;

    public AlphaAlgorithm(ArrayList<Event> eventsList)
    {
        this.eventsList = eventsList;
        this.uniqueActivities = new ArrayList<String>();
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
    
}
