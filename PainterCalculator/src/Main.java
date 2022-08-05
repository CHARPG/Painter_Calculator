import java.util.Scanner;
import java.util.ArrayList;
public class Main {
    //create lists to store and reorganise data used throughout the program for calculations
    static ArrayList<Float> surfaceAreas = new ArrayList<Float>();
    static ArrayList<Integer> coats = new ArrayList<Integer>();
    static ArrayList<Double> paintRequired = new ArrayList<Double>();
    static ArrayList<Double> allCosts = new ArrayList<Double>();
    static ArrayList<String> allColours = new ArrayList<String>();

    //create 3 variables for storing totals
    static int wallAmnt;
    static float totalPaint = 0;
    static float totalCost = 0;
    //scanner to take input
    static Scanner reader = new Scanner(System.in);

    public static void main(String[] args)
    {
        //learn total number of walls
        System.out.println("Welcome to the Painter Calculator \n" + "Please enter amount of walls in the room");
        wallAmnt = reader.nextInt();

        //call calculate function for each wall
        for(int i = 0; i != wallAmnt; i++) {
            calculateSurfaceArea(i + 1);
        }
        //list properties of each wall
        for(int i = 0; i != wallAmnt; i++) {
            System.out.println("Wall " + (i+1));
            String complement = complementaryGenerator(allColours.get(i));
            System.out.println("Colour: " + allColours.get(i));
            if(complement != "") {
                System.out.println("Try using " + complement + " as a complementary colour");
            }
            System.out.println("Surface area area: " + surfaceAreas.get(i) + " meters squared");
            System.out.println(coats.get(i) + " coat(s) of paint");
            System.out.println(paintRequired.get(i) + " total litres of paint");
            System.out.println("Cost: ∼£" + allCosts.get(i) + "\n");
        }
        System.out.println("Total amount of paint: " + totalPaint + "litres" + "\nTotal amount of cans: " + Math.ceil(totalPaint / 3.7) + " one gallon cans" + "\nTotal cost: ∼£" + totalCost);
    }
    public static void calculateSurfaceArea(int i)
    {
        float surfaceArea = 0;

        //get input for shape of wall
        System.out.println("Is wall " + i + ":\nSquare/Rectangular\nTriangular\nCircular");
        String ansShape = reader.next().toLowerCase();

        //error checker
        while(!ansShape.equals("square") && !ansShape.equals("rectangular") && !ansShape.equals("triangular") && !ansShape.equals("circular"))
        {
            System.out.println("I do not recognise that shape. Please try again");
            ansShape = reader.next().toLowerCase();
        }

        //check ans to determine shape of wall
        if (ansShape.toLowerCase().equals("square") || ansShape.toLowerCase().equals("rectangular")) {
            surfaceArea = squareSA(i);
        } else if (ansShape.toLowerCase().equals("triangular")) {
            surfaceArea = triangleSA(i);
        } else if (ansShape.toLowerCase().equals("circular")) {
            surfaceArea = circleSA(i);
        }

        System.out.println("Does this wall have a window, door or plug socket? YES/NO");
        String ans = reader.next().toUpperCase();

        while(!ans.equals("YES") && !ans.equals("NO"))
        {
            System.out.println("I do not recognise that input. Please try again");
            ans = reader.next().toUpperCase();
        }

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
                //calc surface area - extra objects area
                surfaceAreaExtra += (widthExtra * heightExtra);
            }
            int coatsTemp = coatsReq();
            //calc surface area removing the missing space
            surfaceAreas.add(surfaceArea - surfaceAreaExtra);
            //calc the needed paint
            calculatePaint(surfaceArea - surfaceAreaExtra, coatsTemp, i);
        }
        else if (ans.toUpperCase().equals("NO"))
        {
            int coatsTemp = coatsReq();
            //calculate the total surface area of the wall

            //add surface area to list
            surfaceAreas.add(surfaceArea);
            //calc amount of paint needed
            calculatePaint(surfaceArea, coatsTemp, i);
        }
    }
    public static float squareSA(int i)
    {
        System.out.println("Please enter wall " + i + " width in meters");
        //read input for width
        float width = reader.nextFloat();
        System.out.println("Please enter wall " + i + " height in meters");
        //read input for height
        float height = reader.nextFloat();
        float surfaceArea = width * height;

        return surfaceArea;
    }
    public static float triangleSA(int i)
    {
        System.out.println("Please enter wall " + i + " base in meters");
        //read input for base
        float width = reader.nextFloat();
        System.out.println("Please enter wall " + i + " height in meters");
        //read input for height
        float height = reader.nextFloat();

        float surfaceArea = 0.5f * width * height;
        return surfaceArea;
    }
    public static float circleSA(int i)
    {
        System.out.println("Please enter wall " + i + " radius in meters");
        //read input for radius
        float radius = reader.nextFloat();
        double surfaceArea = radius * radius * Math.PI;
        float surfaceAreaCalc = (float)surfaceArea;
        return surfaceAreaCalc;
    }
    public static void calculatePaint(float surfaceArea, int coats, int i)
    {
        //calculate how much paint you will need (1 litre is 6.5 meters of wall) and round to the next int
        double paint = Math.ceil(surfaceArea/6.5) * coats;
        //add value of required paint for this wall to array
        paintRequired.add(paint);
        totalPaint += paint;
        //add cost of paint of this wall to array
        allCosts.add(paint * 6);
        totalCost += (paint * 6);

        System.out.println("What colour paint will you be using?");
        //read input for coats
        String colour = reader.next();
        allColours.add(colour);
    }

    public static int coatsReq()
    {
        System.out.println("How many coats of paint would you like to apply to the wall?");
        //read input for coats
        int coatsTemp = reader.nextInt();
        //add current amount of coats to list
        coats.add(coatsTemp);

        return coatsTemp;
    }

    public static String complementaryGenerator(String colour)
    {
        //switch to get matching complementary colour
        String colourtemp = null;
        switch(colour.toLowerCase())
        {
            case "blue":
                colourtemp = "orange";
                break;
            case "green":
                colourtemp = "red";
                break;
            case "yellow":
                colourtemp = "purple";
                break;
            case "red":
                colourtemp = "green";
                break;
            case "orange":
                colourtemp = "blue";
                break;
            case "purple":
                colourtemp = "yellow";
                break;
            case "white":
            case "black":
            case "grey":
            default:
                colourtemp = "";
                break;
        }
        return colourtemp;
    }
}