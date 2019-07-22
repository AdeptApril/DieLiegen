package com.monoceros;

/*
From implementation discussion in Main:
vehicle traits - licence plate (using that spelling instead of "license" because it's used in the program description)
        - Location (maybe boolean of inGarage?)
        - Maybe vehicle type? At which point it becomes questionably useful to have polymorphism. Hackathon coding would definitely go with a vehicle, and probably a good idea for a quick implementation, so I think I'll go with that for the first run.
        - Perhaps garage level and parking spot number? Could also be tracked in garage class.
*/
public class Vehicle {
    private String licencePlate;
    private String vehicleType;
    private boolean inGarage;
    private int garageLevel;

    /*
    Constructor that creates a new vehicle with a given licence plate and type.
    Possible problem -- no duplicate checking on licence plate. But that's for another class
     */
    public Vehicle(String plate, String type)
    {
        licencePlate = plate;
        vehicleType = type;
        inGarage = false;
        garageLevel = -1; //This may be problematic, as there are basement levels in garages. So numbering of garages should avoid that.
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public boolean isInGarage() {
        return inGarage;
    }

    public void setInGarage(boolean inGarage) {
        this.inGarage = inGarage;
    }

    public int getGarageLevel() {
        return garageLevel;
    }

    public void setGarageLevel(int garageLevel) {
        this.garageLevel = garageLevel;
    }
}
