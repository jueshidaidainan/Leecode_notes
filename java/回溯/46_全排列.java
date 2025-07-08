// 提起全排列，刚开始考虑的是高中数学的公式，但是计算机如何实现呢，想到按照位置进行循环判断，那么数组的长度改变后，循环怎么写
// 计算机递归实现，就是用一个数组来保存当前路径，一个数组来保存是否被使用过，一个数组来保存结果，然后递归进行。
// 也就是用回溯来解决，本质思想和按位置遍历使用一样的，但是计算机的语言是回溯。
// 那么回溯和递归的区别？我理解回溯是一种思想，用递归来实现，而且回溯的递归需要恢复现场（因为从一个分支递归下去，那么并行的分支进行递归时环境不能被污染）递归中需要恢复

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        boolean []onPath = new boolean[nums.length];
        List<Integer> path = Arrays.asList(new Integer[nums.length]);
 
        dfs(0, nums, ans, onPath, path);
        return ans;
    }

    private void dfs(int i, int[] nums, List<List<Integer>> ans, boolean[]onPath, List<Integer> path){
        if(i == nums.length){
            ans.add(new ArrayList<>(path));//list加入的是引用，不是拷贝，所以需要new一个
            return;
        }
        for(int j = 0; j < nums.length; j++){
            if(!onPath[j]){
                path.set(i, nums[j]);//必须set，path的大小是固定的，所以每次递归path都是重用的,所以也就不用恢复现场了。注意这里是i表示每次递归的深度，j表示广度
                onPath[j] = true;
                dfs(i + 1, nums, ans, onPath, path);
                onPath[j] = false;//恢复现场，递归另一种情况
            }
        }
    }
}