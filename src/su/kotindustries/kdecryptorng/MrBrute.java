package su.kotindustries.kdecryptorng;

import java.lang.reflect.Array;
import java.util.*;

class MrBrute {
    private Mask[] amMasks;
    private String[] asDictionary;
    private int iDictionarySize;

    private String[][] aasMatrix;
    private String[] asResultVariants;

    public int combinatesAll, combinatesNormal;

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

    String[][] getMatrixs() {
        return aasMatrix;
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

    void generateMatrixArray(int iIndexA, int iIndexB) {
        List<String[]> laasMatrix = new ArrayList<>();

        String[][] asMatrixsA = amMasks[iIndexA].getMatrixArray();
        String[][] asMatrixsB = amMasks[iIndexB].getMatrixArray();
        combinatesAll = 0;
        combinatesNormal = 0;
        for (String[] asMatrixA : asMatrixsA)
            for (String[] asMatrixB : asMatrixsB) {
                if (!isMatrixCollision(asMatrixA, asMatrixB)){
                    laasMatrix.add(matrixConcat(asMatrixA, asMatrixB));
                    combinatesNormal++;
                }

                combinatesAll++;
            }


        aasMatrix = laasMatrix.toArray(new String[0][0]);
    }

    String decryptWord(String sWord, String[] sDict){
        String sResult = "";
        for (int i = 0; i<sWord.length(); i++)
            for (String letter : sDict){
                if (sWord.substring(i, i+1).compareTo(letter.substring(1,2))==0){
                    sResult+=(letter.substring(0, 1));
                }
            }
        return sResult;
    }

    boolean isMatrixCollision(String[] asMatrixA, String[] asMatrixB) {
        boolean bCollision = false;
        for (String A : asMatrixA)
            for (String B : asMatrixB) {
                String a1 = A.substring(0, 1);
                String a2 = A.substring(1, 2);
                String b1 = B.substring(0, 1);
                String b2 = B.substring(1, 2);

                boolean B1 = (a1.compareTo(b1) == 0);
                boolean B2 = (a2.compareTo(b2) == 0);

                if (((B1) && (!B2)) || ((!B1) && (B2))) {
                    bCollision = true;
                }
            }
        return bCollision;
    }

    private String[] matrixConcat(String[] asMatrixA, String[] asMatrixB) {
        int iLenA = asMatrixA.length;
        int iLenB = asMatrixB.length;
        String[] asMatrixR = new String[iLenA + iLenB];
        System.arraycopy(asMatrixA, 0, asMatrixR, 0, iLenA);
        System.arraycopy(asMatrixB, 0, asMatrixR, iLenA, iLenB);
        Set<String> sRes = new LinkedHashSet<>(Arrays.asList(asMatrixR));
        return sRes.toArray(new String[0]);
    }

    static class Mask {
        private String sMask;
        private String sSignature;
        private List<String[]> lasMatrix;

        Mask(String sWord) {
            sMask = sWord;
            this.sSignature = calcSignature(sWord);
            lasMatrix = new ArrayList<>();
        }

        void checkMatch(String sWord) {
            String sWordSignature = calcSignature(sWord);
            if (sWordSignature.compareTo(sSignature) == 0)
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

    }
}
