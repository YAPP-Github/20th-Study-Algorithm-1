import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        return Arrays.stream(numbers)
                .mapToObj(String::valueOf)
                .sorted((p1, p2) -> (p2 + p2 + p2).compareTo(p1 + p1 + p1))
                .reduce((x, y) -> x + y)
                .map(x -> x.charAt(0) == '0'? "0" : x)
                .get();
    }
}