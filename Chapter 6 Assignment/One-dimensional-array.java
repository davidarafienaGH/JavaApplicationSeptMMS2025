// a) Set the 10 elements of integer array counts to zero.
int[] counts = new int[10]; // Initialized to zero by default in Java

// b) Add one to each of the 15 elements of integer array bonus.
for (int i = 0; i < bonus.length; i++) {
    bonus[i] += 1;
}

// c) Display the five values of integer array bestScores in column format.
for (int score : bestScores) {
    System.out.println(score);
}