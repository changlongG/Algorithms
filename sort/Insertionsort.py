def Insertionsort(list):
    for i in range(len(list)):
        temp = list[i]
        j = i - 1
        while j >= 0 and temp < list[j]:
            list[j+1] = list[j]
            j = j - 1
        list[j+1] = temp
    return list

a = [3,4,2,0,8,9,1,7,6,5]
b = [5,2,4,6,5,7,3,1,2,0]
print (Insertionsort(a))
print (Insertionsort(b))