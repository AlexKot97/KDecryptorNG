package su.kotindustries.kdecryptorng;

public class Main {

    private static IO io;

    public static void main(String[] args) {
        String[] asDictionary;
        String[] asMasks;
        io = new IO();

        asMasks = loadMasks();
        asDictionary = loadDictionary(args);

        MrBrute mb = new MrBrute(asMasks, asDictionary);

        io.println();
        io.print("Working...");
        for (int j = 0; j < mb.getDictionarySize(); j++) {
            mb.checkWord(j);
        }
        io.println(" Done");

        int iMatchCount1 = mb.getMatchCount(0);
        int iMatchCount2 = mb.getMatchCount(1);

        String sSign1 = mb.getSignature(0);
        String sSign2 = mb.getSignature(1);

        int iWordsCount = mb.getDictionarySize();

        io.println();
        io.println("            STATISTICS           ");
        io.println("Dictionary power, words: " + iWordsCount + ", matches found: " + iMatchCount1 + ", " + iMatchCount2);
        io.println();
        io.println("              MATCHES            ");

        io.println("      Mask1: " + asMasks[0] + "      Mask2: " + asMasks[1]);
        io.println(" Signature1: " + sSign1 + " Signature2: " + sSign2);

        mb.generateMatrixArray();
        String[][] lsResult = mb.getMatrixArray();

        for (String[] sLine : lsResult) {
            io.print("      Word1: " + mb.decryptWord(asMasks[0], sLine) + "      Word2: " + mb.decryptWord(asMasks[1], sLine));
            io.println("");
        }
        io.println("Total: " + mb.getCombinationsCountTotal() + " match: " + mb.getCombinationsCountMatch());
        io.println();
    }

    private static String[] loadMasks(){
        io.println("Write here a masks, splits by a space: ");
        String sInput = io.readLine();
        return sInput.split("\\s");
    }

    private static String[] loadDictionary(String[] args) {
        String sDictionaryFileName = "default.dic";
        if (args.length > 1)
            if (io.dir(args[0]))
                sDictionaryFileName = args[0];
        io.println("Dictionary path: " + sDictionaryFileName);

        return io.loadDictionary(sDictionaryFileName);
    }

}