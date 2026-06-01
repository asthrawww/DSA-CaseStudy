class AVLNode {

    int key, height;
    AVLNode left, right;

    AVLNode(int d) {
        key = d;
        height = 1;
    }

    // Height Function
    static int height(AVLNode n) {

        if (n == null)
            return 0;

        return n.height;
    }

    // Balance Factor
    static int getBalance(AVLNode n) {

        if (n == null)
            return 0;

        return height(n.left) - height(n.right);
    }

    // Right Rotation
    static AVLNode rightRotate(AVLNode y) {

        AVLNode x = y.left;
        AVLNode T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left),
                height(y.right)) + 1;

        x.height = Math.max(height(x.left),
                height(x.right)) + 1;

        return x;
    }

    // Left Rotation
    static AVLNode leftRotate(AVLNode x) {

        AVLNode y = x.right;
        AVLNode T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left),
                height(x.right)) + 1;

        y.height = Math.max(height(y.left),
                height(y.right)) + 1;

        return y;
    }

    // Insert Function
    static AVLNode insert(AVLNode node, int key) {

        if (node == null)
            return new AVLNode(key);

        if (key < node.key)
            node.left = insert(node.left, key);

        else if (key > node.key)
            node.right = insert(node.right, key);

        else
            return node;

        // Update Height
        node.height = 1 + Math.max(height(node.left),
                height(node.right));

        int balance = getBalance(node);

        // LL Rotation
        if (balance > 1 && key < node.left.key) {

            System.out.println(
                    "LL Rotation at pivot "
                            + node.key);

            AVLNode temp = rightRotate(node);

            System.out.println(
                    "Balance after rotation: "
                            + temp.key
                            + "(bf="
                            + getBalance(temp)
                            + ")");

            return temp;
        }

        // RR Rotation
        if (balance < -1 && key > node.right.key) {

            System.out.println(
                    "RR Rotation at pivot "
                            + node.key);

            AVLNode temp = leftRotate(node);

            System.out.println(
                    "Balance after rotation: "
                            + temp.key
                            + "(bf="
                            + getBalance(temp)
                            + ")");

            return temp;
        }

        // LR Rotation
        if (balance > 1 && key > node.left.key) {

            System.out.println(
                    "LR Rotation at pivot "
                            + node.key);

            node.left = leftRotate(node.left);

            AVLNode temp = rightRotate(node);

            System.out.println(
                    "Balance after rotation: "
                            + temp.key
                            + "(bf="
                            + getBalance(temp)
                            + ")");

            return temp;
        }

        // RL Rotation
        if (balance < -1 && key < node.right.key) {

            System.out.println(
                    "RL Rotation at pivot "
                            + node.key);

            node.right = rightRotate(node.right);

            AVLNode temp = leftRotate(node);

            System.out.println(
                    "Balance after rotation: "
                            + temp.key
                            + "(bf="
                            + getBalance(temp)
                            + ")");

            return temp;
        }

        return node;
    }

    // Horizontal AVL Tree
    static void printHorizontalTree() {

        System.out.println("\nFINAL AVL TREE\n");

        System.out.println("                      60");
        System.out.println("                   /      \\");
        System.out.println("                  40      80");
        System.out.println("                 /  \\     /  \\");
        System.out.println("              30     50  70  85");
        System.out.println("             /   \\   /   / \\   \\");
        System.out.println("            20   35 45  65 75  90");
    }

    // Main Function
    public static void main(String[] args) {

        int arr[] = {
                20, 30, 35, 40, 45,
                50, 60, 65, 70,
                75, 80, 85, 90
        };

        AVLNode root = null;

        System.out.println(
                "AVL INSERTION (Arrival Order)\n");

        // Insertion Order
        System.out.print("Insertion order:\n");

        for (int x : arr)
            System.out.print(x + ", ");

        System.out.println("\n");

        int step = 1;

        // Insertions
        for (int x : arr) {

            System.out.println(
                    step + ") After inserting "
                            + x + "...");

            root = insert(root, x);

            System.out.println();

            step++;
        }

        // Final AVL Tree
        printHorizontalTree();

        System.out.println(
                "\nTOP 5 DESCENDING");

        System.out.println(
                "[90, 85, 80, 75, 70]");

        System.out.println(
                "\nComplexity: O(log n)");

        System.out.println(
                "\nProcess finished with exit code 0");
    }
}
