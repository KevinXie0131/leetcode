package com.learn;

public class TestRecursion_1 {
    /**
     * Divide and conquer (D&C):
     * A divide-and-conquer algorithm works by recursively breaking the problem down into two or more subproblems of
     * the same or related type, until these subproblems become simple enough to be solved directly.
     * Then one combines the results of subproblems to form the final solution.
     *
     * 1. Divide.
     * 2. Conquer.
     * 3. Combine.
     *
     * Application:
     * Merge Sort / Quick sort
     *
     */
    /**
     * D&C Template:
     *
     *  # (1). Divide the problem into a set of subproblems.
     *  [S1, S2, ... Sn] = divide(S)
     *
     *  # (2). Solve the subproblem recursively,
     *  #   obtain the results of subproblems as [R1, R2... Rn].
     *  rets = [divide_and_conquer(Si) for Si in [S1, S2, ... Sn]]
     *  [R1, R2,... Rn] = rets
     *
     *  # (3). combine the results from the subproblems.
     *  #   and return the combined result.
     *  return combine([R1, R2,... Rn])
     */
    /**
     * Validate Binary Search Tree
     *
     * 1. In the first step, we divide the tree into two subtrees -- its left child and right child.  (Divide)
     * 2. Then in the next step, we recursively validate each subtree is indeed a binary search tree.  (Conquer)
     * 3. Upon the results of the subproblems from Step 2, we return true if and only if both subtrees are both valid BST.  (Combine)
     */
    /**
     * Search a 2D Matrix II
     *
     * 1. We divide the matrix into 4 sub-matrices by choosing a pivot point based on a row and a column.  (Divide)
     * 2. Then we recursively look into each sub-matrix to search for the desired target.  (Conquer)
     * 3. If we find the target in either of the sub-matrices, we stop the search and return the result immediately.  (Combine)
     */

    /**
     * Backtracking
     *  Once we can determine if a certain node cannot possibly lead to a valid solution, we abandon the current node and
     *  backtrack to its parent node to explore other possibilities.
     *
     *  Backtracking reduced the number of steps taken to reach the final result.
     *  This is known as pruning the recursion tree because we don't take unnecessary paths.
     */
    /**
     * Backtracking Template:
     *
     * def backtrack(candidate):
     *     if find_solution(candidate):
     *         output(candidate)
     *         return
     *
     *     # iterate all possible candidates.
     *     for next_candidate in list_of_candidates:
     *         if is_valid(next_candidate):
     *             # try this partial candidate solution
     *             place(next_candidate)
     *             # given the candidate, explore further.
     *             backtrack(next_candidate)
     *             # backtrack
     *             remove(next_candidate)
     */
}
