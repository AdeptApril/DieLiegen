package com.monoceros;

/*
Die Ligen - Programming Exercise

The parking garage next to the Die Ligen office in Stuttgart is going digital (not really, just assume for this exercise) and the owner asks you for your support with the implementation.
The garage should support different types of vehicles:
- Cars
- Motorbikes
Every vehicle has a unique identifier (the licence plate), and can exist only once – thus being either within the garage or outside of it.
The planned garage should support multiple parking levels and the owner may wants to reuse your implementation for other garages as well. As a result, your implementation should allow for arbitrary numbers of parking levels – at least 1 level, but keep it flexible.
The same goes for the number of parking spaces per level – so again, keep this flexible and configurable.
Your task is to implement the garage (a software program that behaves like the described garage). Vehicles should be able to enter and exit the garage – the garage should then assign a free space or reject the vehicle if there are no more free parking lots.
Users of the garage should be able to ask the system for the location of a specific vehicle – people tend to forget where they have parked. The response should include the parking level and the assigned parking lot. Also, the number of free parking lots should be queryable.

-----
Please use Java. Use an in-memory data structure to store the data, for this exercise no database or other means of persistence and integration-work is desired. The program does not need any GUI or command line interface, as long as you can show your implementation is working.
You can send your result in the way you prefer, i.e. as a zipped working space or link to a public repository on GitHub.
If you have any questions, feel free to ask.
Please do not spend more than 4 hrs on this exercise! Whatever you have by then, is fine as an answer. We are not stating how long this implementation should take you and/or if it can be done within the timeframe.
 */

