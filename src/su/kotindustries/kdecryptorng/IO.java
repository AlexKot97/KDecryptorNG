package su.kotindustries.kdecryptorng;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

class IO {

    void print(String sString) {
        System.out.println(sString);
    }

    String readLine() {
        String sOut = "";
        BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
        try {
            sOut = buff.readLine();
        } catch (IOException e) {
            System.out.println("Input ERROR");
        }
        return sOut;
    }

    String[] loadDictionaryT(String sPathName) {
        return new String[]{"молоко", "кот", "инженерный", "инженерном", "шалава", "длинношеее", "Гражданская"};
    }

    String[] loadDictionary(String sPathName) {
        String[] asResult;

        List<String> lsDictionary;
        try {
            Path pPath = new File(sPathName).toPath();
            lsDictionary = Files.readAllLines(pPath);
        } catch (IOException e) {
            return new String[]{"Input/output exception", "File not exists"};
        }
        asResult = new String[lsDictionary.size()];
        for (int j = 0; j < lsDictionary.size(); j++) {
            asResult[j] = lsDictionary.get(j);
        }
        return asResult;
    }

    boolean dir(String sPath) {
        return new File(sPath).exists();
    }

    void println() {
        print("================================================================");
    }
}
