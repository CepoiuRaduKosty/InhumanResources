# GLASSFISH INTELLIJ CONFIG GUIDE
#

1. Go to Edit Configurations in Intellij:
![step1.png](Documentatie/PNGs/glassfishIntellijConfig/step1.PNG)
2. In the window that just appeared, click in the left pane the link "Add new..", and select Glassfish Server, Local variant:
![step2.png](Documentatie/PNGs/glassfishIntellijConfig/step2.PNG)
3. Make sure that the forms are filled like this:
![step3.png](Documentatie/PNGs/glassfishIntellijConfig/step3.PNG)
4. Click plus button at Before Launch pane
![step4.png](Documentatie/PNGs/glassfishIntellijConfig/step4.PNG)
5. Select Build Artifacts from the list
![step5.png](Documentatie/PNGs/glassfishIntellijConfig/step5.PNG)
6. If this list contains a .war file, jump to step 8, otherwise continue
![step6.png](Documentatie/PNGs/glassfishIntellijConfig/step6.PNG)
7. To get the .war file, project must be built; close the windows and go to the gradle panel and go to build and run it (double click); wait for it to finish
![step7.png](Documentatie/PNGs/glassfishIntellijConfig/step7.PNG)
8. After the build has been completed, go back to step 6 (Select Artifacts window) and select first .war file (not exploded)
![step8.png](Documentatie/PNGs/glassfishIntellijConfig/step8.PNG)
9. This is how that pane should look like
![step9.png](Documentatie/PNGs/glassfishIntellijConfig/step9.PNG)
10. Go to the deployment tab of same window and add artifact
![step10.png](Documentatie/PNGs/glassfishIntellijConfig/step10.PNG)
11. Select same war file as step 8
![step11.png](Documentatie/PNGs/glassfishIntellijConfig/step11.PNG)
12. This is how the window should look like (click ok)
![step12.png](Documentatie/PNGs/glassfishIntellijConfig/step12.PNG)
13. Now Glassfish should be available in the run list
![step13.png](Documentatie/PNGs/glassfishIntellijConfig/step13.PNG)

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