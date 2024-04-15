package tn.esprit.springfirst.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.springfirst.entities.*;
import tn.esprit.springfirst.repositories.CourseRepository;
import tn.esprit.springfirst.repositories.PisteRepository;
import tn.esprit.springfirst.repositories.SkierRepository;
import tn.esprit.springfirst.repositories.SubscriptionRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class SkierServiceImpl implements ISkierService{
    SkierRepository skierRepository;
    CourseRepository courseRepository;
    PisteRepository pisteRepository;
    SubscriptionRepository subscriptionRepository;
    @Override
    public Skier addSkier(Skier skier) {

        return skierRepository.save(skier);
    }

    @Override
    public Skier updateSkier(Skier skier) {

        return skierRepository.save(skier);
    }

    @Override
    public void removeSkier(Integer idSkier) {
        skierRepository.deleteById(idSkier);

    }

    @Override
    public Skier retriveSkier(Integer idSkier) {
        return skierRepository.findById(idSkier).get();
    }

    @Override
    public List<Skier> retriveAllSkier() {
        return skierRepository.findAll();
    }

    Skier assignSkierToPiste(Long numSkieur, Long numPiste){
        Skier skier=skierRepository.findByNumSkier(numSkieur);
        Piste piste=pisteRepository.findByNumPiste(numPiste);
        List<Piste> pisteList=new ArrayList<>();

        if(skier.getPistes()!=null){
            pisteList=skier.getPistes();
        }
        pisteList.add(piste);
        skier.setPistes(pisteList);
        skierRepository.save(skier);
        return skier;
    }

    @Transactional
    public Skier addSkieurAndAssignToCourse(Skier skieur, Integer numCourse){
        Course course = courseRepository.findByNumCourse(numCourse);
        Skier skier=skierRepository.save(skieur);

        Set<Registration> registrationSet;
        registrationSet=skieur.getRegistrationSet();
        registrationSet.stream().forEach(
                registration -> {
                    registration.setCourse(course);
                    registration.setSkier(skier);
                }
        );
        return skier;
    }

    List<Skier> retrieveSkiersBySubscriptionType(TypeSubscription typeAbonnement){
        List<Subscription> subscriptions=subscriptionRepository.findByTypeSubscription(typeAbonnement);
        List<Skier> skierList=new ArrayList<>();

        for (Subscription subscription : subscriptions){
            skierList.add(subscription.getSkier());
        }
        return skierList;
    }
}
