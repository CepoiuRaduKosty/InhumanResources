package com.ihr.ihr.ejb;

import com.ihr.ihr.common.dtos.BankInfoDtos.BankInfoDto;
import com.ihr.ihr.common.dtos.EmployeeDtos.EmployeeDto;
import com.ihr.ihr.common.dtos.PaymentInfoDtos.PaymentInfoDto;
import com.ihr.ihr.common.enums.GenderEnum;
import com.ihr.ihr.common.enums.SalaryLevelEnum;
import com.ihr.ihr.entities.BankInfo;
import com.ihr.ihr.entities.Employee;
import com.ihr.ihr.entities.PaymentInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
class EmployeeBeanTest {

    @Mock
    EntityManager entityManager;

    @InjectMocks
    EmployeeBean employeeBean;

    @Spy
    EmployeeValidatorBean employeeValidatorBean;

    @Test
    void createEmployee_positive_knownBankPaymentInfo() {

        EmployeeDto testEmployeeDto = new EmployeeDto(2L, "Andrei", "Popa", GenderEnum.MALE, LocalDate.now(),
                "Sibiu", "none", 30);
        BankInfoDto bankInfoDto = new BankInfoDto(1L, "IBAN1", "BCR");
        PaymentInfoDto paymentInfoDto=new PaymentInfoDto(1L,1000.0, SalaryLevelEnum.ASSOCIATE,
                66.0, 99, 0);

        testEmployeeDto.setBankInfoDto(bankInfoDto);
        testEmployeeDto.setPaymentInfoDto(paymentInfoDto);

        when(entityManager.find(PaymentInfo.class, testEmployeeDto.getPaymentInfoDto().getId())).thenReturn(new PaymentInfo());
        when(entityManager.find(BankInfo.class, testEmployeeDto.getBankInfoDto().getId())).thenReturn(new BankInfo());



        assertDoesNotThrow(() -> employeeBean.createEmployee(testEmployeeDto));

        verify(entityManager).persist(any(Employee.class));

        ArgumentCaptor<Employee> captor = ArgumentCaptor.forClass(Employee.class);
        verify(entityManager).persist(captor.capture());

        Employee capturedEmployee = captor.getValue();
        assertNotNull(capturedEmployee);
        assertEquals(testEmployeeDto.getName(), capturedEmployee.getName());
        assertEquals(testEmployeeDto.getSurname(), capturedEmployee.getSurname());
        assertEquals(testEmployeeDto.getGender(), capturedEmployee.getGender());
        assertEquals(testEmployeeDto.getDateOfBirth(), capturedEmployee.getDateOfBirth());
        assertEquals(testEmployeeDto.getAddress(), capturedEmployee.getAddress());
        assertEquals(testEmployeeDto.getReligion(), capturedEmployee.getReligion());
        assertEquals(testEmployeeDto.getHoursPerWeek(), capturedEmployee.getHoursPerWeek());

        verify(entityManager, times(3)).merge(any());


    }

    @Test
    void createEmployee_negative_unknownBankInfo() {
        EmployeeDto testEmployeeDto = new EmployeeDto(2L, "Andrei", "Popa", GenderEnum.MALE, LocalDate.now(),
                "Sibiu", "none", 30);
        testEmployeeDto.setBankInfoDto(new BankInfoDto(999L, "InvalidIBAN", "InvalidBankName"));

        when(entityManager.find(BankInfo.class, testEmployeeDto.getBankInfoDto().getId())).thenReturn(null);

        assertThrows(NullPointerException.class, () -> employeeBean.createEmployee(testEmployeeDto));

    }


