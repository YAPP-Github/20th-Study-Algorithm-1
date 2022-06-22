package 박현국;

import java.util.*;

class OpenChat {
    public String[] solution(String[] record) {
        List<Log> answer = new ArrayList<>();
        Map<String, String> nickname = new HashMap<>();

        for (String r : record) {
            String[] split = r.split(" ");
            if ("Enter".equals(split[0])) {
                nickname.put(split[1], split[2]);
                answer.add(new Log(split[1], "님이 들어왔습니다."));
            } else if ("Leave".equals(split[0])) {
                answer.add(new Log(split[1], "님이 나갔습니다."));
            } else {
                nickname.put(split[1], split[2]);
            }
        }

        return answer.stream()
                .map(x -> x.toMessage(nickname.get(x.uid)))
                .toArray(String[]::new);
    }

    static class Log {
        String uid;
        String message;

        Log(String uid, String message) {
            this.uid = uid;
            this.message = message;
        }

        String toMessage(String nickname) {
            return nickname + this.message;
        }
    }
}