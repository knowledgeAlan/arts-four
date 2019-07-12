package com.zzm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * <p>
 * candidates 中的数字可以无限制重复被选取。
 * <p>
 * 说明：
 * <p>
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1:
 * <p>
 * 输入: candidates = [2,3,6,7], target = 7,
 * 所求解集为:
 * [
 * [7],
 * [2,2,3]
 * ]
 * 示例 2:
 * <p>
 * 输入: candidates = [2,3,5], target = 8,
 * 所求解集为:
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {

    private static List<List<Integer>> res;



    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        res = new ArrayList<>();

        List<Integer> list = new ArrayList<>();

        Arrays.sort(candidates);
        helper(candidates, list, 0, target);
        return res;
    }

    public static void helper(int[] candidates, List<Integer> list, int start, int target) {

        if (target == 0) {
            res.add(new ArrayList<>(list));
            return;
        }

        for (int i = start; i < candidates.length && target >= candidates[i]; i++) {
            list.add(candidates[i]);
            helper(candidates, list, i, target - candidates[i]);
            list.remove(list.size() - 1);
        }
    }
}
