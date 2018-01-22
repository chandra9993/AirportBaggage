package com.barclaycard.denver.airport;

import com.barclaycard.denver.airport.model.Baggage;
import com.barclaycard.denver.airport.model.Conveyer;
import com.barclaycard.denver.airport.model.Departures;
import com.barclaycard.denver.airport.model.Gate;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
/***
 * 
 * @author hqcpundr
 *
 * The application with the main methods where the execution starts
 */
public class AirportBaggageApp {
    public static void main(String[] args) {
        // Lists to store different inputs
        List<Gate> gateList = new ArrayList<>();
        List<Conveyer> conveyerList = new ArrayList<>();
        List<Departures> departuresList = new ArrayList<>();
        List<Baggage> bagsList = new ArrayList<>();
        // Method to read inputs from the console
        readInput(gateList, conveyerList, departuresList, bagsList);
        // Method to find and print the shoretes path for all the baggages
        printShortestPath(gateList, conveyerList, departuresList, bagsList);
    }

    private static void readInput(List<Gate> gateList, List<Conveyer> conveyerList, List<Departures> departuresList, List<Baggage> bagsList) {
        Scanner scanner = new Scanner(System.in);

        String readingFor = "";
        System.out.println("Please enter input");
        while (scanner.hasNextLine()) {
        	try{
        		String line = scanner.nextLine().trim();
        		// When there is an empty line input The program stops reading from console
        		if ("".equals(line)) {
        			break;
        		}
        		// The console input is deviced by a line starting with "# Section"
        		if (line.startsWith("# Section")) {
        			readingFor = (line.split(":")[1]).trim();
        			continue;
        		}

                // Interprets what input is currently being entered
        		if (readingFor.equalsIgnoreCase("Conveyor System")) {
        			// Reads conveyer information
        		    readConveyer(gateList, conveyerList, line);
        		}
        		if (readingFor.equalsIgnoreCase("Departures")) {
        		    // Reads Departures information
        			readDepartures(gateList, departuresList, line);
        			// AS per the requirement when there is a bag which says ARRIVAL im should be routed to Baggage claim
                    // In order to fulfil the above requirement a Dummy gate object called baggane claim is created and a dummy departure is added from that gate
        			Gate g = gateList.get(gateList.indexOf(new Gate("BaggageClaim")));
        			departuresList.add(new Departures("ARRIVAL", g, null, null));
        		}
        		if (readingFor.equalsIgnoreCase("Bags")) {
        		    // Reads bags section of the input
        			readBags(bagsList, departuresList, gateList, line);
        		}
        	}catch(Exception exception){
        		bagsList.clear();
        		departuresList.clear();
        		gateList.clear();
        		conveyerList.clear();
        	}
        }
        scanner.close();
    }

    private static void readConveyer(List<Gate> gates, List<Conveyer> conveyers, String line) {
        // The input is split based on a space delimiter
        String[] splitLine = line.split(" ");
        Gate gate1 = new Gate(splitLine[0]);
        Gate gate2 = new Gate(splitLine[1]);
        // As the requirement says the conveyer system is bi direction
        // we create 2 conveyers each time in both directions
        Conveyer conveyer = new Conveyer(gate1, gate2, Integer.parseInt(splitLine[2]));
        Conveyer conveyer1 = new Conveyer(gate2, gate1, Integer.parseInt(splitLine[2]));
        gates.add(gate1);
        gates.add(gate2);
        conveyers.add(conveyer);
        conveyers.add(conveyer1);

    }

    private static void readDepartures(List<Gate> gates, List<Departures> departuresList, String line) {
        // Reading departures section and create the departures objects and adding to the list
        String[] splitLine = line.split(" ");
        // Every departure is from a gate, we get the gate information from the gates list and assign to Departures
        Gate g = new Gate(splitLine[1]);
        departuresList.add(new Departures(splitLine[0], gates.get(gates.indexOf(g)), splitLine[2], splitLine[3]));
    }

    private static void readBags(List<Baggage> baggages, List<Departures> departures, List<Gate> gates, String line) {

        // Reading the bags section of input
        String[] lineSplit = line.split(" ");
        // Every bad is from a gate and to a departure, We look up the gates and departures from the availble list.
        baggages.add(new Baggage(lineSplit[0], gates.get(gates.indexOf(new Gate(lineSplit[1]))), departures.get(departures.indexOf(new Departures(lineSplit[2], null, null, null)))));

    }

    private static void printShortestPath(List<Gate> gateList, List<Conveyer> conveyerList, List<Departures> departuresList, List<Baggage> bagsList) {
        // This method iterated over the bags and prints the shortest path fro the everybag using the dijkshitra's algorithm.
        // Prints the path and the total weight for the selected shorted path.
        ShortestPathAlgorithm shortestPathAlgorithm = new ShortestPathAlgorithm(new Graph(gateList, conveyerList));
        bagsList.forEach(bag -> {
            shortestPathAlgorithm.execute(bag.getFromGate());
            LinkedList<Gate> shortPath = shortestPathAlgorithm.getPath(bag.getDepartures().getGate());
            int distance = shortestPathAlgorithm.getShortestDistance(bag.getDepartures().getGate());
            System.out.print(bag.getBagId()+"  ");
            shortPath.forEach(gate -> {
                System.out.print(gate.getGateId()+"  ");
            });
            System.out.print(": "+distance);
            System.out.println();
        });
    }
}
