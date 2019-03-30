package tab;

import java.time.LocalDate;

import com.sun.javafx.scene.control.skin.DatePickerSkin;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class ReportChoice {
	
	private TextField textField;
	private String strDate = LocalDate.now().toString();
	private String strHour = "00";
	private String strMinutes = "00";
	private String strSeconds = "00";
	
	public ReportChoice(TextField textField) {
		this.textField = textField;
		setTimestamp();
	}

	public HBox choice() {
		
		HBox hBox = new HBox();		
		
		Text textCalendar = new Text("Calendar: ");
		Text textTime = new Text(" Time: ");
		Text textHour = new Text(" hour ");
		Text textMinutes = new Text(" minutes ");
		Text textSeconds = new Text(" seconds ");
		
		//calendar
		DatePicker datePicker = new DatePicker(LocalDate.now());
		//Start action calendar
		startActionDatePicker(datePicker);
		DatePickerSkin datePickerSkin = new DatePickerSkin(datePicker);
		Node p = datePickerSkin.getPopupContent();
		TitledPane titledPane = new TitledPane();
		titledPane.setExpanded(false);
		titledPane.setContent(p);
		
		//Hour Minutes Second 
		ComboBox<Integer> comboBoxHour = new ComboBox<>();	
		ComboBox<Integer> comboBoxMinutes = new ComboBox<>();	
		ComboBox<Integer> comboBoxSeconds = new ComboBox<>();	
		for(int i=0; i<25; i++) {
			comboBoxHour.getItems().add(i);
		}	
		for(int i=0; i<61; i++) {
			comboBoxMinutes.getItems().add(i);
			comboBoxSeconds.getItems().add(i);
		}
		
		//Start action time
		startActionBoxHour(comboBoxHour);
		startActionBoxMinutes(comboBoxMinutes);
		startActionBoxSeconds(comboBoxSeconds);
		
		hBox.getChildren().addAll(textCalendar, titledPane, textTime, textHour, comboBoxHour, textMinutes, comboBoxMinutes, textSeconds, comboBoxSeconds);		
		
		return hBox;
	}
	
	private void startActionDatePicker(DatePicker datePicker) {		
		datePicker.setOnAction(new EventHandler<ActionEvent>() {			
			@Override
			public void handle(ActionEvent event) {
				LocalDate date = datePicker.getValue();
				strDate = date.toString();
				setTimestamp();
			}
		});
	}
	
	private void startActionBoxHour(ComboBox<Integer> comboBoxHour) {		
		comboBoxHour.setOnAction(new EventHandler<ActionEvent>() {			
			@Override
			public void handle(ActionEvent event) {
				Integer hour = comboBoxHour.getValue();
				if(hour<10) strHour = "0" + hour;
				else strHour = hour.toString();
				setTimestamp();
			}
		});
	}
	
	private void startActionBoxMinutes(ComboBox<Integer> comboBoxMinutes) {		
		comboBoxMinutes.setOnAction(new EventHandler<ActionEvent>() {			
			@Override
			public void handle(ActionEvent event) {
				Integer minutes = comboBoxMinutes.getValue();
				if(minutes<10) strMinutes = "0" + minutes;
				else strMinutes = minutes.toString();
				setTimestamp();
			}
		});
	}
	
	private void startActionBoxSeconds(ComboBox<Integer> comboBoxSeconds) {		
		comboBoxSeconds.setOnAction(new EventHandler<ActionEvent>() {			
			@Override
			public void handle(ActionEvent event) {
				Integer seconds = comboBoxSeconds.getValue();
				if(seconds<10) strSeconds = "0" + seconds;
				else strSeconds = seconds.toString();
				setTimestamp();
			}
		});
	}
	
	private void setTimestamp() {
		textField.setText(strDate + " " + strHour + ":" + strMinutes + ":" + strSeconds);
	}
}
