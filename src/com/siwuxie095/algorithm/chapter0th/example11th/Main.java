package com.siwuxie095.algorithm.chapter0th.example11th;

/**
 * @author Jiajing Li
 * @date 2021-08-18 21:38:27
 */
public class Main {

    /**
     * ⼀、全排列问题
     *
     * 大家在⾼中的时候就做过排列组合的数学题，也知道 n 个不重复的数，全排列共有 n! 个。
     *
     * PS：为了简单清晰起⻅，这里讨论的全排列问题不包含重复的数字。
     *
     * 那么当时是怎么穷举全排列的呢？⽐⽅说给三个数 [1,2,3]，你肯定不会⽆规律地乱穷举，
     * ⼀般是这样：
     *
     * 先固定第⼀位为 1，然后第⼆位可以是 2，那么第三位只能是 3；然后可以把第⼆位变成
     * 3，第三位就只能是 2 了；然后就只能变化第⼀位，变成 2，然后再穷举后两位……
     *
     * 其实这就是回溯算法，大家在⾼中⽆师⾃通就会⽤，或者可以直接画出一棵回溯树。
     *
     * 只要从根遍历这棵树，记录路径上的数字，其实就是所有的全排列。不妨把这棵树称为回溯
     * 算法的「决策树」。
     *
     * 为啥说这是决策树呢，因为你在每个节点上其实都在做决策。
     *
     * ⽐如你现在是以 2 打头，已经选择了 2：那么你现在就在做决策，可以选择 1 那条树枝，
     * 也可以选择 3 那条树枝。为啥只能在 1 和 3 之中选择呢？因为 2 这个树枝在你⾝后，
     * 这个选择你之前做过了，⽽全排列是不允许重复使⽤数字的。
     *
     * 现在可以解答之前提到的⼏个名词：
     * [2] 就是「路径」，记录你已经做过的选择； [1,3] 就是「选择列表」，表⽰你当前可
     * 以做出的选择；「结束条件」就是遍历到树的底层，在这⾥就是选择列表为空的时候。
     *
     * 如果明⽩了这⼏个名词，可以把「路径」和「选择」列表作为决策树上每个节点的属性。
     *
     * 这里定义的 backtrack 函数其实就像⼀个指针，在这棵树上游⾛，同时要正确维护每个
     * 节点的属性，每当⾛到树的底层，其「路径」就是⼀个全排列。
     *
     * 再进⼀步，如何遍历⼀棵树？这个应该不难吧。各种搜索问题其实都是树的遍历问题，⽽
     * 多叉树的遍历框架就是这样：
     *
     * void traverse(TreeNode root) {
     *     for (TreeNode child : root.childern)
     *         // 前序遍历需要的操作
     *         traverse(child);
     *         // 后序遍历需要的操作
     * }
     *
     * ⽽所谓的前序遍历和后序遍历，它们只是两个很有⽤的时间点。前序遍历的代码在进⼊某⼀
     * 个节点之前的那个时间点执⾏，后序遍历代码在离开某个节点之后的那个时间点执⾏。
     *
     * 回想刚才说的，「路径」和「选择」是每个节点的属性，函数在树上游⾛要正确维护节点的
     * 属性，那么就要在这两个特殊时间点搞点动作：
     *
     * 现在，你是否理解了回溯算法的这段核⼼框架？
     *
     * for 选择 in 选择列表:
     *     # 做选择
     *     将该选择从选择列表移除
     *     路径.add(选择)
     *     backtrack(路径, 选择列表) # 撤销选择
     *     路径.remove(选择)
     *     将该选择再加⼊选择列表
     *
     * 只要在递归之前做出选择，在递归之后撤销刚才的选择，就能正确得到每个节点的选择列表
     * 和路径。
     *
     * 下⾯，直接看全排列代码：
     *
     * List<List<Integer>> res = new LinkedList<>();
     *
     * // 主函数，输入一组不重复的数字，返回它们的全排列
     * List<List<Integer>> permute(int[] nums) {
     *     // 记录「路径」
     *     LinkedList<Integer> track = new LinkedList<>();
     *     backtrack(nums, track);
     *     return res;
     * }
     *
     * // 路径：记录在 track 中
     * // 选择列表：nums 中不存在于 track 的那些元素
     * // 结束条件：nums 中的元素全都在 track 中出现
     * void backtrack(int[] nums, LinkedList<Integer> track) {
     *     // 触发结束条件
     *     if (track.size() == nums.length) {
     *         res.add(new LinkedList(track));
     *         return;
     *     }
     *
     *     for (int i = 0; i < nums.length; i++) {
     *         // 排除不合法的选择
     *         if (track.contains(nums[i]))
     *             continue;
     *         // 做选择
     *         track.add(nums[i]);
     *         // 进入下一层决策树
     *         backtrack(nums, track);
     *         // 取消选择
     *         track.removeLast();
     *     }
     * }
     *
     * 这⾥稍微做了些变通，没有显式记录「选择列表」，⽽是通过 nums 和 track 推导出当前
     * 的选择列表。
     *
     * ⾄此，就通过全排列问题详解了回溯算法的底层原理。当然，这个算法解决全排列不是很⾼效，
     * 因为对链表使⽤ contains ⽅法需要 O(N) 的时间复杂度。有更好的⽅法通过交换元素达到
     * ⽬的，但是难理解⼀些，这⾥就不写了，有兴趣可以⾃⾏搜索⼀下。
     *
     * 但是必须说明的是，不管怎么优化，都符合回溯框架，⽽且时间复杂度都不可能低于 O(N!)，
     * 因为穷举整棵决策树是⽆法避免的。这也是回溯算法的⼀个特点，不像动态规划存在重叠⼦问
     * 题可以优化，回溯算法就是纯暴⼒穷举，复杂度⼀般都很⾼。
     *
     * 明⽩了全排列问题，就可以直接套回溯算法框架了，后续将会看看 N 皇后问题。
     */
    public static void main(String[] args) {

    }

}
