  CREATE TABLE track (
   id int(11) PRIMARY KEY AUTO_INCREMENT,
   title varchar(200) NOT NULL,
   performer varchar(200) NOT NULL,
   duration int(11) NOT NULL,
   album varchar(200) NOT NULL,
   playcount int(11) NOT NULL,
   publicationDate varchar(200) NOT NULL,
   description varchar(200) NOT NULL
 );

 CREATE TABLE user (
   userID int(11) PRIMARY KEY AUTO_INCREMENT,
   username varchar(200) NOT NULL,
   password varchar(200) NOT NULL,
   token varchar(200) NOT NULL
 );

CREATE TABLE playlist (
  id int(11) PRIMARY KEY AUTO_INCREMENT,
  name varchar(200) NOT NULL,
  owner int(11) NOT NULL,
  constraint playlistUser foreign KEY (owner) references user (userID),
);

 CREATE TABLE trackinplaylist (
   trackID int(11) NOT NULL,
   playlistID int(11) NOT NULL,
   offlineavailable bit(1) NOT NULL,
   constraint trackplayID PRIMARY KEY (trackID,playlistID),
   CONSTRAINT trackinplaylist_ibfk_1 FOREIGN KEY (trackID) REFERENCES track (id) ON DELETE CASCADE,
   CONSTRAINT trackinplaylist_ibfk_2 FOREIGN KEY (playlistID) REFERENCES playlist (id) ON DELETE CASCADE
 );


 INSERT INTO user (userID, username, password, token) VALUES
 (2, 'hugotest', '323', '1234-1234-123'),
 (3, 'meron', '54321', '123-1233');

 INSERT INTO playlist (id, name, owner) VALUES
 (1, 'Death metal', 2),
 (3, 'Pop', 3),
 (18, 'Hardcore', 2),
 (21, 'FinalT', 2),
 (25, 'vfdvdf', 2);


 INSERT INTO track (id, title, performer, duration, album, playcount, publicationDate, description) VALUES
 (1, 'Ocean and a rock', 'Lisa Hannigan', 337, 'Sea sew', 0, '', 'descrip'),
 (2, 'So Long, Marianne', 'Leonard Cohen', 546, 'Songs of Leonard Cohen', 0, '', 'descrip2'),
 (3, 'One', 'Metallica', 423, '', 37, '1-11-2001', 'Long version');

 INSERT INTO trackinplaylist (trackID, playlistID, offlineavailable) VALUES
 (1, 1, 0),
 (1, 3, 0),
 (1, 18, 1),
 (1, 21, 0),
 (1, 25, 0),
 (2, 3, 1),
 (3, 1, 1),
 (3, 21, 0),
 (3, 25, 0);
