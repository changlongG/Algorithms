class Node:
    def __init__(self,item):
        self.item = item
        self.child1 = None
        self.child2 = None


class Tree:
    def __init__(self):
        self.root = None

    def bstadd(self,item):
        node = Node(item)
        if self.root == None:
            self.root = node
        else:
            q = [self.root]
            while True:
                pop_node = q.pop(0)
                if node.item < pop_node.item:
                    if pop_node.child1 == None:
                        pop_node.child1 = node
                        return
                    else:
                        q.append(pop_node.child1)
                elif node.item > pop_node.item:
                    if pop_node.child2 == None:
                        pop_node.child2 = node
                        return
                    else:
                        q.append(pop_node.child2)
                else:
                    break
       # elif root.item > value:
       #     root.child1 = self.bstadd(root.child1,value)
        #elif root.item < value:
        #    root.child2 = self.bstadd(root.child2,value)
        #return root

    def add(self, item):
        node = Node(item)
        if self.root is None:
            self.root = node
        else:
            q = [self.root]

            while True:
                pop_node = q.pop(0)
                if pop_node.child1 is None:
                    pop_node.child1 = node
                    return
                elif pop_node.child2 is None:
                    pop_node.child2 = node
                    return
                else:
                    q.append(pop_node.child1)
                    q.append(pop_node.child2)

    def traverse(self):  # 层次遍历
        if self.root is None:
            return None
        q = [self.root]
        res = [self.root.item]
        while q != []:
            pop_node = q.pop(0)
            if pop_node.child1 is not None:
                q.append(pop_node.child1)
                res.append(pop_node.child1.item)

            if pop_node.child2 is not None:
                q.append(pop_node.child2)
                res.append(pop_node.child2.item)
        return res

    def preorder(self,root):  # 先序遍历
        if root is None:
            return []
        result = [root.item]
        left_item = self.preorder(root.child1)
        right_item = self.preorder(root.child2)
        return result + left_item + right_item

    def inorder(self,root):  # 中序序遍历
        if root is None:
            return []
        result = [root.item]
        left_item = self.inorder(root.child1)
        right_item = self.inorder(root.child2)
        return left_item + result + right_item

    def postorder(self,root):  # 后序遍历
        if root is None:
            return []
        result = [root.item]
        left_item = self.postorder(root.child1)
        right_item = self.postorder(root.child2)
        return left_item + right_item + result
    def ss(self):
        return self.item.item

    def findMin(self,root):
        if root.child1 is None:
            return root.item
        else:
            return self.findMin(root.child1)

    def findMax(self,root):
        if root.child2 is None:
            return root.item
        else:
            return self.findMax(root.child2)

    def query(self,root,item):
        if root is None:
            return False
        elif root.item == item:
            return True
        elif root.item > item:
            return self.query(root.child1,item)
        else:
            return self.query(root.child2,item)

    def deleteNode(self,root,item):
        if root is None:
            return None
        elif root.item > item:
            root.child1 = self.deleteNode(root.child1,item)
        elif root.item < item:
            root.child2 = self.deleteNode(root.child2,item)
        else:
            if root.child1 is None and root.child2 is None:
                root = None
            elif root.child1 is None:
                root = root.child2
            elif root.child2 is None:
                root = root.child1
            else:
                tempitem = self.findMin(root.child2)
                root.item = tempitem
                root.child2 = self.deleteNode(root.child2,tempitem)
        return root
t = Tree()

#for i in range(1,10):
#    t.bstadd(i)
t.bstadd(20)
t.bstadd(9)
t.bstadd(28)
t.bstadd(13)
t.bstadd(22)
t.bstadd(11)
t.bstadd(18)
t.bstadd(21)
t.bstadd(24)
t.bstadd(10)
t.bstadd(12)
t.bstadd(15)
t.bstadd(19)
t.bstadd(17)
print('层序遍历:',t.traverse())
print('先序遍历:',t.preorder(t.root))
print('中序遍历:',t.inorder(t.root))
print('后序遍历:',t.postorder(t.root))
print('Min:',t.findMin(t.root))
print('Max:',t.findMax(t.root))

a = t.deleteNode(t.root,20)
print (a.item)
print('层序遍历:',t.traverse())