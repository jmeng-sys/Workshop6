/*Create table PhpMyAdmin*/
CREATE TABLE `travelexperts`.`agentaccounts` ( 
	`AgentId` INT NOT NULL , 
	`Username` VARCHAR(20) NOT NULL , 
	`Password` VARCHAR(200) NOT NULL , 
	PRIMARY KEY (`AgentId`)) ENGINE = InnoDB;

/*Insert Row PhpMyAdmin*/
INSERT INTO `agentaccounts` (`AgentId`, `Username`, `Password`) VALUES ('1', 'DJanet', 'Delton');
INSERT INTO `agentaccounts` (`AgentId`, `Username`, `Password`) VALUES ('2', 'LJudy', 'Lisle');
INSERT INTO `agentaccounts` (`AgentId`, `Username`, `Password`) VALUES ('3', 'RDennis', 'Reynolds');
INSERT INTO `agentaccounts` (`AgentId`, `Username`, `Password`) VALUES ('4', 'CJohn', 'Coville');
INSERT INTO `agentaccounts` (`AgentId`, `Username`, `Password`) VALUES ('5', 'DJanice', 'Dahl');
INSERT INTO `agentaccounts` (`AgentId`, `Username`, `Password`) VALUES ('6', 'DBruce', 'Dixon');
INSERT INTO `agentaccounts` (`AgentId`, `Username`, `Password`) VALUES ('7', 'JBeverly', 'Jones');
INSERT INTO `agentaccounts` (`AgentId`, `Username`, `Password`) VALUES ('8', 'MJane', 'Merrill');
INSERT INTO `agentaccounts` (`AgentId`, `Username`, `Password`) VALUES ('9', 'PBrian', 'Peterson');