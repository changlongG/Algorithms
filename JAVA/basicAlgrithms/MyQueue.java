package algorithm;

//循环队列
public class MyQueue<T> {
	private final int defaultsize = 100;
	private T[] myQueue;
	private int front;
	private int rear;
	private int size;

	public MyQueue() {
		myQueue = (T[]) new Object[defaultsize];
		front = 0;
		rear = 0;
		size = 0;
	}

	public int getSize() {
		return this.size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public boolean isFull() {
		return front == (rear + 1) % defaultsize;
	}

	public void add(T obj) {
		if (!isFull()) {
			myQueue[rear] = obj;
			rear = (rear + 1) % defaultsize;
			size++;
		}
	}

	public T peek() {
		if (!isEmpty()) {

			return myQueue[front];
		} else {
			return null;
		}
	}

	public T poll() {
		if (!isEmpty()) {
			size--;
			T ob = myQueue[front];
			front = (front + 1) % defaultsize;
			return ob;
		} else {
			return null;
		}

	}

	public void print() {
		for (T temp : myQueue) {
			System.out.print(temp + " ");
		}
	}
}
