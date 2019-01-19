def bs(list,target):
    if target<list[0] or target>list[len(list)-1]:
        return None
    a = len(list)//2
    if target == list[a]:
        return a
    elif target<list[a]:
        return bs(list[:a], target)
    elif target>list[a]:
        return bs(list[a:], target) + len(list[:a])




