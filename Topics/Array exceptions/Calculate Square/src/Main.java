class FixingExceptions {

    public static void calculateSquare(int[] array, int index) {
        // write your code here
        try {
            int result = (int) Math.pow(array[index],2);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("Exception!");
        }

    }
}