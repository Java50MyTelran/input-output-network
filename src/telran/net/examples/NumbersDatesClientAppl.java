package telran.net.examples;


import java.io.*;
import java.net.*;

import telran.view.*;

public class NumbersDatesClientAppl {
private static final String HOST = "localhost";
private static final int PORT = 5000;
static BufferedReader reader;
static PrintStream writer;
static Socket socket;
	public static void main(String[] args) throws Exception {
		InputOutput io = new SystemInputOutput();
		socket = new Socket(HOST, PORT);
		reader = new BufferedReader(new InputStreamReader(socket.getInputStream())) ;
		writer = new PrintStream(socket.getOutputStream());
		Menu menu = new Menu("Operations", getItems());

		menu.perform(io);

	}

	private static Item[] getItems() {
		Item items[] = {
			NumbersOperationsMenu.getNumberOperationsItem( "Number Operations", writer, reader),
			DatesOperationsMenu.getDateOperationsItem("Date Operations", writer, reader),
			Item.of("Exit", io -> {
				try {
					socket.close();
				} catch (IOException e) {
					throw new RuntimeException(e.getMessage());
				}
			}, true)
		};
		return items;
	}

}
