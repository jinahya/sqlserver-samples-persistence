-- What the in-memory H2 database of the -test unit needs of WideWorldImporters' schema, beyond what the provider
-- generates from the mappings. Generated from the database's catalog, for the tables the unit maps; not
-- to be edited by hand.

-- ---------------------------------------------------------------------------------------------------- defaults
-- A column the database fills, which the mapping leaves out of an INSERT (a ModifiedDate, a rowguid, ...),
-- is filled here too, by the definition of the table's DEFAULT constraint, as sys.default_constraints
-- holds it.
ALTER TABLE [Application].[SystemParameters] ALTER COLUMN [LastEditedWhen] SET DEFAULT (sysdatetime());
ALTER TABLE [Purchasing].[PurchaseOrderLines] ALTER COLUMN [LastEditedWhen] SET DEFAULT (sysdatetime());
ALTER TABLE [Purchasing].[PurchaseOrders] ALTER COLUMN [LastEditedWhen] SET DEFAULT (sysdatetime());
ALTER TABLE [Purchasing].[SupplierTransactions] ALTER COLUMN [LastEditedWhen] SET DEFAULT (sysdatetime());
ALTER TABLE [Sales].[CustomerTransactions] ALTER COLUMN [LastEditedWhen] SET DEFAULT (sysdatetime());
ALTER TABLE [Sales].[InvoiceLines] ALTER COLUMN [LastEditedWhen] SET DEFAULT (sysdatetime());
ALTER TABLE [Sales].[Invoices] ALTER COLUMN [LastEditedWhen] SET DEFAULT (sysdatetime());
ALTER TABLE [Sales].[OrderLines] ALTER COLUMN [LastEditedWhen] SET DEFAULT (sysdatetime());
ALTER TABLE [Sales].[Orders] ALTER COLUMN [LastEditedWhen] SET DEFAULT (sysdatetime());
ALTER TABLE [Sales].[SpecialDeals] ALTER COLUMN [LastEditedWhen] SET DEFAULT (sysdatetime());
ALTER TABLE [Warehouse].[StockItemHoldings] ALTER COLUMN [LastEditedWhen] SET DEFAULT (sysdatetime());
ALTER TABLE [Warehouse].[StockItemStockGroups] ALTER COLUMN [LastEditedWhen] SET DEFAULT (sysdatetime());
ALTER TABLE [Warehouse].[StockItemTransactions] ALTER COLUMN [LastEditedWhen] SET DEFAULT (sysdatetime());

