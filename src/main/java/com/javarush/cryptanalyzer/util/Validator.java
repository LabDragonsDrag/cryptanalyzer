package com.javarush.cryptanalyzer.util;

import java.io.File;

public class Validator {

    public static boolean isFileNotEmptyOrExist(File file){
        return file != null && file.exists();
    }

}
