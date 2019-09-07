package su.kotindustries.kdecryptorng;

import java.util.ArrayList;
import java.util.List;

class MrBrute {
    private Mask[] amMasks;
    private String[] asDictionary;
    private int iDictionarySize;

    MrBrute() {
        amMasks = new Mask[2];
    }

    void setDictionary(String[] asDictionary) {
        this.asDictionary = asDictionary;
        this.iDictionarySize = asDictionary.length;
    }

    void addMask(Integer iIndex, String sWord) {
        amMasks[iIndex] = new Mask(sWord);
    }

    void checkWord(int iWord) {
        //int iProgress = Math.round(iWord * 100 / iDictionarySize);
        amMasks[0].checkMatch(asDictionary[iWord]);
        amMasks[1].checkMatch(asDictionary[iWord]);
    }

    List<String> getResult(Integer iIndex) {
        return amMasks[iIndex].getMatches();
    }

    int getMatchCount(Integer iIndex) {
        return amMasks[iIndex].getMatchCount();
    }

    int getDictionarySize() {
        return iDictionarySize;
    }

    static class Mask {
        private String sMask;
        private String sSignature;
        private List<String> lsMatch;

        Mask(String sWord) {
            sMask = sWord;
            this.sSignature = calcSignature(sWord);
            lsMatch = new ArrayList<>();
        }

        void checkMatch(String sWord) {
            String sWordSignature = calcSignature(sWord);
            if (sWordSignature.compareTo(sSignature) == 0)
                lsMatch.add("Mask: " + sMask + " Signature: " + sSignature + " Word: " + sWord);
        }

        private String calcSignature(String sWord) {
            StringBuilder sResult = new StringBuilder();
            for (int j = 0; j < sWord.length(); j++)
                sResult.append(sWord.indexOf(sWord.substring(j, j + 1)));
            return sResult.toString();
        }

        List<String> getMatches(){
            return lsMatch;
        }

        int getMatchCount(){
            return lsMatch.size();
        }

    }
}
