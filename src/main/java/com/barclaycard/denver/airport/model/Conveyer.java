package com.barclaycard.denver.airport.model;

public class Conveyer {

    private final Gate source;
    private final Gate destination;
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
