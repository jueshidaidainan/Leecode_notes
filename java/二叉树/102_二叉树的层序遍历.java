public class 102_二叉树的层序遍历 {
    
}

//层序遍历：先进先出。使用队列来完成，在访问每个节点后将其对应的左右子节点加入队列（如果有的话）
//需要注意的是如何知道每一层的节点个数，所以需要在最外面先加入根节点，在内层while的每次循环前，先获得队列的长度，然后根据长度来循环取出队列中的节点。
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null)
            return List.of();
        List<List<Integer>> ans = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int len = queue.size();
            List<Integer> tmp = new ArrayList<>();
            while (len-- > 0) {
                TreeNode cur = queue.poll();
                tmp.add(cur.val);
                if (cur.left != null)
                    queue.offer(cur.left);
                if (cur.right != null)
                    queue.offer(cur.right);
            }
            ans.add(tmp);
        }
        return ans;
    }
}