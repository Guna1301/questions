import java.util.*;

public class leetcode {
    class SegmentTree {
        int n;
        long[][] tree;
        long[] arr;
        final int max = 6;
        Map<Long, Integer> memo = new HashMap<>();

        SegmentTree(long[] nums) {
            this.n = nums.length;
            this.arr = nums;
            tree = new long[4 * n][max + 1];
            build(0, 0, n - 1);
        }
        int getdepth(long x) {
            if (x == 1) return 0;
            if (memo.containsKey(x)) return memo.get(x);
            int depth = 1 + getdepth(Long.bitCount(x));
            memo.put(x, depth);
            return depth;
        }

        void build(int node, int start, int end) {
            if (start == end) {
                int depth = getdepth(arr[start]);
                tree[node][depth]++;
                return;
            }
            int mid = (start + end) / 2;
            build(2 * node + 1, start, mid);
            build(2 * node + 2, mid + 1, end);
            for (int i = 0; i <= max; i++) {
                tree[node][i] = tree[2 * node + 1][i] + tree[2 * node + 2][i];
            }
        }

        void update(int node, int start, int end, int idx, int val) {
            if (start == end) {
                int oldDepth = getdepth(arr[idx]);
                int newDepth = getdepth(val);
                tree[node][oldDepth]--;
                tree[node][newDepth]++;
                arr[idx] = val;
                return;
            }
            int mid = (start + end) / 2;
            if (idx <= mid)
                update(2 * node + 1, start, mid, idx, val);
            else
                update(2 * node + 2, mid + 1, end, idx, val);

            for (int i = 0; i <= max; i++) {
                tree[node][i] = tree[2 * node + 1][i] + tree[2 * node + 2][i];
            }
        }

        long query(int node, int start, int end, int l, int r, int k) {
            if (r < start || end < l) return 0;
            if (l <= start && end <= r) return tree[node][k];
            int mid = (start + end) / 2;
            long left = query(2 * node + 1, start, mid, l, r, k);
            long right = query(2 * node + 2, mid + 1, end, l, r, k);
            return left + right;
        }
    }

    public int[] popcountDepth(long[] nums, long[][] queries) {
        SegmentTree st = new SegmentTree(nums);
        List<Integer> res = new ArrayList<>();

        for (long[] q : queries) {
            if (q[0] == 1) {
                int l = (int) q[1], r = (int) q[2], k = (int) q[3];
                res.add((int) st.query(0, 0, nums.length - 1, l, r, k));
            } else if (q[0] == 2) {
                int idx = (int) q[1], val = (int) q[2];
                st.update(0, 0, nums.length - 1, idx, val);
            }
        }

        int[] ans = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }


}
