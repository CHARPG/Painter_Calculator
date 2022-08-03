import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        //scanner to take input
        Scanner reader = new Scanner(System.in);

        System.out.println("Welcome to the Painter Calculator \n" + "Please enter wall width in meters");
        //read input for width
        float width = reader.nextFloat();
        System.out.println("Please enter wall height in meters");
        //read input for height
        float height = reader.nextFloat();
        System.out.println("How many coats of paint would you like to apply?");
        //read input for coats
        int coats = reader.nextInt();

        //call calculate function
        float surfaceArea = calculateSurfaceArea(width, height);

        System.out.println("Wall surface area is " + surfaceArea + " meters squared");

        //calculate how much paint you will need (1 litre is 6.5 meters of wall) and round to the next int
        double paint = Math.ceil(surfaceArea/6.5) * coats;

        double price = paint * 6;
        //print the total amount of paint needed, the price and amount of coats
        System.out.println("You will need " + paint + " litres of paint\nThis will roughly cost £" + price  + " for " + coats + " coats of paint.");
    }

    public static float calculateSurfaceArea(float width, float height)
    {
        //calculate the total surface area of the wall
        float surfaceArea = width * height;
        return surfaceArea;
    }
}