# DATABASE LOCAL CONFIGUARTION GUIDE
#
Before all the steps, make sure you have created a database
in MySQL Workbench with the name "inhuman_resources". And added a user with the following credentials:

```
Login Name: InhumanResourcesUser
Password: InhumanResourcesPassword
```
Also add the following user privileges:

![img5.png](Documentatie/PNGs/img5.png)



##
## Steps to configure the database:

1. Deploy the application and go to http://localhost:4848
2. Go to JDBC -> JDBC Connection Pools -> Press New
3. Fill the form as follows, then click Next 
   ![img_1.png](Documentatie/PNGs/img_1.png)
4. Add this
  ![img_2.png](Documentatie/PNGs/img_2.png)
5. Add the additional properties as follows
  ![img_3.png](Documentatie/PNGs/img_3.png)
6. Check the ping and click Finish
7. Go to JDBC -> JDBC Resources -> Press New
8. Fill the form as follows, then click OK
  ![img_4.png](Documentatie/PNGs/img_4.png)