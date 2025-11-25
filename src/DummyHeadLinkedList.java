/**
 *  A single linked list structure implementation of the List<T> interface.
 *  This class allows for index based insertion, removal, and access as required
 *  The head node will always be a node with a null value pointing to the actual first index within the list
 * @param <T>
 */
public class DummyHeadLinkedList<T> implements List<T>{

    private int size;
    private Node head;


    private class Node
    {
        Object data;
        DummyHeadLinkedList.Node next;
        Node(Object element)
        {
            data= element;
        }
    }

    /**
     * constructor for DummyHeadLinkedList
     * @param
     */
    public DummyHeadLinkedList()
    {
        head=new Node(null);
        size=0;
    }


    /**
     * Adds an element to the list at a specific index
     * @param index
     * @param element
     * @throws IndexOutOfBoundsException if index is invalid
     */
    @Override
    public void add(int index, Object element) {

        if(index<0||index>size)
        {
            throw new IndexOutOfBoundsException();
        }

        Node new_node=new Node(element);

        Node current = head;
        for(int i=0;i<index;i++) {
            current = current.next;
        }

        new_node.next = current.next;
        current.next = new_node;
        size++;
    }


    /**
     * Adds a new element to the end of the list
     * @param element
     * @return true after element is added
     */
    @Override
    public boolean add(Object element) {



        Node current=head;
        while(current.next!=null)
        {
            current=current.next;
        }
        current.next=new Node(element);

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
    public T get(int index) {
        if(index<0||index>=size)
        {
            throw new IndexOutOfBoundsException();
        }

        if(index==0)
        {

            return (T)head.next.data;
        }

        DummyHeadLinkedList.Node current=head.next;
        for(int i=0;i<index;i++)
        {
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
        if(index<0||index>=size)
        {
            throw new IndexOutOfBoundsException();
        }



        if(index==0)
        {
            Node deleted_node=head.next;
            head.next=deleted_node.next;
            size--;
            return (T)deleted_node.data;
        }

        Node current=head;
        for(int i=0;i<index;i++)
        {
            current=current.next;

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
    public int size() {
        return size;
    }
}