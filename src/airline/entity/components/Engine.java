package airline.entity.components;

public enum Engine {

    PW_JT8D("PW_JT8D","Pratt & Whitney ", "JT8D", 0.744),
    PW_JT9D("PW_JT9D","Pratt & Whitney ", "JT9D", 0.646),
    GE_GE90("GE_GE90","General Electric", "GE90", 0.49 );

    private String model;

    private String manufacturer;

    /**Engine's code name.
     * Using to store name of engine model in the file for the purpose of further read.
     */
    private String codeName;

    /**Fuel consumption for current engine.
     *
     */
    private double fuelConsumption;


    Engine(String codeName, String manufacturer, String model, double fuelConsumption){
        this.codeName=codeName;
        this.manufacturer=manufacturer;
        this.model=model;
        this.fuelConsumption=fuelConsumption;
    }

    public String getModel() {
        return model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public String getCodeName() {
        return codeName;
    }

    @Override
    public String toString() {
        return "Engine{" +
                "model='" + model + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", fuelConsumption=" + fuelConsumption +
                '}';
    }
}
