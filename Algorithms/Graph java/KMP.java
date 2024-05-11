class StringMatching {
    String t;
    String p;
    int[] pi;

    StringMatching(String t, String p) {
        this.t = t;
        this.p = p;
        pi = new int[p.length()];
        computePi();
    }

    public void computePi() {
        int k = 0;
        pi[0] = 0; // No need to write in Java

        for (int q = 1; q < p.length(); q++) {
            while (k > 0 && p.charAt(q) != p.charAt(k)) {
                k = pi[k - 1];
            }

            if (p.charAt(k) == p.charAt(q)) {
                k += 1;
            }

            pi[q] = k;
        }
    }

    public void printPi() {
        System.out.print("The pi array is: ");
        for (int value : pi) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    public void kmp() {
        int q = 0;
        for (int i = 0; i < t.length(); i++) {
            while (q > 0 && p.charAt(q) != t.charAt(i)) {
                q = pi[q - 1];
            }

            if (p.charAt(q) == t.charAt(i)) {
                q++;
            }

            if (q == p.length()) {
                System.out.println("Pattern starts at " + (i - p.length() + 1) + "-th Index..");
                q = pi[q - 1];
            }
        }
    }
}

public class KMP {
    public static void main(String[] args) {
        String t = "AABAACAADAABAABA";
        String p = "AABA";

        StringMatching sm = new StringMatching(t, p);
        sm.printPi();
        sm.kmp();
    }
}