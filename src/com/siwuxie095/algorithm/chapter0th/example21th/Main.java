package com.siwuxie095.algorithm.chapter0th.example21th;

/**
 * @author Jiajing Li
 * @date 2021-08-21 14:59:00
 */
public class Main {

    /**
     * 二、寻找⼀个数（基本的⼆分搜索）
     *
     * 这个场景是最简单的，也是⼤家最熟悉的，即搜索⼀个数，如果存在，返回其索引，否则返回 -1。
     *
     * int binarySearch(int[] nums, int target) {
     *     int left = 0;
     *     int right = nums.length - 1; // 注意
     *
     *     while(left <= right) {
     *         int mid = left + (right - left) / 2;
     *         if(nums[mid] == target)
     *             return mid;
     *         else if (nums[mid] < target)
     *             left = mid + 1; // 注意
     *         else if (nums[mid] > target)
     *             right = mid - 1; // 注意
     *     }
     *     return -1;
     * }
     *
     * 1、为什么 while 循环的条件中是 <=，⽽不是 < ？
     *
     * 答：因为初始化 right 的赋值是 nums.length - 1，即最后⼀个元素的索引，⽽不是 nums.length。
     *
     * 这⼆者可能出现在不同功能的⼆分查找中，区别是：前者相当于两端都闭区间 [left, right] ，后者相
     * 当于左闭右开区间 [left, right)，因为索引⼤⼩为 nums.length 是越界的。
     *
     * 这里的算法中使⽤的是前者 [left, right] 两端都闭的区间。这个区间其实就是每次进⾏搜索的区间。
     *
     * 什么时候应该停⽌搜索呢？当然，找到了⽬标值的时候可以终⽌：
     *
     * if(nums[mid] == target)
     *     return mid;
     *
     * 但如果没找到，就需要 while 循环终⽌，然后返回 -1。那 while 循环什么时候应该终⽌？搜索区间为
     * 空的时候应该终⽌，意味着你没得找了，就等于没找到嘛。
     *
     * while(left <= right) 的终⽌条件是 left == right + 1，写成区间的形式 就是 [right + 1,
     * right]，或者带个具体的数字进去 [3, 2]，可⻅这时候区间为空，因为没有数字既⼤于等于 3 ⼜⼩于
     * 等于 2 的吧。所以这时候 while 循环终⽌是正确的，直接返回 -1 即可。
     *
     * while(left < right) 的终⽌条件是 left == right，写成区间的形式就是 [left, right]，或者
     * 带个具体的数字进去 [2, 2]，这时候区间⾮空，还有⼀个数 2，但此时 while 循环终⽌了。也就是说这
     * 区间 [2, 2] 被漏掉了，索引 2 没有被搜索，如果这时候直接返回 -1 就是错误的。
     *
     * 当然，如果你⾮要⽤ while(left < right) 也可以，既然已经知道了出错的原因，就打个补丁好了：
     *
     * //...
     * while(left < right) {
     *     // ...
     * }
     * return nums[left] == target ? left : -1;
     *
     *
     * 2、为什么 left = mid + 1，right = mid - 1 ？而有的代码是 right = mid 或者 left = mid，
     * 没有这些加加减减，到底怎么回事，怎么判断？
     *
     * 答：这也是⼆分查找的⼀个难点，不过只要你能理解前⾯的内容，就能够很容易判断。
     *
     * 刚才明确了「搜索区间」这个概念，⽽且本算法的搜索区间是两端都闭的，即 [left, right]。那么当发
     * 现索引 mid 不是要找的 target 时，下⼀步应该去搜索哪⾥呢？
     *
     * 当然是去搜索 [left, mid-1] 或者 [mid+1, right]，对不对？因为 mid 已经搜索过，应该从搜索
     * 区间中去除。
     *
     *
     * 3、此算法有什么缺陷？
     *
     * 答：⾄此，你应该已经掌握了该算法的所有细节，以及这样处理的原因。但是，这个算法存在局限性。
     *
     * ⽐如说给你有序数组 nums = [1,2,2,2,3]， target 为 2，此算法返回的索引是 2，没错。但是如果
     * 想得到 target 的左侧边界，即索引 1，或者想得到 target 的右侧边界，即索引 3，这样的话此算法
     * 是⽆法处理的。
     *
     * 这样的需求很常⻅，你也许会说，找到⼀个 target，然后向左或向右线性搜索不⾏吗？可以，但是不好，
     * 因为这样难以保证⼆分查找对数级的复杂度了。
     *
     * 后续的算法就来讨论这两种⼆分查找的算法。
     */
    public static void main(String[] args) {

    }

}
