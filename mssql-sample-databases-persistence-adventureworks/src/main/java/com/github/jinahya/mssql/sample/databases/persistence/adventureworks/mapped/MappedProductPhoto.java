package com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped;

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

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code Production.ProductPhoto} table.
 */
@MappedSuperclass
public abstract class MappedProductPhoto {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = "Production";

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "ProductPhoto";

    // -------------------------------------------------------------------------------------------------- ProductPhotoID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRODUCT_PHOTO_ID = "ProductPhotoID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRODUCT_PHOTO_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_PHOTO_ID = "productPhotoId";

    // -------------------------------------------------------------------------------------------------- ThumbNailPhoto

    /**
     * The name of the {@code varbinary(max)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_THUMB_NAIL_PHOTO = "ThumbNailPhoto";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_THUMB_NAIL_PHOTO} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_THUMB_NAIL_PHOTO = "thumbNailPhoto";

    // ------------------------------------------------------------------------------------------ ThumbnailPhotoFileName

    /**
     * The name of the {@code nvarchar(50)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_THUMBNAIL_PHOTO_FILE_NAME = "ThumbnailPhotoFileName";

    /**
     * The length of the {@value #COLUMN_NAME_THUMBNAIL_PHOTO_FILE_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_THUMBNAIL_PHOTO_FILE_NAME = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_THUMBNAIL_PHOTO_FILE_NAME} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_THUMBNAIL_PHOTO_FILE_NAME = "thumbnailPhotoFileName";

    // ------------------------------------------------------------------------------------------------------ LargePhoto

    /**
     * The name of the {@code varbinary(max)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LARGE_PHOTO = "LargePhoto";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LARGE_PHOTO} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_LARGE_PHOTO = "largePhoto";

    // ---------------------------------------------------------------------------------------------- LargePhotoFileName

    /**
     * The name of the {@code nvarchar(50)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LARGE_PHOTO_FILE_NAME = "LargePhotoFileName";

    /**
     * The length of the {@value #COLUMN_NAME_LARGE_PHOTO_FILE_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_LARGE_PHOTO_FILE_NAME = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LARGE_PHOTO_FILE_NAME} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_LARGE_PHOTO_FILE_NAME = "largePhotoFileName";

    // ---------------------------------------------------------------------------------------------------- ModifiedDate

    /**
     * The name of the {@code datetime, DEFAULT (getdate()), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_MODIFIED_DATE = "ModifiedDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MODIFIED_DATE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_MODIFIED_DATE = "modifiedDate";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedProductPhoto() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "productPhotoId=" + productPhotoId
               + ",thumbNailPhoto=" + (this.thumbNailPhoto == null ? null : this.thumbNailPhoto.length + " bytes")
               + ",thumbnailPhotoFileName=" + thumbnailPhotoFileName
               + ",largePhoto=" + (this.largePhoto == null ? null : this.largePhoto.length + " bytes")
               + ",largePhotoFileName=" + largePhotoFileName
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
        if (!(obj instanceof MappedProductPhoto that)) {
            return false;
        }
        return Objects.equals(productPhotoId, that.productPhotoId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(productPhotoId);
    }

    // -------------------------------------------------------------------------------------------------- productPhotoId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRODUCT_PHOTO_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRODUCT_PHOTO_ID} attribute.
     */
    @Nullable
    public Integer getProductPhotoId() {
        return productPhotoId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRODUCT_PHOTO_ID} attribute with the specified value.
     *
     * @param productPhotoId new value for {@value #ATTRIBUTE_NAME_PRODUCT_PHOTO_ID} attribute.
     */
    protected void setProductPhotoId(@Nullable final Integer productPhotoId) {
        this.productPhotoId = productPhotoId;
    }

    // -------------------------------------------------------------------------------------------------- thumbNailPhoto

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_THUMB_NAIL_PHOTO} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_THUMB_NAIL_PHOTO} attribute.
     */
    @Nullable
    public byte[] getThumbNailPhoto() {
        return thumbNailPhoto;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_THUMB_NAIL_PHOTO} attribute with the specified value.
     *
     * @param thumbNailPhoto new value for {@value #ATTRIBUTE_NAME_THUMB_NAIL_PHOTO} attribute.
     */
    public void setThumbNailPhoto(@Nullable final byte[] thumbNailPhoto) {
        this.thumbNailPhoto = thumbNailPhoto;
    }

    // ------------------------------------------------------------------------------------------ thumbnailPhotoFileName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_THUMBNAIL_PHOTO_FILE_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_THUMBNAIL_PHOTO_FILE_NAME} attribute.
     */
    @Nullable
    public String getThumbnailPhotoFileName() {
        return thumbnailPhotoFileName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_THUMBNAIL_PHOTO_FILE_NAME} attribute with the specified value.
     *
     * @param thumbnailPhotoFileName new value for {@value #ATTRIBUTE_NAME_THUMBNAIL_PHOTO_FILE_NAME} attribute.
     */
    public void setThumbnailPhotoFileName(@Nullable final String thumbnailPhotoFileName) {
        this.thumbnailPhotoFileName = thumbnailPhotoFileName;
    }

    // ------------------------------------------------------------------------------------------------------ largePhoto

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LARGE_PHOTO} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LARGE_PHOTO} attribute.
     */
    @Nullable
    public byte[] getLargePhoto() {
        return largePhoto;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LARGE_PHOTO} attribute with the specified value.
     *
     * @param largePhoto new value for {@value #ATTRIBUTE_NAME_LARGE_PHOTO} attribute.
     */
    public void setLargePhoto(@Nullable final byte[] largePhoto) {
        this.largePhoto = largePhoto;
    }

    // ---------------------------------------------------------------------------------------------- largePhotoFileName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LARGE_PHOTO_FILE_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LARGE_PHOTO_FILE_NAME} attribute.
     */
    @Nullable
    public String getLargePhotoFileName() {
        return largePhotoFileName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LARGE_PHOTO_FILE_NAME} attribute with the specified value.
     *
     * @param largePhotoFileName new value for {@value #ATTRIBUTE_NAME_LARGE_PHOTO_FILE_NAME} attribute.
     */
    public void setLargePhotoFileName(@Nullable final String largePhotoFileName) {
        this.largePhotoFileName = largePhotoFileName;
    }

    // ---------------------------------------------------------------------------------------------------- modifiedDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     */
    @Nonnull
    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute with the specified value.
     *
     * @param modifiedDate new value for {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     */
    public void setModifiedDate(@Nonnull final LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_PRODUCT_PHOTO_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Integer productPhotoId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_THUMB_NAIL_PHOTO,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private byte[] thumbNailPhoto;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = COLUMN_LENGTH_THUMBNAIL_PHOTO_FILE_NAME)
    @Column(name = COLUMN_NAME_THUMBNAIL_PHOTO_FILE_NAME,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_THUMBNAIL_PHOTO_FILE_NAME
    )
    private String thumbnailPhotoFileName;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_LARGE_PHOTO,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private byte[] largePhoto;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = COLUMN_LENGTH_LARGE_PHOTO_FILE_NAME)
    @Column(name = COLUMN_NAME_LARGE_PHOTO_FILE_NAME,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_LARGE_PHOTO_FILE_NAME
    )
    private String largePhotoFileName;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_MODIFIED_DATE,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private LocalDateTime modifiedDate;
}

