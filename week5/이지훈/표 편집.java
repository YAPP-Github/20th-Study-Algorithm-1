import java.util.Stack;

public class 표편집 {
    private static Node now;
    private static Stack<Node> deletedNodes = new Stack<>();

    private static class Node{
        Node prev = null;
        Node next = null;
        boolean isDeleted;
    }

    public String solution(int n, int k, String[] cmds) {
        Node[] nodes = new Node[1000001];

        nodes[0] = new Node();
        for (int i = 1; i < n; i++) {
            nodes[i] = new Node();
            nodes[i].prev = nodes[i - 1];
            nodes[i-1].next = nodes[i];
        }

        now = nodes[k];

        for (String cmd : cmds){
            char c = cmd.charAt(0);

            switch (c) {
                case 'U' : moveUp(cmd);   break;
                case 'D' : moveDown(cmd); break;
                case 'C' : delete();      break;
                case 'Z' : restore();     break;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if(nodes[i].isDeleted) sb.append("X");
            else sb.append("O");
        }

        return sb.toString();
    }

    private static void moveUp(String cmd){
        int moveCnt = Integer.parseInt(cmd.substring(2));
        for (int i = 0; i < moveCnt; i++) {
            now = now.prev;
        }
    }

    private static void moveDown(String cmd){
        int moveCnt = Integer.parseInt(cmd.substring(2));
        for (int i = 0; i < moveCnt; i++) {
            now = now.next;
        }
    }

    private static void delete(){
        now.isDeleted = true;
        deletedNodes.add(now);

        if (now.prev != null) now.prev.next = now.next;

        if (now.next != null) {
            now.next.prev = now.prev;
            now = now.next;
        }
        else now = now.prev;
    }

    private static void restore(){
        Node restoreNode = deletedNodes.pop();
        restoreNode.isDeleted = false;

        if(restoreNode.prev != null) restoreNode.prev.next = restoreNode;
        if(restoreNode.next != null) restoreNode.next.prev = restoreNode;
    }

}
