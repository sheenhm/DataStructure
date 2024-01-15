package cse2010.hw3;

public class DLinkedList<T> {

    /**
     * The header and trailer node pointers.
     */
    private Node<T> header;
    private Node<T> trailer;
    /**
     * The size as the number of nodes in the list.
     */
    private int size = 0;

    /**
     * Constructs an empty list.
     */
    public DLinkedList() {
        header = new Node<>(null, null, null);
        trailer = new Node<>(null, header, null);
        header.setNext(trailer);
        size = 0;
    }

    /**
     * Returns the header node.
     * @return the header node
     */
    public Node<T> getHeader() {
        return header;
    }

    /**
     * Returns the trailer node.
     * @return the trailer node
     */
    public Node<T> getTrailer() {
        return trailer;
    }

    /**
     * Sets the header info.
     * @param info the info to set
     */
    public void setHeaderInfo(T info) {
        header.setItem(info);
    }

    /**
     * Sets the trailer info.
     * @param info the info to set
     */
    public void setTrailerInfo(T info) {
        trailer.setItem(info);
    }

    /**
     * Checks if the list is empty.
     * @return true if the list is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the size of the list.
     * @return the size of the list
     */
    public int getSize() { return size; }

    /**
     * Returns the first node of the list.
     * @return the first node of the list
     */
    public Node<T> getFirst() {
        return header.getNext();
    }

    /**
     * Returns the last node of the list.
     * @return the last node of the list
     */
    public Node<T> getLast() {
        return trailer.getPrev();
    }

    /**
     * Adds a value at the front of the list.
     * @param value the value to add
     */
    public void addFirst(T value) {
        Node<T> node = new Node<>(value, null, null);
        addAfter(header, node);
    }

    /**
     * Adds a value at the end of the list.
     * @param value the value to add
     */
    public void addLast(T value) {
        Node<T> node = new Node<>(value, null, null);
        addBefore(trailer, node);
    }

    /**
     * Removes the first node of the list.
     * @return the info of the removed node
     */
    public T removeFirst() {
        if (isEmpty()) return null;
        T removed = header.getNext().getItem();
        header.setNext(header.getNext().getNext());
        header.getNext().setPrev(header);
        size--;
        return removed;
    }

    /**
     * Removes the last node of the list.
     * @return the info of the removed node
     */
    public T removeLast() {
        if (isEmpty()) return null;
        T removed = trailer.getPrev().getItem();
        trailer.setPrev(trailer.getPrev().getPrev());
        trailer.getPrev().setNext(trailer);
        size--;
        return removed;
    }

    /**
     * Adds a node after the given node.
     * @param p the node to add after
     * @param n the node to add
     */
    public void addAfter(Node<T> p, Node<T> n) {
        n.setPrev(p);
        n.setNext(p.getNext());
        p.getNext().setPrev(n);
        p.setNext(n);
        size++;
    }

    /**
     * Adds a node before the given node.
     * @param p the node to add before
     * @param n the node to add
     */
    public void addBefore(Node<T> p, Node<T> n) {
        n.setPrev(p.getPrev());
        n.setNext(p);
        p.getPrev().setNext(n);
        p.setPrev(n);
        size++;
    }

    /**
     * Removes the given node.
     * @param n the node to remove
     * @return the info of the removed node
     */
    public T remove(Node<T> n) {
        n.getPrev().setNext(n.getNext());
        n.getNext().setPrev(n.getPrev());
        size--;
        return n.getItem();
    }

    /**
     * Returns a string representation of the list.
     * @return a string representation of the list
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(
            "List: size = " + size + " [");
        Node<T> current = header.getNext();

        while (current != trailer) {
            builder.append(current.getItem().toString());
            current = current.getNext();
        }
        builder.append("]");

        return builder.toString();
    }
}
