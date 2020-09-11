package modules.manage;

import modules.manage.entities.EntityManagerFactory;
import modules.manage.users.UserManagerFactory;

public class DataManagerFactory {

    private UserManagerFactory userManagerFactory;
    private EntityManagerFactory entityManagerFactory;

    public DataManagerFactory(UserManagerFactory userManagerFactory, EntityManagerFactory entityManagerFactory) {
        this.userManagerFactory = userManagerFactory;
        this.entityManagerFactory = entityManagerFactory;
    }

    public UserManagerFactory getUserManagerFactory() {
        return userManagerFactory;
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }
}
