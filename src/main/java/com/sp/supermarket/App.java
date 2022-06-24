package com.sp.supermarket;

import com.sp.supermarket.utility.FileReader;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


public class App 
{

    public static void main( String[] args ) {


        if(args.length == 1 ) {
            System.out.println("Interactive mode");

            //getting file inventory
            getFileContent(args[0]).forEach(System.out::println);

        } else if (args.length == 2){
            System.out.println("File mode");
            //getting file inventory
            getFileContent(args[0]).forEach(System.out::println);
            //getting file command.txt
            getFileContent(args[1]).forEach(System.out::println);


        } else {
            System.out.println("Wrong argument is provided");
        }

    }

    private static List<String> getFileContent(String fileName) {
        FileReader fileReader = new FileReader();

        List<String> lines = new ArrayList<>();
        try {
            lines = fileReader.getFileContent(fileName);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return lines;
    }


}
