#dynamic programming to solve coins change given total amount and list of denominations,
#idea to solve this issue is, to compute the total ways of change, first compute the ways not using one denomination
#and compute ways of the rest amount using this denomination, then add them together

class Solution:
    def change_recursive(self, amount, coins):
        """
        :type amount: int
        :type coins: List[int]
        :rtype: int
        """

        if amount < 0:
            return 0
        if amount == 0:
            return 1
        if not coins:
            return 0
        denominationlength = len(coins)
        return self.change_recursive(amount - coins[denominationlength - 1], coins) + self.change_recursive(amount,coins[:denominationlength - 1])

    def change_V2(self,amount,coins):
        ways = [1]+[0]*amount
        for i in coins:
            for j in range(1,amount+1):
                if j>=i:
                    ways[j] = ways[j] + ways[j-i]
        return ways[amount]


sl = Solution()

print(sl.change_V2(500,[3,5,7,9,10,11]))