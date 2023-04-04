public class AutoBuyer {
    private String firstName;
    private String lastName;
    private String action;
    private int flightID;
    private String originAirport;
    private String destinationAirport;
    private String ticketType;
    private int ticketQuantity;

    public AutoBuyer(){

    }

    public AutoBuyer(String firstName2, String lastName2, String action2, int flightID2, String originAirport2,
            String destinationAirport2, String ticketType2, int ticketQuantity2) {
                this.firstName = firstName2;
                this.lastName = lastName2;
                this.action = action2;
                this.flightID = flightID2;
                this.originAirport = originAirport2;
                this.destinationAirport = destinationAirport2;
                this.ticketType = ticketType2;
                this.ticketQuantity = ticketQuantity2;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getFlightID() {
        return flightID;
    }

    public void setFlightID(int flightID) {
        this.flightID = flightID;
    }

    public String getOriginAirport() {
        return originAirport;
    }

    public void setOriginAirport(String originAirport) {
        this.originAirport = originAirport;
    }

    public String getDestinationAirport() {
        return destinationAirport;
    }

    public void setDestinationAirport(String destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public int getTicketQuantity() {
        return ticketQuantity;
    }

    public void setTicketQuantity(int ticketQuantity) {
        this.ticketQuantity = ticketQuantity;
    }

    

    
}
