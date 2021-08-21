package com.siwuxie095.algorithm.chapter0th.example23th;

/**
 * @author Jiajing Li
 * @date 2021-08-21 15:56:26
 */
public class Main {

    /**
     * 四、寻找右侧边界的⼆分查找
     *
     * 类似寻找左侧边界的算法，这⾥也会提供两种写法，还是先写常⻅的左闭右开的写法，只有两处和
     * 搜索左侧边界不同，已标注：
     *
     * int right_bound(int[] nums, int target) {
     *     if (nums.length == 0) return -1;
     *     int left = 0, right = nums.length;
     *
     *     while (left < right) {
     *         int mid = (left + right) / 2;
     *         if (nums[mid] == target) {
     *             left = mid + 1; // 注意
     *         } else if (nums[mid] < target) {
     *             left = mid + 1;
     *         } else if (nums[mid] > target) {
     *             right = mid;
     *         }
     *     }
     *     return left - 1; // 注意
     * }
     *
     * 1、为什么这个算法能够找到右侧边界？
     *
     * 答：类似地，关键点还是这⾥：
     *
     * if (nums[mid] == target) {
     *     left = mid + 1;
     *
     * 当 nums[mid] == target 时，不要⽴即返回，⽽是增⼤「搜索区间」的下界 left，使得区间
     * 不断向右收缩，达到锁定右侧边界的⽬的。
     *
     *
     * 2、为什么最后返回 left - 1 ⽽不像左侧边界的函数，返回 left ？⽽且这⾥既然是搜索右侧
     * 边界，应该返回 right 才对。
     *
     * 答：⾸先，while 循环的终⽌条件是 left == right，所以 left 和 right 是⼀样的，你⾮
     * 要体现右侧的特点，返回 right - 1 好了。
     *
     * ⾄于为什么要减⼀，这是搜索右侧边界的⼀个特殊点，关键在这个条件判断：
     *
     * if (nums[mid] == target) {
     *     left = mid + 1;
     *     // 这样想: mid = left - 1
     *
     * 因为对 left 的更新必须是 left = mid + 1，就是说 while 循环结束时， nums[left] ⼀
     * 定不等于 target 了，⽽ nums[left-1] 可能是 target。
     *
     * ⾄于为什么 left 的更新必须是 left = mid + 1，同左侧边界搜索，就不再赘述。
     *
     *
     * 3、为什么没有返回 -1 的操作？如果 nums 中不存在 target 这个值，怎么办？
     *
     * 答：类似之前的左侧边界搜索，因为 while 的终⽌条件是 left == right，就是说 left 的
     * 取值范围是 [0, nums.length]，所以可以添加两⾏代码，正确地返回 -1：
     *
     * while (left < right) {
     *     // ...
     * }
     * if (left == 0) return -1;
     * return nums[left-1] == target ? (left-1) : -1;
     *
     *
     * 4、是否也可以把这个算法的「搜索区间」也统⼀成两端都闭的形式呢？这样这三个写法就完全统⼀
     * 了，以后就可以闭着眼睛写出来了。
     *
     * 答：当然可以，类似搜索左侧边界的统⼀写法，其实只要改两个地⽅就⾏了：
     *
     * int right_bound(int[] nums, int target) {
     *     int left = 0, right = nums.length - 1;
     *     // 搜索区间为 [left, right]
     *     while (left <= right) {
     *         int mid = left + (right - left) / 2;
     *         if (nums[mid] < target) {
     *             // 搜索区间变为 [mid+1, right]
     *             left = mid + 1;
     *         } else if (nums[mid] > target) {
     *             // 搜索区间变为 [left, mid-1]
     *             right = mid - 1;
     *         } else if (nums[mid] == target) {
     *             // 收缩左侧边界
     *             left = mid + 1;
     *         }
     *     }
     *
     *     // 检查出界情况
     *     if (right < 0 || nums[right] != target)
     *         return -1;
     *     return right;
     * }
     *
     * 当 target ⽐所有元素都⼩时，right 会被减到 -1，所以需要在最后防⽌越界。
     *
     * ⾄此，搜索右侧边界的⼆分查找的两种写法也完成了，其实将「搜索区间」 统⼀成两端都闭反⽽
     * 更容易记忆，你说是吧？
     */
    public static void main(String[] args) {

    }

}
