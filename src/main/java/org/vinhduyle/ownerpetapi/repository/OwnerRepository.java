package org.vinhduyle.ownerpetapi.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.vinhduyle.ownerpetapi.model.Owner;

@Repository
public interface OwnerRepository extends MongoRepository<Owner, String> {
}