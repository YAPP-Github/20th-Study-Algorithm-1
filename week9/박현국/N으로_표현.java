import java.util.*;

class Solution {
    public int solution(int N, int number) {
        if (number == N) {
            return 1;
        }

        List<Set<Integer>> candidates = new ArrayList<>(){{
            add(new HashSet<>());
            add(new HashSet<>(Arrays.asList(N)));
        }};

        for (int i = 2; i <= 8; i++) {
            int temp = Integer.parseInt(String.valueOf(N).repeat(i));
            Set<Integer> caseSet = new HashSet<>(Arrays.asList(temp));
            for (int i_half = 1; i_half <= i / 2; i_half++) {
                for (int x : candidates.get(i_half)) {
                    for (int y : candidates.get(i - i_half)) {
                        caseSet.add(x+y);
                        caseSet.add(x-y);
                        caseSet.add(y-x);
                        caseSet.add(x*y);

                        if (x != 0) {
                            caseSet.add(y / x);
                        }

                        if (y != 0) {
                            caseSet.add(x / y);
                        }
                    }
                }
            }

            if (caseSet.contains(number)) {
                return i;
            }

            candidates.add(caseSet);
        }

        return -1;
    }
}