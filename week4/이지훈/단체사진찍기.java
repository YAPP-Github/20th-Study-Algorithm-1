public class 단체사진찍기 {

    private static final char[] characters = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    private static char[] positions;
    private static boolean[] checks;
    private static int result = 0;

    public int solution(int n, String[] data) {
        positions = new char[8];
        checks = new boolean[8];

        makeUpMembers(0, data);

        return result;
    }

    private void makeUpMembers(int depth, String[] data){
        if(depth == 8){
            if(isTakePictures(data)) result++;

            return;
        }

        for(int i = 0 ; i < 8 ; ++i){
            if(checks[i]) continue;

            checks[i] = true;
            positions[depth] = characters[i];
            makeUpMembers(depth + 1, data);
            checks[i] = false;
        }
    }

    private boolean isTakePictures(String[] data){
        for(int i = 0 ; i < data.length ; ++i){
            char[] conditions = data[i].toCharArray();
            int from = 0;
            int to = 0;

            for(int j = 0 ; j < 8 ; ++j){
                if(positions[j] == conditions[0]) from = j;
                if(positions[j] == conditions[2]) to = j;
            }

            int gap = Math.abs(from - to) - 1;
            char operator = conditions[3];
            int cond = conditions[4] - '0';

            switch(operator){
                case '=':
                    if(gap != cond) return false;
                    break;
                case '<':
                    if(gap >= cond) return false;
                    break;
                case '>':
                    if(gap <= cond) return false;
                    break;
            }
        }

        return true;
    }

}