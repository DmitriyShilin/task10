import tab.TabAddToCart;
import tab.TabRegistration;
import tab.TabSearch;
import javafx.application.Application;
import javafx.stage.Stage;
import service.WebPageToXml;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;

public class MainApp extends Application {

	public static void main(String[] args) {		
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		
		TabPane rootTab = new TabPane();		
		rootTab.getTabs().addAll(TabSearch.createTab(), TabRegistration.createTab(), TabAddToCart.createTab());
		Scene scene = new Scene(rootTab, 1500, 800);
		
		primaryStage.setTitle("Web Resource");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	@Override
	public void stop() {
		WebPageToXml.writeToXml(TabSearch.pages);
	}
}
