package structures.trees;

public class RBTree<TKey extends Comparable<TKey>, TValue> {
    protected static final boolean RED = true;
    protected static final boolean BLACK = false;

    protected class Node {
        public TKey key;
        public TValue value;
        public Node left, right;

        boolean color;
        int size;

        Node(TKey key, TValue value, int size, boolean color) {
            this.key = key;
            this.value = value;

            this.size = size;
            this.color = color;
        }
    }

    protected Node root;

    private boolean isRed(Node node) {
        if (node == null) return false;

        return node.color == RED;
    }

    private boolean isBlack(Node node) {
        if (node == null) return false;

        return node.color == BLACK;
    }

    public int size() {
        return size(root);
    }

    protected int size(Node node) {
        if (node == null) {
            return 0;
        }

        return node.size;
    }

    public boolean isEmpty() {
        return size(root) == 0;
    }

    /**
     * Rotação à equerda
     * @param node
     * @return
     */
    protected Node rotateLeft(Node node) {
        if (node == null || node.right == null) {
            return node;
        }

        Node newRoot = node.right;

        node.right = newRoot.left;
        newRoot.left = node;

        newRoot.color = RED;

        newRoot.size = node.size;
        newRoot.size = size(node.left) + 1 + size(node.left);

        return newRoot;
    }

    /**
     * Implementar o esse método
     * @param node
     * @return
     */
    private Node rotateRight(Node node) {
        // Implementar método que aplica a rotação à direita.
        Node x = node.left;
        node.left = x.right;
        x.right = node;
        x.color = node.color;
        node.color = RED;
        x.size = node.size;
        node.size = size(node.left) + size(node.right) + 1;
        return x;
    }
    private void flipColor(Node node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }


    /**
     * Insere um novo nó
     * @param key
     * @param val
     */
    public void insert(TKey key, TValue val){
        root = insert(root, key, val);
        root.color = BLACK;
    }

    private Node insert(Node node, TKey key, TValue value)
    {
        if (node == null) // Do standard insert, with red link to parent.
            return new Node(key, value, 1, RED);

        int cmp = key.compareTo(node.key);
        if (cmp < 0)
            node.left = insert(node.left, key, value);
        else if (cmp > 0)
            node.right = insert(node.right, key, value);
        else node.value = value;

        if (isRed(node.right) && !isRed(node.left))
            node = rotateLeft(node);
        if (isRed(node.left) && isRed(node.left.left))
            node = rotateRight(node);
        if (isRed(node.left) && isRed(node.left))
            flipColor(node);

        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }
}

