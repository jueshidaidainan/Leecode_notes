//自己的解法，之前在acwing那里应该写过，提交了10次，快debug把26个测试用例全试了
//核心思想：一圈一圈地旋转矩阵，先计算出需要旋转的圈数min_loop，然后在for循环里旋转遍历，最后判断没有被遍历的地方，肯定是单行或者单列，
//然后进行遍历即可
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        LinkedList<Integer> res = new LinkedList<>();
        int m = matrix.length;
        int n = matrix[0].length;
        int tm = m;
        int tn = n;
        if (m == 1) {
            for (int j = 0; j < matrix[0].length; j++) {
                res.add(matrix[0][j]);
            }
            return res;
        }
        if (n == 1) {
            for (int i = 0; i < matrix.length; i++) {
                res.add(matrix[i][0]);
            }
            return res;
        }
        int min_loop = Math.min(m, n) / 2;
        int k = 0;
        if (min_loop == 0)
            min_loop = 1;
        for (; k < min_loop; k++) {
            int i = k;
            int j = k;
            for (; j < n; j++) {//right
                res.add(matrix[i][j]);
            }
            for (i = i + 1, j--; i < m; i++) {//down
                res.add(matrix[i][j]);
            }
            for (j = j - 1, i--; j >= k; j--) {//left
                res.add(matrix[i][j]);
            }
            for (j = j + 1, i--; i > k; i--) {//up
                res.add(matrix[i][j]);
            }
            m = m - 1;
            n = n - 1;
            tm = tm - 2;
            tn = tn - 2;
            if (tm == 0 || tn == 0) {
                return res;
            }
        }
        if (tn > tm) {//row
            for (int j = k; j < n; j++) {
                res.add(matrix[k][j]);
            }
        }
        if (tn < tm) {//coloum
            for (int i = k; i < m; i++) {
                res.add(matrix[i][k]);
            }
        }
         if (tn == tm) {//九宫格那种行列相当的
            res.add(matrix[k][k]);
        }
        return res;
    }
}

// 评论区的题解，和上面解法思路一样，但是代码更简洁
// 将循环是否结束的逻辑与向List中添加元素一起，代码更优雅
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        LinkedList<Integer> res = new LinkedList<>();
        int top = 0;
        int l = 0;
        int r = matrix[0].length - 1;
        int bottom = matrix.length - 1;
        while(true){
            for(int i = l; i <= r; i++) res.add(matrix[top][i]);//ringht
            if(++top > bottom) break;
            for(int i = top; i <= bottom; i++) res.add(matrix[i][r]);//down
            if(--r < l) break;
            for(int i = r;  i >= l; i--) res.add(matrix[bottom][i]); //left
            if(--bottom < top) break;
            for(int i = bottom; i >= top; i--) res.add(matrix[i][l]); //up
            if(++l > r) break;
        }
        return res;
    }
}