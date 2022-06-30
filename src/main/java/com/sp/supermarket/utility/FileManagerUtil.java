package com.sp.supermarket.utility;

import com.sp.supermarket.service.Supermarket;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * File Manager utility class
 * @author Waleed Naveed
 * 23/6/22
 */
public class FileManagerUtil {


    /**
     * This file read file and convvert all lines to List of Strings
     * It is used to read inventory and commands
     * @param fileName
     * @return
     * @throws FileNotFoundException
     */
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

    /**
     * This method fetches the directory path
     * @return
     */
    public static String fetchSystemsPath(){
        File jarPath=new File(Supermarket.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        return jarPath.getParentFile().getParentFile().getParentFile().getAbsolutePath() + Constants.FILE_DIRECTORY_PATH;
    }


    /**
     * Flush file method is used to clear all previous file content if already existed in system
     * @param outputFileName
     */
    public static void flushFile(String outputFileName) {
        File outputFile = new File(FileManagerUtil.fetchSystemsPath()+outputFileName);
        if (outputFile.exists()){
            //flush
            PrintWriter writer = null;
            try {
                writer = new PrintWriter(outputFile);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            writer.print("");
            writer.close();
        }
    }

    /**
     * Method to create file with @param outputFileName if not existed, else it will create the file and append
     * with string and new line
     * @param response
     * @param outputFileName
     */
    public static void printFileLn(String response, String outputFileName) {
        try (FileOutputStream fos = new FileOutputStream(FileManagerUtil.fetchSystemsPath()+outputFileName, true)) {

            String text = response + "\n";
            byte[] mybytes = text.getBytes();

            fos.write(mybytes);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
