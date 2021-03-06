package com.siwuxie095.algorithm.chapter0th.example9th;

/**
 * @author Jiajing Li
 * @date 2021-08-17 22:38:06
 */
public class Main {

    /**
     * 三、总结
     *
     * 第⼀个斐波那契数列的问题，解释了如何通过「备忘录」或者「dp table」的⽅法来优化递归树，并且明确了
     * 这两种⽅法本质上是⼀样的，只是⾃顶向下和⾃底向上的不同⽽已。
     *
     * 第⼆个凑零钱的问题，展⽰了如何流程化确定「状态转移⽅程」，只要通过状态转移⽅程写出暴⼒递归解，剩下
     * 的也就是优化递归树，消除重叠⼦问题⽽已。
     *
     * 计算机解决问题其实没有任何奇技淫巧，它唯⼀的解决办法就是穷举，穷举所有可能性。算法设计⽆⾮就是先
     * 思考 "如何穷举"，然后再追求 "如何聪明地穷举"。
     *
     * 列出动态转移⽅程，就是在解决 "如何穷举" 的问题。之所以说它难，⼀是因为很多穷举需要递归实现，⼆是
     * 因为有的问题本⾝的解空间复杂，不那么容易穷举完整。
     *
     * 备忘录、DP table 就是在追求 "如何聪明地穷举"。⽤空间换时间的思路，是降低时间复杂度的不⼆法门，
     * 除此之外，试问，还能玩出啥花活？
     */
    public static void main(String[] args) {

    }

}
