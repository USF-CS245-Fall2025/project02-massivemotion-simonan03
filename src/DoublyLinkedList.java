/**
 *  A Double Linked List structure implementation of the List<T> interface
 *  This class allows for index based insertion, removal, and access as required
 * @param <T>
 */
public  class   DoublyLinkedList<T> implements  List<T>{
    private int size;
    private Node head;

    /**
     * default constructor that creates a null list with a size of 0
     */
    public  DoublyLinkedList() {
        head = null;
        size = 0;
    }

    /**
     * constructor for DoublyLinkedList that initalizes the data associated with the node
     * @param element the data to be associated with the node
     */
    public  DoublyLinkedList(Object element) {
        head = new Node(element);
        size = 1;
    }

    /**
     * Adds an  element to the list at a specific index
     * @param   index the specified index
     * @param   element the element being inserted at that index
     * @throws  IndexOutOfBoundsException   if  index   is  invalid
     */
    @Override
    public void add(int index,  Object  element) {
        if(index < 0|| index > size) {
            throw   new IndexOutOfBoundsException();
        }
        Node new_node = new Node(element);
        if(index == 0) {
            if( size == 0) {
                head = new_node;
            } else {
                Node old_head = head;
                head = new_node;
                head.next = old_head;
                old_head.prev = head;
            }
        }else {
            Node current = head;
            for (int i = 0; i < index-1; i++) {
                current = current.next;
            }
            Node old_node=current.next;
            current.next=new_node;
            new_node.prev=current;
            new_node.next=old_node;
            if(old_node!=null){
                old_node.prev=new_node;
            }
        }
        size++;
    }

    /**
     * Adds a new element to the end of the list
     * @param element element to be added at the end of the list
     * @return true after element is added
     */
    @Override
    public boolean add(Object element) {
        Node new_node=new Node(element);
        if(size==0) {
            head=new_node;
            size++;
            return true;
        }
        Node current=head;
        while(current.next!=null){
            current=current.next;
        }
        current.next=new_node;
        new_node.prev=current;
        size++;
        return true;
    }

    /**
     * Will return an element associated with a specific index
     * @param index the index being searched for
     * @return element at that index if exists
     * @throws IndexOutOfBoundsException if index is invalid
     */
    @Override
    public T get(int index) {
        if(index<0||index>=size) {
            throw new IndexOutOfBoundsException();
        }
        Node current=head;
        for(int i=0;i<index;i++) {
            current=current.next;
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
    public T remove(int index) {
        if(index<0||index>=size) {
            throw new IndexOutOfBoundsException();
        }
        Node removed_node;
        if(index==0) {
            removed_node=head;
            head=head.next;
            if(head!=null) {
                head.prev=null;
            }
        }else {
            Node current = head;
            for (int i = 0; i < index-1; i++) {
                current = current.next;
            }
            removed_node=current.next;
            Node after=removed_node.next;
            current.next=after;
            if(after!=null){
                after.prev=current;
            }
        }
        size--;
        return  (T)removed_node.data;
    }

    /**
     * returns the amount of values within the list
     * @return size of the list
     */
    @Override
    public int size() {
        return size;
    }
}