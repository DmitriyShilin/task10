package tab;

import bot.BotPromUa;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.BotUser;
import service.EmailValidator;

public class TabRegistration {

	public static Tab createTab() {		
		Tab tabRegistration = new Tab("Registration");
		tabRegistration.setClosable(false);
		VBox vBoxRegistration = new VBox();
		vBoxRegistration.setAlignment(Pos.TOP_CENTER);
		
		Text textName = new Text("Name");	
		VBox.setMargin(textName, new Insets(5.0, 0.0, 0.0, 0.0));
		TextField textFieldName = new TextField();
		textFieldName.setPromptText("Name");
		vBoxRegistration.getChildren().addAll(textName, textFieldName);
		
		Text textPassword = new Text("Password");		
		TextField textFieldPassword = new TextField();
		textFieldPassword.setPromptText("Password");
		vBoxRegistration.getChildren().addAll(textPassword, textFieldPassword);
		
		Text textEmail = new Text("Email");
		TextField textFieldEmail = new TextField();
		textFieldEmail.setPromptText("Email");
		vBoxRegistration.getChildren().addAll(textEmail, textFieldEmail);
		
		Button buttonRegistration = new Button("Create your Prom.ua account");
		vBoxRegistration.getChildren().add(buttonRegistration);
		
		Separator sepRegistration = new Separator();
		sepRegistration.setScaleY(2.0);
		vBoxRegistration.getChildren().add(sepRegistration);
		
		Text message = new Text();
		vBoxRegistration.getChildren().add(message);
		
		vBoxRegistration.setSpacing(5.0);
		tabRegistration.setContent(vBoxRegistration);
		
		startAction(buttonRegistration, textFieldName, textFieldPassword, textFieldEmail, message);
		
		return tabRegistration;
	}
	
	private static void startAction(Button buttonRegistration, TextField textFieldName, TextField textFieldPassword, TextField textFieldEmail, Text message) {
		buttonRegistration.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String name = textFieldName.getCharacters().toString();
				String password = textFieldPassword.getCharacters().toString();
				String email = textFieldEmail.getCharacters().toString();
				if(!name.isEmpty() && !password.isEmpty() && !email.isEmpty() && EmailValidator.validate(email)) {
					message.setText("");
					BotUser user = new BotUser(name, email, password);
					BotPromUa botProm = new BotPromUa(user);
					botProm.getRegistration();
				}
				else message.setText("Enter name, password and email.");
			}
			
		});
	}
	
	
}
