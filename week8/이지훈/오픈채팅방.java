import java.util.*;

public class 오픈채팅방 {
    public List<String> solution(String[] records) {
        List<String> result = new ArrayList<>();
        List<String> chatLogs = new ArrayList<>();
        Map<String, String> uidToName = new HashMap<>();

        for (String record : records) {
            StringTokenizer st = new StringTokenizer(record);
            String command = st.nextToken();
            String uid = st.nextToken();
            String name = "";

            if (!command.equals("Leave")) name = st.nextToken();

            switch(command) {
                case "Enter":
                    chatLogs.add(uid + "@님이 들어왔습니다.");
                    uidToName.put(uid, name);
                    break;
                case "Leave":
                    chatLogs.add(uid + "@님이 나갔습니다.");
                    break;
                case "Change":
                    uidToName.put(uid, name);
                    break;
            }
        }

        for (String log : chatLogs) {
            StringTokenizer st = new StringTokenizer(log, "@");
            String uid = st.nextToken();
            String name = uidToName.get(uid);

            result.add(log.replace(uid+"@", name));
        }

        return result;
    }

}