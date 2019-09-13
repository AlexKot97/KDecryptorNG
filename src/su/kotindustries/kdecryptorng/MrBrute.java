package su.kotindustries.kdecryptorng;

import java.util.*;

class MrBrute {
    private Mask[] amMasks;
    private String[] asDictionary;
    private int iDictionarySize;

    private String[][] aasMatrix;

    private int iCombinationsCountTotal, iCombinationsCountMatch;

    MrBrute(String[] asMasks, String[] asDictionary) {
        amMasks = new Mask[asMasks.length];
        int j = 0;
        for (String sMask : asMasks)
            amMasks[j++] = new Mask(sMask);
        this.asDictionary = asDictionary;
        this.iDictionarySize = asDictionary.length;
    }

    void checkWord(int iWord) {
        amMasks[0].checkMatch(asDictionary[iWord]);
        amMasks[1].checkMatch(asDictionary[iWord]);
    }

    String[][] getMatrixArray() {
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

    void generateMatrixArray() {
        List<String[]> lasMatrix = new ArrayList<>();

        String[][] asMatrixArrayA = amMasks[0].getMatrixArray();
        String[][] asMatrixArrayB = amMasks[1].getMatrixArray();
        iCombinationsCountTotal = 0;
        iCombinationsCountMatch = 0;
        for (String[] asMatrixA : asMatrixArrayA)
            for (String[] asMatrixB : asMatrixArrayB) {
                if (!matrixIsCollision(asMatrixA, asMatrixB)) {
                    lasMatrix.add(matrixConcat(asMatrixA, asMatrixB));
                    iCombinationsCountTotal++;
                }
                iCombinationsCountMatch++;
            }
        aasMatrix = lasMatrix.toArray(new String[0][0]);
    }

    String decryptWord(String sWord, String[] asDictionary) {
        StringBuilder sResult = new StringBuilder();
        for (int i = 0; i < sWord.length(); i++)
            for (String sLetter : asDictionary) {
                if (sWord.substring(i, i + 1).compareTo(sLetter.substring(1, 2)) == 0) {
                    sResult.append(sLetter, 0, 1);
                }
            }
        return sResult.toString();
    }

    private boolean matrixIsCollision(String[] asMatrixA, String[] asMatrixB) {
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
                    break;
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

    int getCombinationsCountTotal() {
        return iCombinationsCountTotal;
    }

    int getCombinationsCountMatch() {
        return iCombinationsCountMatch;
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