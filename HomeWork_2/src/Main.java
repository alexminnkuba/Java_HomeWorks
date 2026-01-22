import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    System.out.print("Введите число от 1 до 99: ");
    int kop = scan.nextInt();
    if(kop < 1 || kop > 99) {
        System.out.println("Число должно быть в диапазоне от 1 до 99");
    } else {
        String str;
        if(kop >= 11 && kop <= 14) {
            str = " копеек";
        } else {
            int lastDigit = kop % 10;
           str =  switch (lastDigit) {
               case 1 -> " копейка";
               case 2, 3, 4 -> " копейки";
               default -> " копеек";
            };
        }
        System.out.println(kop + str);
    }
        scan.close();
    }
}