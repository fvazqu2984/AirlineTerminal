import java.util.HashMap;

public class Ticket {
    private int ticketQuantity;
    private int seatsPurchased;
    private double totalPrice;
    private int confirmationNumber;
    private int flightID;
    private String userName;
    private String seatType;

    /**
     * Ticket Constructor
     */
    public Ticket(){

    }

    /**
     * Constructor to assign values to customer ticket object
     * @param confirmationNumber2
     * @param flightID2
     * @param ticketQuantity2
     * @param seatsPurchased2
     * @param seatType2
     * @param totalPrice2
     */
    public Ticket(int confirmationNumber2, int flightID2, int ticketQuantity2, int seatsPurchased2, String seatType2, double totalPrice2) {
        this.confirmationNumber = confirmationNumber2;
        this.flightID = flightID2;
        this.ticketQuantity = ticketQuantity2;
        this.seatsPurchased = seatsPurchased2;
        this.seatType = seatType2;
        this.totalPrice = totalPrice2;

    }

    /**
     * Constructor to assign values to employee ticket objects
     * @param confirmationNumber2
     * @param flightID2
     * @param userName2
     * @param seatsPurchased2
     * @param seatType2
     * @param totalPrice2
     */
    public Ticket(int confirmationNumber2, int flightID2, String userName2, int seatsPurchased2, String seatType2, double totalPrice2) {
        this.confirmationNumber = confirmationNumber2;
        this.flightID = flightID2;
        this.seatsPurchased = seatsPurchased2;
        this.seatType =seatType2;
        this.totalPrice = totalPrice2;
        this.userName = userName2;
    }

    /**
     * 
     * @return gets ticket username
     */
    public String getUserName(){
        return this.userName;
    }

    /**
     * 
     * @param userNameIn sets ticket username
     */
    public void setUserName(String userNameIn){
        this.userName = userNameIn;
    }

    /**
     * 
     * @param ticketQuantityIn sets ticket quantity
     */
    public void setTicketQuantity(int ticketQuantityIn){
        this.ticketQuantity = ticketQuantityIn;
    }

    /**
     * 
     * @return gets ticket quantity
     */
    public int getTicketQuantity(){
        return this.ticketQuantity;
    }

    /**
     * 
     * @param seatsPurchasedIn sets seats purchased per ticket
     */
    public void setSeatsPurchases(int seatsPurchasedIn){
        this.seatsPurchased = seatsPurchasedIn;
    }

    /**
     * 
     * @return gets seats purchased per ticket
     */
    public int getSeatsPurchased(){
        return this.seatsPurchased;
    }

    /**
     * 
     * @param totalPriceIn sets total price for tickets
     */
    public void setTotalPrice(double totalPriceIn){
        this.totalPrice = totalPriceIn;
    }

    /**
     * 
     * @return gets total price for tickets
     */
    public double getTotalPrice(){
        return this.totalPrice;
    }

    /**
     * 
     * @param confirmationNumberIn sets confirmation number for ticket
     */
    public void setConfirmationNumber(int confirmationNumberIn){
        this.confirmationNumber = confirmationNumberIn;
    }

    /**
     * 
     * @return gets confirmation number for ticket
     */
    public int getConfirmationNumber(){
        return this.confirmationNumber;
    }

    /**
     * 
     * @param flightIDIn sets flight ID belonging to ticket
     */
    public void setFlightID(int flightIDIn){
        this.flightID = flightIDIn;
    }

    /**
     * 
     * @return gets flight ID belonging to ticket
     */
    public int getFlightID(){
        return this.flightID;
    }

    /**
     * 
     * @return gets seat type for ticket
     */
    public String getSeatType() {
        return seatType;
    }

    /**
     * 
     * @param seatType sets seat type for ticket
     */
    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    /**
     * Method that prints ticket information
     */
    public void printTicket(){
        System.out.println("Confirmation: #" + getConfirmationNumber());
        System.out.println("Flight ID: " + getFlightID());
        System.out.println("Tickets quantity : " + getTicketQuantity());
        System.out.println("Seats Purchased: " + getSeatsPurchased());
        System.out.println("Total Price: $" + getTotalPrice());
    }

    /**
     * Method that prints all available information for tickets
     */
    public void printTicketList(){
        System.out.println("Confirmation: #" + getConfirmationNumber());
        System.out.println("Flight ID: " + getFlightID());
        System.out.println("userName: " + getUserName());
        System.out.println("Seats Purchased: " + getSeatsPurchased());
        System.out.println("Seat Type: " + getSeatType());

        System.out.println("Total Price: " + getTotalPrice());
    }

    /**
     * Method that creates ticket map for customers
     * @param confirmationNumber Ticket Number
     * @param flightID ID of flight
     * @param ticketQuantity Number of tickets bought
     * @param seatsPurchased Number of seats bought
     * @param seatType Type of seats
     * @param totalPrice Total price of tickets
     * @return
     */
    public static HashMap<Integer,Ticket> makeTicketMap(int confirmationNumber, int flightID, int ticketQuantity, int seatsPurchased, String seatType, double totalPrice){

        HashMap<Integer,Ticket>  ticketMap = new HashMap<>();

        ticketMap.put(confirmationNumber, new Ticket(confirmationNumber, flightID, ticketQuantity, seatsPurchased, seatType, totalPrice));

        return ticketMap;
    }

}