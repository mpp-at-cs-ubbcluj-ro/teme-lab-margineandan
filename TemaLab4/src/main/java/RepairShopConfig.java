import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import repository.ComputerRepairRequestRepository;
import repository.ComputerRepairedFormRepository;
import repository.file.ComputerRepairRequestFileRepository;
import repository.file.ComputerRepairedFormFileRepository;
import repository.jdbc.ComputerRepairRequestJdbcRepository;
import repository.jdbc.ComputerRepairedFormJdbcRepository;
import repository.mock.ComputerRepairRequestInMemoryRepository;
import services.ComputerRepairServices;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
public class RepairShopConfig {
    @Bean
    Properties getProps() {
        Properties props = new Properties();
        try {
            System.out.println("Searching bd.config in directory:" + (new File(".")).getAbsolutePath());
            try (InputStream input = getClass().getClassLoader().getResourceAsStream("bd.config")) {
                if (input == null) {
                    throw new IOException("bd.config file not found in resources");
                }
                props.load(input);
            }

            if (!props.containsKey("jdbc.url")) {
                throw new IOException("jdbc.url missing in bd.config");
            }

            System.out.println("Database URL: " + props.getProperty("jdbc.url"));
        } catch (IOException e) {
            System.out.println("Configuration file bd.config not found or invalid: " + e);
        }
        return props;
    }

    @Bean
    ComputerRepairRequestRepository requestsRepo(){
        //return new ComputerRepairRequestFileRepository("C:\\Users\\margi\\OneDrive\\Desktop\\FACULTATE\\AN 2 - SEMESTRUL 2\\MPP\\teme-lab-margineandan\\TemaLab4\\ComputerRequests.txt");
        return new ComputerRepairRequestJdbcRepository(getProps());
    }

    @Bean
    ComputerRepairedFormRepository formsRepo(){
        //return new ComputerRepairedFormFileRepository("C:\\Users\\margi\\OneDrive\\Desktop\\FACULTATE\\AN 2 - SEMESTRUL 2\\MPP\\teme-lab-margineandan\\TemaLab4\\RepairedForms.txt", requestsRepo());
        return new ComputerRepairedFormJdbcRepository(getProps());
    }

    @Bean
    ComputerRepairServices services(){
        return new ComputerRepairServices(requestsRepo(),formsRepo());
    }

}
