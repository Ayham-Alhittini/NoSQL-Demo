package org.banksystem.banksystemdemo.data;

import com.decentraldbcluster.dbclient.odm.collection.DbClusterCollection;
import com.decentraldbcluster.dbclient.odm.database.DbClusterDatabase;
import org.banksystem.banksystemdemo.entities.*;
import org.springframework.stereotype.Component;

@Component
public class BankSystemDatabase extends DbClusterDatabase {
    public DbClusterCollection<Loan> loans;
    public DbClusterCollection<AppUser> users;
    public DbClusterCollection<Customer> customers;
    public DbClusterCollection<BankAccount> accounts;
    public DbClusterCollection<Transaction> transaction;
    public DbClusterCollection<AppNotification> notifications;
//    public DbClusterCollection<ExtraEntity> extraCollection;
}
