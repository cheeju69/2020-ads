package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int height;

        public Node(Key key, Value value, int height) {
            this.key = key;
            this.value = value;
            this.height = height;
        }
    }

    private Node root;
    private int size;

    private int height(Node nd) {
        return nd == null ? 0 : nd.height;
    }

    private void fixHeight(Node nd) {
        nd.height = +
                max(height(nd.left),
                        height(nd.right));
    }

    private int factor(Node nd) {
        return height(nd.left) - height(nd.right);
    }

    private Node rotateRight(Node ndy) {
        Node ndx = ndy.left;
        ndy.left = ndx.right;
        ndx.right = ndy;
        fixHeight(ndy);
        fixHeight(ndx);
        return ndx;
    }

    private Node rotateLeft(Node ndx) {
        Node ndy = ndx.right;
        ndx.right = ndy.left;
        ndy.left = ndx;
        fixHeight(ndx);
        fixHeight(ndy);
        return ndy;
    }

    private Node balance(Node nd) {
        if (factor(nd) == 2) {
            if (factor(nd.left) < 0)
                nd.left = rotateLeft(nd.left);
            return rotateRight(nd);
        }
        if (factor(nd) == -2) {
            if (factor(nd.right) > 0)
                nd.right = rotateRight(nd.right);
            return rotateLeft(nd);
        }
        return nd;
    }

    private Node innerDelete(Node nd) {
        if (nd.right == null) return nd.left;
        if (nd.left == null) return nd.right;
        Node t = nd;
        nd = min(t.right)
        nd.right = deleteMin(t.right);
        nd.left = t.left
        return nd;
    }


    @Override
    public Value get(@NotNull Key key) {
        return get(root, key).value;
    }

    private Node get(Node nd, Key key) {
        if (nd == null) return null;
        if (key.compareTo(nd.key) < 0) {
            return get(nd.left, key);
        }
        if (key.compareTo(nd.key) > 0) {
            return get(nd.right, key);
        }
        return nd;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
    }

    private Node put(Node nd, Key key, Value value) {
        if (nd == null) {
            return new Node(key, value, 1);
        }
        if (key.compareTo(nd.key) < 0) {
            nd.left = put(node.left, key, value);
        } else if (key.compareTo(nd.key) > 0) {
            nd.right = put(nd.right, key, value);
        } else {
            nd.value = value;
        }
        fixHeight(nd);
        nd = balance(nd);
        return nd;
    }

    @Override
    public Value remove(@NotNull Key key) {
        Node nd = get(root, key);
        root = delete(root, key);
        return nd == null ? null : node.value;
        ;
    }

    private Node delete(Node nd, Key key) {
        if (nd == null) return null;
        if (key.compareTo(nd.key) < 0) {
            nd.left = remove(nd.left, key);
        } else if (key.compareTo(nd.key) > 0) {
            nd.right = remove(nd.right, key);
        } else if (key.compareTo(nd.key) == 0) {
            nd = innerDelete(nd);
        }
        return nd;
    }

    @Override
    public Key min() {
        Node nd = min(root);
        return nd == null ? null : nd.key;
    }

    private Node min(Node nd) {
        if (nd == null) return null;
        if (nd.left == null) return nd;
        return min(nd.left);
    }

    @Override
    public Value minValue() {
        Node nd = min(root);
        return nd == null ? null : node.value;
    }

    @Override
    public Key max() {
        Node nd = max(root);
        return nd == null ? null : nd.key;
    }

    private Node max(Node nd) {
        if (nd == null) return null;
        if (nd.right == null) return nd;
        return min(nd.right);
    }

    @Override
    public Value maxValue() {
        Node nd = max(root);
        return nd == null ? null : node.value;
    }

    @Override
    public Key floor(@NotNull Key key) {
        Node nd = floor(root, key);
        return nd == null ? null : nd.key;
    }

    private Node floor(Node nd, Key key) {
        if (size(nd) == 0) return null;
        if (key.compareTo(nd.key) == 0)
            return nd;
        if (key.compareTo(nd.key) < 0)
            return floor(nd.left, key);
        Node right = floor(nd.right, key);

        return right == null ? nd : right;
    }

    @Override
    public Key ceil(@NotNull Key key) {
        Node nd = ceil(root, key);
        return nd == null ? null : nd.key;
    }

    private Node ceil(Node nd, Key key) {
        if (nd == null) return null;
        if (key == nd.key) {
            return nd;
        }
        if (key.compareTo(nd.key) > 0) {
            return ceil(node.right, key);
        }
        Node left = ceil(nd.left, key);
        return left == null ? nd : left;
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node nd) {
        if (nd == null) return 0;
        return 1 + size(nd.left) + size(nd.right);
    }

    @Override
    public int height() {
        return height(root);
    }
}
