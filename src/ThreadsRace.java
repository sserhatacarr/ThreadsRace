import java.util.ArrayList;
import java.util.List;

public class ThreadsRace { // gpt yöntemi incelenecek
    public static void main(String[] args) {
        // 1'den 10000'e kadar sayılardan oluşan bir ArrayList oluşturun
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 10000; i++) {
            numbers.add(i);
        }

        // 4 eşit parçaya ayırın
        List<List<Integer>> partitions = new ArrayList<>();
        int partitionSize = numbers.size() / 4;
        int fromIndex = 0;
        int toIndex = partitionSize;
        for (int i = 0; i < 4; i++) {
            if (i == 3) {
                // Son parçaya kalan elemanları ekle
                partitions.add(numbers.subList(fromIndex, numbers.size()));
            } else {
                partitions.add(numbers.subList(fromIndex, toIndex));
            }
            fromIndex = toIndex;
            toIndex += partitionSize;
        }

        // 4 ayrı Thread tasarlayın
        List<Thread> threads = new ArrayList<>();
        List<List<Integer>> evenLists = new ArrayList<>();
        List<List<Integer>> oddLists = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            final int index = i;
            Thread thread = new Thread(() -> { // bu döngü mantığını çalış ve synchronized ile ilgili kısımları incele
                List<Integer> subList = partitions.get(index);
                List<Integer> evenNumbers = new ArrayList<>();
                List<Integer> oddNumbers = new ArrayList<>();
                for (Integer num : subList) {
                    if (num % 2 == 0) {
                        evenNumbers.add(num);
                    } else {
                        oddNumbers.add(num);
                    }
                }
                synchronized (evenLists) {
                    evenLists.add(evenNumbers);
                }
                synchronized (oddLists) {
                    oddLists.add(oddNumbers);
                }
            });
            threads.add(thread);
            thread.start();
        }

        // Bekleyin
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Sonuçları birleştirin
        List<Integer> allEvenNumbers = new ArrayList<>();
        List<Integer> allOddNumbers = new ArrayList<>();

        for (List<Integer> evenList : evenLists) {
            allEvenNumbers.addAll(evenList);
        }

        for (List<Integer> oddList : oddLists) {
            allOddNumbers.addAll(oddList);
        }

        // Çift ve tek sayıları yazdırın
        System.out.println("Çift Sayılar: " + allEvenNumbers);
        System.out.println("Tek Sayılar: " + allOddNumbers);
    }
}
