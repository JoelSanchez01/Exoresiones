   # Evaluador de Expresiones Aritméticas

Este es un evaluador de expresiones aritméticas realizado en java, gracias a la utilización de diversas herramientas como:  
- Pilas

- Programación Modular

- Arreglos

- Entre otras cosas

Gracias a la programación modular es mucho más sencillo poder encontrar algún error en nuestro código porque al tenerlo separado podremos tener más flexibilidad al momento de leerlo para encontrar algún problema.
  

    private static int precedencia(char op){  
    return switch (op){  
        case '+', '-' -> 1;  
        case '*', '/', '%' -> 2;  
         case '^' -> 3;  
          default -> -1;  
          }; 
          }
Al igual que con las funciones que nos entregan las pilas podemos utilizarlas para acceder a los datos que ingresamos a estas, como nuestros operadores o nuestros valores. Su función es muy sencilla ya que podemos stackear cada valor y así tener un mejor orden sobre qué es lo que ingresa en ellas y lo que sale.

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
