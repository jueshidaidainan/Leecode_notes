// 合并区间，大三李朝晖的那个算法课期末考试似乎就是这道题
// 区间的合并逻辑并不复杂，只是题目给定的区间是乱序的，并不是按照左端点进行排序的
class Solution {
    public int[][] merge(int[][] intervals) {
        bubble(intervals);
        // System.out.print(intervals);
        // Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0])); //对数组区间进行排序
        Array.sort(intervals, new Comparator<int[]>(){//上面lamda表达式的实现
            public int compare(int[] a, int[] b){
                return Integer.compare(a[0], b[0]);
            }
        });
        List <int[]> res = new ArrayList<>();//list里面装数组
        int[] currentInterval = intervals[0];
        for(int i = 1; i < intervals.length; i++){
            if(intervals[i][0] <= currentInterval[1]){
                currentInterval[1] = Math.max(currentInterval[1], intervals[i][1]);
            }else{
                res.add(currentInterval);
                currentInterval = intervals[i];
            }
        }
        res.add(currentInterval);//将最后那个数组加入进去，无论和前面数组合并了与否，因为for循环的判断逻辑是和下一个数组区间继续判断，这样最后一个数组区间就需要特殊处理
        return res.toArray(new int[res.size()][]);//将List转化为二维数组
    }

    public void bubble(int [][] intervals){// 这是我写的一个冒泡排序，都写的磕磕绊绊
        int n = intervals.length;
        int[] tmp = new int [2];
        for(int i = 0; i < n - 1; i++){
            for(int j = 0; j < n - i - 1; j++){
                if(intervals[j][0] > intervals[j + 1][0]){
                    tmp =  intervals[j];
                    intervals[j] = intervals[j + 1];
                    intervals[j + 1] =  tmp;
                }
            }
        }
        return ;
    }
}