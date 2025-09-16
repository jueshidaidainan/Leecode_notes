//从给定矩阵的右上角元素开始考虑，如果当前元素比目标值大，则这一列都大于目标值，则这一列的元素都大于目标值，可以减去这一列；
//如果当前元素比目标值小，则这一行都小于目标值，则可以减去这一行。
//注意 在while循环里，不要写三个if虽然也是大于小于等于三种情况，但是逻辑错了。因为后续两个if会依次执行。但是while循环里，每次其实只是三种循环中的一个。
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int i = 0;
        int j = matrix[0].length - 1;
        while(i < matrix.length && j >= 0){//还有剩余的元素
            if(matrix[i][j] == target) return true;
            if(matrix[i][j] > target){//减去一列
                j--;
            }else{//减去一行
                i++;
            }
        }
        return false;
    }
}