# Binary search for almost sorted array like [1,2,4,5,3,6], the number of position shifted must be specified

def bs_v2(list,target,k):
    a = len(list)//2
    if a - k < 0 :
        return -10000
    else:
        for i in range(a-k,a+k+1):
            if target == list[i]:
                return i
        if target<list[a]:
            return bs_v2(list[:a], target,k)
        elif target>list[a]:
            return bs_v2(list[a:], target,k) + len(list[:a])

def bs_v2_nonrecursion(list,target,k):
    low = 0
    high = len(list)
    a = (high - low) // 2
    while low < high:
        for i in range(a-k,a+k+1):
                #print(list[i])
            if target == list[i]:
                return i
        if target < list[a]:
            high = a - 1
            a = (high - low) // 2
        elif target > list[a]:
            low = a + 1
            a = (high - low) // 2 + low
    return None



a = [0,1,2,3,4,5,6,7,8,9]
b = [0,3,4,1,2,5,6,8,9,7]
c = [0,3,4,1,2,6,7,5,8,9]
#print(bs_v2(a,9,0)
print(bs_v2_nonrecursion(c,5,2))