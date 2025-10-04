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
// 这版将cnt改成全局变量，避免函数参数传递，逻辑更清晰。
class Solution {
    private int ans = -1;
    private int cnt = 0;
    public int kthSmallest(TreeNode root, int k) {
        dfs(root, k);
        return ans;
    }
    public void dfs(TreeNode node, int k){
        if(node == null || cnt >= k){//cnt已经大于k了，证明已经找到了，递归栈里其他的分支的右子树就不需要探索了
            return;
        }
        dfs(node.left, k);
        cnt++;
        if(cnt == k){
            ans = node.val;
            return;//找到了就提前返回，不用探索右子树
        }
        dfs(node.right, k);
    }
}

//刚开始使用值传递的写法，感觉逻辑挺奇怪，因为cnt是值传递，所以还得使用l参数来接收
class Solution {
    private int ans = -1;

    public int kthSmallest(TreeNode root, int k) {
        DFS(root, 1, k);
        return ans;
    }

    private int DFS(TreeNode node, int cnt, int k) {
        if (node == null || cnt == k + 1) { //找到之后就返回，避免过多遍历
            return cnt;
        } 
        int l = DFS(node.left, cnt, k);//递归左边
        if (l == k) {//访问节点
            ans = node.val;
        }
        l++;//每访问一个节点，序数递增
        return DFS(node.right, l, k);
    }
}