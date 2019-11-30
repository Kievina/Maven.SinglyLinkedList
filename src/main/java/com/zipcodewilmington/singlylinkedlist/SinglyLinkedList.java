package com.zipcodewilmington.singlylinkedlist;

/**
 * Created by leon on 1/10/18.
 */

public class SinglyLinkedList<T extends Comparable<T>> {
    public Node<T> head;
    public Node<T> tail;
    private Integer size;

    public SinglyLinkedList(T data) {
        this.head = null;
        this.head = new Node<T>(data);
        this.tail = null;
        this.size = 0;
    }

    public SinglyLinkedList() {
        this.head = null;
        this.size = 0;
    }


    class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }


    public void add(T data) {
        //Create a new node
        Node<T> newNode = new Node<>(data);

        if (head != null) {
            //If list is not empty, set head to the current
            Node<T> current = this.head;
            while (current != null) {
                if (current.next == null) {
                    current.next = newNode;
                    size++;
                    break;
                }
                current = current.next;
            }
        } else {
            //Checks if the list is empty
            this.head = newNode;
            size++;
        }
    }

    public Boolean remove(Integer index) {

        Node<T> current = this.head;
        Boolean result = false;
        int count = 0;

        if (index == 0) {
            this.head = current.next;
            size--;
            result = true;
        } else if (index > 0) {
            while (current != null) {
                if (count == index - 1) {
                    current.next = current.next.next;
                    size--;
                    result = true;
                }
                current = current.next;
                count++;
            }
        } else
            result = false;
        return result;
    }

    public boolean contains(T data) {
        Node<T> current = this.head;
        while (current != null) {
            if (current.data.equals(data))
                return true;
            current = current.next;
        }
        return false;
    }

    public Integer find(T data) {
        Node<T> current = this.head;
        int count = 0; // index of Node we are currently looking at
        while (current != null) {
            if (current.data.equals(data))
                return count;
            current = current.next;
            count++;
        }
        return -1;
    }

    public Integer size() {
        return this.size;
    }

    public T get(Integer index) {
        Node<T> current = this.head;
        Integer count = 0; // index of Node we are currently looking at
        while (current != null) {
            if (count.equals(index))
                return current.data;
            current = current.next;
            count++;
        }
        return null;
    }

    public SinglyLinkedList<T> copy() {
        SinglyLinkedList<T> listCopy = new SinglyLinkedList<>();
        Node<T> current = this.head;
        while (current != null) {
            listCopy.add(current.data);
            current = current.next;
        }
        return listCopy;
    }

    public void sort() {
        GenericCompare<T> comp = new GenericCompare<>();

        boolean wasChanged;
        do {
            Node<T> current = head;
            Node<T> previous = null;
            Node<T> next = head.next;
            wasChanged = false;

            while (next != null) {
                if (comp.compare(current.data, next.data) > 0) {
                    wasChanged = true;
                    if (previous != null) {
                        Node temp = next.next;
                        previous.next = next;
                        next.next = current;
                        current.next = temp;
                    } else {
                        Node temp = next.next;
                        head = next;
                        next.next = current;
                        current.next = temp;
                    }
                    previous = next;
                    next = current.next;
                } else {
                    previous = current;
                    current = next;
                    next = next.next;
                }
            }
        } while (wasChanged);
    }

}
