public static void squareOfCharacters(int side, char fillCharacter) {
    for (int row = 0; row < side; row++) {
        for (int col = 0; col < side; col++) {
            System.out.print(fillCharacter);
        }
        System.out.println();
    }
}