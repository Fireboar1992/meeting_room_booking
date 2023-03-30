# meeting_room_booking

Api sequence diagram

![single meeting room booking](https://user-images.githubusercontent.com/48642782/228935705-72e3a3ca-dfca-41d9-95b3-df2bef80d482.png)

Choose the Master branch for the repository.

Configuration of the Project:
1. Java 1.8
2. MySql

Step 1. execute these sql syntax
```
  CREATE TABLE `meeting_room`.`meeting_room` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(120) NOT NULL,
  `status` INT NOT NULL,
  `start_time` DATETIME NULL,
  `end_time` DATETIME NULL,
  `last_update_time` DATETIME NULL,
  `capacity` INT NOT NULL,
  `book_by` VARCHAR(120) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE);
  
  CREATE TABLE `meeting_room`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(120) NOT NULL,
  `password` VARCHAR(120) NOT NULL
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
  UNIQUE INDEX `password_UNIQUE` (`password` ASC) VISIBLE);
  
  INSERT INTO meeting_room.user (username, password) VALUES('MRB001', 'MRB001@pwd');
```  
 Step 2. check api by postman
 
 import the file 'MeetingRoomBooking.postman_collection.json'
  
  
  
  
  
