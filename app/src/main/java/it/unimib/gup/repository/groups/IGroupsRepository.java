package it.unimib.gup.repository.groups;

import it.unimib.gup.model.Category;
import it.unimib.gup.model.Group;

public interface IGroupsRepository {

    void saveGroup(String name, String description, Category category);
}
