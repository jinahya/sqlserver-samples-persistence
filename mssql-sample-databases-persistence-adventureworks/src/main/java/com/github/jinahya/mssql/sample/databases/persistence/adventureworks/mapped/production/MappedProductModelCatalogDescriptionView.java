package com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped.production;

/*-
 * #%L
 * mssql-sample-databases-persistence-adventureworks
 * %%
 * Copyright (C) 2026 Jinahya, Inc.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped.UuidStringAttributeConverter;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * A mapped superclass which holds the mappings of the {@code Production.vProductModelCatalogDescription} view.
 * <p>
 * Identifier: the view declares no key; ProductModelID is unique, and not null, in every row it returns.
 * <p>
 * Read-only: SQL Server takes no whole row of this view in an INSERT or an UPDATE, so no column of it is updatable,
 * and none but the identifier, of which EclipseLink requires a writable mapping, is insertable; some of its columns
 * are derived or constant (error 4406); one of its columns is an identity column of its base table, which no UPDATE
 * sets (error 8102).
 */
@MappedSuperclass
public abstract class MappedProductModelCatalogDescriptionView {

    /**
     * The name of the schema of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "vProductModelCatalogDescription";

    // -------------------------------------------------------------------------------------------------- ProductModelID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRODUCT_MODEL_ID = "ProductModelID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRODUCT_MODEL_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_MODEL_ID = "productModelId";

    // ------------------------------------------------------------------------------------------------------------ Name

    /**
     * The name of the {@code Name (nvarchar)(50), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_NAME = "Name";

    /**
     * The length of the {@value #COLUMN_NAME_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_NAME = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_NAME = "name";

    // --------------------------------------------------------------------------------------------------------- Summary

    /**
     * The name of the {@code nvarchar(max)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SUMMARY = "Summary";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SUMMARY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SUMMARY = "summary";

    // ---------------------------------------------------------------------------------------------------- Manufacturer

    /**
     * The name of the {@code nvarchar(max)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_MANUFACTURER = "Manufacturer";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MANUFACTURER} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_MANUFACTURER = "manufacturer";

    // ------------------------------------------------------------------------------------------------------- Copyright

    /**
     * The name of the {@code nvarchar(30)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_COPYRIGHT = "Copyright";

    /**
     * The length of the {@value #COLUMN_NAME_COPYRIGHT} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_COPYRIGHT = 30;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_COPYRIGHT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_COPYRIGHT = "copyright";

    // ------------------------------------------------------------------------------------------------------ ProductURL

    /**
     * The name of the {@code nvarchar(256)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRODUCT_URL = "ProductURL";

    /**
     * The length of the {@value #COLUMN_NAME_PRODUCT_URL} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PRODUCT_URL = 256;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRODUCT_URL} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_URL = "productUrl";

    // -------------------------------------------------------------------------------------------------- WarrantyPeriod

    /**
     * The name of the {@code nvarchar(256)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_WARRANTY_PERIOD = "WarrantyPeriod";

    /**
     * The length of the {@value #COLUMN_NAME_WARRANTY_PERIOD} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_WARRANTY_PERIOD = 256;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_WARRANTY_PERIOD} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_WARRANTY_PERIOD = "warrantyPeriod";

    // --------------------------------------------------------------------------------------------- WarrantyDescription

    /**
     * The name of the {@code nvarchar(256)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_WARRANTY_DESCRIPTION = "WarrantyDescription";

    /**
     * The length of the {@value #COLUMN_NAME_WARRANTY_DESCRIPTION} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_WARRANTY_DESCRIPTION = 256;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_WARRANTY_DESCRIPTION} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_WARRANTY_DESCRIPTION = "warrantyDescription";

    // ------------------------------------------------------------------------------------------------------- NoOfYears

    /**
     * The name of the {@code nvarchar(256)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_NO_OF_YEARS = "NoOfYears";

    /**
     * The length of the {@value #COLUMN_NAME_NO_OF_YEARS} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_NO_OF_YEARS = 256;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_NO_OF_YEARS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_NO_OF_YEARS = "noOfYears";

    // ------------------------------------------------------------------------------------------ MaintenanceDescription

    /**
     * The name of the {@code nvarchar(256)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_MAINTENANCE_DESCRIPTION = "MaintenanceDescription";

    /**
     * The length of the {@value #COLUMN_NAME_MAINTENANCE_DESCRIPTION} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_MAINTENANCE_DESCRIPTION = 256;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MAINTENANCE_DESCRIPTION} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_MAINTENANCE_DESCRIPTION = "maintenanceDescription";

    // ----------------------------------------------------------------------------------------------------------- Wheel

    /**
     * The name of the {@code nvarchar(256)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_WHEEL = "Wheel";

    /**
     * The length of the {@value #COLUMN_NAME_WHEEL} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_WHEEL = 256;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_WHEEL} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_WHEEL = "wheel";

    // ---------------------------------------------------------------------------------------------------------- Saddle

    /**
     * The name of the {@code nvarchar(256)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SADDLE = "Saddle";

    /**
     * The length of the {@value #COLUMN_NAME_SADDLE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_SADDLE = 256;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SADDLE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SADDLE = "saddle";

    // ----------------------------------------------------------------------------------------------------------- Pedal

    /**
     * The name of the {@code nvarchar(256)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PEDAL = "Pedal";

    /**
     * The length of the {@value #COLUMN_NAME_PEDAL} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PEDAL = 256;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PEDAL} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PEDAL = "pedal";

    // ------------------------------------------------------------------------------------------------------- BikeFrame

    /**
     * The name of the {@code nvarchar(max)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_BIKE_FRAME = "BikeFrame";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_BIKE_FRAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_BIKE_FRAME = "bikeFrame";

    // -------------------------------------------------------------------------------------------------------- Crankset

    /**
     * The name of the {@code nvarchar(256)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CRANKSET = "Crankset";

    /**
     * The length of the {@value #COLUMN_NAME_CRANKSET} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CRANKSET = 256;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CRANKSET} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CRANKSET = "crankset";

    // ---------------------------------------------------------------------------------------------------- PictureAngle

    /**
     * The name of the {@code nvarchar(256)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PICTURE_ANGLE = "PictureAngle";

    /**
     * The length of the {@value #COLUMN_NAME_PICTURE_ANGLE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PICTURE_ANGLE = 256;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PICTURE_ANGLE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PICTURE_ANGLE = "pictureAngle";

    // ----------------------------------------------------------------------------------------------------- PictureSize

    /**
     * The name of the {@code nvarchar(256)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PICTURE_SIZE = "PictureSize";

    /**
     * The length of the {@value #COLUMN_NAME_PICTURE_SIZE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PICTURE_SIZE = 256;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PICTURE_SIZE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PICTURE_SIZE = "pictureSize";

    // -------------------------------------------------------------------------------------------------- ProductPhotoID

    /**
     * The name of the {@code nvarchar(256)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRODUCT_PHOTO_ID = "ProductPhotoID";

    /**
     * The length of the {@value #COLUMN_NAME_PRODUCT_PHOTO_ID} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PRODUCT_PHOTO_ID = 256;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRODUCT_PHOTO_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_PHOTO_ID = "productPhotoId";

    // -------------------------------------------------------------------------------------------------------- Material

    /**
     * The name of the {@code nvarchar(256)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_MATERIAL = "Material";

    /**
     * The length of the {@value #COLUMN_NAME_MATERIAL} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_MATERIAL = 256;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MATERIAL} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_MATERIAL = "material";

    // ----------------------------------------------------------------------------------------------------------- Color

    /**
     * The name of the {@code nvarchar(256)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_COLOR = "Color";

    /**
     * The length of the {@value #COLUMN_NAME_COLOR} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_COLOR = 256;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_COLOR} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_COLOR = "color";

    // ----------------------------------------------------------------------------------------------------- ProductLine

    /**
     * The name of the {@code nvarchar(256)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRODUCT_LINE = "ProductLine";

    /**
     * The length of the {@value #COLUMN_NAME_PRODUCT_LINE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PRODUCT_LINE = 256;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRODUCT_LINE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_LINE = "productLine";

    // ----------------------------------------------------------------------------------------------------------- Style

    /**
     * The name of the {@code nvarchar(256)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_STYLE = "Style";

    /**
     * The length of the {@value #COLUMN_NAME_STYLE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_STYLE = 256;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STYLE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STYLE = "style";

    // ------------------------------------------------------------------------------------------------- RiderExperience

    /**
     * The name of the {@code nvarchar(1024)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_RIDER_EXPERIENCE = "RiderExperience";

    /**
     * The length of the {@value #COLUMN_NAME_RIDER_EXPERIENCE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_RIDER_EXPERIENCE = 1024;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_RIDER_EXPERIENCE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_RIDER_EXPERIENCE = "riderExperience";

    // --------------------------------------------------------------------------------------------------------- rowguid

    /**
     * The name of the {@code uniqueidentifier, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ROWGUID = "rowguid";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ROWGUID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ROWGUID = "rowguid";

    // ---------------------------------------------------------------------------------------------------- ModifiedDate

    /**
     * The name of the {@code datetime, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_MODIFIED_DATE = "ModifiedDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MODIFIED_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_MODIFIED_DATE = "modifiedDate";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedProductModelCatalogDescriptionView() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "productModelId=" + productModelId
               + ",name=" + name
               + ",summary=" + summary
               + ",manufacturer=" + manufacturer
               + ",copyright=" + copyright
               + ",productUrl=" + productUrl
               + ",warrantyPeriod=" + warrantyPeriod
               + ",warrantyDescription=" + warrantyDescription
               + ",noOfYears=" + noOfYears
               + ",maintenanceDescription=" + maintenanceDescription
               + ",wheel=" + wheel
               + ",saddle=" + saddle
               + ",pedal=" + pedal
               + ",bikeFrame=" + bikeFrame
               + ",crankset=" + crankset
               + ",pictureAngle=" + pictureAngle
               + ",pictureSize=" + pictureSize
               + ",productPhotoId=" + productPhotoId
               + ",material=" + material
               + ",color=" + color
               + ",productLine=" + productLine
               + ",style=" + style
               + ",riderExperience=" + riderExperience
               + ",rowguid=" + rowguid
               + ",modifiedDate=" + modifiedDate
               + "}";
    }

    /**
     * {@inheritDoc}
     *
     * @param obj {@inheritDoc}
     * @return {@inheritDoc}
     * @implSpec Equality is by the identifier alone.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MappedProductModelCatalogDescriptionView that)) {
            return false;
        }
        return Objects.equals(productModelId, that.productModelId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(productModelId);
    }

    // -------------------------------------------------------------------------------------------------- productModelId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRODUCT_MODEL_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRODUCT_MODEL_ID} attribute.
     */
    public Integer getProductModelId() {
        return productModelId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRODUCT_MODEL_ID} attribute with the specified value.
     *
     * @param productModelId new value for {@value #ATTRIBUTE_NAME_PRODUCT_MODEL_ID} attribute.
     */
    protected void setProductModelId(final Integer productModelId) {
        this.productModelId = productModelId;
    }

    // ------------------------------------------------------------------------------------------------------------ name

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_NAME} attribute.
     */
    public String getName() {
        return name;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_NAME} attribute with the specified value.
     *
     * @param name new value for {@value #ATTRIBUTE_NAME_NAME} attribute.
     */
    protected void setName(final String name) {
        this.name = name;
    }

    // --------------------------------------------------------------------------------------------------------- summary

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SUMMARY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SUMMARY} attribute.
     */
    public String getSummary() {
        return summary;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SUMMARY} attribute with the specified value.
     *
     * @param summary new value for {@value #ATTRIBUTE_NAME_SUMMARY} attribute.
     */
    protected void setSummary(final String summary) {
        this.summary = summary;
    }

    // ---------------------------------------------------------------------------------------------------- manufacturer

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MANUFACTURER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MANUFACTURER} attribute.
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MANUFACTURER} attribute with the specified value.
     *
     * @param manufacturer new value for {@value #ATTRIBUTE_NAME_MANUFACTURER} attribute.
     */
    protected void setManufacturer(final String manufacturer) {
        this.manufacturer = manufacturer;
    }

    // ------------------------------------------------------------------------------------------------------- copyright

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_COPYRIGHT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_COPYRIGHT} attribute.
     */
    public String getCopyright() {
        return copyright;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_COPYRIGHT} attribute with the specified value.
     *
     * @param copyright new value for {@value #ATTRIBUTE_NAME_COPYRIGHT} attribute.
     */
    protected void setCopyright(final String copyright) {
        this.copyright = copyright;
    }

    // ------------------------------------------------------------------------------------------------------ productUrl

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRODUCT_URL} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRODUCT_URL} attribute.
     */
    public String getProductUrl() {
        return productUrl;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRODUCT_URL} attribute with the specified value.
     *
     * @param productUrl new value for {@value #ATTRIBUTE_NAME_PRODUCT_URL} attribute.
     */
    protected void setProductUrl(final String productUrl) {
        this.productUrl = productUrl;
    }

    // -------------------------------------------------------------------------------------------------- warrantyPeriod

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_WARRANTY_PERIOD} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_WARRANTY_PERIOD} attribute.
     */
    public String getWarrantyPeriod() {
        return warrantyPeriod;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_WARRANTY_PERIOD} attribute with the specified value.
     *
     * @param warrantyPeriod new value for {@value #ATTRIBUTE_NAME_WARRANTY_PERIOD} attribute.
     */
    protected void setWarrantyPeriod(final String warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    // --------------------------------------------------------------------------------------------- warrantyDescription

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_WARRANTY_DESCRIPTION} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_WARRANTY_DESCRIPTION} attribute.
     */
    public String getWarrantyDescription() {
        return warrantyDescription;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_WARRANTY_DESCRIPTION} attribute with the specified value.
     *
     * @param warrantyDescription new value for {@value #ATTRIBUTE_NAME_WARRANTY_DESCRIPTION} attribute.
     */
    protected void setWarrantyDescription(final String warrantyDescription) {
        this.warrantyDescription = warrantyDescription;
    }

    // ------------------------------------------------------------------------------------------------------- noOfYears

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_NO_OF_YEARS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_NO_OF_YEARS} attribute.
     */
    public String getNoOfYears() {
        return noOfYears;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_NO_OF_YEARS} attribute with the specified value.
     *
     * @param noOfYears new value for {@value #ATTRIBUTE_NAME_NO_OF_YEARS} attribute.
     */
    protected void setNoOfYears(final String noOfYears) {
        this.noOfYears = noOfYears;
    }

    // ------------------------------------------------------------------------------------------ maintenanceDescription

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MAINTENANCE_DESCRIPTION} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MAINTENANCE_DESCRIPTION} attribute.
     */
    public String getMaintenanceDescription() {
        return maintenanceDescription;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MAINTENANCE_DESCRIPTION} attribute with the specified value.
     *
     * @param maintenanceDescription new value for {@value #ATTRIBUTE_NAME_MAINTENANCE_DESCRIPTION} attribute.
     */
    protected void setMaintenanceDescription(final String maintenanceDescription) {
        this.maintenanceDescription = maintenanceDescription;
    }

    // ----------------------------------------------------------------------------------------------------------- wheel

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_WHEEL} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_WHEEL} attribute.
     */
    public String getWheel() {
        return wheel;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_WHEEL} attribute with the specified value.
     *
     * @param wheel new value for {@value #ATTRIBUTE_NAME_WHEEL} attribute.
     */
    protected void setWheel(final String wheel) {
        this.wheel = wheel;
    }

    // ---------------------------------------------------------------------------------------------------------- saddle

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SADDLE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SADDLE} attribute.
     */
    public String getSaddle() {
        return saddle;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SADDLE} attribute with the specified value.
     *
     * @param saddle new value for {@value #ATTRIBUTE_NAME_SADDLE} attribute.
     */
    protected void setSaddle(final String saddle) {
        this.saddle = saddle;
    }

    // ----------------------------------------------------------------------------------------------------------- pedal

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PEDAL} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PEDAL} attribute.
     */
    public String getPedal() {
        return pedal;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PEDAL} attribute with the specified value.
     *
     * @param pedal new value for {@value #ATTRIBUTE_NAME_PEDAL} attribute.
     */
    protected void setPedal(final String pedal) {
        this.pedal = pedal;
    }

    // ------------------------------------------------------------------------------------------------------- bikeFrame

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_BIKE_FRAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_BIKE_FRAME} attribute.
     */
    public String getBikeFrame() {
        return bikeFrame;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_BIKE_FRAME} attribute with the specified value.
     *
     * @param bikeFrame new value for {@value #ATTRIBUTE_NAME_BIKE_FRAME} attribute.
     */
    protected void setBikeFrame(final String bikeFrame) {
        this.bikeFrame = bikeFrame;
    }

    // -------------------------------------------------------------------------------------------------------- crankset

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CRANKSET} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CRANKSET} attribute.
     */
    public String getCrankset() {
        return crankset;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CRANKSET} attribute with the specified value.
     *
     * @param crankset new value for {@value #ATTRIBUTE_NAME_CRANKSET} attribute.
     */
    protected void setCrankset(final String crankset) {
        this.crankset = crankset;
    }

    // ---------------------------------------------------------------------------------------------------- pictureAngle

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PICTURE_ANGLE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PICTURE_ANGLE} attribute.
     */
    public String getPictureAngle() {
        return pictureAngle;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PICTURE_ANGLE} attribute with the specified value.
     *
     * @param pictureAngle new value for {@value #ATTRIBUTE_NAME_PICTURE_ANGLE} attribute.
     */
    protected void setPictureAngle(final String pictureAngle) {
        this.pictureAngle = pictureAngle;
    }

    // ----------------------------------------------------------------------------------------------------- pictureSize

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PICTURE_SIZE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PICTURE_SIZE} attribute.
     */
    public String getPictureSize() {
        return pictureSize;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PICTURE_SIZE} attribute with the specified value.
     *
     * @param pictureSize new value for {@value #ATTRIBUTE_NAME_PICTURE_SIZE} attribute.
     */
    protected void setPictureSize(final String pictureSize) {
        this.pictureSize = pictureSize;
    }

    // -------------------------------------------------------------------------------------------------- productPhotoId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRODUCT_PHOTO_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRODUCT_PHOTO_ID} attribute.
     */
    public String getProductPhotoId() {
        return productPhotoId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRODUCT_PHOTO_ID} attribute with the specified value.
     *
     * @param productPhotoId new value for {@value #ATTRIBUTE_NAME_PRODUCT_PHOTO_ID} attribute.
     */
    protected void setProductPhotoId(final String productPhotoId) {
        this.productPhotoId = productPhotoId;
    }

    // -------------------------------------------------------------------------------------------------------- material

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MATERIAL} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MATERIAL} attribute.
     */
    public String getMaterial() {
        return material;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MATERIAL} attribute with the specified value.
     *
     * @param material new value for {@value #ATTRIBUTE_NAME_MATERIAL} attribute.
     */
    protected void setMaterial(final String material) {
        this.material = material;
    }

    // ----------------------------------------------------------------------------------------------------------- color

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_COLOR} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_COLOR} attribute.
     */
    public String getColor() {
        return color;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_COLOR} attribute with the specified value.
     *
     * @param color new value for {@value #ATTRIBUTE_NAME_COLOR} attribute.
     */
    protected void setColor(final String color) {
        this.color = color;
    }

    // ----------------------------------------------------------------------------------------------------- productLine

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRODUCT_LINE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRODUCT_LINE} attribute.
     */
    public String getProductLine() {
        return productLine;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRODUCT_LINE} attribute with the specified value.
     *
     * @param productLine new value for {@value #ATTRIBUTE_NAME_PRODUCT_LINE} attribute.
     */
    protected void setProductLine(final String productLine) {
        this.productLine = productLine;
    }

    // ----------------------------------------------------------------------------------------------------------- style

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STYLE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STYLE} attribute.
     */
    public String getStyle() {
        return style;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STYLE} attribute with the specified value.
     *
     * @param style new value for {@value #ATTRIBUTE_NAME_STYLE} attribute.
     */
    protected void setStyle(final String style) {
        this.style = style;
    }

    // ------------------------------------------------------------------------------------------------- riderExperience

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_RIDER_EXPERIENCE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_RIDER_EXPERIENCE} attribute.
     */
    public String getRiderExperience() {
        return riderExperience;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_RIDER_EXPERIENCE} attribute with the specified value.
     *
     * @param riderExperience new value for {@value #ATTRIBUTE_NAME_RIDER_EXPERIENCE} attribute.
     */
    protected void setRiderExperience(final String riderExperience) {
        this.riderExperience = riderExperience;
    }

    // --------------------------------------------------------------------------------------------------------- rowguid

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ROWGUID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ROWGUID} attribute.
     */
    public UUID getRowguid() {
        return rowguid;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ROWGUID} attribute with the specified value.
     *
     * @param rowguid new value for {@value #ATTRIBUTE_NAME_ROWGUID} attribute.
     */
    protected void setRowguid(final UUID rowguid) {
        this.rowguid = rowguid;
    }

    // ---------------------------------------------------------------------------------------------------- modifiedDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     */
    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute with the specified value.
     *
     * @param modifiedDate new value for {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     */
    protected void setModifiedDate(final LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // read-only but for an INSERT: SQL Server does not write this view, and EclipseLink requires a writable mapping
    // of every identifier column; see the class documentation
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_PRODUCT_MODEL_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Integer productModelId;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_NAME,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_NAME
    )
    private String name;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_SUMMARY,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private String summary;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_MANUFACTURER,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private String manufacturer;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_COPYRIGHT)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_COPYRIGHT,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_COPYRIGHT
    )
    private String copyright;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_PRODUCT_URL)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PRODUCT_URL,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_PRODUCT_URL
    )
    private String productUrl;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_WARRANTY_PERIOD)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_WARRANTY_PERIOD,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_WARRANTY_PERIOD
    )
    private String warrantyPeriod;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_WARRANTY_DESCRIPTION)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_WARRANTY_DESCRIPTION,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_WARRANTY_DESCRIPTION
    )
    private String warrantyDescription;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_NO_OF_YEARS)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_NO_OF_YEARS,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_NO_OF_YEARS
    )
    private String noOfYears;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_MAINTENANCE_DESCRIPTION)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_MAINTENANCE_DESCRIPTION,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_MAINTENANCE_DESCRIPTION
    )
    private String maintenanceDescription;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_WHEEL)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_WHEEL,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_WHEEL
    )
    private String wheel;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_SADDLE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_SADDLE,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_SADDLE
    )
    private String saddle;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_PEDAL)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PEDAL,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_PEDAL
    )
    private String pedal;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_BIKE_FRAME,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private String bikeFrame;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_CRANKSET)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_CRANKSET,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_CRANKSET
    )
    private String crankset;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_PICTURE_ANGLE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PICTURE_ANGLE,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_PICTURE_ANGLE
    )
    private String pictureAngle;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_PICTURE_SIZE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PICTURE_SIZE,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_PICTURE_SIZE
    )
    private String pictureSize;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_PRODUCT_PHOTO_ID)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PRODUCT_PHOTO_ID,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_PRODUCT_PHOTO_ID
    )
    private String productPhotoId;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_MATERIAL)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_MATERIAL,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_MATERIAL
    )
    private String material;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_COLOR)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_COLOR,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_COLOR
    )
    private String color;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_PRODUCT_LINE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PRODUCT_LINE,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_PRODUCT_LINE
    )
    private String productLine;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_STYLE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_STYLE,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_STYLE
    )
    private String style;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_RIDER_EXPERIENCE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_RIDER_EXPERIENCE,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_RIDER_EXPERIENCE
    )
    private String riderExperience;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @NotNull
    @Convert(converter = UuidStringAttributeConverter.class)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ROWGUID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private UUID rowguid;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_MODIFIED_DATE,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private LocalDateTime modifiedDate;
}

