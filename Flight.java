import java.util.HashMap;

public class Flight {

    //Attributes
    private int id;
    private int flightNumber;
    private String originAirport;
    private String originCode;
    private String destinationAirport;
    private String destinationCode;
    private String departureDate;
    private String departureTime;
    private double firstClassPrice;
    private double businessClassPrice;
    private double mainCabinPrice;
    private String arrivalDate;
    private String arrivalTime;
    private int duration;
    private int distance;
    private int timeZoneDifference;
    private int firstClassSeats;
    private int businessClassSeats;
    private int mainCabinSeats;
    private int totalSeats;
    private String flightType;
    private int surcharge;
    private boolean foodServed;
    private int routeCost;
    private int minerPoints;
    private String originAirportCity;
    private String originAirportState;
    private String originAirportCountry;
    private double originAirportFee;
    private boolean originAirportLounge;
    private String destinationAirportCity;
    private String destinationAirportState;
    private String destinationAirportCountry;
    private double destinationAirportFee;
    private boolean destinationAirportLounge;
    private int firstClassSeatsSold;
    private int businessClassSeatsSold;
    private int mainCabinSeatsSold;
    private double totalFirstClassRevenue;
    private double totalBusinessClassRevenue;
    private double totalMainCabinRevenue;
    private double flightTotalTax;
    private double flightTotalAirlineFee;
    private double flightTotalSecurityFee;
    private double savings;

    /**
     * Constructor Flight
     */
    public Flight(){}

    /**
     * Constructor to assign values to flight objects
     * @param id2
     * @param flightNumber2
     * @param originAirport2
     * @param originCode2
     * @param destinationAirport2
     * @param destinationCode2
     * @param departingDate
     * @param departingTime
     * @param duration2
     * @param distance2
     * @param timeZoneDifference2
     * @param arrivalDate2
     * @param arrivalTime2
     * @param flightType2
     * @param surcharge2
     * @param foodServed2
     * @param routeCost2
     * @param minerPoints2
     * @param totalSeats2
     * @param firstClassSeats2
     * @param businessClassSeats2
     * @param mainCabinSeats2
     * @param firstClassPrice2
     * @param businessClassPrice2
     * @param mainCabinPrice2
     * @param originAirportCity2
     * @param originAirportState2
     * @param originAirportCountry2
     * @param originAirportFee2
     * @param originAirportLounge2
     * @param destinationAirportCity2
     * @param destinationAirportState2
     * @param destinationAirportCountry2
     * @param destinationAirportFee2
     * @param destinationAirportLounge2
     */
    public Flight(int id2, int flightNumber2, String originAirport2, String originCode2, String destinationAirport2,
            String destinationCode2, String departingDate, String departingTime, int duration2, int distance2,
            int timeZoneDifference2, String arrivalDate2, String arrivalTime2, String flightType2, int surcharge2,
            boolean foodServed2, int routeCost2, int minerPoints2, int totalSeats2, int firstClassSeats2,
            int businessClassSeats2, int mainCabinSeats2, int firstClassPrice2, int businessClassPrice2,
            int mainCabinPrice2, String originAirportCity2, String originAirportState2, String originAirportCountry2,
            double originAirportFee2, boolean originAirportLounge2, String destinationAirportCity2,
            String destinationAirportState2, String destinationAirportCountry2, double destinationAirportFee2,
            boolean destinationAirportLounge2) {
                this.id = id2;
            this.flightNumber = flightNumber2;
            this.originAirport = originAirport2;
            this.originCode = originCode2;
            this.destinationAirport = destinationAirport2;
            this.destinationCode = destinationCode2;
            this.departureDate = departingDate;
            this.departureTime = departingTime;
            this.duration = duration2;
            this.distance = distance2;
            this.timeZoneDifference = timeZoneDifference2;
            this.arrivalDate = arrivalDate2;
            this.arrivalTime = arrivalTime2;
            this.flightType = flightType2;
            this.surcharge = surcharge2;
            this.foodServed = foodServed2;
            this.routeCost = routeCost2;
            this.minerPoints = minerPoints2;
            this.totalSeats = totalSeats2;
            this.firstClassSeats = firstClassSeats2;
            this.businessClassSeats = businessClassSeats2;
            this.mainCabinSeats = mainCabinSeats2;
            this.firstClassPrice = firstClassPrice2;
            this.businessClassPrice = businessClassPrice2;
            this.mainCabinPrice = mainCabinPrice2;
            this.originAirportCity = originAirportCity2;
            this.originAirportState = originAirportState2;
            this.originAirportCountry = originAirportCountry2;
            this.originAirportFee = originAirportFee2;
            this.originAirportLounge = originAirportLounge2;
            this.destinationAirportCity = destinationAirportCity2;
            this.destinationAirportState = destinationAirportState2;
            this.destinationAirportCountry = destinationAirportCountry2;
            this.destinationAirportFee =destinationAirportFee2;
            this.destinationAirportLounge = destinationAirportLounge2;

    }

