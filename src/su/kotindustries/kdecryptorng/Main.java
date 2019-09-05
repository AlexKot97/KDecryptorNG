package su.kotindustries.kdecryptorng;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        String[] asDictionary;
        String sDictionary;
        String sTarget;
        String sTargetSignature;
        List<String> lsRes;

        IO io = new IO();
        MrBrute mb = new MrBrute();

        sDictionary = "rus.txt";
        if (args.length > 1)
            if (io.dir(args[0]))
                sDictionary = args[0];
        io.print("Dictionary path: " + sDictionary);
        asDictionary = io.loadDictionary(sDictionary);
        io.print("Enter a target mask: ");
        sTarget = io.readLine();

        io.println();
        mb.setDictionary(asDictionary);
        mb.setTarget(sTarget);

        for (int j = 0; j < mb.getWordsCount(); j++)
            io.print(mb.checkWord(j));

        lsRes = mb.getResult();
        int iMatchCount = mb.getMatchCount();
        int iWordsCount = mb.getWordsCount();
        sTargetSignature = mb.getTargetSignature();
        io.println();
        io.print("            STATISTICS           ");
        io.print("Dictionary power, words: " + iWordsCount + ", matches found: " + iMatchCount + ".");
        io.print("Target & Signature: " + sTarget + " : " + sTargetSignature);
        io.println();
        io.print("              MATCHES            ");
        for (String sLine : lsRes) {
            io.print(sLine);
        }
        io.println();
    }
}