package tn.esprit.spring.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.entities.Subscription;
import tn.esprit.spring.entities.TypeSubscription;
import tn.esprit.spring.repositories.ISkierRepository;
import tn.esprit.spring.repositories.ISubscriptionRepository;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.*;

class JUnitSub {

    @Mock
    private ISubscriptionRepository subscriptionRepository;

    @Mock
    private ISkierRepository skierRepository;

    private SubscriptionServicesImpl subscriptionServices;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        subscriptionServices = new SubscriptionServicesImpl(subscriptionRepository, skierRepository);
    }
    @Test
    void addSubscription() {
        Subscription subscription = new Subscription();
        subscription.setTypeSub(TypeSubscription.ANNUAL); // Set the type as needed
        subscription.setStartDate(LocalDate.of(2023,02,02));
        subscription.setPrice((float) 25.13);
        subscription.setNumSub(1l);

        // Mock repository behavior
        when(subscriptionRepository.save(subscription)).thenReturn(subscription);

        // Call the service method
        Subscription savedSubscription = subscriptionServices.addSubscription(subscription);

        // Print debug information
        System.out.println("NumSub: " + savedSubscription.getNumSub());
        System.out.println("StartDate: " + savedSubscription.getStartDate());
        System.out.println("Price: " + savedSubscription.getPrice());
        System.out.println("EndDate: " + savedSubscription.getEndDate());

        // Assertions
        Assertions.assertNotNull(savedSubscription.getNumSub(), "NumSub should not be null");
        Assertions.assertEquals(
                subscription.getStartDate().plusYears(1),
                savedSubscription.getEndDate(),
                "End date should be correct for an annual subscription"
        );


        // Verify repository interaction
        verify(subscriptionRepository, times(1)).save(subscription);
    }

    @Test
    void updateSubscription() {
        Subscription subscription = new Subscription();

        when(subscriptionRepository.save(subscription)).thenReturn(subscription);

        Subscription updatedSubscription = subscriptionServices.updateSubscription(subscription);

        Assertions.assertEquals(subscription, updatedSubscription);
        verify(subscriptionRepository, times(1)).save(subscription);
    }

    @Test
    void retrieveSubscriptionById() {
        Long subscriptionId = 1L;
        Subscription subscription = new Subscription();
        subscription.setTypeSub(TypeSubscription.ANNUAL); // Set the type as needed
        subscription.setStartDate(LocalDate.of(2023,02,02));
        subscription.setPrice((float) 25.13);
        subscription.setNumSub(subscriptionId);

        // Mock repository behavior
        when(subscriptionRepository.save(subscription)).thenReturn(subscription);

        // Call the service method
        Subscription savedSubscription = subscriptionServices.addSubscription(subscription);
        when(subscriptionRepository.findById(subscriptionId)).thenReturn(Optional.of(subscription));

        Subscription retrievedSubscription = subscriptionServices.retrieveSubscriptionById(subscriptionId);

        System.out.println("Expected Subscription: " + subscription);
        System.out.println("Retrieved Subscription: " + retrievedSubscription);

        Assertions.assertEquals(subscription, retrievedSubscription);
    }


    @Test
    void getSubscriptionByType() {
        TypeSubscription subscriptionType = TypeSubscription.ANNUAL;
        Set<Subscription> subscriptions = new HashSet<>();
        subscriptions.add(new Subscription());

        when(subscriptionRepository.findByTypeSubOrderByStartDateAsc(subscriptionType)).thenReturn(subscriptions);

        Set<Subscription> retrievedSubscriptions = subscriptionServices.getSubscriptionByType(subscriptionType);

        Assertions.assertEquals(subscriptions, retrievedSubscriptions);
    }


}
