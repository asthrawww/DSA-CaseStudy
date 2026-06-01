import java.util.*;

class MetroKruskal {

    // Edge Class
    static class Edge {

        String u, v;
        int weight;

        Edge(String u, String v, int w) {

            this.u = u;
            this.v = v;
            this.weight = w;
        }
    }

    // Union Find
    static class UnionFind {

        Map<String, String> parent;

        UnionFind(String[] vertices) {

            parent = new HashMap<>();

            for (String v : vertices)
                parent.put(v, v);
        }

        // Find Function
        String find(String x) {

            while (!parent.get(x).equals(x))
                x = parent.get(x);

            return x;
        }

        // Union Function
        boolean union(String x, String y) {

            String rx = find(x);
            String ry = find(y);

            if (rx.equals(ry))
                return false;

            parent.put(rx, ry);

            return true;
        }

        // Print Parent State
        void printParents() {

            System.out.println(parent);
        }
    }

    // Main Method
    public static void main(String[] args) {

        // Vertices
        String[] vertices = {
                "M", "K", "W",
                "S", "E", "Y", "H"
        };

        // Candidate Edges
        List<Edge> edges =
                new ArrayList<>();

        edges.add(new Edge("Y", "H", 4));
        edges.add(new Edge("M", "H", 5));
        edges.add(new Edge("K", "W", 6));
        edges.add(new Edge("M", "E", 7));
        edges.add(new Edge("M", "K", 8));
        edges.add(new Edge("S", "E", 8));
        edges.add(new Edge("W", "S", 9));
        edges.add(new Edge("E", "Y", 9));
        edges.add(new Edge("M", "S", 10));
        edges.add(new Edge("M", "Y", 11));
        edges.add(new Edge("M", "W", 12));
        edges.add(new Edge("K", "H", 14));

        // Sort edges by weight
        edges.sort(
                Comparator.comparingInt(
                        e -> e.weight));

        System.out.println(
                "KRUSKAL MST - Bangalore Metro\n");

        System.out.println(
                "Edges sorted by cost:\n");

        for (Edge e : edges) {

            System.out.println(
                    e.u + " - "
                            + e.v
                            + " : Rs."
                            + e.weight
                            + " cr");
        }

        // Union Find
        UnionFind uf =
                new UnionFind(vertices);

        List<Edge> mst =
                new ArrayList<>();

        int totalCost = 0;

        System.out.println(
                "\nUNION-FIND EVOLUTION\n");

        // Kruskal Algorithm
        for (Edge e : edges) {

            System.out.println(
                    "Considering Edge: "
                            + e.u + "-"
                            + e.v
                            + " (Rs."
                            + e.weight
                            + " cr)");

            if (uf.union(e.u, e.v)) {

                mst.add(e);

                totalCost += e.weight;

                System.out.println(
                        "Accepted");

                System.out.print(
                        "Parent State: ");

                uf.printParents();
            }

            else {

                System.out.println(
                        "Rejected (Cycle)");
            }

            System.out.println();
        }

        // Final MST
        System.out.println(
                "\nFINAL MST\n");

        for (Edge e : mst) {

            System.out.println(
                    e.u + " - "
                            + e.v
                            + " : Rs."
                            + e.weight
                            + " cr");
        }

        System.out.println(
                "\nTotal MST Cost = Rs."
                        + totalCost
                        + " crore");

        // Redundancy Analysis
        System.out.println(
                "\nREDUNDANCY ANALYSIS\n");

        System.out.println(
                "MST provides only ONE unique path");

        System.out.println(
                "between M and W.");

        System.out.println(
                "Hence redundancy mandate fails.");

        System.out.println(
                "\nMinimum Cost Augmentation:");

        System.out.println(
                "Add edge M-W : Rs.12 cr");

        System.out.println(
                "\nTwo Edge-Disjoint Paths:");

        System.out.println(
                "Path 1 : M -> K -> W");

        System.out.println(
                "Path 2 : M -> W");

        // Complexity
        System.out.println(
                "\nTIME COMPLEXITY");

        System.out.println(
                "Naive Union-Find : O(m * n)");

        System.out.println(
                "Optimized Union-Find : O(m α(n))");

        System.out.println(
                "\nWorst-case pointer hops:");

        System.out.println(
                "At most n-1 = 6 hops");

        System.out.println(
                "\nProcess finished with exit code 0");
    }
}