class KthLargest:

    def __init__(self, k, nums):
        """
        :type k: int
        :type nums: List[int]
        """
        self.kthLargest = k
        self.array = nums
        self.quicksort(self.array,0,len(self.array)-1)

    def quicksort(self,array,low,high):
        if low<high:
            p = self.findPiv(array, low, high)
            self.quicksort(array, low, p-1)
            self.quicksort(array, p+1, high)

    def findPiv(self, array, low, high):
        i = low
        j = high

        while i < j:
            while array[low] >= array[i] and i < high:
                i = i+1
            while array[low] < array[j] and low <= j:
                j = j-1
            tempnode1 = array[j]
            array[j] = array[i]
            array[i] = tempnode1
        tempnode2 = array[j]
        array[j] = array[i]
        array[i] = tempnode2

        tempnode3 = array[j]
        array[j] = array[low]
        array[low] = tempnode3

        return j


    def add(self, val):
        """
        :type val: int
        :rtype: int
        """
        self.array.append(val)
        i = len(self.array) - 1
        temp = self.array[i]
        while i>0:
            if temp >= self.array[i-1]:
                break
            else:
                self.array[i] = self.array[i-1]
                self.array[i-1] = temp
                i-=1
        return self.array[len(self.array)-self.kthLargest]

kl = KthLargest(3,[4,5,8,2])



print(kl.add(3))
print(kl.add(5))
print(kl.add(10))
print(kl.add(9))
print(kl.add(4))
print(kl.array)
