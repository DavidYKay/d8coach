DROP TABLE IF EXISTS `posts`;
CREATE TABLE `posts` (
	`_id` INTEGER PRIMARY KEY,
	`address` TEXT,
	`caption` TEXT,
	`channel_id` INTEGER,
	`distance` REAL,
	`headline` TEXT,
	`is_event` INTEGER,
	`lat` REAL,
	`lng` REAL,
	`photo_url` TEXT,
	`post_time` INTEGER,
	`rank` INTEGER,
	`start_time` INTEGER,
	`sub_title` TEXT,
	`temp_id` INTEGER,
	`text` TEXT,
	`thumb_url` TEXT,
	`user_id` INTEGER,
	`user_name` TEXT
);
INSERT INTO "posts" VALUES(22,'Seoul, Korea','',1,11095.879406806,'icihere.com',0,37.5665,126.978,'http://icihere.com/ici_images/4427b089-fdac-2d56.gif',1254703245,0,NULL,'ici',6,'ici is most useful when accessed from a mobile device. However, even if you are at your desktop computer, you may view and post stories. Visit the ici web portal at <a href=''http://www.icihere.com''>www.icihere.com</a>. Your ici login is valid on both your desktop as well as your mobile device. 
<br><br>
Some features are only available on the web portal. For example, to add a photo to your story, you must use the ici web portal.
<br><br>','http://icihere.com/ici_thumbs/4427b089-fdac-2d56.gif',52,'ici');
INSERT INTO "posts" VALUES(23,'Paris, France','ici',1,5865.7083737392,'Options',0,48.8567,2.35099,'http://icihere.com/ici_images/4427b089-fdac-2d56.gif',1264566624,0,NULL,'ici',5,'<p>''Options'' allows you to enter your preferences and settings.<br /><br /> You may sort your Headlines by ''Latest'', ''Closest'', or ''Events.'' The ''Latest'' option displays the most recent headlines at the top. The ''Closest'' option shows the stories that occurred nearby at the top. The ''Events'' option displays only events and shows the events occurring in the near future at the top. <br /><br /> To limit Headlines to those nearby, reduce the ''Radius'' setting. The ''Max'' setting set the radius to unlimited. <br /><br /> To enter your user name and password, click the ''Sign In'' button on the title bar. You must sign in to post stories.</p>','http://icihere.com/ici_thumbs/4427b089-fdac-2d56.gif',52,'ici');
INSERT INTO "posts" VALUES(24,'Cairo, Egypt','ici',1,9048.498747257,'Input',0,30.0647,31.2495,'http://icihere.com/ici_images/4427b089-fdac-2d56.gif',1254703180,0,NULL,'ici',4,'To submit a story from your mobile device, click the ''Input'' option. 
<br><br>
If you do not have a connection to the internet, save your story in the Queue and upload them when you have a connection. Even if you turn off ici, your stories will remain in the queue until you upload them.
<br><br>
Note: The location (latitude and longitude) is the last known location at the time you save your story not when you upload the story.<br><br>

You may also submit stories from the ici web portal (icihere.com). The web portal offers many additional features to help you create content.
','http://icihere.com/ici_thumbs/4427b089-fdac-2d56.gif',52,'ici');
INSERT INTO "posts" VALUES(25,'Tokyo, Japan','ici',1,10888.84722301,'Map',0,35.6895,139.692,'http://icihere.com/ici_images/4427b089-fdac-2d56.gif',1264567013,0,NULL,'ici',3,'<p>The ''Map'' option displays a map of your current location. <br /><br /> The blue pin marks your current location. If your mobile device uses GPS (Global Position System) technology, you will see that the location is very accurate. If your mobile device uses cellular triangulation, the accuracy varies widely depending on the signals in your area. <br /><br /> Red pins mark the locations associated with the headlines. If you touch a red pin, you will see the Headline for that location. To view the full story, touch the blue arrow on the right side of the popup. To get directions to that location, touch the car icon on left side of the popup. <br /><br /> The green pin marks your intended location which you may establish by pressing the ''Mark'' button at the top. A green pin drops down from above on the center of the map to indicate your intended location. The ''Mark'' feature is handy for finding out what is happening in an area that you plan to go to. If you mark a spot, all ici functions use the marked location as your current location. For example, your new ici messages are tagged at the marked location. The distances shown are calculated from the marked location instead of your actual location.  <br /><br /></p>','http://icihere.com/ici_thumbs/4427b089-fdac-2d56.gif',52,'ici');
INSERT INTO "posts" VALUES(26,'Moscow, Russia','ici',1,7548.6624395334,'Search',0,55.7558,37.6176,'http://icihere.com/ici_images/4427b089-fdac-2d56.gif',1264566651,0,NULL,'ici',2,'<p>The search button on the top right allows you to specify a search string. When you touch the ''Search'' button, ici retrieves the headlines in the current channel that contain the search string you entered. <br /><br /> Some channels offer a large searchable database of information using the search string. These channels are: Eventful, Local Search, Wikipedia, and Yelp. You will find a rich collection of useful information in these search oriented channels. <br /><br /> If you want to find Thai food nearby, use the Yelp channel and enter ''Thai'' in the search form. You will see a list of local Thai restaurants as well as a link to Yelp reviews.  <br /><br /> Your search terms are stored for future use.  <br /><br /></p>','http://icihere.com/ici_thumbs/4427b089-fdac-2d56.gif',52,'ici');
INSERT INTO "posts" VALUES(28,'Beijing, China','ici',1,11033.727466334,'Channels',0,39.9082,116.398,'http://icihere.com/ici_images/4427b089-fdac-2d56.gif',1264566556,0,NULL,'ici',1,'<p>Channels are a collection of ici stories. When you choose a channel from the ''Channels'' list, ici displays a list of headlines for that channel.   <br /><br /> Some channels are divided into sub-channels which you may select from the tab bar at the top. The first item on the tab bar, which displays the name of the channel, shows all stories for the channel. If you select a sub-channel, you will only see the stories for the sub-channel. <br /><br /> The channel moderator controls the content of the channel. If you post a story to a channel, the channel moderator reviews your story before it is available for viewing.  <br /><br /> Some channels offer a large searchable database of information using the search string. These channels are: Eventful, Local Search, Wikipedia, and Yelp. You will find a rich collection of useful information in these search oriented channels. <br /><br /></p>','http://icihere.com/ici_thumbs/4427b089-fdac-2d56.gif',52,'ici');
INSERT INTO "posts" VALUES(29,'Sydney, Australia','ici logo',1,15988.2084892,'Welcome to ici!',0,-33.8671,151.207,'http://icihere.com/ici_images/4427b089-fdac-2d56.gif',1254703334,0,NULL,'ici',0,'ici is a publishing, social network, and blogging platform designed for mobile devices such as the iPhone and iPod Touch. <br><br>

ici enables mobile people to share interests and activities using the mobile device as the primary viewing and input device. ici is specifically designed for location messaging that informs mobile communities. 
<br><br>
Use ici to find out about what is happening in your area. Select the channel of interest and browse the headlines for news and events nearby. Click a headline to read the full story. Click the ''Map'' button to locate the headlines on a map. 
<br><br>

You may view ici stories without registering but you must register in order to contribute content. <ul><li>To register, go to the Settings page and press the ''Sign In'' button. Then press the ''New User'' button at the bottom of the ''Sign In'' page. You must be connected to the internet in order to register.</li></ul>ici is brand new so you will find a whole new world of mobile publishing possibilities.  
','http://icihere.com/ici_thumbs/4427b089-fdac-2d56.gif',52,'ici');


DROP TABLE IF EXISTS `channels`;
CREATE TABLE `channels` (
    `_id` INTEGER PRIMARY KEY,
	`channel` TEXT,
	`channel_access` INTEGER,
	`channel_desc` TEXT,
	`channel_editor` INTEGER,
	`channel_parent_id` INTEGER,
	`channel_price` INTEGER,
	`channel_type` INTEGER,
	`sec_sequence` INTEGER,
	`status` INTEGER,
	`time_stamp` INTEGER
);

INSERT INTO "channels" VALUES(1,'ici',NULL,'ici help channel',NULL,1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO "channels" VALUES(2,'Architecture',NULL,'Architecture ',NULL,2,NULL,NULL,NULL,NULL,NULL);
INSERT INTO "channels" VALUES(3,'Yelp',NULL,'Yelp',NULL,3,NULL,NULL,NULL,NULL,NULL);
INSERT INTO "channels" VALUES(6,'College',NULL,'College',NULL,6,NULL,NULL,NULL,NULL,NULL);
INSERT INTO "channels" VALUES(7,'Gaming',NULL,'Gaming',NULL,7,NULL,NULL,NULL,NULL,NULL);
INSERT INTO "channels" VALUES(9,'iPhone',NULL,'iPhone',NULL,9,NULL,NULL,NULL,NULL,NULL);
INSERT INTO "channels" VALUES(11,'Free WiFi ',NULL,'Free WiFi ',NULL,11,NULL,NULL,NULL,NULL,NULL);
INSERT INTO "channels" VALUES(12,'Local Search',NULL,'Google Local Search. Enter search string.',NULL,12,NULL,NULL,NULL,NULL,NULL);
INSERT INTO "channels" VALUES(20,'Travel',NULL,'Travel',NULL,20,NULL,NULL,NULL,NULL,NULL);
INSERT INTO "channels" VALUES(21,'Sightings',NULL,'Sightings',NULL,21,NULL,NULL,NULL,NULL,NULL);
INSERT INTO "channels" VALUES(23,'Coffee Shop',NULL,'Coffee Shop',NULL,23,NULL,NULL,NULL,NULL,NULL);
INSERT INTO "channels" VALUES(24,'Weird',NULL,'Weird',NULL,24,NULL,NULL,NULL,NULL,NULL);
INSERT INTO "channels" VALUES(29,'Animals',NULL,'Animals test',NULL,29,NULL,NULL,NULL,NULL,NULL);
INSERT INTO "channels" VALUES(33,'Eventful',NULL,'Eventful',NULL,33,NULL,NULL,NULL,NULL,NULL);
INSERT INTO "channels" VALUES(35,'Art',NULL,'Art',NULL,35,NULL,NULL,NULL,NULL,NULL);
INSERT INTO "channels" VALUES(101,'Wikipedia',NULL,'',NULL,101,NULL,NULL,NULL,NULL,NULL);
INSERT INTO "channels" VALUES(102,'POST',NULL,'Philadelphia Open Studio Tours',NULL,102,NULL,NULL,NULL,NULL,NULL);
INSERT INTO "channels" VALUES(103,'Philagrafika',NULL,'Philagrafika',NULL,103,NULL,NULL,NULL,NULL,NULL);
INSERT INTO "channels" VALUES(106,'DanTrack',NULL,'"It''s universally agreed that our music is the best possible rock music to play in a supermarket." - Walter Becker, 2003  How often do you hear a Steely Dan tune in an odd public place -- grocery store, gas station, liquor store? This is where it gets ',NULL,106,NULL,NULL,NULL,NULL,NULL);
INSERT INTO "channels" VALUES(107,'First Person Arts ',NULL,'The First Person Festival of Memoir and Documentary Art is our biggest event of the year, and the only festival in the world dedicated to memoir and documentary art!',NULL,107,NULL,NULL,NULL,NULL,NULL);
INSERT INTO "channels" VALUES(121,'The Graphic Unconscious',NULL,'Philagrafika - The Graphic Unconscious',NULL,103,NULL,NULL,NULL,NULL,NULL);
INSERT INTO "channels" VALUES(122,'Out of Print',NULL,'Philagrafika - Out of Print',NULL,103,NULL,NULL,NULL,NULL,NULL);
INSERT INTO "channels" VALUES(123,'Independent Projects: A - C',NULL,'Independent Projects: A - C',NULL,103,NULL,NULL,NULL,NULL,NULL);
INSERT INTO "channels" VALUES(124,'Independent Projects: D - J',NULL,'Independent Projects: D - J',NULL,103,NULL,NULL,NULL,NULL,NULL);
INSERT INTO "channels" VALUES(125,'Independent Projects: K - R',NULL,'Independent Projects: K - R',NULL,103,NULL,NULL,NULL,NULL,NULL);
INSERT INTO "channels" VALUES(126,'Independent Projects: S - Z',NULL,'Independent Projects: S - Z',NULL,103,NULL,NULL,NULL,NULL,NULL);
INSERT INTO "channels" VALUES(127,'Book Bombs',NULL,'Book Bombs',NULL,103,NULL,NULL,NULL,NULL,NULL);

DROP TABLE IF EXISTS `clicks`;
CREATE TABLE `clicks` (
	`bad` int(1) DEFAULT '0',
	`click_time` int(8) NOT NULL,
	`good` int(1) DEFAULT '0',
	`lat` float NOT NULL,
	`lng` float NOT NULL,
	`post_id` int(11) NOT NULL,
	`ugly` int(1) DEFAULT '0'
);
