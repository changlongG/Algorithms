from sort.quicksort import partition

def kthelement(list,low,high,k):
    if k<1 or k>len(list):
        return None
    a = partition(list,low,high)
    if a-low == k-1:
        return list[a]
    elif k-1<a-low:
        return kthelement(list,low,a-1,k)
    else:
        return kthelement(list,a+1,high,k-1-(a-low))