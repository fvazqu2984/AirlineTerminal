public class AutoBuyer {
    private String firstName;
    private String lastName;
    private String action;
    private int flightID;
    private String originAirport;
    private String destinationAirport;
    private String ticketType;
    private int ticketQuantity;

    /**
     * Autobuyer constructor
     */
    public AutoBuyer(){

    }

    /**
     * Constructor to assign values to autobuyer objects
     * @param firstName2
     * @param lastName2
     * @param action2
     * @param flightID2
     * @param originAirport2
     * @param destinationAirport2
     * @param ticketType2
     * @param ticketQuantity2
     */
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

    /**
     * 
     * @return Gets first name
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * 
     * @param firstName Sets first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * 
     * @return Gets last name
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * 
     * @param lastName Sets last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * 
     * @return Gets action to be performed
     */
    public String getAction() {
        return this.action;
    }

    /**
     * 
     * @param action Sets action to be performed
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * 
     * @return Gets flightID
     */
    public int getFlightID() {
        return this.flightID;
    }

    /**
     * 
     * @param flightID Sets flightID
     */
    public void setFlightID(int flightID) {
        this.flightID = flightID;
    }

    /**
     * 
     * @return Gets origin airport
     */
    public String getOriginAirport() {
        return this.originAirport;
    }

    /**
     * 
     * @param originAirport Sets origin airpot
     */
    public void setOriginAirport(String originAirport) {
        this.originAirport = originAirport;
    }

    /**
     * 
     * @return Gets destination airport
     */
    public String getDestinationAirport() {
        return this.destinationAirport;
    }

    /**
     * 
     * @param destinationAirport Sets destination airport
     */
    public void setDestinationAirport(String destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    /**
     * 
     * @return Gets ticket type
     */
    public String getTicketType() {
        return this.ticketType;
    }

    /**
     * 
     * @param ticketType sets ticket type
     */
    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    /**
     * 
     * @return gets ticket quantity
     */
    public int getTicketQuantity() {
        return this.ticketQuantity;
    }

    /**
     * 
     * @param ticketQuantity sets ticket quantity
     */
    public void setTicketQuantity(int ticketQuantity) {
        this.ticketQuantity = ticketQuantity;
    }

}
