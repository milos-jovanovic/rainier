package dataProvider;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlReader {

	
	private static Object[][] magentoLinksParallel;

	@DataProvider(name = "magentoLinksParallel", parallel = true)
	public static Object[][] getMagentoLinksParallel() {
		if (magentoLinksParallel == null) {
			System.out.println("Ucitavam linkove...");
			// setupLinksFromMagento(filePath);
		}
		System.out.println("Size: "+magentoLinksParallel.length);
		return magentoLinksParallel;
	}

	/**
	 * method for seting up dataProvider links, desired magento SiteMap.xml file url
	 * is passed to method, this setup should be done in @BeforeTest so @Test could
	 * use dataProvider, otherwise Object[][] matrix return will be empty if this
	 * method is not called before test
	 */

	public static void setupLinksFromMagentoParallel(String filePath) throws Exception {

		// Get Document Builder
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();

		// Build Document
		Document document = builder.parse(new URL(filePath).openStream());

		// Normalize the XML Structure; It's just too important !!
		document.getDocumentElement().normalize();

		// Here comes the root node - urlset in this case
		Element root = document.getDocumentElement();
		System.out.println(root.getNodeName());

		// Get all url nodes
		NodeList nList = document.getElementsByTagName("url");
		// System.out.println("============================");
		magentoLinksParallel = new Object[nList.getLength()][1];
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node node = nList.item(temp);
			// System.out.println(""); //Just a separator
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				// Add each node url
				Element eElement = (Element) node;

				magentoLinksParallel[temp][0] = eElement.getElementsByTagName("loc").item(0).getTextContent();
				// System.out.println(eElement.getElementsByTagName("loc").item(0).getTextContent());
			}
		}
	}
	public static List<String> getUrlListFromSitemap(String filePath) throws Exception{
		// Get Document Builder
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();

				// Build Document
				Document document = builder.parse(new URL(filePath).openStream());

				// Normalize the XML Structure; It's just too important !!
				document.getDocumentElement().normalize();

				// Here comes the root node - urlset in this case
				Element root = document.getDocumentElement();
				System.out.println(root.getNodeName());

				// Get all url nodes
				NodeList nList = document.getElementsByTagName("url");
				// System.out.println("============================");
				List<String> lista = new ArrayList<String>();
				for (int temp = 0; temp < nList.getLength(); temp++) {
					Node node = nList.item(temp);
					// System.out.println(""); //Just a separator
					if (node.getNodeType() == Node.ELEMENT_NODE) {
						// Add each node url
						Element eElement = (Element) node;
						lista.add( eElement.getElementsByTagName("loc").item(0).getTextContent());
						// System.out.println(eElement.getElementsByTagName("loc").item(0).getTextContent());
					}
				}
		
		return lista;
	}
	}

