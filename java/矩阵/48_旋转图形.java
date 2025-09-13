//将矩阵绕着中心点顺时针旋转90°
//分析：所以位于 i 行 j 列的元素，去到 j 行 n−1−i 列，即 (i,j)→(j,n−1−i)。
//思路：先转置，再将每行进行reverse
class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for(int i = 0; i < n; i++){//转置
            for(int j = 0; j < i; j++){
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }

        for(int[] row : matrix){//每行的元素进行翻转
            for(int l = 0; l < n / 2; l++){
                int tmp = row[l];
                row[l] = row[n - 1 - l];
                row[n - 1 - l] = tmp;
            }
        }
    }
}