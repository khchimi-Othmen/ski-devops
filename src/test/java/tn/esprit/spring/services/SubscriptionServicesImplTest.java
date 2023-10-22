package tn.esprit.spring.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.Null;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.spring.entities.Skier;
import tn.esprit.spring.entities.Subscription;
import tn.esprit.spring.entities.TypeSubscription;
import tn.esprit.spring.repositories.ISkierRepository;
import tn.esprit.spring.repositories.ISubscriptionRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
;
        import static org.junit.jupiter.api.Assertions.assertTrue;
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class SubscriptionServicesImplTest {

    @InjectMocks
    private SubscriptionServicesImpl subscriptionService;

    @Mock
    private ISubscriptionRepository subscriptionRepository;

    @Mock
    private ISkierRepository skierRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddSubscription() {
        Subscription sampleSubscription = new Subscription();
        sampleSubscription.setTypeSub(TypeSubscription.ANNUAL); // Set the type as needed
        sampleSubscription.setStartDate(LocalDate.of(2023,02,02));
        sampleSubscription.setStartDate(LocalDate.of(2023,03,02));
        sampleSubscription.setPrice((float) 25);
        // Mock behavior of subscriptionRepository.save
        Mockito.when(subscriptionRepository.save(Mockito.any(Subscription.class)))
                .thenReturn(sampleSubscription);

        Subscription savedSubscription = subscriptionService.addSubscription(sampleSubscription);

        assertEquals(sampleSubscription, savedSubscription);
    }


    @Test
    public void testRetrieveSubscriptions() {
        // Mock behavior for the repository method(s) used in the service method
        List<Subscription> sampleSubscriptions = new ArrayList<>();
        Subscription subscription1 = new Subscription();
        subscription1.setNumSub(1L);
        subscription1.setEndDate(LocalDate.now());
        sampleSubscriptions.add(subscription1);

        // Mock behavior for repository methods
        Mockito.when(subscriptionRepository.findDistinctOrderByEndDateAsc())
                .thenReturn(sampleSubscriptions);
        Mockito.when(skierRepository.findBySubscription(Mockito.any(Subscription.class)))
                .thenReturn(new Skier(1L, "John", "Doe",LocalDate.of(2000, 1, 1),"Tunis",null,null,null));

        // Call the service method
        subscriptionService.retrieveSubscriptions();

        // Use Mockito.verify to check if the expected behavior (e.g., repository method calls) occurred
        Mockito.verify(subscriptionRepository).findDistinctOrderByEndDateAsc();
        Mockito.verify(skierRepository).findBySubscription(Mockito.any(Subscription.class));
    }

    // Add similar test methods for other service methods

    @Test
    public void testGetSubscriptionByType() {
        Set<Subscription> sampleSubscriptions = new HashSet<>();
        Subscription subscription1 = new Subscription();
        subscription1.setTypeSub(TypeSubscription.MONTHLY);
        sampleSubscriptions.add(subscription1);

        // Mock behavior of repository method
        Mockito.when(subscriptionRepository.findByTypeSubOrderByStartDateAsc(TypeSubscription.MONTHLY))
                .thenReturn(sampleSubscriptions);

        Set<Subscription> result = subscriptionService.getSubscriptionByType(TypeSubscription.MONTHLY);

        assertTrue(result.contains(subscription1));
    }
}

