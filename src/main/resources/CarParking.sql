DROP TABLE CARPARKING IF EXISTS;

   CREATE TABLE CARPARKING  (
       CARPARKNO VARCHAR(10) NOT NULL,
       ADDRESS VARCHAR(100),
       XCOORD VARCHAR(32),
       YCOORD VARCHAR(32),
       PRIMARY KEY (CARPARKNO)
   ) ;

   DROP TABLE PARKINGLOT IF EXISTS;

   CREATE TABLE PARKINGLOT  (
       CARPARKNO VARCHAR(10) NOT NULL,
       TOTALLOTS VARCHAR(5),
       AVILABLELOTS VARCHAR(5),
       LOTTYPE VARCHAR(5),
       UPDATEDATETIME VARCHAR(32),
       PRIMARY KEY (CARPARKNO)
   ) ;
