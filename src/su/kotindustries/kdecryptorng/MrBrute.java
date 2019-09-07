package su.kotindustries.kdecryptorng;

import java.util.*;

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

    String[][] getResult(Integer iIndex) {
        return amMasks[iIndex].getMatrixArray();
    }

    int getMatchCount(Integer iIndex) {
        return amMasks[iIndex].getMatrixCount();
    }

    int getDictionarySize() {
        return iDictionarySize;
    }

    String getSignature(int iIndex) {
        return amMasks[iIndex].getSignature();
    }

    static class Mask {
        private String sMask;
        private String sSignature;
        //private List<String> lsMatch;
        private List<String[]> lasMatrix;

        Mask(String sWord) {
            sMask = sWord;
            this.sSignature = calcSignature(sWord);
            //lsMatch = new ArrayList<>();
            lasMatrix = new ArrayList<>();
        }

        void checkMatch(String sWord) {
            String sWordSignature = calcSignature(sWord);
            if (sWordSignature.compareTo(sSignature) == 0)
                //lsMatch.add("Mask: " + sMask + " Signature: " + sSignature + " Word: " + sWord);
                //lsMatch.add(sMask + "|" + sWord);
                lasMatrix.add(calcMatrix(sWord));
        }

        private String calcSignature(String sWord) {
            StringBuilder sResult = new StringBuilder();
            for (int j = 0; j < sWord.length(); j++)
                sResult.append(sWord.indexOf(sWord.substring(j, j + 1)));
            return sResult.toString();
        }

        private String[] calcMatrix(String sWord) {
            List<String> lsResult = new ArrayList<>();
            for (int j = 0; j < sWord.length(); j++) {
                String Para = sWord.substring(j, j + 1) + sMask.substring(j, j + 1);
                if (!lsResult.contains(Para))
                    lsResult.add(Para);
            }
            Set<String> sQ = new LinkedHashSet<>(lsResult);
            return sQ.toArray(new String[0]);
        }

        String[][] getMatrixArray() {
            return lasMatrix.toArray(new String[0][0]);
        }

        int getMatrixCount() {
            return lasMatrix.size();
        }

        String getSignature() {
            return sSignature;
        }
/*
        int getMatchCount() {
            return lsMatch.size();
        }
        List<String> getMatches() {
            return lsMatch;
        }
*/
    }
}
