import java.io.FileWriter;
import java.util.HashMap;

public class CsvWriter {

    /**
     * Method that creates and writes an updated flight csv file
     * @param filename Name of the file
     * @param flightMap Takes old flight map to update the csv file
     */
    public static void writeFlightCSV(String filename, HashMap<Integer, Flight> flightMap) {
  
        StringBuilder stringBuilder = new StringBuilder();
        
        stringBuilder.append("ID").append(",").append("Flight Number").append(",").append("Origin Airport").append(",").append("Origin Code").append(",").append("Destination Airport").append(",")
        .append("Destination Code").append(",").append("Departing Date").append(",").append("Departing Time").append(",").append("Duration").append(",").append("Distance").append(",")
        .append("Time Zone Difference").append(",").append("Arrival Date").append(",").append("Arrival Time").append(",").append("Flight Type").append(",").append("Surcharge").append(",")
        .append("Food Served").append(",").append("Route Cost").append(",").append("Miner Points").append(",").append("Total Seats").append(",").append("First Class Seats").append(",")
        .append("Business Class Seats").append(",").append("Main Cabin Seats").append(",").append("First Class Price").append(",").append("Business Class Price").append(",").append("Main Cabin Price").append(",")
        .append("Origin Airport City").append(",").append("Origin Airport State").append(",").append("Origin Airport Country").append(",").append("Origin Airport Fee").append(",").append("Origin Airport Lounge").append(",")
        .append("Destination Airport City").append(",").append("Destination Airport State").append(",").append("Destination Airport Country").append(",").append("Destination Airport Fee").append(",").append("Destination Airport Lounge").append(",")
        .append("First Class Seats Sold").append(",").append("Bussiness Class Seats").append(",").append("Main Cabin Seats Sold").append(",").append(" Total First Class Revenue").append(",")
        .append("Total Business Class Revenue").append(",").append("Total Main Cabin").append(",").append("Total First Class Seats Sold").append(",").append("Total Bussiness Class Seats Sold").append(",")
        .append("Total Main Cabin Seats Sold").append(",").append("Total First Class Revenue").append(",").append("Total Bussiness Class Revenues").append(",").append("Total Main Cabin Revenue").append(",")
        .append("Flight Savings").append(",").append("Total Flight Miner Fee").append(",").append("Total Flight Security Fee").append(",").append("Total Flight Taxes").append("\n");

        //iterate keys in map with and append all info
        for(int i  = 1; i <= flightMap.size(); i++ ){

            stringBuilder.append(flightMap.get(i).getFlightID()).append(",").append(flightMap.get(i).getFlightNumber()).append(",").append(flightMap.get(i).getOriginAirport()).append(",").append(flightMap.get(i).getOriginCode()).append(",")
        .append(flightMap.get(i).getDestinationAirport()).append(",").append(flightMap.get(i).getDestinationCode()).append(",").append(flightMap.get(i).getDepartureDate()).append(",").append(flightMap.get(i).getDepartureTime()).append(",")
        .append(flightMap.get(i).getDuration()).append(",").append(flightMap.get(i).getDistance()).append(",").append(flightMap.get(i).getTimeZoneDifference()).append(",").append(flightMap.get(i).getArrivalDate()).append(",")
        .append(flightMap.get(i).getArrivalTime()).append(",").append(flightMap.get(i).getFlightType()).append(",").append(flightMap.get(i).getSurcharge()).append(",").append(flightMap.get(i).getFoodServed()).append(",")
        .append(flightMap.get(i).getMinerPoints()).append(",").append(flightMap.get(i).getTotalSeats()).append(",").append(flightMap.get(i).getFirstClassSeats()).append(",").append(flightMap.get(i).getBusinessClassSeats()).append(",")
        .append(flightMap.get(i).getMainCabinSeats()).append(",").append(flightMap.get(i).getFirstClassPrice()).append(",").append(flightMap.get(i).getBusinessClassPrice()).append(",").append(flightMap.get(i).getMainCabinPrice()).append(",")
        .append(flightMap.get(i).getOriginAirportCity()).append(",").append(flightMap.get(i).getOriginAirportState()).append(",").append(flightMap.get(i).getOriginAirportCountry()).append(",").append(flightMap.get(i).getOriginAirportFee()).append(",").append(flightMap.get(i).getOriginAirportLounge()).append(",")
        .append(flightMap.get(i).getDestinationAirportCity()).append(",").append(flightMap.get(i).getDestinationAirportState()).append(",").append(flightMap.get(i).getDestinationAirportCountry()).append(",").append(flightMap.get(i).getDestinationAirportFee()).append(",").append(flightMap.get(i).getDestinationAirportLounge()).append(",")
        .append(flightMap.get(i).getFirstClassSeatsSold()).append(",").append(flightMap.get(i).getBusinessClassSeatsSold()).append(",").append(flightMap.get(i).getMainCabinSeatsSold()).append(",").append(flightMap.get(i).getTotalFirstClassRevenue()).append(",")
        .append(flightMap.get(i).getTotalBusinessClassRevenue()).append(",").append(flightMap.get(i).getTotalMainCabinRevenue()).append(",").append(flightMap.get(i).getSavings()).append(",").append(flightMap.get(i).getFlightTotalAirlineFee()).append(",").append(flightMap.get(i).getFlightTotalSecurityFee()).append(",")
        .append(flightMap.get(i).getFlightTotalTax()).append("\n");

        }
        try ( FileWriter fileWriter = new FileWriter(filename)) {
         
         fileWriter.write(stringBuilder.toString());
         
        } catch(Exception e) {
            System.out.println("Exception e");
        }
    }

    /**
     * Method that creates and writes an updated customer csv file
     * @param filename Name of file
     * @param customerMap Takes old customer map to update the csv file
     */
    public static void writeCustomerCSV(String filename, HashMap<Integer, Customer> customerMap) {
  
        StringBuilder stringBuilder = new StringBuilder();
        
        stringBuilder.append("ID").append(",").append("First Name").append(",").append("Last Name").append(",").append("DOB").append(",").append("Role").append(",")
        .append("Money Available").append(",").append("Flights Purchased").append(",").append("MineAir Membership").append(",").append("Username").append(",").append("Password").append("\n");

        for(int i  = 1; i <= customerMap.size(); i++ ){

            stringBuilder.append(customerMap.get(i).getID()).append(",").append(customerMap.get(i).getFirstName()).append(",").append(customerMap.get(i).getLastName()).append(",")
            .append(customerMap.get(i).getDOB()).append(",").append(customerMap.get(i).getRole()).append(",").append(customerMap.get(i).getMoneyAvailable()).append(",")
            .append(customerMap.get(i).getFlightsPurchased()).append(",").append(customerMap.get(i).getMinerAirMembership()).append(",").append(customerMap.get(i).getUserName()).append(",")
            .append(customerMap.get(i).getPassword()).append("\n");    
        }

        try ( FileWriter fileWriter = new FileWriter(filename)) {
         
         fileWriter.write(stringBuilder.toString());
         
        } catch(Exception e) {
            System.out.println("Exception e");
        }
    }
      
}
      