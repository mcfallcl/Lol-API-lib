package riotapiwrapper.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

import riotapiwrapper.request.Request;


/**
 * A simple queue to hold onto requests waiting to be sent.
 * 
 * @author Christopher McFall
 * @see Request
 */
public class RequestQueue implements Queue<Request> {
    
    private int size = 0;
    private int maxSize;
    private Node first;         //first in line
    private Node last;          //last in line
    
    /**
     * Creates a {@code RequestQueue} with specified max size.
     * 
     * @param maxSize   The specified max size of the {@code RequestQueue}.
     */
    public RequestQueue(int maxSize) {
        this.maxSize = maxSize;
    }
    
    /**
     * Creates a {@code RequestQueue} with a max size of 100000
     */
    public RequestQueue() {
        this(100000);
    }
    
    /**
     * Adds a {@code Request} to the end of the queue.
     * 
     * @param request   {@code Request} to be added.
     */
    public boolean add(Request request) {
        if (first == null) {
            first = new Node(request);
            last = first;
            ++size;
            return true;
        }
        if (size < maxSize) {
            linkToLast(request);
            ++size;
            return true;
        } else {
            throw new IllegalStateException("The queue's max size has been "
                    + "reached");
        }
    }
    
    @Override
    public Request remove() {
        if (isEmpty()) {
            throw new NoSuchElementException("The queue is empty");
        }
        Request r = first.request;
        shiftFirst();
        --size;
        return r;
    }
    
    /**
     * Returns the current size of the {@code RequestQueue}.
     * 
     * @return  The current size of the {@code RequestQueue}.
     */
    public int size() {
        return size;
    }
    
    /**
     * Returns a flag indicating if the {@code RequestQueue} is empty.
     * 
     * @return  A flag indicating if the {@code RequestQueue} is empty.
     */
    public boolean isEmpty() {
        return (size == 0);
    }
    
    /**
     * Returns a flag indicating if the {@code RequestQueue} is full.
     * 
     * @return  A flag indiciating if the {@code RequestQueue} is full.
     */
    public boolean isFull() {
        return (size >= maxSize);
    }
    
    /*
     * Shifts the first in the queue to the next.
     */
    private void shiftFirst() {
        switch (size) {
            case 0:
                throw new IllegalStateException("There's nothing in the queue");
            case 1:
                first = null;
                break;
            default:
                first = first.next;
                break;
        }
    }
    
    /*
     * Puts a request at the end of the queue.
     */
    private void linkToLast(Request r) {
        last.next = new Node(r);
        last = last.next;
    }
    
    /*
     * Wrapper to link requests to eachother
     */
    private class Node {
        
        Request request;
        Node next;
        
        Node(Request r) {
            this.request = r;
        }
        
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<Request> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends Request> c) {
        try {
            for (Request r : c) {
                this.add(r);
            }
            return true;
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        while (!isEmpty()) {
            shiftFirst();
        }
    }

    @Override
    public boolean offer(Request e) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Request poll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Request element() {
        if (isEmpty()) throw new NoSuchElementException();
        return first.request;
    }

    @Override
    public Request peek() {
        throw new UnsupportedOperationException();
    }
    
}