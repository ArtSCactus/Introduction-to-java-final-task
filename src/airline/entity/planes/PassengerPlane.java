package airline.entity.planes;


import airline.entity.components.Engine;

import java.util.Objects;

public class PassengerPlane extends Plane {
    /**Passengers capacity.
     *
     */
    private int passengerCapacity;
    /**Cargo capacity.
     *
     */
    private int cargoCapacity;
    /**Engine model.
     *
     * @see Engine available engine models.
     */
    private Engine engineModel;
    /**Constructs passenger plane.
     *
     * @param serialNumber - plane's serial number. Cannot be < 0.
     * @param modelName - name of plane model. Cannot be null.
     * @param crew - amount of crew members. Cannot be <= 0.
     * @param cargoCapacity - cargo capacity. Cannot be < 0.
     * @param passengerCapacity - passenger capacity. Cannot be < 0.
     * @param engineModel - engine model. Cannot be null.
     * @see Engine available engine types.
     */
    public PassengerPlane(int serialNumber, String modelName, int crew, int cargoCapacity, int passengerCapacity, Engine engineModel) {
        super(modelName, serialNumber, crew);
        this.cargoCapacity = cargoCapacity;
        this.passengerCapacity = passengerCapacity;
        this.engineModel = engineModel;
    }

    public double getFuelConsumption() {
        return engineModel.getFuelConsumption();
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    public int getCargoCapacity() {
        return cargoCapacity;
    }

    public void setCarryingCapacity(int cargoCapacity) {
        this.cargoCapacity = cargoCapacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PassengerPlane)) return false;
        PassengerPlane that = (PassengerPlane) o;
        return getPassengerCapacity() == that.getPassengerCapacity() &&
                getCargoCapacity() == that.getCargoCapacity() &&
                engineModel == that.engineModel;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPassengerCapacity(), getCargoCapacity(), engineModel);
    }

    @Override
    public String toString() {
        return "Passenger|"+
                getSerialNumber() + "|"
                + getModelName() + "|"
                + passengerCapacity + "|"
                + cargoCapacity + "|"
                + getCrew() + "|"
                + engineModel.getCodeName();
    }
}
