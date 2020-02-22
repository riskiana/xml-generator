package com.riskiana;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.riskiana.util.CustomMap;

public class App {
	private static final String LINE = "\n";
	private static final String TAB = "\t";

	public static void main(String[] args)
			throws JAXBException, SAXException, IOException, ParserConfigurationException, TransformerException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();

		JAXBContext jaxBContent = null;
		Marshaller marshaller = null;
		StringWriter stringWriter = new StringWriter();

		CustomMap map = new CustomMap();
		System.out.println(getXMLRecord());
		Document document = builder.parse(new InputSource(new StringReader(getXMLRecord())));
		String key = "";
		String value = "";

		NodeList flowList = document.getElementsByTagName("field");
		for (int i = 0; i < flowList.getLength(); i++) {
			NodeList childList = flowList.item(i).getChildNodes();
			for (int j = 0; j < childList.getLength(); j++) {
				Node childNode = childList.item(j);
				if ("key".equals(childNode.getNodeName())) {
					key = childList.item(j).getTextContent().trim();

				} else if ("value".equals(childNode.getNodeName())) {
					value = childList.item(j).getTextContent().trim();
				}

			}
			map.addEntry(key, value);
		}
		System.out.println("");
		StringWriter sb = new StringWriter();
		jaxBContent = JAXBContext.newInstance(map.getClass());
		marshaller = jaxBContent.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(map, sb);

		System.out.println(sb.toString());
	}

	public static String getXMLRecord() {
		String XMLString = "<data>" + LINE;
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("usrn", "913333");
		data.put("name", "North Street");
		data.put("locality", "Glasgow City");
		data.put("town", "Glasgow");
		data.put("county", "Scotland");
		data.put("descriptionStyle", "UK");

		Map<String, String> treeMap = new TreeMap<String, String>(data);

		for (Map.Entry<String, String> entity : treeMap.entrySet()) {
			XMLString += TAB + "<field>" + LINE;
			XMLString += TAB + TAB + "<key>" + entity.getKey() + "</key>" + LINE;
			XMLString += TAB + TAB + "<value>" + entity.getValue() + "</value>" + LINE;
			XMLString += TAB + "</field>" + LINE;
		}
		XMLString += "</data>";
		return XMLString;
	}

}