-- --------------------------------------------------------------------------------------------- period columns
-- A system-versioned table's ValidFrom and ValidTo have no default; SQL Server fills them itself. Here
-- ValidFrom is the time of the INSERT, and ValidTo the end of time, as SQL Server sets them for a current
-- row.
ALTER TABLE [Application].[Cities] ALTER COLUMN [ValidFrom] SET DEFAULT (sysdatetime());
ALTER TABLE [Application].[Cities] ALTER COLUMN [ValidTo] SET DEFAULT ('9999-12-31 23:59:59.9999999');
ALTER TABLE [Application].[Countries] ALTER COLUMN [ValidFrom] SET DEFAULT (sysdatetime());
ALTER TABLE [Application].[Countries] ALTER COLUMN [ValidTo] SET DEFAULT ('9999-12-31 23:59:59.9999999');
ALTER TABLE [Application].[DeliveryMethods] ALTER COLUMN [ValidFrom] SET DEFAULT (sysdatetime());
ALTER TABLE [Application].[DeliveryMethods] ALTER COLUMN [ValidTo] SET DEFAULT ('9999-12-31 23:59:59.9999999');
ALTER TABLE [Application].[PaymentMethods] ALTER COLUMN [ValidFrom] SET DEFAULT (sysdatetime());
ALTER TABLE [Application].[PaymentMethods] ALTER COLUMN [ValidTo] SET DEFAULT ('9999-12-31 23:59:59.9999999');
ALTER TABLE [Application].[People] ALTER COLUMN [ValidFrom] SET DEFAULT (sysdatetime());
ALTER TABLE [Application].[People] ALTER COLUMN [ValidTo] SET DEFAULT ('9999-12-31 23:59:59.9999999');
ALTER TABLE [Application].[StateProvinces] ALTER COLUMN [ValidFrom] SET DEFAULT (sysdatetime());
ALTER TABLE [Application].[StateProvinces] ALTER COLUMN [ValidTo] SET DEFAULT ('9999-12-31 23:59:59.9999999');
ALTER TABLE [Application].[TransactionTypes] ALTER COLUMN [ValidFrom] SET DEFAULT (sysdatetime());
ALTER TABLE [Application].[TransactionTypes] ALTER COLUMN [ValidTo] SET DEFAULT ('9999-12-31 23:59:59.9999999');
ALTER TABLE [Purchasing].[SupplierCategories] ALTER COLUMN [ValidFrom] SET DEFAULT (sysdatetime());
ALTER TABLE [Purchasing].[SupplierCategories] ALTER COLUMN [ValidTo] SET DEFAULT ('9999-12-31 23:59:59.9999999');
ALTER TABLE [Purchasing].[Suppliers] ALTER COLUMN [ValidFrom] SET DEFAULT (sysdatetime());
ALTER TABLE [Purchasing].[Suppliers] ALTER COLUMN [ValidTo] SET DEFAULT ('9999-12-31 23:59:59.9999999');
ALTER TABLE [Sales].[BuyingGroups] ALTER COLUMN [ValidFrom] SET DEFAULT (sysdatetime());
ALTER TABLE [Sales].[BuyingGroups] ALTER COLUMN [ValidTo] SET DEFAULT ('9999-12-31 23:59:59.9999999');
ALTER TABLE [Sales].[CustomerCategories] ALTER COLUMN [ValidFrom] SET DEFAULT (sysdatetime());
ALTER TABLE [Sales].[CustomerCategories] ALTER COLUMN [ValidTo] SET DEFAULT ('9999-12-31 23:59:59.9999999');
ALTER TABLE [Sales].[Customers] ALTER COLUMN [ValidFrom] SET DEFAULT (sysdatetime());
ALTER TABLE [Sales].[Customers] ALTER COLUMN [ValidTo] SET DEFAULT ('9999-12-31 23:59:59.9999999');
ALTER TABLE [Warehouse].[ColdRoomTemperatures] ALTER COLUMN [ValidFrom] SET DEFAULT (sysdatetime());
ALTER TABLE [Warehouse].[ColdRoomTemperatures] ALTER COLUMN [ValidTo] SET DEFAULT ('9999-12-31 23:59:59.9999999');
ALTER TABLE [Warehouse].[Colors] ALTER COLUMN [ValidFrom] SET DEFAULT (sysdatetime());
ALTER TABLE [Warehouse].[Colors] ALTER COLUMN [ValidTo] SET DEFAULT ('9999-12-31 23:59:59.9999999');
ALTER TABLE [Warehouse].[PackageTypes] ALTER COLUMN [ValidFrom] SET DEFAULT (sysdatetime());
ALTER TABLE [Warehouse].[PackageTypes] ALTER COLUMN [ValidTo] SET DEFAULT ('9999-12-31 23:59:59.9999999');
ALTER TABLE [Warehouse].[StockGroups] ALTER COLUMN [ValidFrom] SET DEFAULT (sysdatetime());
ALTER TABLE [Warehouse].[StockGroups] ALTER COLUMN [ValidTo] SET DEFAULT ('9999-12-31 23:59:59.9999999');
ALTER TABLE [Warehouse].[StockItems] ALTER COLUMN [ValidFrom] SET DEFAULT (sysdatetime());
ALTER TABLE [Warehouse].[StockItems] ALTER COLUMN [ValidTo] SET DEFAULT ('9999-12-31 23:59:59.9999999');

-- ------------------------------------------------------------------------------------------- computed columns
-- A computed column, which SQL Server computes and the mapping leaves out of an INSERT, takes NULL here;
-- H2 computes nothing.
ALTER TABLE [Application].[People] ALTER COLUMN [SearchName] SET NULL;
ALTER TABLE [Application].[People] ALTER COLUMN [OtherLanguages] SET NULL;
ALTER TABLE [Purchasing].[SupplierTransactions] ALTER COLUMN [IsFinalized] SET NULL;
ALTER TABLE [Sales].[CustomerTransactions] ALTER COLUMN [IsFinalized] SET NULL;
ALTER TABLE [Sales].[Invoices] ALTER COLUMN [ConfirmedDeliveryTime] SET NULL;
ALTER TABLE [Sales].[Invoices] ALTER COLUMN [ConfirmedReceivedBy] SET NULL;
ALTER TABLE [Warehouse].[StockItems] ALTER COLUMN [Tags] SET NULL;
ALTER TABLE [Warehouse].[StockItems] ALTER COLUMN [SearchDetails] SET NULL;

