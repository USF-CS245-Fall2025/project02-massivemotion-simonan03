/**
 *  A single linked list structure implementation of the List<T> interface
 *  This class allows for index based insertion, removal, and access as required
 * @param <T>
 */
public class LinkedList<T> implements List<T> {
    private Node head;
    private int size;

    /**
     * constructor for LinkedList
     * @param
     */
    public LinkedList() {
        head=null;
        size=0;
    }

    /**
     * the constructor that creates a linked list with a head node that holds the specified data x
     * @param x the data that the node is holding
     */
    public LinkedList(Object x) {
        head=new Node(x);
        size=1;
    }

    /**
     * Adds an element to the list at a specific index
     * @param index
     * @param element
     * @throws IndexOutOfBoundsException if index is invalid
     */
    @Override
    public void add(int index, Object element) {
        if(index<0||index>size){
            throw new IndexOutOfBoundsException();
        }
        Node new_node=new Node(element);
        if(index==0){
            new_node.next=head;
            head=new_node;
        } else{
            Node current = head;
            int count = 0;
            while (count != index - 1){
                current = current.next;
                count++;
            }
            Node old_node = current.next;
            current.next = new_node;
            new_node.next = old_node;
        }
        size++;
    }

    /**
     * Adds a new element to the end of the list
     * @param element
     * @return true after element is added
     */
    @Override
    public boolean add(Object element){
        if(head==null){
            head=new Node(element);
        } else{
            Node current=head;
            while(current.next!=null){
                current=current.next;
            }
            current.next=new Node(element);
        }
        size++;
        return true;
    }

    /**
     * Will return an element associated with a specific index
     * @param index
     * @return requested element
     * @throws IndexOutOfBoundsException if index is invalid
     */
    @Override
    public T get(int index){
        if(index<0||index>=size){
            throw new IndexOutOfBoundsException();
        }
        if(index==0){
            return (T)head.data;
        }
        Node current=head;
        int count=0;
        while(count!=index){
            current=current.next;
            count++;
        }
        return (T)current.data;
    }

    /**
     * removes an element associated with the requested index
     * @param index
     * @return the removed element associated with
     * @throws IndexOutOfBoundsException if index is invalid
     */
    @Override
    public T remove(int index){
        if(index<0||index>=size){
            throw new IndexOutOfBoundsException();
        }
        if(index==0){
            Node old_head=head;
            head=head.next;
            size--;
            return (T)old_head.data;
        }
        Node current=head;
        int count=0;
        while(count!=index-1){
            current=current.next;
            count++;
        }
        Node deleted_node= current.next;
        current.next=deleted_node.next;
        size--;
        return (T)deleted_node.data;
    }

    /**
     * returns the amount of values within the list
     * @return size of the list
     */
    @Override
    public int size()  {
        return size;
    }
}