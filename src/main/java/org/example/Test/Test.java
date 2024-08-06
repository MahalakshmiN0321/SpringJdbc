package org.example.Test;

import org.example.Dao.EmployeeDao;
import org.example.beans.Employee;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Test {
    public static void main(String[] args) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        EmployeeDao employeeDao = (EmployeeDao) context.getBean("EmployeeDao");

        /*
        Employee employee=new Employee();
        employee.setEno(101);
        employee.setEname("maha");
        employee.setEsalary(14000);
        employee.setEaddr("chennai");
        String status=employeeDao.add(employee);
        System.out.println(status);

        Employee employee1=new Employee();
        employee1.setEno(102);
        employee1.setEname("kavi");
        employee1.setEsalary(29000);
        employee1.setEaddr("Pondicherry");
        String status1=employeeDao.add(employee1);
        System.out.println(status1);


        Employee employee2=new Employee();
        employee2.setEno(103);
        employee2.setEname("pavi");
        employee2.setEsalary(20000);
        employee2.setEaddr("Ooty");
        String status2=employeeDao.add(employee2);
        System.out.println(status2);

*/


        Employee emp = employeeDao.search(102);
        if (emp == null) {
            System.out.println("Employee not Existed");
        } else {
            System.out.println("Employee Details");
            System.out.println("*****************");
            System.out.println("Employee Number:" + emp.getEno());
            System.out.println("Employee Name:" + emp.getEname());
            System.out.println("Employee Salary:" + emp.getEsalary());
            System.out.println("Employee Address:" + emp.getEaddr());
        }



/*
        Employee employee1=new Employee();
        employee1.setEno(101);
        employee1.setEname("kavi");
        employee1.setEsalary(30000);
        employee1.setEaddr("pondy");
        String status=employeeDao.update(employee1);
        System.out.println(status);
    }


/*
        String status = employeeDao.delete(101);
        System.out.println(status);

 */

    }
}



