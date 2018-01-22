package com.barclaycard.denver.airport.model;
/***
 * 
 * @author hqcpundr
 *
 */
public class Baggage {
    private final String bagId;
    private final Gate fromGate;
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
