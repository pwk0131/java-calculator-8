package calculator;

//camp.nextstep.edu.missionutils에서 제공하는 Console API를 사용하여 구현

import camp.nextstep.edu.missionutils.Console;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현

        /* [기능목록 및 commit 순서]
         * 1."덧셈할 문자열을 입력해 주세요." 문구 출력 -> Console.readLine()으로 사용자 입력받기. 결과를 형식에 맞게 출력하기(일단 0) [완]
         * 2. 입력된 문자열이 비어있거나, null일 경우 -> 0을 반환하는 기능. [완]
         * 3. 쉼표(,) 또는 콜론(:)을 기준으로 문자열을 분리하는 기능, 분리된 각 숫자의 합을 계산하여 반환하는 기능. (예: "1,2:3" -> 6) [완]
         * 4. 문자열이 //와 \n으로 시작하는지 판별하는 기능. //와 \n 사이의 문자를 커스텀 구분자로 인식하여 문자열을 분리하고 합을 계산하는 기능. (예: "//;\n1;2;3" -> 6) [완]
         * 5. 예외처리: 분리된 숫자를 확인하여 음수가 포함되어 있을 경우 IllegalArgumentException을 발생시키는 기능.
         * 6. 예외처리: 구분자로 숫자가 들어올 경우
         */

        // Console API를 사용하여 입출력 구현
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String input = Console.readLine();

        int result = calculateSum(input);
        System.out.println("결과 : " + result);
    }

    // 계산 로직 메서드
    public static int calculateSum(String text) {
        // 빈 문자열 또는 null 처리
        if (text == null || text.isEmpty()) {
            return 0;
        }

        // 커스텀 구분자 처리
        String delimiter = ",|:";
        String numbersText = text;

        // "//"와 "\n" 사이의 커스텀 구분자 찾기
        Pattern pattern = Pattern.compile("//(.)\\\\n(.*)");
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            delimiter = Pattern.quote(matcher.group(1)); // 찾은 구분자
            numbersText = matcher.group(2);              // 숫자 부분
        }

        String[] numbers = numbersText.split(delimiter);

        int sum = 0;
        for (String numberStr : numbers) {
            sum += Integer.parseInt(numberStr);
        }

        return sum;
    }
}
