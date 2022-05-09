import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

public class CartesianCalculator {

    //For possible future improvements (currently impossible with the way Minecraft records position data)
    //Cartesian angle = Minecraft angle + 90 degrees
    //x3 = ((z1 - z2) + x2tan(θ2) - x1tan(θ1)) / (tan(θ2) - tan(θ1))
    //z3 = ((z1tan(θ2) - z2tan(θ1)) + (x2 - x1) * tan(θ2) * tan(θ1)) / (tan(θ2) - tan(θ1))
    //The variables x1, z1, and θ1 are the X, Z, and cartesian angle for point 1, and x2, z2, and θ2 are the same for point 2.
    //We are looking for point (x3, z3). Make sure you calculator is using degrees, not radians!

    // Calculates degrees at which the lines intersect
    public static double calculateDegreeCertainty(double firstSlope, double secondSlope) {
        double top = firstSlope - secondSlope;
        double bottom = 1 + (firstSlope * secondSlope);
        double toCalc = top / bottom;

        double result = Math.atan(toCalc);
        result = Math.toDegrees(result);
        result = Math.abs(result);
        double roundedFinal = round(result, 2);

        return roundedFinal;
    }

    // Calculates distance to the stronghold
    public static double calculateBlocksAway(String secondThrowEye, double strongholdX, double strongholdZ) {
        String[] secondThrowDataEye = secondThrowEye.split(",");
        double secondThrowEyeX = Double.valueOf(secondThrowDataEye[0]);
        double secondThrowEyeZ = Double.valueOf(secondThrowDataEye[1]);

        double diffX = Math.abs(secondThrowEyeX - strongholdX);
        double diffZ = Math.abs(secondThrowEyeZ - strongholdZ);

        double result = Math.sqrt(Math.pow(diffX, 2) + Math.pow(diffZ, 2));
        return result;
    }

    // Calculate all
    public static double[] calculateStronghold (String firstThrow, String firstThrowEye, String secondThrow, String secondThrowEye) {
        // Converting data
        String[] firstThrowData = firstThrow.split(",");
        String[] firstThrowDataEye = firstThrowEye.split(",");
        String[] secondThrowData = secondThrow.split(",");
        String[] secondThrowDataEye = secondThrowEye.split(",");
        double firstThrowX = Double.valueOf(firstThrowData[0]);
        double firstThrowZ = Double.valueOf(firstThrowData[1]);
        double firstThrowEyeX = Double.valueOf(firstThrowDataEye[0]);
        double firstThrowEyeZ = Double.valueOf(firstThrowDataEye[1]);
        double secondThrowX = Double.valueOf(secondThrowData[0]);
        double secondThrowZ = Double.valueOf(secondThrowData[1]);
        double secondThrowEyeX = Double.valueOf(secondThrowDataEye[0]);
        double secondThrowEyeZ = Double.valueOf(secondThrowDataEye[1]);

        // Calculating line data
        double firstThrowSlope = calculateSlope(firstThrowX, firstThrowZ, firstThrowEyeX, firstThrowEyeZ);
        double secondThrowSlope = calculateSlope(secondThrowX, secondThrowZ, secondThrowEyeX, secondThrowEyeZ);
        double firstThrowIntercept = calculateIntercept(firstThrowX, firstThrowZ, firstThrowSlope);
        double secondThrowIntercept = calculateIntercept(secondThrowX, secondThrowZ, secondThrowSlope);

        // Calculating output data
        double[] strongholdXZ = calculateIntersection(firstThrowSlope, firstThrowIntercept, secondThrowSlope, secondThrowIntercept);
        double[] finalResult = Arrays.copyOf(strongholdXZ, 3);
        finalResult[2] = calculateDegreeCertainty(firstThrowSlope, secondThrowSlope);

        return finalResult;
    }

    // Calculate slope of a line
    public static double calculateSlope(double firstX, double firstY, double secondX, double secondY) {
        // Will throw error on undefined slope (if there is no slope, vertical line)
        return (firstY - secondY) / (firstX - secondX);
    }

    // Calculate intercept of a line
    public static double calculateIntercept(double firstX, double firstY, double slope) {
        double operator = firstX * slope;
        double result = firstY - operator;

        return result;
    }

    // Calculates intersection of lines based on slope and intersect
    public static double[] calculateIntersection(double firstSlope, double firstIntercept, double secondSlope, double secondIntercept) {
        double[] firstLine = new double[2];
        double[] secondLine = new double[2];

        // Establishing line equations
        firstLine[0] = firstSlope;
        firstLine[1] = firstIntercept;
        secondLine[0] = secondSlope;
        secondLine[1] = secondIntercept;

        // Calculate X
        double finalX = firstLine[0] + (secondLine[0] * (-1));
        double finalNumeric = secondLine[1] + (firstLine[1] * (-1));
        double strongholdX = finalNumeric / finalX;

        // Calculate Y
        double strongholdY = (firstLine[0] * strongholdX) + firstLine[1];

        // Rounding
        strongholdX = round(strongholdX, 2);
        strongholdY = round(strongholdY, 2);

        // Returning intersection
        double[] result = new double[2];
        result[0] = strongholdX;
        result[1] = strongholdY;
        return result;
    }

    // Rounding to two decimal places method
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}

