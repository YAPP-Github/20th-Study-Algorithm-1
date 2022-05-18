import java.util.Stack;

public class 표편집_시간초과 {
    private static int nowRow = 0;
    private static Stack<Integer> deletedRows = new Stack<>();
    private static boolean[] table;

    public String solution(int n, int k, String[] cmds) {
        table = new boolean[n];
        nowRow = k;

        for (String cmd : cmds){
            char c = cmd.charAt(0);

            switch (c) {
                case 'U' : moveUp(cmd);   break;
                case 'D' : moveDown(cmd); break;
                case 'C' : delete();   break;
                case 'Z' : restore();  break;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (boolean isDeleted : table) {
            if(isDeleted) sb.append("X");
            else sb.append("O");
        }

        return sb.toString();
    }

    private static void moveUp(String cmd){
        int moveCnt = Integer.parseInt(cmd.substring(2));

        while (moveCnt != 0) {
            nowRow--;
            if(!table[nowRow]) moveCnt--;
        }
    }

    private static void moveDown(String cmd){
        int moveCnt = Integer.parseInt(cmd.substring(2));

        while (moveCnt != 0) {
            nowRow++;
            if(!table[nowRow]) moveCnt--;
        }
    }

    private static void delete(){
        table[nowRow] = true;
        deletedRows.add(nowRow);

        if(nowRow == table.length - 1) {
            while (true){
                nowRow--;

                if(!table[nowRow]) break;
            }
        }
        else {
            while (true) {
                nowRow++;

                if(!table[nowRow]) break;
            }
        }
    }

    private static void restore(){
        table[deletedRows.pop()] = false;
    }

}
