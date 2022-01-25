
/**
 * The test class where I create a bunch of arrays with different sizes and recursive limit to run through the
 * quicksort and calculate the runtime.
 **/
public class TestFile {
    Integer[] mArray;

    /**
     * The main of this class
     * @param arg
     * */
    public static void main(String arg[]) {

        int size = 20000;
        FHsort test1 = new FHsort();

        //creates 20 cases
        for (int j = 0; j < 20; j++) {

            TestFile array1 = new TestFile(size);

            System.out.println("The array size of " + array1.mArray.length);
            //run each case 3 times
            for (int k = 0; k < 3; k++) {
                Integer[] copyOfArray1 = array1.mArray;
                System.out.println("Run " + (k + 1));
                //runs different recursion limits from 2 to 300
                for (int i = 2; i <= 300; i += 2) {
                    test1.setRecursionLimit(i);
                    long start = System.currentTimeMillis();
                    test1.quickSort(copyOfArray1);
                    long end = System.currentTimeMillis();
                    System.out.println(i + "," + (end - start));
                }
            }
            size += 700000;
        }

    }

    /**
     * Constructor that help to create the arrays with different sizes and data
     * @param size
     * */
    TestFile(int size) {
        mArray = new Integer[size];
        for (int i = 0; i < mArray.length; i++) {
            int newInt = (int) (Math.random() * 10000);
            mArray[i] = newInt;
        }
    }


}
