package com.sp.supermarket;

import com.sp.supermarket.service.Supermarket;
import com.sp.supermarket.utility.FileReader;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


public class App 
{

    public static void main( String[] args ) {

        Supermarket supermarket = new Supermarket();
        if(args.length == 1 ) {
            supermarket.runInteractiveMode(args[0]);
        } else if (args.length == 2){
            supermarket.runFileMode(args[0],args[1]);

        } else {
            System.out.println("Wrong argument is provided");
        }

    }


}
