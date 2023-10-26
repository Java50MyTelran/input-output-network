package telran.net.examples;

import telran.net.NetworkHandler;
import telran.view.*;

public class NumbersOperationsMenu {
	static NetworkHandler handler;
	static String name;
public static Item getNumberOperationsItem(String name, NetworkHandler handler) {
	NumbersOperationsMenu.handler = handler;
	NumbersOperationsMenu.name = name;
	
	return Item.of(name, NumbersOperationsMenu::performMethod);
	
	
	
}
static void twoNumbersAction(InputOutput io,
		String operation) {
	double firstNumber = io.readDouble("Enter first number", "no number");
	double secondNumber = io.readDouble("Enter second number","no number");
	io.writeObjectLine(handler.send("numbers/" + operation, new double[] {firstNumber, secondNumber}));
	
	
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
