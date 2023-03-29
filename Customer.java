public class Customer {
    public static Customer[] makeCustomerArray;
    private int id;
    private String firstName;
    private String lastName;
    private double moneyAvailable;
    private int flightsPurchased;
    private boolean minerAirMembership;
    protected String userName;
    private String password;
    private String dob;
    private String role;

    /**
     * Customer Constructor
     */
    public Customer(){

    }

    /**
     * Constructor to assign values to customer objects
     * @param id2
     * @param firstName2
     * @param lastName2
     * @param dob2
     * @param role2
     * @param moneyAvailable2
     * @param flightsPurchased2
     * @param minerAirMembership2
     * @param userName2
     * @param password2
     */
    public Customer(int id2, String firstName2, String lastName2, String dob2, String role2, double moneyAvailable2,
            int flightsPurchased2, boolean minerAirMembership2, String userName2, String password2) {
                this.id = id2;
                this.firstName = firstName2;
                this.lastName = lastName2;
                this.moneyAvailable = moneyAvailable2;
                this.flightsPurchased = flightsPurchased2;
                this.minerAirMembership = minerAirMembership2;
                this.userName = userName2;
                this.password = password2;
                this.dob = dob2;
                this.role = role2;
    }

    /**
     * 
     * @return gets customer id
     */
    public int getID() {
        return id;
    }

    /**
     * 
     * @param id sets customer id
     */
    public void setID(int id) {
        this.id = id;
    }

    /**
     * 
     * @return gets first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * 
     * @param firstName sets first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * 
     * @return gets last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * 
     * @param lastName sets last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * 
     * @return gets customer money available
     */
    public double getMoneyAvailable() {
        return moneyAvailable;
    }

    /**
     * 
     * @param moneyAvailable sets money available
     */
    public void setMoneyAvailable(double moneyAvailable) {
        this.moneyAvailable = moneyAvailable;
    }

    /**
     * 
     * @return gets flights purchased
     */
    public int getFlightsPurchased() {
        return flightsPurchased;
    }

    /**
     * 
     * @param flightsPurchased sets flights purchased
     */
    public void setFlightsPurchased(int flightsPurchased) {
        this.flightsPurchased = flightsPurchased;
    }

    /**
     * 
     * @return gets if customer has miner air membership
     */
    public boolean getMinerAirMembership() {
        return minerAirMembership;
    }

    /**
     * 
     * @param minerAirMembership sets if customer has miner air membership
     */
    public void setMinerAirMembership(boolean minerAirMembership) {
        this.minerAirMembership = minerAirMembership;
    }

    /**
     * 
     * @return gets username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 
     * @param userName sets username
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 
     * @return gets password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 
     * @param password sets password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 
     * @return gets date of birth
     */
    public String getDOB() {
        return dob;
    }

    /**
     * 
     * @param dobIn sets date of birth
     */
    public void setDOB(String dobIn) {
        this.dob = dobIn;
    }

    /**
     * 
     * @return gets use role
     */
    public String getRole() {
        return role;
    }

    /**
     * 
     * @param roleIn sets user role
     */
    public void setRole(String roleIn) {
        this.role = roleIn;
    }

    /**
     * Method that prints all available information for current customers
     */
    public void printCustomer(){
        System.out.println("First Name: " + getFirstName());
        System.out.println("Last Name: " + getLastName());
        System.out.println("Money Available: " + getMoneyAvailable());
        System.out.println("Flights Purchased: " + getFlightsPurchased());
        System.out.println("MinerAir Membership: " + getMinerAirMembership());
        System.out.println("Username: " + getUserName());
        System.out.println("Password: " + getPassword());
    }
    
}
