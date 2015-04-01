package riotapiwrapper.util;

import riotapiwrapper.request.Request;


/**
 * A simple queue to hold onto requests waiting to be sent.
 * 
 * @author Christopher McFall
 * @see Request
 */
public class RequestQueue {
    
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
    public void add(Request request) {
        if (first == null) {
            first = new Node(request);
            last = first;
            ++size;
            return;
        }
        if (size < maxSize) {
            linkToLast(request);
            ++size;
        } else {
            //show a message
        }
    }
    
    /**
     * Removes and returns the {@code Request} that's at the front of the queue.
     * 
     * @return  The {@code Request} at the front of the queue.
     */
    public Request pop() {
        if (isEmpty()) {
            //message goes here for user
            return null;
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
    
}
