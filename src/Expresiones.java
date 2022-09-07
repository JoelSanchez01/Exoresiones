//AUTOR: JOEL EDUARDO SANCHEZ HERRERA
//ESTILO DE CODIGO: BDS

import java.util.Scanner;
import java.util.Stack;

public class Expresiones {


    static Stack<Double> valores = new Stack<>();
    static Stack<Character> operadores = new Stack<>();

    static double Evaluar(String expresion){
        char[] caracteres = expresion.toCharArray();

        for (int i = 0; i < caracteres.length ; i++) {
            if(caracteres[i] == ' ') continue;
            else if (caracteres[i] >= '0' && caracteres[i] <= '9') {
                StringBuilder constructor = new StringBuilder();
                while (i < caracteres.length && (caracteres[i] >= '0' && caracteres[i] <= '9')) {
                    constructor.append(caracteres[i]);
                    i++;
                }
                String s = constructor.toString();
                double numero = Double.parseDouble(s);
                valores.push(numero);
                i--;
            }
            else if(caracteres[i] == '(') {
                operadores.push(caracteres[i]);
            }
            else if(caracteres[i] == ')'){
                while(operadores.peek() != '('){
                    hacerOperacion();
                }
                operadores.pop();

            } else if(esOperador(caracteres[i])){
                while(!operadores.isEmpty() && precedencia(operadores.peek()) >= precedencia(caracteres[i])){
                        hacerOperacion();
                }
                operadores.push(caracteres[i]);
            }
        }

        while(!operadores.isEmpty()){
            hacerOperacion();
        }
        return valores.pop();
    }


    private static int precedencia(char op){
        return switch (op){
            case '+', '-' -> 1;
            case '*', '/', '%' -> 2;
            case '^' -> 3;
            default -> -1;
        };
    }

    private static void hacerOperacion(){
        char op = operadores.pop();
        double b = valores.pop();
        double a = valores.pop();

        switch (op){
            case '+' -> valores.push(a+b);
            case '-' -> valores.push(a-b);
            case '*' -> valores.push(a*b);
            case '/' -> valores.push(a/b);
            case '%' -> valores.push(a%b);
            case '^' -> valores.push(Math.pow(a, b));
        }
    }

    private static boolean esOperador(char op){
        return op == '*' || op == '/'
                || op == '^' || op == '%'
                || op == '+' || op == '-';

    }




    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(Evaluar(sc.next()));
    }





}
