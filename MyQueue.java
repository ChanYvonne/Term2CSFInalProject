import java.util.*;

public class MyQueue<T>{
    private MyLinkedList<T> end;
    
    /**
     * Adds the given item to the rear of the queue.
     */
    public MyQueue(){
	end = new MyLinkedList<T>();
    }
    
    public void enqueue(T item){
	end.add(item);
    }

    /**
     * Removes the front item from the queue and returns it.
     * @exception java.util.NoSuchElementException if the queue is empty.
     */
    public T dequeue(){
	if (isEmpty()){
	    throw new NoSuchElementException();
	}
	return end.remove(size()-1);

    }

    /**
     * Returns the front item from the queue without popping it.
     * @exception java.util.NoSuchElementException if the queue is empty.
     */
    public T peek(){
	if (isEmpty()){
	    throw new NoSuchElementException();
	}
	return end.get(size()-1);
    }

    /**
     * Returns the number of items currently in the queue.
     */
    public int size(){
	return end.size();
    }

    /**
     * Returns whether the queue is empty or not.
     */
    public boolean isEmpty(){
	return size() == 0;
    }

    public String toString(){
	return end.toString();
    }

    public static void main(String[]args){
	MyQueue<Integer> test = new MyQueue<Integer>();
	test.enqueue(7);
	test.enqueue(6);
	test.enqueue(8);
	test.enqueue(4);
	test.enqueue(1);
	test.enqueue(5);
	test.enqueue(9);
	System.out.println(test.dequeue());
	System.out.println(test.isEmpty());
	System.out.println(test.peek());
    }
}
