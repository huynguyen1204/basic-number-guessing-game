import java.util.*;
public class testing {
    static Scanner scan = new Scanner(System.in);
    public static int best_attempt=Integer.MAX_VALUE;
    public static void main(String[] args){
        System.out.println("""
                Welcome to the Number Guessing Game!
                I'm thinking of a number between 1 and 100.
                You have 5 chances to guess the correct number.
                Please select the difficulty level:
                1. Easy (10 chances)
                2. Medium (5 chances)
                3. Hard (3 chances)
                4. Unlimited""");
        String again="y";
        while(again.equals("y")){
        System.out.println("Enter your choice");
        int choice = scan.nextInt();
        while (choice>4){
            System.out.println("Enter your choice again");
            choice = scan.nextInt();
        }
        //Start game
        game(choice);
            System.out.println("Do you want to play again ?(y/n)");
            again= scan.next();

        }
    }
    //Choose mode and play game
    private static void game(int choice) {
        Random rand= new Random();
        int attempt=0;

        int answer = rand.nextInt(1,101);
        String level;
        int chance;
        if (choice==1){
            level="Easy difficulty level.";
            chance=10;}
        else if (choice==2) {
            level="Medium difficulty level.";
            chance=5;}
        else if (choice==3){
            level="Hard difficulty level.";
            chance=3;}
        else{
           level="Unlimited mode.";
            chance=Integer.MAX_VALUE;}
        System.out.println("Great! You have selected the "+ level+ "\n" +
                "Let's start the game!");

        boolean check= false;
        int suggest=0;
        while (chance>=0 && !check){
            if (chance<=3||chance>5){
                if(suggest<3){
                    boolean getHelp = false;
                    if (choice==3){
                        getHelp=true;
                    }
                    else{
                        System.out.println("Do you want some help?(y/n)");
                        String help= scan.next();
                        if (help.equals("y")){
                            getHelp=true;
                        }
                    }
                    if (getHelp) {
                        if (suggest==0) System.out.println("1st Suggest");
                    else if (suggest==1)  System.out.println("2nd Suggest");
                    else System.out.println("Final Suggest");
                        help(suggest, answer);
                        suggest++;
                    }
                }
            }else{
                System.out.println("You have used up all suggestion");
            }
            System.out.println("Enter your guess: ");

            check=gameRule(answer);
            attempt++;

            if (choice!=4){
             chance--;
            }
            if (chance==0&& !check){
                System.out.println("Game Over!!!\n" + "The answer is " + answer);
                chance--;
            }


        }
        if (check){
            System.out.println("You have guess the number in " + attempt + " attempt");
            if (attempt<best_attempt){
                best_attempt=attempt;
            }
            System.out.println("Your best attempt is " + best_attempt);
        }


    }

    private static void help(int suggest, int answer) {
        int first_digit=answer/10;
        int lower_bound=first_digit*10;
        int upper_bound=(first_digit+1)*10;
        switch (suggest){
            case 0:
                if(upper_bound==100){
                    System.out.println("The answer is in range of " + (lower_bound-20) + " to " + upper_bound);
                } else if (lower_bound==0) {
                    System.out.println("The answer is in range of " + lower_bound + " to " + (upper_bound+20));
                }else{
                System.out.println("The answer is in range of " + (lower_bound-10) + " to " + (upper_bound+10));}
                break;
            case 1:
                System.out.println("The answer is in range of " + lower_bound + " to " + upper_bound);
                break;
            case 2:
                if(answer==upper_bound){
                    System.out.println("The answer is between " + (answer-1) + " and " + upper_bound);
                } else {
                    System.out.println("The answer is between " + answer + " and " + (answer + 1));
                }
                break;
            default:
                System.out.println("you have use up all your suggestion, don't bother to press y again");
                //if this line show up then check your if else statement again
        }
    }

    private static boolean gameRule(int answer) {
        int guess= scan.nextInt();
        if (guess<=100){
            if (guess>answer){
                System.out.println("The answer is smaller than " + guess);
                return false;
            }
            else if (guess< answer) {
                System.out.println("The answer is greater than " + guess);
                return false;
            }
            else{
                System.out.println("You have guess the right answer");
                return true;
            }
        }


        System.out.println("Please enter the guess in the range");
        return false;
    }
}
