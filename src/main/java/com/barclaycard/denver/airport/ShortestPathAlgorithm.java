package com.barclaycard.denver.airport;

import com.barclaycard.denver.airport.model.Conveyer;
import com.barclaycard.denver.airport.model.Gate;

import java.util.*;

public class ShortestPathAlgorithm {

        private final List<Gate> nodes;
        private final List<Conveyer> Conveyers;
        private Set<Gate> settledNodes;
        private Set<Gate> unSettledNodes;
        private Map<Gate, Gate> predecessors;
        private Map<Gate, Integer> distance;

        public ShortestPathAlgorithm(Graph graph) {
            // create a copy of the array so that we can operate on this array
            this.nodes = new ArrayList<>(graph.getGates());
            this.Conveyers = new ArrayList<Conveyer>(graph.getConveyers());
        }

        public void execute(Gate source) {
            settledNodes = new HashSet<>();
            unSettledNodes = new HashSet<Gate>();
            distance = new HashMap<Gate, Integer>();
            predecessors = new HashMap<Gate, Gate>();
            distance.put(source, 0);
            unSettledNodes.add(source);
            while (unSettledNodes.size() > 0) {
                Gate node = getMinimum(unSettledNodes);
                settledNodes.add(node);
                unSettledNodes.remove(node);
                findMinimalDistances(node);
            }
        }

        private void findMinimalDistances(Gate node) {
            List<Gate> adjacentNodes = getNeighbors(node);
            for (Gate target : adjacentNodes) {
                if (getShortestDistance(target) > getShortestDistance(node)
                        + getDistance(node, target)) {
                    distance.put(target, getShortestDistance(node)
                            + getDistance(node, target));
                    predecessors.put(target, node);
                    unSettledNodes.add(target);
                }
            }

        }

        private int getDistance(Gate node, Gate target) {
            for (Conveyer Conveyer : Conveyers) {
                if (Conveyer.getSource().equals(node)
                        && Conveyer.getDestination().equals(target)) {
                    return Conveyer.getWeight();
                }
            }
            throw new RuntimeException("Should not happen");
        }

        private List<Gate> getNeighbors(Gate node) {
            List<Gate> neighbors = new ArrayList<Gate>();
            for (Conveyer Conveyer : Conveyers) {
                if (Conveyer.getSource().equals(node)
                        && !isSettled(Conveyer.getDestination())) {
                    neighbors.add(Conveyer.getDestination());
                }
            }
            return neighbors;
        }

        private Gate getMinimum(Set<Gate> Gatees) {
            Gate minimum = null;
            for (Gate Gate : Gatees) {
                if (minimum == null) {
                    minimum = Gate;
                } else {
                    if (getShortestDistance(Gate) < getShortestDistance(minimum)) {
                        minimum = Gate;
                    }
                }
            }
            return minimum;
        }

        private boolean isSettled(Gate Gate) {
            return settledNodes.contains(Gate);
        }

        public int getShortestDistance(Gate destination) {
            Integer d = distance.get(destination);
            if (d == null) {
                return Integer.MAX_VALUE;
            } else {
                return d;
            }
        }

        /*
         * This method returns the path from the source to the selected target and
         * NULL if no path exists
         */
        public LinkedList<Gate> getPath(Gate target) {
            LinkedList<Gate> path = new LinkedList<Gate>();
            Gate step = target;
            // check if a path exists
            if (predecessors.get(step) == null) {
                return null;
            }
            path.add(step);
            while (predecessors.get(step) != null) {
                step = predecessors.get(step);
                path.add(step);
            }
            // Put it into the correct order
            Collections.reverse(path);
            return path;
        }

}
