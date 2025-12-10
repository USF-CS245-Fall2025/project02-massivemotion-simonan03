public interface List<T> { //This defines the contract that all list implementations must follow.

    public void add (int index, T element);
    public boolean add (T element);
    public T get (int index);
    public T remove (int index);
    public int size ();
}
