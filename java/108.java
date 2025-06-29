/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
// 递归的写法 return 也就是递归返回什么，如何接收，是得细品啊
// 再者是递归出口的判断逻辑决定了区间分治的开闭逻辑：left == right 为递归出口的话，代表着左闭右开区间（因为[2,2]会被判定为非法，所以右开），left > right 为递归出口的话，代表着左闭右闭区间（[2,2]会是合法的，所以右闭），但是所有的区间得遍历到，那么区间左侧一定闭（个人理解）
class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        return dfs(nums, 0, nums.length);
    }
    private TreeNode dfs(int [] nums, int left, int right){
        if(left == right) return null; // 这样是左闭右开区间的写法
        int mid = (left + right) >>> 1; //>>> 无符号右移 符合二分这种正数索引的题 避免溢出
        return new TreeNode(nums[mid], dfs(nums, left, mid), dfs(nums, mid + 1, right)); // 巧妙利用给定的构造函数结合return来写递归
    }
}