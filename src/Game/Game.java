package Game;

import java.util.Arrays;
import java.util.Random;

public class Game {

    private static final Random generator = new Random();
    private static final int[][] numbers = new int[4][4];


    public static void main(String[] args) {
        gameStart();


    }



    public static void gameStart() {
        System.out.println("   ПЯТНАШКИ");
        init();
        showGameField();
    }

    public static void init() {
        int[] myNumbers = new int[16];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                numbers[i][j] = 0;
                myNumbers[i * 4 + j] = 0;
            }
        }

        for (int i = 1; i < 16; i++) {
            int k;
            int l;
            do {
                k = generator.nextInt(100) % 4;
                l = generator.nextInt(100) % 4;
            }
            while (numbers[k][l] != 0);
            numbers[k][l] = i;
            myNumbers[k * 4 + l] = i;
        }

        boolean change = true;
        int counter = 1;
        while (change) {
            change = false;
            for (int i = 0; i < 16; i++) {
                if (myNumbers[i] != i) {
                    for (int j = 0; j < 16; j++) {
                        if (myNumbers[j] == i) {
                            int temp = myNumbers[i];
                            myNumbers[i] = myNumbers[j];
                            myNumbers[j] = temp;
                            change = true;
                            counter++;
                            break;
                        }
                    }
                    break;
                }
            }
        }
        if (counter % 2 != 0) {
            int temp = numbers[0][0];
            numbers[0][0] = numbers[3][3];
            numbers[3][3] = temp;
        }

    }

    public static void change(int num) {
        int i = 0;
        int j = 0;
        for (int k = 0; k < 4; k++) {
            for (int l = 0; l < 4; l++) {
                if (numbers[k][l] == num) {
                    i = k;
                    j = l;
                }
            }
        }

        if (i > 0){
            if (numbers[i - 1][j] == 0) {
                numbers[i - 1][j] = num;
                numbers[i][j] = 0;
            }
        }

        if (i < 3) {
            if (numbers[i + 1][j] == 0) {
                numbers[i + 1][j] = num;
                numbers[i][j] = 0;
            }
        }

        if (j > 0) {
            if (numbers[i][j - 1] == 0) {
                numbers[i][j - 1] = num;
                numbers[i][j] = 0;
            }
        }

        if (j < 3) {
            if (numbers[i][j + 1] == 0) {
                numbers[i][j + 1] = num;
                numbers[i][j] = 0;
            }
        }
    }


        public boolean checkWin() {
            boolean status = true;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (i == 3 && j > 2)
                        break;
                    if (numbers[i][j] != i * 4 + j + 1) {
                        status = false;
                    }
                }
            }
            return status;
        }

    public static void showGameField() {
            System.out.println();
            System.out.println(Arrays.toString(Game.numbers[0]));
            System.out.println(Arrays.toString(Game.numbers[1]));
            System.out.println(Arrays.toString(Game.numbers[2]));
            System.out.println(Arrays.toString(Game.numbers[3]));
            System.out.println();

        }

}


