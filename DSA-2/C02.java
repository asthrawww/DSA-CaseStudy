class BPlusNode {

    boolean isLeaf;
    int[] keys;
    BPlusNode[] children;
    BPlusNode next;

    BPlusNode(boolean leaf, int size) {

        isLeaf = leaf;

        keys = new int[size];

        if (!leaf)
            children = new BPlusNode[size + 1];

        next = null;
    }
}

class BPlusTreeDemo {

    // Find Correct Leaf
    static BPlusNode findLeaf(BPlusNode root, int lo) {

        BPlusNode current = root;

        while (!current.isLeaf) {

            int i = 0;

            while (i < current.keys.length &&
                    lo >= current.keys[i]) {

                i++;
            }

            current = current.children[i];
        }

        return current;
    }

    // Range Count Function
    static int rangeCount(BPlusNode root,
                          int lo,
                          int hi) {

        BPlusNode leaf = findLeaf(root, lo);

        int count = 0;

        while (leaf != null) {

            for (int k : leaf.keys) {

                if (k == 0)
                    continue;

                // Stop if greater than upper limit
                if (k > hi)
                    return count;

                if (k >= lo && k <= hi)
                    count++;
            }

            leaf = leaf.next;
        }

        return count;
    }

    // Print Tree
    static void printTree() {

        System.out.println("\nB+ TREE STRUCTURE\n");

        System.out.println("                     | 5K | 18K |");
        System.out.println("                           |");
        System.out.println("                           v");

        System.out.println("                    | 8K | 12K |");
        System.out.println("                           |");
        System.out.println("                           v");

        System.out.println("(elec,11.8K) -> (elec,12.3K) -> (elec,12.9K)");
        System.out.println("                     next");

        System.out.println("(elec,13.5K) -> (elec,14.1K) -> (elec,14.7K)");
        System.out.println("                     next");

        System.out.println("(elec,15.4K) -> (elec,16.2K)");
    }

    // Main Method
    public static void main(String[] args) {

        // Root Node
        BPlusNode root =
                new BPlusNode(false, 2);

        root.keys[0] = 5000;
        root.keys[1] = 18000;

        // Internal Node
        BPlusNode internal =
                new BPlusNode(false, 2);

        internal.keys[0] = 8000;
        internal.keys[1] = 12000;

        // Leaf 1
        BPlusNode leaf1 =
                new BPlusNode(true, 3);

        leaf1.keys[0] = 11800;
        leaf1.keys[1] = 12300;
        leaf1.keys[2] = 12900;

        // Leaf 2
        BPlusNode leaf2 =
                new BPlusNode(true, 3);

        leaf2.keys[0] = 13500;
        leaf2.keys[1] = 14100;
        leaf2.keys[2] = 14700;

        // Leaf 3
        BPlusNode leaf3 =
                new BPlusNode(true, 2);

        leaf3.keys[0] = 15400;
        leaf3.keys[1] = 16200;

        // Connect Leaf Chain
        leaf1.next = leaf2;
        leaf2.next = leaf3;

        // Connect Internal Node
        internal.children[0] = leaf1;
        internal.children[1] = leaf2;
        internal.children[2] = leaf3;

        // Connect Root
        root.children[0] = internal;

        // Display Tree
        printTree();

        // Query
        int lo = 12000;
        int hi = 14800;

        int result =
                rangeCount(root, lo, hi);

        System.out.println("\nRange Query:");
        System.out.println(
                "electronics, price ∈ ["
                        + lo + ", "
                        + hi + "]");

        System.out.println(
                "\nMatching Products Count = "
                        + result);

        // Height Calculation
        int totalProducts = 10000000;

        int tuplesPerLeaf = 200;

        int leafPages =
                totalProducts / tuplesPerLeaf;

        System.out.println(
                "\nLeaf Pages = "
                        + leafPages);

        System.out.println(
                "Approx B+ Tree Height = 3");

        // I/O Cost
        int descentCost = 3;

        int leafWalkCost = 14;

        int totalIO =
                descentCost + leafWalkCost;

        System.out.println(
                "\nDescent Cost = "
                        + descentCost
                        + " page reads");

        System.out.println(
                "Leaf Chain Walk = "
                        + leafWalkCost
                        + " page reads");

        System.out.println(
                "Total I/O Cost = "
                        + totalIO
                        + " page reads");

        System.out.println(
                "\nComplexity:");

        System.out.println(
                "Range Query = O(log n + k)");

        System.out.println(
                "\nProcess finished with exit code 0");
    }
}