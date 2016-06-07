import java.util.*;

public class MyLinkedList<T> implements Iterable<T>{

    private class LNode{
	private T value;
	private LNode next;
	private LNode previous;
	
	public LNode(T v){
	    value = v;
	}
	
	public T getValue(){
	    return value;
	}
	
	public LNode getNext(){
	    return next;
	}

	public LNode getPrevious(){
	    return previous;
	}
	
	public T setValue(T v){
	    T old = value;
	    value = v;
	    return old;
	}
	
	public void setNext(LNode n){
	    next = n;
	}

	public void setPrevious(LNode n){
	    previous = n;
	}
	
	public String toString(){
	    return value.toString();
	}
    }

    LNode head;
    LNode tail;
    int size;
    
    public Iterator<T> iterator(){
	//This uses an anonymous class! You don't need to know this.
	return new Iterator<T>()
	{
	    private LNode current = head;

	    public boolean hasNext(){
		return current != null;
	    }
	    public T next(){
		if(!hasNext()){
		    throw new NoSuchElementException();
		}
		T value = current.getValue();
		current = current.getNext();
		return value;
	    }
	    public void remove(){
		throw new UnsupportedOperationException();
	    }
	};
    } 

    public Iterator<T> iteratorBack(){
	// changes current so that it iterates from the back
	return new Iterator<T>()
	{
	    private LNode current = tail;

	    public boolean hasNext(){
		return current != null;
	    }
	    public T next(){
		if(!hasNext()){
		    throw new NoSuchElementException();
		}
		T value = current.getValue();
		current = current.getPrevious();
		return value;
	    }
	    public void remove(){
		throw new UnsupportedOperationException();
	    }
	};
    }

    public String toString(){
	String ans = "[";
	LNode p = head;
	while(p!=null){
	    ans += p.getValue();
	    if(p.getNext()!=null){
		ans += ", ";
	    }
	    p = p.getNext();
	}
	return ans+"]";
    }
    
    public String toString(boolean b){
	return toString()+" head: "+head +", tail: "+tail;
    }
    
    private LNode getNth(int index){
	//should start looping from the closer side
	
	//check bounds before calling this method!
	LNode temp = head;
	while(index > 0){
	    temp = temp.getNext();
	    index --;
	}
	return temp;
    }
    
    public boolean add(T value){
	if(head == null){
	    head = new LNode(value);
	    tail = head;
	}else{
	    /*
	    tail.setNext(new LNode(value));
	    LNode temp = tail.getNext();
	    temp.setPrevious(tail);
	    tail = temp;
	    */
	    
	    LNode temp = new LNode(value);
	    temp.setPrevious(tail);
	    tail.setNext(temp);
	    tail = tail.getNext();
	    //System.out.println(toString(true));
	}
	size++;
	return true;
    }
    
    public T remove(int index){
	if(index < 0 || index >= size()){
	    throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size());
	}
        
	LNode temp;
	if (size == 1){
	    temp = head;
	    head = null;
	    tail = null;
	    size--;
	    return temp.getValue();
	}else if(index == 0){
	    temp = head;
	    head = head.getNext();
	    head.setPrevious(null);
	    size--;
	    //System.out.println(toString(true));
	    return temp.getValue();
	}else if (index == size()-1){
	    temp = tail;
	    tail = tail.getPrevious();
	    
	    tail.setNext(null);
	    size--;
	    //System.out.println(toString(true));
	    return temp.getValue();
	 
	}else{
	    LNode p = getNth(index-1);
	    temp = p.getNext();
	    if(tail == temp){
		tail = p;
	    }else{
		p.setNext(temp.getNext());
		temp.getNext().setPrevious(p);
		//System.out.println(toString(true));
	    }
	    size --;
	    return temp.getValue();
	}
    }
    
    public boolean add(int index, T value){
	if(index < 0 || index > size()){
	    throw new IndexOutOfBoundsException("Index "+index+", Size: "+size());
	}
	
	LNode temp = new LNode(value);
		    
	if (index == size() || size() == 0){
	    add(value);
	}else if(index == 0){
	    if (size == 1){
		tail = head;
	    }
	    temp.setNext(head);
	    head.setPrevious(temp);
	    //temp.setPrevious(null);
	    head = temp;
	    //System.out.println(toString(true));
	    size++;
	}else{ 
	    LNode p = getNth(index-1);
	    temp.setPrevious(p);
	    temp.setNext(p.getNext());
	    p.getNext().setPrevious(temp);
	    p.setNext(temp);
	    //System.out.println(p.getNext().toString());
	    //System.out.println(toString(true));
	    if (tail.getNext() != null){
		tail = tail.getNext();
	    }
	    size++;
	}
	return true;
    }
    //draw pictures!!! it helps

    public int size(){
	return size;
    }
    
    public T get(int index){
	if(index < 0 || index >= size()){
	    throw new IndexOutOfBoundsException("Index "+index+", Size: "+size());
	}
	return getNth(index).getValue();
    }

    public T set(int index, T newValue){
	if(index < 0 || index >= size()){
	    throw new IndexOutOfBoundsException("Index "+index+", Size: "+size());
	}
	return getNth(index).setValue(newValue);
    }

    public int indexOf(T target){
	LNode temp = head;
	int index = 0;
	while(temp != null){
	    if(temp.getValue().equals(target)){
		return index;
	    }
	    index++;
	    temp = temp.getNext();
	}
	return -1;
    }
}
