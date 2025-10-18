package calculator;

// camp.nextstep.edu.missionutils에서 제공하는 Console API를 사용하여 구현
// Matcher, Pattern 내부 메서드 사용

import camp.nextstep.edu.missionutils.Console;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현

        /* [기능목록 및 commit 순서]
         * 1."덧셈할 문자열을 입력해 주세요." 문구 출력 -> Console.readLine()으로 사용자 입력받기. 결과를 형식에 맞게 출력하기(일단 0)
         * 2. 입력된 문자열이 비어있거나, null일 경우 -> 0을 반환하는 기능.
         * 3. 쉼표(,) 또는 콜론(:)을 기준으로 문자열을 분리하는 기능, 분리된 각 숫자의 합을 계산하여 반환하는 기능. (예: "1,2:3" -> 6)
         * 4. 문자열이 //와 \n으로 시작하는지 판별하는 기능. //와 \n 사이의 문자를 커스텀 구분자로 인식하여 문자열을 분리하고 합을 계산하는 기능. (예: "//;\n1;2;3" -> 6)
         * 5. 예외처리: 분리된 숫자를 확인하여 음수가 포함되어 있을 경우 IllegalArgumentException을 발생시키는 기능.
         * 6. 예외처리: 잘못된 값의 확장 (예: "1,a,2", "//;\n1;abc;3")
         * 7. 클린코드 리팩토링 -> 단일책임 원칙 유사 구현
         */

        // Console API를 사용하여 입출력 구현
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String input = Console.readLine();

        int result = calculate(input);
        System.out.println("결과 : " + result);
    }

    // 계산의 전체 흐름을 관리한다.
    public static int calculate(String text) {
        if (text == null || text.isEmpty()) {
            return 0;
        }

        String[] numberTokens = splitText(text); // 문자열을 분리하는 책임
        return sumTokens(numberTokens);      // 숫자들을 더하는 책임
    }

    // 입력된 문자열을 구분자를 기준으로 분리하여 숫자 문자열 배열을 반환한다.
    private static String[] splitText(String text) {
        String delimiter = ",|:";
        String numbersText = text;

        Pattern pattern = Pattern.compile("//(.)\\\\n(.*)");
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            delimiter = Pattern.quote(matcher.group(1));
            numbersText = matcher.group(2);
        }

        return numbersText.split(delimiter);
    }

    // 숫자 문자열 배열을 받아 합계를 구한다.
    private static int sumTokens(String[] numberTokens) {
        int sum = 0;
        for (String token : numberTokens) {
            sum += parsePositiveNumber(token); // 각 문자열을 검증하고 숫자로 바꾸는 책임
        }
        return sum;
    }

    // 하나의 문자열을 검증하고 양수(0 포함)로 변환합니다. 빈 문자열은 0으로 처리한다
    private static int parsePositiveNumber(String token) {
        if (token.isEmpty()) {
            return 0;
        }

        try {
            int number = Integer.parseInt(token);
            if (number < 0) {
                throw new IllegalArgumentException("음수는 입력할 수 없습니다.");
            }
            return number;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("문자열에 숫자가 아닌 값이 포함되어 있습니다.");
        }
    }
}
