package com.sp.supermarket.utility;


import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.List;
import static org.junit.Assert.*;

public class FileManagerUtilTest {


    @Test
    public void testGetContent()  {
        try {
            List<String> lines = FileManagerUtil.getFileContent("TestInventory.csv");
            assertFalse(lines.isEmpty());

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Test(expected = FileNotFoundException.class)
    public void testGetContentNegative() throws FileNotFoundException {
        FileManagerUtil.getFileContent("sadapay.txt");

    }


}