    //Setters and Getters

    /**
     * 
     * @param idIn sets the flight ID
     */
    public void setFlightID(int idIn){
        this.id = idIn;
    }

    /**
     * 
     * @return gets flight ID
     */
    public int getFlightID(){
        return this.id;
    }

    /**
     * 
     * @param flightNumberIn sets flight number
     */
    public void setFlightNumber(int flightNumberIn){
        this.flightNumber = flightNumberIn;
    }

    /**
     * 
     * @return gets flight number
     */
    public int getFlightNumber(){
        return this.flightNumber;
    }
    
    /**
     * 
     * @param originAirportIn sets origin airport
     */
    public void setOriginAirport(String originAirportIn){
        this.originAirport = originAirportIn;
    }

    /**
     * 
     * @return gets origin airport
     */
    public String getOriginAirport(){
        return this.originAirport;
    }

    /**
     * 
     * @param originCodeIn sets origin code
     */
    public void setOriginCode(String originCodeIn){
        this.originCode = originCodeIn;
    }

    /**
     *     
     * @return gets origin code
     */
    public String getOriginCode(){
        return this.originCode;
    }

    /**
     * 
     * @param destinationAirportIn sets destination airport
     */
    public void setDestinationAirport(String destinationAirportIn){
        this.destinationAirport = destinationAirportIn;
    }

    /**
     * 
     * @return gets destination airport
     */
    public String getDestinationAirport(){
        return this.destinationAirport;
    }

    /**
     * 
     * @param destinationCodeIn sets destination code
     */
    public void setDestinationCode(String destinationCodeIn){
        this.destinationCode = destinationCodeIn;
    }

    /**
     * 
     * @return gets destiantion code
     */
    public String getDestinationCode(){
        return this.destinationCode;
    }

    /**
     * 
     * @param departureDateIn sets departure date
     */
    public void setDepartureDate(String departureDateIn){
        this.departureDate = departureDateIn;
    }

    /**
     * 
     * @return gets departure date
     */
    public String getDepartureDate(){
        return this.departureDate;
    }

    /**
     * 
     * @param departureTimeIn sets departure time
     */
    public void setDepartureTime(String departureTimeIn){
        this.departureTime = departureTimeIn;
    }

    /**
     * 
     * @return gets departure time
     */
    public String getDepartureTime(){
        return this.departureTime;
    }

    /**
     * 
     * @param arrivalDateIn set arrival date
     */
    public void setArrivalDate(String arrivalDateIn){
        this.arrivalDate = arrivalDateIn;
    }

    /**
     * 
     * @return gets arrival date
     */
    public String getArrivalDate(){
        return this.arrivalDate;
    }

    /**
     * 
     * @param arrivalTimeIn sets arrival time
     */
    public void setArrivalTime(String arrivalTimeIn){
        this.arrivalTime = arrivalTimeIn;
    }

    /**
     * 
     * @return gets arrival time
     */
    public String getArrivalTime(){
        return this.arrivalTime;
    }

    /**
     * 
     * @param firstClassPriceIn sets first class price
     */
    public void setFirstClassPrice(int firstClassPriceIn){
        this.firstClassPrice = firstClassPriceIn;
    }

    /**
     * 
     * @return gets first class price
     */
    public double getFirstClassPrice(){
        return this.firstClassPrice;
    }

    /**
     * 
     * @param businessClassPriceIn sets bussiness class price
     */
    public void setBusinessClassPrice(int businessClassPriceIn){
        this.businessClassPrice = businessClassPriceIn;
    }

    /**
     * 
     * @return gets bussiness class price
     */
    public double getBusinessClassPrice(){
        return this.businessClassPrice;
    }

    /**
     * 
     * @return get flight type
     */
    public String getFlightType() {
        return this.flightType;
    }

    /**
     * 
     * @param flightTypeIn sets flight type
     */
    public void setFlightType(String flightTypeIn) {
        this.flightType = flightTypeIn;
    }

    /**
     * 
     * @return gets surcharge
     */
    public int getSurcharge() {
        return this.surcharge;
    }

    /**
     * 
     * @param surchargeIn sets surcharge
     */
    public void setSurcharge(int surchargeIn) {
        this.surcharge = surchargeIn;
    }

