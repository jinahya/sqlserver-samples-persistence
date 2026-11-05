-- What the in-memory H2 database of the -test unit needs of AdventureWorks' schema, beyond what the provider
-- generates from the mappings. Generated from the database's catalog, for the tables the unit maps; not
-- to be edited by hand.

-- ---------------------------------------------------------------------------------------------------- defaults
-- A column the database fills, which the mapping leaves out of an INSERT (a ModifiedDate, a rowguid, ...),
-- is filled here too, by the definition of the table's DEFAULT constraint, as sys.default_constraints
-- holds it.
ALTER TABLE [dbo].[AWBuildVersion] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [dbo].[ErrorLog] ALTER COLUMN [ErrorTime] SET DEFAULT (getdate());
ALTER TABLE [HumanResources].[Department] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [HumanResources].[Employee] ALTER COLUMN [SalariedFlag] SET DEFAULT ((1));
ALTER TABLE [HumanResources].[Employee] ALTER COLUMN [VacationHours] SET DEFAULT ((0));
ALTER TABLE [HumanResources].[Employee] ALTER COLUMN [SickLeaveHours] SET DEFAULT ((0));
ALTER TABLE [HumanResources].[Employee] ALTER COLUMN [CurrentFlag] SET DEFAULT ((1));
ALTER TABLE [HumanResources].[Employee] ALTER COLUMN [rowguid] SET DEFAULT (newid());
ALTER TABLE [HumanResources].[Employee] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [HumanResources].[EmployeeDepartmentHistory] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [HumanResources].[EmployeePayHistory] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [HumanResources].[JobCandidate] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [HumanResources].[Shift] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [Person].[Address] ALTER COLUMN [rowguid] SET DEFAULT (newid());
ALTER TABLE [Person].[Address] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [Person].[AddressType] ALTER COLUMN [rowguid] SET DEFAULT (newid());
ALTER TABLE [Person].[AddressType] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [Person].[BusinessEntity] ALTER COLUMN [rowguid] SET DEFAULT (newid());
ALTER TABLE [Person].[BusinessEntity] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [Person].[BusinessEntityAddress] ALTER COLUMN [rowguid] SET DEFAULT (newid());
ALTER TABLE [Person].[BusinessEntityAddress] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [Person].[BusinessEntityContact] ALTER COLUMN [rowguid] SET DEFAULT (newid());
ALTER TABLE [Person].[BusinessEntityContact] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [Person].[ContactType] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [Person].[CountryRegion] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [Person].[EmailAddress] ALTER COLUMN [rowguid] SET DEFAULT (newid());
ALTER TABLE [Person].[EmailAddress] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [Person].[Password] ALTER COLUMN [rowguid] SET DEFAULT (newid());
ALTER TABLE [Person].[Password] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [Person].[Person] ALTER COLUMN [NameStyle] SET DEFAULT ((0));
ALTER TABLE [Person].[Person] ALTER COLUMN [EmailPromotion] SET DEFAULT ((0));
ALTER TABLE [Person].[Person] ALTER COLUMN [rowguid] SET DEFAULT (newid());
ALTER TABLE [Person].[Person] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [Person].[PersonPhone] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [Person].[PhoneNumberType] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [Person].[StateProvince] ALTER COLUMN [IsOnlyStateProvinceFlag] SET DEFAULT ((1));
ALTER TABLE [Person].[StateProvince] ALTER COLUMN [rowguid] SET DEFAULT (newid());
ALTER TABLE [Person].[StateProvince] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [Production].[BillOfMaterials] ALTER COLUMN [StartDate] SET DEFAULT (getdate());
ALTER TABLE [Production].[BillOfMaterials] ALTER COLUMN [PerAssemblyQty] SET DEFAULT ((1.00));
ALTER TABLE [Production].[BillOfMaterials] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [Production].[Culture] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [Production].[Document] ALTER COLUMN [FolderFlag] SET DEFAULT ((0));
ALTER TABLE [Production].[Document] ALTER COLUMN [ChangeNumber] SET DEFAULT ((0));
ALTER TABLE [Production].[Document] ALTER COLUMN [rowguid] SET DEFAULT (newid());
ALTER TABLE [Production].[Document] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [Production].[Illustration] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [Production].[Location] ALTER COLUMN [CostRate] SET DEFAULT ((0.00));
ALTER TABLE [Production].[Location] ALTER COLUMN [Availability] SET DEFAULT ((0.00));
ALTER TABLE [Production].[Location] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [Production].[Product] ALTER COLUMN [MakeFlag] SET DEFAULT ((1));
ALTER TABLE [Production].[Product] ALTER COLUMN [FinishedGoodsFlag] SET DEFAULT ((1));
ALTER TABLE [Production].[Product] ALTER COLUMN [rowguid] SET DEFAULT (newid());
ALTER TABLE [Production].[Product] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [Production].[ProductCategory] ALTER COLUMN [rowguid] SET DEFAULT (newid());
ALTER TABLE [Production].[ProductCategory] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [Production].[ProductCostHistory] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [Production].[ProductDescription] ALTER COLUMN [rowguid] SET DEFAULT (newid());
ALTER TABLE [Production].[ProductDescription] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [Production].[ProductInventory] ALTER COLUMN [Quantity] SET DEFAULT ((0));
ALTER TABLE [Production].[ProductInventory] ALTER COLUMN [rowguid] SET DEFAULT (newid());
ALTER TABLE [Production].[ProductInventory] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [Production].[ProductListPriceHistory] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [Production].[ProductModel] ALTER COLUMN [rowguid] SET DEFAULT (newid());
ALTER TABLE [Production].[ProductModel] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [Production].[ProductModelIllustration] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [Production].[ProductModelProductDescriptionCulture] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [Production].[ProductPhoto] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [Production].[ProductProductPhoto] ALTER COLUMN [Primary] SET DEFAULT ((0));
ALTER TABLE [Production].[ProductProductPhoto] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [Production].[ProductReview] ALTER COLUMN [ReviewDate] SET DEFAULT (getdate());
ALTER TABLE [Production].[ProductReview] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [Production].[ProductSubcategory] ALTER COLUMN [rowguid] SET DEFAULT (newid());
ALTER TABLE [Production].[ProductSubcategory] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [Production].[ScrapReason] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [Production].[TransactionHistory] ALTER COLUMN [ReferenceOrderLineID] SET DEFAULT ((0));
ALTER TABLE [Production].[TransactionHistory] ALTER COLUMN [TransactionDate] SET DEFAULT (getdate());
ALTER TABLE [Production].[TransactionHistory] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [Production].[TransactionHistoryArchive] ALTER COLUMN [ReferenceOrderLineID] SET DEFAULT ((0));
ALTER TABLE [Production].[TransactionHistoryArchive] ALTER COLUMN [TransactionDate] SET DEFAULT (getdate());
ALTER TABLE [Production].[TransactionHistoryArchive] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [Production].[UnitMeasure] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [Production].[WorkOrder] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [Production].[WorkOrderRouting] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [Purchasing].[ProductVendor] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [Purchasing].[PurchaseOrderDetail] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [Purchasing].[PurchaseOrderHeader] ALTER COLUMN [RevisionNumber] SET DEFAULT ((0));
ALTER TABLE [Purchasing].[PurchaseOrderHeader] ALTER COLUMN [Status] SET DEFAULT ((1));
ALTER TABLE [Purchasing].[PurchaseOrderHeader] ALTER COLUMN [OrderDate] SET DEFAULT (getdate());
ALTER TABLE [Purchasing].[PurchaseOrderHeader] ALTER COLUMN [SubTotal] SET DEFAULT ((0.00));
ALTER TABLE [Purchasing].[PurchaseOrderHeader] ALTER COLUMN [TaxAmt] SET DEFAULT ((0.00));
ALTER TABLE [Purchasing].[PurchaseOrderHeader] ALTER COLUMN [Freight] SET DEFAULT ((0.00));
ALTER TABLE [Purchasing].[PurchaseOrderHeader] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [Purchasing].[ShipMethod] ALTER COLUMN [ShipBase] SET DEFAULT ((0.00));
ALTER TABLE [Purchasing].[ShipMethod] ALTER COLUMN [ShipRate] SET DEFAULT ((0.00));
ALTER TABLE [Purchasing].[ShipMethod] ALTER COLUMN [rowguid] SET DEFAULT (newid());
ALTER TABLE [Purchasing].[ShipMethod] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [Purchasing].[Vendor] ALTER COLUMN [PreferredVendorStatus] SET DEFAULT ((1));
ALTER TABLE [Purchasing].[Vendor] ALTER COLUMN [ActiveFlag] SET DEFAULT ((1));
ALTER TABLE [Purchasing].[Vendor] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [Sales].[CountryRegionCurrency] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [Sales].[CreditCard] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [Sales].[Currency] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [Sales].[CurrencyRate] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [Sales].[Customer] ALTER COLUMN [rowguid] SET DEFAULT (newid());
ALTER TABLE [Sales].[Customer] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [Sales].[PersonCreditCard] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [Sales].[SalesOrderDetail] ALTER COLUMN [UnitPriceDiscount] SET DEFAULT ((0.0));
ALTER TABLE [Sales].[SalesOrderDetail] ALTER COLUMN [rowguid] SET DEFAULT (newid());
ALTER TABLE [Sales].[SalesOrderDetail] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [Sales].[SalesOrderHeader] ALTER COLUMN [RevisionNumber] SET DEFAULT ((0));
ALTER TABLE [Sales].[SalesOrderHeader] ALTER COLUMN [OrderDate] SET DEFAULT (getdate());
ALTER TABLE [Sales].[SalesOrderHeader] ALTER COLUMN [Status] SET DEFAULT ((1));
ALTER TABLE [Sales].[SalesOrderHeader] ALTER COLUMN [OnlineOrderFlag] SET DEFAULT ((1));
ALTER TABLE [Sales].[SalesOrderHeader] ALTER COLUMN [SubTotal] SET DEFAULT ((0.00));
ALTER TABLE [Sales].[SalesOrderHeader] ALTER COLUMN [TaxAmt] SET DEFAULT ((0.00));
ALTER TABLE [Sales].[SalesOrderHeader] ALTER COLUMN [Freight] SET DEFAULT ((0.00));
ALTER TABLE [Sales].[SalesOrderHeader] ALTER COLUMN [rowguid] SET DEFAULT (newid());
ALTER TABLE [Sales].[SalesOrderHeader] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [Sales].[SalesOrderHeaderSalesReason] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [Sales].[SalesPerson] ALTER COLUMN [Bonus] SET DEFAULT ((0.00));
ALTER TABLE [Sales].[SalesPerson] ALTER COLUMN [CommissionPct] SET DEFAULT ((0.00));
ALTER TABLE [Sales].[SalesPerson] ALTER COLUMN [SalesYTD] SET DEFAULT ((0.00));
ALTER TABLE [Sales].[SalesPerson] ALTER COLUMN [SalesLastYear] SET DEFAULT ((0.00));
ALTER TABLE [Sales].[SalesPerson] ALTER COLUMN [rowguid] SET DEFAULT (newid());
ALTER TABLE [Sales].[SalesPerson] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [Sales].[SalesPersonQuotaHistory] ALTER COLUMN [rowguid] SET DEFAULT (newid());
ALTER TABLE [Sales].[SalesPersonQuotaHistory] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [Sales].[SalesReason] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [Sales].[SalesTaxRate] ALTER COLUMN [TaxRate] SET DEFAULT ((0.00));
ALTER TABLE [Sales].[SalesTaxRate] ALTER COLUMN [rowguid] SET DEFAULT (newid());
ALTER TABLE [Sales].[SalesTaxRate] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [Sales].[SalesTerritory] ALTER COLUMN [SalesYTD] SET DEFAULT ((0.00));
ALTER TABLE [Sales].[SalesTerritory] ALTER COLUMN [SalesLastYear] SET DEFAULT ((0.00));
ALTER TABLE [Sales].[SalesTerritory] ALTER COLUMN [CostYTD] SET DEFAULT ((0.00));
ALTER TABLE [Sales].[SalesTerritory] ALTER COLUMN [CostLastYear] SET DEFAULT ((0.00));
ALTER TABLE [Sales].[SalesTerritory] ALTER COLUMN [rowguid] SET DEFAULT (newid());
ALTER TABLE [Sales].[SalesTerritory] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [Sales].[SalesTerritoryHistory] ALTER COLUMN [rowguid] SET DEFAULT (newid());
ALTER TABLE [Sales].[SalesTerritoryHistory] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [Sales].[ShoppingCartItem] ALTER COLUMN [Quantity] SET DEFAULT ((1));
ALTER TABLE [Sales].[ShoppingCartItem] ALTER COLUMN [DateCreated] SET DEFAULT (getdate());
ALTER TABLE [Sales].[ShoppingCartItem] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [Sales].[SpecialOffer] ALTER COLUMN [DiscountPct] SET DEFAULT ((0.00));
ALTER TABLE [Sales].[SpecialOffer] ALTER COLUMN [MinQty] SET DEFAULT ((0));
ALTER TABLE [Sales].[SpecialOffer] ALTER COLUMN [rowguid] SET DEFAULT (newid());
ALTER TABLE [Sales].[SpecialOffer] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [Sales].[SpecialOfferProduct] ALTER COLUMN [rowguid] SET DEFAULT (newid());
ALTER TABLE [Sales].[SpecialOfferProduct] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [Sales].[Store] ALTER COLUMN [rowguid] SET DEFAULT (newid());
ALTER TABLE [Sales].[Store] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());

