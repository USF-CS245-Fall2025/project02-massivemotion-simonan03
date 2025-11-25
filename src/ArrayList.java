/**
 *  An arraylist structure implementation of the List<T> interface
 *  This class allows for index based insertion, removal, and access as required
 * @param <T>
 */

public class ArrayList<T> implements List<T>{

private Object[] list;
private int size;

    /**
     * constructor for ArrayList
     */
public ArrayList()
{
    list=new Object[10];
    size=0;
}

    /**
     * Will adjust available space in list to fit more values in case there's not enough space
     * @param new_size
     */
    private void size_keeper(int new_size)
{
    if(new_size>=list.length)
    {
        Object[] list2=new Object[list.length*2];
        for(int i=0;i<size;i++)
        {
            list2[i]=list[i];
        }
        list=list2;

    }

}

    /**
     * Adds an element to the list at a specific index
     * @param index
     * @param element
     * @throws IndexOutOfBoundsException if index is invalid
     */
    @Override
    public void add(int index, T element) {

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        } else {
            size_keeper(size+1);
            for (int i = size; i >index; i--) {
                list[i]=list[i-1];
            }
            list[index]=element;
            size++;

        }

    }

    /**
     * Adds a new element to the end of the list
     * @param element
     * @return true after element is added
     */
    @Override
    public boolean add(T element) {
        size_keeper(size+1);
        list[size++]=element;
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
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return (T)list[index];
    }

    /**
     * removes an element associated with the requested index
     * @param index
     * @return the removed element associated with
     * @throws IndexOutOfBoundsException if index is invalid
     */
    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
            Object removed=list[index];
            for (int i = index; i < size-1; i++) {
                    list[i] = list[i+1];
                }
            //makes sure that the last index is not duplicated
            list[size-1]=null;
            size--;
            return (T)removed;
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