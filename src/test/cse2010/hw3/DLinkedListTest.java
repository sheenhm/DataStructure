package cse2010.hw3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DLinkedListTest {

    @Test
    void create_empty_list() {
        // Arrange (Given)

        // Act (When)
        DLinkedList<Integer> list = new DLinkedList<>();

        // Assert (Then)
        assertEquals(0, list.getSize());
        assertEquals(list.getHeader().getNext(), list.getTrailer());
        assertEquals(list.getTrailer().getPrev(), list.getHeader());
    }

    @Test
    void should_add_first() {
        // Arrange (Given)
        DLinkedList<Integer> list = new DLinkedList<>();

        // Act (When)
        list.addFirst(42);

        // Assert (Then)
        assertEquals(list.getSize(), 1);
        assertEquals(list.getFirst().getItem(), 42);
    }

    @Test
    void should_add_last() {
        // Arrange (Given)
        DLinkedList<Integer> list = new DLinkedList<>();

        // Act (When)
        list.addFirst(42);

        // Assert (Then)
        assertEquals(list.getSize(), 1);
        assertEquals(list.getLast().getItem(), 42);
    }

    @Test
    void should_remove_first() {
        // Arrange (Given)
        DLinkedList<Integer> list = new DLinkedList<>();
        list.addFirst(1);
        list.addFirst(2);

        // Act (When)
        int value = list.removeFirst();

        // Assert (Then)
        assertEquals(list.getSize(), 1);
        assertEquals(value, 2);
        assertEquals(list.getLast().getItem(), 1);
        assertEquals(list.getFirst().getItem(), 1);
    }

    @Test
    void should_remove_last() {
        // Arrange (Given)
        DLinkedList<Integer> list = new DLinkedList<>();
        list.addLast(1);
        list.addLast(2);

        // Act (When)
        int value = list.removeLast();

        // Assert (Then)
        assertEquals(list.getSize(), 1);
        assertEquals(value, 2);
        assertEquals(list.getLast().getItem(), 1);
        assertEquals(list.getFirst().getItem(), 1);
    }
}
