import java.util.Scanner;

public class FindingInverseOfMatrix3by3 {

    // ===> ( 1st Part of Question )

    public static int mod(int matrix[][], int cofactorMatrix[][]) {
        int mod = 0;
        int i = 0;

        for (int j = 0; j < cofactorMatrix[i].length; j++) {
            int entry = matrix[i][j] * cofactorMatrix[i][j];
            mod += entry;
        }
        return mod;
    }

    public static int cofactorMod(int matrix[][], int Ai, int Aj) {
        int primProd = 1;
        int secProd = 1;
        int array2by2[][] = new int[2][2];

        int k = 0;
        for (int i = 0; i < matrix.length; i++) {
            int l = 0;
            for (int j = 0; j < matrix[i].length; j++) {
                if (i != Ai && j != Aj) {
                    array2by2[k][l] = matrix[i][j];
                    if (l == 1) {
                        k++;
                    }
                    l++;
                }
            }
        }

        for (int i = 0; i < array2by2.length; i++) {
            // primary diagonal
            primProd *= array2by2[i][i];
            // secondar diagonal
            int j = array2by2.length - 1 - i;
            secProd *= array2by2[i][j];
        }

        return primProd - secProd;
    }

    public static void cofactorsMatrix(int matrix[][], int cofactors[][]) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                int mod = cofactorMod(matrix, i, j);
                int pow = (i + 1) + (j + 1);
                int powRes = (int) Math.pow(-1, pow);
                cofactors[i][j] = powRes * mod;
                System.out.printf("Cofactor A(%d,%d) = %d\n", i + 1, j + 1, cofactors[i][j]);
            }
        }
    }

    public static void cofactorsMatrixTranspose(int transpose[][]) {
        for (int i = 0; i < transpose.length; i++) {
            for (int j = 0; j < transpose.length; j++) {
                if (i != j) {
                    for (int k = i; k < transpose.length; k++) {
                        for (int l = 0; l < transpose[k].length; l++) {
                            if ((i == l && j == k)) {
                                int temp = transpose[i][j];
                                transpose[i][j] = transpose[k][l];
                                transpose[k][l] = temp;
                            }
                        }
                    }
                }
            }
        }
    }

    public static boolean takingMatrixInverse(int matrix[][], String inverseArr[][]) {
        int cofactors[][] = new int[matrix.length][matrix[0].length];
        int transpose[][] = new int[matrix.length][matrix[0].length];

        cofactorsMatrix(matrix, cofactors);
        int mod = mod(matrix, cofactors);

        if (mod == 0) {
            return false;
        }

        for (int i = 0; i < cofactors.length; i++) {
            for (int j = 0; j < cofactors[i].length; j++) {
                transpose[i][j] = cofactors[i][j];
            }
        }
        cofactorsMatrixTranspose(transpose);

        for (int i = 0; i < transpose.length; i++) {
            for (int j = 0; j < transpose[i].length; j++) {
                if (transpose[i][j] % mod == 0) {
                    inverseArr[i][j] = "" + (transpose[i][j] / mod);
                } else {
                    inverseArr[i][j] = transpose[i][j] + "/" + mod;
                }
            }
        }

        System.out.println("\n\n:: Cofactors Array ::");
        displayArr(cofactors);

        System.out.println("\n\n=> Discriminant: " + mod);

        System.out.println("\n\n:: Adj. of Cofactors Array :: (Transpose)");
        displayArr(transpose);

        return true;
    }

    // ===> ( 2nd Part of Question )

    public static void multiplyMatrix(int mA[][], int mB[][]) {
        int multiplyArr[][] = new int[3][1];
        int col = mB[0].length - 1;

        System.out.println("\n\n:: Before Multiplying Array ::");
        for (int i = 0; i < mA.length; i++) {
            int sum = 0;
            for (int j = 0; j < mA[i].length; j++) {
                int prod = mA[i][j] * mB[j][col];
                if (j == mA[i].length-1) {
                    System.out.printf("(%d)(%d) = %d\n", mA[i][j], mB[j][col], prod);
                } else {
                    System.out.printf("(%d)(%d) = %d\t + \t", mA[i][j], mB[j][col], prod);
                }
                
                sum += prod;
            }
            multiplyArr[i][col] = sum;
        }

        System.out.println("\n\n:: After Multiplying Array ::");
        System.out.println(":: Final Answer ::");
        for (int i = 0; i < multiplyArr.length; i++) {
            for (int j = 0; j < multiplyArr[0].length; j++) {
                System.out.printf("x%d = %d", i+1, multiplyArr[i][j]);
            }
            System.out.println();
        }
    }

    // -----------------------------------------------------------------
    // -----------------------------------------------------------------
    // -----------------------------------------------------------------
    // Main Method
    public static void main(String[] args) {
        // ===> ( 1st Part of Question )
        Scanner sc = new Scanner(System.in);

        int matrix[][] = new int[3][3];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.printf("Enter value of cell(%d,%d): ", i + 1, j + 1);
                matrix[i][j] = sc.nextInt();
            }
        }
        System.out.print("\033[H\033[2J");

        System.out.println("---------------------------------");
        System.out.println("    " + author);
        System.out.println("---------------------------------\n\n");

        String inverseArr[][] = new String[matrix.length][matrix[0].length];
        boolean checkInverse = takingMatrixInverse(matrix, inverseArr);

        if (checkInverse == false) {
            System.out.println("=> MOD is '0'\nNOT Invertible");
        }

        System.out.println("\n\n:: Final Inverse Array ::");
        displayArr(inverseArr);




        // ===> ( 2nd Part of Question )

        int matrixB[][] = new int[3][1];
        int matrixA[][] = new int[3][3];

        System.out.println("\n\n\n\n");
        System.out.println(":: NOW FOR MULTIPLYING ::");
        System.out.println("\n=> Enter Matrix A:-");
        for (int i = 0; i < matrixA.length; i++) {
            for (int j = 0; j < matrixA[i].length; j++) {
                System.out.printf("Enter value of cell(%d,%d): ", i + 1, j + 1);
                matrixA[i][j] = sc.nextInt();
            }
        }

        System.out.println("\n\n=> Enter Matrix B:-");
        for (int i = 0; i < matrixB.length; i++) {
            for (int j = 0; j < matrixB[0].length; j++) {
                System.out.printf("Enter value of cell(%d,%d): ", i + 1, j + 1);
                matrixB[i][j] = sc.nextInt();
            }
        }

        System.out.println("\n\n:: Matrix A & Matrix B ::");
        displayArr(matrix, matrixB);

        multiplyMatrix(matrixA, matrixB);

        sc.close(); // scanner object closed
    }
    // -----------------------------------------------------------------
    // -----------------------------------------------------------------
    // -----------------------------------------------------------------
    // -----------------------------------------------------------------

    public static void displayArr(int arr[][]) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + "\t  ");
            }
            System.out.println();
        }
    }

    public static void displayArr(String arr[][]) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + "\t  ");
            }
            System.out.println();
        }
    }

    public static void displayArr(int mA[][], int mB[][]) {
        for (int i = 0; i < mA.length; i++) {
            for (int j = 0; j < mA[i].length; j++) {
                System.out.print(mA[i][j] + " ");
            }

            System.out.print("\t\t");

            for (int j = 0; j < mB[i].length; j++) {
                System.out.print(mB[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static String author = "Developed by:- Q U A L O";
}