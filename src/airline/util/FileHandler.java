package airline.util;

import airline.exceptions.FileParsingException;
import airline.exceptions.PlaneParseException;
import airline.entity.AirlineCompany;
import airline.entity.components.Engine;
import airline.entity.planes.CargoPlane;
import airline.entity.planes.PassengerPlane;
import airline.entity.planes.Plane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.Scanner;

/**
 * A class, that carries out I/O to/from file.
 */
public class FileHandler {
    private static final Logger LOGGER = LogManager.getLogger(FileHandler.class);
    /**
     * Parses given row and returns Plane object.
     * <p>
     * Reading the row and parsing from it parameters for new plane.
     * Then constructs it and returns.
     *
     * @param row - String row like:
     *           PlaneType|serialNumber|modelName|passengerCapacity|cargoCapacity|crew|engineModel
     * @return airline company object
     * @throws PlaneParseException - if
     */
    public static Plane parseAndConstructPlane(String row) throws PlaneParseException {//поменять название
        String[] parameters = row.split("\\|");
        try {
            switch (parameters[0]) {
                case ("Passenger"):
                    int serialNumber = Integer.parseInt(parameters[1]);
                    String modelName = parameters[2];
                    int passengerCapacity = Integer.parseInt(parameters[3]);
                    int cargoCapacity = Integer.parseInt(parameters[4]);
                    int crew = Integer.parseInt((parameters[5]));
                    Engine engine = Engine.valueOf(parameters[6]);
                    return new PassengerPlane(serialNumber, modelName, crew, cargoCapacity, passengerCapacity, engine);

                case ("Cargo"):
                    serialNumber = Integer.parseInt(parameters[1]);
                    modelName = parameters[2];
                    passengerCapacity = Integer.parseInt(parameters[3]);
                    cargoCapacity = Integer.parseInt(parameters[4]);
                    crew = Integer.parseInt((parameters[5]));
                    engine = Engine.valueOf(parameters[6]);
                    return new CargoPlane(serialNumber, modelName, crew, cargoCapacity, passengerCapacity, engine);

                default:
                    LOGGER.error("Failed to convert plane type");
                    throw new PlaneParseException("Unknown plane type before the first | symbol");
            }
        } catch (NumberFormatException e) {
            LOGGER.error("Failed to convert row to Plane object");
            throw new PlaneParseException("Cannot convert String to Integer");
        }
    }

    /**
     * Writes given information about company and it's planes list to the file.
     * <p>
     * In the first row will be placed name of the company.
     * All lines below will be the result of method {@code toString} from class PassengerPlane/CargoPlane.
     *
     * @param company  AirlineCompany obj.
     * @param filePath - path to file. If it not exists, it will be created.
     * @exception  IOException - if got problems with file location or access.
     * @see PassengerPlane#toString() PassengerPlane toString method.
     * @see CargoPlane#toString() CargoPlane toString method.
     */
    public static void writeCompany(AirlineCompany company, String filePath) {
        File textFile = new File(filePath);
        //checking file on existence. If it not exists, it will be created.
        if (!textFile.exists()) {
            try {
                textFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //Writing company to the file
            try (FileWriter fileOutput = new FileWriter(textFile)){
                fileOutput.write(company.getName() + "\n");
                for (Plane plane : company.getPlanesList()) {
                    fileOutput.write(plane.toString() + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Reads file and creates AirlineCompany obj.
     * <p>
     * Reads first row of file and installing it as name of the company.
     * Then reads all lines below and parsing rows to Plane objects.
     * Modified version of {@code parseFileAndConstructCompany} method. If skipBrokenLineMode is true,
     * the lines, where was met {@code PlaneParseException} will be skipped and information about it will be printed to
     * log by logger. Otherwise, the method will work as default variant.
     *
     * @param filePath - path to the file.
     * @return AirlineCompany obj.
     * @see PlaneParseException for more information about PlaneParseException
     * @throws FileParsingException - if FileNotFoundException, PlaneParseException, IOException, are caught.
     * In each case will be returned message of caught exceptions.
     */
    public static AirlineCompany parseFileAndConstructCompany(String filePath, boolean skipBrokenLineMode) throws FileParsingException {// переимновать
        AirlineCompany company;
        try (FileReader fileInput = new FileReader(filePath)){
            Scanner input = new Scanner(fileInput);
            company = new AirlineCompany(input.nextLine());
            String currentLine;
            while (input.hasNextLine()) {
                currentLine=input.nextLine();
                try{
                company.addPlane(FileHandler.parseAndConstructPlane(currentLine));
            } catch(PlaneParseException e) {
                    if (skipBrokenLineMode) {
                        LOGGER.info("Skipped broken line");
                        LOGGER.debug("A broken line was skipped. Variables status: {currentLine=\""+currentLine
                                +"\", skipBrokenLinMode=\""+skipBrokenLineMode+"\"}");
                    } else {
                        LOGGER.debug("Current line"+currentLine);
                        LOGGER.error("A broken line was met. skipBrokenLineMode is false. Throwing FileParsingException");
                        throw new FileParsingException("An error occurred while parsing string row as Plane object. Message: " + e.getMessage());
                    }
            }
            }
            fileInput.close(); // just in case
            return company;
        } catch (FileNotFoundException e) {
            LOGGER.debug("No file found. Given file path:  "+filePath);
            LOGGER.error("No file found by given pass.");
            throw new FileParsingException("No such file by given pass: " + filePath);
        } catch (IOException e) {
            LOGGER.debug("Caught IOException: "+e.getMessage());
            LOGGER.error("Caught IOException.");
           throw new FileParsingException("An error with Input/Output stream. Message: "+e.getMessage());
        }
    }
    /**
     * Reads file and creates AirlineCompany obj.
     * <p>
     * Reads first row of file and installing it as name of the company.
     * Then reads all lines below and parsing rows to Plane objects.
     * This is a default realization of method{@code parseFileAndConstructCompany()}.
     * If in the process of file parsing and object constructing will be met any exception, the method will be stopped
     * and will be thrown FileParsingException with message, that describes the situation.
     *
     * @param filePath - path to the file.
     * @return AirlineCompany obj.
     * @throws FileParsingException - if FileNotFoundException, PlaneParseException, IOException, are caught.
     * In each case will be returned message of caught exceptions.
     */
    public static AirlineCompany parseFileAndConstructCompany(String filePath) throws FileParsingException {// переимновать
        AirlineCompany company;
        try (FileReader fileInput = new FileReader(filePath)){
            Scanner input = new Scanner(fileInput);
            company = new AirlineCompany(input.nextLine());
            String currentLine;
            while (input.hasNextLine()) {
                currentLine=input.nextLine();
                try{
                    company.addPlane(FileHandler.parseAndConstructPlane(currentLine));
                } catch(PlaneParseException e) {
                    LOGGER.debug("Failed to convert row to Plane object. Current line: "+currentLine);
                    LOGGER.error("Failed to convert row to Plane object.");
                        throw new FileParsingException("An error occurred while parsing string row as Plane object. Message: " + e.getMessage());
                    }
                }
            fileInput.close(); // just in case.
            return company;
        } catch (FileNotFoundException e) {
            LOGGER.debug("No file found. Given file path:  "+filePath);
            LOGGER.error("No file found by given pass.");
            throw new FileParsingException("No such file by given pass: " + filePath);
        } catch (IOException e) {
            LOGGER.debug("Caught IOException: "+e.getMessage());
            LOGGER.error("Caught IOException.");
            throw new FileParsingException("An error with Input/Output stream. Message: "+e.getMessage());
        }
    }
}
