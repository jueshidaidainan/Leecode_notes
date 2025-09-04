//这道题刚开始想的时候，没想明白这个子序列允许元素不连着。如果按照选或者不选来想，枚举一个字符串的所有字串，与另外一个字串进行比较。那肯定超时了。
//看了灵神的写法，原来是使用双指针，考虑两个字符串每个字符的前缀的最长公共子序列。这样就和动态规划结合起来了。
//位置i和j要是相等就+1，不等就分两个字符串指针分别移动，求最大值。
//至于边界什么的，这道题都是0，所以倒是不用特殊处理。



//记忆化搜索的写法
class Solution {
    private char[] t1, t2;
    private int [][] memo;
    public int longestCommonSubsequence(String text1, String text2) {
        t1 = text1.toCharArray();
        t2 = text2.toCharArray();
        int n = t1.length;
        int m = t2.length;
        memo = new int[n][m];
        for(int[] row : memo){
            Arrays.fill(row, -1);
        }
        return dfs(n - 1, m - 1);
    }

    private int dfs(int i, int j){
        if(i < 0 || j < 0){
            return 0;
        }
        if(memo[i][j] != -1) return memo[i][j];
        if(t1[i] == t2[j]){
            return memo[i][j] = dfs(i - 1, j - 1) + 1;
        }
        return memo[i][j] = Math.max(dfs(i - 1, j), dfs(i, j - 1));
    }
}

//递推的写法
class Solution {

    public int longestCommonSubsequence(String text1, String text2) {
        char[] t1 = text1.toCharArray();
        char[] t2 = text2.toCharArray();
        int n = t1.length;
        int m = t2.length;
        int f[][] = new int[n + 1][m + 1];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(t1[i] == t2[j]){
                    f[i + 1][j + 1] = f[i][j] + 1;
                }else{
                    f[i + 1][j + 1] = Math.max(f[i + 1][j], f[i][j + 1]);
                }               
            }
        }
        return f[n][m];
    }
}