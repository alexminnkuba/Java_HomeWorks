static class WordCounter {
    private final Function<String, Integer> countWords = str -> {
        return (int)Arrays.stream(str.trim().split(" ")).count();
    };

    public int count(String text) {
        return countWords.apply(text);
    }
}
void main() {
    WordCounter counter = new WordCounter();

    String[] words = {
            "Лямбда выражения",
            "Программа, которая подсчитывает количество слов в предложении"
    };

    int wordCount;

    for(String text : words) {
        wordCount = 0;
        wordCount = counter.count(text);
        System.out.println("Исходная строка: \"" + text + "\"");
        System.out.println("Количество слов: " + wordCount);
    }
}
