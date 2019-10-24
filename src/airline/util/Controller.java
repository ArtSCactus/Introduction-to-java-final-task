package airline.util;

import airline.exceptions.FileParsingException;
import airline.entity.AirlineCompany;
import airline.entity.comparators.CompareType;
import airline.entity.components.Engine;
import airline.entity.planes.Plane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class Controller {
    /**
     * Main AirlineCompany (planes list) storage.
     */
    private AirlineCompany company;
    private static final Logger LOGGER = LogManager.getLogger(Controller.class);

    public Controller(String filePath) throws FileParsingException {
       company=FileHandler.parseFileAndConstructCompany(filePath);
    }

    public Controller(String companyName, boolean notFromFile){
        company= new AirlineCompany(companyName);
    }

    /**
     * Constructs empty airline company controller.
     * <p>
     * Name of the company will be installed as "Unnamed company".
     * List of planes will be installed as empty, but not null.
     */
    Controller()
    {
        company = new AirlineCompany("Unnamed company");
    }

    /**
     * Constructs airline company controller, that based on already exists object.
     * <p>
     * Initializes controller with already built AirlineCompany object.
     *
     * @param company - already exists object or object, that was built before
     */
    Controller(AirlineCompany company) {
        this.company = company;
    }

    /**
     * Constructs custom airline company controller.
     * <p>
     * Constructs airline company object with custom name and custom planes list realization.
     *
     * @param companyName     - String type row with company name
     * @param listRealization - custom realization of List interface or already built list of planes.
     */
    Controller(String companyName, List<Plane> listRealization) {
        company = new AirlineCompany(companyName, listRealization);
    }

    /**
     * Returns name of airline company.
     *
     * @return {@code company.getName()}
     */
    public String getCompanyName() {
        return company.getName();
    }

    /**Returns company's planes list.
     *
     * @return {@code List<plane>}
     */
    public List<Plane> getPlanesList() {
        return company.getPlanesList();
    }

    public Plane getPlane(int index) {
        return company.getPlane(index);
    }

    /**Returns plane by model name.
     *
     * Returns first met plane with given model name.
     *
     * @param modelName - (String) model name
     * @return Plane obj
     */
    public Plane getPlane(String modelName) {
        if (modelName == null) {
            throw new NullPointerException("Model name cannot be null");
        }
        return company.getPlane(modelName);
    }


    /**
     * Appropriation new empty object of AirlineCompany.
     *
     * @param name - name of new company
     */
    public void createCompany(String name) {
        if (name == null) {
            LOGGER.error("Company name is null");
            throw new NullPointerException("Company name cannot be null.");
        }
        if (name.length() == 0) {
            LOGGER.error("Empty company name");
            throw new IllegalArgumentException("Company name cannot be empty");
        }
        company = new AirlineCompany(name);
    }

    /**
     * Counts total passenger capacity for current company.
     *
     * @return (int) total passenger capacity
     */
    public int totalPassengerCapacity() {
        int totalCapacity = 0;
        for (int index = 0; index < company.amountOfPlanes(); index++) {
            totalCapacity += company.getPlane(index).getPassengerCapacity();
        }
        return totalCapacity;
    }

    /**
     * Counts total cargo capacity for current company.
     *
     * @return (int) total cargo capacity
     */
    public int totalCargoCapacity() {
        int totalCapacity = 0;
        for (int index = 0; index < company.amountOfPlanes(); index++) {
            totalCapacity += company.getPlane(index).getCargoCapacity();
        }
        return totalCapacity;
    }

    /**Adds already existing Plane object.
     *
     * @param plane
     * @throws NullPointerException if plane are null
     */
    public void addPlane(Plane plane) {
        if (plane==null){
            LOGGER.error("Plane object came with null");
            throw new NullPointerException("Plane cannot be null.");
        }
        company.addPlane(plane);
    }

    /**Constructs and adds new plane to company's planes list.
     *
     * @param planeType         1 - for PassengerPlane object, 2 - for CargoPlane object
     * @param serialNumber      - (int) plane serial number
     * @param modelName         - (String) model name
     * @param crew              - amount of crew members
     * @param cargoCapacity     - total capacity for cargo
     * @param passengerCapacity - total capacity for passengers
     * @param engineModel       - Engine(enum class) object
     */
    public void addPlane(int planeType, int serialNumber, String modelName, int crew, int cargoCapacity,
                         int passengerCapacity, Engine engineModel) {
        company.addPlane(Plane.constructPlane(planeType, serialNumber, modelName, crew, cargoCapacity,
                passengerCapacity, engineModel));
    }

    /**
     * Returns Plane object by given fuel consumption.
     * <p>
     * Returns first met plane with given fuel consumption.
     *
     * @param fuelConsumption - (double) value,
     * @return Plane type object from list of planes
     * @throws NoSuchElementException if plane with such parameters wasn't found
     * @see AirlineCompany more information about list of planes
     * @see com.completedtasks.airline.entity.components.Engine more information about fuel consumption
     */
    public Plane getPlaneByFuelConsumption(List<Plane> planes, double fuelConsumption) throws NoSuchElementException {
        for (int index = 0; index < company.amountOfPlanes(); index++) {
            if (company.getPlane(index).getFuelConsumption() == fuelConsumption) {
                return company.getPlane(index);
            }
        }
        LOGGER.info("No founded plane");
        throw new NoSuchElementException("No such plane found");
    }

    public Plane getPlaneByModelName(String modelName){
      return company.getPlane(modelName);
    }

    public void toFile(String filePath){
        if (filePath==null){
            LOGGER.debug("Given file path: "+filePath);
            LOGGER.error("File path came with null");
            throw new NullPointerException("File path cannot be null");
        }
        FileHandler.writeCompany(company,filePath);
    }
    /**
     * Sorts planes list.
     * <p>
     * Sorts planes list, that placed in AirlineCompany with given mode.
     *
     * @param mode - CompareType object.
     * @see CompareType for more information about available mods.
     */
    public void sortPlanesList(CompareType mode) {
        company.sortPlanes(mode);
    }

    /**
     * Sorts planes list with default mode.
     * <p>
     * Sorts planes list by serial number.
     *
     * @see CompareType#BY_SERIAL_NUMBER for more information about BY_SERIAL_NUMBER mode
     */
    public void sortPlanesList() {
        company.sortPlanes(CompareType.BY_SERIAL_NUMBER);
    }

    /**Returns list of engines from Engine enum.
     *
     * Calls method {@code Arrays.asList(Engine.values())}
     *
     * @return {@code List<Engine>}
     */
    public List<Engine> getEnginesList(){
        return Arrays.asList(Engine.values());
    }

    /**Setter for company name.
     *
     * @param name - new company name.
     * @exception NullPointerException if name is null
     */
    public void setCompanyName(String name){
        if (name==null){
            LOGGER.error("Company name is null");
            throw new NullPointerException("Company name cannot be null");
        }
        company.setName(name);
    }

}
