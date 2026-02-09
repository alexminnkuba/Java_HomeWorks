
void main() {
    int[] arr = new int[]{2,5,2,7,8,8,10};
    System.out.println("Исходный массив: " + Arrays.toString(arr));

    Set<Integer> set = new HashSet<>();
    for (int i = 0; i < arr.length; i++) {
        set.add(arr[i]);
    }

    System.out.println("Массив без дубликатов: " + Arrays.toString(set.toArray()));
}

