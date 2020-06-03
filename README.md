# SpringBoot_rest_api
Sample Rest API using Spring Boot and Hibernate to perform CRUD operation in MYSQL database.
Liquibase is used for DB version control. 

## Use of Liquibase
Liquibase is an open-source solution for managing revisions of your database schema scripts. It works across various types of databases and supports various file formats for defining the DB structure. The feature that is probably most attractive in Liquibase is its ability to roll changes back and forward from a specific point — saving you from needing to know what was the last change/script you ran on a specific DB instance.

`Liquibase uses scripts — referred to as "changesets" — to manage the changes you do to your DB. The changesets files can be in various formats including XML, JSON, YAML, and SQL. In the examples below, I'm using the XML format.`

As you continue to change and enhance your DB structure through the development lifecycle, you'll add more changesets. A master file lists all the changeset files (or the directories where they are). In parallel, Liquibase tracks in your database which changesets have already run.

When you issue a Liquibase update command, Liquibase looks at the current state of your DB and identifies which changes have already happened. Then, it runs the rest of the changes, getting you to the latest revision of the structure you are defining.

By integrating Liquibase into your overall code version management system and continuous integration platform, you can sync up your database versions with your app version. In my case, this would, of course, mean integration with Oracle Developer Cloud Service (DevCS) — which you get for free with the Oracle Database Cloud Service. In the video below, I show a flow that covers:

```
-Tracking my DBA tasks in the issue system.
-Modifying a local MySQL DB with Liquibase (doing forward and backward rolls).
-Adding a changeset defining a new table.
-Committing to Git.
-Automatic build implementing the changes in Oracle Database Cloud Service.
-Automatic testing with UT/PLSQL.
```
