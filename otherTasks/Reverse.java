import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Reverse {
    public static void main(String[] args) throws IOException, NullPointerException, FileNotFoundException {
        int rows = 0, columns = 0;
        Scanner scStrings = new Scanner(System.in);
        int[][] matrix = new int[3][3];
        String line = scStrings.nextLine();
        while (line != null) {
            Scanner scNumbers = new Scanner(line);
            String st = scNumbers.next();
            while (st != null) {
                if (columns == matrix[rows].length) {
                    matrix[rows] = Arrays.copyOf(matrix[rows], matrix[rows].length * 2);
                }
                int input = Integer.parseInt(st);
                if (input == 0) {
                    matrix[rows][columns] = 1000001;
                } else {
                    matrix[rows][columns] = input;
                }
                columns++;
                st = scNumbers.next();
            }
            columns = 0;
            rows++;
            if (rows == matrix.length) {
                matrix = Arrays.copyOf(matrix, 2 * matrix.length);
            }
            if (matrix[rows] == null) {
                matrix[rows] = new int[10];
            }
            line = scStrings.nextLine();
        }
        /*for (int k = matrix[rows].length - 1; k >= 0; k--) {
            if (matrix[rows][k] == 1000001) {
                System.out.print(0 + " ");
            } else if (matrix[rows][k] != 0) {
                System.out.print(matrix[rows][k] + " ");
            }
        }

         */
        for (int i = rows - 1; i >= 0; i--) {
            for (int j = matrix[i].length - 1; j >= 0; j--) {
                if (matrix[i][j] == 1000001) {
                    System.out.print(0 + " ");
                } else if (matrix[i][j] != 0) {
                    System.out.print(matrix[i][j] + " ");
                }
            }
            System.out.println();
        }
        //matrix = new int[4][4];
     /*  BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream("output.txt"),
                        StandardCharsets.UTF_8
                )
        );
        try {
            for (int i = rows - 1; i >= 0; i--) {
                for (int j = matrix[i].length - 1; j >= 0; j--) {
                    if (matrix[i][j] == 1000001) {
                        writer.write(0);
                        writer.newLine();
                    } else if (matrix[i][j] != 0) {
                        writer.write(matrix[i][j]);
                        writer.newLine();
                    }
                }
                //System.out.println();
            }
        } catch (IOException e) {
            System.out.println("Problems... :" + e.getMessage());
        } finally {
            writer.close();
        }
       /* }
    } catch (FileNotFoundException e) {
        System.out.println("Cannot found File" + e.getMessage());
    } catch (IOException e) {
        System.out.println("Cannot write" + e.getMessage());
    }
    }

        */
        }
    }


