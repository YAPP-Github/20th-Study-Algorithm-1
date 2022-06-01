public class 가장 긴 팰린드롬 {

    private static char[] chars;

    public int solution(String s){

        chars = s.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            int checkLen = chars.length - i;
            int startIndex = 0;
            int endIndex = startIndex + checkLen - 1;

            while (true) {
                if (endIndex >= chars.length) break;

                if (isPalindrome(checkLen, startIndex, endIndex)) {
                    return checkLen;
                }

                startIndex++;
                endIndex++;
            }
        }

        return -1;
    }

    private static boolean isPalindrome(int len, int startIndex, int endIndex) {
        boolean check = true;

        for (int i = 0; i < len / 2; i++) {
            if (chars[startIndex++] != chars[endIndex--]) {
                check = false;
                break;
            }
        }

        return check;
    }

}