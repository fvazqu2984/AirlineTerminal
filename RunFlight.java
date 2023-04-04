// Francisco Vazquez
// March 30, 2023
// CS 3331 – Advanced Object-Oriented Programming – Spring 2023
// Dr. Mejia
// Programming Assignment 4
// A program that simulates an airline terminal and lets the customer view multiple flights information, 
//and also purchase tickets for those flights. Employees are allowed to edit flight information and view amounts.
//
// This work was done individually and completely on my own. I did not share, reproduce, or alter any part of this assignment for any purpose.
// I did not share code, upload this assignment online in any form, or view/received/modified code written from anyone else.
// All deliverables were produced entirely on my own. This assignment is part of an academic course at The University of Texas at El Paso and a grade will be assigned for the work I produced.

import java.util.HashMap;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * This is a description of the main class
 * @version 1.0
 */

public class RunFlight{
    /**
     * The main method description
     * @param args argument taken into the main
     */
    public static void main(String[] args){
        HashMap<String, Airport> airportMap = FileReader.makeAirportsMap();
        HashMap<Integer, Customer> customerMap = FileReader.makeCustomerMap();
        HashMap<Integer, Flight> flightMap = FileReader.makeFlightMap();
        //HashMap<Integer, AutoBuyer> autoInstructionMap = FileReader.makeInstructionsMap();

        //Creates file where user actions are going to be logged
        File file1 = new File("log.txt");
        try (FileWriter fw = new FileWriter(file1)){
            PrintWriter pw = new PrintWriter(fw);

            int flightLength = flightMap.size();
            int confirmationNumber = 1;
            int flightID = 0;
            int ticketQuantity = 0;
            int seatsPurchased = 0;
            double totalPrice = 0;
            String userName = " ";
            String seatType = " ";
            double flightSurcharge = 0;
            double minerFee = 9.15;
            double securityFee = 5.60;
            double savings = 0;
            double flightTotalTax = 0;
            double flightTotalAirlineFee = 0;
            double flightTotalSecurityFee = 0;
            double totalAirportFees = 0;

            //Initialize maps that contain all tickets purchased
            HashMap<Integer,Ticket> ticketArray = Ticket.makeTicketMap(confirmationNumber, flightID, ticketQuantity, seatsPurchased, seatType, totalPrice);
            HashMap<Integer,Ticket> flightTicketList = Flight.flightTicketList(confirmationNumber, flightID, userName , seatsPurchased, seatType, totalPrice);

            boolean mainMenu = true;
            ScannerSingleton sc = ScannerSingleton.getInstance();

            while(mainMenu){

                //Start of main user terminal
                System.out.println("Welcome to Miner Air");
                System.out.println("Enter EXIT to terminate the program or enter any symbol to log in");
                String userTerminate = sc.next(); 
                
                //If the user ends program create csv files
                if(userTerminate.equals("EXIT")){

                    //Create new CSV files
                    CsvWriter.writeFlightCSV("FlightScheduleUpdated.csv", flightMap);
                    CsvWriter.writeCustomerCSV("CustomerListUpdated.csv", customerMap);
                    pw.println("User terminated program");
                    mainMenu = false;
                    break;
                }

                //Ask user full name
                System.out.println("Enter your first and last name:");
                String userFirstName = sc.next(); 
                String userLastName = sc.next(); 
                System.out.println("Your name is: " + userFirstName + " "+ userLastName);

                //Log in system
                int userIDIndex = isLoggedIn(customerMap, sc);

                String userRole = customerMap.get(userIDIndex).getRole();

                System.out.println("User " + customerMap.get(userIDIndex).getUserName() +  " logged in");
                pw.println("User " + customerMap.get(userIDIndex).getUserName() +  " logged in as a " + userRole);
                System.out.println(userRole);

                //If user is customer menu
                if(userRole.equals("Customer")){
                    boolean inTicketMenu = true;
                    
                    //Loop for ticket buying menu
                    while(inTicketMenu){
                        System.out.println("0. Sign Out ");
                        System.out.println("1. Buy Tickets");
                        System.out.println("2. Cancel Tickets");
                        int inputID = sc.nextInt();

                        //If user inputs 0 log out and go back to the main menu
                        if(inputID == 0){
                            inTicketMenu = false;
                            pw.println(customerMap.get(userIDIndex).getUserName() + " has signed out");
                            break;
                        }

                        //If user inputs 2 print all tickets to cancel a ticket
                        if(inputID == 2){
                            System.out.println("Here are all purchased tickets");

                            //Traverses tickerMap to print all user tickets
                            for(int i = 1; i <= ticketArray.size(); i++){ 
                                ticketArray.get(i).printTicket();
                                System.out.println(" ");
                            }

                            ticketCancelMenu(flightTicketList, ticketArray, customerMap, flightMap, sc, flightID, userIDIndex, minerFee);
                            pw.println(customerMap.get(userIDIndex).getUserName() + " has canceled a ticket and their money has been refunded");
                        }

                        if(inputID == 1){

                            int inputFlightID = 0;

                            //Ask user to search for fligth by ID or airport codes
                            System.out.println("Press 0 to search flights by ID OR press 1 to search flights by airport codes");
                            int flightSearchInput = sc.nextInt();
                            
                            if(flightSearchInput == 0){
                                //Ask user for flight ID to buy tickets for
                                System.out.println("Please enter a flight ID (1-" + flightLength + ") or enter 0 to go back to menu");
                                inputFlightID = sc.nextInt();
                                flightID = inputFlightID;

                                //If user inputs 0 log out and go back to customer menu
                                if(inputID == 0){
                                    break;
                                }
                            }else if(flightSearchInput == 1){
                                //Ask user for flight codes to buy tickets for
                                System.out.println("Please enter Aiport Origin Code");
                                String airOriginCode = sc.next();
                                System.out.println("Please enter Aiport Destination Code");
                                String airDestinationCode = sc.next();

                                ArrayList<Integer> flightIDS = new ArrayList<Integer>();

                                for(int i =1; i<flightMap.size(); i++){
                                    String origCode = flightMap.get(i).getOriginCode();
                                    String destCode = flightMap.get(i).getDestinationCode();
                
                                    if(airOriginCode.equals(origCode) && airDestinationCode.equals(destCode)){
                                        flightIDS.add(flightMap.get(i).getFlightID());
                                        System.out.println("Flight ID: " + flightMap.get(i).getFlightID());
                                        System.out.println(" Flight Number: " + flightMap.get(i).getFlightNumber());
                                        System.out.println(" Departure Date: " + flightMap.get(i).getDepartureDate());
                                        System.out.println(" Departure Time: " + flightMap.get(i).getDepartureTime());
                                        System.out.println(" ");
                                    }           
                                }

                                if(flightIDS.size() == 0){
                                    System.out.println("No flights found. Try again");
                                } else{

                                    System.out.println("Choose a flight id from above");
                                    flightID = sc.nextInt();
                                    if(flightIDS.contains(flightID)){
                                        inputFlightID = flightID;   
                                    } else{
                                        System.out.println("Incorrect input. Try again");
                                    }
                                }            
                            }

                            //User can select from the flight numbers
                            if(inputFlightID >=1 && inputFlightID <= flightLength){

                                System.out.println("Do you wish to see selected flight information? 0.NO 1.YES");
                                int infoConfirm = sc.nextInt();

                                if(infoConfirm == 1){
                                    System.out.println("Flight Information");
                                    flightMap.get(inputFlightID).printFlight();
                                    pw.println("User printed the information for flight " + inputFlightID);
                                } else{
                                    System.out.println("Continue to tickets purchase");
                                    System.out.println(" ");
                                }
                            
                                confirmationNumber = ticketPurchaseSystem(flightMap, customerMap, airportMap, ticketArray, flightTicketList, sc, flightSurcharge, seatType, ticketQuantity, userIDIndex, totalPrice, seatsPurchased, flightID, confirmationNumber, minerFee, securityFee, savings, flightTotalTax,
                                flightTotalAirlineFee, flightTotalSecurityFee, totalAirportFees);
                                pw.println("User " + customerMap.get(userIDIndex).getUserName() +  " bought tickets for FlightID " + flightMap.get(inputFlightID).getID());
                            } else{
                                System.out.println("Flight not found. Try again");
                            }
                            
                        }
                        
                    }
                    
                }else if (userRole.equals("Employee")){
                    boolean inEmployeeMenu = true;
                    boolean inFlightMenu = false;
                    boolean inAirportMenu = false;
                    boolean inAutoBuyerMenu = false;
                    boolean inElectronicTicketMenu = false;


                    while(inEmployeeMenu){
                        System.out.println("Press 0 to sign out");
                        System.out.println("Press 1 to view airport information");
                        System.out.println("Press 2 to view/change flights");
                        System.out.println("Press 3 to auto buy tickets");
                        System.out.println("Press 4 to get electronic ticket summary");
                        int employeeMenuChoice = sc.nextInt();

                        if(employeeMenuChoice == 0){
                            inEmployeeMenu = false;
                            break;
                        } else if(employeeMenuChoice == 1){
                            inAirportMenu = true;
                        } else if(employeeMenuChoice == 2){
                            inFlightMenu = true;
                        } else if(employeeMenuChoice == 3){
                            inAutoBuyerMenu = true;
                        }else if(employeeMenuChoice == 4){
                            inElectronicTicketMenu = true;

                        }else{
                            System.out.println("Incorrect Input");
                        }

                        while(inElectronicTicketMenu){

                            System.out.println("Electronic Ticket Menu");
                            System.out.println("Enter customer username to print ticket");
                            String customerSearch = sc.next();

                            for(int i =1; i<customerMap.size(); i++){
                                if(customerMap.get(i).getUserName().equals(customerSearch)){

                                    CsvWriter.writeElectronicTicket(customerSearch + ".txt", flightTicketList, flightMap, customerSearch);
                                    break;
                                }
                            }

                            inElectronicTicketMenu = false;
                        }

                        while(inAutoBuyerMenu){
                            System.out.println("Auto Buyer Menu");
                            System.out.println("Select autobuyer file");
                            System.out.println("1.10k");
                            System.out.println("2.100k");
                            System.out.println("3.400k");

                            int userFileInput = sc.nextInt();
                            String fileName = " "; 

                            if(userFileInput == 1){
                                fileName = "/Users/frank/PA4/AutoPurchaser10K.csv";
                            } else if(userFileInput == 2){
                                fileName = "/Users/frank/PA4/AutoPurchaser100K.csv";
                            } else if(userFileInput == 3){
                                fileName = "/Users/frank/PA4/AutoPurchaser400K.csv";
                            } else{
                                System.out.println("Incorrect input");
                                break;
                            }

                            HashMap<Integer, AutoBuyer> autoInstructionMap = FileReader.makeInstructionsMap(fileName);

                            System.out.println("Please wait a moment for all tickets to be purchased...");
                            autoBuySystem(autoInstructionMap, customerMap, flightMap, airportMap, ticketArray, flightTicketList, securityFee, minerFee, confirmationNumber, savings, flightTotalTax, flightTotalAirlineFee, flightTotalSecurityFee, totalAirportFees, pw);
                            System.out.println("All tickets purchased");
                            pw.println(customerMap.get(userIDIndex).getUserName() + " has auto bought all tickets");
                            inAutoBuyerMenu = false;

                        }

                        while(inAirportMenu){
                            System.out.println("Please enter a valid airport code to view information");
                            String airportInput = sc.next();

                            for(int i =1; i<flightMap.size(); i++){
                                String airportCode = flightMap.get(i).getOriginCode();
            
                                if(airportInput.equals(airportCode)){
                                    System.out.println("Airport Code: " + flightMap.get(i).getOriginCode());
                                    System.out.println(" Airport Name: " + flightMap.get(i).getOriginAirport());
                                    System.out.println(" Airport City: " + flightMap.get(i).getOriginAirportCity());
                                    System.out.println(" Airport State: " + flightMap.get(i).getOriginAirportState());
                                    System.out.println(" Airport Country: " + flightMap.get(i).getOriginAirportCountry());
                                    System.out.println(" Airport Fee: " + flightMap.get(i).getOriginAirportFee());
                                    System.out.println(" Airport Louge: " + flightMap.get(i).getOriginAirportLounge());
                                    String currentAirport = flightMap.get(i).getOriginCode();
                                    System.out.println(" Total Amount in Airport Fees: " + airportMap.get(currentAirport).getAirportTotalFees());

                                    break;
                                }                
                            }
                            pw.println(customerMap.get(userIDIndex).getUserName() + " has vieved airport information");

                            inAirportMenu = false;
                        }

                        while(inFlightMenu){
                            System.out.println("Please enter a flight ID (1-" + flightLength + ") or enter 0 to go back to menu");
                            int inputID = sc.nextInt();
                
                            //If user inputs 0 go back to the main menu
                            if(inputID == 0){

                                //Creates new csv files
                                CsvWriter.writeFlightCSV("FlightScheduleUpdated.csv", flightMap);
                                CsvWriter.writeCustomerCSV("CustomerListUpdated.csv", customerMap);
                                pw.println("User terminated program");
                                inFlightMenu = false;
                                break;
                            }

                            //User can select from the flight numbers
                            if(inputID >=1 && inputID <= flightLength){
                                int flightChoice = inputID;

                                System.out.println("Here are the available actions for flight " + flightChoice);
                                System.out.println("0. Get all flight information");
                                System.out.println("1. Get flight tickets");
                                System.out.println("2. Cancel tickets");
                                System.out.println("3. Amount Collected for flight seats");
                                System.out.println("4. Update Origin Airport");
                                System.out.println("5. Update Origin Code");
                                System.out.println("6. Update Destination Airport");
                                System.out.println("7. Update Destination Code");
                                System.out.println("8. Update Departure Date/Time");
                                System.out.println("9. Update First Class Price");
                                System.out.println("10. Update Business Class Price");
                                System.out.println("11. Update Main Cabin Price");
                                System.out.println("12. Purchase Flight Tickets");
                                System.out.println("13. Print Total Flight Fees/Taxes");
                                int inputAction = sc.nextInt();

                                //Print flight information if user input 0
                                if(inputAction == 0){
                                    System.out.println("Flight Information");
                                    flightMap.get(flightChoice).printFlight();
                                    pw.println("User printed the information for flight " + flightChoice);
                            
                                //Gets all tickets for the specific flight and prints them
                                } else if(inputAction == 1){

                                    //Traverse flightTicketList and get all tickets that have the selected flight id
                                    System.out.println("Here are all available tickets for the selected flight");
                                    for(int i = 1; i <= flightTicketList.size(); i++){
                                        if(flightTicketList.get(i).getFlightID() == flightChoice){
                                            flightTicketList.get(i).printTicketList();
                                        }else {
                                            System.out.println(" ");
                                        }
                                    }
                                    pw.println("User printed all tickets for flight " + flightChoice);

                                //Cancel a ticket from the selected flight
                                } else if(inputAction == 2){

                                    //Traverse flightTicketList and get all tickets that have the selected flight id
                                    for(int i = 1; i <= flightTicketList.size(); i++){
                                        if(flightTicketList.get(i).getFlightID() == flightChoice){
                                            flightTicketList.get(i).printTicketList();
                                        }else {
                                            System.out.println(" "); 
                                            break;
                                        }
                                    }
                            
                                    ticketCancelMenu(flightTicketList, ticketArray, customerMap, flightMap, sc, flightChoice, userIDIndex, minerFee);
                                    pw.println(customerMap.get(userIDIndex).getUserName() + " has canceled a ticket and their money has been refunded");
                        
                                //Print seat amount if user input 3
                                } else if(inputAction == 3){
                                    //Calculates amount sold from first class seats for current flight
                                    System.out.println("Amount from First Class Seats Sold: ");
                                    double firstClassAmount = flightMap.get(flightChoice).getTotalFirstClassRevenue();
                                    System.out.println("$" + firstClassAmount);
                                    System.out.println(" ");

                                    //Calculates amount sold from bussiness class seats for current flight
                                    System.out.println("Amount from Bussiness Class Seats Sold: ");
                                    double bussinessClassAmount = flightMap.get(flightChoice).getTotalBusinessClassRevenue();
                                    System.out.println("$" + bussinessClassAmount);
                                    System.out.println(" ");

                                    //Calculates amount sold from main cabin seats for current flight
                                    System.out.println("Amount from Main Cabin Seats Sold: ");
                                    double mainCabinAmount = flightMap.get(flightChoice).getTotalMainCabinRevenue();
                                    System.out.println("$" + mainCabinAmount);
                                    System.out.println(" ");

                                    //Calculates total amount sold from seats for current flight
                                    System.out.println("Total Amount from Total Seats Sold: ");
                                    double totalSeatsAmount = firstClassAmount + bussinessClassAmount + mainCabinAmount;
                                    System.out.println("$" + totalSeatsAmount);
                            

                                    //Calculates possible and current profit from all seats in current flight
                                    double totalPossibleProfit = flightMap.get(flightChoice).getFirstClassSeats() * flightMap.get(flightChoice).getFirstClassPrice();
                                    totalPossibleProfit += flightMap.get(flightChoice).getBusinessClassSeats() * flightMap.get(flightChoice).getBusinessClassPrice();
                                    totalPossibleProfit += flightMap.get(flightChoice).getMainCabinSeats() * flightMap.get(flightChoice).getMainCabinPrice();

                                    System.out.println("Total Expected Profit for Flight: $" + totalPossibleProfit);
                                    System.out.println("Current Profit for Flight: $" + (totalSeatsAmount - totalPossibleProfit));
                                    pw.println(customerMap.get(userIDIndex).getUserName() + " has requested total amounts for flight seats");
                            
                                //Update origin airport if input 4
                                }else if(inputAction == 4){
                                    System.out.println("Update Origin Airport to: ");
                                    String inputName = sc.next();
                                    flightMap.get(flightChoice).setOriginAirport(inputName);
                                    System.out.println("Flight ID " + flightChoice + " updated origin airport to " + flightMap.get(flightChoice).getOriginAirport());
                                    pw.println("Flight ID " + flightChoice + " has updated origin airport to " + flightMap.get(flightChoice).getOriginAirport());
                        
                                //Update origin code if input 5
                                }else if(inputAction == 5){
                                    System.out.println("Update Origin Code to: ");
                                    String inputName = sc.next();
                                    flightMap.get(flightChoice).setOriginCode(inputName);
                                    System.out.println("Flight ID " + flightChoice + " updated origin code to " + flightMap.get(flightChoice).getOriginCode());
                                    pw.println("Flight ID " + flightChoice + " has updated origin code to " + flightMap.get(flightChoice).getOriginCode());
                        
                                //Update destination airport if input 6
                                }else if(inputAction == 6){
                                    System.out.println("Update Destination Airport to: ");
                                    String inputName = sc.next();
                                    flightMap.get(flightChoice).setDestinationAirport(inputName);
                                    System.out.println("Flight ID " + flightChoice + " updated destination airport to " + flightMap.get(flightChoice).getDestinationAirport());
                                    pw.println("Flight ID " + flightChoice + " has updated destination airport to " + flightMap.get(flightChoice).getDestinationAirport());

                                //Update destination code if input 7
                                }else if(inputAction == 7){
                                    System.out.println("Update Destination Code to: ");
                                    String inputName = sc.next();
                                    flightMap.get(flightChoice).setDestinationCode(inputName);
                                    System.out.println("Flight ID " + flightChoice + " updated destination code to " + flightMap.get(flightChoice).getDestinationCode());
                                    pw.println("Flight ID " + flightChoice + " has updated destination code to " + flightMap.get(flightChoice).getDestinationCode());

                                //Update flight arrival date and time if input 8
                                }else if(inputAction == 8){
                                    // Get departure and arrival date and time strings from the user
                                    System.out.println("Enter departure date (mm/dd/yy): ");
                                    sc.nextLine();
                                    String newDepartureDateStr = sc.next();
                                    System.out.println("Enter departure time (hh:mm AM/PM): ");
                                    sc.nextLine();
                                    String newDepartureTimeStr = sc.nextLine();

                                    String newDepartureDateTimeStr = newDepartureDateStr + " " + newDepartureTimeStr;

                                    String departureString = flightMap.get(flightChoice).getDepartureDate() + " " + flightMap.get(flightChoice).getDepartureTime();
                                    String arrivalString = flightMap.get(flightChoice).getArrivalDate() + " " + flightMap.get(flightChoice).getArrivalTime();
                                    System.out.println("Old departure time: " + departureString);
                                    System.out.println("Old arrival time: " + arrivalString);

                                    DateTimeFormatter myDateTimeFormatter = DateTimeFormatter.ofPattern("M/d/yy H:mm a");  

                                    //Parse departure and arrival date and time strings into LocalDateTime objects
                                    LocalDateTime newDepartureDateTime = LocalDateTime.parse(newDepartureDateTimeStr, myDateTimeFormatter);
                                    LocalDateTime arrivalDateTime = LocalDateTime.parse(arrivalString, myDateTimeFormatter);
                                    LocalDateTime departureDateTime = LocalDateTime.parse(departureString, myDateTimeFormatter);
                                
                                    //Calculate the difference between the original departure date/time and the new departure date/time
                                    int daysDifference = newDepartureDateTime.getDayOfYear() - departureDateTime.getDayOfYear();
                                    int hoursDifference = newDepartureDateTime.getHour() - departureDateTime.getHour();
                                    int minutesDifference = newDepartureDateTime.getMinute() - departureDateTime.getMinute();
        
                                    // Update arrival date/time based on the difference between the original and new departure date/time
                                    LocalDateTime newArrivalDateTime = arrivalDateTime.plusDays(daysDifference).plusHours(hoursDifference).plusMinutes(minutesDifference);

                                    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("M/d/yy H:mm a");  
                                    String formattedArrival = newArrivalDateTime.format(myFormatObj);
                                    String formattedDeparture = newDepartureDateTime.format(myFormatObj);   
                                    System.out.println("New departure date/time: " + formattedDeparture);
                                    System.out.println("New arrival date/time: " + formattedArrival);

                                    //split and update values
                                    String formatDept = formattedDeparture;
                                    String [] departureArray = formatDept.split(" ");
                                    String departureDate = departureArray[0];
                                    String departureTime = departureArray[1] + " " + departureArray[2];

                                    String formatArriv = formattedDeparture;
                                    String [] arrivalArray = formatArriv.split(" ");
                                    String arrivalDate = arrivalArray[0];
                                    String arrivalTime = arrivalArray[1] + " " + arrivalArray[2];

                                    //Update time/date values in map
                                    flightMap.get(flightChoice).setDepartureDate(departureDate);
                                    flightMap.get(flightChoice).setDepartureTime(departureTime);
                                    flightMap.get(flightChoice).setArrivalDate(arrivalDate);
                                    flightMap.get(flightChoice).setArrivalTime(arrivalTime);

                                    pw.println("Flight ID " + flightChoice + " updated date/time");

                                //Update first class price if input 9
                                }else if(inputAction == 9){
                                    System.out.println("Update First Class Price to: ");
                                    int inputName = sc.nextInt();
                                    flightMap.get(flightChoice).setFirstClassPrice(inputName);
                                    System.out.println("Flight ID " + flightChoice + " updated first class price to " + flightMap.get(flightChoice).getFirstClassPrice());
                                    pw.println("Flight ID " + flightChoice + " has updated first class price to " + flightMap.get(flightChoice).getFirstClassPrice());

                                //Update bussiness class price if input 10
                                }else if(inputAction == 10){
                                    System.out.println("Update Business Class Price to: ");
                                    int inputName = sc.nextInt();
                                    flightMap.get(flightChoice).setBusinessClassPrice(inputName);
                                    System.out.println("Flight ID " + flightChoice + " updated business class price to " + flightMap.get(flightChoice).getBusinessClassPrice());
                                    pw.println("Flight ID " + flightChoice + " has updated business class price to " + flightMap.get(flightChoice).getBusinessClassPrice());
                        
                                //Update main cabin price if input 11
                                }else if(inputAction == 11){
                                    System.out.println("Update Main Cabin Price to: ");
                                    int inputName = sc.nextInt();
                                    flightMap.get(flightChoice).setMainCabinPrice(inputName);
                                    System.out.println("Flight ID " + flightChoice + " updated main cabin price to " + flightMap.get(flightChoice).getMainCabinPrice());
                                    pw.println("Flight ID " + flightChoice + " updated main cabin price to " + flightMap.get(flightChoice).getMainCabinPrice());
                            
                                //Purchase tickets if input 12
                                }else if(inputAction == 12){

                                    flightID = flightChoice;

                                    confirmationNumber = ticketPurchaseSystem(flightMap, customerMap, airportMap, ticketArray, flightTicketList, sc, flightSurcharge, seatType, ticketQuantity, userIDIndex, totalPrice, seatsPurchased, flightID, confirmationNumber, minerFee, securityFee, savings, flightTotalTax,
                                    flightTotalAirlineFee, flightTotalSecurityFee, totalAirportFees);
                                    pw.println("User " + customerMap.get(userIDIndex).getUserName() +  " bought tickets for FlightID " + flightMap.get(flightID).getID());

                                }else if(inputAction == 13){

                                    System.out.println("Total flight savings amount: $" +flightMap.get(flightChoice).getSavings());
                                    System.out.println("Total flight miner fee amount: $" +flightMap.get(flightChoice).getFlightTotalAirlineFee());
                                    System.out.println("Total flight security fee amount: $" +flightMap.get(flightChoice).getFlightTotalSecurityFee());
                                    System.out.println("Total flight tax amount: $" +flightMap.get(flightChoice).getFlightTotalTax());
                                }
                            
                                else{
                                    System.out.println("Error: Not A Valid ID");
                                }
                    
                            }
                        }
                    
                    }
                }

            }//end of main menu loop

            pw.close();

        } catch (IOException e) {
            System.out.println("IO Exception occured");
        }
    }//end of main

