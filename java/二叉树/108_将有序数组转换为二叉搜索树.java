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
        return new TreeNode(nums[mid], dfs(nums, left, mid), dfs(nums, mid + 1, right)); // 巧妙利用给定的构造函数结合return来写递归，一次创建根节点和左右子树的根节点，所以相等的时候只有根节点会创建
    }
}

//2025.10.20 自己写的，递归的思路很明显，将数组按照中点一直分到不可分，中点左边是左子树，中点右边是右子树。但是这种题目就涉及到如何根据区间的开闭来写递归的边界条件了，要保证不重不漏。
//我好像倾向于写闭区间的写法，这道题就简单考虑如果左右端点重合，还是有意义的，所以写了l > r。但是我印象有种类型的题，灵神是推荐开区间写法的。
class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        return toBST(nums, 0, nums.length - 1);
    }

    private TreeNode toBST(int[] nums, int l, int r){
        if(l > r) return null;
        int mid = (r - l) / 2 + l;
        TreeNode cur = new TreeNode(nums[mid]);
        cur.left = toBST(nums, l, mid - 1);
        cur.right = toBST(nums, mid + 1, r);
        return  cur;
    }
}