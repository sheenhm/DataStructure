package cse2010.hw3;

public class Node<T> {
    private T item;
    private Node<T> prev;
    private Node<T> next;

    /**
     * Constructs a node with the given item and links.
     * @param item the item
     * @param prev the previous node
     * @param next the next node
     */
    public Node(T item, Node<T> prev, Node<T> next) {
        this.item = item;
        this.prev = prev;
        this.next = next;
    }

    /**
     * Returns the item of this node.
     * @return the item
     */
    public T getItem() {
        return item;
    }

    /**
     * Sets the item of this node.
     * @param item the item to set
     */
    public void setItem(T item) {
        this.item = item;
    }

    /**
     * Returns the previous node of this node.
     * @return the previous node
     */
    public Node<T> getPrev() {
        return prev;
    }

    /**
     * Sets the previous node of this node.
     * @param prev the previous node to set
     */
    public void setPrev(Node<T> prev) {
        this.prev = prev;
    }

    /**
     * Returns the next node of this node.
     * @return the next node
     */
    public Node<T> getNext() {
        return next;
    }

    /**
     * Sets the next node of this node.
     * @param next the next node to set
     */
    public void setNext(Node<T> next) {
        this.next = next;
    }
}