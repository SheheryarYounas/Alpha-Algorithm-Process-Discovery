import java.io.FileReader;
import java.io.IOException;
import com.opencsv.CSVReader; //Use referenced libraries at bottom left to import the library
import com.opencsv.exceptions.CsvValidationException;

import java.util.ArrayList;

class EventLogAnalyzer {

    private ArrayList<Event> eventsList = new ArrayList<Event>();

    //The throws exception is for the while loop line
    public ArrayList<Event> gatherData() throws CsvValidationException 
    {
            //Why does it need double backward slashes???
            String csv_file_location = "C:\\Users\\youni\\Desktop\\FAST\\Process Mining Assignment 3\\Alpha-Algorithm-Process-Discovery\\AnonymizedEventData.csv"; 

            try(FileReader fileReader = new FileReader(csv_file_location);
                CSVReader csv_reader = new CSVReader(fileReader))
            {
                String read_line[];
                Boolean firstLine = true;

                while((read_line = csv_reader.readNext()) != null) //While there is a next line to read
                {
                    //To skip the first row as it only contains column names, I am adding continue here
                    if (firstLine)
                    {
                        firstLine = false;
                        continue;
                    }

                    for (int i = 0; i < read_line.length; i = i + 1)
                    {
                        //Now, I am gonna read each column separately so they are in separate arrays
                        //For future use, modify the below variables to the column names of the csv file
                        
                        String ticketNumberTemp = read_line[0];
                        String statusTemp = read_line[1];
                        String timeTemp = read_line[2];
                        String groupNameTemp = read_line[3];
                        String owner = read_line[4];
                        
                        //I will take the values and create event objects
                        Event event = new Event(ticketNumberTemp, statusTemp, timeTemp, groupNameTemp, owner);
                        eventsList.add(event);  
                    }
                }

            } 
            
            catch (IOException e)
            {
                e.printStackTrace();
            }

            
            return eventsList;
    }
}