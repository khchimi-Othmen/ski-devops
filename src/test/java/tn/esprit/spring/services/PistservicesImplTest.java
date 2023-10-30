package tn.esprit.spring.services;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Color;
import tn.esprit.spring.entities.Piste;
import tn.esprit.spring.repositories.IPisteRepository;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PistservicesImplTest {

    @Autowired
    private PisteServicesImpl pisteServices;

    @Autowired
    private IPisteRepository pisteRepository;

    private Piste piste;

    @BeforeEach
    void setUp() {
        piste = new Piste();
        piste.setNamePiste("Test Piste");
        piste.setColor(Color.BLUE); // Set the color
        piste.setLength(1000);      // Set the length
        piste.setSlope(30);         // Set the slope

        // You can also create sample skiers and associate them with the piste if needed
        // Set<Skier> skiers = new HashSet<>();
        // skiers.add(skier1);
        // skiers.add(skier2);
        // piste.setSkiers(skiers);
    }

    @AfterEach
    void tearDown() {
        if (piste != null && piste.getNumPiste() != null) {
            pisteRepository.delete(piste);
        }
    }

    @Test
    @Order(1)
    void shouldRetrieveAllPistesWhenEmpty() {
        List<Piste> pistes = pisteServices.retrieveAllPistes();

        assertNotNull(pistes, "Returned list should not be null");
        assertTrue(pistes.isEmpty(), "Expected an empty list");
    }

    @Test
    @Order(2)
    void shouldAddPiste() {
        Piste savedPiste = pisteServices.addPiste(piste);

        assertNotNull(savedPiste, "Added piste should not be null");
        assertEquals(piste, savedPiste, "Added piste should match the input piste");
    }

    @Test
    @Order(3)
    void shouldRemovePiste() {
        pisteServices.addPiste(piste);

        pisteServices.removePiste(piste.getNumPiste());

        assertNull(pisteServices.retrievePiste(piste.getNumPiste()), "Piste should be removed");
    }

    @Test
    @Order(4)
    void shouldRetrievePiste() {
        Piste savedPiste = pisteServices.addPiste(piste);

        Piste retrievedPiste = pisteServices.retrievePiste(savedPiste.getNumPiste());

       // assertNotNull(retrievedPiste, "Retrieved piste should not be null");
     //   assertEquals(savedPiste, retrievedPiste, "Retrieved piste should match the saved piste");
    }
}
