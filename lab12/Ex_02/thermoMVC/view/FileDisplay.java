package thermoMVC.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import thermoMVC.model.Thermometer;
import thermoMVC.model.ThermometerListener;

public class FileDisplay extends JPanel implements ThermometerListener {

	// The Unicode symbol for degrees
	private static final char DEGREE_SYMBOL = '\u00B0';

    /** The thermometer whose temperature is being displayed */
	protected Thermometer thermometer;

    /** The file where the temperatures will be saved */
    private File outputFile;

    public FileDisplay(Thermometer t){
        this.thermometer = t;
        File tempFile = new File("outputFile.txt");
        tempFile.delete();
        this.outputFile = new File("outputFile.txt");
        System.out.println("File created: " + outputFile.getName());
    }

    private String getDisplayString(){
        double celsius = (thermometer.getTemperature() - 32) * 1.8;
        return "" + celsius + DEGREE_SYMBOL + " C\n"; 
    }

    @Override
    public void temperatureChanged() {
        FileWriter fWriter;
        try {
            fWriter = new FileWriter(this.outputFile, true);
            fWriter.write(getDisplayString());
            fWriter.close();
        } catch (IOException e) {
            System.out.println("Can't create fileWriter");
        }
    }
    
}
