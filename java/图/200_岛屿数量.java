//这道题虽然是在图标签了，其实图是一个二维矩阵罢了
//要求岛屿数量，其实就是用递归来解的，还不需要维护现场，因为不涉及回溯。但是为了避免走回头路陷入死循环，需要一个标记矩阵，标记访问过的格子为2。
//递归的出口就是遇到了访问过的格子和边界。
//注意需要先修改访问过的格子为2，再递归，不然会重复访问。而且要先判断是否越界，再判断是否越界，避免访问到无效空间。
class Solution {
    public int numIslands(char[][] grid) {
        int ans = 0;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){//遇到孤岛后，从棋盘的宏观角度再次往下走
                if(grid[i][j] == '1'){
                    dfs(grid, i, j);
                    ans++;
                } 
            }
        }
        return ans;
    }

    private void dfs(char[][] grid, int i, int j){
        if(i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] != '1'){//这里的判断逻辑，grid[i][j] != '1'一定要在最后，确保这个格子没有越界，才可以访问
            return;
        }
        grid[i][j] = '2';//先标记，避免又访问回头路
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
        dfs(grid, i - 1, j);
        dfs(grid, i + 1, j);
    }
}