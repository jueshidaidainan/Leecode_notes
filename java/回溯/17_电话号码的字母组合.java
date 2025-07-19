//虽然这道题在回溯里，但是由于不需要恢复现场（回退，path直接覆盖即可），所以严格意义上来说其实是递归
//利用递归遍历每个电话号码的长度，实现人脑的全排列即可
//这道题也体现出了：递归可以用来解决多重循环的问题（特别是二重循环往上一般就要考虑递归了）
class Solution {
    private static final String[] MAPPING =  new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    public List<String> letterCombinations(String digits) {
        int n = digits.length();
        if(n == 0){
            return List.of();//返回空的list，这是list.of代表不可变
        }
        List<String> ans  = new ArrayList<>();
        char[] path = new char[n];
        dfs(0, ans, path, digits.toCharArray());
        return ans;
    }

    private void dfs(int i, List<String>ans, char[] path, char[]digits){
        if(i == digits.length){
            ans.add(new String(path));
            return;
        }
        String letters = MAPPING[digits[i] - '0'];//利用ASCII码求下标
        for(char c : letters.toCharArray()){
            path[i] = c;//return 之后在for循环里进行覆盖
            dfs(i + 1, ans, path, digits);
        }
    }
}