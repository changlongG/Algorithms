class Solution:
    def singleNonDuplicate(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        resultleft = 0
        resultright = 0
        result = 0
        if len(nums) == 0:
            pass
        elif len(nums) == 1:
            return nums[0]
        elif len(nums)>2:
            mid = len(nums)//2
            if nums[mid] == nums[mid-1]:
                resultleft = self.singleNonDuplicate(nums[:mid-1])
                resultright = self.singleNonDuplicate(nums[mid+1:])
            elif nums[mid] == nums[mid+1]:
                resultleft = self.singleNonDuplicate(nums[:mid])
                resultright = self.singleNonDuplicate(nums[mid+2:])
            else:
                result = nums[mid]
        if resultleft != 0:
            result = resultleft
        elif resultright != 0:
            result = resultright
        return result
sl = Solution()
list = [1,1,2,3,3,4,4,8,8]
print(sl.singleNonDuplicate(list))