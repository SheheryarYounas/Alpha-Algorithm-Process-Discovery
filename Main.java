import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFrame;

public class Main {
    //Including the exception again for the while loop here
    //The opencsv csvReader library is dependent on Apache Commons Lang Library or specifically the ObjectUtils class, so downloaded that jar file as well
    public static void main(String[] args) throws Exception
    {
        ArrayList<Event> eventsList = new ArrayList<Event>();
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to the Alpha Algorithm Program");
        System.out.println("This program will be used for process discovery");

        EventLogAnalyzer eventloganalyzer = new EventLogAnalyzer();
        eventsList = eventloganalyzer.gatherData();
        
        System.out.println("Successfully acquired: " + eventsList.size() + " events");
        
        AlphaAlgorithm alphaAlgorithm = new AlphaAlgorithm(eventsList);
        
        int option = 0;

        do
        {
            System.out.println("Please select an option: ");
            System.out.println("1. Generate and print multi-set of traces");
            System.out.println("2. Generate and print Task Log");
            System.out.println("3. Print TI and TO of each trace");
            System.out.println("4. Generate and print Process Log");
            System.out.println("5. Generate and print Frequency Log");
            System.out.println("6. Output the resultant process");

            option = input.nextInt();

            if (option == 1)
            {
                alphaAlgorithm.generateTraces();
            }

            else if (option == 2)
            {
                alphaAlgorithm.findUniqueActivities();
            }

            else if (option == 3)
            {
                alphaAlgorithm.printTIAndTO();
            }

            else if (option == 4)
            {
                alphaAlgorithm.generateProcessLog();
            }

            else if (option == 5)
            {
                alphaAlgorithm.generateFrequencyLog();
            }

            else if (option == 6)
            {
                GraphGenerator graphGenerator = new GraphGenerator();
        JFrame frame = new JFrame("Process Flow Chart");
        frame.add(graphGenerator);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 1200);
        frame.setVisible(true);
            }

            else if (option == 7)
            {
                System.out.println("Exiting Program");
            }

            else
            {
                System.out.println("Invalid Option");
            }

        } while (option != 7);
    }
}