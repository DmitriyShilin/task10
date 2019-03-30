package service;

import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.opencsv.CSVWriter;

import jdbc.ConnectionFactory;

public class ReportToCSV {
	
	private static final String PATH = System.getProperty("user.dir") + File.separator + "data" + File.separator + "report.csv";	
	private static Logger logger = Logger.getLogger(ReportToCSV.class.getName());

	public static void writeToCSV(Timestamp timestampFrom, Timestamp timestampTo) {
		String query = "SELECT * FROM cart WHERE timestamp BETWEEN '" + timestampFrom.toString() + "' AND '" + timestampTo.toString() + "'";
		try(CSVWriter writer = new CSVWriter(new FileWriter(PATH));
			Connection conn = ConnectionFactory.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query)){
			
			writer.writeAll(rs, true);
			
		} catch (Exception e) {
			logger.log(Level.SEVERE, null, e);
		}
	}
}
