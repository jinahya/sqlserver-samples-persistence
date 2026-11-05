-- What the in-memory H2 database of the -test unit needs of FutonManufacturing's schema, beyond what the provider
-- generates from the mappings. Generated from the database's catalog, for the tables the unit maps; not
-- to be edited by hand.

-- ---------------------------------------------------------------------------------------------------- defaults
-- A column the database fills, which the mapping leaves out of an INSERT (a ModifiedDate, a rowguid, ...),
-- is filled here too, by the definition of the table's DEFAULT constraint, as sys.default_constraints
-- holds it.
-- Left out, since H2 does not take a SQL Server data type in a CONVERT:
-- dbo.BillOfMaterials.EffectiveDate (CONVERT([date],getdate()))
-- dbo.PurchaseOrder.OrderDate (CONVERT([date],getdate()))
-- dbo.SalesOrder.OrderDate (CONVERT([date],getdate()))
-- dbo.SalesQuote.QuoteDate (CONVERT([date],getdate()))
-- dbo.SalesReturn.ReturnDate (CONVERT([date],getdate()))
-- dbo.SupplierItem.EffectiveDate (CONVERT([date],getdate()))
ALTER TABLE [dbo].[BillOfMaterials] ALTER COLUMN [ScrapRate] SET DEFAULT ((0));
ALTER TABLE [dbo].[BillOfMaterials] ALTER COLUMN [BOMLevel] SET DEFAULT ((0));
ALTER TABLE [dbo].[BillOfMaterials] ALTER COLUMN [IsActive] SET DEFAULT ((1));
ALTER TABLE [dbo].[BillOfMaterials] ALTER COLUMN [CreatedDate] SET DEFAULT (getdate());
ALTER TABLE [dbo].[BillOfMaterials] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [dbo].[Customer] ALTER COLUMN [IsActive] SET DEFAULT ((1));
ALTER TABLE [dbo].[Customer] ALTER COLUMN [CreatedDate] SET DEFAULT (getdate());
ALTER TABLE [dbo].[Customer] ALTER COLUMN [CustomerType] SET DEFAULT ('Retail');
ALTER TABLE [dbo].[Inventory] ALTER COLUMN [QuantityOnHand] SET DEFAULT ((0));
ALTER TABLE [dbo].[Inventory] ALTER COLUMN [QuantityAllocated] SET DEFAULT ((0));
ALTER TABLE [dbo].[Inventory] ALTER COLUMN [LastUpdated] SET DEFAULT (getdate());
ALTER TABLE [dbo].[InventoryTransaction] ALTER COLUMN [TransactionDate] SET DEFAULT (getdate());
ALTER TABLE [dbo].[Items] ALTER COLUMN [StandardCost] SET DEFAULT ((0));
ALTER TABLE [dbo].[Items] ALTER COLUMN [ListPrice] SET DEFAULT ((0));
ALTER TABLE [dbo].[Items] ALTER COLUMN [IsActive] SET DEFAULT ((1));
ALTER TABLE [dbo].[Items] ALTER COLUMN [LeadTimeDays] SET DEFAULT ((0));
ALTER TABLE [dbo].[Items] ALTER COLUMN [ReorderPoint] SET DEFAULT ((0));
ALTER TABLE [dbo].[Items] ALTER COLUMN [SafetyStock] SET DEFAULT ((0));
ALTER TABLE [dbo].[Items] ALTER COLUMN [CreatedDate] SET DEFAULT (getdate());
ALTER TABLE [dbo].[Items] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [dbo].[PriceList] ALTER COLUMN [IsActive] SET DEFAULT ((1));
ALTER TABLE [dbo].[PriceListDetail] ALTER COLUMN [MinimumQuantity] SET DEFAULT ((1));
ALTER TABLE [dbo].[ProductionCompletion] ALTER COLUMN [QuantityScrapped] SET DEFAULT ((0));
ALTER TABLE [dbo].[ProductionCompletion] ALTER COLUMN [CompletionDate] SET DEFAULT (getdate());
ALTER TABLE [dbo].[ProductionOrder] ALTER COLUMN [QuantityCompleted] SET DEFAULT ((0));
ALTER TABLE [dbo].[ProductionOrder] ALTER COLUMN [QuantityScrapped] SET DEFAULT ((0));
ALTER TABLE [dbo].[ProductionOrder] ALTER COLUMN [Status] SET DEFAULT ('Planned');
ALTER TABLE [dbo].[ProductionOrder] ALTER COLUMN [Priority] SET DEFAULT ((5));
ALTER TABLE [dbo].[ProductionOrder] ALTER COLUMN [CreatedDate] SET DEFAULT (getdate());
ALTER TABLE [dbo].[ProductionOrder] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [dbo].[ProductionOrderMaterial] ALTER COLUMN [IssuedQuantity] SET DEFAULT ((0));
ALTER TABLE [dbo].[Promotion] ALTER COLUMN [IsActive] SET DEFAULT ((1));
ALTER TABLE [dbo].[Promotion] ALTER COLUMN [MinimumPurchase] SET DEFAULT ((0));
ALTER TABLE [dbo].[PurchaseOrder] ALTER COLUMN [Status] SET DEFAULT ('Draft');
ALTER TABLE [dbo].[PurchaseOrder] ALTER COLUMN [Subtotal] SET DEFAULT ((0));
ALTER TABLE [dbo].[PurchaseOrder] ALTER COLUMN [TaxAmount] SET DEFAULT ((0));
ALTER TABLE [dbo].[PurchaseOrder] ALTER COLUMN [ShippingAmount] SET DEFAULT ((0));
ALTER TABLE [dbo].[PurchaseOrder] ALTER COLUMN [TotalAmount] SET DEFAULT ((0));
ALTER TABLE [dbo].[PurchaseOrder] ALTER COLUMN [CreatedDate] SET DEFAULT (getdate());
ALTER TABLE [dbo].[PurchaseOrder] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [dbo].[PurchaseOrderDetail] ALTER COLUMN [QuantityReceived] SET DEFAULT ((0));
ALTER TABLE [dbo].[QualityInspection] ALTER COLUMN [InspectionDate] SET DEFAULT (getdate());
ALTER TABLE [dbo].[ReturnReason] ALTER COLUMN [IsActive] SET DEFAULT ((1));
ALTER TABLE [dbo].[SalesChannel] ALTER COLUMN [IsActive] SET DEFAULT ((1));
ALTER TABLE [dbo].[SalesOrder] ALTER COLUMN [Status] SET DEFAULT ('Draft');
ALTER TABLE [dbo].[SalesOrder] ALTER COLUMN [Subtotal] SET DEFAULT ((0));
ALTER TABLE [dbo].[SalesOrder] ALTER COLUMN [TaxAmount] SET DEFAULT ((0));
ALTER TABLE [dbo].[SalesOrder] ALTER COLUMN [ShippingAmount] SET DEFAULT ((0));
ALTER TABLE [dbo].[SalesOrder] ALTER COLUMN [TotalAmount] SET DEFAULT ((0));
ALTER TABLE [dbo].[SalesOrder] ALTER COLUMN [CreatedDate] SET DEFAULT (getdate());
ALTER TABLE [dbo].[SalesOrder] ALTER COLUMN [ModifiedDate] SET DEFAULT (getdate());
ALTER TABLE [dbo].[SalesOrder] ALTER COLUMN [DiscountAmount] SET DEFAULT ((0));
ALTER TABLE [dbo].[SalesOrderDetail] ALTER COLUMN [QuantityShipped] SET DEFAULT ((0));
ALTER TABLE [dbo].[SalesOrderDetail] ALTER COLUMN [DiscountPercent] SET DEFAULT ((0));
ALTER TABLE [dbo].[SalesQuote] ALTER COLUMN [Status] SET DEFAULT ('Draft');
ALTER TABLE [dbo].[SalesQuote] ALTER COLUMN [Subtotal] SET DEFAULT ((0));
ALTER TABLE [dbo].[SalesQuote] ALTER COLUMN [DiscountAmount] SET DEFAULT ((0));
ALTER TABLE [dbo].[SalesQuote] ALTER COLUMN [TaxAmount] SET DEFAULT ((0));
ALTER TABLE [dbo].[SalesQuote] ALTER COLUMN [TotalAmount] SET DEFAULT ((0));
ALTER TABLE [dbo].[SalesQuote] ALTER COLUMN [CreatedDate] SET DEFAULT (getdate());
ALTER TABLE [dbo].[SalesQuoteDetail] ALTER COLUMN [DiscountPercent] SET DEFAULT ((0));
ALTER TABLE [dbo].[SalesRep] ALTER COLUMN [IsActive] SET DEFAULT ((1));
ALTER TABLE [dbo].[SalesReturn] ALTER COLUMN [Status] SET DEFAULT ('Pending');
ALTER TABLE [dbo].[SalesReturn] ALTER COLUMN [RefundAmount] SET DEFAULT ((0));
ALTER TABLE [dbo].[SalesReturn] ALTER COLUMN [RestockingFee] SET DEFAULT ((0));
ALTER TABLE [dbo].[SalesReturn] ALTER COLUMN [CreatedDate] SET DEFAULT (getdate());
ALTER TABLE [dbo].[SalesTerritory] ALTER COLUMN [IsActive] SET DEFAULT ((1));
ALTER TABLE [dbo].[Store] ALTER COLUMN [IsActive] SET DEFAULT ((1));
ALTER TABLE [dbo].[Supplier] ALTER COLUMN [IsActive] SET DEFAULT ((1));
ALTER TABLE [dbo].[Supplier] ALTER COLUMN [CreatedDate] SET DEFAULT (getdate());
ALTER TABLE [dbo].[SupplierItem] ALTER COLUMN [MinimumOrderQuantity] SET DEFAULT ((1));
ALTER TABLE [dbo].[SupplierItem] ALTER COLUMN [LeadTimeDays] SET DEFAULT ((0));
ALTER TABLE [dbo].[SupplierItem] ALTER COLUMN [IsPreferred] SET DEFAULT ((0));
ALTER TABLE [dbo].[Warehouse] ALTER COLUMN [IsActive] SET DEFAULT ((1));
ALTER TABLE [dbo].[WorkCenter] ALTER COLUMN [IsActive] SET DEFAULT ((1));

