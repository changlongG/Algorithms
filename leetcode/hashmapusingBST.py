class Node:
    def __init__(self, key, value):
        self.key = key
        self.value = value
        self.left = None
        self.right = None


class MyHashMap:

    def __init__(self):
        """
        Initialize your data structure here.
        """
        self.head = None

    def put(self, key, value):
        """
        value will always be non-negative.
        :type key: int
        :type value: int
        :rtype: void
        """
        tempnode = Node(key, value)
        if self.head is None:
            self.head = tempnode
        else:
            queue = []
            queue.append(self.head)
            while len(queue):
                popnode = queue.pop(0)
                if popnode.key == key:
                    popnode.value = value
                    break
                elif popnode.key > key:

                    if popnode.left is None:
                        popnode.left = tempnode
                        return
                    else:
                        queue.append(popnode.left)
                else:

                    if popnode.right is None:

                        popnode.right = tempnode
                        return
                    else:
                        queue.append(popnode.right)

    def get(self, key):
        """
        Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key
        :type key: int
        :rtype: int
        """
        if self.head is None:
            return -1
        else:
            queue = []
            queue.append(self.head)
            while len(queue):
                popnode = queue.pop(0)
                if popnode.key == key:
                    return popnode.value
                elif popnode.key > key:
                    if popnode.left is None:
                        return -1
                    else:
                        queue.append(popnode.left)
                else:
                    if popnode.right is None:
                        return -1
                    else:
                        queue.append(popnode.right)

    def findMin(self, node):
        if node.left is None:
            return node
        else:
            return self.findMin(node.left)

    def remove(self, key):
        """
        Removes the mapping of the specified value key if this map contains a mapping for the key
        :type key: int
        :rtype: void
        """
        if self.head is None:
            return
        elif self.head.left is None and self.head.right is None:
            if self.head.key == key:
                self.head = None
            return
        queue = []
        queue.append(self.head)
        prevnode = self.head
        while len(queue):
            popnode = queue.pop(0)
            if popnode.key == key:
                if popnode.left is None and popnode.right is None:
                    #print("2222222222")
                    if prevnode.left and prevnode.left.key == key:
                        prevnode.left = None
                    elif prevnode.right and prevnode.right.key == key:
                        prevnode.right = None
                elif popnode.right is None and popnode.left is not None:
                    #print("33333333333")
                    if prevnode.left and prevnode.left.key == key:
                        prevnode.left = popnode.left
                    elif prevnode.right and prevnode.right.key == key:
                        prevnode.right = popnode.left
                else:
                    #print("111111111")
                    tempnode = self.findMin(popnode.right)
                    tempkey = tempnode.key
                    tempvalue = tempnode.value
                    self.remove(tempkey)
                    popnode.key = tempkey
                    popnode.value = tempvalue
            elif popnode.key < key:
                if popnode.left:
                    queue.append(popnode.left)
            else:
                if popnode.right:
                    queue.append(popnode.right)
            prevnode = popnode

hsm = MyHashMap()
hsm.put(1,1)
hsm.put(2,2)
#print(hsm.get(1))
#print(hsm.get(3))
#hsm.put(2,1)
#print(hsm.get(2))
#hsm.remove(1)
print(hsm.get(1))
print(hsm.get(2))