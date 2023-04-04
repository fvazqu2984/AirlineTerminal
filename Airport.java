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

    public Airport(){

    }

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


    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public String getAirportCity() {
        return airportCity;
    }

    public void setAirportCity(String airportCity) {
        this.airportCity = airportCity;
    }

    public String getAirportState() {
        return airportState;
    }

    public void setAirportState(String airportState) {
        this.airportState = airportState;
    }

    public String getAirportCountry() {
        return airportCountry;
    }

    public void setAirportCountry(String airportCountry) {
        this.airportCountry = airportCountry;
    }

    public double getAirportFee() {
        return airportFee;
    }

    public void setAirportFee(double airportFee) {
        this.airportFee = airportFee;
    }

    public double getAirportTotalFees() {
        return airportTotalFees;
    }

    public void setAirportTotalFees(double airportTotalFees) {
        this.airportTotalFees = airportTotalFees;
    }

    public boolean getAirportLounge() {
        return airportLounge;
    }

    public void setAirportLounge(boolean airportLounge) {
        this.airportLounge = airportLounge;
    }

    public double getMoneyEarned() {
        return moneyEarned;
    }

    public void setMoneyEarned(double moneyEarned) {
        this.moneyEarned = moneyEarned;
    }

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
