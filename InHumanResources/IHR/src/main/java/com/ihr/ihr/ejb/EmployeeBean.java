package com.ihr.ihr.ejb;

import com.ihr.ihr.common.dtos.BankInfoDto;
import com.ihr.ihr.common.dtos.EmployeeDto;
import com.ihr.ihr.common.dtos.PaymentInfoDto;
import com.ihr.ihr.common.excep.UnknownBankInfoException;
import com.ihr.ihr.common.excep.UnknownPaymentInfoException;
import com.ihr.ihr.common.interf.EmployeeProvider;
import com.ihr.ihr.entities.BankInfo;
import com.ihr.ihr.entities.Employee;
import com.ihr.ihr.entities.PaymentInfo;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class EmployeeBean implements EmployeeProvider {

    private static final Logger LOG = Logger.getLogger(EmployeeBean.class.getName());

    @PersistenceContext
    EntityManager entityManager;

    private void setEmployeeInformation(Employee employee, EmployeeDto employeeDto)
    {
        employee.setName(employeeDto.getName());
        employee.setSurname(employeeDto.getSurname());
        employee.setGender(employeeDto.getGender());
        employee.setDateOfBirth(employeeDto.getDateOfBirth());
        employee.setAddress(employeeDto.getAddress());
        employee.setReligion(employeeDto.getReligion());
        employee.setHoursPerWeek(employeeDto.getHoursPerWeek());
    }
    @Override
    public void createEmployee(EmployeeDto employeeDto) throws UnknownBankInfoException, UnknownPaymentInfoException {
        LOG.info("createEmployee");
        Employee employee = new Employee();
        setEmployeeInformation(employee, employeeDto);

        entityManager.persist(employee);

        BankInfo bankInfo;

        try {
            bankInfo = entityManager.find(BankInfo.class, employeeDto.getBankInfoDto().getId());
        }
        catch (EJBException ex)
        {
            throw new UnknownBankInfoException(ex.getMessage());
        }
        bankInfo.setEmployee(employee);


        PaymentInfo paymentInfo;
        try {
            paymentInfo = entityManager.find(PaymentInfo.class, employeeDto.getPaymentInfoDto().getId());
        }
        catch (EJBException ex)
        {
            throw new UnknownPaymentInfoException(ex.getMessage());
        }
        paymentInfo.setEmployee(employee);

        employee.setBankInfo(bankInfo);
        employee.setPaymentInfo(paymentInfo);

        entityManager.merge(employee);
        entityManager.merge(bankInfo);
        entityManager.merge(paymentInfo);
    }

    @Override
    public void deleteEmployeeById(Long employeeId)
    {
        LOG.info("deleteEmployeeById");
        Employee employee = entityManager.find(Employee.class, employeeId);

        BankInfo bankInfo = employee.getBankInfo();
        PaymentInfo paymentInfo = employee.getPaymentInfo();

        employee.setPaymentInfo(null);
        employee.setBankInfo(null);

        paymentInfo.setEmployee(null);
        bankInfo.setEmployee(null);

        entityManager.remove(bankInfo);
        entityManager.remove(paymentInfo);

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

            return copyEmployeeToDto(employee);
        } catch (Exception e) {
            throw new EJBException(e);
        }
    }
    private List<EmployeeDto> copyEmployeesToDto(List<Employee> employees) {
        List<EmployeeDto> employeesDto = new ArrayList<>();
        employees.forEach(employee ->
        {
            employeesDto.add(copyEmployeeToDto(employee));
        });
        return employeesDto;
    }

    private EmployeeDto copyEmployeeToDto(Employee employee)
    {
        EmployeeDto employeeDto = new EmployeeDto(employee.getId(),
                employee.getName(),
                employee.getSurname(),
                employee.getGender(),
                employee.getDateOfBirth(),
                employee.getAddress(),
                employee.getReligion(),
                employee.getHoursPerWeek());

        BankInfoDto bankInfoDto = new BankInfoDto(employee.getBankInfo().getId(),
                employee.getBankInfo().getIBAN(),
                employee.getBankInfo().getBankName());

        PaymentInfoDto paymentInfoDto = new PaymentInfoDto(employee.getPaymentInfo().getId(),
                employee.getPaymentInfo().getMonthlyBasicSalary(),
                employee.getPaymentInfo().getSalaryLevel(),
                employee.getPaymentInfo().getBonusForSuccess(),
                employee.getPaymentInfo().getNumberOfShares(),
                employee.getPaymentInfo().getCummulatedShares());

        employeeDto.setBankInfoDto(bankInfoDto);
        employeeDto.setPaymentInfoDto(paymentInfoDto);

        return employeeDto;
    }
}
