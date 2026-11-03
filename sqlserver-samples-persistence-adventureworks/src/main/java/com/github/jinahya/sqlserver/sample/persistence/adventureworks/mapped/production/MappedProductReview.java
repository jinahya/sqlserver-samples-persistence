package com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.production;

/*-
 * #%L
 * sqlserver-samples-persistence-adventureworks
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

import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDefault;
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDescription;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code Production.ProductReview} table.
 * <p>
 * The {@value #COLUMN_NAME_PRODUCT_ID} column is mapped read-only, as a foreign key; the extending
 * entity owns the writable mapping, through an association's
 * {@link jakarta.persistence.JoinColumn @JoinColumn}.
 *
 * <table class="striped">
 * <caption>Columns of {@code Production.ProductReview}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Description</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code ProductReviewID}</th>
 *     <td>Primary key for ProductReview records.</td></tr>
 * <tr><th scope="row">{@code ProductID}</th>
 *     <td>Product identification number. Foreign key to Product.ProductID.</td></tr>
 * <tr><th scope="row">{@code ReviewerName}</th><td>Name of the reviewer.</td></tr>
 * <tr><th scope="row">{@code ReviewDate}</th><td>Date review was submitted.</td></tr>
 * <tr><th scope="row">{@code EmailAddress}</th><td>Reviewer's e-mail address.</td></tr>
 * <tr><th scope="row">{@code Rating}</th>
 *     <td>Product rating given by the reviewer. Scale is 1 to 5 with 5 as the highest
 *     rating.</td></tr>
 * <tr><th scope="row">{@code Comments}</th><td>Reviewer's comments</td></tr>
 * <tr><th scope="row">{@code ModifiedDate}</th>
 *     <td>Date and time the record was last updated.</td></tr>
 * </tbody>
 * </table>
 *
 * <table class="striped">
 * <caption>Defaults of {@code Production.ProductReview}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Definition</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code ReviewDate}</th><td>{@code (getdate())}</td></tr>
 * <tr><th scope="row">{@code ModifiedDate}</th><td>{@code (getdate())}</td></tr>
 * </tbody>
 * </table>
 *
 * @see <a
 *         href="https://learn.microsoft
 *         .com/en-us/sql/t-sql/functions/getdate-transact-sql">GETDATE</a>
 */
@MappedSuperclass
@MsDescription(
        schema = MappedProductReview.TABLE_SCHEMA,
        table = MappedProductReview.TABLE_NAME,
        value = "Customer reviews of products they have purchased."
)
public abstract class MappedProductReview {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "ProductReview";

    // ----------------------------------------------------------------------------- ProductReviewID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRODUCT_REVIEW_ID = "ProductReviewID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRODUCT_REVIEW_ID} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_REVIEW_ID = "productReviewId";

    // ----------------------------------------------------------------------------------- ProductID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRODUCT_ID = "ProductID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRODUCT_ID} column. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_ID = "productId";

    // -------------------------------------------------------------------------------- ReviewerName

    /**
     * The name of the {@code Name (nvarchar)(50), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_REVIEWER_NAME = "ReviewerName";

    /**
     * The length of the {@value #COLUMN_NAME_REVIEWER_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_REVIEWER_NAME = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_REVIEWER_NAME} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_REVIEWER_NAME = "reviewerName";

    // ---------------------------------------------------------------------------------- ReviewDate

    /**
     * The name of the {@code datetime, DEFAULT (getdate()), NOT NULL} column. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_REVIEW_DATE = "ReviewDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_REVIEW_DATE} column. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_REVIEW_DATE = "reviewDate";

    // -------------------------------------------------------------------------------- EmailAddress

    /**
     * The name of the {@code nvarchar(50), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_EMAIL_ADDRESS = "EmailAddress";

    /**
     * The length of the {@value #COLUMN_NAME_EMAIL_ADDRESS} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_EMAIL_ADDRESS = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_EMAIL_ADDRESS} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_EMAIL_ADDRESS = "emailAddress";

    // -------------------------------------------------------------------------------------- Rating

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_RATING = "Rating";

    /**
     * The minimum value of the {@value #COLUMN_NAME_RATING} column, as the
     * {@code CK_ProductReview_Rating} check constraint ({@code [Rating]>=(1)}) requires. The value
     * is {@value}.
     */
    public static final int COLUMN_MIN_RATING = +1;

