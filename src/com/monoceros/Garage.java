package com.monoceros;

/*
From the discussion in the Main class:
Have a garage class
traits - levels (at least one, but no inherent limit. Probably good to deal with physically impossible edge cases, perhaps with a check against a variable set somewhere)
        - parking spaces per level (It mentions parking spaces, but a possible extension would be having types of parking spaces. E.g., compact or motorcycle that take up less space, or just things like electric cars or parking spaces for disabled users.)
        - parking spaces remaining
        park() - enter(vehicle) method (assign a space if there is space, reject if not -- perhaps boolean return? Or maybe -1 if no space, number of level parked? Instructions aren't clear.)
        unPark() - exit(vehicle) method
        //Not using(get from Vehicle) - getLevel(vehicle) method - returns level number (could be letters, but using ints seems easiest)
        //Not using(get from Vehicle) - getParkingSpot(vehicle) method - returns parking space number -- and this is number on a certain level.
        - constructor should take in levels and parking spaces per level
        - Maybe a name?
*/

import java.util.ArrayList;
import java.util.List;

public class Garage {
    private int levels;
    private int spacesPerLevel;
    private int spacesRemaining;
    private String garageName;
    private Vehicle[] spaces;
    private int garageCapacity;

    Garage(int inLevels, int inSpacesPerLevel, String inGarageName)
    {
        levels = inLevels;
        spacesPerLevel = inSpacesPerLevel;
        garageName = inGarageName;
        int capacity = inLevels * inSpacesPerLevel;
        spaces = new Vehicle[capacity];
        spacesRemaining = capacity;
        garageCapacity = capacity;
    }

    /*
    Park the vehicle (changing the incoming vehicle object), then return true if it worked. If no space, return false.
    Note; Checking for duplicates is left for elsewhere. This'll happily park any vehicle.
     */
    boolean park(Vehicle vehicle)
    {
        if(spacesRemaining <= 0)
            return false;
        else {
            for(int i = 0; i<garageCapacity; i++)
            {
                if(spaces[i] == null)
                {
                    vehicle.setInGarage(true);
                    vehicle.setGarageLevel(i/spacesPerLevel); //Yes, this means the garage starts at level 0. This is less weird in Europe than the US.
                    vehicle.setSpotNum(i%spacesPerLevel); //Yes, this means that spots start at 0.
                    spaces[i] = vehicle;
                    System.out.println("Parked on level " + vehicle.getGarageLevel() + " in spot " + vehicle.getSpotNum());
                    break;
                }
            }
            return true;
        }
    }

    public int getLevels() {
        return levels;
    }

    public void setLevels(int levels) {
        this.levels = levels;
    }

    public int getSpacesPerLevel() {
        return spacesPerLevel;
    }

    public void setSpacesPerLevel(int spacesPerLevel) {
        this.spacesPerLevel = spacesPerLevel;
    }

    public int getSpacesRemaining() {
        return spacesRemaining;
    }

    public void setSpacesRemaining(int spacesRemaining) {
        this.spacesRemaining = spacesRemaining;
    }

    public String getGarageName() {
        return garageName;
    }

    public void setGarageName(String garageName) {
        this.garageName = garageName;
    }

    public int getGarageCapacity() {
        return garageCapacity;
    }

    public void setGarageCapacity(int garageCapacity) {
        this.garageCapacity = garageCapacity;
    }

    /*
        Unpark the vehicle (changing the incoming vehicle object).
        Note: This'll unpark the first vehicle licence that matches, even if there are duplicates.
         */
    void unPark(Vehicle vehicle)
    {
        for(int i = 0; i<garageCapacity; i++)
        {
            if(spaces[i] != null && spaces[i].equals(vehicle))
            {
                vehicle.setInGarage(false);
                vehicle.setGarageLevel(-1);
                vehicle.setSpotNum(-1);
                spaces[i] = null;
                break;
            }
        }
    }
}