    /**
     * 
     * @return gets if food is served
     */
    public boolean getFoodServed() {
        return this.foodServed;
    }

    /**
     * 
     * @param foodServedIn sets if food is served
     */
    public void setFoodServed(boolean foodServedIn) {
        this.foodServed = foodServedIn;
    }

    /**
     * 
     * @return gets route cost
     */
    public int getRouteCost() {
        return this.routeCost;
    }

    /**
     * 
     * @param routeCostIn  sets route cost
     */
    public void setRouteCost(int routeCostIn) {
        this.routeCost = routeCostIn;
    }

    /**
     * 
     * @return gets miner points
     */
    public int getMinerPoints() {
        return this.minerPoints;
    }

    /**
     * 
     * @param minerPoints sets miner points
     */
    public void setMinerPoints(int minerPoints) {
        this.minerPoints = minerPoints;
    }

    /**
     * 
     * @param mainCabinPriceIn sets main cabin price
     */
    public void setMainCabinPrice(double mainCabinPriceIn){
        this.mainCabinPrice = mainCabinPriceIn;
    }

    /**
     * 
     * @return gets main cabin price
     */
    public double getMainCabinPrice(){
        return this.mainCabinPrice;
    }

    /**
     * 
     * @return gets first class seats
     */
    public int getFirstClassSeats(){
        return this.firstClassSeats;
    }

    /**
     * 
     * @param firstClassSeatsIn sets first class seats
     */
    public void setFirstClassSeats(int firstClassSeatsIn){
        this.firstClassSeats = firstClassSeatsIn;
    }

    /**
     * 
     * @return gets bussiness class seats
     */
    public int getBusinessClassSeats(){
        return this.businessClassSeats;
    }

    /**
     * 
     * @param bussinessClassSeatsIn sets bussiness class seats
     */
    public void setBusinessClassSeats(int bussinessClassSeatsIn){
        this.businessClassSeats = bussinessClassSeatsIn;
    }

    /**
     * 
     * @return gets main cabin seats
     */
    public int getMainCabinSeats(){
        return this.mainCabinSeats;
    }

    /**
     * 
     * @param mainCabinSeatsIn sets main cabin seats
     */
    public void setMainCabinSeats(int mainCabinSeatsIn){
        this.mainCabinSeats = mainCabinSeatsIn;
    }

    /**
     * 
     * @return gets total seats
     */
    public int getTotalSeats(){
        return this.totalSeats;
    }

    /**
     * 
     * @param totalSeatsIn sets total seats
     */
    public void setTotalSeats(int totalSeatsIn){
        this.totalSeats = totalSeatsIn;
    }

    //Only Getters

    /**
     * 
     * @return gets flight id
     */
    public int getID(){
        return this.id;
    }

    /**
     * 
     * @return gets duration
     */
    public int getDuration(){
        return this.duration;
    }

    /**
     * 
     * @return gets distance
     */
    public int getDistance(){
        return this.distance;
    }

    /**
     * 
     * @return gets time zone difference
     */
    public int getTimeZoneDifference(){
        return this.timeZoneDifference;
    }

    /**
     * 
     * @return gets destination airport state
     */
    public String getDestinationAirportState() {
        return this.destinationAirportState;
    }

    /**
     * 
     * @param destinationAirportStateIn sets destination airport state
     */
    public void setDestinationAirportState(String destinationAirportStateIn) {
        this.destinationAirportState = destinationAirportStateIn;
    }

    /**
     * 
     * @return gets origin airport city
     */
    public String getOriginAirportCity() {
        return this.originAirportCity;
    }

    /**
     * 
     * @param originAirportCityIn sets origin airport city
     */
    public void setOriginAirportCity(String originAirportCityIn) {
        this.originAirportCity = originAirportCityIn;
    }

    /**
     * 
     * @return gets origin airport country
     */
    public String getOriginAirportCountry() {
        return this.originAirportCountry;
    }

    /**
     * 
     * @param originAirportCountryIn sets origin airport country
     */
    public void setOriginAirportCountry(String originAirportCountryIn) {
        this.originAirportCountry = originAirportCountryIn;
    }

    /**
     * 
     * @return gets origin airport fee
     */
    public double getOriginAirportFee() {
        return this.originAirportFee;
    }

    /**
     * 
     * @param originAirportFeeIn sets origin airport fee
     */
    public void setOriginAirportFee(double originAirportFeeIn) {
        this.originAirportFee = originAirportFeeIn;
    }

    /**
     * 
     * @return gets destination airport city
     */
    public String getDestinationAirportCity() {
        return this.destinationAirportCity;
    }

