package com.sp.supermarket.utility;


import org.junit.Test;

import java.io.FileNotFoundException;

public class FileReaderTest {


    @Test(expected = FileNotFoundException.class)
    public void testGetContentException() throws FileNotFoundException {
        FileReader.getFileContent("sadapay.txt");
    }
}
