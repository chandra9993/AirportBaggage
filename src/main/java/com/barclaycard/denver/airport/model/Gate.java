package com.barclaycard.denver.airport.model;

public class Gate {
private final String gateId;

    public Gate(String gateId) {
        this.gateId = gateId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Gate gate = (Gate) o;

        return gateId != null ? gateId.equals(gate.gateId) : gate.gateId == null;
    }

    @Override
    public int hashCode() {
        return gateId != null ? gateId.hashCode() : 0;
    }

    public String getGateId() {
        return gateId;
    }


    @Override
    public String toString() {
        return "Gate{" +
                "gateId='" + gateId + '\'' +
                '}';
    }
}
