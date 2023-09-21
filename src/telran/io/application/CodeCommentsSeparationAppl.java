package telran.io.application;

import java.nio.file.Files;
import java.nio.file.Path;
import java.io.*;
public class CodeCommentsSeparationAppl {
	//Application for code comments separation
	//args[0] - path to file with code and comments
	//args[1] - path to file with only code
	//args[2] - path to file with only comments
public static void main(String[] args) {
	if (args.length < 3) {
		System.out.println("Too few arguments, usage: <file with code and comments> <file code> <file comments");
	} else {
		try(BufferedReader sourceFile = new BufferedReader(new FileReader(args[0]));
				PrintWriter codeFile = new PrintWriter(args[1]);
				PrintWriter commentsFile = new PrintWriter(args[2])) {
			if (!Files.exists(Path.of(args[0]))) {
				throw new Exception(args[0] + " doesn't exist");
			}
			sourceFile.lines().forEach(l -> {
				if(l.trim().startsWith("//")) {
					commentsFile.println(l.replace("//", ""));
				} else {
					codeFile.println(l);
				}
			});
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
}
