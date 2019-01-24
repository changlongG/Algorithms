#find minimum time difference in a list of time, eg:["hh:mm","hh:mm"], first covert 
#string of time to minutes(int), sort the coverted array and then the problem will be 
#find the minimum different of integer in a sorted array

import datetime
class Solution:
    def findMinDifference(self, timePoints):
        """
        :type timePoints: List[str]
        :rtype: int
        """
        length = len(timePoints)
        for i in range(length):
            timePoints[i] = int(timePoints[i][:2])*60+int(timePoints[i][3:])
        timePoints.sort()

        mindiff = 60*24
        for j in range(length):
            if j == length - 1:
                break
            else:
                mindiff = min(mindiff, timePoints[j+1] - timePoints[j])

        mindiff = min(mindiff, 60*24-(timePoints[length-1] - timePoints[0]))
        return mindiff

sl = Solution()
print(sl.findMinDifference(["04:01","02:01"]))