//本来写了一个两层for循环的，后面的测试用例会超时
//其实买卖股票只需要遍历一次就可以，维护两个变量，一个记录最低价格，一个记录最大利润。
class Solution {
    public int maxProfit(int[] prices) {
        int ans = 0;
        int minPrice = prices[0];
        for(int p : prices){
            minPrice = Math.min(p, minPrice);
            ans = Math.max(ans, p - minPrice);
        }
        return ans;
    }
}