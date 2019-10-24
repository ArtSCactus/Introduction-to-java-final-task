package airline.view;

import airline.entity.components.Engine;
import airline.entity.planes.Plane;
import airline.exceptions.FileParsingException;
import airline.util.Controller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;


/**User interface
 * Realized through console.
 */
public class Viewer {

    private static final Logger LOGGER = LogManager.getLogger(Viewer.class);

    private Controller controller;

    /**
     * Main menu on entrance to application.
     */
    public void run() {
        System.out.println("Hello, welcome to our airline company." +
                "\nPlease, select command:" +
                "\n1-Create company." +
                "\n2-Load company from txt file");
        int choice = inputInt();
        switch (choice) {
            case (1):
                createCompany();
                inCompanyMenu();
                break;
            case(2):
                String inputedRow;
                while(true) {
                    System.out.println("Please, enter path to file (type Exit to exit the application):");
                    inputedRow=inputString();
                    try {
                        controller = new Controller(inputedRow);
                        inCompanyMenu();
                    } catch (FileParsingException e) {
                        LOGGER.error(e.getCustomMessage());
                        if (e.getCustomMessage().equals("No such file by given pass: " +inputedRow)){
                            System.out.println("No file found by given path.");
                        }
                    }
                    if(inputedRow.equals("Exit")){
                        break;
                    }
                }
                break;
            default:
                System.out.println("No such command");
        }
    }
    private void saveCompanyToFile(){
        String filePath;
        System.out.print("Please, enter path to file. If no path specified, " +
                "file wll be created/overwritten by default file path" +
                "\n==>");
        filePath=inputString();
        controller.toFile(filePath);
        System.out.println("File successfully saved by path: "+filePath);
    }
    /**
     * Initializes airline company through console.
     * <p>
     * Asks user to input company name and then calls method {@code createCompany()} from controller.
     *
     * @see Controller#createCompany(String) more information about createCompany()
     */
    private void createCompany() {
        System.out.print("Please, choose the company name: ");
        String companyName = inputString();
        System.out.println("Company " + companyName + " has been successfully created.");
            controller = new Controller(companyName,true);
    }

    /**
     * Module, that carries out entering plane from console.
     * <p>
     * Contains console interface.
     * Reads from console and validates every field of Plane object.
     * After successful reading from console adds new plane to current airline company.
     *
     * @see Viewer#inputIntWithValidation(int, int) more information about {@code inputIntWithValidation(int, int)}
     * @see Viewer#inputIntWithValidation(int, boolean) more information about {@code inputIntWithValidation(key boolean)}
     * @see Viewer#inputInt() more information about {@code inputInt()}
     * @see Viewer#inputEngine() more information about {@code inputEngine()}
     */
    private void inputPlane() {
        System.out.print("Please, choose plane type:" +
                "\n1-Cargo plane" +
                "\n2-Passenger plane" +
                "\n==>");
        int planeTypeCode = inputIntWithValidation(1, 2); //1- minimal value, 2 - maximal value, includes 1 and 2
        switch (planeTypeCode) {
            //Passenger plane construction
            case (1):
                System.out.print("Input serial number more, than 0:\n==>");
                int serialNumber = inputIntWithValidation(1, false); //false fo more than mode
                System.out.print("\nInput model name:\n==>");
                String modelName = inputString();
                System.out.print("\nInput amount of crew members more than 0:\n==>");
                int crew = inputIntWithValidation(1, false); //false for more than mode
                System.out.print("\nInput cargo capacity more than 0: \n==>");
                int cargoCapacity = inputIntWithValidation(1, false);//false for more than mode
                System.out.print("\nInput passenger capacity more than 0:\n==>");
                int passengerCapacity = inputIntWithValidation(1, false);//false for more than mode
                System.out.println("\nInput engine code name(type anything to see available engines):\n");
                Engine engine = inputEngine();
                controller.addPlane(Plane.constructPlane(planeTypeCode, serialNumber, modelName,
                        crew, cargoCapacity, passengerCapacity, engine));
                break;
            //Cargo plane construction
            case (2):
                System.out.print("Input serial number more, than 0:\n==>");
                serialNumber = inputIntWithValidation(1, false); //false fo more than mode
                System.out.print("\nInput model name:\n==>");
                modelName = inputString();
                System.out.print("\nInput amount of crew members more than 0:\n==>");
                crew = inputIntWithValidation(1, false); //false for more than mode
                System.out.print("\nInput cargo capacity more than 0: \n==>");
                cargoCapacity = inputIntWithValidation(1, false);//false for more than mode
                System.out.print("\nInput passenger capacity more than 0:\n==>");
                passengerCapacity = inputIntWithValidation(1, false);//false for more than mode
                System.out.println("\nInput engine code name(type anything to see available engines):\n");
                engine = inputEngine();
                controller.addPlane(Plane.constructPlane(planeTypeCode, serialNumber, modelName,
                        crew, cargoCapacity, passengerCapacity, engine));
                break;
        }
    }

