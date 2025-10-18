public class 543_二叉树的直径 {
    // 二叉树的直径是指树中任意两个节点之间最长路径的长度 。这条路径可能经过也可能不经过根节点 root 。
    // 两节点之间路径的长度由它们之间边数表示。
}


//我们可以知道直径的两个端点一定是叶子节点，若不是叶子节点，那就一定可以延长，那它就不是直径了。
//所以我们可以遍历每个节点，将每个节点看作拐点（当然直径可以是条链，我们的算法也是支持的），计算左右子树的最长链，然后拼接为当前节点的最长链。在递归遍历中维护一个全局变量，记录最长路径。
class Solution {
    private int ans = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return ans;
    }
    private int dfs(TreeNode node){
        if(node == null){
            return -1;//对于叶子节点，返回-1再加上下面的1，就是0
        }

        int rLeft = dfs(node.left) + 1;//左子树最大链长+1
        int rRight = dfs(node.right) + 1;//右子树最大链长+1
        ans = Math.max(ans, rLeft + rRight);//两条链拼成路径
        return Math.max(rLeft, rRight);//当前子树最大链长
    }
}