    @Test
    void deleteEmployeeById_positive_idExists() {
        Long testEmployeeId = 1L;

        Employee mockEmployee = new Employee();
        mockEmployee.setId(testEmployeeId);

        PaymentInfo paymentInfo=new PaymentInfo();
        BankInfo bankInfo = new BankInfo();
        bankInfo.setBankName("BCR");
        bankInfo.setEmployee(mockEmployee);
        bankInfo.setIBAN("Iban1");
        bankInfo.setId(1L);
        paymentInfo.setEmployee(mockEmployee);
        paymentInfo.setId(1L);
        paymentInfo.setBonusForSuccess(66.0);
        paymentInfo.setSalaryLevel(SalaryLevelEnum.ASSOCIATE);
        paymentInfo.setNumberOfShares(0);
        paymentInfo.setMonthlyBasicSalary(99.0);

        mockEmployee.setPaymentInfo(paymentInfo);
        mockEmployee.setBankInfo(bankInfo);

        when(entityManager.find(Employee.class, testEmployeeId)).thenReturn(mockEmployee);

        assertDoesNotThrow(() -> employeeBean.deleteEmployeeById(testEmployeeId));

        verify(entityManager).remove(mockEmployee);
        verify(entityManager).remove(bankInfo);
        verify(entityManager).remove(paymentInfo);
    }

    @Test
    void deleteEmployeeById_negative_idDoesNotExist() {

        Long nonExistingEmployeeId = 999L;
        when(entityManager.find(Employee.class, nonExistingEmployeeId)).thenReturn(null);
        assertThrows(NullPointerException.class,() -> employeeBean.deleteEmployeeById(nonExistingEmployeeId));
        verify(entityManager, never()).remove(any());
    }

    @Test
    void updateEmployeeById_positive_idExists() {

        Long testEmployeeId = 1L;

        EmployeeDto updatedEmployeeDto = new EmployeeDto(2L, "Andrei", "Popa", GenderEnum.MALE, LocalDate.now(),
                "Sibiu", "none", 30);
        updatedEmployeeDto.setName("UpdatedName");
        updatedEmployeeDto.setSurname("UpdatedSurname");
        Employee existingEmployee = new Employee();
        existingEmployee.setId(testEmployeeId);
        when(entityManager.find(Employee.class, testEmployeeId)).thenReturn(existingEmployee);

        assertDoesNotThrow(() -> employeeBean.updateEmployeeById(testEmployeeId, updatedEmployeeDto));

        assertEquals(updatedEmployeeDto.getName(), existingEmployee.getName());
        assertEquals(updatedEmployeeDto.getSurname(), existingEmployee.getSurname());
        assertEquals(updatedEmployeeDto.getHoursPerWeek(), existingEmployee.getHoursPerWeek());
        assertEquals(updatedEmployeeDto.getReligion(), existingEmployee.getReligion());
        assertEquals(updatedEmployeeDto.getAddress(), existingEmployee.getAddress());
        assertEquals(updatedEmployeeDto.getDateOfBirth(), existingEmployee.getDateOfBirth());
        assertEquals(updatedEmployeeDto.getGender(), existingEmployee.getGender());

    }

    @Test
    void updateEmployeeById_negative_idDoesNotExist() {

        Long nonExistingEmployeeId = 999L;


        EmployeeDto updatedEmployeeDto = new EmployeeDto(2L, "Andrei", "Popa", GenderEnum.MALE, LocalDate.now(),
                "Sibiu", "none", 30);
        updatedEmployeeDto.setName("UpdatedName");
        updatedEmployeeDto.setSurname("UpdatedSurname");
        when(entityManager.find(Employee.class, nonExistingEmployeeId)).thenReturn(null);

        assertThrows(NullPointerException.class,() -> employeeBean.updateEmployeeById(nonExistingEmployeeId, updatedEmployeeDto));

        verify(entityManager, never()).merge(any());

    }

