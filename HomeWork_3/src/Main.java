
void main(String[] args) {
 Scanner scan = new Scanner(System.in);
    System.out.println("Вычисление площади фигур");
    System.out.println("Выбор фигуры:");
    System.out.println("1 - треугольник");
    System.out.println("2 - прямоугольник");
    System.out.println("3 - круг");

    int number = scan.nextInt();

    switch (number) {
        case 1:
            System.out.print("Введите сторону а: ");
            double a = scan.nextDouble();
            System.out.print("Введите сторону b: ");
            double b = scan.nextDouble();
            System.out.print("Введите сторону c: ");
            double c = scan.nextDouble();

            double triangleArea = Area(a, b, c);
            System.out.printf("Площадь треугольника: %.2f%n", triangleArea);
            break;
        case 2:
            System.out.print("Введите длину: ");
            double length = scan.nextDouble();
            System.out.print("Введите ширину: ");
            double width = scan.nextDouble();

            double rectangleArea = Area(length, width);
            System.out.printf("Площадь прямоугольника: %.2f%n", rectangleArea);
            break;
        case 3:
            System.out.print("Введите радиус: ");
            double radius = scan.nextDouble();

            double circleArea = Area(radius);
            System.out.printf("Площадь круга: %.2f%n", circleArea);
            break;
        default:
            System.out.println("Неверный выбор!");
    }
    scan.close();
}

    private static double Area(double a, double b, double c) {
        double perimetr = (a + b + c) / 2;
        return Math.sqrt(perimetr * (perimetr - a) * (perimetr - b) * (perimetr - c));
    }

    private  static  double Area(double length, double width) {
        return  length * width;
    }

    private  static double Area(double radius) {
        return Math.PI * radius * radius;
    }