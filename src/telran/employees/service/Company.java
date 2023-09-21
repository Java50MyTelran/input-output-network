package telran.employees.service;

import java.nio.file.*;
import java.util.List;

import telran.employees.dto.*;
import java.io.*;
public interface Company {
boolean addEmployee(Employee empl); //adds a given employee object, returns true if added otherwise false (if employee with the id exists)
Employee removeEmployee(long id); //returns reference to an employee being removed otherwise null (if employee doesn't exist)
Employee getEmployee(long id);//returns reference to an employee by the given id otherwise null (if employee doesn't exist)
List<Employee> getEmployees(); //returns list of all employee objects. In the case of none exists it returns empty list
@SuppressWarnings("unchecked")
default void restore(String dataFile) {
	//restoring all employees from a given file
	//if there is no file it just means that application doen't have any saved data, that is no exception should be thrown
	//all possible exceptions should be propagated as a RuntimeException
	if(Files.exists(Path.of(dataFile))) {
		try(ObjectInputStream stream = new ObjectInputStream(new FileInputStream(dataFile))) {
			List<Employee> employeesRestore = (List<Employee>) stream.readObject();
			employeesRestore.forEach(e -> addEmployee(e));
		}catch(Exception e) {
			throw new RuntimeException(e.toString());
		}
	}
}
default void save(String dataFile) {
	//saving all employee objects to a given file
	//Implementation hint: use getEmployees() method to get the list of all employee objects and to serialize whole list to the file
	//all possible exceptions should be propagated as a RuntimeException
	try(ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(dataFile))) {
		stream.writeObject(getEmployees());
	}catch(Exception e) {
		throw new RuntimeException(e.toString());
	}
}
}
