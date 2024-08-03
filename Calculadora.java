import java.util.Scanner;

public class Calculadora {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("¡Bienvenido a una MikeContadorChiquito! \n" +
                "Para poder continuar debes seleccinar una opcion de las que se te muestran \n" +
                "1: Suma \n" +
                "2: Resta \n" +
                "3: Multiplicación \n" +
                "4: División");

        int opcion = sc.nextInt();

        System.out.println("Introduce un numero: ");
        short num1 = sc.nextShort();
        System.out.println("Introduce otro numero: ");
        short num2 = sc.nextShort();

        switch (opcion) {
            case 1:
                System.out.println("El resultado de la suma es: " + (num1 + num2));
                break;
            case 2:
                System.out.println("El resultado de la resta es: " + (num1 - num2));
                break;
            case 3:
                System.out.println("El resultado de la multiplicacion es: " + (num1 * num2));
                break;
            case 4:
                System.out.println("El resultado de la division es: " + (num1 / num2));
                break;
            default:
                System.out.println("Opción no valida");
        }

        if (num1 > num2) {
            System.out.println(num1 + " es mayor que " + num2);
        } else if (num2 > num1) {
            System.out.println(num1 + " es menor que " + num2);
        } else {
            System.out.println("Los dos numeros son iguales");
        }
        sc.close();
    }
}