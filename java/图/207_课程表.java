//课程表是否能够修完，其实就是看课程科目之间是否会存在互相依赖的问题。如果将课程构建为一个图，也就是看图里面是否存在环。
//我们知道链表是否存在环，可以使用快慢指针，如果快慢指针相遇则存在环。那么图结构如何判断是否存在环，则可以使用dfs的深搜图的方式。在递归的过程中如果访问的节点还在栈里，则表示存在环。
//我们这里使用邻接表的方式，效率更高，注意邻接表创建数组对象，使用Arrays.setAll()方法初始化数组元素。
//创建一个数组colors，记录节点的访问状态，0表示未访问，1表示正在访问，2表示已经访问过。

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {    
        int n = numCourses;
        List<Integer>[] g = new ArrayList[n];//创建数组对象，使用邻接表的形式，比邻接矩阵效率更高。数组内的对象是ArrayList
        Arrays.setAll(g, i -> new ArrayList<>());//初始化数组元素，使用lambda表达式

        for(int[] p : prerequisites){
            g[p[1]].add(p[0]);
        }

        int[] colors = new int[n];
        for(int i = 0; i < numCourses; i++){
            if(colors[i] == 0 && isLoop(g, i, colors)){//递归看图里有没有环
                return false;
            }
        }
        return true;

    }

    private boolean isLoop(List<Integer>[] g, int x, int[] colors){
        colors[x] = 1;//正在访问

        for(int y : g[x]){
            if(colors[y] == 1 || colors[y] == 0 && isLoop(g, y, colors)){
                return true;//找到了环
            }
        }
        colors[x] = 2;//已经被访问过
        return false;
    }
}