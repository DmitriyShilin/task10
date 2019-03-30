package service;

import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.opencsv.CSVWriter;

import jdbc.ConnectionFactory;

public class ReportToCSV {
	
	private static final String PATH = System.getProperty("user.dir") + File.separator + "data" + File.separator + "report.csv";	
	private static final String PATHRU = System.getProperty("user.dir") + File.separator + "data" + File.separator + "reportRU.csv";
	private static Logger logger = Logger.getLogger(ReportToCSV.class.getName());

	public static void writeToCSV(Timestamp timestampFrom, Timestamp timestampTo) {
		String query = "SELECT * FROM cart WHERE timestamp BETWEEN '" + timestampFrom.toString() + "' AND '" + timestampTo.toString() + "'";
		try(CSVWriter writer = new CSVWriter(new FileWriter(PATH));
			CSVWriter writerRU = new CSVWriter(new FileWriter(PATHRU), ';', '"', '\u0000', "\n");
			Connection conn = ConnectionFactory.getConnection();
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);			
			ResultSet rs = stmt.executeQuery(query);){
			
			writer.writeAll(rs, true);			
			rs.beforeFirst();
			writerRU.writeAll(rs, true);
			
		} catch (Exception e) {
			logger.log(Level.SEVERE, null, e);
		}
	}
}
