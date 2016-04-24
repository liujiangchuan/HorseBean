package com.ll.services.util;

import com.ll.services.FApplication;
import com.ll.services.helper.FLog;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FFileUtil
{
    /**
     * Get file content.
     *
     * @param name
     * @return String
     */
    public static String readAssetsFile(String name)
    {
        String res = "";
        try
        {
            InputStream in = FApplication.getAppContext().getResources().getAssets().open(name);

            int length = in.available();
            byte[] buffer = new byte[length];

            in.read(buffer);
            in.close();
            //			res = EncodingUtils.getString(buffer, "UTF-8");
            res = buffer.toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * Copy file from oldPath to newPath
     *
     * @param oldPath
     * @param newPath
     */
    public static void copyFile(String oldPath, String newPath)
    {
        try
        {
            int byteread = 0;
            File oldfile = new File(oldPath);
            File newfile = new File(newPath);
            if (newfile.exists())
            {
                newfile.delete();
            }
            newfile.createNewFile();
            if (oldfile.exists())
            {
                FileInputStream inStream = new FileInputStream(oldPath);
                FileOutputStream outStream = new FileOutputStream(newPath);
                byte[] buffer = new byte[1024];
                while ((byteread = inStream.read(buffer)) > 0)
                {
                    outStream.write(buffer, 0, byteread);
                }
                inStream.close();
                outStream.close();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Write data to file.
     *
     * @param fileName
     * @param message
     */
    public static void write2File(String fileName, String message, boolean append)
    {
        try
        {
            FileOutputStream outStream = new FileOutputStream(fileName, append);
            byte[] bytes = message.getBytes();
            outStream.write(bytes);
            outStream.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Read data from file.
     *
     * @param fileName
     * @return
     */
    public static String readFile(String fileName)
    {
        String ret = "";
        try
        {
            FileInputStream fin = new FileInputStream(fileName);
            int length = fin.available();
            byte[] buffer = new byte[length];
            fin.read(buffer);
            //			ret = EncodingUtils.getString(buffer, "UTF-8");
            ret = buffer.toString();
            fin.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * Delete file if exists.
     *
     * @param path
     */
    public static void deleteIfExists(String path)
    {
        File f = new File(path);
        if (f.exists())
        {
            f.delete();
        }
    }

    /**
     * Delete all files in the given folder.
     *
     * @param path
     */
    public static void deleteAllInFolder(String path)
    {
        File f = new File(path);
        deleteAllInFolder(f);
    }

    /**
     * Delete all files in the given folder.
     *
     * @param f
     */
    public static void deleteAllInFolder(File f)
    {
        if (f.exists())
        {
            if (f.isFile())
            {
                FLog.i("f.delete(): " + f.delete());
            }
            else if (f.isDirectory())
            {
                File[] childFile = f.listFiles();
                if (null != childFile && childFile.length != 0)
                {
                    for (File file : childFile)
                    {
                        deleteAllInFolder(file);
                    }
                }
            }
        }
    }

    /**
     * If exists.
     *
     * @param path
     * @return
     */
    public static boolean isFileExists(String path)
    {
        File f = new File(path);
        if (f.exists())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
