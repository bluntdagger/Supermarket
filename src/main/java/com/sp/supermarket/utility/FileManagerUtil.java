package com.sp.supermarket.utility;

import com.sp.supermarket.service.Supermarket;

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
    public static List<String> getFileContent(String fileName) throws FileNotFoundException {


        List<String> lines = new ArrayList<>();
        File myObj = new File(fetchSystemsPath()+fileName);


            Scanner scanner = new Scanner(myObj);
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
            scanner.close();

        return lines;
    }

    static String fetchSystemsPath(){
        File jarPath=new File(Supermarket.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        return jarPath.getParentFile().getParentFile().getParentFile().getAbsolutePath() + "/functional_spec/fixtures/";
    }


}
