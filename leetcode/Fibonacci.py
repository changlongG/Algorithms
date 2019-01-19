def fib(N):
    """
    :type N: int
    :rtype: int
    """
    if N == 0:
        return 0
    elif N == 1:
        return 1
    else:
        list = [0, 1]
        while N > len(list):
            list.append(list[len(list) - 2] + list[len(list) - 1])

        return list[N - 2] + list[N - 1]

print(fib(6))