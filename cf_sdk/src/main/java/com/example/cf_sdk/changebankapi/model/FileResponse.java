package com.example.cf_sdk.changebankapi.model;

import java.io.File;

/**
 * Used to convert response as file
 */

public class FileResponse {

    private File mFile;

    private String mFileName="";

    public FileResponse(File file, String fileName) {
        mFile = file;
        mFileName = fileName;
    }


    public static FileResponse Create(File file, String fileName){
        return new FileResponse(file, fileName);
    }

    public File getFile() {
        return mFile;
    }

    public String getFileName() {
        return mFileName;
    }

}