    @Test
    void findAllEmployees_positive_existentEmployees() {

        List<Employee> testEmployees = new ArrayList<Employee>();
        BankInfo bankInfo=new BankInfo();
        PaymentInfo paymentInfo=new PaymentInfo();

        bankInfo.setBankName("BCR");
        bankInfo.setIBAN("Iban1");
        bankInfo.setId(1L);
        paymentInfo.setId(1L);
        paymentInfo.setBonusForSuccess(66.0);
        paymentInfo.setSalaryLevel(SalaryLevelEnum.ASSOCIATE);
        paymentInfo.setNumberOfShares(0);
        paymentInfo.setMonthlyBasicSalary(99.0);

        Employee employee=new Employee();
        employee.setName("Andrei");
        employee.setAddress("Sibiu");
        employee.setId(2L);
        employee.setGender(GenderEnum.MALE);
        employee.setReligion("none");
        employee.setDateOfBirth(LocalDate.now());
        employee.setHoursPerWeek(30);
        employee.setSurname("Popa");
        employee.setBankInfo(bankInfo);
        employee.setPaymentInfo(paymentInfo);
        testEmployees.add(employee);

        employee=new Employee();
        employee.setName("John");
        employee.setAddress("Sibiu");
        employee.setId(5L);
        employee.setGender(GenderEnum.MALE);
        employee.setReligion("none");
        employee.setDateOfBirth(LocalDate.now());
        employee.setHoursPerWeek(30);
        employee.setSurname("Popa");
        employee.setBankInfo(bankInfo);
        employee.setPaymentInfo(paymentInfo);
        testEmployees.add(employee);



        TypedQuery<Employee> typedQuery = mock(TypedQuery.class);
        when(entityManager.createQuery("SELECT C FROM Employee c", Employee.class)).thenReturn(typedQuery);

        when(typedQuery.getResultList()).thenReturn(testEmployees);

        List<EmployeeDto> result = employeeBean.findAllEmployees();

        verify(entityManager).createQuery("SELECT C FROM Employee c", Employee.class);
        verify(typedQuery).getResultList();

        assertEquals(testEmployees.size(), result.size());

        for(int i=0;i<testEmployees.size();i++) {
            assertEquals(testEmployees.get(i).getName(), result.get(i).getName());
            assertEquals(testEmployees.get(i).getSurname(), result.get(i).getSurname());
            assertEquals(testEmployees.get(i).getGender(), result.get(i).getGender());
            assertEquals(testEmployees.get(i).getDateOfBirth(), result.get(i).getDateOfBirth());
            assertEquals(testEmployees.get(i).getAddress(), result.get(i).getAddress());
            assertEquals(testEmployees.get(i).getReligion(), result.get(i).getReligion());
            assertEquals(testEmployees.get(i).getHoursPerWeek(), result.get(i).getHoursPerWeek());
        }

    }

    @Test
    void findAllEmployees_negative_NoEmployees() {

        TypedQuery<Employee> typedQuery = mock(TypedQuery.class);
        when(entityManager.createQuery("SELECT C FROM Employee c", Employee.class)).thenReturn(typedQuery);

        when(typedQuery.getResultList()).thenReturn(Collections.emptyList());

        List<EmployeeDto> result = employeeBean.findAllEmployees();

        verify(entityManager).createQuery("SELECT C FROM Employee c", Employee.class);
        verify(typedQuery).getResultList();


        assertTrue(result.isEmpty());
    }

