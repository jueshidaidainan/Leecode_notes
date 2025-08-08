//自己写的解法，题解也差不多这个意思
class Solution {
    //将矩阵展开看作数组就行了，用整除和取余完成就行
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int l = 0;
        int r = m * n -1;
        while(l <= r){//闭区间写法
            int mid = l + (r - l) / 2;
            int row = mid / n;
            int col = mid % n;
            if(matrix[row][col] == target) return true;
            if(matrix[row][col] < target){
                l = mid + 1;
            }else{
                r = mid - 1;
            }
        }
        return false;
    }
}