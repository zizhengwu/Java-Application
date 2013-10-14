import java.util.Stack;
import java.lang.Character;
import java.util.Scanner;
public class Calculator {
	public static void main(String[] args) {
		String expression = "";
		// for (int i = 0; i < args.length; i++) {
		// 	expression += args[i];
		// }
		while(true) {
			Scanner scanner = new Scanner(System.in);
			String line = scanner.nextLine();
			expression = line;
			System.out.println(evaluateExpression(expression));
		}
	}

	public static int evaluateExpression(String expression) {
		Stack <Character> operatorStack = new Stack <Character>();
		Stack <Integer> operandStack = new Stack <Integer>();
		java.util.StringTokenizer tokens = new java.util.StringTokenizer(expression, "()+-/*", true);
		while (tokens.hasMoreTokens()) {
			String token = tokens.nextToken().trim();
			if (token.length() == 0)
				continue;
			else if (token.charAt(0) == '+' || token.charAt(0) == '-') {
				while (!operatorStack.isEmpty() && (
					operatorStack.peek() == '+' ||
					operatorStack.peek() == '-' ||
					operatorStack.peek() == '*' ||
					operatorStack.peek() == '/' 
					)){
					processAnOperator(operandStack, operatorStack);
				}
				operatorStack.push(token.charAt(0));
			}
			else if (token.charAt(0) == '*' || token.charAt(0) == '/') {
				while (!operatorStack.isEmpty() && (
					operatorStack.peek() == '*' ||
					operatorStack.peek() == '/'
					)) {
					processAnOperator(operandStack, operatorStack);
				}
				operatorStack.push(token.charAt(0));
			}
			else if (token.trim().charAt(0) == '(') {
				operatorStack.push('(');
			}
			else if (token.trim().charAt(0) == ')') {
				while (operatorStack.peek() != '(') {
					processAnOperator(operandStack, operatorStack);
				}
				operatorStack.pop();
			}
			else {
				operandStack.push(new Integer(token));
			}
		}
		while (!operatorStack.isEmpty()) {
			processAnOperator(operandStack, operatorStack);
		}
		return operandStack.pop();
	}

	private static void processAnOperator(Stack<Integer> operandStack,
			Stack<Character> operatorStack) {
		char op = operatorStack.pop();
		int op1 = operandStack.pop();
		int op2 = operandStack.pop();
		if (op == '+')
			operandStack.push(op2 + op1);
		else if (op == '-')
			operandStack.push(op2 - op1);
		else if (op == '*')
			operandStack.push(op2 * op1);
		else if (op == '/')
			operandStack.push(op2 / op1);
	}
}