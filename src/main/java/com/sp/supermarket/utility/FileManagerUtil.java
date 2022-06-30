package com.sp.supermarket.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * File Manager utility class
 * @author Waleed Naveed
 * 23/6/22
 */
public class FileManagerUtil {
    public static List<String> getFileContent(String path) throws FileNotFoundException {


        List<String> lines = new ArrayList<>();
            URL url = FileManagerUtil.class.getClassLoader().getResource(path) ;
            String filePath = "";
            if(url != null){
                filePath = url.getFile();
            }
            File myObj = new File(filePath);
            Scanner scanner = new Scanner(myObj);
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
            scanner.close();

        return lines;
    }
}
