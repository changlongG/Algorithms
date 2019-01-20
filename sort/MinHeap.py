#Min heap data structure, can be used to sort an array

class heap:
    def __init__(self, arr):
        self.arr = arr[:]
        self.size = len(arr)
        for i in range(self.size//2-1,-1,-1):
            self.shift_down(i)

    def shift_down(self,i):
        while True:
            left_child = i*2+1
            right_child = i*2+2
            t = i
            if left_child < self.size:
                if self.arr[i] > self.arr[left_child]:
                    t = left_child
                else:
                    t = i

            if right_child < self.size:
                if self.arr[t] > self.arr[right_child]:
                    t = right_child

            if i != t:
                self.arr[i],self.arr[t] = self.arr[t],self.arr[i]
                i = t
            else:
                break

    def shift_up(self,i):

        while True:
            parent = (i - 1) // 2
            if parent >= 0:
                if self.arr[i] < self.arr[parent]:
                    self.arr[i],self.arr[parent] = self.arr[parent],arr[i]
                    i = parent
            else:
                break

    def push(self,dat):
        self.arr.append(dat)
        self.shift_up(self.size)
        self.size += 1

    def pop(self):

        t = self.arr[0]
        self.arr[0] = self.arr[-1]
        self.arr.pop(0)
        self.size -= 1
        self.shift_down(0)


        return t

a = [50,60,2,4,5,77,56,90,12,43,45,76]
aa = heap(a)
print(aa.arr)
print(aa.pop())
print(aa.pop())
print(aa.pop())
print(aa.pop())
print(aa.pop())
print(aa.pop())
print(aa.pop())
print(aa.pop())
print(aa.pop())
print(aa.pop())
print(aa.pop())
print(aa.pop())
