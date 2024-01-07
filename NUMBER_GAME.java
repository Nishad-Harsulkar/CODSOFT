import java.util.*;
public class NUMBER_GAME
{
    public static void NumberGame()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the Number Guessing game !");
        int total_rounds=0;
        int rounds_won=0;
        do
        {
            System.out.println("Enter the Desired range from which the Number must be Guessed ");
            int U = sc.nextInt();

            // Generate the numbers
            //int number = 1 + (int)(100 * Math.random());
            int number = 1 + (int) (U * Math.random());
            // input
            int K;
            System.out.println("How many number of trials do you want ?");
            K = sc.nextInt();

            int i, guess;
            System.out.println("A number is chosen" + " between 1 to " + U + ". Guess the number"
                    + " within " + K + " trials.");

            // Iterate over K Trials
            for (i = 0; i < K; i++) {
                System.out.println("Guess the number:");
                guess = sc.nextInt();

                //conditions
                if (number == guess) {
                    System.out.println("Congratulations!" + " You guessed the number.");
                    rounds_won++;
                    break;
                } else if (number > guess && i != K - 1) {
                    System.out.println("The number is " + "greater than " + guess);
                } else if (number < guess && i != K - 1) {
                    System.out.println("The number is" + " less than " + guess);
                }
            }

            if (i == K)
            {
                System.out.println("You have exhausted " + K + " trials.");
                System.out.println("The number was " + number);
            }
            total_rounds++;
            System.out.println("Do you want to play another round? (yes/no)");
            String playAgain = sc.next().toLowerCase();
            if (!playAgain.equals("yes"))
            {
                break; // Exit the loop if the user doesn't want to play another round
            }

        }while(true);
        System.out.println("Final score : ");
        System.out.println("Rounds won :"+rounds_won);
        System.out.println("Total rounds played :"+total_rounds);
    }
    public static void main(String[] args)
    {
        NumberGame();
    }
}

