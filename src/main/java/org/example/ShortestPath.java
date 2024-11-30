package org.example;

import java.util.*;

public class ShortestPath {
    static class Edge {
        int targetCityIndex, transportationCost;

        public Edge(int targetCityIndex, int transportationCost) {
            this.targetCityIndex = targetCityIndex;
            this.transportationCost = transportationCost;
        }
    }
    /**
     * Here we will need to use Dijkstra's algorithm to find the shortest path(minimum transportation cost) between two cities.
     *
     * @param graph           a map where each city index maps to a list of edges, representing neighboring cities and transportation costs.
     * @param startCityIndex  the index of the starting city.
     * @param endCityIndex    the index of the destination city.
     * @param totalCities     the total number of cities in the graph.
     * @return                the minimum transportation cost between the starting city and the destination city,
     *                        or -1 if no path exists.
     */
    public static int dijkstra(Map<Integer, List<Edge>> graph, int startCityIndex, int endCityIndex, int totalCities) {
        int[] minimumCosts = new int[totalCities + 1]; // array to store the minimum cost to reach each city
        Arrays.fill(minimumCosts, Integer.MAX_VALUE); // initialize cities like they are the most expensive
        minimumCosts[startCityIndex] = 0; // the cost to reach the starting city is 0.

        // priority queue to process cities based on the minimum transportation cost - Dijkstra's algorithm
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        priorityQueue.add(new int[]{startCityIndex, 0}); // Start with the first city and go through others

        // process the priority queue until all reachable cities have been evaluated
        while (!priorityQueue.isEmpty()) {

            // get the city with the currently lowest transportation cost
            int[] currentCity = priorityQueue.poll();
            int currentCityIndex = currentCity[0];
            int currentCost = currentCity[1];

            // if the current city is the destination city, return the accumulated cost
            if (currentCityIndex == endCityIndex) {
                return currentCost;
            }

            // iterate through all neighbor cities of the current city
            for (Edge edge : graph.getOrDefault(currentCityIndex, new ArrayList<>())) {
                int newCost = currentCost + edge.transportationCost;

                // if a cheaper path to the neighbor city is found, update and add to the queue
                if (newCost < minimumCosts[edge.targetCityIndex]) {
                    minimumCosts[edge.targetCityIndex] = newCost;  // update the minimum cost
                    priorityQueue.add(new int[]{edge.targetCityIndex, newCost}); // add the neighbor city for further evaluation
                }
            }
        }

        return -1; // No path found
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of test cases: ");
        int testCases = scanner.nextInt();
        scanner.nextLine(); // next line

        while (testCases-- > 0) {
            System.out.print("Enter the number of cities: ");
            int totalCities = scanner.nextInt();
            scanner.nextLine(); // next line

            Map<String, Integer> cityNameToIndex = new HashMap<>();
            Map<Integer, String> cityIndexToName = new HashMap<>();
            Map<Integer, List<Edge>> graph = new HashMap<>();

            for (int cityIndex = 1; cityIndex <= totalCities; cityIndex++) { // go through the cities one by one
                System.out.print("Enter city name: ");
                String cityName = scanner.nextLine();

                cityNameToIndex.put(cityName, cityIndex);
                cityIndexToName.put(cityIndex, cityName);

                System.out.print("Enter the number of neighbors for city " + cityName + ": ");
                int numberOfNeighbors = scanner.nextInt();
                scanner.nextLine(); // next line

                List<Edge> edges = graph.getOrDefault(cityIndex, new ArrayList<>()); // create a list of edges for each city-neighbour
                for (int neighbor = 0; neighbor < numberOfNeighbors; neighbor++) {
                    System.out.print("Enter neighbor city index and transportation cost: ");
                    int neighborCityIndex = scanner.nextInt();
                    int transportationCost = scanner.nextInt();

                    edges.add(new Edge(neighborCityIndex, transportationCost));
                }
                scanner.nextLine(); // next line
                graph.put(cityIndex, edges);
            }

            System.out.print("Enter the number of paths to find: ");
            int numberOfPathsToFind = scanner.nextInt();
            scanner.nextLine(); // next line

            for (int pathIndex = 0; pathIndex < numberOfPathsToFind; pathIndex++) {
                System.out.print("Enter source and destination cities (e.g., 'city1 city2'): ");
                String[] query = scanner.nextLine().split(" ");
                String sourceCity = query[0];
                String destinationCity = query[1];

                int sourceCityIndex = cityNameToIndex.get(sourceCity);
                int destinationCityIndex = cityNameToIndex.get(destinationCity);

                int minimumCost = dijkstra(graph, sourceCityIndex, destinationCityIndex, totalCities);
                System.out.println("Minimum transportation cost from " + sourceCity + " to " + destinationCity + ": " + minimumCost);
            }

            if (testCases > 0) {
                System.out.println("Press Enter to continue to the next test case...");
                scanner.nextLine(); // Consume blank line
            }
        }

        scanner.close();
    }
}
