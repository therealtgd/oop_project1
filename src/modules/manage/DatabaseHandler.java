package modules.manage;


import modules.manage.users.UserDatabaseFactory;

public class DatabaseHandler {

    private UserDatabaseFactory userDatabase;
    private EntityDatabaseFactory entityDatabase;

    public DatabaseHandler(UserDatabaseFactory userDatabase, EntityDatabaseFactory entityManagerFactory) {
        this.userDatabase = userDatabase;
        this.entityDatabase = entityManagerFactory;
    }

    public UserDatabaseFactory getUserDatabase() { return userDatabase; }


    public EntityDatabaseFactory getEntityDatabase() {
        return entityDatabase;
    }
}
