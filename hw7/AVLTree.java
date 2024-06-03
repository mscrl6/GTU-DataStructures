/**
 * Represents an AVL tree that manages stock data with self-balancing
 * capabilities.
 * This tree maintains operations such as insertion, deletion, and search within
 * O(log n) time complexity.
 */
public class AVLTree {
    /**
     * Node class for AVL Tree nodes.
     */
    private class Node {
        Stock stock;
        Node left, right;
        int height;

        /**
         * Constructs a new node with a stock.
         * 
         * @param stock The stock data stored in the node.
         */
        Node(Stock stock) {
            this.stock = stock;
            this.height = 1;
        }
    }

    private Node root;

    /**
     * Inserts a stock into the AVL Tree.
     * If the stock already exists, it updates the existing stock's information.
     * 
     * @param stock The stock to insert or update.
     */

    public void insert(Stock stock) {
        root = insert(root, stock);
    }

    /**
     * Recursive helper method to insert a new stock into the tree or update an
     * existing one.
     * 
     * @param node  The current node in the tree.
     * @param stock The stock to insert or update.
     * @return The new root of the subtree.
     */
    private Node insert(Node node, Stock stock) {
        if (node == null) {
            return new Node(stock);
        }

        int compareResult = stock.getSymbol().compareTo(node.stock.getSymbol());
        if (compareResult < 0) {
            node.left = insert(node.left, stock);
        } else if (compareResult > 0) {
            node.right = insert(node.right, stock);
        } else {
            // Update existing node's stock attributes
            node.stock.setPrice(stock.getPrice());
            node.stock.setVolume(stock.getVolume());
            node.stock.setMarketCap(stock.getMarketCap());
            return node;
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));
        return balance(node);
    }

    /**
     * Deletes a stock from the AVL Tree by its symbol.
     * 
     * @param symbol The symbol of the stock to delete.
     */
    public void delete(String symbol) {
        root = delete(root, symbol);
    }

    /**
     * Recursive helper method to delete a stock from the tree.
     * 
     * @param node   The current node in the tree.
     * @param symbol The symbol of the stock to delete.
     * @return The new root of the subtree.
     */
    private Node delete(Node node, String symbol) {
        if (node == null) {
            return node;
        }

        int compareResult = symbol.compareTo(node.stock.getSymbol());

        if (compareResult < 0) {
            node.left = delete(node.left, symbol);
        } else if (compareResult > 0) {
            node.right = delete(node.right, symbol);
        } else {
            if ((node.left == null) || (node.right == null)) {
                Node temp = (node.left != null) ? node.left : node.right;
                if (temp == null) {
                    temp = node;
                    node = null;
                } else {
                    node = temp;
                }
            } else {
                Node temp = getMinimumNode(node.right);
                node.stock = temp.stock;
                node.right = delete(node.right, temp.stock.getSymbol());
            }
        }

        if (node == null) {
            return node;
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));
        return balance(node);
    }

    /**
     * Searches for a stock in the AVL Tree by its symbol.
     * 
     * @param symbol The symbol of the stock to search for.
     * @return The stock if found, or null if not found.
     */
    public Stock search(String symbol) {
        Node result = search(root, symbol);
        return (result != null) ? result.stock : null;
    }

    /**
     * Recursive helper method to search for a stock in the tree.
     * 
     * @param node   The current node in the tree.
     * @param symbol The symbol of the stock to search for.
     * @return The node containing the stock, or null if not found.
     */
    private Node search(Node node, String symbol) {
        if (node == null || node.stock.getSymbol().equals(symbol)) {
            return node;
        }

        if (symbol.compareTo(node.stock.getSymbol()) < 0) {
            return search(node.left, symbol);
        } else {
            return search(node.right, symbol);
        }
    }

    /**
     * Calculates the height of a given node in the AVL Tree.
     * 
     * @param node The node whose height is to be calculated.
     * @return The height of the node, or 0 if the node is null.
     */
    private int height(Node node) {
        return (node == null) ? 0 : node.height;
    }

    /**
     * Calculates the balance factor of a given node in the AVL Tree.
     * The balance factor is defined as the height difference between the left and
     * right subtree.
     * 
     * @param node The node whose balance factor is to be calculated.
     * @return The balance factor of the node. If the node is null, returns 0.
     */
    private int getBalanceFactor(Node node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    /**
     * Performs a right rotation on the given node in the AVL Tree.
     * This method is called when a node becomes unbalanced with a left-heavy
     * subtree.
     * 
     * @param y The node around which the right rotation is to be performed.
     * @return The new root of the subtree after the rotation.
     */
    private Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        x.right = y;
        y.left = T2;
        y.height = 1 + Math.max(height(y.left), height(y.right));
        x.height = 1 + Math.max(height(x.left), height(x.right));
        return x;
    }

    /**
     * Performs a left rotation on the given node in the AVL Tree.
     * This method is called when a node becomes unbalanced with a right-heavy
     * subtree.
     * 
     * @param x The node around which the left rotation is to be performed.
     * @return The new root of the subtree after the rotation.
     */
    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        y.left = x;
        x.right = T2;
        x.height = 1 + Math.max(height(x.left), height(x.right));
        y.height = 1 + Math.max(height(y.left), height(y.right));
        return y;
    }

    /**
     * Balances the given node in the AVL Tree.
     * It checks the balance factor and decides whether a left rotation, right
     * rotation, or a double rotation is necessary.
     * 
     * @param node The node to balance.
     * @return The balanced node.
     */
    private Node balance(Node node) {
        int balanceFactor = getBalanceFactor(node);
        if (balanceFactor > 1) {
            if (getBalanceFactor(node.left) < 0) {
                node.left = rotateLeft(node.left);
            }
            return rotateRight(node);
        }
        if (balanceFactor < -1) {
            if (getBalanceFactor(node.right) > 0) {
                node.right = rotateRight(node.right);
            }
            return rotateLeft(node);
        }
        return node;
    }

    /**
     * Finds and returns the node with the minimum value in the given subtree.
     * 
     * @param node The root node of the subtree.
     * @return The node with the minimum value in the subtree.
     */
    private Node getMinimumNode(Node node) {
        Node current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    /**
     * Performs an in-order traversal of the AVL Tree starting from the root.
     * This method prints the stock information of each node in ascending order.
     */
    public void inOrderTraversal() {
        inOrderTraversal(root);
    }

    /**
     * Helper method to recursively perform an in-order traversal of a subtree.
     * 
     * @param node The current node in the subtree.
     */
    private void inOrderTraversal(Node node) {
        if (node != null) {
            inOrderTraversal(node.left);
            System.out.println(node.stock);
            inOrderTraversal(node.right);
        }
    }

    /**
     * Performs a pre-order traversal of the AVL Tree starting from the root.
     * This method prints the stock information as it visits each node before its
     * children.
     */
    public void preOrderTraversal() {
        preOrderTraversal(root);
    }

    /**
     * Helper method to recursively perform a pre-order traversal of a subtree.
     * 
     * @param node The current node in the subtree.
     */
    private void preOrderTraversal(Node node) {
        if (node != null) {
            System.out.println(node.stock);
            preOrderTraversal(node.left);
            preOrderTraversal(node.right);
        }
    }

    /**
     * Performs a post-order traversal of the AVL Tree starting from the root.
     * This method prints the stock information as it visits each node after its
     * children.
     */
    public void postOrderTraversal() {
        postOrderTraversal(root);
    }

    /**
     * Helper method to recursively perform a post-order traversal of a subtree.
     * 
     * @param node The current node in the subtree.
     */
    private void postOrderTraversal(Node node) {
        if (node != null) {
            postOrderTraversal(node.left);
            postOrderTraversal(node.right);
            System.out.println(node.stock);
        }
    }
}
