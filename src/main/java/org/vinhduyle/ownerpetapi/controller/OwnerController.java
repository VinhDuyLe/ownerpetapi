package org.vinhduyle.ownerpetapi.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vinhduyle.ownerpetapi.model.Owner;
import org.vinhduyle.ownerpetapi.model.Pet;
import org.vinhduyle.ownerpetapi.repository.OwnerRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class OwnerController {

    @Autowired
    private OwnerRepository ownerRepository;

    // GET “/api/owners”, get all owners along with their pets
    @GetMapping("/owners")
    public ResponseEntity<List<Owner>> getAllOwners() {
        try {
            List<Owner> owners = ownerRepository.findAll();
            if (owners.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(owners, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET “/api/owner/{id}”, get an owner by id
    @GetMapping("/owner/{id}")
    public ResponseEntity<Owner> getOwnerById(@PathVariable("id") String id) {
        Optional<Owner> ownerData = ownerRepository.findById(id);
        return ownerData.map(owner -> new ResponseEntity<>(owner, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // POST “/api/owner”, save an owner to MongoDB, along with any possible pets
    @PostMapping("/owner")
    public ResponseEntity<Owner> createOwner(@RequestBody Owner owner) {
        try {
            Owner _owner = ownerRepository.save(new Owner(owner.getId(), owner.getName(), owner.getPets()));
            return new ResponseEntity<>(_owner, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // PUT “/api/owner/{id}/pet, add a pet to an existing owner with the path variable id.
    @PutMapping("/owner/{id}/pet")
    public ResponseEntity<Owner> addPetToOwner(@PathVariable("id") String id, @RequestBody Pet pet) {
        Optional<Owner> ownerData = ownerRepository.findById(id);

        if (ownerData.isPresent()) {
            Owner owner = ownerData.get();
            owner.addPet(pet); // Assuming you have an addPet method in your Owner model
            return new ResponseEntity<>(ownerRepository.save(owner), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE /api/owner/{id}, delete an owner by the id
    @DeleteMapping("/owner/{id}")
    public ResponseEntity<HttpStatus> deleteOwner(@PathVariable("id") String id) {
        try {
            ownerRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}