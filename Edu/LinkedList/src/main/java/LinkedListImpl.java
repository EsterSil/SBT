import java.util.Collection;
import java.util.Iterator;


public class LinkedListImpl<E> implements Iterable<E> {

    private Node<E> head = null;
    private Node<E> tail = null;

    private int size = 0;

    public LinkedListImpl() {
    }

    public void add(E e) {
        Node<E> node = new Node<>(e);
        if (head == null) {
            head = node;
        } else {
            tail.setNext(node);
        }
        tail = node;
        size++;

    }

    public void add(int index, E element) {
        Node<E> current = head;
        if (index > size) {
            throw new IllegalArgumentException();
        }
        if (index == size) {
            add(element);
        }

        for (int i = 0; i < index - 1; i++) {
            current = current.getNext();
        }
        size++;
        Node<E> next = current;
        Node<E> newNode = new Node<>(element);
        if (index == 0) {
            newNode.setNext(next);
            head = newNode;
        } else {
            next = current.getNext();
            current.setNext(newNode);
            newNode.setNext(next);
        }

    }

    public E get(int index) {
        Node<E> current = head;
        if (index >= size) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < index; i++) {
            current = current.getNext();

        }
        return current.data;
    }

    public E remove(int index) {
        Node<E> current = head;
        if (index >= size) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < index - 1; i++) {
            current = current.getNext();
        }
        size--;
        Node<E> removing = current.getNext();
        if (index == 0) {
            head = removing;
            return current.data;
        }
        Node<E> next = removing.getNext();
        removing.setNext(null);
        current.setNext(next);
        return removing.data;
    }

    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> current = head;

            public boolean hasNext() {
                return current != null;
            }

            public E next() {
                Node<E> next = current.getNext();
                E data = current.data;
                current = next;
                return data;
            }
        };
    }

    public boolean addAll(Collection<? extends E> collection) {
        for (E collectionElement : collection) {
            this.add(collectionElement);
        }
        return true;
    }

    public boolean copy(Collection<? super E> c) {
        for (E e : this) {
            c.add(e);
        }
        return false;
    }

    public int getSize() {
        return size;
    }


    private static class Node<E> {
        E data;
        Node<E> next = null;

        Node(E data) {
            this.data = data;
        }


        Node<E> getNext() {
            return next;
        }

        void setNext(Node<E> next) {
            this.next = next;
        }
    }
}



