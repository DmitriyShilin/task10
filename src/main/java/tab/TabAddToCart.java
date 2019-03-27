package tab;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;

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

public class TabAddToCart {
	
	public static Tab createTab() {		
		Tab tabAdd = new Tab("Add to cart");
		tabAdd.setClosable(false);
		VBox vBoxAdd = new VBox();
		vBoxAdd.setAlignment(Pos.TOP_CENTER);
		
		Text textUrl = new Text("Url");	
		VBox.setMargin(textUrl, new Insets(5.0, 0.0, 0.0, 0.0));
		TextField textFieldUrl = new TextField();
		textFieldUrl.setPromptText("Url");
		vBoxAdd.getChildren().addAll(textUrl, textFieldUrl);
		
		Text textPasswordSignIn = new Text("Password");		
		TextField textFieldPasswordSignIn = new TextField();
		textFieldPasswordSignIn.setPromptText("Password");
		vBoxAdd.getChildren().addAll(textPasswordSignIn, textFieldPasswordSignIn);
		
		Text textEmailSignIn = new Text("Email");		
		TextField textFieldEmailSignIn = new TextField();
		textFieldEmailSignIn.setPromptText("Email");
		vBoxAdd.getChildren().addAll(textEmailSignIn, textFieldEmailSignIn);
		
		Button buttonAdd = new Button("Add to cart");
		vBoxAdd.getChildren().add(buttonAdd);
		
		Separator sepAdd = new Separator();
		sepAdd.setScaleY(2.0);
		vBoxAdd.getChildren().add(sepAdd);
		
		Text message = new Text();
		vBoxAdd.getChildren().add(message);
		
		vBoxAdd.setSpacing(5.0);
		tabAdd.setContent(vBoxAdd);
		
		startAction(buttonAdd, textFieldEmailSignIn, textFieldPasswordSignIn, textFieldUrl, message);
		
		return tabAdd;
	}
	
	private static void startAction(Button buttonAdd, TextField textFieldEmailSignIn, TextField textFieldPasswordSignIn, TextField textFieldUrl, Text message) {
		buttonAdd.setOnAction(new EventHandler<ActionEvent>() {	
			@Override
			public void handle(ActionEvent event) {
				String url = textFieldUrl.getCharacters().toString();
				String password = textFieldPasswordSignIn.getCharacters().toString();
				String email = textFieldEmailSignIn.getCharacters().toString();
				if(!url.isEmpty() && !password.isEmpty() && !email.isEmpty() && EmailValidator.validate(email)) {
					message.setText("");
					BotUser user = new BotUser(null, email, password);
					BotPromUa promUa = new BotPromUa(user);
					try{
						promUa.addToCart(url);
					} 
					catch (NoSuchElementException e) {
						message.setText("Incorrec url");
					}
					catch  (TimeoutException e) {
						message.setText("Incorrec email/password");
					}
				}
				else message.setText("Enter url, password and email.");
			}
		});
		
	}
	

}
