package com.example.uzkassatask.service.serviceImpl;

import com.example.uzkassatask.entity.Company;
import com.example.uzkassatask.entity.Employee;
import com.example.uzkassatask.model.ResponseCurrencyRate;
import com.example.uzkassatask.model.SignInModel;
import com.example.uzkassatask.model.SignUpModel;
import com.example.uzkassatask.repository.CompanyRepository;
import com.example.uzkassatask.repository.EmployeeRepository;
import com.example.uzkassatask.service.OtherServices;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

@Service
public class OtherServiceImpl implements OtherServices {

    @Autowired
    private JavaMailSender javaMailSender;

    private final String sender="juraevshaxboz0594@gmail.com";

    private final CompanyRepository companyRepository;
    private final EmployeeRepository employeeRepository;

    public OtherServiceImpl(CompanyRepository companyRepository, EmployeeRepository employeeRepository) {
        this.companyRepository = companyRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    @Transactional
    public String signUp(SignUpModel signUpModel) throws Exception {

        Optional<Company> company=companyRepository.findAll()
                .stream()
                .filter(company1 -> company1.getAddress().equals(signUpModel.getCompanyAddress())
                &&company1.getZipCode()==signUpModel.getCompanyZipCode()
                &&company1.getName().equals(signUpModel.getCompanyName()))
                .findFirst();
        Employee employee;
        if (company.isPresent()){
            employee=createEmployee(signUpModel, company.get());
        }
        else{
            Company company1=new Company();
            company1.setName(signUpModel.getCompanyName());
            company1.setZipCode(signUpModel.getCompanyZipCode());
            company1.setAddress(signUpModel.getCompanyAddress());
            company1=companyRepository.save(company1);
            employee=createEmployee(signUpModel, company1);
        }
        employeeRepository.save(employee);
//        sendActivationToEmail(signUpModel.getEmpEmail(), employee.getCompany().getId());
        return "activation link sent";
    }

    private void sendActivationToEmail(String email, long companyId) throws Exception {

        try {

            // Creating a simple mail message
            SimpleMailMessage mailMessage
                    = new SimpleMailMessage();

            // Setting up necessary details
            mailMessage.setFrom(sender);
            mailMessage.setTo(email);
            mailMessage.setText("localhost:8183/api/v1/employee/"+companyId+"/activate");
            mailMessage.setSubject("activation link");

            // Sending the mail
            javaMailSender.send(mailMessage);
        }

        // Catch block to handle the exceptions
        catch (Exception e) {
            throw new Exception("Error while Sending Mail");
        }

    }
    private Employee createEmployee(SignUpModel signUpModel, Company company){
        Employee employee=new Employee();
        employee.setName(signUpModel.getEmpName());
        employee.setEmail(signUpModel.getEmpEmail());
        employee.setUsername(signUpModel.getEmpUsername());
        employee.setPassword(signUpModel.getEmpPassword());
        employee.setCompany(company);
        return employee;
    }
    @Value("${org.springframework.security.signing-key}")
    private String token;

    @Override
    @Transactional
    public String singIn(SignInModel signInModel) {

        Optional<Employee> employee=employeeRepository
                .findByUsernameAndPassword(signInModel.getEmpUsername(), signInModel.getEmpPassword());
        if (employee.isPresent()){

            return token;
        }
        else
            return "not registered";
    }

    @Override
    @Transactional
    public ResponseCurrencyRate getCurrencyRateApiByCode(String code) {

        try {

            URL url = new URL("https://nbu.uz/uz/exchange-rates/json/");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int responseCode = conn.getResponseCode();

            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {

                String inline = "";
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    inline += scanner.nextLine();
                }

                scanner.close();

                JsonParser jsonParser = new JsonParser();
                JsonArray jsonArray = (JsonArray) jsonParser.parse(inline);

                ArrayList<ResponseCurrencyRate> list=new ArrayList<>();
                for (int i = 0; i < jsonArray.size(); i++) {

                    JsonObject object = jsonArray.get(i).getAsJsonObject();
                    list.add(new ResponseCurrencyRate(
                            object.get("title").getAsString(),
                            object.get("code").getAsString(),
                            object.get("cb_price").getAsString(),
                            object.get("nbu_buy_price").getAsString(),
                            object.get("nbu_cell_price").getAsString(),
                            object.get("date").getAsString()
                    ));
                }
                for (ResponseCurrencyRate item :
                        list) {
                    if (code.equals(item.getCode()))
                        return item;

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
