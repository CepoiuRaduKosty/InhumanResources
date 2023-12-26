package com.ihr.ihr.ejb;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.ihr.ihr.common.dtos.EmployeeDto;
import com.ihr.ihr.common.interf.BankInfoProvider;
import com.ihr.ihr.common.interf.EmployeeProvider;
import com.ihr.ihr.common.interf.PaymentInfoProvider;
import com.ihr.ihr.entities.BankInfo;
import com.ihr.ihr.entities.Employee;
import com.ihr.ihr.entities.PaymentInfo;
import jakarta.ejb.EJBException;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ejb.Stateless;
import jakarta.persistence.TypedQuery;

@Stateless
public class EmployeeBean implements EmployeeProvider {

    private static final Logger LOG = Logger.getLogger(EmployeeBean.class.getName());

    @PersistenceContext
    EntityManager entityManager;

    private void setEmployeeInformation(Employee employee,EmployeeDto employeeDto)
    {
        employee.setName(employeeDto.getName());
        employee.setSurname(employeeDto.getSurname());
        employee.setBankInfo(entityManager.find(BankInfo.class, employeeDto.getBankInfoId()));
        employee.setPaymentInfo(entityManager.find(PaymentInfo.class, employeeDto.getPaymentInfoId()));
        employee.setGender(employeeDto.getGender());
        employee.setDateOfBirth(employeeDto.getDateOfBirth());
        employee.setAddress(employeeDto.getAddress());
        employee.setReligion(employeeDto.getReligion());
        employee.setHoursPerWeek(employeeDto.getHoursPerWeek());
    }
    @Override
    public void createEmployee(EmployeeDto employeeDto)
    {
        LOG.info("createEmployee");
        Employee employee = new Employee();
        setEmployeeInformation(employee,employeeDto);
        entityManager.persist(employee);
    }

    @Override
    public void deleteEmployeeById(Long employeeId)
    {
        LOG.info("deleteEmployeeById");
        Employee employee = entityManager.find(Employee.class, employeeId);
        entityManager.remove(employee);
    }

    @Override
    public void updateEmployeeById(Long employeeId, EmployeeDto employeeDto)
    {
        LOG.info("updateEmployee");
        Employee employee = entityManager.find(Employee.class, employeeId);
        setEmployeeInformation(employee,employeeDto);
    }

    @Override
    public List<EmployeeDto> findAllEmployees() {
        LOG.info("findAllEmployees");
        try {
            TypedQuery<Employee> typedQuery = entityManager.createQuery("SELECT C FROM Employee c", Employee.class);
            List<Employee> employees = typedQuery.getResultList();

            return copyEmployeesToDto(employees);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }
    @Override
    public List<EmployeeDto> findAllEmployeesByName(String employeeName) {
        LOG.info("findAllEmployeesByName");
        try {
            TypedQuery<Employee> typedQuery = entityManager.createQuery("SELECT C FROM Employee c", Employee.class);
            List<Employee> employees = typedQuery.getResultList();
            List<Employee> employeesSearch = new ArrayList<>();
            for (Employee employee : employees)
            {
                if (employee.getName().contains(employeeName) || employee.getSurname().contains(employeeName)) {
                    employeesSearch.add(employee);
                }
            }
            return copyEmployeesToDto(employeesSearch);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }
    @Override
    public EmployeeDto findById(Long employeeId) {
        LOG.info("findById");
        try {
            Employee employee = entityManager.find(Employee.class, employeeId);
            if (employee == null)
            {
                return null;
            }
            return new EmployeeDto(employee.getId(), employee.getName(), employee.getSurname(),
                    employee.getBankInfo().getId(), employee.getPaymentInfo().getId(),
                    employee.getGender(), employee.getDateOfBirth(), employee.getAddress(),
                    employee.getReligion(), employee.getHoursPerWeek());
        } catch (Exception e) {
            throw new EJBException(e);
        }
    }
    private List<EmployeeDto> copyEmployeesToDto(List<Employee> employees) {
        List<EmployeeDto> employeesDto = new ArrayList<>();
        employees.forEach(employee ->
        {
            EmployeeDto employeeDto = new EmployeeDto(employee.getId(), employee.getName(), employee.getSurname(),
                    employee.getBankInfo().getId(), employee.getPaymentInfo().getId(), employee.getGender(), employee.getDateOfBirth(),
                    employee.getAddress(), employee.getReligion(), employee.getHoursPerWeek());
            employeesDto.add(employeeDto);
        });
        return employeesDto;
    }
}