    @Test
    void findAllEmployeesByName_positive_existentEmployees() {

        List<Employee> testEmployees = new ArrayList<Employee>();

        Employee employee=new Employee();

        BankInfo bankInfo=new BankInfo();
        PaymentInfo paymentInfo=new PaymentInfo();

        bankInfo.setBankName("BCR");
        bankInfo.setIBAN("Iban1");
        bankInfo.setId(1L);
        paymentInfo.setId(1L);
        paymentInfo.setBonusForSuccess(66.0);
        paymentInfo.setSalaryLevel(SalaryLevelEnum.ASSOCIATE);
        paymentInfo.setNumberOfShares(0);
        paymentInfo.setMonthlyBasicSalary(99.0);

        employee.setName("Andrei");
        employee.setAddress("Sibiu");
        employee.setId(2L);
        employee.setGender(GenderEnum.MALE);
        employee.setReligion("none");
        employee.setDateOfBirth(LocalDate.now());
        employee.setHoursPerWeek(30);
        employee.setSurname("Popa");
        employee.setBankInfo(bankInfo);
        employee.setPaymentInfo(paymentInfo);
        testEmployees.add(employee);

        employee=new Employee();
        employee.setName("John");
        employee.setAddress("Sibiu");
        employee.setId(5L);
        employee.setGender(GenderEnum.MALE);
        employee.setReligion("none");
        employee.setDateOfBirth(LocalDate.now());
        employee.setHoursPerWeek(30);
        employee.setSurname("Popa");
        employee.setBankInfo(bankInfo);
        employee.setPaymentInfo(paymentInfo);
        testEmployees.add(employee);

        TypedQuery<Employee> typedQuery = mock(TypedQuery.class);
        when(entityManager.createQuery("SELECT C FROM Employee c", Employee.class)).thenReturn(typedQuery);

        when(typedQuery.getResultList()).thenReturn(testEmployees);

        List<EmployeeDto> result = employeeBean.findAllEmployeesByName("John");

        verify(entityManager).createQuery("SELECT C FROM Employee c", Employee.class);
        verify(typedQuery).getResultList();

        assertEquals(1, result.size());

        assertEquals(testEmployees.get(1).getName(), result.get(0).getName());
        assertEquals(testEmployees.get(1).getSurname(), result.get(0).getSurname());
        assertEquals(testEmployees.get(1).getGender(), result.get(0).getGender());
        assertEquals(testEmployees.get(1).getDateOfBirth(), result.get(0).getDateOfBirth());
        assertEquals(testEmployees.get(1).getAddress(), result.get(0).getAddress());
        assertEquals(testEmployees.get(1).getReligion(), result.get(0).getReligion());
        assertEquals(testEmployees.get(1).getHoursPerWeek(), result.get(0).getHoursPerWeek());

    }

    @Test
    void findAllEmployeesByName_negative_NoEmployees() {

        TypedQuery<Employee> typedQuery = mock(TypedQuery.class);
        when(entityManager.createQuery("SELECT C FROM Employee c", Employee.class)).thenReturn(typedQuery);

        when(typedQuery.getResultList()).thenReturn(Collections.emptyList());
        List<EmployeeDto> result = employeeBean.findAllEmployeesByName("NonExistingName");

        verify(entityManager).createQuery("SELECT C FROM Employee c", Employee.class);
        verify(typedQuery).getResultList();


        assertTrue(result.isEmpty());
    }

    @Test
    void findById_positive_idExists() {

        Long testEmployeeId = 1L;
        Employee mockEmployee = new Employee();

        BankInfo bankInfo=new BankInfo();
        PaymentInfo paymentInfo=new PaymentInfo();

        bankInfo.setBankName("BCR");
        bankInfo.setIBAN("Iban1");
        bankInfo.setId(1L);
        paymentInfo.setId(1L);
        paymentInfo.setBonusForSuccess(66.0);
        paymentInfo.setSalaryLevel(SalaryLevelEnum.ASSOCIATE);
        paymentInfo.setNumberOfShares(0);
        paymentInfo.setMonthlyBasicSalary(99.0);

        mockEmployee.setId(testEmployeeId);
        mockEmployee.setName("John Doe");
        mockEmployee.setBankInfo(bankInfo);
        mockEmployee.setPaymentInfo(paymentInfo);
        when(entityManager.find(Employee.class, testEmployeeId)).thenReturn(mockEmployee);

        EmployeeDto result = employeeBean.findById(testEmployeeId);

        verify(entityManager).find(Employee.class, testEmployeeId);

        assertNotNull(result);
        assertEquals(testEmployeeId, result.getId());
        assertEquals("John Doe", result.getName());
    }

    @Test
    void findById_negative_idDoesNotExist() {

        Long nonExistingEmployeeId = 999L;

        when(entityManager.find(Employee.class, nonExistingEmployeeId)).thenReturn(null);

        EmployeeDto result = employeeBean.findById(nonExistingEmployeeId);

        verify(entityManager).find(Employee.class, nonExistingEmployeeId);

        assertNull(result);
    }


}