package it.unimib.gup.repository.groups;

import it.unimib.gup.model.Category;
import it.unimib.gup.model.Group;

public interface IGroupsRepository {

    Group saveGroup(String name, String description, Category category);
}
