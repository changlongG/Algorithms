package algorithm;

class Node {
	private int val;
	private Node next;

	public Node(int val) {
		this.val = val;
		this.next = null;
	}

	public void setVal(int val) {
		this.val = val;
	}

	public int getVal() {
		return this.val;
	}

	public void setNext(Node node) {
		this.next = node;
	}

	public Node getNext() {
		return this.next;
	}
}

class linkedlist {
	private Node root;
	private int length;

	/** Initialize your data structure here. */
	public linkedlist() {
		this.root = null;
		this.length = 0;
	}

	/**
	 * Get the value of the index-th node in the linked list. If the index is
	 * invalid, return -1.
	 */
	public int get(int index) {
		if (this.root == null || index > this.length - 1 || index < 0) {
			return -1;
		}
		Node tempNode = this.root;
		for (int i = 0; i < index; i++) {
			tempNode = tempNode.getNext();
		}
		return tempNode.getVal();
	}

	/**
	 * Add a node of value val before the first element of the linked list.
	 * After the insertion, the new node will be the first node of the linked
	 * list.
	 */
	public void addAtHead(int val) {
		Node tempNode = new Node(val);
		tempNode.setNext(this.root);
		this.root = tempNode;
		this.length++;
	}

	/** Append a node of value val to the last element of the linked list. */
	public void addAtTail(int val) {
		Node tempNode = new Node(val);
		if (this.root == null) {
			this.root = tempNode;
		} else {
			Node root = this.root;
			for (int i = 0; i < this.length - 1; i++) {
				root = root.getNext();
			}
			root.setNext(tempNode);
		}
		this.length++;
	}

	/**
	 * Add a node of value val before the index-th node in the linked list. If
	 * index equals to the length of linked list, the node will be appended to
	 * the end of linked list. If index is greater than the length, the node
	 * will not be inserted.
	 */
	public void addAtIndex(int index, int val) {

		if (index > this.length || index < 0) {

		} else if (this.length == 1 && index == 0) {

			addAtHead(val);
		} else if (this.length == index) {

			addAtTail(val);
		} else {

			Node tempNode = new Node(val);
			Node root = this.root;
			for (int i = 0; i < index - 1; i++) {
				root = root.getNext();
			}
			Node tempNode1 = root.getNext();
			root.setNext(tempNode);
			tempNode.setNext(tempNode1);
			this.length++;
		}
	}

	/** Delete the index-th node in the linked list, if the index is valid. */
	public void deleteAtIndex(int index) {
		if (index > this.length - 1 || index < 0) {

		} else if (this.length == 1 && index == 0) {
			this.root = null;
			this.length--;
		} else {
			Node root = this.root;
			for (int i = 0; i < index - 1; i++) {
				root = root.getNext();
			}
			root.setNext(root.getNext().getNext());
			this.length--;
		}
	}

	public static void main(String[] args) {
		linkedlist obj = new linkedlist();
		obj.addAtHead(0);
		obj.addAtTail(2);
		obj.addAtIndex(1, 4);
		obj.addAtHead(4);
		obj.addAtIndex(1, 4);
		obj.addAtTail(5);
		obj.addAtTail(2);
		obj.addAtIndex(2, 0);

		Node root = obj.root;
		System.out.print(root.getVal() + " ");
		for (int i = 0; i < obj.length - 1; i++) {
			root = root.getNext();
			System.out.print(root.getVal() + " ");
		}

	}
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList(); int param_1 = obj.get(index);
 * obj.addAtHead(val); obj.addAtTail(val); obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */