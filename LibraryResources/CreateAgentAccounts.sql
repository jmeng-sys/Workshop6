
Drop Table If Exists AgentAccounts;
Create Table AgentAccounts (
	[AgentId] [int] IDENTITY (1, 1) NOT NULL,
	[Username] [nvarchar] (20) NOT NULL,
	[Password] [nvarchar] (200) NOT NULL
);