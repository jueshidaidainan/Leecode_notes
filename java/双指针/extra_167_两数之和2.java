public class extra_167_两数之和2 {
    
}

//做了三数之和来看这道题，本能的想写去重的逻辑，但是这道题保证答案有且唯一，所以逻辑就简单了。不用去重，找到就可以。
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int [] ans = new int[2];//为了满足编译器对返回值的检查，实则用不到
        int n = numbers.length;
        int L = 0;
        int R = n - 1;

       while(L < R){
        int s = numbers[L] + numbers[R];
        if(s < target){
            L++;
        }else if(s > target){
            R--;
        }else{
            return new int[]{L + 1, R + 1};
        }
       }
        return ans;
    }
}