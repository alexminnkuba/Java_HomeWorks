void main() {
    Student st1 = new Student("Roman");
    Student st2 = new Student("Vladimir");

    st1.printStudentInfo();
    System.out.println();
    st2.printStudentInfo();
}

class Student {
    private String name;
    private Laptop laptop;
    public Student(String name) {
        this.name = name;
        this.laptop = new Laptop();
    }

    public  void printStudentInfo() {
        System.out.print(name + " => ");
        laptop.printLaptop();
    }

    class Laptop {
        private final String model;
        private final String processor;
        private final int ram;

        public  Laptop() {
            this.model = "HP";
            this.processor = "i7";
            this.ram = 16;
        }

        public void printLaptop() {
            System.out.printf("%s, %s, %d", model, processor, ram);
        }
    }
}
