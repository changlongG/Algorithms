def quicksort(list,low,high):
    if low < high:
        s = partition(list,low,high)
        quicksort(list,low,s-1)
        quicksort(list,s+1,high)

def partition(list,low,high):
    i = low
    j = high
    while i<j:
        while i<high and list[low]>=list[i]:
            i = i+1
        while low<=j and list[low]<list[j]:
            j = j-1
        temp = list[i]
        list[i] = list[j]
        list[j] = temp
    temp = list[i]
    list[i] = list[j]
    list[j] = temp
    temp = list[j]
    list[j] = list[low]
    list[low] = temp
    return j

#a = [3,4,2,0,8,9,1,7,6,5]
#b = [5,2,4,6,5,7,3,1,2,0]
#quicksort(a,0,len(a)-1)
#print (a)
#quicksort(b,0,len(b)-1)
#print (b)