package com.sp.supermarket.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {
    public  List<String> getFileContent(String path) throws FileNotFoundException {

        List<String> lines = new ArrayList<>();
            URL url = getClass().getClassLoader().getResource(path) ;
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
