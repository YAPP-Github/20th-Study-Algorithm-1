package 박현국;

import java.util.*;
import java.util.stream.Collectors;

public class EditTable {
    public String solution(int n, int k, String[] cmd) {
        int tableSize = n;
        int cursor = k;
        Stack<Integer> deletedCursor = new Stack<>();

        for (String command : cmd) {
            String[] split = command.split(" ");
            if ("U".equals(split[0])) {
                cursor -= Integer.parseInt(split[1]);
            } else if ("D".equals(split[0])) {
                cursor += Integer.parseInt(split[1]);
            } else if ("C".equals(split[0])) {
                deletedCursor.push(cursor);
                tableSize--;
                cursor = Math.min(tableSize - 1, cursor);
            } else if ("Z".equals(split[0])) {
                tableSize++;

                if (deletedCursor.pop() <= cursor) {
                    cursor++;
                }
            }
        }

        StringBuilder builder = new StringBuilder();
        builder.append("O".repeat(Math.max(0, tableSize)));

        while(! deletedCursor.empty()) {
            builder.insert(deletedCursor.pop(), "X");
        }
        return builder.toString();
    }

    public String solution_timed_out(int n, int k, String[] cmd) {
        List<Character> tableList = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            tableList.add('O');
        }
        int cursor = k;
        Stack<Integer> deletedCursor = new Stack<>();

        for (String command : cmd) {
            String[] split = command.split(" ");
            if ("U".equals(split[0])) {
                cursor -= Integer.parseInt(split[1]);
            } else if ("D".equals(split[0])) {
                cursor += Integer.parseInt(split[1]);
            } else if ("C".equals(split[0])) {
                deletedCursor.push(cursor);
                tableList.remove(cursor);
                cursor = Math.max(tableList.size()-1, cursor);
            } else if ("Z".equals(split[0])) {
                int idx = deletedCursor.pop();
                tableList.add(idx, 'O');

                if (idx <= cursor) {
                    cursor++;
                }
            }
        }

        while (! deletedCursor.empty()) {
            int idx = deletedCursor.pop();
            tableList.add(idx, 'X');
        }

        return tableList.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());
    }
}
