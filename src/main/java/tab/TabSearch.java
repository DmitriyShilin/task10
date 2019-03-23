package tab;

import java.util.LinkedList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.WebPage;
import model.WebPages;
import service.GetInformationFromUrl;

public class TabSearch {
	
	public static WebPages pages = new WebPages();
	
	public static Tab createTab() {		
		Tab tabSearch = new Tab("Search items");
		tabSearch.setClosable(false);
		
		ScrollPane scrollPane = new ScrollPane();
		
		VBox vBoxSearch = new VBox();
		vBoxSearch.setAlignment(Pos.TOP_CENTER);
		
		Text textSearch = new Text("Enter url");
		VBox.setMargin(textSearch, new Insets(5.0, 0.0, 0.0, 0.0));
		vBoxSearch.getChildren().add(textSearch);
		
		TextField textFieldSearch = new TextField();
		textFieldSearch.setPromptText("https://prom.ua/p895411456-igrovoj-noutbu-omen.html");
		vBoxSearch.getChildren().add(textFieldSearch);
		
		Button buttonSearch = new Button("Search");
		vBoxSearch.getChildren().add(buttonSearch);
		
		Separator sepSearch = new Separator();
		sepSearch.setScaleY(2.0);
		vBoxSearch.getChildren().add(sepSearch);		
		
		Text text = new Text();
		text.setWrappingWidth(1500);
		vBoxSearch.getChildren().add(text);
		
		ImageView img = new ImageView();
		vBoxSearch.getChildren().add(img);
		
		vBoxSearch.setSpacing(5.0);
		scrollPane.setContent(vBoxSearch);		
		
		tabSearch.setContent(scrollPane);
		
		startAction(buttonSearch, textFieldSearch, text, img);
		
		return tabSearch;		
	}
	
	private static void startAction(Button buttonSearch, TextField textFieldSearch, Text text, ImageView img) {
		buttonSearch.setOnAction(new EventHandler<ActionEvent>() {			
			@Override
			public void handle(ActionEvent event) {
				String url = textFieldSearch.getCharacters().toString();
				if(url.startsWith("https://prom.ua/")) {
					GetInformationFromUrl info = new GetInformationFromUrl(url);
					text.setText(getTextInfo(info));;
					img.setImage(new Image(info.getImgUrl()));
					setWebPage(info);
				}
				else text.setText("incorrect url"); 
			}
		});
	}
	
	private static String getTextInfo(GetInformationFromUrl info) {
		StringBuilder str = new StringBuilder();
		str.append("Information. \n");
		str.append("\nName:");
		str.append("\n" + info.getName() + "\n");
		str.append("\nUrl:");
		str.append("\n" + info.getUrl()+ "\n");
		str.append("\nArticleId:");
		str.append("\n" + info.getArticleId()+ "\n");
		str.append("\nPrice:");
		str.append("\n" + info.getPrice()+ "\n");
		str.append("\nAvailability:");
		str.append("\n" + info.getAvailability()+ "\n");
		str.append("\nDescription:");
		str.append("\n" + info.getDescription()+ "\n");
		str.append("\nSpecifications:\n");
		LinkedList<String> specifications = info.getSpecifications();
		for(String specification: specifications) {
			str.append(specification + "\n");
		}
		return str.toString();
	}
	
	private static void setWebPage(GetInformationFromUrl info) {
		pages.getWebPages().add(new WebPage(info.getUrl(), 
											info.getArticleId(), 
											info.getName(), 
											info.getPrice(), 
											info.getSpecifications(), 
											info.getAvailability(), 
											info.getDescription(), 
											info.getImgUrl()));
	}
}
