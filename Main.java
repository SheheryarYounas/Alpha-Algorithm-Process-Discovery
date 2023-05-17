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
            System.out.println("Please select an option (Do the steps in order): ");
            System.out.println("1. Generate and print multi-set of traces (Requirement 1: L)");
            System.out.println("2. Generate and print Task Log (Requirement 4)(Unique Activities that represent places PL in the process model)");
            System.out.println("3. Print TI and TO of each trace (Requirement 3)");
            System.out.println("4. Generate and print Process Log (Requirement 2)(TL: Unique Traces)");
            System.out.println("5. Generate and print Frequency Log");
            System.out.println("6. Output the Direct Successors, Causality, Parallel and Exclusive Relations (Requirement 5: FL or Arcs in the process model)");
            System.out.println("7. Exit Program");

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
               alphaAlgorithm.passTracesToGraphGenerator();
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