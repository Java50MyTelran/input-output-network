package telran.view.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import telran.employees.dto.Employee;
import telran.view.*;

class InputOutputTest {
 InputOutput io = new SystemInputOutput();
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	@Disabled
	void testReadEmployeeString() {
		Employee empl = io.readObject("Enter employee <id>#<name>#<iso birthdate>#<department>#<salary>", "Wrong Employee",
				str -> {
					String[] tokens = str.split("#");
					if (tokens.length != 5) {
						throw new RuntimeException("must be 5 tokens");
					}
					long id = Long.parseLong(tokens[0]);
					String name = tokens[1];
					String department = tokens[3];
					int salary = Integer.parseInt(tokens[4]);
					LocalDate birthDate = LocalDate.parse(tokens[2]);
					return new Employee(id, name, department, salary, birthDate);
				});
		io.writeObjectLine(empl);
	}
	@Test
	//@Disabled
	void testReadEmployeeBySeparateField() {
		
		//id in range [100000-999999]
		//name - more than two letters where first one is a capital
		//birthDate - any localdate in range [1950-12-31 - 2003-12-31
		//department - one out of ["QA", "Development", "Audit", "Accounting", "Management"
		//salary - integer number in range [7000 - 50000]
		long id = io.readLong("Enter Employee identity", "Wrong identity value",
				100000, 999999);
		String name = io.readString("Enter Employee name", "Wrong name", str -> str.matches("[A-Z][a-z]{2,}"));
		LocalDate birthDate = io.readIsoDate("Enter birthdate in ISO (YYYY-MM-DD) format","Wrong birthdate",
				LocalDate.parse("1950-12-31"), LocalDate.parse("2003-12-31"));
		HashSet<String> departments = new HashSet<>(List.of("QA", "Development", "Audit", "Accounting", "Management"));
		String department = io.readString("Enter department from " + departments, "Wrong department", departments);
		int salary = io.readInt("Enter salary", department, 7000, 50000);
		io.writeObjectLine(new Employee(id, name, department, salary, birthDate));
	}
	@Test
	
	void testSimpleArithmeticCalculator() {
		HashSet<String> operations = new HashSet<>(List.of("+","-","*","/"));
		double op1 = io.readDouble("Enter first number", "Wrong number");
		double op2 = io.readDouble("Enter second number", "Wrong number");
		String operation = io.readString("Enter operation from " + operations, "Wrong operation", operations);
		double res = 
		switch(operation) {
		case "+" -> op1 + op2;
		case "-" -> op1 - op2;
		case "*" -> op1 * op2;
		case "/" -> op1 / op2;
		default -> throw new IllegalArgumentException("Unexpected value: " + operation);
		
		};
		io.writeObjectLine(res);
	}

}
