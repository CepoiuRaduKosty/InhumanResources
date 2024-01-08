package com.ihr.ihr.ejb;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.ihr.ihr.common.dtos.BankInfoDtos.BankInfoDto;
import com.ihr.ihr.common.dtos.EmployeeDtos.CreateEmployeeDto;
import com.ihr.ihr.common.dtos.EmployeeDtos.EmployeeDto;
import com.ihr.ihr.common.dtos.EmployeeDtos.UpdateEmployeeDto;
import com.ihr.ihr.common.dtos.PaymentInfoDtos.PaymentInfoDto;
import com.ihr.ihr.common.excep.InvalidEmployeeDto;
import com.ihr.ihr.common.excep.UnknownBankInfoException;
import com.ihr.ihr.common.excep.UnknownEmployeeException;
import com.ihr.ihr.common.excep.UnknownPaymentInfoException;
import com.ihr.ihr.common.excep.UnknownUserException;
import com.ihr.ihr.common.interf.EmployeeProvider;
import com.ihr.ihr.common.interf.EmployeeValidation;
import com.ihr.ihr.entities.BankInfo;
import com.ihr.ihr.entities.Employee;
import com.ihr.ihr.entities.PaymentInfo;
import com.ihr.ihr.entities.User;

import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Stateless
public class EmployeeBean implements EmployeeProvider {

    private static final Logger LOG = Logger.getLogger(EmployeeBean.class.getName());

    @PersistenceContext
    EntityManager entityManager;

    @Inject
    EmployeeValidation employeeValidation;

    private void setEmployeeInformation(Employee employee, UpdateEmployeeDto updateEmployeeDto)
    {
        employee.setName(updateEmployeeDto.getName());
        employee.setSurname(updateEmployeeDto.getSurname());
        employee.setGender(updateEmployeeDto.getGender());
        employee.setDateOfBirth(updateEmployeeDto.getDateOfBirth());
        employee.setAddress(updateEmployeeDto.getAddress());
        employee.setReligion(updateEmployeeDto.getReligion());
        employee.setHoursPerWeek(updateEmployeeDto.getHoursPerWeek());
    }
    @Override
    public Long createEmployee(CreateEmployeeDto createEmployeeDto) throws UnknownBankInfoException, UnknownPaymentInfoException, InvalidEmployeeDto {
        LOG.info("createEmployee");

        if(!employeeValidation.isEmployeeDataValid(createEmployeeDto))
            throw new InvalidEmployeeDto();

        Employee employee = new Employee();
        setEmployeeInformation(employee, createEmployeeDto);

        entityManager.persist(employee);

        BankInfo bankInfo;

        try {
            bankInfo = entityManager.find(BankInfo.class, createEmployeeDto.getBankInfoDto().getId());
        }
        catch (EJBException ex)
        {
            throw new UnknownBankInfoException(ex.getMessage());
        }
        bankInfo.setEmployee(employee);


        PaymentInfo paymentInfo;
        try {
            paymentInfo = entityManager.find(PaymentInfo.class, createEmployeeDto.getPaymentInfoDto().getId());
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
        return employee.getId();
    }

    @Override
    public void deleteEmployeeById(Long employeeId)
    {
        LOG.info("deleteEmployeeById");
        Employee employee = entityManager.find(Employee.class, employeeId);

        if(employee.getUser() != null) {
            User user = employee.getUser();
            user.setEmployee(null);
            entityManager.merge(user);
        }

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
    public void updateEmployeeById(Long employeeId, UpdateEmployeeDto updateEmployeeDto) throws InvalidEmployeeDto {
        LOG.info("updateEmployee");

        if(!employeeValidation.isEmployeeDataValid(updateEmployeeDto))
            throw new InvalidEmployeeDto();

        Employee employee = entityManager.find(Employee.class, employeeId);
        setEmployeeInformation(employee,updateEmployeeDto);
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
                if (employee.getName().toLowerCase().contains(employeeName.toLowerCase()) || employee.getSurname().toLowerCase().contains(employeeName.toLowerCase())) {
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
                employee.getPaymentInfo().getCumulatedShares());

        employeeDto.setBankInfoDto(bankInfoDto);
        employeeDto.setPaymentInfoDto(paymentInfoDto);

        return employeeDto;
    }

    private User safeGetUserEntityById(Long userID) throws UnknownUserException {
        User user = entityManager.find(User.class, userID);
        if (user == null)
            throw new UnknownUserException();
        return user;
    }

    private Employee safeGetEmployeeEntityById(Long employeeID) throws UnknownEmployeeException {
        Employee employee = entityManager.find(Employee.class, employeeID);
        if (employee == null)
            throw new UnknownEmployeeException();
        return employee;
    }

    @Override
    public void setUserToEmployee(Long userID, Long employeeID) throws UnknownEmployeeException, UnknownUserException {
        User user = safeGetUserEntityById(userID);
        Employee employee = safeGetEmployeeEntityById(employeeID);

        employee.setUser(user);
        entityManager.merge(employee);
    }
}
