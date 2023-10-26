package telran.net.examples;


import java.io.*;
import java.net.*;

import telran.net.NetworkHandler;
import telran.net.TcpClientHandler;
import telran.view.*;

public class NumbersDatesTcpClientAppl {
private static final String HOST = "localhost";
private static final int PORT = 5000;

	public static void main(String[] args) throws Exception {
		InputOutput io = new SystemInputOutput();
		TcpClientHandler handler = new TcpClientHandler(HOST, PORT);
		Menu menu = new Menu("Operations", getItems(handler));

		menu.perform(io);

	}

	private static Item[] getItems(TcpClientHandler handler) {
		Item items[] = {
			NumbersOperationsMenu.getNumberOperationsItem( "Number Operations", handler),
			DatesOperationsMenu.getDateOperationsItem("Date Operations", handler),
			Item.of("Exit", io -> {
				try {
					handler.close();
				} catch (IOException e) {
					throw new RuntimeException(e.getMessage());
				}
			}, true)
		};
		return items;
	}

}
