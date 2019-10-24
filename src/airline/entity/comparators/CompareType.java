package airline.entity.comparators;

import airline.entity.planes.Plane;

import java.util.Comparator;

/**Comparators that are using while sorting planes list in class AirlineCompany.
 *
 */
public enum CompareType implements Comparator<Plane> {
    /**Sorting by passenger capacity in ascending order.
     *
     */
    BY_PASSENGER_CAPACITY(){
        @Override
        public int compare (Plane ob1, Plane ob2){
            if (ob1.getPassengerCapacity() == ob2.getPassengerCapacity()) {
                return 0;
            }
            if (ob1.getPassengerCapacity() > ob2.getPassengerCapacity()) {
                return 1;
            }
            else {
                return -1;
            }
        }
    },
    /**Sorting by cargo capacity in ascending order.
     *
     */
    BY_CARGO_CAPACITY(){
        @Override
        public int compare (Plane ob1, Plane ob2){
            if (ob1.getCargoCapacity() == ob2.getCargoCapacity()) {
                return 0;
            }
            if (ob1.getCargoCapacity() > ob2.getCargoCapacity()) {
                return 1;
            }
            else {
                return -1;
            }
        }
    },
    /**Sorting by plane serial number in ascending order.
     *
     */
    BY_SERIAL_NUMBER(){
        @Override
        public int compare (Plane ob1, Plane ob2){
            if (ob1.getSerialNumber() == ob2.getSerialNumber()) {
                return 0;
            }
            if (ob1.getSerialNumber() > ob2.getSerialNumber()) {
                return 1;
            }
            else {
                return -1;
            }
        }
    },
    /**Sorting by alphabetic order model names.
     *
     */
    BY_MODEL_NAME(){
        @Override
        public int compare (Plane ob1, Plane ob2) {
           return (ob1.getModelName().compareTo(ob2.getModelName()));
        }
    },
    /**Sorting by serial number and model name (alphabetic order) in ascending order.
     *
     */
    BY_SERIAL_NUM_AND_MODEL(){
        @Override
        public int compare (Plane ob1, Plane ob2){
            if (ob1.getSerialNumber() == ob2.getSerialNumber() &
                    ob1.getModelName().compareTo(ob2.getModelName())==0) {
                return 0;
            }
            if (ob1.getSerialNumber() > ob2.getSerialNumber()&
                    ob1.getModelName().compareTo(ob2.getModelName())>0) {
                return 1;
            }
            else {
                return -1;
            }
        }
    }

}
