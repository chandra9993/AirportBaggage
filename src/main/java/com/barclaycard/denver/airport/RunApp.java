package com.barclaycard.denver.airport;

import com.barclaycard.denver.airport.model.Baggage;
import com.barclaycard.denver.airport.model.Conveyer;
import com.barclaycard.denver.airport.model.Departures;
import com.barclaycard.denver.airport.model.Gate;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class RunApp {
    public static void main(String[] args) {
        List<Gate> gateList = new ArrayList<>();
        List<Conveyer> conveyerList = new ArrayList<>();
        List<Departures> departuresList = new ArrayList<>();
        List<Baggage> bagsList = new ArrayList<>();
        readInput(gateList, conveyerList, departuresList, bagsList);
        printShortestPath(gateList, conveyerList, departuresList, bagsList);
    }

    private static void readInput(List<Gate> gateList, List<Conveyer> conveyerList, List<Departures> departuresList, List<Baggage> bagsList) {
        Scanner scanner = new Scanner(System.in);

        String readingFor = "";
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if ("".equals(line)) {
                break;
            }
            if (line.startsWith("# Section")) {
                readingFor = (line.split(":")[1]).trim();
                continue;
            }

            if (readingFor.equalsIgnoreCase("Conveyor System")) {
                readConveyer(gateList, conveyerList, line);
            }
            if (readingFor.equalsIgnoreCase("Departures")) {
                readDepartures(gateList, departuresList, line);
                Gate g = gateList.get(gateList.indexOf(new Gate("BaggageClaim")));
                departuresList.add(new Departures("ARRIVAL", g, null, null));
            }
            if (readingFor.equalsIgnoreCase("Bags")) {
                readBags(bagsList, departuresList, gateList, line);
            }
        }

    }

    private static void readConveyer(List<Gate> gates, List<Conveyer> conveyers, String line) {
        String[] splitLine = line.split(" ");
        Gate gate1 = new Gate(splitLine[0]);
        Gate gate2 = new Gate(splitLine[1]);
        Conveyer conveyer = new Conveyer(gate1, gate2, Integer.parseInt(splitLine[2]));
        Conveyer conveyer1 = new Conveyer(gate2, gate1, Integer.parseInt(splitLine[2]));
        gates.add(gate1);
        gates.add(gate2);
        conveyers.add(conveyer);
        conveyers.add(conveyer1);

    }

    private static void readDepartures(List<Gate> gates, List<Departures> departuresList, String line) {
        String[] splitLine = line.split(" ");
        Gate g = new Gate(splitLine[1]);
        departuresList.add(new Departures(splitLine[0], gates.get(gates.indexOf(g)), splitLine[2], splitLine[3]));
    }

    private static void readBags(List<Baggage> baggages, List<Departures> departures, List<Gate> gates, String line) {
        String[] lineSplit = line.split(" ");
        baggages.add(new Baggage(lineSplit[0], gates.get(gates.indexOf(new Gate(lineSplit[1]))), departures.get(departures.indexOf(new Departures(lineSplit[2], null, null, null)))));

    }

    private static void printShortestPath(List<Gate> gateList, List<Conveyer> conveyerList, List<Departures> departuresList, List<Baggage> bagsList) {
        ShortestPathAlgorithm shortestPathAlgorithm = new ShortestPathAlgorithm(new Graph(gateList, conveyerList));
        bagsList.forEach(bag -> {
            shortestPathAlgorithm.execute(bag.getFromGate());
            LinkedList<Gate> shortPath = shortestPathAlgorithm.getPath(bag.getDepartures().getGate());
            int distance = shortestPathAlgorithm.getShortestDistance(bag.getDepartures().getGate());
            System.out.print(bag.getBagId()+"  ");
            shortPath.forEach(gate -> {
                System.out.print(gate.getGateId()+"  ");
            });
            System.out.print(distance);
            System.out.println();
        });
    }
}
