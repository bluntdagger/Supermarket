package com.sp.supermarket;

import com.sp.supermarket.utility.FileReader;

import java.io.FileNotFoundException;
import java.util.List;


public class App 
{
    public static void main( String[] args ) {


        FileReader fileReader = new FileReader();
        if(args.length == 1 ) {
            System.out.println("Interactive mode");
            System.out.println(args[0]);
            List<String> lines = null;
            try {
                lines = fileReader.getFileContent(args[0]);
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            }
            lines.forEach(System.out::println);
        } else if (args.length == 2){
            System.out.println("File mode");
        } else {
            System.out.println("Wrong argument is provided");
        }




    }



}
