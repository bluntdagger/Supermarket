package com.sp.supermarket.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * File reader utility class
 * @author Waleed Naveed
 * 23/6/22
 */
public class FileReader {
    public static List<String> getFileContent(String path) throws FileNotFoundException {

        System.out.println("file path");
        System.out.println(new File(".").getAbsolutePath());
        System.out.println("path");
        System.out.println(path);
        List<String> lines = new ArrayList<>();
            URL url = FileReader.class.getClassLoader().getResource(path) ;
            String filePath = "";
            if(url != null){
                filePath = url.getFile();
            }
            System.out.println(filePath);
            File myObj = new File(filePath);
            Scanner scanner = new Scanner(myObj);
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
            scanner.close();

        return lines;
    }
}
