/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lojadoprazer.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import lojadoprazer.enums.EmployeeType;

/**
 *
 * @author danielvilha
 */
@XStreamAlias("employee")
public class Employee {
    
    @XStreamAlias("id")
    private int id;
    
    @XStreamOmitField
    private User user;
    
    @XStreamAlias("matricula")
    @XStreamAsAttribute
    private int matricula;
    
    @XStreamAlias("salario")
    @XStreamAsAttribute
    private double salario;
    
    @XStreamAlias("employeeType")
    @XStreamAsAttribute
    private EmployeeType employeeType;

    public Employee() {
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
    }

    @Override
    public String toString() {
        return  getUser().toString() + "\n" +
                "Matricula: " + matricula + "\n" +
                "Salário: " + salario + "\n" +
                "Tipo empregado: " + employeeType;
    }
}
