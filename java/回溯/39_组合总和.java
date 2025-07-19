//这道题算是回溯题，注意每个数字可以重复使用，先写了一版，发现结果会有重复的结果，所以要加上一个去重的方法
//这是因为dfs刚开始没有参数j，所以后续的数字和也会从头遍历candidates，和前面的数字产生了相同的结果，所以添加j去重
//但是又由于数字是可以重复使用的，dfs递归调用传入的参数就是j，而不是j+1
class Solution {
    private List<List<Integer>> ans = new ArrayList<>();
    private List<Integer> path = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if(target <= 0){
            return List.of();
        }
        dfs(candidates, target, 0, 0);
        return ans;
    }

    private void dfs(int[]candidates, int target, int sum, int j){
        if(sum == target){
            ans.add(new ArrayList<>(path));
        }
        if(sum > target){
            return;
        }

        for(int i = j; i < candidates.length; i++){
            path.add(candidates[i]);
            sum += candidates[i];
            dfs(candidates, target, sum, i);
            path.remove(path.size() - 1);// 这两行都是恢复现场
            sum -= candidates[i];
        }
    }
}