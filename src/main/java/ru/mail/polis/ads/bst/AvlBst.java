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

    void fixHeight(Node nd) {
        nd.height = 1 + Math.max(height(nd.left), height(nd.right));
    }

    private Node balance(Node nd) {
        if (factor(nd) == 2) {
            if (factor(nd.left) < 0) {
                nd.left = rotateLeft(nd.left);
            }
            return rotateRight(nd);
        }
        if (factor(nd) == -2) {
            if (factor(nd.right) > 0) {
                nd.right = rotateRight(nd.right);
            }
            return rotateLeft(nd);
        }
        return nd;
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

    private Node innerRemove(Node nd) {
        if (nd.left == null) {
            return nd.right;
        }
        if (nd.right == null) {
            return nd.left;
        }
        Node t = nd;
        nd = min(t.right);
        nd.right = removeMin(t.right);
        nd.left = t.left;
        return nd;
    }

    private Node removeMin(Node nd) {
        if (nd.left == null) {
            return nd.right;
        }
        nd.left = removeMin(nd.left);
        return nd;
    }
    @Override
    public Value get(@NotNull Key key) {
        Node nd = get(root, key);
        if (nd == null) {
            return null;
        }
        return nd.value;
    }

    public Node get(Node nd, Key key) {
        if (nd == null) {
            return null;
        }
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
            size++;
            return new Node(key, value, 1);
        }
        if (key.compareTo(nd.key) < 0) {
            nd.left = put(nd.left, key, value);
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
        if (nd == null) {
            return null;
        }
        root = remove(root, key);
        size--;
        return nd.value;
    }

    private Node remove(Node nd, Key key) {
        if (nd == null) {
            return null;
        }
        if (key.compareTo(nd.key) < 0) {
            nd.left = remove(nd.left, key);
        }
        if (key.compareTo(nd.key) > 0) {
            nd.right = remove(nd.right, key);
        }
        if (key.compareTo(nd.key) == 0) {
            nd = innerRemove(nd);
        }
        return nd;
    }


    @Override
    public Key min() {
        Node nd = min(root);
        return nd == null ? null : nd.key;
    }

    @Override
    public Value minValue() {
        Node nd = min(root);
        return nd == null ? null : nd.value;
    }

    private Node min(Node nd) {
        if (nd == null) {
            return null;
        }
        return nd.left == null ? nd : min(nd.left);
    }

    @Override
    public Key max() {
        Node nd = max(root);
        return nd == null ? null : nd.key;
    }

    private Node max(Node nd) {
        if (nd == null) {
            return null;
        }
        return nd.right == null ? nd : max(nd.right);
    }

    @Override
    public Value maxValue() {
        Node nd = max(root);
        return nd == null ? null : nd.value;
    }

    @Override
    public Key floor(@NotNull Key key) {
        Node nd = floor(root, key);
        return nd == null ? null : nd.key;
    }

    private Node floor(Node nd, Key key) {
        if (nd == null) {
            return null;
        }
        if (nd.key.compareTo(key) == 0) {
            return nd;
        }
        if (nd.key.compareTo(key) > 0) {
            return floor(nd.left, key);
        }

        Node node = floor(nd.right, key);
        return node == null ? nd : node;

    }

    @Override
    public Key ceil(@NotNull Key key) {
        Node nd = ceil(root, key);
        return nd == null ? null : nd.key;
    }

    private Node ceil(Node nd, Key key) {
        if (nd == null) {
            return null;
        }
        if (nd.key.compareTo(key) == 0) {
            return nd;
        }
        if (nd.key.compareTo(key) < 0) {
            return ceil(nd.right, key);
        }

        Node node = ceil(nd.left, key);
        return node == null ? nd : node;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return height(root);
    }

    private int height(Node x) {
        return x == null ? 0 : x.height;
    }
}