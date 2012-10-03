package gui.util;

import java.io.File;
import java.io.IOException;

public class FileUtil
{
    private FileUtil()
    {
    }

    public static File getFile(String filePath)
        throws IOException
    {
        File tempFile = new File(filePath);
        if (!tempFile.exists())
        {
            createFile(tempFile);
        }
        return tempFile;
    }

    public static boolean createFile(File fileToCreate)
        throws IOException
    {
        String canonicalPath = fileToCreate.getCanonicalPath();
        File fileDirectory = new File(canonicalPath.substring(0,
                                                              canonicalPath.lastIndexOf("/")));
        fileDirectory.mkdirs();
        return fileToCreate.createNewFile();
    }

    public static void stringCompare(String firstString,
                                     String secondString)
    {
        int stringSize = Math.min(firstString.length(),
                                  secondString.length());
        for (int i = 0; i < stringSize; i++)
        {
            System.out.println(firstString.charAt(i++) + " || " + secondString.charAt(i++));
        }
    }
}
