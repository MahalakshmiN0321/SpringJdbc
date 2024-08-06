package org.example.Dao;

import org.example.beans.Employee;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {
    private JdbcTemplate  jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String add(Employee employee) {
        String status = "";
        try {
            List<Employee> emplist = jdbcTemplate.query("Select * from employee where Eno= " + employee.getEno(), (rs, rownum) -> {
                Employee employee1 = new Employee();
                employee1.setEno(rs.getInt("ENO"));
                employee1.setEname(rs.getString("ENAME"));
                employee1.setEsalary(rs.getInt("ESALARY"));
                employee1.setEaddr(rs.getString("EADDR"));
                return employee1;
            });
            if (emplist.isEmpty() == true) {
                String sql = "insert into employee values (?, ?, ?, ?)";
                int rowCount = jdbcTemplate.update(sql, employee.getEno(), employee.getEname(), employee.getEsalary(), employee.getEaddr());
                if (rowCount == 1) {
                    status = "Employee Inserted successfully";
                } else {
                    status = "Employee Insertion failure";
                }
            } else {
                status = "Employee Existed Already";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }



    @Override
    public Employee search(int eno) {
        Employee emp = null;
        try {
            // Use parameterized query to avoid SQL injection
            String sql = "SELECT * FROM employee WHERE eno = ?";

            // Use jdbcTemplate to execute the query with parameters
            List<Employee> emplist = jdbcTemplate.query(sql, new Object[]{eno}, (rs, rowNum) -> {
                Employee employee = new Employee();
                employee.setEno(rs.getInt("ENO"));
                employee.setEname(rs.getString("ENAME"));
                employee.setEsalary(rs.getInt("ESALARY"));
                employee.setEaddr(rs.getString("EADDR"));
                return employee;
            });
             //Check if emplist contains any results
            if (!emplist.isEmpty()) {
                emp = emplist.get(0);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return emp;
    }


    @Override
    public String update(Employee employee) {
        String status = "";
        try {
            // Check if employee exists
            Employee employee1 = search(employee.getEno());
            if (employee1 == null) {
                status = "Employee not existed";
            } else {
                // Perform the update with a proper WHERE clause
                String sql = "UPDATE employee SET Ename = ?, Esalary = ?, Eaddr = ? WHERE eno = ?";
                int rowCount = jdbcTemplate.update(sql, employee.getEname(), employee.getEsalary(), employee.getEaddr(), employee.getEno());
                if (rowCount == 1) {
                    status = "Employee updated successfully";
                    // Logging the success message
                } else {
                    status = "Employee updation failure";
                }
            }
        } catch (Exception e) {
            status = "Error in updating employee";
        }
        return status;
    }


    @Override
    public String delete(int eno){
        String status="";
        try{
            Employee employee=search(eno);
            if(employee==null){
                status="Employee deleted fails";
            }else{
                String sql = "DELETE FROM employee WHERE eno = ?";
                int rowcount = jdbcTemplate.update(sql, eno);
                if(rowcount==1){
                    System.out.println("Employee deleted successfully");
                }else{
                    System.out.println("Employee not deleted");
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return status;
    }
}
