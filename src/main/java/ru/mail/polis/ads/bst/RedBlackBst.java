package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * LLRB implementation of binary search tree.
 */
public class RedBlackBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

    private static final boolean BLACK = false;
    private static final boolean RED = true;

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        boolean color;

        public Node(Key key, Value value, boolean color) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            this.color = color;
        }
    }

    private Node root;
    int bstsize = 0;

    private boolean isRed(Node x) {
        return (x != null) && (x.color == RED);
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        y.color = x.color;
        x.color = RED;
        return y;
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        y.left = x.right;
        x.right = y;
        x.color = y.color;
        y.color = RED;
        return x;
    }

    private Node flipColors(Node x) {
        x.color = !x.color;
        x.left.color = !x.left.color;
        x.right.color = !x.right.color;
        return x;
    }

    private Node fixUp(Node x) {
        if (isRed(x.right) && !isRed(x.left))
            x = rotateLeft(x);
        if (isRed(x.left) && isRed(x.left.left))
            x = rotateRight(x);
        if (isRed(x.left) && isRed(x.right))
            flipColors(x);
        return x;
    }
    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        Node x = get(root, key);
        if (x == null) {
            return null;
        }
        return x.value;
    }

    private Node get(Node x, Key key) {
        if (x == null) return null;
        int keyCompare = key.compareTo(x.key);
        if (keyCompare < 0) return get(x.left, key);
        if (keyCompare > 0) return get(x.right, key);
        return x;
    }
    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) {
            bstsize++;
            return new Node(key, value, RED);
        }
        int keyCompare = key.compareTo(x.key);
        if (keyCompare < 0) {
            x.left = put(x.left, key, value);
        } else if (keyCompare > 0) {
            x.right = put(x.right, key, value);
        } else {
            x.value = value;
        }
        return fixUp(x);
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        Value x = get(key);
        if (x == null) {
            return null;
        }
        root.color = RED;
        root = remove(root, key);
        bstsize--;
        return x;
    }
    private Node deleteMin(Node x) {
        if (x.left == null)
            return null;
        if (!isRed(x.left) && !isRed(x.left.left))
            x = moveRedLeft(x);
        x.left = deleteMin(x.left);
        return fixUp(x);
    }

    private Node deleteMax(Node x) {
        if (isRed(x.left))
            x = rotateRight(x);
        if (x.right == null)
            return null;
        if (!isRed(x.right) && !isRed(x.right.left))
            x = moveRedRight(x);
        x.right = deleteMax(x.right);
        return fixUp(x);
    }

    private Node remove(Node x, Key key) {
        if (x == null)
            return null;
        int keyCompare = key.compareTo(x.key);
        if (keyCompare < 0) {
            if (x.left != null) {
                if (!isRed(x.left) && !isRed(x.left.left)) {
                    x = moveRedLeft(x);
                }
                x.left = remove(x.left, key);
            }
        } else {
            if (isRed(x.left)) {
                x = rotateRight(x);
                x.right = remove(x.right, key);
            } else if ((keyCompare == 0) && (x.right == null)) {
                return null;
            } else {
                if (x.right != null && !isRed(x.right) && !isRed(x.right.left)) {
                    x = moveRedRight(x);
                }
                if (x.key == key) {
                    Node minNode = min(x.right);
                    x.key = minNode.key;
                    x.value = minNode.value;
                    x.right = deleteMin(x.right);
                } else {
                    x.right = remove(x.right, key);
                }
            }
        }
        return fixUp(x);
    }

    private Node moveRedLeft(Node x) {
        flipColors(x);
        if (isRed(x.right.left)) {
            x.right = rotateRight(x.right);
            x = rotateLeft(x);
            flipColors(x);
        }
        return x;
    }

    private Node moveRedRight(Node x) {
        flipColors(x);
        if (!isRed(x.left.left))
            x = rotateRight(x);
        return x;
    }

    @Nullable
    @Override
    public Key min() {
        Node x = min(root);
        if (x == null) {
            return null;
        }
        return x.key;
    }

    private Node min(Node x) {
        if (x == null) {
            return null;
        }
        if (x.left == null) {
            return x;
        }
        return min(x.left);
    }

    @Nullable
    @Override
    public Value minValue() {
        Node x = min(root);
        if (x == null) {
            return null;
        }
        return x.value;
    }

    @Nullable
    @Override
    public Key max() {
        Node x = max(root);
        if (x == null) {
            return null;
        }
        return x.key;
    }

    private Node max(Node x) {
        if (x == null) {
            return null;
        }
        if (x.right == null) {
            return x;
        }
        return max(x.right);
    }

    @Nullable
    @Override
    public Value maxValue() {
        Node x = max(root);
        if (x == null) {
            return null;
        }
        return x.value;
    }

    @Nullable
    @Override
    public Key floor(@NotNull Key key) {
        return floor(root, key, null);
    }

    private Key floor(Node node, Key key, Key maxKey) {
        if (node == null) {
            return maxKey;
        }
        if (node.key == key) {
            maxKey = node.key;
        }
        int keyCompare = key.compareTo(node.key);
        if (keyCompare < 0) {
            maxKey = floor(node.left, key, maxKey);
        } else if (keyCompare > 0) {
            maxKey = node.key;
            maxKey = floor(node.right, key, maxKey);
        }
        return maxKey;
    }

    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
        Node node = root;
        return ceil(node, key);
    }

    private Key ceil(Node node, Key key) {
        if (node == null) {
            return null;
        }
        if (node.key == key) {
            return node.key;
        }
        int keyCompare = key.compareTo(node.key);
        if (keyCompare < 0) {
            return node.left == null ? node.key : ceil(node.left, key);
        } else if (keyCompare > 0) {
            return node.right == null ? null : ceil(node.right, key);
        }
        return node.key;
    }

    @Override
    public int size() {
        return bstsize;
    }
}
