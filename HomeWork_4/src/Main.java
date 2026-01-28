static Scanner scan = new Scanner(System.in);

void main() {
    System.out.print("Введите размер массива: ");
    int size = scan.nextInt();

    if(size <= 0) {
        System.out.println("Размер должен быть положительным числом!");
        scan.close();
        return;
    }

    int[] array = new int[size];
    boolean isInitialArray = false;

    while (true) {
        printMenu();
        int num = scan.nextInt();

        switch (num) {
            case 1:
                if(isInitialArray) {
                    System.out.println("Массив уже заполнен. Ввод повторно не требуется.");
                } else {
                    System.out.println("Введите " + size + " элементов: ");
                    inputArray(array, 0);
                    isInitialArray = true;
                }
                break;
            case 2:
                printArray(array);
                break;
            case 3:
                System.out.println("Введите число для поиска: ");
                int number = scan.nextInt();
                int index = findElement(array, number, 0);
                if(index != -1) {
                    System.out.println("Число " + number + " найдено на позиции: " + (index + 1));
                } else {
                    System.out.println("Элемент не найден");
                }
                break;
            case 4:
                bubbleSort(array, array.length);
                System.out.println("Массив отсортирован");
                break;
            case 5:
                System.out.println("Программа завершена");
                scan.close();
                return;

            default:
                System.out.println("Такого пункта нет. Выберите от 1 до 5.");
        }
        System.out.println();
    }
}

private static void bubbleSort(int[] array, int size) {
    if(size == 1) {
        return;
    }

    for (int i = 0; i < size -1; i++) {
        if(array[i] > array[i + 1]) {
            int temp = array[i];
            array[i] = array[i + 1];
            array[i + 1] = temp;
        }
    }
    bubbleSort(array, size - 1);
}

private static int findElement(int[] array, int number, int index) {
    if(index >= array.length) {
        return  -1;
    }
    if(array[index] == number) {
        return index;
    }
    return findElement(array, number, index + 1);
}

private static void inputArray(int[] array, int index) {
    if(index >= array.length) {
        return;
    }
    System.out.println("Элемент [" + (index + 1) + "]: ");
    array[index] = scan.nextInt();
    inputArray(array, index + 1);
}

private static void printMenu() {
    System.out.println("Меню:");
    System.out.println("1. Ввод элементов массива");
    System.out.println("2. Отображение массива");
    System.out.println("3. Поиск элемента по значению");
    System.out.println("4. Сортировка пузырьком");
    System.out.println("5. Выход");
}

private static void printArray(int[] array) {
    System.out.println("Вывод значений массива: ");
    for (int i = 0; i < array.length; i++) {
        System.out.print(array[i] + " ");
    }
    System.out.println();
}

