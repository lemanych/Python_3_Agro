import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class XMLReader {

	static final String outputEncoding = "UTF-8";
	
	public static void main(String argv[]) throws ParserConfigurationException, SAXException {
		
		
 
		try {
			Scanner s = new Scanner(System.in);
			System.out.println("ќбер≥ть один з вар≥ант≥в:\n«читати дан≥ з файлу - '*'\n¬вести дан≥ з клав≥атури - '+'");
			String input = s.nextLine();
			switch (input)  {
			case "+":
				System.out.println("¬вед≥ть к≥льк≥сть п≥дприЇмств: ");
				int len = Integer.parseInt(s.nextLine());
				Company[] coms = new Company[len];
				for (int temp = 0; temp < coms.length; temp++) 
					coms[temp] = new Company();
				
				for (int temp = 0; temp < len; temp++) {
					System.out.println("¬вед≥ть назву: ");
					coms[temp].setName(s.nextLine());
					System.out.println("¬вед≥ть тип власност≥: ");
					coms[temp].setPropertyType(s.nextLine());
					System.out.println("¬вед≥ть к≥льк≥сть прац≥вник≥в: ");
					coms[temp].setEmployeesNumber(Integer.parseInt(s.nextLine()));
					System.out.println("¬вед≥ть тип продукц≥њ: ");
					coms[temp].setProductType(s.nextLine());
					System.out.println("¬вед≥ть прибуток: ");
					coms[temp].setIncome(s.nextLine());
					coms[temp].output();
				}
				
				write(coms);
				break;
				
			case "*":
				String filename = "Test2.xml";
				File xmlFile = new File(filename);
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				DocumentBuilder db = dbf.newDocumentBuilder(); 
				Document doc = db.parse(xmlFile);
				
				doc.getDocumentElement().normalize();
				NodeList nList = doc.getElementsByTagName("company");
				//output(doc, nList);
				Company[] companies = new Company[nList.getLength()];
				for (int temp = 0; temp < companies.length; temp++) 
					companies[temp] = new Company();
				
				for (int temp = 0; temp < nList.getLength(); temp++) {
				    Node nNode = nList.item(temp);
				    
				    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					       Element eElement = (Element) nNode;
					       companies[temp].setName(eElement.getAttribute("name"));
					       companies[temp].setPropertyType(
					    		   eElement.getElementsByTagName("propertyType").item(0).getTextContent());
					       companies[temp].setEmployeesNumber(
					    		   Integer.parseInt(eElement.getElementsByTagName("employeesNumber").item(0).getTextContent()));
					       companies[temp].setProductType(
					    		   eElement.getElementsByTagName("productType").item(0).getTextContent());
					       companies[temp].setIncome(
					    		   eElement.getElementsByTagName("income").item(0).getTextContent());  
					       companies[temp].output();
				    }
				}
				System.out.println("¬≥дсортовано в пор€дку зб≥льшенн€ к≥лькост≥ прац≥вник≥в:");
				sortByEmployees(companies);
				for (int temp = 0; temp < companies.length; temp++) 
					companies[temp].output();
				
				
				write(companies);
				break;
				
			default:
				System.out.println("ўось не так!");
			}
			
			s.close();
		}  catch (IOException e) {	
			System.out.println(e.getMessage());
		}
			
	}
	
	public static void sortByEmployees(Company[] companies) {
		Arrays.sort(companies, Comparator.comparingInt(Company -> Company.getEmployeesNumber()));
	}
	
	public static void write(Company[] companies) {

	      try {
	         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	         Document doc = dBuilder.newDocument();
	         
	         // root element
	         Element rootElement = doc.createElement("agro_company");
	         doc.appendChild(rootElement);

	         for (int temp = 0; temp < companies.length; temp++) {
					Element company = doc.createElement("company");
					rootElement.appendChild(company);
					
					// setting attribute to element
					Attr attr = doc.createAttribute("name");
					attr.setValue(companies[temp].getName());
					company.setAttributeNode(attr);
					
					Element propertyType = doc.createElement("propertyType");
					propertyType.appendChild(doc.createTextNode(companies[temp].getPropertyType()));
					company.appendChild(propertyType);
					
					Element employeesNumber = doc.createElement("employeesNumber");
					employeesNumber.appendChild(doc.createTextNode(Integer.toString(companies[temp].getEmployeesNumber())));
					company.appendChild(employeesNumber);
					
					Element productType = doc.createElement("productType");
					productType.appendChild(doc.createTextNode(companies[temp].getProductType()));
					company.appendChild(productType);
					
					Element income = doc.createElement("income");
					income.appendChild(doc.createTextNode(companies[temp].getIncome()));
					company.appendChild(income);
					
					// write the content into xml file
					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer = transformerFactory.newTransformer();
					DOMSource source = new DOMSource(doc);
					StreamResult result = new StreamResult(new File(System.getProperty("user.dir")
                            + File.separator + "agro.xml"));
					transformer.transform(source, result);
					
					// Output to console for testing
					StreamResult consoleResult = new StreamResult(System.out);
					transformer.transform(source, consoleResult);
	         }

	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	   }
	
}

