package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListImpl implements List {
    Node begin;
    Node end;
    int size;

    public ListImpl() {
        begin = null;
        end = null;
        this.size = 0;
    }

    private static class Node {
        Node nextElement;
        Object data;
    }

    @Override
    public void clear() {
        begin = null;
    }

    @Override
    public int size() {
        int _size = 0;
        if (begin != null) {
            _size++;
            while (begin.nextElement != null) {
                _size++;
                begin = begin.nextElement;
            }
        } else {
            return 0;
        }
        return _size;
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {
        @Override
        public boolean hasNext() {
            return begin != null;
        }

        @Override
        public Object next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node tmp = begin;
            begin = begin.nextElement;
            return tmp.data;
        }
    }

    @Override
    public void addFirst(Object element) {
        Node toAdd = new Node();
        toAdd.data = element;

        if (begin == null) {
            begin = toAdd;
            end = toAdd;
        } else {
            toAdd.nextElement = begin;
            begin = toAdd;
        }
        size++;
    }

    @Override
    public void addLast(Object element) {
        Node toAdd = new Node();
        toAdd.data = element;
        if (end == null) {
            begin = toAdd;
        } else {
            end.nextElement = toAdd;
        }
        end = toAdd;
        size++;
    }

    @Override
    public void removeFirst() {
        if (begin != null) {
            begin = begin.nextElement;
            size--;
        }
    }

    @Override
    public void removeLast() {
        Node tmp = begin;
        if (begin == null) {
            return;
        } if (begin.nextElement == null ) {
            begin = end = null;
        } else {
            while (tmp.nextElement.nextElement != null) {
                tmp = tmp.nextElement;
            }
            tmp.nextElement = null;
        }
        size--;
    }

    @Override
    public Object getFirst() {
        if (begin == null) {
            return null;
        } else {
            return begin.data;
        }
    }

    @Override
    public Object getLast() {
        if (begin == null) {
            return null;
        }
        Object toReturn = null;
        Node tmp = begin;
        while (tmp.nextElement != null) {
            if (tmp.nextElement.nextElement == null) {
                toReturn = tmp.nextElement.data;
                break;
            }
            tmp = tmp.nextElement;
        }
        return toReturn;
    }

    @Override
    public Object search(Object element) {
        Node tmp = begin;
        while (tmp != null) {
            if (element.equals(tmp.data)) {
                return tmp.data;
            }
            tmp = tmp.nextElement;
        }
        return null;
    }

    @Override
    public boolean remove(Object element) {
        if (begin == null) return false;
        if (begin == end) {
            begin = null;
            end = null;
            return true;
        }
        if (begin.data == element) {
            begin = begin.nextElement;
            return true;
        }

        Node node = begin;
        while (node.nextElement != null) { // for each element of list
            if (node.nextElement.data == element) { //if the data of the next element in list equals to element we're searching
                if (end == node.nextElement) { // if the end of the list equals to the next element after our "node"
                    end = node; // the reference of the end of the list
                    // is assigned now to the penultimate element, so it is the last element now.
                }
                node.nextElement = node.nextElement.nextElement;
                return true;
            }
            node = node.nextElement;
        }
        return false;
    }

    @Override
    public String toString() {
        Node node = begin;
        StringBuilder toReturn = new StringBuilder("[");
        while (node != null) {
            if (node.nextElement == null) {
                toReturn.append(node.data);
                break;
            } else {
                toReturn.append(node.data + ", ");
                node = node.nextElement;
            }
        }
        toReturn.append("]");
        return toReturn.toString();
    }

    public static void main(String[] args) {
        ListImpl list = new ListImpl();

        list.addLast("A");
        list.addLast("B");
        list.addLast("C");
        list.addLast(null);
        list.removeLast();
        list.removeLast();
        list.removeLast();
        list.removeLast();
        list.removeLast();
        list.removeLast();
        list.removeLast();
        list.removeLast();
        list.removeLast();
        list.removeFirst();
        list.removeFirst();
        list.remove(1);
        System.out.println(list.size());
        Iterator<Object> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}