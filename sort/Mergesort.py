def merge(left,right):
    result = []
    while len(left)>0 and len(right)>0:
        if left[0]<right[0]:
            result.append(left.pop(0))
        else:
            result.append(right.pop(0))
    result = result + left + right
    return result

def mergesort(list):
    if len(list) <= 1:
        return list
    mid = len(list)//2
    left = mergesort(list[:mid])
    right = mergesort(list[mid:])

    list = merge(left,right)

    return list

a = [3,4,2,0,8,9,1,7,6,5]
b = [5,2,4,6,5,7,3,1,2,0]
print (mergesort(a))
print (mergesort(b))