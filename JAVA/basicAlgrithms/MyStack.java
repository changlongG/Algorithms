package algorithm;

public class MyStack<T> {
	private T[] myStack;
	private int size;
	private int top;
	private final int stacksize = 5;

	public MyStack() {
		myStack = (T[]) new Object[stacksize];
		size = 0;
		top = 0;
	}

	public int getSize() {
		return this.size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public boolean isFull() {
		return size == stacksize;
	}

	public void push(T obj) {
		if (!isFull()) {
			myStack[top++] = obj;
			size++;
		}
	}

	public T peek() {
		if (!isEmpty()) {
			return myStack[top - 1];
		} else {
			return null;
		}
	}

	public T pop() {
		if (!isEmpty()) {
			size--;
			return myStack[--top];

		} else {
			return null;
		}
	}
}
