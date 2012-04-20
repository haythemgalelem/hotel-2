USE [hotel]
GO

/****** Object:  Table [dbo].[booking]    Script Date: 04/20/2012 14:24:16 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[booking](
	[by_customer_email] [varchar](50) NOT NULL,
	[in_hotel_hid] [int] NOT NULL,
	[at] [varchar](10) NOT NULL,
	[duration] [int] NOT NULL,
	[roomNr] [int] NOT NULL,
	[numAdults] [int] NOT NULL,
	[numChilds] [int] NOT NULL
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO

