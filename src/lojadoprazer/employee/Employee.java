/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lojadoprazer.employee;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import lojadoprazer.user.User;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author danielvilha
 */
public class Employee extends User {
    
    private int eUid;
    private int matricula;
    private EmployeeType employeeType;
    private double salario;

    public int getEuid() {
        return eUid;
    }

    public void setEuid(int eUid) {
        this.eUid = eUid;
    }
    
    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
    }
    
    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
    
    public Employee getEmployeeById(int eUid) {
        Employee emp = new Employee();
        User us = new User();
        try {
            us = User.getUserById(eUid);
            File fXmlFile = new File("/Users/danielvilha/Developer/Projects/Loja/LojaDoPrazer/src/lojadoprazer/employee/employees.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("employee");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    if (Integer.parseInt(eElement.getElementsByTagName("eUid").item(0).getTextContent()) == eUid) {
                        emp.setUid(us.getUid());
                        emp.setEuid(Integer.parseInt(eElement.getElementsByTagName("eUid").item(0).getTextContent()));
                        emp.setMatricula(Integer.parseInt(eElement.getElementsByTagName("matricula").item(0).getTextContent()));
                        emp.setEmployeeType(toEmployeeType(eElement.getElementsByTagName("employeeType").item(0).getTextContent()));
                        emp.setSalario(Double.parseDouble(eElement.getElementsByTagName("salario").item(0).getTextContent()));
                        emp.setUserType(us.getUserType());
                        emp.setTypeName(us.getTypeName());
                        emp.setCpf(us.getCpf());
                        emp.setUserName(us.getUserName());
                    }
                }
            }
        } catch (IOException | ParserConfigurationException | SAXException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return emp;
    }
    
    public EmployeeType toEmployeeType(String eType) {
        if (eType.equals("funcionario")) {
            return EmployeeType.funcionario;
        } else if (eType.equals("gerente")) {
            return EmployeeType.gerente;
        } else {
            return EmployeeType.diretor;
        }
    }
}
