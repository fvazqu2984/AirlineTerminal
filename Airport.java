public class Airport {

    private String airportCode;
    private String airportName;
    private String airportCity;
    private String airportState;
    private String airportCountry;
    private double airportFee;
    private boolean airportLounge;
    private double airportTotalFees;
    private double moneyEarned;

    /**
     * Airport constructor
     */
    public Airport(){

    }

    /**
     * Method that assigns values to airport objects
     * @param airportCode2
     * @param airportName2
     * @param airportCity2
     * @param airportState2
     * @param airportCountry2
     * @param airportFee2
     * @param airportLounge2
     * @param airportTotalFees2
     * @param moneyEarned2
     */
    public Airport(String airportCode2, String airportName2, String airportCity2, String airportState2,
            String airportCountry2, double airportFee2, boolean airportLounge2, double airportTotalFees2,
            double moneyEarned2) {
                this.airportCode = airportCode2;
                this.airportName = airportName2;
                this.airportCity = airportCity2;
                this.airportState = airportState2;
                this.airportCountry = airportCountry2;
                this.airportFee = airportFee2;
                this.airportLounge = airportLounge2;
                this.airportTotalFees = airportTotalFees2;
                this.moneyEarned = moneyEarned2;
    }

    /**
     * 
     * @return gets airport code
     */
    public String getAirportCode() {
        return airportCode;
    }

    /**
     * 
     * @param airportCode sets airport code
     */
    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    /**
     * 
     * @return gets airport name
     */
    public String getAirportName() {
        return airportName;
    }

    /**
     * 
     * @param airportName sets airport name
     */
    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    /**
     * 
     * @return gets airport city
     */
    public String getAirportCity() {
        return airportCity;
    }

    /**
     * 
     * @param airportCity sets airport city
     */
    public void setAirportCity(String airportCity) {
        this.airportCity = airportCity;
    }

    /**
     * 
     * @return gets airport state
     */
    public String getAirportState() {
        return airportState;
    }

    /**
     * 
     * @param airportState sets airport state
     */
    public void setAirportState(String airportState) {
        this.airportState = airportState;
    }

    /**
     * 
     * @return gets airport country
     */
    public String getAirportCountry() {
        return airportCountry;
    }

    /**
     * 
     * @param airportCountry sets airport country
     */
    public void setAirportCountry(String airportCountry) {
        this.airportCountry = airportCountry;
    }

    /**
     * 
     * @return gets airport fee
     */
    public double getAirportFee() {
        return airportFee;
    }

    /**
     * 
     * @param airportFee sets airport fee
     */
    public void setAirportFee(double airportFee) {
        this.airportFee = airportFee;
    }

    /**
     * 
     * @return gets airport total fees
     */
    public double getAirportTotalFees() {
        return airportTotalFees;
    }

    /**
     * 
     * @param airportTotalFees sets airport total fees
     */
    public void setAirportTotalFees(double airportTotalFees) {
        this.airportTotalFees = airportTotalFees;
    }

    /**
     * 
     * @return gets airport lounge
     */
    public boolean getAirportLounge() {
        return airportLounge;
    }

    /**
     * 
     * @param airportLounge sets airport lounge
     */
    public void setAirportLounge(boolean airportLounge) {
        this.airportLounge = airportLounge;
    }

    /**
     * 
     * @return gets money earned
     */
    public double getMoneyEarned() {
        return moneyEarned;
    }

    /**
     * 
     * @param moneyEarned sets money earned
     */
    public void setMoneyEarned(double moneyEarned) {
        this.moneyEarned = moneyEarned;
    }

    /**
     * Method that prints airport information
     */
    public void printAirport(){
        System.out.println("Airport Code: " + getAirportCode());
        System.out.println("Airport Name: " + getAirportName());
        System.out.println("Airport City: " + getAirportCity());
        System.out.println("Airport State: " + getAirportState());
        System.out.println("Airport Fee: " + getAirportFee());
        System.out.println("Airport Lounge: " + getAirportLounge());
        System.out.println("Total Amount from Fees: " + getAirportTotalFees());
    }

}
