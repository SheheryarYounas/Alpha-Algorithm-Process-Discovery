public class Event {
    
    private String ticketNumber;
    private String status;
    private String time;
    private String groupName;
    private String owner;

    public Event(String ticketNumber, String status, String time, String groupName, String owner)
    {
        this.ticketNumber = ticketNumber;
        this.status = status;
        this.time = time;
        this.groupName = groupName;
        this.owner = owner;
    }

    public String getTicketNumber()
    {
        return ticketNumber;
    }

    public String getStatus()
    {
        return status;
    }

    public String getTime()
    {
        return time;
    }

    public String getGroupName()
    {
        return groupName;
    }

    public String getOwner()
    {
        return owner;
    }

    public void setTicketNumber(String ticketNumber)
    {
        this.ticketNumber = ticketNumber;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public void setTime(String time)
    {
        this.time = time;
    }

    public void setGroupName(String groupName)
    {
        this.groupName = groupName;
    }

    public void setOwner(String owner)
    {
        this.owner = owner;
    }

}
