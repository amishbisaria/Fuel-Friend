package com.company;
import java.text.DecimalFormat;
import java.util.Random;

public class Main {

    //random number generator
    static Random rand = new Random();

    //main class
    public static void main(String[] args)
    {
        //Print out units for car, pump, and driver
        System.out.println("Units of Measure:");
        String carUnit = returnUnit();
        System.out.println("- Car: " + carUnit);
        String pumpUnit = returnUnit();
        System.out.println("- Pump: " + pumpUnit);
        String driverUnit = returnUnit();
        System.out.println("- Driver: " + driverUnit);
        System.out.println();

        //Print out tank capacity and fuel remaining
        System.out.println("Tank details:");
        double tankCapacity = (rand.nextDouble()*30) + 20; //returns random value from 20 to 50
        System.out.println("- Capacity: " + numFormat(tankCapacity) + " " + carUnit);

        double fuelRemaining;
        do
        {
            fuelRemaining = rand.nextDouble() * 50; //returns random value from 0 to 50
        }
        while (fuelRemaining >= tankCapacity); //makes sure that fuel remaining is less than tank capacity

        System.out.println("- Fuel Remaining: " + numFormat(fuelRemaining) + " " + carUnit);
        System.out.println();

        //Print out quantity of fuel to fill in the units for the car, pump, and driver
        System.out.println("Quantity of fuel to fill:");
        double fuelToFillCar = tankCapacity - fuelRemaining;
        System.out.println("- Car Units: " + numFormat(fuelToFillCar) + " " + carUnit);
        double fuelToFillPump = convertUnits(fuelToFillCar, carUnit, pumpUnit);
        System.out.println("- Pump Units: " + numFormat(fuelToFillPump) + " " + pumpUnit);
        double fuelToFillDriver = convertUnits(fuelToFillCar, carUnit, driverUnit);
        System.out.println("- Driver Units: " + numFormat(fuelToFillDriver) + " " + driverUnit);

    }

    //this method returns a random unit of measurement
    public static String returnUnit() //returns a random unit of measurement
    {
        int unitNum = rand.nextInt(3) + 1; //randomly selects a number from 1 to 3
        switch (unitNum)
        {
            case 1:
                return "US Gallons";
            case 2:
                return "Imperial Gallons";
            case 3:
                return "Liters";
            default:
                throw new IllegalStateException("Unexpected value: " + unitNum);
        }
    }

    //this method converts from fromUnit to toUnit
    public static double convertUnits (double carUnit, String fromUnit, String toUnit)
    {
        //if fromUnit equals carUnit, then no calculation is necessary
        if (fromUnit.equals(toUnit))
        {
            return carUnit;
        }
        switch (fromUnit)
        {
            case "US Gallons":
                if (toUnit.equals("Imperial Gallons"))
                {
                    return carUnit / 1.201;
                }
                else if (toUnit.equals("Liters"))
                {
                    return carUnit * 3.785;
                }
            case "Imperial Gallons":
                if (toUnit.equals("US Gallons"))
                {
                    return carUnit * 1.201;
                }
                else if (toUnit.equals("Liters"))
                {
                    return carUnit * 4.546;
                }
            case "Liters":
                if (toUnit.equals("US Gallons"))
                {
                    return carUnit / 3.785;
                }
                else if (toUnit.equals("Imperial Gallons"))
                {
                    return carUnit / 4.546;
                }
            default:
                throw new IllegalStateException("Unexpected value: " + fromUnit + " to " + toUnit);
        }
    }

    //this method uses formats decimals to only show a maximum of 2 numbers after decimal point
    public static String numFormat(double num)
    {
        return new DecimalFormat("#.##").format(num);
    }

}
