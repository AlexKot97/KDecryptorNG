package su.kotindustries.kdecryptorng;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        String[] asDictionary;
        String sDictionary;
        String[] asMasks = new String[2];

        List<String> lsRes1, lsRes2;


        IO io = new IO();
        MrBrute mb = new MrBrute();

        sDictionary = "default.dic";
        if (args.length > 1)
            if (io.dir(args[0]))
                sDictionary = args[0];
        io.print("Dictionary path: " + sDictionary);
        asDictionary = io.loadDictionary(sDictionary);
        mb.setDictionary(asDictionary);

        io.print("Enter a mask 1: ");
        asMasks[0] = io.readLine();
        mb.addMask(0, asMasks[0]);

        io.print("Enter a mask 2: ");
        asMasks[1] = io.readLine();
        mb.addMask(1, asMasks[1]);

        io.println();

        for (int j = 0; j < mb.getDictionarySize(); j++)
            io.print(mb.checkWord(j));

        lsRes1 = mb.getResult(0);
        lsRes2 = mb.getResult(1);

        int iMatchCount1 = mb.getMatchCount(0);
        int iMatchCount2 = mb.getMatchCount(1);
        int iWordsCount = mb.getDictionarySize();
        io.println();
        io.print("            STATISTICS           ");
        io.print("Dictionary power, words: " + iWordsCount + ", matches found: " + iMatchCount1 + ", " + iMatchCount2);
        io.println();
        io.print("              MATCHES            ");
        for (String sLine : lsRes1) {
            io.print(sLine);
        }
        io.println();
        for (String sLine : lsRes2) {
            io.print(sLine);
        }
        io.println();
    }
}