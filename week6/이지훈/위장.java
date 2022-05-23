import java.util.*;

public class 위장 {

    public int solution(String[][] clothes) {
        Map<String, Integer> typeToCount = new HashMap<>();
        int result = 1;

        for (int i = 0; i < clothes.length; i++) {
            String type = clothes[i][1];
            typeToCount.put(type, typeToCount.getOrDefault(type, 0) + 1);
        }

        for (String key : typeToCount.keySet()) {
            result *= (typeToCount.get(key) + 1); // 각 의상별로 안입는다는 선택지가 존재하므로 +1
        }

        return result - 1; // 아무것도 안입는 경우 제외
    }

}