USE [hotel]
GO

/****** Object:  Table [dbo].[customer]    Script Date: 03/27/2012 13:21:49 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[customer](
	[email] [varchar](50) NOT NULL,
	[pwd] [varchar](24) NOT NULL,
	[name] [varchar](50) NOT NULL,
	[adr] [varchar](100) NOT NULL,
	[tel] [numeric](16, 0) NOT NULL
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO

