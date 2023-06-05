package com.nsid.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.nsid.demo.model.City;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface CityRepository extends CrudRepository<City, Integer> {

}