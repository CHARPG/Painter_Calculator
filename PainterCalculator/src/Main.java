import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static ArrayList<Float> surfaceAreas = new ArrayList<Float>();
    public static ArrayList<Integer> coats = new ArrayList<Integer>();
    public static ArrayList<Double> paintRequired = new ArrayList<Double>();
    public static ArrayList<Double> allCosts = new ArrayList<Double>();
    public static int wallAmnt;

    public static void main(String[] args)
    {
        float totalPaint = 0;

        float totalCost = 0;

        Boolean done = false;
        //scanner to take input
        Scanner reader = new Scanner(System.in);

        System.out.println("Welcome to the Painter Calculator \n" + "Please enter amount of walls in the room");
        wallAmnt = reader.nextInt();

        //call calculate function
        for(int i = 0; i != wallAmnt; i++) {
            calculateSurfaceArea(i + 1);
        }
        for(int i = 0; i != wallAmnt; i++) {
            System.out.println("Wall " + (i+1) + " surface area area is " + surfaceAreas.get(i) + " meters squared");
            System.out.println("Wall " + (i+1) + " needs " + coats.get(i) + " coats of paint");
            System.out.println("Wall " + (i+1) + " needs " + paintRequired.get(i) + " total litres of paint");
            System.out.println("Wall " + (i+1) + " will cost roughly £" + allCosts.get(i) + "\n");

        }
        for (int i = 0; i < paintRequired.size(); i++) {
        totalPaint += paintRequired.get(i);
        }
        for (int i = 0; i < allCosts.size(); i++) {
            totalCost += allCosts.get(i);
        }
        System.out.println("Total amount of paint: " + totalPaint);
        System.out.println("Total cost: £" + totalCost);
    }

    public static void calculateSurfaceArea(int i)
    {
        Scanner reader = new Scanner(System.in);
        System.out.println("Please enter wall " + i + " width in meters");
        //read input for width
        float width = reader.nextFloat();
        System.out.println("Please enter wall " + i + " height in meters");
        //read input for height
        float height = reader.nextFloat();

        System.out.println("Does this wall have a window, door or plug socket?");
        String ans = reader.next();

        if(ans.toUpperCase().equals("YES"))
        {
            System.out.println("How many?");
            int addValAmnt = reader.nextInt();

            float surfaceAreaExtra = 0;

            for(int j = 0; j < addValAmnt; j++)
            {
                System.out.println("Please enter width of object " + (j+1) + " in meters");
                //read input for width
                float widthExtra = reader.nextFloat();
                System.out.println("Please enter height of object " + (j+1) + " in meters");
                //read input for height
                float heightExtra = reader.nextFloat();
                surfaceAreaExtra += (widthExtra * heightExtra);
            }
            System.out.println("How many coats of paint would you like to apply to wall " + i + "?");
            //read input for coats
            int coatsTemp = reader.nextInt();
            coats.add(coatsTemp);
            surfaceAreas.add((width * height) - surfaceAreaExtra);
            calculatePaint((width * height) -surfaceAreaExtra, coatsTemp);
        }
        else
        {
            System.out.println("How many coats of paint would you like to apply to wall " + i + "?");
            //read input for coats
            int coatsTemp = reader.nextInt();
            coats.add(coatsTemp);
            //calculate the total surface area of the wall
            float surfaceArea = width * height;
            surfaceAreas.add(surfaceArea);
            calculatePaint(surfaceArea, coatsTemp);
        }


    }
    public static void calculatePaint(float surfaceArea, int coats)
    {
        //calculate how much paint you will need (1 litre is 6.5 meters of wall) and round to the next int
        double paint = Math.ceil(surfaceArea/6.5) * coats;
        //add value of required paint for this wall to array
        paintRequired.add(paint);
        //add cost of paint of this wall to array
        allCosts.add(paint * 6);
    }
}