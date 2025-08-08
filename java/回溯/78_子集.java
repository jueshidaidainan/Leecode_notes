// 使用回溯的思想来写，核心也是每个元素选与不选两种情况
class Solution {
    List<Integer> t = new ArrayList<>();
    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        dfs(0, nums);
        return ans;
    }
//用回溯思想，递归写法来解
//但是递归其实也是实现了迭代的思想，针对n个位置，判断选取或者不选取，cur就是标记这个的，所以不选取和选取都是+1
//注意，虽然是从0开始，但是由于是提前加一，所以递归出口也就是==length
    private void dfs(int cur, int[] nums) {
        if (cur == nums.length) {
            ans.add(new ArrayList<Integer>(t));
            return;
        }

        t.add(nums[cur]);
        dfs(cur + 1, nums);
        t.remove(t.size() - 1);
        dfs(cur + 1, nums);
    }
}