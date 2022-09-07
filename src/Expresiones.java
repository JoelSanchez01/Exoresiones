//AUTOR: JOEL EDUARDO SANCHEZ HERRERA
//ESTILO DE CODIGO: BDS

import java.util.Scanner;
import java.util.Stack;

public class Expresiones {


    static Stack<Double> valores = new Stack<>();
    static Stack<Character> operadores = new Stack<>();

    static double Evaluar(String expresion){
        //REVISIMOS UN VALOR Y LO SEPARAMOS EN UN ARREGLO DE CARACTERES
        char[] caracteres = expresion.toCharArray();

        for (int i = 0; i < caracteres.length ; i++) {
            if(caracteres[i] == ' ') continue; //si es un espacio lo ignoramos
            else if (caracteres[i] >= '0' && caracteres[i] <= '9') { //si es un numero entra a la funcion donde agarramos los digitos que contenga
                StringBuilder constructor = new StringBuilder();
                while (i < caracteres.length && ((caracteres[i] >= '0' && caracteres[i] <= '9') || caracteres[i] == '.')) { //VERIFICA QUE SEA UN NUMERO DE MAS CIFRAS O CONTENGA UN PUNTO
                    constructor.append(caracteres[i]); //LO AGREGA TODO EN UN STRING
                    i++;
                }
                String s = constructor.toString(); 
                double numero = Double.parseDouble(s); //LO CONVIERTE EN UN NUMERO Y LO MANDA A LA PILA DE NUMEROS
                valores.push(numero); 
                i--;
            }
            else if(caracteres[i] == '(') {
                operadores.push(caracteres[i]); //SI ES UN INICIO DE PARENTESIS LO MANDA A LA PILA DE OPERADORES
            }
            else if(caracteres[i] == ')'){
                while(operadores.peek() != '('){
                    hacerOperacion(); //REVISA QUE TENGA UN PARENTESIS DE CIERRE Y HACE LA OPERACION
                }
                operadores.pop();

            } else if(esOperador(caracteres[i])){
                while(!operadores.isEmpty() && precedencia(operadores.peek()) >= precedencia(caracteres[i])){
                        hacerOperacion(); //hace la operacion dependiendo de la precedencia
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
            default -> -1; //asigna un valor de jerarquia a la precedencia
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
        //DEPENDIENDO DEL CASO HACE UN SWITCH PARA HACER LA OPERACION
    }

    private static boolean esOperador(char op){
        return op == '*' || op == '/'
                || op == '^' || op == '%'
                || op == '+' || op == '-';
    //REVISA QUE TODO SEA UN OPERADOR
    }




    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(Evaluar(sc.next())); //IMPRIME LA FUNCION CUANDO LA MANDA A LLAMAR Y MANDA UN VALOR POR TECLADO
    }





}
