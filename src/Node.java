/**
 *  This Node class will be used for all list iterations I created within the src folder
 *  This class allows storing/sharing data and can point to another node object
 * @param <T>
 */
public class Node{
    Object data;
    Node next;
    Node prev;

    /**
     * constructor that makes a node and defines its data based on the given data
     *
     * @param element the data to be stored
     */
    Node(Object element){
        data = element;
    }
}