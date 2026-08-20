import java.util.*;

public class ArrayListExample {

    public static void main(String[] args) {

        // =========================================================
        // 1. CREATING AN ARRAYLIST
        // =========================================================

        ArrayList<String> fruits = new ArrayList<>();

        // ArrayList with initial capacity
        ArrayList<Integer> numbers = new ArrayList<>(10);

        // Creating from another collection
        ArrayList<String> copiedFruits = new ArrayList<>(fruits);


        // =========================================================
        // 2. ADDING ELEMENTS
        // =========================================================

        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Mango");

        System.out.println("After add(): " + fruits);

        // add(index, element)
        fruits.add(1, "Orange");

        System.out.println("After add(index, element): " + fruits);

        // addAll(Collection)
        ArrayList<String> moreFruits = new ArrayList<>();

        moreFruits.add("Grapes");
        moreFruits.add("Pineapple");

        fruits.addAll(moreFruits);

        System.out.println("After addAll(): " + fruits);

        // addAll(index, Collection)
        fruits.addAll(2, Arrays.asList("Watermelon", "Papaya"));

        System.out.println("After addAll(index, collection): " + fruits);


        // =========================================================
        // 3. ACCESSING ELEMENTS
        // =========================================================

        String firstFruit = fruits.get(0);

        System.out.println("get(0): " + firstFruit);

        // getFirst() and getLast()
        // Available in modern Java versions
        System.out.println("First: " + fruits.getFirst());
        System.out.println("Last: " + fruits.getLast());


        // =========================================================
        // 4. MODIFYING ELEMENTS
        // =========================================================

        fruits.set(0, "Strawberry");

        System.out.println("After set(): " + fruits);


        // =========================================================
        // 5. CHECKING ELEMENTS
        // =========================================================

        System.out.println("Contains Mango? "
                + fruits.contains("Mango"));

        System.out.println("Contains Apple? "
                + fruits.contains("Apple"));

        System.out.println("Is empty? "
                + fruits.isEmpty());


        // =========================================================
        // 6. FINDING INDEX
        // =========================================================

        System.out.println("Index of Mango: "
                + fruits.indexOf("Mango"));

        System.out.println("Last index of Mango: "
                + fruits.lastIndexOf("Mango"));


        // =========================================================
        // 7. REMOVING ELEMENTS
        // =========================================================

        fruits.remove("Mango");

        System.out.println("After remove(object): " + fruits);

        fruits.remove(0);

        System.out.println("After remove(index): " + fruits);


        // =========================================================
        // IMPORTANT:
        // remove(int) vs remove(Integer)
        // =========================================================

        ArrayList<Integer> nums = new ArrayList<>();

        nums.add(10);
        nums.add(20);
        nums.add(30);

        // Removes element at index 1
        nums.remove(1);

        System.out.println("remove(1): " + nums);

        nums.add(20);

        // Removes object 20
        nums.remove(Integer.valueOf(20));

        System.out.println("remove(Integer.valueOf(20)): " + nums);


        // =========================================================
        // 8. REMOVE ALL ELEMENTS
        // =========================================================

        ArrayList<String> list1 = new ArrayList<>();

        list1.add("A");
        list1.add("B");
        list1.add("C");
        list1.add("D");

        list1.clear();

        System.out.println("After clear(): " + list1);


        // =========================================================
        // 9. SIZE
        // =========================================================

        ArrayList<String> names = new ArrayList<>();

        names.add("Rahul");
        names.add("Amit");
        names.add("Raj");

        System.out.println("Size: " + names.size());


        // =========================================================
        // 10. ITERATING ARRAYLIST
        // =========================================================

        ArrayList<String> languages = new ArrayList<>();

        languages.add("Java");
        languages.add("Python");
        languages.add("JavaScript");
        languages.add("C++");

        // Traditional for loop
        System.out.println("\nTraditional for loop:");

        for (int i = 0; i < languages.size(); i++) {
            System.out.println(languages.get(i));
        }

        // Enhanced for loop
        System.out.println("\nEnhanced for loop:");

        for (String language : languages) {
            System.out.println(language);
        }

        // forEach()
        System.out.println("\nforEach():");

        languages.forEach(language -> System.out.println(language));


        // =========================================================
        // 11. ITERATOR
        // =========================================================

        System.out.println("\nIterator:");

        Iterator<String> iterator = languages.iterator();

        while (iterator.hasNext()) {
            String language = iterator.next();
            System.out.println(language);
        }


        // =========================================================
        // 12. LISTITERATOR
        // =========================================================

        System.out.println("\nListIterator:");

        ListIterator<String> listIterator =
                languages.listIterator();

        while (listIterator.hasNext()) {
            System.out.println(listIterator.next());
        }


        // Reverse iteration
        System.out.println("\nReverse iteration:");

        while (listIterator.hasPrevious()) {
            System.out.println(listIterator.previous());
        }


        // =========================================================
        // 13. SORTING
        // =========================================================

        ArrayList<Integer> scores = new ArrayList<>(
                Arrays.asList(50, 10, 90, 30, 70)
        );

        System.out.println("\nOriginal: " + scores);

        // Ascending
        Collections.sort(scores);

        System.out.println("Ascending: " + scores);

        // Descending
        Collections.sort(scores, Collections.reverseOrder());

        System.out.println("Descending: " + scores);

        // Using sort()
        scores.sort(Comparator.naturalOrder());

        System.out.println("Natural order: " + scores);

        scores.sort(Comparator.reverseOrder());

        System.out.println("Reverse order: " + scores);


        // =========================================================
        // 14. REVERSE
        // =========================================================

        Collections.reverse(scores);

        System.out.println("After reverse(): " + scores);


        // =========================================================
        // 15. SHUFFLE
        // =========================================================

        Collections.shuffle(scores);

        System.out.println("After shuffle(): " + scores);


        // =========================================================
        // 16. MIN AND MAX
        // =========================================================

        System.out.println("Minimum: "
                + Collections.min(scores));

        System.out.println("Maximum: "
                + Collections.max(scores));


        // =========================================================
        // 17. FREQUENCY
        // =========================================================

        ArrayList<String> colors = new ArrayList<>(
                Arrays.asList(
                        "Red",
                        "Blue",
                        "Red",
                        "Green",
                        "Red"
                )
        );

        System.out.println("Frequency of Red: "
                + Collections.frequency(colors, "Red"));


        // =========================================================
        // 18. BINARY SEARCH
        // =========================================================

        ArrayList<Integer> sortedNumbers = new ArrayList<>(
                Arrays.asList(10, 20, 30, 40, 50)
        );

        int index = Collections.binarySearch(
                sortedNumbers,
                30
        );

        System.out.println("Index of 30: " + index);


        // =========================================================
        // 19. COPY
        // =========================================================

        ArrayList<String> source = new ArrayList<>(
                Arrays.asList("A", "B", "C")
        );

        ArrayList<String> destination =
                new ArrayList<>(Arrays.asList("", "", ""));

        Collections.copy(destination, source);

        System.out.println("After copy(): " + destination);


        // =========================================================
        // 20. FILL
        // =========================================================

        ArrayList<String> fillList = new ArrayList<>(
                Arrays.asList("A", "B", "C", "D")
        );

        Collections.fill(fillList, "X");

        System.out.println("After fill(): " + fillList);


        // =========================================================
        // 21. REPLACE ALL
        // =========================================================

        ArrayList<String> animals = new ArrayList<>(
                Arrays.asList(
                        "Cat",
                        "Dog",
                        "Cat",
                        "Lion"
                )
        );

        animals.replaceAll(
                animal -> animal.equals("Cat")
                        ? "Tiger"
                        : animal
        );

        System.out.println("After replaceAll(): " + animals);


        // =========================================================
        // 22. REMOVE IF
        // =========================================================

        ArrayList<Integer> values = new ArrayList<>(
                Arrays.asList(10, 15, 20, 25, 30, 35)
        );

        values.removeIf(value -> value % 2 == 0);

        System.out.println("After removeIf(): " + values);


        // =========================================================
        // 23. REMOVE ALL
        // =========================================================

        ArrayList<String> allFruits = new ArrayList<>(
                Arrays.asList(
                        "Apple",
                        "Banana",
                        "Mango",
                        "Apple",
                        "Orange"
                )
        );

        ArrayList<String> removeFruits =
                new ArrayList<>(
                        Arrays.asList("Apple", "Mango")
                );

        allFruits.removeAll(removeFruits);

        System.out.println("After removeAll(): " + allFruits);


        // =========================================================
        // 24. RETAIN ALL
        // =========================================================

        ArrayList<String> languages2 = new ArrayList<>(
                Arrays.asList(
                        "Java",
                        "Python",
                        "C++",
                        "JavaScript"
                )
        );

        ArrayList<String> required =
                new ArrayList<>(
                        Arrays.asList("Java", "Python")
                );

        languages2.retainAll(required);

        System.out.println("After retainAll(): " + languages2);


        // =========================================================
        // 25. CONTAINS ALL
        // =========================================================

        ArrayList<String> programmingLanguages =
                new ArrayList<>(
                        Arrays.asList(
                                "Java",
                                "Python",
                                "C++"
                        )
                );

        boolean containsAll =
                programmingLanguages.containsAll(
                        Arrays.asList("Java", "Python")
                );

        System.out.println("Contains all? " + containsAll);


        // =========================================================
        // 26. SUBLIST
        // =========================================================

        ArrayList<Integer> numbers2 = new ArrayList<>(
                Arrays.asList(
                        10, 20, 30, 40, 50
                )
        );

        List<Integer> subList =
                numbers2.subList(1, 4);

        System.out.println("SubList: " + subList);


        // =========================================================
        // 27. CONVERT ARRAYLIST TO ARRAY
        // =========================================================

        ArrayList<String> tech = new ArrayList<>(
                Arrays.asList(
                        "Java",
                        "Spring",
                        "React"
                )
        );

        String[] array =
                tech.toArray(new String[0]);

        System.out.println(
                "Array: " + Arrays.toString(array)
        );


        // =========================================================
        // 28. CONVERT ARRAY TO ARRAYLIST
        // =========================================================

        String[] programming =
                {"Java", "Python", "C++"};

        ArrayList<String> programmingList =
                new ArrayList<>(
                        Arrays.asList(programming)
                );

        System.out.println(
                "ArrayList: " + programmingList
        );


        // =========================================================
        // 29. JAVA 9 - List.of()
        // =========================================================

        List<String> immutableList =
                List.of("Java", "Spring", "React");

        System.out.println("List.of(): " + immutableList);

        // immutableList.add("Docker");
        // This will throw UnsupportedOperationException


        // =========================================================
        // 30. COPYING IMMUTABLE LIST INTO ARRAYLIST
        // =========================================================

        ArrayList<String> mutableList =
                new ArrayList<>(
                        List.of("Java", "Spring", "React")
                );

        mutableList.add("Docker");

        System.out.println(
                "Mutable list: " + mutableList
        );


        // =========================================================
        // 31. ENSURE CAPACITY
        // =========================================================

        ArrayList<Integer> capacityList =
                new ArrayList<>();

        capacityList.ensureCapacity(100);

        capacityList.add(10);
        capacityList.add(20);

        System.out.println(
                "Capacity list: " + capacityList
        );


        // =========================================================
        // 32. TRIM TO SIZE
        // =========================================================

        capacityList.trimToSize();

        System.out.println(
                "After trimToSize(): " + capacityList
        );


        // =========================================================
        // 33. CLONE
        // =========================================================

        ArrayList<String> original =
                new ArrayList<>(
                        Arrays.asList("A", "B", "C")
                );

        ArrayList<String> cloned =
                (ArrayList<String>) original.clone();

        System.out.println("Original: " + original);
        System.out.println("Cloned: " + cloned);


        // =========================================================
        // 34. EQUALS
        // =========================================================

        ArrayList<Integer> listA =
                new ArrayList<>(
                        Arrays.asList(1, 2, 3)
                );

        ArrayList<Integer> listB =
                new ArrayList<>(
                        Arrays.asList(1, 2, 3)
                );

        System.out.println(
                "Lists equal? " + listA.equals(listB)
        );


        // =========================================================
        // 35. HASHCODE
        // =========================================================

        System.out.println(
                "HashCode: " + listA.hashCode()
        );


        // =========================================================
        // 36. SPLITERATOR
        // =========================================================

        ArrayList<String> cities =
                new ArrayList<>(
                        Arrays.asList(
                                "Delhi",
                                "Mumbai",
                                "Lucknow",
                                "Pune"
                        )
                );

        Spliterator<String> spliterator =
                cities.spliterator();

        System.out.println("\nSpliterator:");

        spliterator.forEachRemaining(
                city -> System.out.println(city)
        );


        // =========================================================
        // 37. STREAM
        // =========================================================

        ArrayList<Integer> streamNumbers =
                new ArrayList<>(
                        Arrays.asList(
                                10, 15, 20, 25, 30
                        )
                );

        System.out.println("\nEven numbers:");

        streamNumbers.stream()
                .filter(n -> n % 2 == 0)
                .forEach(System.out::println);


        // =========================================================
        // 38. STREAM MAP
        // =========================================================

        System.out.println("\nSquared numbers:");

        streamNumbers.stream()
                .map(n -> n * n)
                .forEach(System.out::println);


        // =========================================================
        // 39. STREAM COLLECT
        // =========================================================

        List<Integer> evenNumbers =
                streamNumbers.stream()
                        .filter(n -> n % 2 == 0)
                        .toList();

        System.out.println(
                "Collected even numbers: "
                        + evenNumbers
        );


        // =========================================================
        // 40. STREAM COUNT
        // =========================================================

        long count =
                streamNumbers.stream()
                        .filter(n -> n > 20)
                        .count();

        System.out.println(
                "Numbers greater than 20: " + count
        );


        // =========================================================
        // 41. STREAM ANY MATCH
        // =========================================================

        boolean hasGreaterThan25 =
                streamNumbers.stream()
                        .anyMatch(n -> n > 25);

        System.out.println(
                "Any number > 25? "
                        + hasGreaterThan25
        );


        // =========================================================
        // 42. STREAM ALL MATCH
        // =========================================================

        boolean allPositive =
                streamNumbers.stream()
                        .allMatch(n -> n > 0);

        System.out.println(
                "All positive? " + allPositive
        );


        // =========================================================
        // 43. STREAM NONE MATCH
        // =========================================================

        boolean noneNegative =
                streamNumbers.stream()
                        .noneMatch(n -> n < 0);

        System.out.println(
                "No negative numbers? "
                        + noneNegative
        );


        // =========================================================
        // 44. STREAM MIN
        // =========================================================

        Optional<Integer> minimum =
                streamNumbers.stream()
                        .min(Integer::compareTo);

        System.out.println(
                "Stream minimum: " + minimum.orElse(null)
        );


        // =========================================================
        // 45. STREAM MAX
        // =========================================================

        Optional<Integer> maximum =
                streamNumbers.stream()
                        .max(Integer::compareTo);

        System.out.println(
                "Stream maximum: " + maximum.orElse(null)
        );


        // =========================================================
        // 46. STREAM REDUCE
        // =========================================================

        int sum =
                streamNumbers.stream()
                        .reduce(0, Integer::sum);

        System.out.println(
                "Sum using reduce(): " + sum
        );


        // =========================================================
        // 47. SORTING OBJECTS USING COMPARATOR
        // =========================================================

        ArrayList<Student> students =
                new ArrayList<>();

        students.add(
                new Student("Rahul", 85)
        );

        students.add(
                new Student("Amit", 95)
        );

        students.add(
                new Student("Raj", 75)
        );

        // Sort by marks
        students.sort(
                Comparator.comparingInt(Student::getMarks)
        );

        System.out.println(
                "\nStudents sorted by marks:"
        );

        students.forEach(System.out::println);


        // Sort descending
        students.sort(
                Comparator.comparingInt(Student::getMarks)
                        .reversed()
        );

        System.out.println(
                "\nStudents sorted by marks descending:"
        );

        students.forEach(System.out::println);


        // Sort by name
        students.sort(
                Comparator.comparing(Student::getName)
        );

        System.out.println(
                "\nStudents sorted by name:"
        );

        students.forEach(System.out::println);
    }


    // =============================================================
    // STUDENT CLASS
    // =============================================================

    static class Student {

        private String name;
        private int marks;

        public Student(String name, int marks) {
            this.name = name;
            this.marks = marks;
        }

        public String getName() {
            return name;
        }

        public int getMarks() {
            return marks;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", marks=" + marks +
                    '}';
        }
    }
}

