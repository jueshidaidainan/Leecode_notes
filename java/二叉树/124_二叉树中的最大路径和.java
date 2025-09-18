//求图中最大的路径和，算的是节点的路径和。 路径有两种情况，一种是跨过某个根节点，叫做链；另一种是不跨过根节点（根节点也有可能是内部的根节点），叫做直径。
//递归求解，返回当前节点作为根节点的最大贡献值，即当前节点的值加上左右子节点的最大贡献值（会选取大于0的子节点）。而最大的路径和肯定是在直径里面找一个，所以在递归的同时维护最大值即可。


class Solution {
    private int ans = Integer.MIN_VALUE;
    
    public int maxPathSum(TreeNode root) {
        if(root.left == null && root.right == null) return root.val;
        this.DFS(root);
        return ans;
    }
    private int DFS(TreeNode node){
        if(node == null) return 0;
        
        //递归计算左右子节点的最大贡献值，只会选取大于0的子节点
        int lLen = Math.max(DFS(node.right), 0);
        int RLen = Math.max(DFS(node.left), 0);
        
        //curMaxR代表当前节点作为根节点的最大贡献（是一条链，一个根节点下去的），会作为返回值return
        int curMaxR = node.val + Math.max(lLen, RLen);

        //加上根节点和左右两边子树，是直径，最终是ans
        int curC = lLen + RLen + node.val;

        
        ans = Math.max(ans, curC);
        return curMaxR;
    }
}