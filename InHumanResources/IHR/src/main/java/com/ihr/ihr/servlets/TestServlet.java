package com.ihr.ihr.servlets;

import com.ihr.ihr.common.dtos.EmployeeDto;
import com.ihr.ihr.common.enums.GenderEnum;
import com.ihr.ihr.ejb.EmployeeService;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

@WebServlet(name = "TestServlet", value = "/test")
public class TestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {

        ArrayList<String> debug = new ArrayList<>();

        // test code
        debug.add("croissant");
       // employeeService.deleteEmployeeById(123);
        //employeeService.createEmployee(115,"radu","marian",3,3, GenderEnum.FEMALE, LocalDate.now(),"aa","aaaa",40);
      //  employeeService.createEmployee(185,"radu","gabi",3,3, GenderEnum.FEMALE, LocalDate.now(),"aa","aaaa",40);
       // employeeService.createEmployee(195,"hau ","mau",3,3, GenderEnum.FEMALE, LocalDate.now(),"aa","aaaa",40);
        // employeeService.updateEmployee(125,"nuradu","nuradu",3,3, GenderEnum.FEMALE, LocalDate.now(),"aa","aaaa",40);
       //employeeService.findByName("radu");
        for(EmployeeDto employeeDto: employeeService.findAllEmployees())
        {
            debug.add(employeeDto.getName()+" "+ employeeDto.getSurname());
        }

        debug.add("ho fa");
        for(EmployeeDto employeeDto: employeeService.findAllEmployeesByName("radu"))
        {
            debug.add(employeeDto.getName()+" "+ employeeDto.getSurname());
        }

      debug.add( employeeService.findById(125).getName());

            // end test

        request.setAttribute("debugtxt", debug);
        request.getRequestDispatcher("/WEB-INF/pages/test.jsp").forward(request, response);
    }
    @Inject
    EmployeeService employeeService;

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

    }
}