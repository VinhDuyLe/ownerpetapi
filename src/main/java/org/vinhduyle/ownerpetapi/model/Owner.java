package org.vinhduyle.ownerpetapi.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "owners")
public class Owner {
    @Id
    private String id;
    private String name;
    private List<Pet> pets = new ArrayList<>(); // Initialize to avoid NullPointerException

    public void addPet(Pet pet) {
        if (this.pets == null) {
            this.pets = new ArrayList<>();
        }
        this.pets.add(pet);
    }
}