class Solution:
    def orderlyQueue(self, S, K):
        """
        :type S: str
        :type K: int
        :rtype: str
        """
        print(S)
        if K>1:
            return "".join(sorted(S))
        else:
            return min(S[i:]+S[:i] for i in range(len(S)))

s = Solution()
print(s.orderlyQueue('cba',1))