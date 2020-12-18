package RepositoryTerror;

import Interfaces.IRepositoryTerror;
import Service.PropertyService;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

public class FactoryRepository {
    public IRepositoryTerror GetRepository(String[] arg) throws Exception {

        if (arg == null || arg.length == 0 || arg[0].equals("-a")) {
            return new RepositoryApi();
        }

        if (arg.length == 2 && arg[0].equals("-f")) {
            String fileName = arg[1];
            if (new File(fileName).exists()) {
                return new RepositoryFile(fileName);
            } else {
                throw new Exception("Not found file " + fileName);
            }
        }

        if (arg.length == 1 && arg[0].equals("-f")) {

            File[] files = new File(PropertyService.PathTempFile).listFiles();
            Arrays.sort(files, Comparator.comparingLong(File::lastModified).reversed());

            if (files.length > 0)
                return new RepositoryFile(files[0].getAbsolutePath());
            else {
                throw new Exception("Not found file in folder " + PropertyService.PathTempFile);
            }
        }

        throw new Exception("Wrong args");
    }
}