    /**
     * Method for user to log in to airline terminal
     * @param customerMap Hashmap that include all of the customer in the csv file
     * @param sc Scanner to take user input
     * @return Return int value of the user ID
     */
    public static int isLoggedIn(HashMap<Integer,Customer> customerMap, ScannerSingleton sc ){

        boolean loggedIn = false;
        boolean userFound = false;
        int userID = 0;
        int currentIndex = 0;

        while(!loggedIn){
            while(!userFound){
                System.out.println("Enter your username:");
                String userNameInput = sc.next();
                
                //Find customer ID by username 
                for(int i =1; i<customerMap.size(); i++){
                    String customerUserName = customerMap.get(i).getUserName();

                    if(userNameInput.equals(customerUserName)){
                        userID = customerMap.get(i).getID();
                        System.out.println("Your ID is: " + userID );
                        userFound = true;
                        currentIndex = i;
                    }                
                }
            }
    
            System.out.println("Your username is: " + customerMap.get(currentIndex).getUserName());

            //Enter correct password for ID
            System.out.println("Enter your password:");
            String passwordInput = sc.next();
            String customerPassword = customerMap.get(userID).getPassword();

            if(passwordInput.equals(customerPassword)){
                System.out.println("Your password is: " + passwordInput);
                loggedIn = true;
            } else{
                System.out.println("Incorrect Password ");
            }
        }
        return currentIndex;   
    }

