// 这是一道有点类似于八皇后的题目
// 外层循环遍历每一个格子为起点，调用回溯判断的函数dfs
// 如果当前的位置超出了边界或者当前格子不是目标字符，则返回false
// 否则，将当前格子置为“空”标记已经被访问过（这样接下来判断上下左右四个方向的时候肯定和word的第k+1个对不上），并调用dfs判断上下左右的方向是否和第k+1个字母匹配
// 恢复现场时，将当前格子还原为原来的字符
// 找到一个就返回true，否则返回false
class Solution {
    public boolean exist(char[][] board, String word) {
        char[] words = word.toCharArray();
        for(int i = 0; i < board.length; i++){//遍历每一个格子为起点
            for(int j = 0; j < board[0].length; j++)
                if(dfs(board, words, i, j, 0)) return true;
        }
        return false;
    }

    private boolean dfs(char[][] board, char[] word, int i, int j, int k){
        if(i < 0 || i >= board.length || j < 0 || j >= board[0].length || word[k] != board[i][j]){//超出边界或者不匹配都返回false
            return false;
        }
        if(k == word.length - 1) return true;// 只有全部匹配才会return true
        board[i][j] = '\0';//置空

        boolean ans = dfs(board, word, i + 1, j, k + 1) || dfs(board, word, i - 1, j, k + 1) ||
                      dfs(board, word, i, j + 1, k + 1) || dfs(board, word, i, j - 1, k + 1);
        board[i][j] = word[k];//恢复现场
        return ans;
    }
}