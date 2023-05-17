import javax.swing.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.awt.Font;
import java.util.Set;
import java.util.HashSet;


public class GraphGenerator extends JPanel { //JPanel will be my container

    private HashMap<String, Trace> tracesList;
    private HashMap<String, Node> nodeList;

    
    public GraphGenerator(HashMap<String, Trace> tracesList)
    {
        this.tracesList = tracesList;
        nodeList = new HashMap<String, Node>();
    }

    public GraphGenerator()
    {
        //Empty constructor
    }

    public void findSequence()
    {
        for (Trace trace: tracesList.values())
        {
             ArrayList<String> statusList = trace.getStatusList();

             for (int i = 0; i < statusList.size() - 1; i++)
             {
                if (nodeList.containsKey(statusList.get(i)))
                {
                    if (i == 0)
                    {
                        nodeList.get(statusList.get(i)).addNextStatus(statusList.get(i+1));
                    }

                    else
                    {
                        nodeList.get(statusList.get(i)).addNextStatus(statusList.get(i+1));
                        nodeList.get(statusList.get(i)).addPreviousStatus(statusList.get(i-1));
                    }
                }

                else
                {
                    Node node = new Node(statusList.get(i));
                    nodeList.put(statusList.get(i), node);
                    if (i == 0)
                    {
                        nodeList.get(statusList.get(i)).addNextStatus(statusList.get(i+1));
                    }

                    else
                    {
                        nodeList.get(statusList.get(i)).addNextStatus(statusList.get(i+1));
                        nodeList.get(statusList.get(i)).addPreviousStatus(statusList.get(i-1));
                    }
                }
             }
        }

        removeDuplicates();

    }

    public void removeDuplicates()
    {
        for (Node node: nodeList.values())
        {
            ArrayList<String> nextStatuses = node.getNextStatus();
            ArrayList<String> previousStatuses = node.getPreviousStatus();

            Set<String> set = new HashSet<String>(nextStatuses); //This will remove duplicates by itself
            nextStatuses.clear(); //Clear the original list
            nextStatuses.addAll(set); //Hand the list back without duplicates

            set = new HashSet<String>(previousStatuses); //This will remove duplicates by itself
            previousStatuses.clear(); //Clear the original list
            previousStatuses.addAll(set); //Hand the list back without duplicates

        }
    }

    public void testPrint()
    {
        System.out.println(nodeList.get("Open").getNextStatus());
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
    //         statusList.add("Pepsid him");
    //         statusList.add("Balled him");
    //         statusList.add("Called him");
    //         statusList.add("Dolled him");
    //         statusList.add("Eat pepsi");
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
