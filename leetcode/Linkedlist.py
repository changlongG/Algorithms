class Item:
    def __init__(self, val):
        self.val = val
        self.next = None


class MyLinkedList:

    def __init__(self):
        """
        Initialize your data structure here.
        """
        self.head = None
        self.length = 0

    def get(self, index):
        """
        Get the value of the index-th node in the linked list. If the index is invalid, return -1.
        :type index: int
        :rtype: int
        """
        if index < 0 or index >= self.length:
            return -1
        else:
            temp = self.head
            for i in range(index):
                temp = temp.next
            return temp.val

    def addAtHead(self, val):
        """
        Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
        :type val: int
        :rtype: void
        """
        temp = Item(val)
        if self.head is None:
            self.head = temp
        else:
            temp.next = self.head
            self.head = temp
        self.length += 1

    def addAtTail(self, val):
        """
        Append a node of value val to the last element of the linked list.
        :type val: int
        :rtype: void
        """
        temp = Item(val)
        temp1 = self.head
        for i in range(self.length):
            if temp1.next is None:
                break
            else:
                temp1 = temp1.next
        temp1.next = temp
        self.length += 1

    def addAtIndex(self, index, val):
        """
        Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
        :type index: int
        :type val: int
        :rtype: void
        """
        temp = Item(val)
        if index < 0 or index > self.length:
            return -1
        elif index == 0:
            self.addAtHead(val)
        elif index == self.length:
            self.addAtTail(val)
        else:
            temp1 = self.head
            for i in range(index - 1):
                temp1 = temp1.next
            temp2 = temp1.next
            temp1.next = temp
            temp.next = temp2
            self.length += 1

    def deleteAtIndex(self, index):
        """
        Delete the index-th node in the linked list, if the index is valid.
        :type index: int
        :rtype: void
        """
        if index < 0 or index > self.length:
            return -1
        else:
            temp = self.head
            for i in range(index - 1):
                temp = temp.next
            temp.next = temp.next.next
            self.length -= 1



linkedList = MyLinkedList();
#linkedList.addAtHead(1);
#print(linkedList.length)


#print(linkedList.get(1))
linkedList.addAtHead(1);
linkedList.addAtTail(3)
linkedList.addAtIndex(1,2);
linkedList.deleteAtIndex(1)
print(linkedList.get(0))
print(linkedList.get(1))
print(linkedList.get(2))
