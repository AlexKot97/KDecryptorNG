package su.kotindustries.kdecryptorng;

import java.util.ArrayList;
import java.util.List;

class MrBrute {
    private List<String> lsMatch;
    private String[] asDictionary;
    private String sTargetSignature;
    private int iDictionarySize;

    MrBrute() {
        lsMatch = new ArrayList<>();
    }

    void setDictionary(String[] asDictionary) {
        this.asDictionary = asDictionary;
        this.iDictionarySize = asDictionary.length;
    }

    void setTarget(String sWord) {
        sTargetSignature = calcSignature(sWord);
    }

    String checkWord(int iWord) {
        int iProgress = Math.round(iWord * 100 / iDictionarySize);
        String sWordSignature = calcSignature(asDictionary[iWord]);
        String sReport = "Progress: " + iProgress;
        sReport += "% Id: " + iWord + "  Target signature: ";
        sReport += sTargetSignature + " Suspect: " + asDictionary[iWord];

        if (sWordSignature.compareTo(sTargetSignature) == 0)
            lsMatch.add(sReport);
        return sReport;
    }

    private String calcSignature(String sWord) {
        StringBuilder sResult = new StringBuilder();
        for (int j = 0; j < sWord.length(); j++)
            sResult.append(sWord.indexOf(sWord.substring(j, j + 1)));
        return sResult.toString();
    }

    List<String> getResult() {
        return lsMatch;
    }

    int getMatchCount() {
        return lsMatch.size();
    }

    int getWordsCount() {
        return iDictionarySize;
    }

    String getTargetSignature() {
        return sTargetSignature;
    }
}
