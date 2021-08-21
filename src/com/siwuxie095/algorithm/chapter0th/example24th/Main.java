package com.siwuxie095.algorithm.chapter0th.example24th;

/**
 * @author Jiajing Li
 * @date 2021-08-21 16:09:03
 */
public class Main {

    /**
     * 五、逻辑统⼀
     *
     * 来梳理⼀下所有细节差异的因果逻辑。
     *
     * 第⼀个，最基本的⼆分查找算法：
     *
     * 因为这里初始化 right = nums.length - 1
     * 所以决定了「搜索区间」是 [left, right]
     * 所以决定了 while (left <= right)
     * 同时也决定了 left = mid+1 和 right = mid-1
     *
     * 因为只需找到⼀个 target 的索引即可
     * 所以当 nums[mid] == target 时可以⽴即返回
     *
     *
     * 第⼆个，寻找左侧边界的⼆分查找：
     *
     * 因为这里初始化 right = nums.length
     * 所以决定了「搜索区间」是 [left, right)
     * 所以决定了 while (left < right)
     * 同时也决定了 left = mid + 1 和 right = mid
     *
     * 因为需找到 target 的最左侧索引
     * 所以当 nums[mid] == target 时不要⽴即返回
     * ⽽要收紧右侧边界以锁定左侧边界
     *
     *
     * 第三个，寻找右侧边界的⼆分查找：
     *
     * 因为这里初始化 right = nums.length
     * 所以决定了「搜索区间」是 [left, right)
     * 所以决定了 while (left < right)
     * 同时也决定了 left = mid + 1 和 right = mid
     *
     * 因为需找到 target 的最右侧索引
     * 所以当 nums[mid] == target 时不要⽴即返回
     * ⽽要收紧左侧边界以锁定右侧边界
     *
     * ⼜因为收紧左侧边界时必须 left = mid + 1
     * 所以最后⽆论返回 left 还是 right，必须减⼀
     *
     *
     * 对于寻找左右边界的⼆分搜索，常⻅的⼿法是使⽤左闭右开的「搜索区间」，这里还根据逻辑将「搜索区间」
     * 全都统⼀成了两端都闭，便于记忆，只要修改两处即可变化出三种写法：
     *
     * int binary_search(int[] nums, int target) {
     *     int left = 0, right = nums.length - 1;
     *     while(left <= right) {
     *         int mid = left + (right - left) / 2;
     *         if (nums[mid] < target) {
     *             left = mid + 1;
     *         } else if (nums[mid] > target) {
     *             right = mid - 1;
     *         } else if(nums[mid] == target) {
     *             // 直接返回
     *             return mid;
     *         }
     *     }
     *     // 直接返回
     *     return -1;
     * }
     *
     * int left_bound(int[] nums, int target) {
     *     int left = 0, right = nums.length - 1;
     *     while (left <= right) {
     *         int mid = left + (right - left) / 2;
     *         if (nums[mid] < target) {
     *             left = mid + 1;
     *         } else if (nums[mid] > target) {
     *             right = mid - 1;
     *         } else if (nums[mid] == target) {
     *             // 别返回，收紧右边界，锁定左侧边界
     *             right = mid - 1;
     *         }
     *     }
     *     // 最后要检查 left 越界的情况
     *     if (left >= nums.length || nums[left] != target)
     *         return -1;
     *     return left;
     * }
     *
     *
     * int right_bound(int[] nums, int target) {
     *     int left = 0, right = nums.length - 1;
     *     while (left <= right) {
     *         int mid = left + (right - left) / 2;
     *         if (nums[mid] < target) {
     *             left = mid + 1;
     *         } else if (nums[mid] > target) {
     *             right = mid - 1;
     *         } else if (nums[mid] == target) {
     *             // 别返回，收紧左边界，锁定右侧边界
     *             left = mid + 1;
     *         }
     *     }
     *     // 最后要检查 right 越界的情况
     *     if (right < 0 || nums[right] != target)
     *         return -1;
     *     return right;
     * }
     *
     * 如果以上内容你都能理解，那么恭喜你，⼆分查找算法的细节不过如此。
     *
     * 总结：
     * 1、分析⼆分查找代码时，不要出现 else，全部展开成 else if ⽅便理解。
     * 2、注意「搜索区间」和 while 的终⽌条件，如果存在漏掉的元素，记得在最后检查。
     * 3、如需定义左闭右开的「搜索区间」搜索左右边界，只要在 nums[mid] == target 时做修改即可，搜索右侧时需要减⼀。
     * 4、如果将「搜索区间」全都统⼀成两端都闭，好记，只要稍改 nums[mid] == target 条件处的代码和返回的逻辑即可。
     */
    public static void main(String[] args) {

    }

}
