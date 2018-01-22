package com.barclaycard.denver.airport.model;
/***
 * 
 * @author hqcpundr
 * Class to store the baggage informtion
 */
public class Baggage {
    // bag Id - unique identifier for the bag
    private final String bagId;
    // from gate is where the bag is starting from
    private final Gate fromGate;
    // Departure where the bag needs to go
    private final Departures departures;

    public Baggage(String bagId, Gate fromGate, Departures departures) {
        this.bagId = bagId;
        this.fromGate = fromGate;
        this.departures = departures;
    }

    public String getBagId() {
        return bagId;
    }

    public Gate getFromGate() {
        return fromGate;
    }

    public Departures getDepartures() {
        return departures;
    }


    @Override
    public String toString() {
        return "Baggage{" +
                "bagId='" + bagId + '\'' +
                ", fromGate=" + fromGate +
                ", departures=" + departures +
                '}';
    }
}
