#binarty search in a sorted 2d matrix
class Solution:
    def binarySearch(self, array, target):

        if len(array) == 0:
            return False
        low_col = 0
        high_col = len(array) - 1
        mid = (high_col - low_col) // 2
        while low_col <= high_col:
            if low_col == high_col and array[low_col] != target:
                return False
            if array[mid] == target:

                return True
            elif array[mid] > target:
                high_col = mid - 1
                mid = (high_col - low_col) // 2
            elif array[mid] < target:
                low_col = mid + 1
                mid = (high_col - low_col) // 2 + low_col
        return False

    def searchMatrix(self, matrix, target):
        """
        :type matrix: List[List[int]]
        :type target: int
        :rtype: bool
        """
        row = len(matrix)
        low_row = 0
        high_row = row - 1
        if row == 0:
            return False
        elif row == 1:

            return self.binarySearch(matrix[0], target)
        elif matrix[high_row][0] < target:
            return self.binarySearch(matrix[high_row], target)

        else:
            mid = (high_row - low_row) // 2
            while low_row <= high_row:

                if matrix[mid][0] == target:
                    return True
                elif matrix[mid][0] > target and matrix[mid - 1][0] < target:

                    return self.binarySearch(matrix[mid - 1], target)

                elif matrix[mid][0] > target:
                    high_row = mid - 1
                    mid = (high_row - low_row) // 2
                elif matrix[mid][0] < target:
                    low_row = mid + 1
                    mid = (high_row - low_row) // 2 + low_row
        return False




