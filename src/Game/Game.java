package Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.Random;

public class Game {

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static final Random random = new Random();
    private static final int[][] numbers = new int[4][4];
    private static int enter;


    public static void main(String[] args) throws IOException {
        gameStart();

    }


    private static void gameStart() throws IOException {
        System.out.println("ПЯТНАШКИ");
        init();

        do {
            showGameField();
            System.out.println();
            System.out.println("Введите номер ячейки,который вы хотите переместить:");
            enter = Integer.parseInt(reader.readLine());
            change(enter);
            checkWin();
            if (checkWin()) {
                System.out.println("ПОЗДРАВЛЯЕМ, ВЫ ВЫИГРАЛИ!!!!!!");
            }
        }while (!checkWin());
        showGameField();

    }


    private static void init() throws IOException {
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
                k = random.nextInt(100) % 4;
                l = random.nextInt(100) % 4;
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
                            enter = temp;
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

    private static void change(int num) throws IOException {
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

        if (enter == numbers[i][j]) {
            if (i > 0) {//up
                if (numbers[i - 1][j] == 0) {
                    numbers[i - 1][j] = num;
                    numbers[i][j] = 0;
                }
            }

            if (i < 3) {//down
                if (numbers[i + 1][j] == 0) {
                    numbers[i + 1][j] = num;
                    numbers[i][j] = 0;
                }
            }

            if (j > 0) {//left
                if (numbers[i][j - 1] == 0) {
                    numbers[i][j - 1] = num;
                    numbers[i][j] = 0;
                }
            }

            if (j < 3) {//right
                if (numbers[i][j + 1] == 0) {
                    numbers[i][j + 1] = num;
                    numbers[i][j] = 0;
                }
            }
        }
    }


        private static boolean checkWin() {
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

    private static void showGameField() {
            System.out.println();

        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers.length; j++) {

                    if (numbers[i][j] < 10){
                        System.out.print("  " + numbers[i][j] + " ");
                    }
                    else if (numbers[i][j] >= 10) {
                        System.out.print( " " + numbers[i][j] + " ");
                }

            }
            System.out.println();
        }

        }
}


