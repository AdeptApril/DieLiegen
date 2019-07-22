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

Other notes -
    This could work as test-driven development, but I think I'd want a few rounds of what the user would expect to happen in order to design the tests
    Obviously, this doesn't preclude having tests anyway.
 */

public class Main {

    public static void main(String[] args) {
	// write your code here
    }

//    private void lookupVehicle(Vehicle vehicle)
//    {
//
//    }

    private void listVehicles()
    {

    }
}
