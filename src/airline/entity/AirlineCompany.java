package airline.entity;


import airline.entity.comparators.CompareType;
import airline.entity.planes.Plane;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AirlineCompany {
    /**
     * Company's name.
     */
    private String name;
    /**
     * List of planes that contains this company.
     */
    private List<Plane> planesList;

    /**
     * Initializes company with given name.
     * <p>
     * Also initializes {@code planesList}  as empty ArrayList.
     * Uses to initialize an empty company with given company name.
     *
     * @param name - company's name. Cannot be null.
     */
    public AirlineCompany(String name) {
        this.name = name;
        planesList = new ArrayList<>();
    }

    /**
     * Initializes airline company with given name and planes list.
     * <p>
     * Uses to initialize airline company with already existing (or constructed before)
     * planes list and given name.
     *
     * @param name       - company's name
     * @param planesList - already existing list of planes.
     */
    public AirlineCompany(String name, List<Plane> planesList) {
        this.name = name;
        this.planesList = planesList;
    }

    /**Adds constructed Plane object.
     *
     * @param newPlane - already constructed and validated Plane object.
     */
    public void addPlane(Plane newPlane) {
        planesList.add(newPlane);
    }

    /**Deletes plane by it's index in planes list.
     *
     * @param index - number of plane in {@code planesList}
     */
    public void deletePlane(int index) {
        planesList.remove(index);
    }

    /**Deletes first met plane in plane's list by given model name.
     *
     * @param modelName String row, that describes plane model name
     */
    public void deletePlane(String modelName) {
        for (int index = 0; index < planesList.size(); index++) {
            if (planesList.get(index).getModelName().equals(modelName)) {
                planesList.remove(index);
                break;
            }
        }
    }

    /**Setting new planes list.
     *
     * Setting new realization of {@code List<Plane>}.
     * Can be used to reinitialize current {@code planesList}
     *
     * @param planesList - {@code List<Plane>} realization
     */
    public void setPlanesList(List<Plane> planesList) {
        this.planesList = planesList;
    }

    public List<Plane> getPlanesList() {
        return planesList;
    }

    public int amountOfPlanes() {
        return planesList.size();
    }

    public Plane getPlane(int index) {
        return planesList.get(index);
    }

    public Plane getPlane(String modelName) {
        for (Plane plane : planesList) {
            if (plane.getModelName().equals(modelName)) {
                return plane;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void sortPlanes(CompareType sortMode) {
        planesList.sort(sortMode);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AirlineCompany)) return false;
        AirlineCompany that = (AirlineCompany) o;
        return getName().equals(that.getName()) &&
                getPlanesList().equals(that.getPlanesList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPlanesList());
    }

    private String toStringPlanes() {
        String planesRow = "";
        for (Plane plane : planesList) {
            planesRow += plane.toString() + "\n";
        }
        return planesRow;
    }

    @Override
    public String toString() {
        return "AirlineCompany: " +
                "name = " + name +
                " planes list:\n" + toStringPlanes();
    }
}
