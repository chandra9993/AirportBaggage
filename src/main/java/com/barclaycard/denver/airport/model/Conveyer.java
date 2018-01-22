package com.barclaycard.denver.airport.model;
/***
 * 
 * @author hqcpundr
 * Class to store the conveayer information
 */
public class Conveyer {

    // Source gate where the conveyer starts
    private final Gate source;
    // Destination gate where the conveyer ends
    private final Gate destination;
    // Wight (time taken)  for this route
    private final int weight;

    public Conveyer(Gate source, Gate destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public Gate getSource() {
        return source;
    }

    public Gate getDestination() {
        return destination;
    }

    public int getWeight() {
        return weight;
    }
}
