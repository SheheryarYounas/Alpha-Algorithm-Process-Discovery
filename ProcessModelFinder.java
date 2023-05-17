import javax.swing.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.awt.Font;
import java.util.Set;
import java.util.HashSet;


public class ProcessModelFinder extends JPanel { //JPanel will be my container

    private HashMap<String, Trace> tracesList;
    private ArrayList<String> uniqueActivities;
    Set<String> setDirectSuccessors;
    Set<String> setCausality;
    Set<String> setParallel;
    Set<String> setExclusive;

    public ProcessModelFinder(HashMap<String, Trace> tracesList, ArrayList<String> uniqueActivities)
    {
        this.tracesList = tracesList;
        this.uniqueActivities = uniqueActivities;
        this.setDirectSuccessors = new HashSet<String>();
        this.setCausality = new HashSet<String>();
        this.setParallel = new HashSet<String>();
        this.setExclusive = new HashSet<String>();
    }

    public void findDirectSuccessors()
    {

        for (Trace trace: tracesList.values())
        {
            ArrayList<String> statusList = trace.getStatusList();

            for (int i = 0; i < statusList.size() - 1; i = i + 1)
            {
                String activity_pair_1 = statusList.get(i);
                String activity_pair_2 = statusList.get(i + 1);

                String activity_pair = activity_pair_1 + " -> " + activity_pair_2;
                setDirectSuccessors.add(activity_pair);
            }
        }
    }

    public void findCausalityAndParallel()
    {
        
        
        for (String activity_pair: setDirectSuccessors)
        {
            String[] activities = activity_pair.split(" -> ");
            String activity_1 = activities[0];
            String activity_2 = activities[1];
            String reverse = activity_2 + " -> " + activity_1;

            if (!setDirectSuccessors.contains(reverse))
            {
                setCausality.add(activity_pair);
            }

            else if (setDirectSuccessors.contains(reverse))
            {
                setParallel.add(activity_pair);
            }

            

        }
    }

    public void findExclusive()
    {
        for (String activity: uniqueActivities)
        {
            boolean isExclusive = true;
            for (String activity_pair: setDirectSuccessors)
            {
                String[] activities = activity_pair.split(" -> ");
                String activity_1 = activities[0];
                String activity_2 = activities[1];

                if (activity_1.equals(activity) || activity_2.equals(activity))
                {
                    isExclusive = false;
                    break;
                }
            }

            if (isExclusive)
            {
                setExclusive.add(activity);
            }
        }
    }

    public void testPrint()
    {
        System.out.println(setDirectSuccessors);
        System.out.println("Found " + setDirectSuccessors.size() + " direct successors");
        findCausalityAndParallel();

        System.out.println(setCausality);
        System.out.println("Found " + setCausality.size() + " causality");

        System.out.println(setParallel);
        System.out.println("Found " + setParallel.size() + " parallel");

        findExclusive();
        System.out.println(setExclusive);
      
        if (setExclusive.size() == 0)
        {
            System.out.println("No exclusive found");
        }

        else if (setExclusive.size() == 1)
        {
            System.out.println("Found " + setExclusive.size() + " exclusive");
        }

        

        printPetriNet();

    }

    public void printPetriNet() {
        System.out.println("Places:");
        for (String activity : uniqueActivities) {
            System.out.println(activity);
        }
    
        System.out.println("\nTransitions:");
        for (String activityPair : setDirectSuccessors) {
            String[] activities = activityPair.split(" -> ");
            System.out.println(activities[0] + " -> " + activities[1]);
        }
    
        System.out.println("\nArcs:");
        for (String activityPair : setCausality) {
            String[] activities = activityPair.split(" -> ");
            System.out.println(activities[0] + " -> " + activities[1]);
        }
        for (String activityPair : setParallel) {
            String[] activities = activityPair.split(" -> ");
            System.out.println(activities[0] + " -> " + activities[1]);
        }
    }

    // private void drawProcessFlowChart(Graphics graphic)
    // {
    //     //Experimenting by drawing activity of the first trace

    //     int x = 10; //x coordinate of the rectangle
    //     int y = 10; //y coordinate of the rectangle
    //     int width = 275; //width of the rectangle
    //     int height = 25; //height of the rectangle
    //     int horizontalSpacing = 10;
    //     int verticalSpacing = 10;
    //     Font font = new Font("TimesRoman", Font.PLAIN, 20);

    //     graphic.setFont(font);
    //         ArrayList<String> statusList = new ArrayList<String>();
    //         statusList.add("Start");
    //         statusList.add("Ferrari");
    //         statusList.add("Closed");
    //         System.out.println(statusList.toString());
    //         for (int i = 0; i < statusList.size(); i++)
    //         {
                
    //             int rectY = y; //Y coordinate of rectangle
    //             graphic.drawRect(20, rectY, width, height);
                
    //             String text = statusList.get(i);  // text to display
                
    //             //Getting half of the height of font to center the text vertically
    //             int textY = rectY + (height / 2) + (graphic.getFontMetrics().getHeight() / 2) - 3;  // calculate the y position for the text
    //             graphic.drawString(text, 100, textY);  // draw the text

    //             int arrow_x = x + width/2; //At half of the rectangle
    //             int arrow_y = rectY + height; //At the bottom of the rectangle
    //             int arrow_end_x = arrow_x; //Arrow is straight so its end x coordinate is same as its start x coordinate
    //             int arrow_end_y = arrow_y + verticalSpacing; //Arrow is straight so its end y coordinate is same as its start y coordinate + vertical spacing
    //             graphic.drawLine(arrow_x, arrow_y, arrow_end_x, arrow_end_y); //Draw the arrow
    //             graphic.drawLine(arrow_end_x, arrow_end_y, arrow_end_x - 5, arrow_end_y - 5); //Draw the arrow tip to the left
    //             graphic.drawLine(arrow_end_x, arrow_end_y, arrow_end_x + 5, arrow_end_y - 5); //Draw the arrow tip to the right



    //             y += height + verticalSpacing;
    //         }

        
    //}




}
