// 合并区间，大三李朝晖的那个算法课期末考试似乎就是这道题
// 区间的合并逻辑并不复杂，只是题目给定的区间是乱序的，并不是按照左端点进行排序的。
// 所以需要先进行左端点排序，然后判断后一个区间的左端点是否小于前一个区间的右端点，小于则进行合并，否则加入结果集。并更新区间的右端点为二者间最大的那个。
// 第一次写可能是按照官方的题解写的，将判断后的正确结果加入ans中，所以循环开始是从第二个区间开始。第二次采用灵神的题解，先将第一个存入ans，存入元素的右端点与下一个区间重合就对其进行修改。代码更优雅。
//2025.9.30
class Solution {
    public int[][] merge(int[][] intervals) {
        List<int[]> ans = new ArrayList<>();
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);//利用lambda表达式按照左端点进行排序

        for(int[] p : intervals){
            int m = ans.size();
            if(m > 0 && p[0] <= ans.get(m - 1)[1]){//m-1不能越界。且可以合并的情况
                ans.get(m - 1)[1] = Math.max(p[1], ans.get(m - 1)[1]);
            }else{//不能合并的情况
                ans.add(p);
            }
        }

        return ans.toArray(new int[0][]);//List的toArray方法，jdk6之后只需要传入需要转换的类型即可，长度写0，不够ans存的话，会自己扩
    }
}


//2025.3.16
class Solution {
    public int[][] merge(int[][] intervals) {
        // bubble(intervals);
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