    /**
     * 
     * @param destinationAirportCityIn gets destination airport city
     */
    public void setDestinationAirportCity(String destinationAirportCityIn) {
        this.destinationAirportCity = destinationAirportCityIn;
    }

    /**
     * 
     * @return gets destination airport country
     */
    public String getDestinationAirportCountry() {
        return this.destinationAirportCountry;
    }

    /**
     * 
     * @param destinationAirportCountryIn sets destination airport country
     */
    public void setDestinationAirportCountry(String destinationAirportCountryIn) {
        this.destinationAirportCountry = destinationAirportCountryIn;
    }

    /**
     * 
     * @return gets destination airport fee
     */
    public double getDestinationAirportFee() {
        return this.destinationAirportFee;
    }

    /**
     * 
     * @param destinationAirportFeeIn sets destination airport fee
     */
    public void setDestinationAirportFee(double destinationAirportFeeIn) {
        this.destinationAirportFee = destinationAirportFeeIn;
    }

    /**
     * 
     * @return gets if there is destination airport lounge
     */
    public boolean getDestinationAirportLounge() {
        return this.destinationAirportLounge;
    }

    /**
     * 
     * @param destinationAirportLoungeIn sets if there is destination airport lounge
     */
    public void setDestinationAirportLounge(boolean destinationAirportLoungeIn) {
        this.destinationAirportLounge = destinationAirportLoungeIn;
    }

    /**
     * 
     * @return gets origin airport state
     */
    public String getOriginAirportState() {
        return this.originAirportState;
    }

    /**
     * 
     * @param originAirportStateIn sets origin airport state
     */
    public void setOriginAirportState(String originAirportStateIn) {
        this.originAirportState = originAirportStateIn;
    }

    /**
     * 
     * @return gets if there is origin airport lounge
     */
    public boolean getOriginAirportLounge() {
        return this.originAirportLounge;
    }

    /**
     * 
     * @param originAirportLoungeIn sets if there is origin airport lounge
     */
    public void setOriginAirportLounge(boolean originAirportLoungeIn) {
        this.originAirportLounge = originAirportLoungeIn;
    }

    /**
     * 
     * @return gets total first class seats sold
     */
    public int getFirstClassSeatsSold() {
        return this.firstClassSeatsSold;
    }

    /**
     * 
     * @param firstClassSeatsSoldIn sets total first class seats sold
     */
    public void setFirstClassSeatsSold(int firstClassSeatsSoldIn) {
        this.firstClassSeatsSold = firstClassSeatsSoldIn;
    }

    /**
     * 
     * @return gets total bussiness class seats sold
     */
    public int getBusinessClassSeatsSold() {
        return this.businessClassSeatsSold;
    }

    /**
     * 
     * @param businessClassSeatsSoldIn sets total bussiness class seats sold
     */
    public void setBusinessClassSeatsSold(int businessClassSeatsSoldIn) {
        this.businessClassSeatsSold = businessClassSeatsSoldIn;
    }

    /**
     * 
     * @return gets total main cabin seats sold
     */
    public int getMainCabinSeatsSold() {
        return this.mainCabinSeatsSold;
    }

    /**
     * 
     * @param mainCabinSeatsSoldIn sets total main cabin seats sold
     */
    public void setMainCabinSeatsSold(int mainCabinSeatsSoldIn) {
        this.mainCabinSeatsSold = mainCabinSeatsSoldIn;
    }

    /**
     * 
     * @return get total first class revenuew
     */
    public double getTotalFirstClassRevenue() {
        return this.totalFirstClassRevenue;
    }

    /**
     * 
     * @param totalFirstClassRevenueIn sets total first class revenue
     */
    public void setTotalFirstClassRevenue(double totalFirstClassRevenueIn) {
        this.totalFirstClassRevenue = totalFirstClassRevenueIn;
    }

    /**
     * 
     * @return gets total bussiness class revenue
     */
    public double getTotalBusinessClassRevenue() {
        return this.totalBusinessClassRevenue;
    }

    /**
     * 
     * @param totalBusinessClassRevenueIn sets total bussiness class revenue
     */
    public void setTotalBusinessClassRevenue(double totalBusinessClassRevenueIn) {
        this.totalBusinessClassRevenue = totalBusinessClassRevenueIn;
    }

    /**
     * 
     * @return gets total main cabin revenue
     */
    public double getTotalMainCabinRevenue() {
        return this.totalMainCabinRevenue;
    }

    /**
     * 
     * @param totalMainCabinRevenueIn sets total main cabin revenue
     */
    public void setTotalMainCabinRevenue(double totalMainCabinRevenueIn) {
        this.totalMainCabinRevenue = totalMainCabinRevenueIn;
    }

