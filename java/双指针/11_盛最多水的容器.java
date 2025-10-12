class Solution {
    public int maxArea(int[] height) {
        int n = height.length;
        int l = 0;
        int r = n - 1;
        int max_area = Area(l, r, height[l], height[r]);
        while(l < r){//双指针的写法，求的只是最大值，所以最大值每次要在判断函数里，比如最开始注释里的就是犯了这个毛病，当然这个循环的逻辑还可以优化
            // max_area = Area(l, r, height[l], height[r]);
            if(height[l] <= height[r]){
                // max_area = Math.max(Area(l, r, height[l], height[r]), Area(l + 1, r, height[l + 1], height[r]));
                max_area = Math.max(max_area, Area(l + 1, r, height[l + 1], height[r]));
                l++;
            }else{
                // max_area = Math.max(Area(l, r, height[l], height[r]), Area(l, r - 1, height[l], height[r - 1]));
                max_area = Math.max(max_area, Area(l, r - 1, height[l], height[r - 1]));
                r--;
            }
            System.out.println(max_area);
        }
        return max_area;
    }

    public int Area(int i, int j, int h1, int h2){//计算面积的函数 i < j
        int h = (h1 < h2) ? h1 : h2;
        return h * (j - i);
    }
}

//改进版本
class Solution {
    public int maxArea(int[] height) {
        int n = height.length;
        int l = 0;
        int r = n - 1;
        int max_area = Area(l, r, height[l], height[r]);
        while(l < r){
            // max_area = Area(l, r, height[l], height[r]);
            max_area = Math.max(max_area, Area(l, r, height[l], height[r]));
            if(height[l] <= height[r]){
                // max_area = Math.max(Area(l, r, height[l], height[r]), Area(l + 1, r, height[l + 1], height[r]));
                l++;
            }else{
                // max_area = Math.max(Area(l, r, height[l], height[r]), Area(l, r - 1, height[l], height[r - 1]));
                r--;
            }
            System.out.println(max_area);
        }
        return max_area;
    }

    public int Area(int i, int j, int h1, int h2){//计算面积的函数 i < j
        int h = (h1 < h2) ? h1 : h2;
        return h * (j - i);
    }
}

// 题解C++
class Solution {
public:
    int maxArea(vector<int>& height) {
        int l = 0, r = height.size() - 1;
        int ans = 0;
        while (l < r) {
            int area = min(height[l], height[r]) * (r - l);
            ans = max(ans, area);
            if (height[l] <= height[r]) {
                ++l;
            }
            else {
                --r;
            }
        }
        return ans;
    }
};
