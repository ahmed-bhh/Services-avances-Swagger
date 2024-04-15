package tn.esprit.springfirst.services;

import tn.esprit.springfirst.entities.*;
import tn.esprit.springfirst.repositories.CourseRepository;
import tn.esprit.springfirst.repositories.RegistrationRepository;
import tn.esprit.springfirst.repositories.SkierRepository;
import tn.esprit.springfirst.repositories.SubscriptionRepository;

import java.util.*;

public class RegistrationServiceImpl implements IRegistration{
    RegistrationRepository registrationRepository;
    SkierRepository skierRepository;
    CourseRepository courseRepository;
    SubscriptionRepository subscriptionRepository;
    @Override
    public Registration add(Registration registration) {
        return registrationRepository.save(registration);
    }

    @Override
    public Registration update(Registration registration) {
        return registrationRepository.save(registration);
    }

    @Override
    public void delete(Long id) {
        registrationRepository.deleteById(id);
    }

    @Override
    public List<Registration> getall() {
        return registrationRepository.findAll();
    }

    @Override
    public Registration getbyid(Long id) {
        return registrationRepository.findByNumRegistration(id);
    }

    Registration addRegistrationAndAssignToSkier(Registration registration, Long numSkier){
        Registration r=registrationRepository.save(registration);
        Skier skier= skierRepository.findByNumSkier(numSkier);

        r.setSkier(skier);
        Set<Registration> registrationSet=skier.getRegistrationSet();
        registrationSet.add(registration);
        skier.setRegistrationSet(registrationSet);

        return registration;
    }

    Registration assignRegistrationToCourse(Long numRegistration, Integer numCourse){
        Registration registration=registrationRepository.findByNumRegistration(numRegistration);
        Course course=courseRepository.findByNumCourse(numCourse);

        registration.setCourse(course);

        Set<Registration> registrationSet=course.getRegistrationSet();
        registrationSet.add(registration);
        course.setRegistrationSet(registrationSet);

        return registration;
    }

    Set<Subscription> getSubscriptionByType(TypeSubscription type){
        List<Subscription> subscriptions=subscriptionRepository.findByTypeSubscription(type);
        Collections.sort(subscriptions, new Comparator<Subscription>() {
            @Override
            public int compare(Subscription o1, Subscription o2) {
                return o1.getStartDate().compareTo(o2.getStartDate());
            }
        });
        return new HashSet<>(subscriptions);
    }


}