    /**
     * 
     * @return gets flight total tax collected
     */
    public double getFlightTotalTax() {
        return this.flightTotalTax;
    }

    /**
     * 
     * @param flightTotalTaxIn sets flight total tax collected
     */
    public void setFlightTotalTax(double flightTotalTaxIn) {
        this.flightTotalTax = flightTotalTaxIn;
    }

    /**
     * 
     * @return gets flight total airline fee
     */
    public double getFlightTotalAirlineFee() {
        return this.flightTotalAirlineFee;
    }

    /**
     * 
     * @param flightTotalAirlineFeeIn sets flight total airline fee
     */
    public void setFlightTotalAirlineFee(double flightTotalAirlineFeeIn) {
        this.flightTotalAirlineFee = flightTotalAirlineFeeIn;
    }

    /**
     * 
     * @return gets flight total security fee
     */
    public double getFlightTotalSecurityFee() {
        return this.flightTotalSecurityFee;
    }

    /**
     * 
     * @param flightTotalSecurityFeeIn sets flight total security fee
     */
    public void setFlightTotalSecurityFee(double flightTotalSecurityFeeIn) {
        this.flightTotalSecurityFee = flightTotalSecurityFeeIn;
    }

    /**
     * 
     * @return gets savings
     */
    public double getSavings() {
        return this.savings;
    }

    /**
     * 
     * @param savingsIn sets savings
     */
    public void setSavings(double savingsIn) {
        this.savings = savingsIn;
    }

    /**
     * Method that prints all available information for current flight
     */
    public void printFlight(){
        System.out.println("Flight Number: " + getFlightNumber());
        System.out.println("Origin Airport: " + getOriginAirport());
        System.out.println("Origin Code: " + getOriginCode());
        System.out.println("Destination Airport: " + getDestinationAirport());
        System.out.println("Destination Code: " + getDestinationCode());
        System.out.println("Departure Date: " + getDepartureDate());
        System.out.println("Departure Time: " + getDepartureTime());
        System.out.println("Arrival Date: " + getArrivalDate());
        System.out.println("Arrival Time: " + getArrivalTime());
        System.out.println("Duration: " + getDuration());
        System.out.println("Distance: " + getDistance());
        System.out.println("Time Zone Difference: " + getTimeZoneDifference());
        System.out.println("First Class Price: " + getFirstClassPrice());
        System.out.println("Business Class Price: " + getBusinessClassPrice());
        System.out.println("Main Cabin Price: " + getMainCabinPrice());
        System.out.println("First Class Seats: " + getFirstClassSeats());
        System.out.println("Business Class Seats: " + getBusinessClassSeats());
        System.out.println("Main Cabin Seats: " + getMainCabinSeats());
        System.out.println("Total Seats: " + getTotalSeats());
        System.out.println("Flight Type: " + getFlightType());
        System.out.println("Surcharge: " + getSurcharge());
        System.out.println("Food Served: " + getFoodServed());
        System.out.println("Route Cost: " + getRouteCost());
        System.out.println("Miner Points: " + getMinerPoints());
        System.out.println("Origin Airport City: " + getOriginAirportCity());
        System.out.println("Origin Airport State: " + getOriginAirportState());
        System.out.println("Origin Airport Country: " + getOriginAirportCountry());
        System.out.println("Origin Airport Fee: " + getOriginAirportFee());
        System.out.println("Origin Airport Lounge: " + getOriginAirportLounge());
        System.out.println("Destination Airport City: " + getDestinationAirportCity());
        System.out.println("Destination Airport State: " + getDestinationAirportState());
        System.out.println("Destination Airport Country: " + getDestinationAirportCountry());
        System.out.println("Destination Airport Fee: " + getDestinationAirportFee());
        System.out.println("Destination Airport Lounge: " + getDestinationAirportLounge());
    }

    /**
     * Methods that creates a map with ticket objects for employee
     * @param confirmationNumber Ticket Number
     * @param flightID ID of flight tickets bought from
     * @param userName userName of user who bought tickets
     * @param seatsPurchased Number of seats purchased
     * @param seatType Type of seats purchased
     * @param totalPrice Total price of seats purchased
     * @return Return hashmap of ticket objects for employees
     */
    public static HashMap<Integer,Ticket> flightTicketList(int confirmationNumber, int flightID, String userName, int seatsPurchased, String seatType, double totalPrice){

        HashMap<Integer,Ticket>  ticketMap = new HashMap<>();

        ticketMap.put(confirmationNumber, new Ticket(confirmationNumber,flightID, userName, seatsPurchased, seatType, totalPrice));

        return ticketMap;
    }

}
