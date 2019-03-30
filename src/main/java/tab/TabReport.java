package tab;

import java.sql.Timestamp;
import java.time.LocalDate;

import com.sun.javafx.scene.control.skin.DatePickerSkin;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import service.ReportToCSV;

public class TabReport {
	
	public static Tab createTab() {
		
		Tab tabReport = new Tab("Report");
		tabReport.setClosable(false);
		VBox vBoxReport = new VBox();
		vBoxReport.setAlignment(Pos.TOP_CENTER);
		
		Text textFrom = new Text("From this date");
		VBox.setMargin(textFrom, new Insets(5.0, 0.0, 0.0, 0.0));
		vBoxReport.getChildren().add(textFrom);
		
		TextField textFieldReportFrom = new TextField();
		vBoxReport.getChildren().add(new ReportChoice(textFieldReportFrom).choice());
		vBoxReport.getChildren().add(textFieldReportFrom);
		
		Text textTo = new Text("To next date");	
		vBoxReport.getChildren().add(textTo);
		
		TextField textFieldReportTo = new TextField();
		vBoxReport.getChildren().add(new ReportChoice(textFieldReportTo).choice());
		vBoxReport.getChildren().add(textFieldReportTo);
		
		Button buttonReport = new Button("Report to .csv file");
		vBoxReport.getChildren().add(buttonReport);
		
		Text message = new Text();
		vBoxReport.getChildren().add(message);
		
		vBoxReport.setSpacing(5.0);
		tabReport.setContent(vBoxReport);
		
		startActionDatePickerTo(buttonReport, textFieldReportFrom, textFieldReportTo, message);
		
		return tabReport;
	}
	
	private static void startActionDatePickerTo(Button buttonReport, TextField textFieldReportFrom, TextField textFieldReportTo, Text message) {		
		buttonReport.setOnAction(new EventHandler<ActionEvent>() {			
			@Override
			public void handle(ActionEvent event) {
				message.setText("");
				String strFrom = textFieldReportFrom.getCharacters().toString();
				String strTo = textFieldReportTo.getCharacters().toString();
				try {
					Timestamp timestampFrom =  Timestamp.valueOf(strFrom);
					Timestamp timestampTo =  Timestamp.valueOf(strTo);
					checkTimestamp(timestampFrom, timestampTo);
					ReportToCSV.writeToCSV(timestampFrom, timestampTo);
				} catch (IllegalArgumentException e) {
					message.setText("Incorrec timestamp");
				}				
			}
		});
	}
	
	private static void checkTimestamp(Timestamp timestampFrom, Timestamp timestampTo) throws IllegalArgumentException{
		Long timeNow = System.currentTimeMillis();
		Long timeFrom = timestampFrom.getTime();
		Long timeTo = timestampTo.getTime();
		if(timeNow<timeFrom || timeNow<timeTo || timeTo<timeFrom) throw new IllegalArgumentException();
	}	
}
