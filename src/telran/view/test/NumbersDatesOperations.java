package telran.view.test;


import telran.view.*;

public class NumbersDatesOperations {

	public static void main(String[] args) {
		InputOutput io = new SystemInputOutput();
		
		Menu menu = new Menu("Operations", getItems());

		menu.perform(io);

	}

	private static Item[] getItems() {
		Item items[] = {
			NumbersOperationsMenu.getNumberOperationsItem( "Number Operations"),
			DatesOperationsMenu.getDateOperationsItem("Date Operations"),
			Item.exit()
		};
		return items;
	}

}
