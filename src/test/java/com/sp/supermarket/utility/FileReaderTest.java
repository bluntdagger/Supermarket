package com.sp.supermarket.utility;

import org.junit.Test;

import java.io.FileNotFoundException;

public class FileReaderTest {

    @Test(expected = FileNotFoundException.class)
    public void getFileContentInvalidTest(){
        FileReader fileReader = new FileReader();
        fileReader.getFileContent("sadapay.txt");
    }
}
