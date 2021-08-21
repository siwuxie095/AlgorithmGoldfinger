package com.siwuxie095.algorithm.chapter0th.example20th;

/**
 * @author Jiajing Li
 * @date 2021-08-21 14:16:19
 */
public class Main {

    /**
     * 一、⼆分查找框架
     *
     * int binarySearch(int[] nums, int target) {
     *     int left = 0, right = ...;
     *     while(...) {
     *         int mid = left + (right - left) / 2;
     *         if (nums[mid] == target) {
     *             ...
     *         } else if (nums[mid] < target) {
     *             left = ...
     *         } else if (nums[mid] > target) {
     *             right = ...
     *         }
     *     }
     *     return ...;
     * }
     *
     * 分析⼆分查找的⼀个技巧是：不要出现 else，⽽是把所有情况⽤ else if 写清楚，这样可以清楚地
     * 展现所有细节。这里都会使⽤ else if，旨在讲清楚，理解后可⾃⾏简化。
     *
     * 其中 ... 标记的部分，就是可能出现细节问题的地⽅，当你⻅到⼀个⼆分查找的代码时，⾸先注意这
     * ⼏个地⽅。后续会⽤实例分析这些地⽅能有什么样的变化。
     *
     * 另外声明⼀下，计算 mid 时需要防⽌溢出，代码中 left + (right - left) / 2 就和 (left
     * + right) / 2 的结果相同，但是有效防⽌了 left 和 right 太⼤，直接相加导致溢出。
     */
    public static void main(String[] args) {

    }

}
