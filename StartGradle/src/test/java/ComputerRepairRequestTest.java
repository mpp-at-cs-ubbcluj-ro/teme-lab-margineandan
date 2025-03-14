import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ro.mpp2025.model.ComputerRepairRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ComputerRepairRequestTest {

    @Test
    @DisplayName("Test - Getters for ComputerRepairRequest Object")
    void testGetters() {
        ComputerRepairRequest computerRepairRequest =
                new ComputerRepairRequest(1, "Marginean Dan", "Tudor Vladimirescu Nr. 9",
                "0726624048", "HLP3001", "2024-01-01", "Will not open");

        assertEquals(1, computerRepairRequest.getID());
        assertEquals("Marginean Dan", computerRepairRequest.getOwnerName());
        assertEquals("Tudor Vladimirescu Nr. 9", computerRepairRequest.getOwnerAddress());
        assertEquals("0726624048", computerRepairRequest.getPhoneNumber());
        assertEquals("HLP3001", computerRepairRequest.getModel());
        assertEquals("2024-01-01", computerRepairRequest.getDate());
        assertEquals("Will not open", computerRepairRequest.getProblemDescription());
    }

    @Test
    @DisplayName("Test - Setters for ComputerRepairRequest Object")
    void testSetters() {
        ComputerRepairRequest computerRepairRequest = new ComputerRepairRequest();

        computerRepairRequest.setID(1);
        computerRepairRequest.setOwnerName("Marginean Dan");
        computerRepairRequest.setOwnerAddress("Tudor Vladimirescu Nr. 9");
        computerRepairRequest.setPhoneNumber("0726624048");
        computerRepairRequest.setModel("HLP3001");
        computerRepairRequest.setDate("2024-01-01");
        computerRepairRequest.setProblemDescription("Will not open");

        assertEquals(1, computerRepairRequest.getID());
        assertEquals("Marginean Dan", computerRepairRequest.getOwnerName());
        assertEquals("Tudor Vladimirescu Nr. 9", computerRepairRequest.getOwnerAddress());
        assertEquals("0726624048", computerRepairRequest.getPhoneNumber());
        assertEquals("HLP3001", computerRepairRequest.getModel());
        assertEquals("2024-01-01", computerRepairRequest.getDate());
        assertEquals("Will not open", computerRepairRequest.getProblemDescription());
    }
}
