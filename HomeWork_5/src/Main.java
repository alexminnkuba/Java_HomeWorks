
void main() {
    String[] tests = {
            "Лидер Венере не вредил",
            "Лидер Венере не вредит",
            "Могу добавить",
            "Лазер резал",
            "А роза упала на лапу Азора",
            "Madam I'm Adam",
            "hello"
    };

    for(String test: tests) {
        boolean result = isPalindrome(test);
        System.out.printf("%30s : %s%n", test, result);
    }
}

private static boolean isPalindrome(String text) {
    if(text == null) {
        return false;
    }
    String str = text.toLowerCase().replaceAll("[^а-яa-z]", "");
    StringBuilder reversed = new StringBuilder(str);
    reversed.reverse();
    return str.equals(reversed.toString());
}