    /**
     * The maximum value of the {@value #COLUMN_NAME_RATING} column, as the
     * {@code CK_ProductReview_Rating} check constraint ({@code [Rating]<=(5)}) requires. The value
     * is {@value}.
     */
    public static final int COLUMN_MAX_RATING = +5;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_RATING} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_RATING = "rating";

    // ------------------------------------------------------------------------------------ Comments

    /**
     * The name of the {@code nvarchar(3850)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_COMMENTS = "Comments";

    /**
     * The length of the {@value #COLUMN_NAME_COMMENTS} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_COMMENTS = 3850;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_COMMENTS} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_COMMENTS = "comments";

    // -------------------------------------------------------------------------------- ModifiedDate

    /**
     * The name of the {@code datetime, DEFAULT (getdate()), NOT NULL} column. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_MODIFIED_DATE = "ModifiedDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MODIFIED_DATE} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_MODIFIED_DATE = "modifiedDate";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedProductReview() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "productReviewId=" + productReviewId
               + ",productId=" + productId
               + ",reviewerName=" + reviewerName
               + ",reviewDate=" + reviewDate
               + ",emailAddress=" + emailAddress
               + ",rating=" + rating
               + ",comments=" + comments
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
        if (!(obj instanceof MappedProductReview that)) {
            return false;
        }
        return Objects.equals(productReviewId, that.productReviewId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(productReviewId);
    }

    // ----------------------------------------------------------------------------- productReviewId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRODUCT_REVIEW_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRODUCT_REVIEW_ID} attribute.
     */
    public Integer getProductReviewId() {
        return productReviewId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRODUCT_REVIEW_ID} attribute with the
     * specified value.
     *
     * @param productReviewId new value for {@value #ATTRIBUTE_NAME_PRODUCT_REVIEW_ID} attribute.
     */
    protected void setProductReviewId(final Integer productReviewId) {
        this.productReviewId = productReviewId;
    }

    // ----------------------------------------------------------------------------------- productId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute.
     */
    protected Integer getProductId() {
        return productId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute with the specified
     * value. The attribute is read-only; the extending entity calls this to keep it in step with
     * the association which writes the column.
     *
     * @param productId new value for {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute.
     */
    protected void setProductId(final Integer productId) {
        this.productId = productId;
    }

    // -------------------------------------------------------------------------------- reviewerName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_REVIEWER_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_REVIEWER_NAME} attribute.
     */
    public String getReviewerName() {
        return reviewerName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_REVIEWER_NAME} attribute with the specified
     * value.
     *
     * @param reviewerName new value for {@value #ATTRIBUTE_NAME_REVIEWER_NAME} attribute.
     */
    public void setReviewerName(final String reviewerName) {
        this.reviewerName = reviewerName;
    }

    // ---------------------------------------------------------------------------------- reviewDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_REVIEW_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_REVIEW_DATE} attribute.
     */
    public LocalDateTime getReviewDate() {
        return reviewDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_REVIEW_DATE} attribute with the specified
     * value.
     *
     * @param reviewDate new value for {@value #ATTRIBUTE_NAME_REVIEW_DATE} attribute.
     */
    public void setReviewDate(final LocalDateTime reviewDate) {
        this.reviewDate = reviewDate;
    }

    // -------------------------------------------------------------------------------- emailAddress

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_EMAIL_ADDRESS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_EMAIL_ADDRESS} attribute.
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_EMAIL_ADDRESS} attribute with the specified
     * value.
     *
     * @param emailAddress new value for {@value #ATTRIBUTE_NAME_EMAIL_ADDRESS} attribute.
     */
    public void setEmailAddress(final String emailAddress) {
        this.emailAddress = emailAddress;
    }

    // -------------------------------------------------------------------------------------- rating

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_RATING} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_RATING} attribute.
     */
    public Integer getRating() {
        return rating;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_RATING} attribute with the specified
     * value.
     *
     * @param rating new value for {@value #ATTRIBUTE_NAME_RATING} attribute.
     */
    public void setRating(final Integer rating) {
        this.rating = rating;
    }

    // ------------------------------------------------------------------------------------ comments

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_COMMENTS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_COMMENTS} attribute.
     */
    public String getComments() {
        return comments;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_COMMENTS} attribute with the specified
     * value.
     *
     * @param comments new value for {@value #ATTRIBUTE_NAME_COMMENTS} attribute.
     */
    public void setComments(final String comments) {
        this.comments = comments;
    }

    // -------------------------------------------------------------------------------- modifiedDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     */
    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute with the specified
     * value.
     *
     * @param modifiedDate new value for {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     */
    public void setModifiedDate(final LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    // ---------------------------------------------------------------------------------------------
    // not updatable: an identity column, which SQL Server does not update (error 8102); and the
    // identifier, which
    // the provider leaves out of an INSERT, as it is @GeneratedValue(strategy = IDENTITY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_PRODUCT_REVIEW_ID, nullable = false, insertable = true,
            updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_PRODUCT_REVIEW_ID,
                          value = "Primary key for ProductReview records.")
    private Integer productReviewId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class
    // documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PRODUCT_ID, nullable = false, insertable = false, updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_PRODUCT_ID,
                          value = "Product identification number. Foreign key to "
                                  + "Product.ProductID.")
    private Integer productId;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_REVIEWER_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_REVIEWER_NAME, nullable = false, insertable = true, updatable = true,
            length = COLUMN_LENGTH_REVIEWER_NAME)
    @MsDescription.Column(name = COLUMN_NAME_REVIEWER_NAME, value = "Name of the reviewer.")
    private String reviewerName;

    // ---------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_REVIEW_DATE, nullable = false, insertable = true, updatable = true)
    @MsDescription.Column(name = COLUMN_NAME_REVIEW_DATE, value = "Date review was submitted.")
    @MsDefault(name = "DF_ProductReview_ReviewDate", value = "(getdate())")
    private LocalDateTime reviewDate;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_EMAIL_ADDRESS)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_EMAIL_ADDRESS, nullable = false, insertable = true, updatable = true,
            length = COLUMN_LENGTH_EMAIL_ADDRESS)
    @MsDescription.Column(name = COLUMN_NAME_EMAIL_ADDRESS, value = "Reviewer's e-mail address.")
    private String emailAddress;

    // ---------------------------------------------------------------------------------------------
    // CK_ProductReview_Rating: ([Rating]>=(1) AND [Rating]<=(5))
    @Max(COLUMN_MAX_RATING)
    @Min(COLUMN_MIN_RATING)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_RATING, nullable = false, insertable = true, updatable = true)
    @MsDescription.Column(name = COLUMN_NAME_RATING,
                          value = "Product rating given by the reviewer. Scale is 1 to 5 with 5 "
                                  + "as the highest rating.")
    private Integer rating;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_COMMENTS)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_COMMENTS, nullable = true, insertable = true, updatable = true,
            length = COLUMN_LENGTH_COMMENTS)
    @MsDescription.Column(name = COLUMN_NAME_COMMENTS, value = "Reviewer's comments")
    private String comments;

    // ---------------------------------------------------------------------------------------------
    // the database's DEFAULT (getdate()) fills it on an INSERT; the date and time the record was
    // last updated,
    // which nothing in the database restamps, so an UPDATE writes it
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_MODIFIED_DATE, nullable = false, insertable = false,
            updatable = true)
    @MsDescription.Column(name = COLUMN_NAME_MODIFIED_DATE,
                          value = "Date and time the record was last updated.")
    @MsDefault(name = "DF_ProductReview_ModifiedDate", value = "(getdate())")
    private LocalDateTime modifiedDate;
}

