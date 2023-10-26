package telran.net.examples;

import java.net.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import telran.net.TcpServer;

import java.io.*;

public class NumbersDatesServerAppl {
	static final int PORT = 5000;

	public static void main(String[] args) throws Exception {
		TcpServer tcpServer = new TcpServer(PORT, new NumbersDatesProtocol());
		tcpServer.run();

	}

	
}
