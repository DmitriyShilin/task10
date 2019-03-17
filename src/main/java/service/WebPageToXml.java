package service;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import model.WebPages;

public class WebPageToXml {
	
	private static final String PATH = System.getProperty("user.dir") + File.separator + "data" + File.separator + "data.xml";
	private static Logger logger = Logger.getLogger(WebPageToXml.class.getName());
	
	public static void writeToXml(WebPages pages) {
		try {
			JAXBContext context = JAXBContext.newInstance(WebPages.class);
			Marshaller marshaller = context.createMarshaller();
			File data = new File(PATH);
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.marshal(pages, data);
		} catch (JAXBException e) {
			logger.log(Level.SEVERE, null, e);
		}
	}
}
