package modules.manage;

import modules.manage.entities.EntityFileDatabaseFactory;
import modules.manage.users.UserFileDatabaseFactory;

public class DatabaseHandler {

    private UserFileDatabaseFactory userManagerFactory;
    private EntityFileDatabaseFactory entityManagerFactory;

    public DatabaseHandler(UserFileDatabaseFactory userManagerFactory, EntityFileDatabaseFactory entityManagerFactory) {
        this.userManagerFactory = userManagerFactory;
        this.entityManagerFactory = entityManagerFactory;
    }

    public UserFileDatabaseFactory getUserManagerFactory() {
        return userManagerFactory;
    }

    public EntityFileDatabaseFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }
}
