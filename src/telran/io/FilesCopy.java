package telran.io;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FilesCopy implements CopyFile {

	@Override
	public void copy(String pathToSource, String pathToDestination) throws Exception {
		Files.copy(Path.of(pathToSource), Path.of(pathToDestination), StandardCopyOption.REPLACE_EXISTING);

	}

}