    /**
     * Main menu after airline company initialization.
     * Contains console interface.
     */
    private void inCompanyMenu() {
        int choice;
        System.out.println("You are owner of company " + controller.getCompanyName() + ".");
        while(true) {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~" +
                "\nPlease, choose the command: " +
                    "\nWhat do you want to do?" +
                    "\n1-Take a look at planes list" +
                    "\n2-Add new plane" +
                    "\n3-Change company name" +
                    "\n4-Find plane"+
                "\n5-Save to file"+
                    "\n0-Exit");
          choice = inputInt();
            switch (choice) {
                case (1):
                    printPlanes(controller.getPlanesList());
                    break;
                case (2):
                    inputPlane();
                    break;
                case (3):
                    System.out.print("Enter new company name: ");
                    String newCompanyName = inputString();
                    controller.setCompanyName(newCompanyName);
                    System.out.println("Company name successfully changed on " + newCompanyName);
                    break;
                case (4):
                    searchLauncher();
                    break;
                case(5):
                    saveCompanyToFile();
                    break;
                case(0):
                    System.exit(0);
            }
        }
    }
    private void searchLauncher(){
        System.out.println("Please, choose search type: "+
                "\n1-By serial number;"+
                "\n2-By fuel consumption"+
                "\n3-By fuel consumption between min and maximum"
        +"\n4-By cargo capacity between min and maximum"
        +"\n5-By cargo capacity between min and maximum");
        int choice=inputIntWithValidation(1,4); // minimum- 1, maximum - 4
        switch(choice){
            // by serial number min and max
            case(1):
                System.out.println("Please, input serial number (MIN and MAX)");
                try {
                    printPlanes(SearchEngine.getPlanesBySerialNumber(controller.getPlanesList(),
                            inputIntWithFormatValidation(), inputIntWithFormatValidation()));
                } catch(NoSuchElementException ex){
                    System.out.println("No match found");
                }
                break;
                // by fuel consumption single value
            case(2):
                System.out.println("Please, input fuel consumption");
                try {
                    printPlanes(SearchEngine.getPlanesByFuelConsumption(controller.getPlanesList(),
                            inputDoubleWithFormatValidation()));
                }catch (NoSuchElementException ex){
                    System.out.println("No match found");
                }
                break;
                // by fuel consumption min and max
            case(3):
                System.out.println("Please, input minimal value and maximal value");
                try {
                    printPlanes(SearchEngine.getPlanesByFuelConsumption(controller.getPlanesList(),
                            inputDoubleWithFormatValidation(), inputDoubleWithFormatValidation()));
                }catch(NoSuchElementException ex){
                    System.out.println("No match found");
                }
                break;
                // by cargo capacity
            case(4):
                System.out.println("Please, input minimal value and maximal value");
                try {
                    printPlanes(SearchEngine.getPlanesByCargoCapacity(controller.getPlanesList(),
                            inputIntWithFormatValidation(), inputIntWithFormatValidation()));
                }catch (NoSuchElementException ex){
                    System.out.println("No match found");
                }
                break;
                // by passenger capacity min and max
            case(5):
                System.out.println("Please, input minimal value and maximal value");
                try {
                    printPlanes(SearchEngine.getPlanesByPassengerCapacity(controller.getPlanesList(),
                            inputIntWithFormatValidation(), inputIntWithFormatValidation()));
                }catch (NoSuchElementException ex){
                    System.out.println("No match found");
                }
                break;
        }
    }
    /**
     * Reads int value from console without any validation.
     *
     * @return (int) value
     */
    private int inputInt() {
        Scanner input = new Scanner(System.in);
        return input.nextInt();
    }
    /**
     * Reads int value from console without any validation.
     *
     * @return (double) value
     */
    private double inputDouble() {
        Scanner input = new Scanner(System.in);
        return input.nextDouble();
    }
    /**Reads int value from console with validation on number format.
     *
     * @return (int) value
     */
    private int inputIntWithFormatValidation(){
        int number;
        while (true) {
            try {
                number = inputInt();
                break;
            } catch (InputMismatchException ex) {
                LOGGER.debug("Caught InputMismatchException."+ex.getMessage());
                LOGGER.error("Failed to read number from console.");
                System.out.println("Please, type an INTEGER number. You entered something else");
            }
        }
        return number;
    }
    /**Reads int value from console with validation on number format.
     *
     * @return (double) value
     */
    private double inputDoubleWithFormatValidation(){
        double number;
        while (true) {
            try {
                number = inputDouble();
                break;
            } catch (InputMismatchException ex) {
                LOGGER.debug("Caught InputMismatchException."+ex.getMessage());
                LOGGER.error("Failed to read number from console.");
                System.out.println("Please, type an DOUBLE number (Type number inf format 43,95). You entered something else");
            }
        }
        return number;
    }

    /**
     * Reading integer number from console.
     * <p>
     * Reading from console integer number and validating is it number
     * (catching NumberFormatException) and is it between from and to (includes from and to).
     *
     * @param from minimal value
     * @param to   maximal value
     * @return (int) value
     */
    private int inputIntWithValidation(int from, int to) {
        int number = inputIntWithFormatValidation();
        while (number < from & number > to) {
            System.out.print("Please, choose between " + from + " and " + to);
            try {
                number = inputInt();
            } catch (InputMismatchException ex) {
                LOGGER.debug("Caught InputMismatchException."+ex.getMessage());
                LOGGER.error("Failed to read number from console.");
                System.out.println("Please, type an INTEGER number. You entered something else");
            }
        }
        return number;
    }

    /**
     * Reads from console int number and validating it, depends on mode.
     * <p>
     * Depends on mode and key, reading from console int number and validating it.
     * Set notBigger true to set validation by "is number >k".
     * Set notBigger false to set mode "is number <k".
     *
     * @param key       number, regarding which will be validation bigger or less.
     * @param notBigger true to set validation by "is number >k". Otherwise will be mode "is number <k"
     * @return (int) value
     */
    private int inputIntWithValidation(int key, boolean notBigger) {
        int number = inputIntWithFormatValidation();
        if (notBigger) {
            while (number > key) {
                System.out.print("Please, choose number below " + key);
                try {
                    number = inputInt();
                } catch (InputMismatchException ex) {
                    LOGGER.debug("Caught InputMismatchException."+ex.getMessage());
                    LOGGER.error("Failed to read number from console.");
                    System.out.println("Please, type an INTEGER number. You entered something else");
                }
            }
        } else {
            while (number < key) {
                System.out.print("Please, choose number below " + key);
                try {
                    number = inputInt();
                } catch (InputMismatchException ex) {
                    LOGGER.debug("Caught InputMismatchException."+ex.getMessage());
                    LOGGER.error("Failed to read number from console.");
                    System.out.println("Please, type the number. You entered something else");
                }
            }
        }
        return number;
    }

    /**
     * Reading String row from console.
     *
     * @return (String row from console)
     */
    private String inputString() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    /**
     * Reads engine code name from console.
     * <p>
     * Reads from console string line and calls with it {@code Engine.valueOf()}.
     * If such element wasn't found catching exception and printing to console all engine,
     * to show user available engines.
     *
     * @return Engine object
     * @see Engine more information about Engine
     */
    private Engine inputEngine() {
        Scanner input = new Scanner(System.in);
        String engineModelName;
        while (true) {
            try {
                engineModelName = input.nextLine().toUpperCase();
                return Engine.valueOf(engineModelName);
            } catch (IllegalArgumentException ex) {
                LOGGER.debug("Caught IllegalArgumentException."+ex.getMessage());
                LOGGER.error("Failed to get engine from console.");
                System.out.println("No such engine found. Here's all available engines: ");
                printEnginesList(controller.getEnginesList());
                System.out.print("Input code name here==>");
            }
        }
    }

    /**
     * Prints list of engines to console.
     * <p>
     * Used while catching exception when such engine wasn't found.
     *
     * @param engines {@code List<Engine>} with all available engines
     * @see Viewer#inputEngine() more information about inputEngine()
     */
    private void printEnginesList(List<Engine> engines) {
        for (Engine engine : engines) {
            System.out.println("Model: " + engine.getModel() + " by " + engine.getManufacturer()
                    + "\nFuel consumption: " + engine.getFuelConsumption() + " kg/(kg-n/hour)"//kilogram of fuel per kilogram-force per hour
                    + "\nCode name: " + engine.getCodeName());
        }
    }
    /**
     * Print all planes in airline company to console.
     * <p>
     * Prints information about each plane in airline company to console.
     */
    public static void printPlanes(List<Plane> planes) {
        if(planes.size()==0){
            System.out.println("Airline company does not have any plane.");
            return;
        }
        for (Plane plane : planes) {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                    "Plane: " + plane.getModelName() +
                    "\nSerial number: " + plane.getSerialNumber() +
                    "\nModel name: " + plane.getModelName() +
                    "\nFuel consumption: " + plane.getFuelConsumption() +
                    "\nCrew: " + plane.getCrew() +
                    "\nPassenger capacity: " + plane.getPassengerCapacity()
                    + "\nCargo capacity: " + plane.getCargoCapacity()+
                    "\n~~~~~~~~~~~~~~~~~~~~~~~~");
        }
    }

    /**Prints information about given plane to console.
     *
     * @param plane plane, that will be printed to console.
     */
    public static void printPlane(Plane plane) {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                "Plane: " + plane.getModelName() +
                "\nSerial number: " + plane.getSerialNumber() +
                "\nModel name: " + plane.getModelName() +
                "\nFuel consumption: " + plane.getFuelConsumption() +
                "\nCrew: " + plane.getCrew() +
                "\nPassenger capacity: " + plane.getPassengerCapacity()
                + "\nCargo capacity: " + plane.getCargoCapacity()+
                "\n~~~~~~~~~~~~~~~~~~~~~~~~");

    }
}
