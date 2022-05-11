class TakeGroupPhoto {
    private int count = 0;
    private String[] data;
    private char[] friendsArr;
    private boolean[] isVisited;

    public int solution(int n, String[] data) {
        this.data = data;
        this.friendsArr = new char[]{'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
        isVisited = new boolean[friendsArr.length];
        dfs(0, "");
        return count;
    }

    private void dfs(int depth, String order) {
        if (depth == friendsArr.length - 1) {
            if (isPossible(order)) this.count++;
            return;
        }

        for (int i = 0; i < friendsArr.length; i++) {
            if (isVisited[i]) continue;
            isVisited[i] = true;
            dfs(depth + 1, order + friendsArr[i]);
            isVisited[i] = false;
        }
    }

    private boolean isPossible(String order) {
        boolean isPossible = true;
        for (String rule : data) {
            int friends1Index = order.indexOf(String.valueOf(rule.charAt(0)));
            int friends2Index = order.indexOf(String.valueOf(rule.charAt(2)));
            char operator = rule.charAt(3);
            int distance = Integer.parseInt(String.valueOf(rule.charAt(4)));
            int friends1and2Distance = Math.abs(friends1Index - friends2Index) - 1;

            if (operator == '<' && friends1and2Distance >= distance) {
                isPossible = false;
                break;
            } else if (operator == '>' && friends1and2Distance <= distance) {
                isPossible = false;
                break;
            } else if (operator == '=' && friends1and2Distance != distance) {
                isPossible = false;
                break;
            }
        }
        return isPossible;
    }
}