package it.unimib.gup.repository.groups;

import it.unimib.gup.model.Category;

public interface IGroupsRepository {

    void saveGroup(String id, String name, String description, Category category, String color);
}
