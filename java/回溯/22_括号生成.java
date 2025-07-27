// 括号生成 本题采用回溯的写法
// 左括号和右括号其实就是子集枚举里选与不选的情况，只是这里有限制：字符串的任意前缀，左括号数量要>=右括号数量（这里就是剪枝的地方了）
// 所以当右括号数量等于n的时候，2n个位置也就满了。
// 而左括号的数量只有<n这个限制。


class Solution {
    private List<String> ans = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        if (n == 0) {
            return List.of();
        }
        char[] path = new char[n * 2];
        dfs(ans, path, 0, 0, n);
        return ans;
    }

    private void dfs(List<String> ans, char[] path, int numsL, int numsR, int n) {
        if (numsR == n) {
            ans.add(new String(path));
        }
        // 要求任何前缀里 左括号的数量都要大于等于右括号，但是添加右括号的前提是大于（不包含等于了）
        if(numsL < n){
            path[numsL + numsR] = '(';
            // numsL++; 不能这么写，这样写return回来之后，这一层的另一个分支也被改变了
            dfs(ans, path, numsL + 1, numsR, n);
        }
        if(numsL > numsR){
            path[numsL + numsR] = ')';
            // numsR++;
            dfs(ans, path, numsL, numsR + 1, n);
        }

    }
}