-- ------------------------------------------------------------------------------------------- computed columns
-- A computed column, which SQL Server computes and the mapping leaves out of an INSERT, takes NULL here;
-- H2 computes nothing.
ALTER TABLE [HumanResources].[Employee] ALTER COLUMN [OrganizationLevel] SET NULL;
ALTER TABLE [Production].[Document] ALTER COLUMN [DocumentLevel] SET NULL;
ALTER TABLE [Production].[WorkOrder] ALTER COLUMN [StockedQty] SET NULL;
ALTER TABLE [Purchasing].[PurchaseOrderDetail] ALTER COLUMN [LineTotal] SET NULL;
ALTER TABLE [Purchasing].[PurchaseOrderDetail] ALTER COLUMN [StockedQty] SET NULL;
ALTER TABLE [Purchasing].[PurchaseOrderHeader] ALTER COLUMN [TotalDue] SET NULL;
ALTER TABLE [Sales].[Customer] ALTER COLUMN [AccountNumber] SET NULL;
ALTER TABLE [Sales].[SalesOrderDetail] ALTER COLUMN [LineTotal] SET NULL;
ALTER TABLE [Sales].[SalesOrderHeader] ALTER COLUMN [SalesOrderNumber] SET NULL;
ALTER TABLE [Sales].[SalesOrderHeader] ALTER COLUMN [TotalDue] SET NULL;

