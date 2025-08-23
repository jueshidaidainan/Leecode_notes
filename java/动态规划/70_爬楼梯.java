//思路：f(i)表示上到第i个台阶的路径数，我们可以从第i-1阶跳一步，也可以从i-2阶跳两步，所以f(i) = f(i-1) + f(i-2)

//自己结合gemini写的优化过的递归写法,如果普通的递归对于有些用例会超时。但是如果我们写出递归的搜索树，我们会发现对于每个i，只要的计算会重复，所以我们可以利用缓存来优化递归。
class Solution {

    public int climbStairs(int n) {

        int[] cache = new int[n + 2];
        // Arrays.setAll(cache, i -> -1);
        Arrays.fill(cache, -1);
        return dfs(n, cache);
    }

    private int dfs(int i, int[] cache) {
        if (i == 0)
            return 1;
        if (i < 0)
            return 0;

        if (cache[i] != -1)
            return cache[i];
        int res = dfs(i - 1, cache) + dfs(i - 2, cache);
        cache[i] = res;
        return res;
    }
}

//dp的极致优化版本，递推公式只维护两个变量。因为递推公式只依赖前两个变量，所以只需要两个变量就可以完成dp。
class Solution {
    public int climbStairs(int n) {
        int f0 = 1, f1 = 1;//初始化，注意f0和f1的初始值，从f(2)=f(0)+ff(1)理解
        for(int i = 2; i <= n; i++){
            int newF = f0 + f1;
            f0 = f1;
            f1 = newF;
        }
        return f1;
    }
}