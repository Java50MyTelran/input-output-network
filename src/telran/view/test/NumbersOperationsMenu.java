package telran.view.test;

import java.util.function.BinaryOperator;

import telran.view.*;


public class NumbersOperationsMenu {
	static String name;
public static Item getNumberOperationsItem(String name) {
	NumbersOperationsMenu.name = name;
	return Item.of(name, NumbersOperationsMenu::performMethod);
	
	
	
}
static void twoNumbersAction(InputOutput io,
		BinaryOperator<Double> operator) {
	double firstNumber = io.readDouble("Enter first number", "no number");
	double secondNumber = io.readDouble("Enter second number","no number");
	io.writeObjectLine(operator.apply(firstNumber, secondNumber));
}

static void performMethod(InputOutput io1) {
	Item [] items = {
			Item.of("Add two numbers",
					io -> twoNumbersAction(io, (a,b) -> a + b)),
			Item.of("Subtract two numbers", io -> twoNumbersAction(io, (a,b) -> a - b)),
			Item.of("Divide two numbers", io -> twoNumbersAction(io, (a,b) -> a / b)),
			Item.of("Multiply two numbers", io -> twoNumbersAction(io, (a,b) -> a * b)),
			Item.of("Percent of part from whole", io -> {
				double part = io.readDouble("Enter part as a number", "Wrong number");
				double whole = io.readDouble("Enter whole as a number", "Wrong number");
				io.writeObjectLine(part / whole * 100);
			}),
			Item.exit()
		};
			Menu menu = new Menu(name, items);
			menu.perform(io1);
}
}
