import java.io.OutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.io.InputStream;

public class LCPESY {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        LongestCommonPattern solver = new LongestCommonPattern();
        int testCount = Integer.parseInt(in.next());
        for (int i = 1; i <= testCount; i++)
            solver.solve(i, in, out);
        out.close();
    }
}

class LongestCommonPattern {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        char[] A = in.readString().toCharArray();
        char[] B = in.readString().toCharArray();

        int[] freqA = count(A);
        int res = 0;

        for (char c : B) {
            if (freqA[c] > 0) {
                freqA[c]--;
                res++;
            }
        }
        // int[] freqB = count(B);
        /*
         * for (int i = 0; i < freqA.length; ++i) { res += Math.min(freqA[i],
		 * freqB[i]); }
		 */
        out.println(res);
    }

    private int[] count(char[] s) {
        int[] freq = new int[256];
        for (char c : s) {
            ++freq[c];
        }
        return freq;
    }
}

class InputReader {
    private InputStream stream;
    private byte[] buffer = new byte[1024];
    private int curChar;
    private int numChars;

    public InputReader(InputStream stream) {
        this.stream = stream;
    }

    private int read() {
        if (numChars == -1)
            throw new InputMismatchException();
        if (curChar >= numChars) {
            curChar = 0;
            try {
                numChars = stream.read(buffer);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
            if (numChars <= 0)
                return -1;
        }
        return buffer[curChar++];
    }

    public String readString() {
        int c = read();
        while (isWhiteSpace(c))
            c = read();
        StringBuilder res = new StringBuilder();
        do {
            res.appendCodePoint(c);
            c = read();
        } while (!isWhiteSpace(c));
        return res.toString();
    }

    public String next() {
        return readString();
    }

    public int readInt() {
        int c = read();
        while (isWhiteSpace(c))
            c = read();
        int sign = 1;
        if (c == '-') {
            sign = -1;
            c = read();
        }
        int res = 0;
        do {
            if (c < '0' || c > '9')
                throw new InputMismatchException();
            res *= 10;
            res += c - '0';
            c = read();
        } while (!isWhiteSpace(c));
        return res * sign;
    }

    private boolean isWhiteSpace(int c) {
        return c == ' ' || c == '\n' || c == '\t' || c == '\r' || c == -1;
    }
}