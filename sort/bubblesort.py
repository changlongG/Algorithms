def bubblesort(list):
    for i in range(len(list)-1):
        a = 0
        for j in range(1,len(list)):
            if list[a]>list[j]:
                temp = list[a]
                list[a] = list[j]
                list[j] = temp
            a = j
    return list

#a = [3,4,2,0,8,9,1,7,6,5]
#b = [5,2,4,6,5,7,3,1,2,0]
#print (bubblesort(a))
#print (bubblesort(b))