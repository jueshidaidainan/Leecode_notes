//这道题也是道双指针动态规划的题目。dfs(i, j) 代表了将字符串 s 的前缀 s[0...i] (从开头到第 i 个字符) 编辑成字符串 t 的前缀 t[0...j] (从开头到第 j 个字符) 所需要的最少操作次数。
//这样就方便理解递归出口了，当一方为空时，另一方就得插入或者删除自己的长度。
//递归状态转移的时候：当两个字符串的位置i和j的字符不相等的时候，有三种操作：替换、删除、插入。分别对应了dfs(i - 1, j - 1)、dfs(i - 1, j)、dfs(i, j - 1)

//递归的计划搜索的写法
class Solution {
    private char[]s, t;
    private int[][] memo;
    public int minDistance(String word1, String word2) {
        s = word1.toCharArray();
        t = word2.toCharArray();
        int n = s.length;
        int m = t.length;
        memo = new int[n][m];
        for(int []row : memo){
            Arrays.fill(row, -1);
        }
        return dfs(n - 1, m -1);
    }

    private int dfs(int i, int j){
        if(i < 0) return j + 1;//j+1次插入
        if(j < 0) return i + 1;//i+1次删除

        if(memo[i][j] != -1) return memo[i][j];
        if(s[i] == t[j]) return dfs(i - 1, j - 1);//相等，指针同时移动
        return memo[i][j] = Math.min(Math.min(dfs(i - 1, j), dfs(i, j - 1)), dfs(i - 1, j - 1)) + 1;//删除，插入，替换三种情况取最小。这三种任意一种的操作在当下都是一次操作，所以+1

    }
}

//双重for循环的写法：自底向上
//f[i][j] 代表将字符串 s 的前 i 个字符 (s 的长度为 i 的前缀) 转换为字符串 t 的前 j 个字符 (t 的长度为 j 的前缀) 所需要的最少编辑次数。
class Solution {

    public int minDistance(String word1, String word2) {
        char[] s = word1.toCharArray();
        char[] t = word2.toCharArray();
        int n = s.length;
        int m = t.length;
        int[][] f = new int[n + 1][m + 1];
        for (int j = 0; j < m; j++) {//初始化第一行,f[0][0]因为本来就该是0，所以不用初始化
            f[0][j + 1] = j + 1;
        }

        for(int i = 0; i < n; i++){
            f[i + 1][0] = i + 1;//初始化第一列,因为f[0][0]不用处理，所以也不用单独写for循环来处理，可以在状态赋值的时候顺便处理
            for(int j = 0; j < m; j++){
                if(s[i] == t[j]){
                    f[i + 1][j + 1]  = f[i][j];
                }else{
                    f[i + 1][j + 1] = Math.min(Math.min(f[i][j + 1], f[i + 1][j]), f[i][j]) + 1;
                }
            }
        }
        return f[n][m];
    }

}


// 三种选择与对应的代码
// 选择一：替换 (Replace)
// 思路：“我直接把 s[i] 这个字符替换成 t[j] 不就行了吗？”

// 操作：执行一次“替换”操作。代价是 1。

// 剩下的问题：现在 s[i] 和 t[j] 已经相等了，我们就不需要再管它们了。我们剩下的任务，就是把 s 剩下的部分 s[0...i-1] 变成 t 剩下的部分 t[0...j-1]。

// 对应的代码：这个“剩下的问题”的解，正好就是 dfs(i - 1, j - 1)。

// 总代价：1 + dfs(i - 1, j - 1)

// 选择二：删除 (Delete)
// 思路：“我觉得 s[i] 这个字符是多余的，我把它删除掉，让 s[0...i-1] 去和 t[0...j] 匹配试试。”

// 操作：执行一次“删除”操作。代价是 1。

// 剩下的问题：s[i] 被删掉了，我们剩下的任务，就是把 s 剩下的部分 s[0...i-1] 变成 t 的整个前缀 t[0...j]。

// 对应的代码：这个“剩下的问题”的解，正好就是 dfs(i - 1, j)。

// 总代价：1 + dfs(i - 1, j)

// 选择三：插入 (Insert)
// 思路：“s[0...i] 的末尾好像少了个字符，我插入一个 t[j] 来匹配它。”

// 操作：执行一次“插入”操作。代价是 1。

// 剩下的问题：我们在 s 的末尾插入了 t[j]，它和 t 的末尾匹配上了。我们剩下的任务，就是把 s 的原始部分 s[0...i] 变成 t 除去末尾后的部分 t[0...j-1]。

// 对应的代码：这个“剩下的问题”的解，正好就是 dfs(i, j - 1)。

// 总代价：1 + dfs(i, j - 1)