package telran.io.performance;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import telran.io.*;
import telran.performance.PerformanceTest;

public class CopyFilesPerformanceAppl {
	static final String pathoToSource = "/Users/User/Videos/angular-lecture.flv";
	static final String pathoToDestination = "/Users/User/Videos/angular-lecture-copy.flv";

	public static void main(String[] args) {
		Integer[] bufferLengthValues = { 4_000, 1_000_000, 1_000_000_000 };
		try {
			long size = Files.size(Path.of(pathoToSource));
			List<CopyFilesPerformanceTest> tests = new ArrayList<>(List.of(getPerformanceTest(size, "FilesCopy",
					new FilesCopy()), getPerformanceTest(size, "TransferToCopy",new TransferToCopy())));
			tests.addAll(Arrays.stream(bufferLengthValues).map(bl -> getPerformanceBufferSizeTest(bl, size)).toList());
			tests.forEach(t -> t.run());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
	private static CopyFilesPerformanceTest getPerformanceTest(long size, String testName, CopyFile copyFile) {
		CopyFilesPerformanceTest test = new CopyFilesPerformanceTest(String.format("%s ; size:%d", testName, size),
				1, pathoToSource, pathoToDestination, copyFile);
		return test;
	}

	private static CopyFilesPerformanceTest getPerformanceBufferSizeTest(Integer bl, long size) {
		CopyFilesPerformanceTest test =
				new CopyFilesPerformanceTest(String.format("%s implementation buffer length %d; size:%d",
						"CopyFileStreams", bl, size),
						1, pathoToSource, pathoToDestination, new ReadBufferCopy(bl));
		return test;
	}

}
