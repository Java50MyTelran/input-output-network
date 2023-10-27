package telran.net.examples;
import java.net.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.io.*;
public class NumbersDatesServerAppl {
  static final int PORT = 5000;
	public static void main(String[] args) throws Exception{
		ServerSocket serverSocket = new ServerSocket(PORT);
		System.out.println("Server is listnening on port " + PORT);
		while(true) {
			Socket socket = serverSocket.accept();
			runProtocol(socket);
		}

	}
	private static void runProtocol(Socket socket)  {
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintStream writer = new PrintStream(socket.getOutputStream())) {
			boolean running = true;
			while(running) {
				String request = reader.readLine();
				if (request == null) {
					System.out.println("client closed connection");
					running = false;
				} else {
					String response = getResponse(request);
					writer.println(response);
				}
			}
			
		} catch(Exception e) {
			System.out.println("Abnormal socket closing");
		}
		
	}
	private static String getResponse(String request) {
		// <type>#<operation>#<operand1>#<operand2>
		String response = null;
		String[] tokens = request.split("#");
		if(tokens.length != 4) {
			response = "request must be in format <type>#<operation>#<operand1>#<operand2>";
		} else {
			response = switch(tokens[0]) {
			case "numbers" -> numbersOperations(tokens);
			case "dates" -> datesOperations(tokens);
			default -> "Wrong type " + tokens[0];
			};
		}
		return response;
	}
	private static String datesOperations(String[] tokens) {
		String response = null;
		try {
			response = switch(tokens[1]) {
			case "days" -> LocalDate.parse(tokens[2]).plusDays(Integer.parseInt(tokens[3])) + "";
			case "between" -> ChronoUnit.DAYS.between(LocalDate.parse(tokens[2]), LocalDate.parse(tokens[3])) + "";
			default -> "Wrong operation " + tokens[1];
			};
		} catch (Exception e) {
			response = "Wrong Data";
		}
		return response;
	}
	private static String numbersOperations(String[] tokens) {
		String response = null;
		try {
			double firstNumber = Double.parseDouble(tokens[2]);
			double secondNumber = Double.parseDouble(tokens[3]);
			response = switch(tokens[1]) {
			case "add" ->  firstNumber + secondNumber + "";
			case "subtract" ->  firstNumber - secondNumber + "";
			case "divide" ->  firstNumber / secondNumber + "";
			case "multiply" ->  firstNumber * secondNumber + "";
			default -> "Wrong operation";
			};
		} catch (Exception e) {
			response = "Wrong Data";
		}
		return response;
	}

}
