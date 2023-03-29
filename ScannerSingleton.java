import java.util.Scanner;

public class ScannerSingleton {

    private static ScannerSingleton instance = null;
    private Scanner sc;

    /**
     * Scanner Constructor
     */
    private ScannerSingleton() {
        sc = new Scanner(System.in);
    }

    /**
     * If no scanner object exist it will create one, if it exists it will re-use existing scanner object
     * @return
     */
    public static ScannerSingleton getInstance() {
        if (instance == null) {
            instance = new ScannerSingleton();
        }
        return instance;
    }

    /**
     * Gets next user input line
     * @return
     */
    public String nextLine() {
        return sc.nextLine();
    }

    /**
     * Gets next user input string
     * @return
     */
    public String next() {
        return sc.next();
    }

    /**
     * Gets next user input integer
     * @return
     */
    public int nextInt() {
        return sc.nextInt();
    }

    /**
     * Gets next user input double
     * @return
     */
    public double nextDouble() {
        return sc.nextDouble();
    }

    /**
     * Gets next user input boolean
     * @return
     */
    public boolean nextBoolean() {
        return sc.nextBoolean();
    }
}