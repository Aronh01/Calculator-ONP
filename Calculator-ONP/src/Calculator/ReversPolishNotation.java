package Calculator;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class ReversPolishNotation {	
	Stack<String> stack=new Stack<String>();
	List<Character> operators=Arrays.asList('+','-','*','/');
	
	public String count(String text)
	{
		stack.clear();
		String equation=conversionToRPN(text);
		for(int i=0;i<equation.length();++i)
		{
			if(Character.isDigit(equation.charAt(i)) || (i+1<equation.length() && equation.charAt(i)=='-' && Character.isDigit(equation.charAt(i+1))))
			{
				String pom="";
				while(i<equation.length() && equation.charAt(i)!=' ')
				{
					pom+=equation.charAt(i);
					++i;
				}
				
				stack.push(pom);
			}
			else if(operators.contains(equation.charAt(i)) && stack.size()>1)
			{
				double a=Double.parseDouble(stack.pop());
				double b=Double.parseDouble(stack.pop());
				stack.push(String.valueOf(arithmeticOperation(b, a, equation.charAt(i))));
			}
			else if(equation.charAt(i)==' ');
		}
		if(stack.size()==0)
			return "b³¹d";
		else
			return stack.pop();
	}
	
	private double arithmeticOperation(double a,double b,char operator)
	{
		double result=0;
		switch(operator)
		{
			case '+':result=a+b;break;
			case '-':result=a-b;break;
			case '*':result=a*b;break;
			case '/':result=a/b;break;
		}
		return result;
	}
	
	private String conversionToRPN(String text)
	{
		String result = "";
		boolean sign = true;
		text=text.replaceAll(" ","");
		text=text.replace(',', '.');
		 
		for(char i:text.toCharArray())
		{
			if (i == '(') 
			{
				stack.push("(");
				sign = true;
				result += " ";
			} 
			else if (i == ')')
			{
				result += " " + getFromBracket();
				sign = false;
			} 
			else if (operators.contains(i) && !sign) 
			{
				result += " " + getFromStack(String.valueOf(i));
				sign = true;
			} 
			else 
			{
				result += i;
				sign = false;              
			}
		}
		result += getAllFromStack();
		return result;
	}
	
	private String getFromBracket() 
	{
		String result = "";
		String c;
		if (!stack.empty()) 
		{
			c = stack.pop();
			while (!stack.empty() && c!="(")
			{
				result = result + " " + c;
				c =stack.pop();
			}
		}
		return result;
	}
	
	private String getFromStack(String operator) 
	{	
		String result = "";
		String c;
		if (!stack.empty()) 
		{
			c=stack.peek();
			while (((operator.equals("+") || operator.equals("-")) && !c.equals("(")) ||((operator.equals("/") || operator.equals("*")) && (c.equals("*") || c.equals("/"))))
			{
				if(stack.empty()) break;
				c =stack.pop();
				result += " " + c;
			}
		}
		stack.push(operator);
		 
		return result;
	}	
	
	private String getAllFromStack() 
	{
		String result = "";
		String c;
		while (!stack.empty())
		{
			c = stack.pop();
			result += " " + c;
		}
		return result;
	}

}
