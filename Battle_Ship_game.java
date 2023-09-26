package Project;



import java.util.Scanner;



public class BattleShipsGame {

    public static final int numRows = 10;

    public static final int numCols = 10;

    public static int playerShips;

    public static int computerShips;

    public static String[][] grid = new String[numRows][numCols];

    public static int[][] missedGuesses = new int[numRows][numCols];



    public static void main(String[] args) {

        System.out.println("** Welcome to Battle Ships game **");

        System.out.println("Right now, sea is empty\n");



        createOceanMap();



        deployPlayerShips();

        deployComputerShips();



        while (playerShips > 0 && computerShips > 0) {

            battle();

        }



        gameOver();

    }



    public static void createOceanMap() {

        System.out.print("  ");

        for (int i = 0; i < numCols; i++)

            System.out.print(i);

        System.out.println();



        for (int i = 0; i < grid.length; i++) {

            for (int j = 0; j < grid[i].length; j++) {

                grid[i][j] = " ";

                if (j == 0)

                    System.out.print(i + "|" + grid[i][j]);

                else if (j == grid[i].length - 1)

                    System.out.print(grid[i][j] + "|" + i);

                else

                    System.out.print(grid[i][j]);

            }

            System.out.println();

        }



        System.out.print("  ");

        for (int i = 0; i < numCols; i++)

            System.out.print(i);

        System.out.println();

    }



    public static void deployPlayerShips() {

        Scanner input = new Scanner(System.in);



        System.out.println("\nDeploy your ships:");

        computerShips = 5;

        for (int i = 1; i <= computerShips; ) {

            int x = (int) (Math.random() * 10);

            int y = (int) (Math.random() * 10);



            if (isValidCoordinate(x, y) && grid[x][y].equals(" ")) {

                grid[x][y] = "x";

                System.out.println(i + ". ship DEPLOYED");

                i++;

            }

           

        }

        printOceanMap();

    }



    public static void deployComputerShips() {

        System.out.println("\nComputer is deploying ships:");

        playerShips = 5;

        for (int i = 1; i <= playerShips; ) {

            int x = (int) (Math.random() * 10);

            int y = (int) (Math.random() * 10);



            if (isValidCoordinate(x, y) && grid[x][y].equals(" ")) {

                grid[x][y] = "@";

                i++;

            }

        }

    }



    public static void battle() {

        playerTurn();

        computerTurn();

        printOceanMap();

        System.out.println("\nYour ships: " + playerShips + " | Computer ships: " + computerShips + "\n");

    }



    public static void playerTurn() {

        System.out.println("YOUR TURN");

        int x, y;

        do {

            Scanner input = new Scanner(System.in);

            System.out.print("Enter X coordinate: ");

            x = input.nextInt();

            System.out.print("Enter Y coordinate: ");

            y = input.nextInt();

        } while (!isValidCoordinate(x, y) || grid[x][y].equals("!") || grid[x][y].equals("x"));



        if (grid[x][y].equals("@")) {

            System.out.println("Boom! You sunk the ship!");

            grid[x][y] = "!";

            computerShips--;

        } else if (grid[x][y].equals("x")) {

            System.out.println("Oh no, you sunk your own ship :(");

            grid[x][y] = "!";

            playerShips--;

        } else if (grid[x][y].equals(" ")) {

            System.out.println("Sorry, you missed");

            grid[x][y] = "-";

        }

    }



    public static void computerTurn() {

        System.out.println("\nCOMPUTER'S TURN");

        int x, y;

        do {

            x = (int) (Math.random() * 10);

            y = (int) (Math.random() * 10);

        } while (!isValidCoordinate(x, y) || grid[x][y].equals("!") || grid[x][y].equals("x"));



        if (grid[x][y].equals("x")) {

            System.out.println("The Computer sunk one of its own ships");

            grid[x][y] = "!";

        } else if (grid[x][y].equals("@")) {

            System.out.println("The Computer sunk one of your ships!");

            grid[x][y] = "!";

            playerShips--;

        } else if (grid[x][y].equals(" ")) {

            System.out.println("Computer missed");

            missedGuesses[x][y] = 1;

        }

    }



    public static void gameOver() {

        System.out.println("Your ships: " + playerShips + " | Computer ships: " + computerShips);

        if (playerShips > 0 && computerShips <= 0)

            System.out.println("Hooray! You won the battle :)");

        else

            System.out.println("Sorry, you lost the battle");

    }



    public static void printOceanMap() {

        System.out.println();

        System.out.print("  ");

        for (int i = 0; i < numCols; i++)

            System.out.print(i);

        System.out.println();



        for (int x = 0; x < grid.length; x++) {

            System.out.print(x + "|");

            for (int y = 0; y < grid[x].length; y++) {

                System.out.print(grid[x][y]);

            }

            System.out.println("|" + x);

        }



        System.out.print("  ");

        for (int i = 0; i < numCols; i++)

            System.out.print(i);

        System.out.println();

    }



    public static boolean isValidCoordinate(int x, int y) {

        return x >= 0 && x < numRows && y >= 0 && y < numCols;

    }

   

}