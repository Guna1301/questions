import java.util.*;
class august31 {
    public long maxProduct(int[] nums) {
        Map<Integer, Integer> best = new HashMap<>();

        for (int num : nums) {
            best.put(num, Math.max(best.getOrDefault(num, 0), num));
        }

        long ans = 0;
        List<Integer> keys = new ArrayList<>(best.keySet());

        for (int i = 0; i < keys.size(); i++) {
            for (int j = i + 1; j < keys.size(); j++) {
                if ((keys.get(i) & keys.get(j)) == 0) {
                    long val = 1L * best.get(keys.get(i)) * best.get(keys.get(j));
                    ans = Math.max(ans, val);
                }
            }
        }
        return ans;
    }
}
