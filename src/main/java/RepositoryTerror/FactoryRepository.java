package RepositoryTerror;

import Interfaces.IRepositoryTerror;
import Service.PropertyService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FactoryRepository {
    public IRepositoryTerror GetRepository(String[] arg) throws Exception {

        if (arg == null || arg.length == 0 || arg[0].equals("-a")) {
            return new RepositoryApi();
        }

        if (arg.length == 2 && arg[0].equals("-f")) {
            return new RepositoryFile(arg[1]);
        }

        if (arg.length == 1 && arg[0].equals("-f")) {

            File[] files = new File(PropertyService.PathTempFile).listFiles();
            Arrays.sort(files, Comparator.comparingLong(File::lastModified).reversed());

            if (files.length>0)
                return new RepositoryFile(files[0].getAbsolutePath());

//            try (Stream<Path> paths = Files.walk(Paths.get(PropertyService.PathTempFile))) {
//                List<String> files = paths.filter(Files::isRegularFile).map(Path::toString).collect(Collectors.toList());

              //  if (!files.isEmpty())
              //      return new RepositoryFile(files.get(0));

//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }

        throw new Exception("Wrong args");
    }
}