/*
Implementation ideas:
Have a vehicle class, with car and motorbike sub classes (Decided against polymorphism. Discussed below)
vehicle traits - licence plate (using that spelling instead of "license" because it's used in the program description)
               - Location (maybe boolean of inGarage?)
               - Maybe vehicle type? At which point it becomes questionably useful to have polymorphism. Hackathon coding would definitely go with a vehicle, and probably a good idea for a quick implementation, so I think I'll go with that for the first run.
               - Perhaps garage level and parking spot number? Could also be tracked in garage class.

Have a garage class
    traits - levels (at least one, but no inherent limit. Probably good to deal with physically impossible edge cases, perhaps with a check against a variable set somewhere)
           - parking spaces per level (It mentions parking spaces, but a possible extension would be having types of parking spaces. E.g., compact or motorcycle that take up less space, or just things like electric cars or parking spaces for disabled users.)
           - parking spaces remaining
           - enter(vehicle) method (assign a space if there is space, reject if not -- perhaps boolean return? Or maybe -1 if no space, number of level parked? Instructions aren't clear.)
           - exit(vehicle) method
           - getLevel(vehicle) method - returns level number (could be letters, but using ints seems easiest)
           - getParkingSpot(vehicle) method - returns parking space number -- and this is number on a certain level.
           - constructor should take in levels and parking spaces per level
           - Maybe a name?

Things that are much more open to interpretation:
How to add vehicles?
    - Have a list of vehicles that are populated in the code (I'll go with this as the quicker solution)
    - Have a generation method that would generate x vehicles (possibly overloaded to allow for different types of vehicles, or other combinations of starting values)
    - Have a menu option for adding vehicles, and leave it entirely up to the user.

Main activities -
    Setup - Make a list of vehicles, start with all outside of the garage
          - Construct the garage
    Running of the garage
        - list vehicles
        - lookup vehicle (response should include parking level and assigned parking spot)
        - park vehicle
        - unpark vehicle
        - exit program

Other notes -
    This could work as test-driven development, but I think I'd want a few rounds of what the user would expect to happen in order to design the tests
    Obviously, this doesn't preclude having tests anyway.
    TODO: Check for and/or catch various edge cases like blank vehicle names or other weird input. This is more of a, "think about more edge cases" than any certainty of bad things happening.
 */

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Map<String, Vehicle> vehicles = new HashMap<>(); //Vehicle located by plate number
        String menuChoice;
        String plate; //This could probably be placed in a better spot. E.g., less global, but it's used across multiple
                        //cases in the menu, and might be used elsewhere for currently-cared-about vehicle.
        Garage myGarage;

        //Adding vehicles to the map
        vehicles.put("C1", new Vehicle("C1", "car"));
        vehicles.put("C2", new Vehicle("C2", "car"));
        vehicles.put("B1", new Vehicle("B1", "motorbike"));
        vehicles.put("C3", new Vehicle("C3", "car"));
        vehicles.put("C4", new Vehicle("C4", "car"));
        vehicles.put("C5", new Vehicle("C5", "car"));
        vehicles.put("B2", new Vehicle("B2", "motorbike"));
        vehicles.put("C6", new Vehicle("C6", "car"));
        vehicles.put("C7", new Vehicle("C7", "car"));

        //Making a garage
        myGarage = new Garage(3, 2, "Die Ligen");

        while(true)
        {
            menuChoice = menu();
            switch(menuChoice) {
                case "1": //List vehicles
                    for(Map.Entry<String, Vehicle> e : vehicles.entrySet())
                    {
                        Vehicle vehicle = e.getValue();
                        System.out.print("Plate: " + vehicle.getLicencePlate()
                                + " - vehicle type: " + vehicle.getVehicleType()
                                + " - in garage? " + vehicle.isInGarage());
                        if(vehicle.isInGarage())
                        {
                            System.out.print(" - Garage level: " + vehicle.getGarageLevel()
                                + " - parking spot: " + vehicle.getSpotNum());
                        }
                        System.out.println();
                    }
                    break;
                case "2": //lookup vehicle
                    System.out.println("Please enter licence plate of vehicle you'd like to find:");
                    plate = getLicencePlate();
                    if(vehicles.containsKey(plate) && vehicles.get(plate).isInGarage())
                    {
                        //(response should include parking level and assigned parking spot)
                        System.out.println("The vehicle is located on floor: " + vehicles.get(plate).getGarageLevel()
                            + " and is in spot: " + vehicles.get(plate).getSpotNum());
                    }
                    else {
                        System.out.println("I'm sorry; I don't seem to see that vehicle in the garage.");
                    }
                    break;
                case "3": //park vehicle
                    System.out.println("Please enter licence plate of vehicle you'd like to park:");
                    plate = getLicencePlate();
                    if(vehicles.containsKey(plate))
                    {
                        Vehicle vehicle = vehicles.get(plate);
                        if(vehicle.isInGarage())
                        {
                            System.out.println("I'm sorry; that vehicle is already parked.");
                        } else //vehicle exists and is not already parked
                        {
                            myGarage.park(vehicle);
                        }
                    } else
                    {
                        System.out.println("I'm sorry, but I can't find that vehicle");
                    }
                    break;
                case "4": //unpark vehicle
                    System.out.println("Please enter licence plate of vehicle you'd like to unpark:");
                    plate = getLicencePlate();
                    if(vehicles.containsKey(plate))
                    {
                        Vehicle vehicle = vehicles.get(plate);
                        if(!vehicle.isInGarage())
                        {
                            System.out.println("I'm sorry; that vehicle isn't parked in the garage.");
                        } else //vehicle exists and is parked in the garage
                        {
                            myGarage.unPark(vehicle);
                        }
                    } else
                    {
                        System.out.println("I'm sorry, but I can't find that vehicle");
                    }
                    break;
                case "0": //Exit program
                    return;
                default:
                    System.out.println("I'm sorry, I didn't understand that choice. Here's the menu again:");
                    break;
            }
        }
    }

    private static String menu()
    {
        System.out.println();
        System.out.println("Welcome to the Die Ligen parking garage!"); //Would get name from garage class, but this is static, so would have to be re-written in a different way
        System.out.println("How can we help you?");
        System.out.println("1. List vehicles");
        System.out.println("2. Lookup vehicle in garage");
        System.out.println("3. Park vehicle");
        System.out.println("4. Unpark vehicle");
        System.out.println("0. Exit Program");
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    /*
    Upon receipt of a vehicle, prints out parking level and assigned parking spot
     */
    private void lookupVehicle(Vehicle vehicle)
    {

    }

    /*
    Lists available vehicles (in garage or not)
     */
    private void listVehicles()
    {

    }

    /*
    Helpful method for getting licence plate from user
     */
    private static String getLicencePlate()
    {
        System.out.println("Please enter licence plate:");
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
}
