package calculator;

//camp.nextstep.edu.missionutils에서 제공하는 Console API를 사용하여 구현

import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현

        /* [기능목록 및 commit 순서]
         * 1."덧셈할 문자열을 입력해 주세요." 문구 출력 -> Console.readLine()으로 사용자 입력받기. 결과를 형식에 맞게 출력하기(일단 0)
         * 2. 입력된 문자열이 비어있거나, null일 경우 -> 0을 반환하는 기능.
         * 3. 쉼표(,) 또는 콜론(:)을 기준으로 문자열을 분리하는 기능, 분리된 각 숫자의 합을 계산하여 반환하는 기능. (예: "1,2:3" -> 6)
         * 4. 문자열이 //와 \n으로 시작하는지 판별하는 기능. //와 \n 사이의 문자를 커스텀 구분자로 인식하여 문자열을 분리하고 합을 계산하는 기능. (예: "//;\n1;2;3" -> 6)
         * 5. 분리된 숫자를 확인하여 음수가 포함되어 있을 경우 IllegalArgumentException을 발생시키는 기능.
         */

        // Console API를 사용하여 구현
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

        // 정규식을 사용하여 , 또는 : 로 분리
        String[] numbers = text.split(",|:");

        int sum = 0;
        for (String numberStr : numbers) {
            sum += Integer.parseInt(numberStr); // 문자열을 정수로 변환하여 더함
        }

        return sum;
    }
}
