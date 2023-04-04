import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class FileReader {

    /**
     * Method that reads a flight csv and creates a hash map with flight objects as values
     * @return returns hashmap with flight onjects
     */
    public static HashMap<Integer,Flight> makeFlightMap(){

        HashMap<Integer,Flight> flightMap = new HashMap<>();

        try {
            File text = new File("/Users/frank/PA4/FlightSchedule4.csv");
            Scanner sc = new Scanner(text);
            sc.nextLine();
            sc.useDelimiter(",|\\r\\n");

            while (sc.hasNextLine()) {
                String flightType = sc.next();
                String arrivalDate = sc.next();
                int mainCabinSeats = sc.nextInt();
                int flightNumber = sc.nextInt();
                String destinationCode = sc.next();
                String originAirport = sc.next();
                int minerPoints = sc.nextInt();
                String originCode = sc.next();
                int businessClassPrice = sc.nextInt();
                int mainCabinPrice = sc.nextInt();
                String departingTime = sc.next();
                int duration = sc.nextInt();
                int surcharge = sc.nextInt();
                boolean originAirportLounge = sc.nextBoolean();
                int distance = sc.nextInt();
                int timeZoneDifference = sc.nextInt();
                String arrivalTime = sc.next();
                String departingDate = sc.next();
                String originAirportState = sc.next();
                String originAirportCountry = sc.next();
                double originAirportFee = sc.nextDouble();
                String destinationAirportCity = sc.next();
                boolean foodServed = sc.nextBoolean();     
                String destinationAirportState = sc.next();
                String destinationAirportCountry = sc.next();
                double destinationAirportFee = sc.nextDouble();
                boolean destinationAirportLounge = sc.nextBoolean();
                int routeCost = sc.nextInt();
                int totalSeats = sc.nextInt();
                int id = sc.nextInt();
                int firstClassSeats = sc.nextInt();
                String destinationAirport = sc.next();
                int businessClassSeats = sc.nextInt();
                String originAirportCity = sc.next();
                int firstClassPrice = sc.nextInt();

                //Creates flight objects and stores then in array
                flightMap.put(id, new Flight(id, flightNumber, originAirport, originCode, destinationAirport, destinationCode, departingDate
                , departingTime, duration, distance, timeZoneDifference, arrivalDate, arrivalTime, flightType, surcharge, foodServed, routeCost, minerPoints
                , totalSeats, firstClassSeats, businessClassSeats, mainCabinSeats, firstClassPrice, businessClassPrice, mainCabinPrice,
                originAirportCity, originAirportState, originAirportCountry, originAirportFee, originAirportLounge, destinationAirportCity, destinationAirportState,
                destinationAirportCountry, destinationAirportFee, destinationAirportLounge)); 

            }
            sc.close();

        } catch (Exception FileNotFoundException) {
            System.out.println("FlightFileNotFoundException");
        }
        
        return flightMap;

    }

    /**
     * Method that reads a customer csv and creates a hash map with customer objects as values.
     * @return returns hashmap with customer objects
     */
    public static HashMap<Integer,Customer> makeCustomerMap(){

        HashMap<Integer, Customer> customerMap = new HashMap<>();

        try{
            Scanner sc = new Scanner(new File("/Users/frank/PA4/CustomerListPA3.csv"));
            sc.nextLine();
            sc.useDelimiter(",|\\r\\n");

            int arrayIndex = 1;

            while (sc.hasNextLine()) {
                String dob = sc.next();
                String userName = sc.next();
                double moneyAvailable = sc.nextDouble();
                String lastName = sc.next();
                String password = sc.next();
                String role = sc.next();
                int id = sc.nextInt();
                String firstName = sc.next();
                int flightsPurchased = sc.nextInt();
                boolean minerAirMembership = sc.nextBoolean();
               
    
                //Creates customer objects and stores then in array
                customerMap.put(arrayIndex, new Customer(id,firstName,lastName, dob, role, moneyAvailable,flightsPurchased,minerAirMembership,userName,password)); 

                arrayIndex++;
        }

        sc.close();

        }catch(Exception FileNotFoundException){
            System.out.println("CustomerFileNotFoundException");
        }

        return customerMap;
    }

    public static HashMap<String, Airport> makeAirportsMap(){

        HashMap<String, Airport> airportMap = new HashMap<>();

        try{

            File text = new File("/Users/frank/PA4/FlightSchedule4.csv");
            Scanner sc = new Scanner(text);
            sc.nextLine();
            sc.useDelimiter(",|\\r\\n");

            //int arrayIndex = 1;

            while (sc.hasNextLine()) {
                sc.next();
                sc.next();
                sc.nextInt();
                sc.nextInt();
                sc.next();
                String airportName = sc.next();
                sc.nextInt();
                String airportCode = sc.next();
                sc.nextInt();
                sc.nextInt();
                sc.next();
                sc.nextInt();
                sc.nextInt();
                boolean airportLounge = sc.nextBoolean();
                sc.nextInt();
                sc.nextInt();
                sc.next();
                sc.next();
                String airportState = sc.next();
                String airportCountry = sc.next();
                double airportFee = sc.nextDouble();
                sc.next();
                sc.nextBoolean();     
                sc.next();
                sc.next();
                sc.nextDouble();
                sc.nextBoolean();
                sc.nextInt();
                sc.nextInt();
                sc.nextInt();
                sc.nextInt();
                sc.next();
                sc.nextInt();
                String airportCity = sc.next();
                sc.nextInt();
                double airportTotalFees = 0;
                double moneyEarned = 0;

                airportMap.put(airportCode, new Airport(airportCode, airportName, airportCity, airportState, airportCountry, airportFee, airportLounge, airportTotalFees, moneyEarned));
                
            }

        sc.close();

        } catch (Exception FileNotFoundException) {
            System.out.println("AirportFileNotFoundException");
        }

        return airportMap;

    }

    public static HashMap<Integer, AutoBuyer> makeInstructionsMap(){

        HashMap<Integer, AutoBuyer> instructionMap = new HashMap<>();

        try{
            Scanner sc = new Scanner(new File("/Users/frank/PA4/AutoPurchaser10K.csv"));
            sc.nextLine();
            sc.useDelimiter(",|\\r\\n");

            int arrayIndex = 1;

            while (sc.hasNextLine()) {
                String firstName = sc.next();
                String lastName = sc.next();
                String action = sc.next();
                int flightID = sc.nextInt();
                String originAirport = sc.next();
                String destinationAirport = sc.next();
                String ticketType = sc.next();
                int ticketQuantity = sc.nextInt();

                //Creates instructions objects and stores then in array
                instructionMap.put(arrayIndex, new AutoBuyer(firstName, lastName, action, flightID, originAirport, destinationAirport, ticketType, ticketQuantity)); 

                arrayIndex++;
        }

        sc.close();

        }catch(Exception FileNotFoundException){
            System.out.println("AutoBuyerFileNotFoundException");
        }

        return instructionMap;

    
    }
}
