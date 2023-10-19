import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Create an ArrayList containing numbers from 1 to 10,000.

        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 10000; i++) {
            numbers.add(i);
        }
        // Divide the 10,000 elements in this ArrayList into 4 equal parts of 2500 elements each.
        ArrayList<Integer> part1 = new ArrayList<>();
        ArrayList<Integer> part2 = new ArrayList<>();
        ArrayList<Integer> part3 = new ArrayList<>();
        ArrayList<Integer> part4 = new ArrayList<>();

        // Design 4 separate Threads to find even and odd numbers in these 4 separate 2500-element ArrayLists.
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < numbers.size() / 4; i++) {
                    if (numbers.get(i) % 2 == 0) {
                        part1.add(numbers.get(i));
                    }
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = numbers.size() / 4; i < numbers.size() / 2; i++) {
                    if (numbers.get(i) % 2 == 0) {
                        part2.add(numbers.get(i));
                    }
                }
            }
        });
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = numbers.size() / 2; i < (numbers.size() / 4) * 3; i++) {
                    if (numbers.get(i) % 2 == 0) {
                        part3.add(numbers.get(i));
                    }
                }
            }
        });
        Thread thread4 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = (numbers.size() / 4) * 3; i < numbers.size(); i++) {
                    if (numbers.get(i) % 2 == 0) {
                        part4.add(numbers.get(i));
                    }
                }
            }
        });
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Combine even numbers found by 4 Threads into a common ArrayList.
        ArrayList<Integer> evenNumbers = new ArrayList<>();
        evenNumbers.addAll(part1);
        evenNumbers.addAll(part2);
        evenNumbers.addAll(part3);
        evenNumbers.addAll(part4);

        // Design 4 separate Threads to find odd numbers in these 4 separate 2500-element ArrayLists.
        ArrayList<Integer> part5 = new ArrayList<>();
        ArrayList<Integer> part6 = new ArrayList<>();
        ArrayList<Integer> part7 = new ArrayList<>();
        ArrayList<Integer> part8 = new ArrayList<>();

        Thread thread5 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < numbers.size() / 4; i++) {
                    if (numbers.get(i) % 2 != 0) {
                        part5.add(numbers.get(i));
                    }
                }
            }
        });
        Thread thread6 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = numbers.size() / 4; i < numbers.size() / 2; i++) {
                    if (numbers.get(i) % 2 != 0) {
                        part6.add(numbers.get(i));
                    }
                }
            }
        });
        Thread thread7 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = numbers.size() / 2; i < (numbers.size() / 4) * 3; i++) {
                    if (numbers.get(i) % 2 != 0) {
                        part7.add(numbers.get(i));
                    }
                }
            }
        });
        Thread thread8 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = (numbers.size() / 4) * 3; i < numbers.size(); i++) {
                    if (numbers.get(i) % 2 != 0) {
                        part8.add(numbers.get(i));
                    }
                }
            }
        });
        thread5.start();
        thread6.start();
        thread7.start();
        thread8.start();
        try {
            thread5.join();
            thread6.join();
            thread7.join();
            thread8.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Combine odd numbers found by 4 Threads into a common ArrayList.
        ArrayList<Integer> oddNumbers = new ArrayList<>();
        oddNumbers.addAll(part5);
        oddNumbers.addAll(part6);
        oddNumbers.addAll(part7);
        oddNumbers.addAll(part8);

        System.out.println("Even numbers: " + evenNumbers);
        System.out.println("Odd numbers: " + oddNumbers);
    }
}
