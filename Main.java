import java.util.ArrayList;

public class Main {
    //Including the exception again for the while loop here
    //The opencsv csvReader library is dependent on Apache Commons Lang Library or specifically the ObjectUtils class, so downloaded that jar file as well
    public static void main(String[] args) throws Exception
    {
        ArrayList<Event> eventsList = new ArrayList<Event>();

        System.out.println("Welcome to the Alpha Algorithm Program");
        System.out.println("This program will be used for process discovery");

        EventLogAnalyzer eventloganalyzer = new EventLogAnalyzer();
        eventsList = eventloganalyzer.gatherData();
        
        System.out.println("Successfully acquired: " + eventsList.size() + " events");
        
        AlphaAlgorithm alphaAlgorithm = new AlphaAlgorithm(eventsList);
        alphaAlgorithm.findUniqueActivities();
        alphaAlgorithm.generateTraces();
    }
}