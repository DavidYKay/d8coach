-- phpMyAdmin SQL Dump
-- version 2.11.2.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Sep 26, 2010 at 07:03 AM
-- Server version: 5.0.84
-- PHP Version: 5.2.14-pl0-gentoo

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

--
-- Database: `d8coach`
--

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE IF NOT EXISTS `login` (
  `name` varchar(20) NOT NULL,
  `uid` int(11) NOT NULL auto_increment,
  `passhash` varchar(32) NOT NULL,
  PRIMARY KEY  (`uid`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

-- --------------------------------------------------------

--
-- Table structure for table `missions`
--

CREATE TABLE IF NOT EXISTS `missions` (
  `missionid` int(11) NOT NULL auto_increment,
  `uid` int(11) NOT NULL,
  `target` int(11) NOT NULL,
  PRIMARY KEY  (`missionid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `objectives`
--

CREATE TABLE IF NOT EXISTS `objectives` (
  `oid` int(11) NOT NULL auto_increment,
  `value` text NOT NULL,
  PRIMARY KEY  (`oid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `sessions`
--

CREATE TABLE IF NOT EXISTS `sessions` (
  `uid` int(11) NOT NULL,
  `sesshash` varchar(32) NOT NULL,
  `expires` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `targets`
--

CREATE TABLE IF NOT EXISTS `targets` (
  `tid` int(11) NOT NULL auto_increment,
  `hair` enum('brown','blonde','red','black','dyed') NOT NULL,
  `name` varchar(20) NOT NULL,
  `age` int(11) NOT NULL,
  `build` int(11) NOT NULL,
  `ethnicity` enum('white','black','latino','asian') NOT NULL,
  PRIMARY KEY  (`tid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;