-- ----------------------------------------------------------------------------------------- unbounded columns
-- A column SQL Server does not bound, (max), xml, geography or hierarchyid, which the mapping gives no
-- length, and the provider, so, 255, is unbounded here too.
ALTER TABLE [Application].[Cities] ALTER COLUMN [Location] SET DATA TYPE BINARY VARYING; -- geography
ALTER TABLE [Application].[Countries] ALTER COLUMN [Border] SET DATA TYPE BINARY VARYING; -- geography
ALTER TABLE [Application].[People] ALTER COLUMN [HashedPassword] SET DATA TYPE BINARY VARYING; -- varbinary(max)
ALTER TABLE [Application].[People] ALTER COLUMN [UserPreferences] SET DATA TYPE CHARACTER VARYING; -- nvarchar(max)
ALTER TABLE [Application].[People] ALTER COLUMN [Photo] SET DATA TYPE BINARY VARYING; -- varbinary(max)
ALTER TABLE [Application].[People] ALTER COLUMN [CustomFields] SET DATA TYPE CHARACTER VARYING; -- nvarchar(max)
ALTER TABLE [Application].[StateProvinces] ALTER COLUMN [Border] SET DATA TYPE BINARY VARYING; -- geography
ALTER TABLE [Application].[SystemParameters] ALTER COLUMN [DeliveryLocation] SET DATA TYPE BINARY VARYING; -- geography
ALTER TABLE [Application].[SystemParameters] ALTER COLUMN [ApplicationSettings] SET DATA TYPE CHARACTER VARYING; -- nvarchar(max)
ALTER TABLE [Purchasing].[PurchaseOrders] ALTER COLUMN [Comments] SET DATA TYPE CHARACTER VARYING; -- nvarchar(max)
ALTER TABLE [Purchasing].[PurchaseOrders] ALTER COLUMN [InternalComments] SET DATA TYPE CHARACTER VARYING; -- nvarchar(max)
ALTER TABLE [Purchasing].[Suppliers] ALTER COLUMN [InternalComments] SET DATA TYPE CHARACTER VARYING; -- nvarchar(max)
ALTER TABLE [Purchasing].[Suppliers] ALTER COLUMN [DeliveryLocation] SET DATA TYPE BINARY VARYING; -- geography
ALTER TABLE [Sales].[Customers] ALTER COLUMN [DeliveryLocation] SET DATA TYPE BINARY VARYING; -- geography
ALTER TABLE [Sales].[Invoices] ALTER COLUMN [CreditNoteReason] SET DATA TYPE CHARACTER VARYING; -- nvarchar(max)
ALTER TABLE [Sales].[Invoices] ALTER COLUMN [Comments] SET DATA TYPE CHARACTER VARYING; -- nvarchar(max)
ALTER TABLE [Sales].[Invoices] ALTER COLUMN [DeliveryInstructions] SET DATA TYPE CHARACTER VARYING; -- nvarchar(max)
ALTER TABLE [Sales].[Invoices] ALTER COLUMN [InternalComments] SET DATA TYPE CHARACTER VARYING; -- nvarchar(max)
ALTER TABLE [Sales].[Invoices] ALTER COLUMN [ReturnedDeliveryData] SET DATA TYPE CHARACTER VARYING; -- nvarchar(max)
ALTER TABLE [Sales].[Orders] ALTER COLUMN [Comments] SET DATA TYPE CHARACTER VARYING; -- nvarchar(max)
ALTER TABLE [Sales].[Orders] ALTER COLUMN [DeliveryInstructions] SET DATA TYPE CHARACTER VARYING; -- nvarchar(max)
ALTER TABLE [Sales].[Orders] ALTER COLUMN [InternalComments] SET DATA TYPE CHARACTER VARYING; -- nvarchar(max)
ALTER TABLE [Warehouse].[StockItems] ALTER COLUMN [MarketingComments] SET DATA TYPE CHARACTER VARYING; -- nvarchar(max)
ALTER TABLE [Warehouse].[StockItems] ALTER COLUMN [InternalComments] SET DATA TYPE CHARACTER VARYING; -- nvarchar(max)
ALTER TABLE [Warehouse].[StockItems] ALTER COLUMN [Photo] SET DATA TYPE BINARY VARYING; -- varbinary(max)
ALTER TABLE [Warehouse].[StockItems] ALTER COLUMN [CustomFields] SET DATA TYPE CHARACTER VARYING; -- nvarchar(max)
ALTER TABLE [Warehouse].[VehicleTemperatures] ALTER COLUMN [CompressedSensorData] SET DATA TYPE BINARY VARYING; -- varbinary(max)