-- ----------------------------------------------------------------------------------------- unbounded columns
-- A column SQL Server does not bound, (max), xml, geography or hierarchyid, which the mapping gives no
-- length, and the provider, so, 255, is unbounded here too.
ALTER TABLE [dbo].[DatabaseLog] ALTER COLUMN [TSQL] SET DATA TYPE CHARACTER VARYING; -- nvarchar(max)
ALTER TABLE [dbo].[DatabaseLog] ALTER COLUMN [XmlEvent] SET DATA TYPE CHARACTER VARYING; -- xml
ALTER TABLE [HumanResources].[Employee] ALTER COLUMN [OrganizationNode] SET DATA TYPE BINARY VARYING; -- hierarchyid
ALTER TABLE [HumanResources].[JobCandidate] ALTER COLUMN [Resume] SET DATA TYPE CHARACTER VARYING; -- xml
ALTER TABLE [Person].[Address] ALTER COLUMN [SpatialLocation] SET DATA TYPE BINARY VARYING; -- geography
ALTER TABLE [Person].[Person] ALTER COLUMN [AdditionalContactInfo] SET DATA TYPE CHARACTER VARYING; -- xml
ALTER TABLE [Person].[Person] ALTER COLUMN [Demographics] SET DATA TYPE CHARACTER VARYING; -- xml
ALTER TABLE [Production].[Document] ALTER COLUMN [DocumentNode] SET DATA TYPE BINARY VARYING; -- hierarchyid
ALTER TABLE [Production].[Document] ALTER COLUMN [DocumentSummary] SET DATA TYPE CHARACTER VARYING; -- nvarchar(max)
ALTER TABLE [Production].[Document] ALTER COLUMN [Document] SET DATA TYPE BINARY VARYING; -- varbinary(max)
ALTER TABLE [Production].[Illustration] ALTER COLUMN [Diagram] SET DATA TYPE CHARACTER VARYING; -- xml
ALTER TABLE [Production].[ProductModel] ALTER COLUMN [CatalogDescription] SET DATA TYPE CHARACTER VARYING; -- xml
ALTER TABLE [Production].[ProductModel] ALTER COLUMN [Instructions] SET DATA TYPE CHARACTER VARYING; -- xml
ALTER TABLE [Production].[ProductPhoto] ALTER COLUMN [ThumbNailPhoto] SET DATA TYPE BINARY VARYING; -- varbinary(max)
ALTER TABLE [Production].[ProductPhoto] ALTER COLUMN [LargePhoto] SET DATA TYPE BINARY VARYING; -- varbinary(max)
ALTER TABLE [Sales].[Store] ALTER COLUMN [Demographics] SET DATA TYPE CHARACTER VARYING; -- xml

-- ------------------------------------------------------------------------------------------ check constraints
-- A check constraint which matches with a character class, LIKE '[A-Za-z]', which H2's LIKE does not have,
-- is dropped; SQL Server, in an IT, holds a column to it.
-- CK_ProductInventory_Shelf: ([Shelf] like '[A-Za-z]' OR [Shelf]='N/A')
ALTER TABLE [Production].[ProductInventory] DROP CONSTRAINT [CK_ProductInventory_Shelf];
