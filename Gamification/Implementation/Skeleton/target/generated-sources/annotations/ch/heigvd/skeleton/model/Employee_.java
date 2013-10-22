package ch.heigvd.skeleton.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2013-10-22T20:38:24")
@StaticMetamodel(Employee.class)
public class Employee_ { 

    public static volatile SingularAttribute<Employee, Long> id;
    public static volatile SingularAttribute<Employee, String> lastName;
    public static volatile SingularAttribute<Employee, String> email;
    public static volatile SingularAttribute<Employee, Double> salary;
    public static volatile SingularAttribute<Employee, String> firstName;

}