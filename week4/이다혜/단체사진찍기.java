import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

    public class 단체사진찍기 {

        public static HashMap<String, ArrayList<String> > groups;
        static char [] members = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
        public static HashSet<Character> isVisited;
        static int answer;
        static Deque<Character> line;

        public static int solution(int n, String[] data) {
            answer = 0;
            groups = new HashMap<>();
            isVisited = new HashSet<>();
            
            for(String order : data) {
                char[] condition = order.toCharArray();
                char[] participants = new char[2];
                participants[0] = condition[0];
                participants[1] = condition[2];
                Arrays.sort(participants);
                String parties = new String(participants);
                char[] distLimit = new char[2];
                distLimit[0] = condition[3];
                distLimit[1] = condition[4];
                String dist = new String(distLimit);
                // System.out.println(dist);
                
                if(!groups.containsKey(parties)) groups.put(parties, new ArrayList<>());
                groups.get(parties).add(dist);
            }

            for(int i = 0; i < 8; i++) {
                line = new LinkedList<>();
                isVisited.add(members[i]);
                dfsSearch(members[i]);
                isVisited.remove(members[i]);
            }
            

            return answer;
        }

        public static void dfsSearch(Character member) {
            line.add(member);

            if(line.size() >= 8) {
                if(checkCondition()) answer++;
                return ;
            }

            for(int i = 0; i < 8; i++) {
                if(!isVisited.contains(members[i])) {
                    isVisited.add(members[i]);
                    dfsSearch(members[i]);
                    isVisited.remove(members[i]);
                    line.removeLast();
                }
            }
        }

        public static boolean checkCondition() {
            String arr = "";
            for(char m : line) arr += m;
            
            for(String party : groups.keySet()) {
                char first = party.charAt(0);
                char second = party.charAt(1);
                int firstIdx = arr.indexOf(first);
                int secondIdx = arr.indexOf(second);
                
                int dist = Math.abs(firstIdx - secondIdx) - 1;
                ArrayList<String> conds = groups.get(party);
                for(String c : conds) {
                    int di = c.charAt(1)-'0';
                    switch(c.charAt(0)) {
                        case '=':
                        if(dist != di) return false;
                        break;

                        case '>':
                        if(dist <= di) return false;
                        break;

                        case '<':
                        if(dist >= di) return false;
                        break;
                    }
                }
                
            }
            return true;
        }
    }