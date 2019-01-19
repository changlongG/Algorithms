class MyHashMap:
    def __init__(self):
        self.size = 10
        self.buckets = [[[],[]] for i in range(self.size)]

    def hash(self,key):
        return key%self.size

    def getIndex(self,key):
        hash = self.hash(key)
        bucket = self.buckets[hash]
        for index,element in enumerate(bucket[0]):
            if element == key:
                return bucket,index
        return bucket,-1

    def put(self,key,value):
        bucket,index = self.getIndex(key)
        if index == -1:
            bucket[0].append(key)
            bucket[1].append(value)
        else:
            bucket[1][index] = value

    def get(self,key):
        bucket, index = self.getIndex(key)
        if index == -1:
            return -1
        else:
            return bucket[1][index]

    def remove(self,key):
        bucket, index = self.getIndex(key)
        if index == -1:
            return
        else:
            bucket[0].pop(index)
            bucket[1].pop(index)

hsm = MyHashMap()
hsm.put(1,1)
hsm.put(2,2)
print(hsm.buckets)
hsm.put(1,4)
print(hsm.buckets)
hsm.put(11,1)
print(hsm.buckets)
#print(hsm.get(1))
#print(hsm.get(3))
#hsm.put(2,1)
#print(hsm.get(2))
#hsm.remove(1)
print(hsm.get(1))
print(hsm.get(2))