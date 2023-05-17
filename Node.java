import java.util.ArrayList;

public class Node {
    private String status;
    private ArrayList<String> previousStatuses;
    private ArrayList<String> nextStatuses;

    public Node(String status)
    {
        this.status = status;
        previousStatuses = new ArrayList<String>();
        nextStatuses = new ArrayList<String>();
    }

    public String getStatus()
    {
        return status;
    }

    public void setPreviousStatus(ArrayList<String> previousStatus)
    {
        this.previousStatuses = previousStatus;
    }

    public ArrayList<String> getPreviousStatus()
    {
        return previousStatuses;
    }

    public void setNextStatus(ArrayList<String> nextStatus)
    {
        this.nextStatuses = nextStatus;
    }

    public ArrayList<String> getNextStatus()
    {
        return nextStatuses;
    }

    public void addNextStatus(String nextStatus)
    {
        nextStatuses.add(nextStatus);
    }

    public void addPreviousStatus(String previousStatus)
    {
        previousStatuses.add(previousStatus);
    }

    


    
}
