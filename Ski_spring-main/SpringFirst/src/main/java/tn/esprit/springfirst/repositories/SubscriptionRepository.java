package tn.esprit.springfirst.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.springfirst.entities.Subscription;
import tn.esprit.springfirst.entities.TypeSubscription;

import java.util.List;

@Repository

public interface SubscriptionRepository extends JpaRepository<Subscription,Integer> {
    List<Subscription> findByTypeSubscription(TypeSubscription typeSubscription);
}
