package su.kotindustries.kdecryptorng;

import java.util.ArrayList;
import java.util.List;

class MrBrute {
    private List<String> lsMatch;
    private String[] asDictionary;
    private String sTargetSignature;

    void setDictionary(String[] asDictionary) {
        this.asDictionary = asDictionary;
    }

    void setTarget(String sWord) {
        sTargetSignature = calcSignature(sWord);
    }

    void initMatch() {
        lsMatch = new ArrayList<>();
    }

    String checkWord(int iWord) {
        String sWordSignature = calcSignature(asDictionary[iWord]);
        String sReport = "Id: " + iWord + "  Target signature: " + sTargetSignature + " Suspect: " + asDictionary[iWord];
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
        return asDictionary.length;
    }

    String getTargetSignature() {
        return sTargetSignature;
    }


}
