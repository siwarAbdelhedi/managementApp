package CRUDApp.emsbackend.service;

import CRUDApp.emsbackend.dto.EmployeeDto;
import CRUDApp.emsbackend.dto.LoginDto;
import CRUDApp.emsbackend.response.LoginResponse;

import java.util.List;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);

    EmployeeDto getEmployeeById(Long employeeId);

    List<EmployeeDto> getAllEmployees();

    EmployeeDto updateEmployee(Long employeeId, EmployeeDto updateEmployeeDto);

    void deleteEmployee(Long employeeId);

    String addEmployee(EmployeeDto employeeDto);

    LoginResponse loginEmployee(LoginDto loginDto);
}