    /**
     * Method that allows customers and employees to purchase tickets for selected flight
     * @param flightMap Hashmap of flight objects
     * @param customerMap Hashmap of customer objects
     * @param ticketArray List of Tickets for Customers
     * @param flightTicketList List of tickets for Employees
     * @param sc Scanner 
     * @param flightSurcharge Fee for internatinal flight
     * @param seatType Type of seat 
     * @param ticketQuantity Total amount of tickets per transaction
     * @param userIDIndex ID of User buying the tickets
     * @param totalPrice Total money from tickets
     * @param seatsPurchased Amount of seats purchased
     * @param flightID ID of the selected flight
     * @param confirmationNumber Number of the ticket
     * @param minerFee Miner fee
     * @param securityFee Security fee
     * @param savings Total amount saved
     * @param flightTotalTax Total amount collected from sales tax
     * @param flightTotalAirlineFee Total amount collected from airline fees
     * @param flightTotalSecurityFee Total amount collected from security fees
     * @return Return confirmation number
     */
    public static int ticketPurchaseSystem(HashMap<Integer,Flight> flightMap, HashMap<Integer,Customer> customerMap, HashMap<String, Airport> airportMap, HashMap<Integer,Ticket> ticketArray, HashMap<Integer,Ticket> flightTicketList, ScannerSingleton sc,
     double flightSurcharge, String seatType, int ticketQuantity, int userIDIndex, double totalPrice, int seatsPurchased, int flightID, int confirmationNumber
     , double minerFee, double securityFee, double savings, double flightTotalTax, double flightTotalAirlineFee, double flightTotalSecurityFee, double totalAirportFees){

        //Gets any surchage price on international flights
        if(flightMap.get(flightID).getFlightType().equals("International")){
            flightSurcharge = flightMap.get(flightID).getSurcharge();
        }else{
            flightSurcharge = 0;
        }
    
        //Ticket menu to choose type of seats
        System.out.println("Here are the available ticket types and prices for flight " + flightID + " (International flights are subject to a subcharge)");
        System.out.println("Choose a type of ticket: ");
        System.out.println("1.First Class: $" + flightMap.get(flightID).getFirstClassPrice());
        System.out.println("2.Business Class: $" + flightMap.get(flightID).getBusinessClassPrice());
        System.out.println("3.Main Cabin: $" + flightMap.get(flightID).getMainCabinPrice());
        int inputAction = sc.nextInt();

        //Option for first class tickets
        if(inputAction == 1){
            seatType = "First";
            System.out.println("How many tickets would you like? (8 ticket limit per transaction)");
            ticketQuantity = sc.nextInt();

            //Checks if there are seats available and the ticket amount is less than the limit
            if((flightMap.get(flightID).getFirstClassSeats() >= ticketQuantity) && ticketQuantity > 0 && ticketQuantity <=8){
                //Calculate price for all tickets
                double ticketcost = flightMap.get(flightID).getFirstClassPrice();
                double customerDiscount = ticketcost * .05;
                double employeeDiscountFC = ticketcost * .50;
                seatsPurchased = ticketQuantity;

                //Checks if user is employee to give discount
                if(customerMap.get(userIDIndex).getRole().equals("Employee")){
                    ticketcost = ticketcost - employeeDiscountFC;
                    System.out.println("You are an employee, 50% discount applied on tickets");
                }

                //Checks for minerline member discount
                if(customerMap.get(userIDIndex).getMinerAirMembership() == true){
                    ticketcost = ticketcost - customerDiscount;
                    System.out.println("You are a MinerAir member, 5% discount applied on tickets");

                }

                //Fees
                double originFee = flightMap.get(flightID).getOriginAirportFee();
                double destinationFee = flightMap.get(flightID).getDestinationAirportFee();
                double fee = flightSurcharge + securityFee + originFee + destinationFee;
                totalPrice = ticketcost + fee;
                totalPrice = totalPrice * ticketQuantity;
                totalPrice = totalPrice + minerFee;

                //Checks if the customer has enough money for the purchase
                if(customerMap.get(userIDIndex).getMoneyAvailable() >= totalPrice){
                    //Checks for flight surcharge
                    if(flightSurcharge != 0){
                        System.out.println("The surcharge on this flight is: $" + flightSurcharge);
                    }

                    System.out.println("The MinerAirlines fee is: $" + minerFee);
                    System.out.println("The security fee is: $" + securityFee);
                    System.out.println("The subtotal is: $" + totalPrice);

                    //Taxes
                    double totalTax = totalPrice * .0825;
                    double totalAfterTax = totalPrice + totalTax;
                    System.out.println("Price after Texas Sale Tax: $" + totalAfterTax);

                    boolean purchasedConfirmed = true;
                    //User confirmation
                    while(purchasedConfirmed){
                        System.out.println("Press 1 to confirm purchase or 0 to cancel transaction" );
                        int userConfirmation = sc.nextInt();

                        if(userConfirmation == 0){
                            purchasedConfirmed = false;

                        }else if(userConfirmation == 1){
                            ticketArray.put(confirmationNumber, new Ticket(confirmationNumber, flightID , ticketQuantity, seatsPurchased, seatType, totalAfterTax));
                            flightTicketList.put(confirmationNumber, new Ticket(confirmationNumber, flightID, customerMap.get(userIDIndex).getUserName(), seatsPurchased, seatType, totalAfterTax));

                            //Updates the money available for the customer
                            double updateCustomerMoney = customerMap.get(userIDIndex).getMoneyAvailable() - totalAfterTax;
                            customerMap.get(userIDIndex).setMoneyAvailable(updateCustomerMoney);

                            //Updates seats of purchased flight
                            int updateFlightSeats = seatsPurchased + flightMap.get(flightID).getFirstClassSeats();
                            flightMap.get(flightID).setFirstClassSeats(updateFlightSeats);
            
                            //Prints ticket for user
                            System.out.println("Here is your ticket:");
                            ticketArray.get(confirmationNumber).printTicket();

                            //Update Amount Values
                            double firstRevenue = flightMap.get(flightID).getTotalFirstClassRevenue() + totalAfterTax;
                            flightMap.get(flightID).setTotalFirstClassRevenue(firstRevenue);

                            int firstSeats = flightMap.get(flightID).getFirstClassSeatsSold() + seatsPurchased;
                            flightMap.get(flightID).setFirstClassSeatsSold(firstSeats);

                            //Update Taxes and fees
                            savings = flightMap.get(flightID).getSavings() + savings;
                            flightTotalAirlineFee = flightMap.get(flightID).getFlightTotalAirlineFee() + minerFee;
                            flightTotalSecurityFee = flightMap.get(flightID).getFlightTotalSecurityFee() + (securityFee * ticketQuantity);
                            flightTotalTax = flightMap.get(flightID).getFlightTotalTax() + totalTax;
                            flightMap.get(flightID).setSavings(savings);
                            flightMap.get(flightID).setFlightTotalAirlineFee(flightTotalAirlineFee);
                            flightMap.get(flightID).setFlightTotalSecurityFee(flightTotalSecurityFee);
                            flightMap.get(flightID).setFlightTotalTax(flightTotalTax);

                            String currentAirport = flightMap.get(flightID).getOriginCode();
                            totalAirportFees = airportMap.get(currentAirport).getAirportTotalFees() + originFee + destinationFee;
                            airportMap.get(currentAirport).setAirportTotalFees(totalAirportFees);
            
                            confirmationNumber = confirmationNumber + 1;
                            purchasedConfirmed = true;
                            int flightsPurchased = 1 + customerMap.get(userIDIndex).getFlightsPurchased();
                            customerMap.get(userIDIndex).setFlightsPurchased(flightsPurchased);
                            return confirmationNumber;

                        }
                    }
                }else
                    System.out.println("Insufficient Funds");
            }else 
                System.out.println("No seats available");

        //Option for business class tickets
        }else if(inputAction == 2){
            seatType = "Bussiness";
            System.out.println("How many tickets would you like? (8 ticket limit per transaction)");
            ticketQuantity = sc.nextInt();
    
            //Checks if there are seats available and the ticket amount is less than the limit
            if((flightMap.get(flightID).getBusinessClassSeats() >= ticketQuantity) && ticketQuantity > 0 && ticketQuantity <=8){
                //Calculate price for all tickets
                double ticketcost = flightMap.get(flightID).getBusinessClassPrice();
                double customerDiscount = ticketcost * .05;
                double employeeDiscount = ticketcost * .75;
                seatsPurchased = ticketQuantity;

                //Checks if user is employee to give discount
                if(customerMap.get(userIDIndex).getRole().equals("Employee")){
                    ticketcost = ticketcost - employeeDiscount;
                    System.out.println("You are an employee, 75% discount applied on tickets");
                }

                //Checks for minerline member discount
                if(customerMap.get(userIDIndex).getMinerAirMembership() == true){
                    ticketcost = ticketcost - customerDiscount;
                    System.out.println("You are a MinerAir member, 5% discount applied on tickets");

                }

                //Fees
                double originFee = flightMap.get(flightID).getOriginAirportFee();
                double destinationFee = flightMap.get(flightID).getDestinationAirportFee();
                double fee = flightSurcharge + securityFee + originFee + destinationFee;
                totalPrice = ticketcost + fee;
                totalPrice = totalPrice * ticketQuantity;
                totalPrice = totalPrice + minerFee;

                //Checks if the customer has enough money for the purchase
                if(customerMap.get(userIDIndex).getMoneyAvailable() >= totalPrice){
                    //Checks for flight surcharge
                    if(flightSurcharge != 0){
                        System.out.println("The surcharge on this flight is: $" + flightSurcharge);
                    }

                    System.out.println("The MinerAirlines fee is: $" + minerFee);
                    System.out.println("The security fee is: $" + securityFee);
                    System.out.println("The subtotal is: $" + totalPrice);

                    //Taxes
                    double totalTax = totalPrice * .0825;
                    double totalAfterTax = totalPrice + totalTax;
                    System.out.println("Price after Texas Sale Tax: $" + totalAfterTax);

                    boolean purchasedConfirmed = true;
                    //User confirmation
                    while(purchasedConfirmed){
                        System.out.println("Press 1 to confirm purchase or 0 to cancel transaction" );
                        int userConfirmation = sc.nextInt();

                        if(userConfirmation == 0){
                            purchasedConfirmed = false;

                        }else if(userConfirmation == 1){
                            ticketArray.put(confirmationNumber, new Ticket(confirmationNumber, flightID , ticketQuantity, seatsPurchased, seatType, totalAfterTax));
                            flightTicketList.put(confirmationNumber, new Ticket(confirmationNumber, flightID, customerMap.get(userIDIndex).getUserName(), seatsPurchased, seatType, totalAfterTax));

                            //Updates the money available for the customer
                            double updateCustomerMoney = customerMap.get(userIDIndex).getMoneyAvailable() - totalAfterTax;
                            customerMap.get(userIDIndex).setMoneyAvailable(updateCustomerMoney);

                            //Updates seats of purchased flight
                            int updateFlightSeats = seatsPurchased + flightMap.get(flightID).getBusinessClassSeats();
                            flightMap.get(flightID).setBusinessClassSeats(updateFlightSeats);
            
                            //Prints ticket for user
                            System.out.println("Here is your ticket:");
                            ticketArray.get(confirmationNumber).printTicket();

                            //Update Amount Values
                            double bussinessRevenue = flightMap.get(flightID).getTotalBusinessClassRevenue() + totalAfterTax;
                            flightMap.get(flightID).setTotalBusinessClassRevenue(bussinessRevenue);

                            int bussinessSeats = flightMap.get(flightID).getBusinessClassSeatsSold() + seatsPurchased;
                            flightMap.get(flightID).setBusinessClassSeatsSold(bussinessSeats);

                            //Update Taxes and fees
                            savings = flightMap.get(flightID).getSavings() + savings;
                            flightTotalAirlineFee = flightMap.get(flightID).getFlightTotalAirlineFee() + minerFee;
                            flightTotalSecurityFee = flightMap.get(flightID).getFlightTotalSecurityFee() + (securityFee * ticketQuantity);
                            flightTotalTax = flightMap.get(flightID).getFlightTotalTax() + totalTax;
                            flightMap.get(flightID).setSavings(savings);
                            flightMap.get(flightID).setFlightTotalAirlineFee(flightTotalAirlineFee);
                            flightMap.get(flightID).setFlightTotalSecurityFee(flightTotalSecurityFee);
                            flightMap.get(flightID).setFlightTotalTax(flightTotalTax);

                            String currentAirport = flightMap.get(flightID).getOriginCode();
                            totalAirportFees = airportMap.get(currentAirport).getAirportTotalFees() + originFee + destinationFee;
                            airportMap.get(currentAirport).setAirportTotalFees(totalAirportFees);
            
                            confirmationNumber = confirmationNumber + 1;
                            purchasedConfirmed = true;
                            int flightsPurchased = 1 + customerMap.get(userIDIndex).getFlightsPurchased();
                            customerMap.get(userIDIndex).setFlightsPurchased(flightsPurchased);
                            return confirmationNumber;

                        }
                    }
                }else
                    System.out.println("Insufficient Funds");
            }else 
                System.out.println("No seats available");

        //Option for main cabin tickets
        }else if(inputAction == 3){
            seatType = "Main";
            System.out.println("How many tickets would you like? (8 ticket limit per transaction)");
            ticketQuantity = sc.nextInt();
    
            //Checks if there are seats available and the ticket amount is less than the limit
            if((flightMap.get(flightID).getMainCabinSeats() >= ticketQuantity) && ticketQuantity > 0 && ticketQuantity <=8){
                //Calculate price for all tickets
                double ticketcost = flightMap.get(flightID).getMainCabinPrice();
                double customerDiscount = ticketcost * .05;
                double employeeDiscount = ticketcost * .75;
                seatsPurchased = ticketQuantity;

                //Checks if user is employee to give discount
                if(customerMap.get(userIDIndex).getRole().equals("Employee")){
                    ticketcost = ticketcost - employeeDiscount;
                    savings = employeeDiscount * ticketQuantity;
                    System.out.println("You are an employee, 75% discount applied on tickets");
                }

                //Checks for minerline member discount
                if(customerMap.get(userIDIndex).getMinerAirMembership() == true){
                    ticketcost = ticketcost - customerDiscount;
                    savings = customerDiscount * ticketQuantity;
                    System.out.println("You are a MinerAir member, 5% discount applied on tickets");
                }

                System.out.println("You are saving $" + savings + " in this flight");

                //Fees
                double originFee = flightMap.get(flightID).getOriginAirportFee();
                double destinationFee = flightMap.get(flightID).getDestinationAirportFee();
                double fee = flightSurcharge + securityFee + originFee + destinationFee;
                totalPrice = ticketcost + fee;
                totalPrice = totalPrice * ticketQuantity;
                totalPrice = totalPrice + minerFee;

                //Checks if the customer has enough money for the purchase
                if(customerMap.get(userIDIndex).getMoneyAvailable() >= totalPrice){
                    //Checks for flight surcharge
                    if(flightSurcharge != 0){
                        System.out.println("The surcharge on this flight is: $" + flightSurcharge);
                    }

                    System.out.println("The MinerAirlines fee is: $" + minerFee);
                    System.out.println("The security fee is: $" + securityFee);
                    System.out.println("The subtotal is: $" + totalPrice);

                    //Taxes
                    double totalTax = totalPrice * .0825;
                    double totalAfterTax = totalPrice + totalTax;
                    System.out.println("Price after Texas Sale Tax: $" + totalAfterTax);

                    boolean purchasedConfirmed = true;
                    //User confirmation
                    while(purchasedConfirmed){
                        System.out.println("Press 1 to confirm purchase or 0 to cancel transaction" );
                        int userConfirmation = sc.nextInt();

                        if(userConfirmation == 0){
                            purchasedConfirmed = false;

                        }else if(userConfirmation == 1){
                            ticketArray.put(confirmationNumber, new Ticket(confirmationNumber, flightID , ticketQuantity, seatsPurchased, seatType, totalAfterTax));
                            flightTicketList.put(confirmationNumber, new Ticket(confirmationNumber, flightID, customerMap.get(userIDIndex).getUserName(), seatsPurchased, seatType, totalAfterTax));

                            //Updates the money available for the customer
                            double updateCustomerMoney = customerMap.get(userIDIndex).getMoneyAvailable() - totalAfterTax;
                            customerMap.get(userIDIndex).setMoneyAvailable(updateCustomerMoney);

                            //Updates seats of purchased flight
                            int updateFlightSeats = seatsPurchased + flightMap.get(flightID).getMainCabinSeats();
                            flightMap.get(flightID).setMainCabinSeats(updateFlightSeats);
            
                            //Prints ticket for user
                            System.out.println("Here is your ticket:");
                            ticketArray.get(confirmationNumber).printTicket();

                            //Update Amount Values
                            double mainRevenue = flightMap.get(flightID).getTotalMainCabinRevenue() + totalAfterTax;
                            flightMap.get(flightID).setTotalMainCabinRevenue(mainRevenue);

                            int mainSeats = flightMap.get(flightID).getMainCabinSeatsSold() + seatsPurchased;
                            flightMap.get(flightID).setMainCabinSeatsSold(mainSeats);

                            //Update Taxes and fees
                            savings = flightMap.get(flightID).getSavings() + savings;
                            flightTotalAirlineFee = flightMap.get(flightID).getFlightTotalAirlineFee() + minerFee;
                            flightTotalSecurityFee = flightMap.get(flightID).getFlightTotalSecurityFee() + (securityFee * ticketQuantity);
                            flightTotalTax = flightMap.get(flightID).getFlightTotalTax() + totalTax;
                            flightMap.get(flightID).setSavings(savings);
                            flightMap.get(flightID).setFlightTotalAirlineFee(flightTotalAirlineFee);
                            flightMap.get(flightID).setFlightTotalSecurityFee(flightTotalSecurityFee);
                            flightMap.get(flightID).setFlightTotalTax(flightTotalTax);

                            String currentAirport = flightMap.get(flightID).getOriginCode();
                            totalAirportFees = airportMap.get(currentAirport).getAirportTotalFees() + originFee + destinationFee;
                            airportMap.get(currentAirport).setAirportTotalFees(totalAirportFees);

                            
            
                            confirmationNumber = confirmationNumber + 1;
                            purchasedConfirmed = true;
                            int flightsPurchased = 1 + customerMap.get(userIDIndex).getFlightsPurchased();
                            customerMap.get(userIDIndex).setFlightsPurchased(flightsPurchased);
                            return confirmationNumber;
                        }
                    }
                }else
                    System.out.println("Insufficient Funds");
            }else 
                System.out.println("No seats available");

        } return confirmationNumber;
    }

