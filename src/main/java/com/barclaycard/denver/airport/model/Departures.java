package com.barclaycard.denver.airport.model;
/***
 * 
 * @author hqcpundr
 * Departures class to store the flight departure information.
 */
public class Departures {
    private final String flightId;
    // The gate where the flight is taking off from
    private final Gate gate;
    // Destination of the fligh
    private final String destination;
    // Flight Departure time
    private final String time;

    public Departures(String flightId, Gate gate, String destination, String time) {
        this.flightId = flightId;
        this.gate = gate;
        this.destination = destination;
        this.time = time;
    }

    public String getFlightId() {
        return flightId;
    }

    public Gate getGate() {
        return gate;
    }

    public String getDestination() {
        return destination;
    }

    public String getTime() {
        return time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Departures)) return false;

        Departures that = (Departures) o;

        return getFlightId().equals(that.getFlightId());
    }

    @Override
    public int hashCode() {
        return getFlightId().hashCode();
    }

    @Override
    public String toString() {
        return "Departures{" +
                "flightId='" + flightId + '\'' +
                ", gate=" + gate +
                ", destination='" + destination + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
