//package Sendit;

import javax.swing.*;
import java.io.IOException;
import java.io.*;

import java.util.Scanner;
public class ChooseFile
{
    JFileChooser fileChooser = new JFileChooser();
    StringBuilder sb = new StringBuilder();

    public File PickFile() throws IOException
    {
        if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
        {
         //get file
            return fileChooser.getSelectedFile();


        }else {return null;}

    }

}
