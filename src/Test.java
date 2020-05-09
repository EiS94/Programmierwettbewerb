public class Test {

    public static void main(String[] args) throws Exception{
        long start;
        long end;
        double total;
        final String labyrinth = Samples.createLabyrinth(1000,1000,10000, true);
        //final String labyrinth = Samples.createSnake(1000,1000,10000);
        Thread eulerTestThread = new Thread(new Runnable() {
            @Override
            public void run(){
                try {
                    EulerOhneEuler.main(new String[]{labyrinth});
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        start = System.nanoTime();
        eulerTestThread.start();
        while((System.nanoTime() - start <= 20000000000L) && eulerTestThread.isAlive()){

        }
        end = System.nanoTime();
        eulerTestThread.interrupt();
        total = (end - start)/ 1000000000.0;
        System.out.println("\n" + total + "s");
    }
}
