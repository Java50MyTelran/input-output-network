package telran.net.examples;

import java.util.function.BinaryOperator;

import telran.view.*;
import java.io.*;

public class NumbersOperationsMenu {
	static String name;
	static PrintStream writer;
	static BufferedReader reader;
public static Item getNumberOperationsItem(String name, PrintStream writer, BufferedReader reader) {
	NumbersOperationsMenu.name = name;
	NumbersOperationsMenu.writer = writer;
	NumbersOperationsMenu.reader = reader;
	return Item.of(name, NumbersOperationsMenu::performMethod);
	
	
	
}
static void twoNumbersAction(InputOutput io,
		String operation) {
	double firstNumber = io.readDouble("Enter first number", "no number");
	double secondNumber = io.readDouble("Enter second number","no number");
	writer.println(String.format("numbers#%s#%f#%f", operation, firstNumber, secondNumber));
	try {
		io.writeLine(reader.readLine());
	} catch (IOException e) {
		throw new RuntimeException(e.getMessage());
	}
	
	
}

static void performMethod(InputOutput io1) {
	Item [] items = {
			Item.of("Add two numbers",
					io -> twoNumbersAction(io, "add")),
			Item.of("Subtract two numbers", io -> twoNumbersAction(io, "subtract")),
			Item.of("Divide two numbers", io -> twoNumbersAction(io, "divide")),
			Item.of("Multiply two numbers", io -> twoNumbersAction(io, "multiply")),
			Item.exit()
		
		};
			Menu menu = new Menu(name, items);
			menu.perform(io1);
}
}
