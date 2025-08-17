//这道题虽然也是图，但是不是采用递归的方法，也就是深度搜素，而是广度搜索。刚开始沿着岛屿问题的思路，写dfs，发现最小的感染时间可能需要比对获得。而且这道题的感染方式也更适合广度搜索。
//思路：先遍历找到第一个腐烂的橘子，同时记录下来新鲜橘子的数量，方便返回的时候判断是否感染完全。
//注意bfs一般是使用队列来写，但是灵神为了方便使用了双列表。静态的DIRECTIONS，也很方便。
//while循环的判断条件，下面的注释我写了为什么要有两个。q非空一般bfs都会有，重点是为什么要有这两个，缺一不可。


class Solution {
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int fresh = 0;
        List<int[]> q = new ArrayList<>();
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 1){
                    fresh++;
                }else if(grid[i][j] == 2){
                    q.add(new int[]{i, j});//第一个坏橘子
                }
            }
        }

        int ans = 0;
        while(fresh > 0 && !q.isEmpty()){//有新鲜橘子且坏橘子队列非空,一个都不能少，fresh > 0如果没有，则即使没有新鲜橘子，ans也会++。q.isEmpty()若是没有，完全感染不到的橘子，就会在while循环了陷入死循环。
            ans ++;//过了一分钟
            List <int []> tmp = q;//之前的队列需要遍历来感染新橘子，新感染的坏橘子也需要存储，其实队列可以不开这个空间，但是两个列表这样写简单
            q = new ArrayList<int []>();
            for(int[] pos : tmp){
                for(int [] dir : DIRECTIONS){
                    int i = pos[0] + dir[0];
                    int j = pos[1] + dir[1];
                    if(0 <= i && i < m && 0 <= j && j < n && grid[i][j] == 1){//新鲜橘子
                        fresh--;
                        grid[i][j] = 2;//橘子变坏
                        q.add(new int[]{i, j});
                    }
                }
            }
        }

        return fresh > 0 ? -1 : ans;
    }
}