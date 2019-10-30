package airline.entity.planes;


import airline.entity.components.Engine;

public abstract class Plane {
    /**Serial number for current plane.
     *
     */
    private final int SERIAL_NUMBER;
    /**Amount of crew crew members for current plane.
     *
     */
    private int crewSize;
    /**Name of this plane model.
     *
     */
    private String modelName;

    /**Constructs the base of plane.
     *
     * @param modelName - name of model
     * @param serialNumber - serial number
     * @param crewSize - amount of crew members
     */
    public Plane(String modelName, int serialNumber, int crewSize) {
        this.modelName = modelName;
        this.SERIAL_NUMBER = serialNumber;
        this.crewSize = crewSize;
    }

    /**
     * Constructs and returns plane.
     * <p>
     * Plane factory. Constructs and Returns plane by given parameters.
     * If (int) plane type would be less than 1 and bigger than 2 will be returned null.
     *
     * @param planeType         1 - for PassengerPlane object, 2 - for CargoPlane object
     * @param serialNumber      - (int) plane serial number
     * @param modelName         - (String) model name
     * @param crew              - amount of crew members
     * @param cargoCapacity     - total capacity for cargo
     * @param passengerCapacity - total capacity for passengers
     * @param engineModel       - Engine(enum class) object
     * @return CargoPlane/PassengerPlane object
     */
    public static Plane constructPlane(int planeType, int serialNumber, String modelName, int crew, int cargoCapacity,
                                       int passengerCapacity, Engine engineModel) {
        switch (planeType) {
            case (1):
                return new PassengerPlane(serialNumber, modelName,
                        crew, cargoCapacity, passengerCapacity, engineModel);
            case (2):
                return new CargoPlane(serialNumber, modelName,
                        crew, cargoCapacity, passengerCapacity, engineModel);
            default:
                return null;
        }
    }

    public String getModelName() {
        return modelName;
    }

    public int getSerialNumber() {
        return SERIAL_NUMBER;
    }

    public int getCrewSize() {
        return crewSize;
    }

    public void setCrewSize(int crewSize) {
        this.crewSize = crewSize;
    }

    public abstract int getPassengerCapacity();

    public abstract int getCargoCapacity();

    public abstract double getFuelConsumption();

    public abstract String toString();

    public abstract boolean equals(Object o);

    public abstract int hashCode();
}
