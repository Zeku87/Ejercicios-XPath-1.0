import java.util.ArrayList;
import java.util.Collections;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class Main {

	public static void main(String[] args) throws Exception {

		//DOM
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);

		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse("xml/productos.xml");
		//XPath
		XPathFactory xPathFactory = XPathFactory.newInstance();
		XPath xPath = xPathFactory.newXPath();


		//Obten los nodos denominacion y precio de todos los productos.
		System.out.println("Obten los nodos denominacion y precio de todos los productos.");
		XPathExpression expr = xPath.compile("//productos/produc/precio/text() | //productos/produc/denominacion/text()");
		Object result = expr.evaluate(doc, XPathConstants.NODESET);
		NodeList nodes = (NodeList) result;
		for(int i = 0; i < nodes.getLength(); i++) {
			System.out.println(nodes.item(i).getTextContent());
		}
		
		//Obten los nodos de los productos que sean placa base.
		System.out.println("Obten los nodos de los productos que sean placa base.");
						// "//productos/produc[starts-with(denominacion, 'Placa Base')]"
		expr = xPath.compile("//productos/produc[contains(denominacion, 'Placa Base')]");
		result = expr.evaluate(doc, XPathConstants.NODESET);
		nodes = (NodeList) result;
		for(int i = 0; i < nodes.getLength(); i++) {
			System.out.println(nodes.item(i).getTextContent());
		}
		
		//Obten los nodos de los productos con precio mayor de 60 euros y de la zona 20.
		System.out.println("Obten los nodos de los productos con precio mayor de 60 euros y de la zona 20.");
		expr = xPath.compile("//productos/produc[precio > 60 and cod_zona = 20]");
		result = expr.evaluate(doc, XPathConstants.NODESET);
		nodes = (NodeList) result;
		for(int i = 0; i < nodes.getLength(); i++) {
			System.out.println(nodes.item(i).getTextContent());
		}
		
		//Obten el producto más barato de la zona 20.
		System.out.println("Obtén el producto más barato de la zona 20.");
		expr = xPath.compile("//productos/produc[number(cod_zona)= 20]/precio/text()");
		result = expr.evaluate(doc, XPathConstants.NODESET);
		nodes = (NodeList) result;
		ArrayList<Integer> valores = new ArrayList<Integer>(nodes.getLength());
		for(int i = 0; i < nodes.getLength(); i++) {
			valores.add(Integer.parseInt(nodes.item(i).getTextContent()));
		}
	
		System.out.println("El precio mas barato es: " + Collections.min(valores));
	}

}