    /**
     * Method to cancel tickets bought
     * @param flightTicketList Hashmap list of tickets for employee use
     * @param ticketArray Hashmap list of tickets for customer use
     * @param customerMap Hashmap containing customer objects
     * @param flightMap Hashmap containing flight objects
     * @param sc Scanner
     * @param flightChoice ID of selected flight
     * @param userIDIndex ID of user canceling flights
     * @param minerFee Miner Fee 
     */
    public static void ticketCancelMenu(HashMap<Integer,Ticket> flightTicketList, HashMap<Integer,Ticket> ticketArray, HashMap<Integer,Customer> customerMap, HashMap<Integer,Flight> flightMap, ScannerSingleton sc, int flightChoice, int userIDIndex, double minerFee ){
    
        System.out.println("Enter a ticket # to cancel a ticket");
        int cancelInput = sc.nextInt();

        if(cancelInput >=1 && cancelInput <= flightTicketList.size()){
            //Refunds money to customer
            double refundMoney = ticketArray.get(cancelInput).getTotalPrice();

            //If user customer don't refund minerFee
            if(customerMap.get(userIDIndex).getRole() == "Customer"){
                refundMoney = refundMoney + minerFee;
            }

            customerMap.get(userIDIndex).setMoneyAvailable(refundMoney);

            //Gets flight id and seats purchases from ticket map
            int cancelFlight = ticketArray.get(cancelInput).getFlightID();
            int returnSeats = ticketArray.get(cancelInput).getSeatsPurchased();

            //Gets current seats in flight
            int currentTotalSeats = flightMap.get(cancelFlight).getTotalSeats();
            int currentFirstSeats = flightMap.get(cancelFlight).getFirstClassSeats();
            int currentBussinessSeats = flightMap.get(cancelFlight).getBusinessClassSeats();
            int currentMainSeats = flightMap.get(cancelFlight).getMainCabinSeats();

            //Return total seasts
            flightMap.get(cancelFlight).setTotalSeats(returnSeats + currentTotalSeats);

            //Return seasts by type purchased
            if(ticketArray.get(cancelInput).getSeatType().equals("First")){
                flightMap.get(cancelFlight).setFirstClassSeats(currentFirstSeats + ticketArray.get(cancelInput).getSeatsPurchased());

            }else if(ticketArray.get(cancelInput).getSeatType().equals("Bussiness")){
                flightMap.get(cancelFlight).setBusinessClassSeats(currentBussinessSeats + ticketArray.get(cancelInput).getSeatsPurchased());

            }else if(ticketArray.get(cancelInput).getSeatType().equals("Main")){
                flightMap.get(cancelFlight).setMainCabinSeats(currentMainSeats + ticketArray.get(cancelInput).getSeatsPurchased());
            }

            //Cancels ticket in ticket map
            ticketArray.get(cancelInput).setConfirmationNumber(0);
            ticketArray.get(cancelInput).setFlightID(0);
            ticketArray.get(cancelInput).setSeatsPurchases(0);
            ticketArray.get(cancelInput).setTicketQuantity(0);
            ticketArray.get(cancelInput).setTotalPrice(0);

            //Cancels ticket in flightTicketMap
            flightTicketList.get(cancelInput).setConfirmationNumber(0);
            flightTicketList.get(cancelInput).setFlightID(0);
            flightTicketList.get(cancelInput).setUserName("CANCELLED");
            flightTicketList.get(cancelInput).setSeatsPurchases(0);
            flightTicketList.get(cancelInput).setTotalPrice(0);

            System.out.println("Ticket and money have been refunded");

        }else{
            System.out.println("Try again");
        }

    }

