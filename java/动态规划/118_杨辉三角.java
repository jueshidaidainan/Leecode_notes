//思路：这个问题初高中经常接触，比较熟悉，注意每行的第一个和最后一个是1，其余的位置，在数组表示的时候是上一行的正对着的位置和左边一个位置的值之和（j和j-1）

class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>(numRows);//预分配空间
        ans.add(List.of(1));//第一行

        for(int i = 1; i < numRows; i++){
            List<Integer> row = new ArrayList<>();
            row.add(1);//每行第一个是1
            for(int j = 1; j < i; j++){
                row.add(ans.get(i - 1).get(j - 1) + ans.get(i - 1).get(j));//左右肩之和
            }
            row.add(1);//每行最后一个也是1
            ans.add(row);
        }
        return ans;
    }
}