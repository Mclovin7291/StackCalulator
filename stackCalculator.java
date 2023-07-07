import java.util.*;
public class stackCalculator {

    public static void main(String[] args) {
       String expresssion = "10 + 2 - 15 / 2 * 6 ";//The expression to be evaluated

       System.out.println("The expression equals: " + calculate(expresssion));//prints the evaluated expression
    }

    public static int calculate(String exp){// method that will calculate the expression

        char[] arr = exp.toCharArray(); //converts the string into a list characters.

        Stack<Integer> values = new Stack<Integer>();// stack for the values

        Stack<Character> opperations= new Stack<Character>();// stack for the opperations

        for(int i=0; i < arr.length; i++){// loop that goes through each element of the expression.
            if (arr[i]== ' '){// if there is a space continue with loop.
                continue;
            }
            if (arr[i] >= '0' && arr[i] <= '9'){// if the character is a number put in the string buffer.
                StringBuffer str = new StringBuffer();

                while ( i < arr.length && arr[i] >= '0' && arr[i] <= '9'){// if there is another didgit, add it to the string buffer. 
                    str.append(arr[i++]);
                }
                values.push(Integer.parseInt(str.toString()));// push the string buffer to the bottom of the stack. 
                i--; 

            }
            else if (arr[i]== '('){// if there is  an open bracis, push it to ops 
                opperations.push(arr[i]);
            }
            else if (arr[i]== ')'){//if there is a close bracis, solve everything that bracis
                while (opperations.peek() != '('){
                    values.push(ops(opperations.pop(),values.pop(),values.pop()));
                }
                opperations.pop();
            }
            // if one of the elements are a opperent, see if that element takes presedence.
            // takes the opperation that takes pressedence and applys that two the top two values of the values stack 

            else if (arr[i] == '+' || arr[i] == '-' || arr[i] == '*'||arr[i] == '/'){

                while( !opperations.empty() && presedence(arr[i],opperations.peek())){

                    values.push(ops(opperations.pop(),values.pop(), values.pop()));
                }
                opperations.push(arr[i]);
            }   
        }
        //now that the precedence opperations are complete, do the remaining ones.
        while (!opperations.empty()){
            values.push(ops(opperations.pop(),values.pop(), values.pop()));
        }
        return values.pop();// returns the final value
    }


    public static int ops( char op, int y, int x){
        switch(op){
            case '+':// if the opperation is +, add them
                return x + y;
            case '-':// if the opperation is -, subtract them
                return x - y;    
            case '*':// if the opperation is *, multiply them
                return x * y; 
            case '/':// if the opperation is /, divide them
                if( y== 0){
                   throw new UnsupportedOperationException("Cannot divide by zero.");// if the numerator is zero, the expression cannot be divided.
                }  
                return x / y;
        }
        return 0;
    }
    //if opTwo has a similar or higher presedence than opOne, return true.
    // if not true, its false.
    public static boolean presedence( char opOne,char opTwo ){// 
        if( opTwo == '(' || opTwo==')'){
            return false;
        }    
        if ((opOne == '*' || opOne == '/') && (opTwo== '+' || opTwo == '-')){
            return false;
        }
        else {
            return true;
        }
    }
}
/*
 * input: 10 * 2 / 0
 * output: UnsupportedOperationException: Cannot divide by zero.
 */

 /*
 *input: 10 * 2 - 15 
 *output: 5
 */

 /*input:"10 * (2 - 15) "
  *output: -130
  */

  /*
   * input: 10 + 2 - 15 / 2 * 6 
   * output:-30
  */

  /*time complexity if O(1)
   * space complexity 1
   */