-- ------------------------------------------------------------------------------------------- computed columns
-- A computed column, which SQL Server computes and the mapping leaves out of an INSERT, takes NULL here;
-- H2 computes nothing.
ALTER TABLE [dbo].[Inventory] ALTER COLUMN [QuantityAvailable] SET NULL;
ALTER TABLE [dbo].[PurchaseOrderDetail] ALTER COLUMN [LineTotal] SET NULL;
ALTER TABLE [dbo].[SalesOrder] ALTER COLUMN [NetAmount] SET NULL;
ALTER TABLE [dbo].[SalesOrderDetail] ALTER COLUMN [LineTotal] SET NULL;
ALTER TABLE [dbo].[SalesOrderDetail] ALTER COLUMN [DiscountAmount] SET NULL;
ALTER TABLE [dbo].[SalesOrderDetail] ALTER COLUMN [NetAmount] SET NULL;
ALTER TABLE [dbo].[SalesQuoteDetail] ALTER COLUMN [LineTotal] SET NULL;

-- ----------------------------------------------------------------------------------------- unbounded columns
-- A column SQL Server does not bound, (max), xml, geography or hierarchyid, which the mapping gives no
-- length, and the provider, so, 255, is unbounded here too.
ALTER TABLE [dbo].[BillOfMaterials] ALTER COLUMN [Notes] SET DATA TYPE CHARACTER VARYING; -- nvarchar(max)
ALTER TABLE [dbo].[InventoryTransaction] ALTER COLUMN [Notes] SET DATA TYPE CHARACTER VARYING; -- nvarchar(max)
ALTER TABLE [dbo].[Items] ALTER COLUMN [Description] SET DATA TYPE CHARACTER VARYING; -- nvarchar(max)
ALTER TABLE [dbo].[ProductionCompletion] ALTER COLUMN [Notes] SET DATA TYPE CHARACTER VARYING; -- nvarchar(max)
ALTER TABLE [dbo].[ProductionOrder] ALTER COLUMN [Notes] SET DATA TYPE CHARACTER VARYING; -- nvarchar(max)
ALTER TABLE [dbo].[Promotion] ALTER COLUMN [Description] SET DATA TYPE CHARACTER VARYING; -- nvarchar(max)
ALTER TABLE [dbo].[PurchaseOrder] ALTER COLUMN [Notes] SET DATA TYPE CHARACTER VARYING; -- nvarchar(max)
ALTER TABLE [dbo].[QualityInspection] ALTER COLUMN [Notes] SET DATA TYPE CHARACTER VARYING; -- nvarchar(max)
ALTER TABLE [dbo].[SalesOrder] ALTER COLUMN [Notes] SET DATA TYPE CHARACTER VARYING; -- nvarchar(max)
ALTER TABLE [dbo].[SalesQuote] ALTER COLUMN [Notes] SET DATA TYPE CHARACTER VARYING; -- nvarchar(max)
ALTER TABLE [dbo].[SalesReturn] ALTER COLUMN [Notes] SET DATA TYPE CHARACTER VARYING; -- nvarchar(max)