    /**
     * Method that reads auto purchaser file and buys all tickets in the file automatically
     * @param autoInstructionsMap  Hashmap that has instructions objects to buy tickets
     * @param customerMap Hashmap containing customer objects
     * @param flightMap Hashmap containing flight objects
     * @param airportMap Hashmap containing airport objects
     * @param ticketArray Hashmap list of tickets for customer use
     * @param flightTicketList Hashmap list of tickets for employee use
     * @param securityFee security fee per ticket
     * @param minerFee miner fee per transaction
     * @param confirmationNumber number of ticket
     * @param savings Total amount saved
     * @param flightTotalTax Total amount collected from sales tax
     * @param flightTotalAirlineFee Total amount collected from airline fees
     * @param flightTotalSecurityFee Total amount collected from security fees
     * @param totalAirportFees Total amount of fees collected per airport
     */
    public static void autoBuySystem(HashMap<Integer, AutoBuyer> autoInstructionsMap, HashMap<Integer, Customer> customerMap, HashMap<Integer, Flight> flightMap, HashMap<String, Airport> airportMap, HashMap<Integer,Ticket> ticketArray, HashMap<Integer,Ticket> flightTicketList, double securityFee, double minerFee, int confirmationNumber, double savings, double flightTotalTax, double flightTotalAirlineFee, double flightTotalSecurityFee, double totalAirportFees, PrintWriter pw){

        String userName = "";
        int userIDIndex = 0;
        double totalPrice = 0;
        double flightSurcharge = 0;
 
        for(int i = 1; i<= autoInstructionsMap.size(); i++){

            //Find username based on name
            for(int j = 1; j<= customerMap.size(); j++){
                String autoName = autoInstructionsMap.get(i).getFirstName() + autoInstructionsMap.get(i).getLastName();
                String customerName = customerMap.get(j).getFirstName() + customerMap.get(j).getLastName();
                
                if(autoName.equals(customerName)){
                    userName = customerMap.get(j).getUserName();
                    userIDIndex = customerMap.get(j).getID();
        
                    int flightID = autoInstructionsMap.get(i).getFlightID();
                    String ticketType = autoInstructionsMap.get(i).getTicketType();
                    int ticketQuantity = autoInstructionsMap.get(i).getTicketQuantity();
                    String seatType = ticketType;

                if(ticketType.equals("First Class")){

                    if(flightMap.get(flightID).getFlightType().equals("International")){
                        flightSurcharge = flightMap.get(flightID).getSurcharge();
                    }else{
                        flightSurcharge = 0;
                    }

                    if((flightMap.get(flightID).getFirstClassSeats() >= ticketQuantity) && ticketQuantity > 0 && ticketQuantity <=8){
                        //Calculate price for all tickets
                        double ticketcost = flightMap.get(flightID).getFirstClassPrice();
                        double customerDiscount = ticketcost * .05;
                        double employeeDiscountFC = ticketcost * .50;
                        int seatsPurchased = ticketQuantity;

                        //Checks if user is employee to give discount
                        if(customerMap.get(userIDIndex).getRole().equals("Employee")){
                            ticketcost = ticketcost - employeeDiscountFC;
                        }

                        //Checks for minerline member discount
                        if(customerMap.get(userIDIndex).getMinerAirMembership() == true){
                            ticketcost = ticketcost - customerDiscount;
                        }

                        //Fees
                        double originFee = flightMap.get(flightID).getOriginAirportFee();
                        double destinationFee = flightMap.get(flightID).getDestinationAirportFee();
                        double fee = flightSurcharge + securityFee + originFee + destinationFee;
                        totalPrice = ticketcost + fee;
                        totalPrice = totalPrice * ticketQuantity;
                        totalPrice = totalPrice + minerFee;

                        //Taxes
                        double totalTax = totalPrice * .0825;
                        double totalAfterTax = totalPrice + totalTax;

                        //Checks if the customer has enough money for the purchase
                        if(customerMap.get(userIDIndex).getMoneyAvailable() >= totalPrice){

                            ticketArray.put(confirmationNumber, new Ticket(confirmationNumber, flightID , ticketQuantity, seatsPurchased, seatType, totalAfterTax));
                            flightTicketList.put(confirmationNumber, new Ticket(confirmationNumber, flightID, customerMap.get(userIDIndex).getUserName(), seatsPurchased, seatType, totalAfterTax));

                            //Updates the money available for the customer
                            double updateCustomerMoney = customerMap.get(userIDIndex).getMoneyAvailable() - totalAfterTax;
                            customerMap.get(userIDIndex).setMoneyAvailable(updateCustomerMoney);

                            //Updates seats of purchased flight
                            int updateFlightSeats = seatsPurchased + flightMap.get(flightID).getFirstClassSeats();
                            flightMap.get(flightID).setFirstClassSeats(updateFlightSeats);

                            //Update Amount Values
                            double firstRevenue = flightMap.get(flightID).getTotalFirstClassRevenue() + totalAfterTax;
                            flightMap.get(flightID).setTotalFirstClassRevenue(firstRevenue);

                            int firstSeats = flightMap.get(flightID).getFirstClassSeatsSold() + seatsPurchased;
                            flightMap.get(flightID).setFirstClassSeatsSold(firstSeats);

                            //Update Taxes and fees
                            savings = flightMap.get(flightID).getSavings() + savings;
                            flightTotalAirlineFee = flightMap.get(flightID).getFlightTotalAirlineFee() + minerFee;
                            flightTotalSecurityFee = flightMap.get(flightID).getFlightTotalSecurityFee() + (securityFee * ticketQuantity);
                            flightTotalTax = flightMap.get(flightID).getFlightTotalTax() + totalTax;
                            flightMap.get(flightID).setSavings(savings);
                            flightMap.get(flightID).setFlightTotalAirlineFee(flightTotalAirlineFee);
                            flightMap.get(flightID).setFlightTotalSecurityFee(flightTotalSecurityFee);
                            flightMap.get(flightID).setFlightTotalTax(flightTotalTax);

                            String currentAirport = flightMap.get(flightID).getOriginCode();
                            totalAirportFees = airportMap.get(currentAirport).getAirportTotalFees() + originFee + destinationFee;
                            airportMap.get(currentAirport).setAirportTotalFees(totalAirportFees);
        
                            confirmationNumber = confirmationNumber + 1;
                            int flightsPurchased = 1 + customerMap.get(userIDIndex).getFlightsPurchased();
                            customerMap.get(userIDIndex).setFlightsPurchased(flightsPurchased);

                            pw.println(userName + " bought tickets");
                        }else
                            pw.println("Insufficient Funds for transaction " + i);
                    }else 
                        pw.println("No seats available for transaction " + i);

                }else if(ticketType.equals("Business Class")){
                    if(flightMap.get(flightID).getFlightType().equals("International")){
                        flightSurcharge = flightMap.get(flightID).getSurcharge();
                    }else{
                        flightSurcharge = 0;
                    }

                    if((flightMap.get(flightID).getBusinessClassSeats() >= ticketQuantity) && ticketQuantity > 0 && ticketQuantity <=8){
                        //Calculate price for all tickets
                        double ticketcost = flightMap.get(flightID).getBusinessClassPrice();
                        double customerDiscount = ticketcost * .05;
                        double employeeDiscountFC = ticketcost * .50;
                        int seatsPurchased = ticketQuantity;

                        //Checks if user is employee to give discount
                        if(customerMap.get(userIDIndex).getRole().equals("Employee")){
                            ticketcost = ticketcost - employeeDiscountFC;
                        }

                        //Checks for minerline member discount
                        if(customerMap.get(userIDIndex).getMinerAirMembership() == true){
                            ticketcost = ticketcost - customerDiscount;
                        }

                        //Fees
                        double originFee = flightMap.get(flightID).getOriginAirportFee();
                        double destinationFee = flightMap.get(flightID).getDestinationAirportFee();
                        double fee = flightSurcharge + securityFee + originFee + destinationFee;
                        totalPrice = ticketcost + fee;
                        totalPrice = totalPrice * ticketQuantity;
                        totalPrice = totalPrice + minerFee;

                        //Taxes
                        double totalTax = totalPrice * .0825;
                        double totalAfterTax = totalPrice + totalTax;

                        //Checks if the customer has enough money for the purchase
                        if(customerMap.get(userIDIndex).getMoneyAvailable() >= totalPrice){

                            ticketArray.put(confirmationNumber, new Ticket(confirmationNumber, flightID , ticketQuantity, seatsPurchased, seatType, totalAfterTax));
                            flightTicketList.put(confirmationNumber, new Ticket(confirmationNumber, flightID, customerMap.get(userIDIndex).getUserName(), seatsPurchased, seatType, totalAfterTax));

                            //Updates the money available for the customer
                            double updateCustomerMoney = customerMap.get(userIDIndex).getMoneyAvailable() - totalAfterTax;
                            customerMap.get(userIDIndex).setMoneyAvailable(updateCustomerMoney);

                            //Updates seats of purchased flight
                            int updateFlightSeats = seatsPurchased + flightMap.get(flightID).getBusinessClassSeats();
                            flightMap.get(flightID).setBusinessClassSeats(updateFlightSeats);

                            //Update Amount Values
                            double businessRevenue = flightMap.get(flightID).getTotalBusinessClassRevenue() + totalAfterTax;
                            flightMap.get(flightID).setTotalBusinessClassRevenue(businessRevenue);

                            int businessSeats = flightMap.get(flightID).getBusinessClassSeatsSold() + seatsPurchased;
                            flightMap.get(flightID).setBusinessClassSeatsSold(businessSeats);

                            //Update Taxes and fees
                            savings = flightMap.get(flightID).getSavings() + savings;
                            flightTotalAirlineFee = flightMap.get(flightID).getFlightTotalAirlineFee() + minerFee;
                            flightTotalSecurityFee = flightMap.get(flightID).getFlightTotalSecurityFee() + (securityFee * ticketQuantity);
                            flightTotalTax = flightMap.get(flightID).getFlightTotalTax() + totalTax;
                            flightMap.get(flightID).setSavings(savings);
                            flightMap.get(flightID).setFlightTotalAirlineFee(flightTotalAirlineFee);
                            flightMap.get(flightID).setFlightTotalSecurityFee(flightTotalSecurityFee);
                            flightMap.get(flightID).setFlightTotalTax(flightTotalTax);

                            String currentAirport = flightMap.get(flightID).getOriginCode();
                            totalAirportFees = airportMap.get(currentAirport).getAirportTotalFees() + originFee + destinationFee;
                            airportMap.get(currentAirport).setAirportTotalFees(totalAirportFees);
        
                            confirmationNumber = confirmationNumber + 1;
                            int flightsPurchased = 1 + customerMap.get(userIDIndex).getFlightsPurchased();
                            customerMap.get(userIDIndex).setFlightsPurchased(flightsPurchased);

                            pw.println(userName + " bought tickets");
                        }else
                            pw.println("Insufficient Funds for transaction " + i);
                    }else 
                        pw.println("No seats available for transaction " + i);

                }else if(ticketType.equals("Main Cabin")){
                    if(flightMap.get(flightID).getFlightType().equals("International")){
                        flightSurcharge = flightMap.get(flightID).getSurcharge();
                    }else{
                        flightSurcharge = 0;
                    }

                    if((flightMap.get(flightID).getMainCabinSeats() >= ticketQuantity) && ticketQuantity > 0 && ticketQuantity <=8){
                        //Calculate price for all tickets
                        double ticketcost = flightMap.get(flightID).getMainCabinPrice();
                        double customerDiscount = ticketcost * .05;
                        double employeeDiscountFC = ticketcost * .50;
                        int seatsPurchased = ticketQuantity;

                        //Checks if user is employee to give discount
                        if(customerMap.get(userIDIndex).getRole().equals("Employee")){
                            ticketcost = ticketcost - employeeDiscountFC;
                        }

                        //Checks for minerline member discount
                        if(customerMap.get(userIDIndex).getMinerAirMembership() == true){
                            ticketcost = ticketcost - customerDiscount;
                        }

                        //Fees
                        double originFee = flightMap.get(flightID).getOriginAirportFee();
                        double destinationFee = flightMap.get(flightID).getDestinationAirportFee();
                        double fee = flightSurcharge + securityFee + originFee + destinationFee;
                        totalPrice = ticketcost + fee;
                        totalPrice = totalPrice * ticketQuantity;
                        totalPrice = totalPrice + minerFee;

                        //Taxes
                        double totalTax = totalPrice * .0825;
                        double totalAfterTax = totalPrice + totalTax;

                        //Checks if the customer has enough money for the purchase
                        if(customerMap.get(userIDIndex).getMoneyAvailable() >= totalPrice){

                            ticketArray.put(confirmationNumber, new Ticket(confirmationNumber, flightID , ticketQuantity, seatsPurchased, seatType, totalAfterTax));
                            flightTicketList.put(confirmationNumber, new Ticket(confirmationNumber, flightID, customerMap.get(userIDIndex).getUserName(), seatsPurchased, seatType, totalAfterTax));

                            //Updates the money available for the customer
                            double updateCustomerMoney = customerMap.get(userIDIndex).getMoneyAvailable() - totalAfterTax;
                            customerMap.get(userIDIndex).setMoneyAvailable(updateCustomerMoney);

                            //Updates seats of purchased flight
                            int updateFlightSeats = seatsPurchased + flightMap.get(flightID).getMainCabinSeats();
                            flightMap.get(flightID).setMainCabinSeats(updateFlightSeats);

                            //Update Amount Values
                            double mainRevenue = flightMap.get(flightID).getTotalMainCabinRevenue() + totalAfterTax;
                            flightMap.get(flightID).setTotalMainCabinRevenue(mainRevenue);

                            int firstSeats = flightMap.get(flightID).getMainCabinSeatsSold() + seatsPurchased;
                            flightMap.get(flightID).setMainCabinSeatsSold(firstSeats);

                            //Update Taxes and fees
                            savings = flightMap.get(flightID).getSavings() + savings;
                            flightTotalAirlineFee = flightMap.get(flightID).getFlightTotalAirlineFee() + minerFee;
                            flightTotalSecurityFee = flightMap.get(flightID).getFlightTotalSecurityFee() + (securityFee * ticketQuantity);
                            flightTotalTax = flightMap.get(flightID).getFlightTotalTax() + totalTax;
                            flightMap.get(flightID).setSavings(savings);
                            flightMap.get(flightID).setFlightTotalAirlineFee(flightTotalAirlineFee);
                            flightMap.get(flightID).setFlightTotalSecurityFee(flightTotalSecurityFee);
                            flightMap.get(flightID).setFlightTotalTax(flightTotalTax);

                            String currentAirport = flightMap.get(flightID).getOriginCode();
                            totalAirportFees = airportMap.get(currentAirport).getAirportTotalFees() + originFee + destinationFee;
                            airportMap.get(currentAirport).setAirportTotalFees(totalAirportFees);
        
                            confirmationNumber = confirmationNumber + 1;
                            int flightsPurchased = 1 + customerMap.get(userIDIndex).getFlightsPurchased();
                            customerMap.get(userIDIndex).setFlightsPurchased(flightsPurchased);

                            pw.println(userName + " bought tickets");

                        }else
                            pw.println("Insufficient Funds for transaction " + i);
                        }else 
                        pw.println("No seats available for transaction " + i);
                } else{
                    pw.println("No type found");
                }
                    
                }

            }
            
        }
    }
}