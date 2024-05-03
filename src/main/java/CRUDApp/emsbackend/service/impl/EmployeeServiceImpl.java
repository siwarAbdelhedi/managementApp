package CRUDApp.emsbackend.service.impl;

import CRUDApp.emsbackend.dto.EmployeeDto;
import CRUDApp.emsbackend.dto.LoginDto;
import CRUDApp.emsbackend.entity.Employee;
import CRUDApp.emsbackend.exception.ResoourceNotFoundException;
import CRUDApp.emsbackend.mapper.EmployeeMapper;
import CRUDApp.emsbackend.repository.EmployeeRepository;
import CRUDApp.emsbackend.response.LoginResponse;
import CRUDApp.emsbackend.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor


public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {


        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
       Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new ResoourceNotFoundException("Employee is not exist with given id :" +employeeId));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(employee -> EmployeeMapper.mapToEmployeeDto(employee)).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updateEmployee) {
        Employee employee= employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResoourceNotFoundException("Employee is not exist with id :" +employeeId)
        );

        employee.setFirstName(updateEmployee.getFirstName());
        employee.setLastName(updateEmployee.getLastName());
        employee.setEmail(updateEmployee.getEmail());

        Employee updateEmployeeObj = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(updateEmployeeObj);
    }

    @Override
    public void deleteEmployee(Long employeeId) {

        Employee employee= employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResoourceNotFoundException("Employee is not exist with id :" +employeeId)
        );
        employeeRepository.deleteById(employeeId);
    }

    @Override
    public String addEmployee(EmployeeDto employeeDto) {

        String rawPassword = employeeDto.getPassword();

        String encodedPassword = rawPassword.startsWith("$2a$") ? rawPassword : passwordEncoder.encode(rawPassword);

        Employee employee = new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail(),
                //this.PasswordEncoder.encode(employeeDto.getPassword())
                encodedPassword
        );
        employeeRepository.save(employee);

        return employee.getFirstName();
    }

    @Override
    public LoginResponse loginEmployee(LoginDto loginDto) {

        String msg = "";
        Employee employee1 = employeeRepository.findByEmail(loginDto.getEmail());
        if (employee1 != null) {
            String password = loginDto.getPassword();
            String encodedPassword = employee1.getPassword();
            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
            if (isPwdRight) {
                Optional<Employee> employee = employeeRepository.findOneByEmailAndPassword(loginDto.getEmail(), encodedPassword);
                if (employee.isPresent()) {
                    return new LoginResponse("Login Success", true);
                } else {
                    return new LoginResponse("Login Failed", false);
                }
            } else {
                return new LoginResponse("password Not Match", false);
            }
        }else {
            return new LoginResponse("Email not exits", false);
        }
    }


}
