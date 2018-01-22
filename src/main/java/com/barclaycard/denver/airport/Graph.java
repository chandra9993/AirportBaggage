package com.barclaycard.denver.airport;

import com.barclaycard.denver.airport.model.Conveyer;
import com.barclaycard.denver.airport.model.Gate;

import java.util.List;
/***
 * 
 * @author hqcpundr
 * Building a graph Data structure from edges and vertices
 */
public class Graph {
    // Edges of the Graph data structure
    private final List<Gate> gates;
    // Vertices of the Graph Data Structure
    private final List<Conveyer> conveyers;

    public Graph(List<Gate> Gates, List<Conveyer> Conveyers) {
        this.gates = Gates;
        this.conveyers = Conveyers;
    }

    public List<Gate> getGates() {
        return gates;
    }

    public List<Conveyer> getConveyers() {
        return conveyers;
    }



}
