package airline.util;

import airline.entity.planes.Plane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class SearchEngine {
    private static final Logger LOGGER = LogManager.getLogger(SearchEngine.class);

    /**
     * Returns list of planes with matched model name.
     *
     * @param modelName (String) type row with model name
     * @return {@code List<Plane> foundedPLanes as ArrayList<>()}
     * @throws NullPointerException if planes or modelName are null
     */
    public static List<Plane> getPlanesByModel(List<Plane> planes, String modelName) {
        if (planes == null) {
            LOGGER.warn("Planes list came as null. NullPointerException has been thrown");
            throw new NullPointerException("Plane list cannot be null");
        }
        if (modelName == null) {
            LOGGER.warn("Model name came as null. NullPointerException has been thrown");
            throw new NullPointerException("Model name cannot be null");
        }
        List<Plane> foundedPlanes = new ArrayList<>();
        boolean noMatches = true;
        for (Plane plane : planes) {
            if (plane.getModelName().equalsIgnoreCase(modelName)) {
                foundedPlanes.add(plane);
                noMatches = false;
            }
        }
        if (noMatches) {
            throw new NoSuchElementException("No match found");
        }
        return foundedPlanes;
    }

    /**
     * Returns list of matched planes.
     * <p>
     * Returns list of matched planes with serial number, that placed between min and max values
     * (includes min and max).
     * If min value will be bigger than max value, values of min and max will be swapped.
     *
     * @param planes {@code List<Plane>}
     * @param min    minimal value of serial number
     * @param max    maximal value of serial number
     * @return {@code List<Plane> foundedPLanes as ArrayList<>()}
     * @throws NullPointerException if planes are null
     */
    public static List<Plane> getPlanesBySerialNumber(List<Plane> planes, int min, int max) {
        if (planes == null) {
            LOGGER.warn("Planes list came as null. NullPointerException has been thrown");
            throw new NullPointerException("Plane list cannot be null");
        }
        if (min < 0 || max < 0) {
            LOGGER.debug("Min: " + min + " Max: " + max);
            LOGGER.error("Min or max (or both) came as <0 ");
            throw new IllegalArgumentException("Minimal or maximal serial number cannot be less than 0");
        }
        if (min > max) {
            min = min + max;
            max = min - max;
            min = min - max;
            LOGGER.warn("Min and max variables came as min>max. Variables has been swapped.");
            LOGGER.debug("min>max. min: " + min + " max: " + max);
        }
        List<Plane> foundedPlanes = new ArrayList<>();
        boolean noMatches = true;
        for (Plane plane : planes) {
            if (plane.getSerialNumber() >= min & plane.getSerialNumber() <= max) {
                foundedPlanes.add(plane);
                noMatches = false;
            }
        }
        if (noMatches) {
            LOGGER.warn("No matcher founded in search. No such element exception has been thrown");
            throw new NoSuchElementException("No match found");
        }
        return foundedPlanes;
    }

    /**
     * Returns list of planes with matched fuel consumption.
     * <p>
     * Returns list of matched planes with fuel consumption, that matches the given fuel consumption.
     *
     * @param planes {@code List<Plane>}
     * @param fuelConsumption (double) value of fuel consumption
     * @return {@code List<Plane> foundedPLanes as ArrayList<>()}
     * @throws NullPointerException if planes are null
     */
    public static List<Plane> getPlanesByFuelConsumption(List<Plane> planes, double fuelConsumption) {
        if (planes == null) {
            LOGGER.warn("Planes list came as null. NullPointerException has been thrown");
            throw new NullPointerException("Plane list cannot be null");
        }
        if (fuelConsumption < 0) {
            LOGGER.debug("Fuel consumption is <0: " + fuelConsumption);
            LOGGER.error("Fuel consumption is less than 0.");
            throw new IllegalArgumentException("Fuel consumption cannot be less than 0");
        }
        boolean noMatches = true;
        List<Plane> foundedPlanes = new ArrayList<>();
        for (Plane plane : planes) {
            if (plane.getFuelConsumption() == fuelConsumption) {
                foundedPlanes.add(plane);
                noMatches = false;
            }
        }
        if (noMatches) {
            LOGGER.warn("No matcher founded in search. No such element exception has been thrown");
            throw new NoSuchElementException("No match found");
        }
        return foundedPlanes;
    }

    /**
     * Returns list of matched planes with matched fuel consumption.
     * <p>
     * Returns list of matched planes with serial number, that placed between min and max values
     * (includes min and max).
     * If min value will be bigger than max value, values of min and max will be swapped.
     *
     * @param planes {@code List<Plane>}
     * @param min    (double) minimal value of fuel consumption
     * @param max    (double) maximal value of fuel consumption
     * @return {@code List<Plane> foundedPLanes as ArrayList<>()}
     * @throws NullPointerException if planes are null
     */
    public static List<Plane> getPlanesByFuelConsumption(List<Plane> planes, double min, double max) {
        if (planes == null) {
            LOGGER.warn("Planes list came as null. NullPointerException has been thrown");
            throw new NullPointerException("Plane list cannot be null");
        }
        if (min < 0 || max < 0) {
            LOGGER.debug("Min: " + min + " Max: " + max);
            LOGGER.error("Min or max (or both) came as <0.");
            throw new IllegalArgumentException("Minimal or maximal fuel consumption cannot be less than 0");
        }
        if (min > max) {
            min = min + max;
            max = min - max;
            min = min - max;
        }
        boolean noMatches = true;
        List<Plane> foundedPlanes = new ArrayList<>();
        for (Plane plane : planes) {
            if (plane.getFuelConsumption() >= min & plane.getFuelConsumption() <= max) {
                foundedPlanes.add(plane);
                noMatches = false;
            }
        }
        if (noMatches) {
            LOGGER.warn("No matcher founded in search. No such element exception has been thrown");
            throw new NoSuchElementException("No match found");
        }
        return foundedPlanes;
    }

    /**
     * Returns list of planes with matched cargo capacity.
     * <p>
     * Returns list of matched planes with serial number, that placed between min and max values
     * (includes min and max).
     * If min value will be bigger than max value, values of min and max will be swapped.
     *
     * @param planes {@code List<Plane>}
     * @param min    (double) minimal value of cargo capacity
     * @param max    (double) maximal value of cargo capacity
     * @return {@code List<Plane> foundedPLanes as ArrayList<>()}
     * @throws NullPointerException if planes are null
     */
    public static List<Plane> getPlanesByCargoCapacity(List<Plane> planes, int min, int max) {
        if (planes == null) {
            LOGGER.warn("Planes list came as null. NullPointerException has been thrown");
            throw new NullPointerException("Plane list cannot be null");
        }
        if (min < 0 || max < 0) {
            LOGGER.debug("Min: " + min + " Max: " + max);
            LOGGER.error("Min or max (or both) came as <0 ");
            throw new IllegalArgumentException("Minimal or maximal cargo capacity cannot be less than 0");
        }
        if (min > max) {
            min = min + max;
            max = min - max;
            min = min - max;
        }
        boolean noMatches = true;
        List<Plane> foundedPlanes = new ArrayList<>();
        for (Plane plane : planes) {
            if (plane.getCargoCapacity() >= min & plane.getCargoCapacity() <= max) {
                foundedPlanes.add(plane);
                noMatches = false;
            }
        }
        if (noMatches) {
            LOGGER.warn("No matcher founded in search. No such element exception has been thrown");
            throw new NoSuchElementException("No match found");
        }
        return foundedPlanes;
    }

    /**
     * Returns list of planes with matched passenger capacity.
     * <p>
     * Returns list of matched planes with serial number, that placed between min and max values
     * (includes min and max).
     * If min value will be bigger than max value, values of min and max will be swapped.
     *
     * @param planes {@code List<Plane>}
     * @param min    (int) minimal value of passenger capacity
     * @param max    (int) maximal value of passenger capacity
     * @return {@code List<Plane> foundedPLanes as ArrayList<>()}
     * @throws NullPointerException if planes are null
     */
    public static List<Plane> getPlanesByPassengerCapacity(List<Plane> planes, int min, int max) {
        if (planes == null) {
            LOGGER.warn("Planes list came as null. NullPointerException has been thrown");
            throw new NullPointerException("Plane list cannot be null");
        }
        if (min < 0 || max < 0) {
            LOGGER.debug("Min: " + min + " Max: " + max);
            LOGGER.error("Min or max (or both) came as <0 ");
            throw new IllegalArgumentException("Minimal or maximal cargo capacity cannot be less than 0");
        }
        if (min > max) {
            min = min + max;
            max = min - max;
            min = min - max;
        }
        boolean noMatches = true;
        List<Plane> foundedPlanes = new ArrayList<>();
        for (Plane plane : planes) {
            if (plane.getPassengerCapacity() >= min & plane.getPassengerCapacity() <= max) {
                foundedPlanes.add(plane);
                noMatches = false;
            }
        }
        if (noMatches) {
            LOGGER.warn("No matcher founded in search. No such element exception has been thrown");
            throw new NoSuchElementException("No match found");
        }
        return foundedPlanes;
    }
}
