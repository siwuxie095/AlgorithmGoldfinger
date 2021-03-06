package com.siwuxie095.algorithm.chapter0th.example6th;

/**
 * @author Jiajing Li
 * @date 2021-08-17 08:16:06
 */
public class Main {

    /**
     * 动态规划解题套路框架
     *
     * 动态规划问题的⼀般形式就是求最值。动态规划其实是运筹学的⼀种最优化⽅法，只不过在计算机问题上
     * 应⽤⽐较多，⽐如说让你求最⻓递增⼦序列、最⼩编辑距离等等。
     *
     * 既然是要求最值，核⼼问题是什么呢？求解动态规划的核⼼问题是穷举。因为要求最值，肯定要把所有可
     * ⾏的答案穷举出来，然后在其中找最值。
     *
     * 动态规划就这么简单，但就是穷举就完事了吗？很多人看到的动态规划问题都觉得很难。
     *
     * ⾸先，动态规划的穷举有点特别，因为这类问题存在「重叠⼦问题」，如果暴⼒穷举的话效率会极其低下，
     * 所以需要「备忘录」或者「DP table」来优化穷举过程，避免不必要的计算。
     *
     * ⽽且，动态规划问题⼀定会具备「最优⼦结构」，才能通过⼦问题的最值得到原问题的最值。
     *
     * 另外，虽然动态规划的核⼼思想就是穷举求最值，但是问题可以千变万化，穷举所有可⾏解其实并不是⼀
     * 件容易的事，只有列出正确的「状态转移⽅程」才能正确地穷举。
     *
     * 以上提到的重叠⼦问题、最优⼦结构、状态转移⽅程就是动态规划三要素。具体什么意思后续会举例详解，
     * 但是在实际的算法问题中，写出状态转移⽅程是最困难的，这也就是为什么很多朋友觉得动态规划问题困
     * 难的原因，下面来提供⼀个思维框架，辅助你思考状态转移⽅程：
     *
     * 明确「状态」 -> 定义 dp 数组/函数的含义 -> 明确「选择」-> 明确 base case。
     *
     * 说白了就是三点：状态，选择，dp 数组的定义。最后的代码就可以套这个框架：
     *
     * # 初始化 base case
     * dp[0][0][...] = base case
     * # 进行状态转移
     * for 状态1 in 状态1的所有取值：
     *     for 状态2 in 状态2的所有取值：
     *         for ...
     *             dp[状态1][状态2][...] = 求最值(选择1，选择2...)
     *
     * 后续将通过斐波那契数列问题和凑零钱问题来详解动态规划的基本原理。前者主要是让你明⽩什么是重叠
     * ⼦问题（斐波那契数列严格来说不是动态规划问题），后者主要集中于如何列出状态转移⽅程。
     *
     * 不要嫌弃这两个例⼦简单，只有简单的例⼦才能让你把精⼒充分集中在算法背后的通⽤思想和技巧上，⽽
     * 不会被那些隐晦的细节问题搞的莫名其妙。
     */
    public static void main(String[] args) {

    }

}
