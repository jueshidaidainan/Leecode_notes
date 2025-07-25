// 矩阵置0 利用两个标志数组来标记
// 先遍历矩阵来确定哪些行和列需要被置0 然后再遍历一次矩阵，根据标志数组来将对应的行和列置0
class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean flagCol = false, flagRow = false;
        for(int i = 0; i < m; i++){
            if(matrix[i][0] == 0){
                flagCol = true;
                break;
            }
        }
        for(int j = 0; j < n; j++){
            if(matrix[0][j] == 0){
                flagRow = true;
                break;
            }
        }
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                if(matrix[i][j] == 0) matrix[i][0] = matrix[0][j] = 0;
            }
        }
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                if(matrix[i][0] == 0 || matrix[0][j] == 0) matrix[i][j] = 0;
            }
        }
        if(flagCol){
            for(int i = 0; i < m; i++) matrix[i][0] = 0;
        }
        if(flagRow){
            for(int j = 0; j < n; j++) matrix[0][j] = 0;
        }
        
    }
}