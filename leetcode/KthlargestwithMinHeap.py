import heapq

class KthLargest:

    def __init__(self, k, nums):
        """
        :type k: int
        :type nums: List[int]
        """
        self.minheap = []
        self.size = k
        for i in nums:
            self.add(i)

    def add(self, val):
        """
        :type val: int
        :rtype: int
        """
        if len(self.minheap) < self.size:
            heapq.heappush(self.minheap,val)
        else:
            if self.minheap[0]<val:
                heapq.heappop(self.minheap)
                heapq.heappush(self.minheap,val)
        return self.minheap[0]

kl = KthLargest(3,[4,5,8,2])
print(kl.add(3))
print(kl.add(5))
print(kl.add(10))
print(kl.add(9))
print(kl.add(4))
print(kl.minheap)