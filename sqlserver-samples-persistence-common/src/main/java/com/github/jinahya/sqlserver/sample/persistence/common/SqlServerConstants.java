package com.github.jinahya.sqlserver.sample.persistence.common;

/**
 * Constants of SQL Server.
 */
public final class SqlServerConstants {




    // --------------------------------------------------------------------------------------- money

    /**
     * The precision of the {@code money} type, which DDL cannot change. The value is {@value}.
     *
     * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/data-types/money-and-smallmoney-transact-sql">
     * money and smallmoney (Transact-SQL)</a>
     */
    public static final int MONEY_PRECISION = 19;

    /**
     * The scale of the {@code money} type, which DDL cannot change. The value is {@value}.
     *
     * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/data-types/money-and-smallmoney-transact-sql">
     * money and smallmoney (Transact-SQL)</a>
     */
    public static final int MONEY_SCALE = 4;

    /**
     * The minimum value of the {@code money} type, of precision {@value #MONEY_PRECISION} and
     * scale {@value #MONEY_SCALE}. The value is {@value}.
     *
     * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/data-types/money-and-smallmoney-transact-sql">
     * money and smallmoney (Transact-SQL)</a>
     */
    public static final String MONEY_MIN = "-922337203685477.5808";

    /**
     * The maximum value of the {@code money} type, of precision {@value #MONEY_PRECISION} and
     * scale {@value #MONEY_SCALE}. The value is {@value}.
     *
     * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/data-types/money-and-smallmoney-transact-sql">
     * money and smallmoney (Transact-SQL)</a>
     */
    public static final String MONEY_MAX = "922337203685477.5807";

    // ------------------------------------------------------------------------------------- tinyint

    /**
     * The precision of the {@code tinyint} type, which DDL cannot change. The value is {@value}.
     *
     * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/data-types/int-bigint-smallint-and-tinyint-transact-sql">
     * int, bigint, smallint, and tinyint (Transact-SQL)</a>
     */
    public static final int TINYINT_PRECISION = 3;

    /**
     * The scale of the {@code tinyint} type, which DDL cannot change. The value is {@value}.
     *
     * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/data-types/int-bigint-smallint-and-tinyint-transact-sql">
     * int, bigint, smallint, and tinyint (Transact-SQL)</a>
     */
    public static final int TINYINT_SCALE = 0;

    /**
     * The minimum value of the {@code tinyint} type, which is unsigned. The value is {@value}.
     *
     * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/data-types/int-bigint-smallint-and-tinyint-transact-sql">
     * int, bigint, smallint, and tinyint (Transact-SQL)</a>
     */
    public static final int TINYINT_MIN = 0;

    /**
     * The maximum value of the {@code tinyint} type, which is unsigned. The value is {@value}.
     *
     * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/data-types/int-bigint-smallint-and-tinyint-transact-sql">
     * int, bigint, smallint, and tinyint (Transact-SQL)</a>
     */
    public static final int TINYINT_MAX = 255;

    // ------------------------------------------------------------------------------------ smallint

    /**
     * The precision of the {@code smallint} type, which DDL cannot change. The value is {@value}.
     *
     * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/data-types/int-bigint-smallint-and-tinyint-transact-sql">
     * int, bigint, smallint, and tinyint (Transact-SQL)</a>
     */
    public static final int SMALLINT_PRECISION = 5;

    /**
     * The scale of the {@code smallint} type, which DDL cannot change. The value is {@value}.
     *
     * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/data-types/int-bigint-smallint-and-tinyint-transact-sql">
     * int, bigint, smallint, and tinyint (Transact-SQL)</a>
     */
    public static final int SMALLINT_SCALE = 0;

    /**
     * The minimum value of the {@code smallint} type, which is signed. The value is {@value}.
     *
     * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/data-types/int-bigint-smallint-and-tinyint-transact-sql">
     * int, bigint, smallint, and tinyint (Transact-SQL)</a>
     */
    public static final int SMALLINT_MIN = Short.MIN_VALUE;

    /**
     * The maximum value of the {@code smallint} type, which is signed. The value is {@value}.
     *
     * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/data-types/int-bigint-smallint-and-tinyint-transact-sql">
     * int, bigint, smallint, and tinyint (Transact-SQL)</a>
     */
    public static final int SMALLINT_MAX = Short.MAX_VALUE;

    // ----------------------------------------------------------------------------------------- int

    /**
     * The precision of the {@code int} type, which DDL cannot change. The value is {@value}.
     *
     * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/data-types/int-bigint-smallint-and-tinyint-transact-sql">
     * int, bigint, smallint, and tinyint (Transact-SQL)</a>
     */
    public static final int INT_PRECISION = 10;

    /**
     * The scale of the {@code int} type, which DDL cannot change. The value is {@value}.
     *
     * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/data-types/int-bigint-smallint-and-tinyint-transact-sql">
     * int, bigint, smallint, and tinyint (Transact-SQL)</a>
     */
    public static final int INT_SCALE = 0;

    /**
     * The minimum value of the {@code int} type, which is signed. The value is {@value}.
     *
     * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/data-types/int-bigint-smallint-and-tinyint-transact-sql">
     * int, bigint, smallint, and tinyint (Transact-SQL)</a>
     */
    public static final int INT_MIN = Integer.MIN_VALUE;

    /**
     * The maximum value of the {@code int} type, which is signed. The value is {@value}.
     *
     * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/data-types/int-bigint-smallint-and-tinyint-transact-sql">
     * int, bigint, smallint, and tinyint (Transact-SQL)</a>
     */
    public static final int INT_MAX = Integer.MAX_VALUE;

    // -------------------------------------------------------------------------------------- bigint

    /**
     * The precision of the {@code bigint} type, which DDL cannot change. The value is {@value}.
     *
     * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/data-types/int-bigint-smallint-and-tinyint-transact-sql">
     * int, bigint, smallint, and tinyint (Transact-SQL)</a>
     */
    public static final int BIGINT_PRECISION = 19;

    /**
     * The scale of the {@code bigint} type, which DDL cannot change. The value is {@value}.
     *
     * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/data-types/int-bigint-smallint-and-tinyint-transact-sql">
     * int, bigint, smallint, and tinyint (Transact-SQL)</a>
     */
    public static final int BIGINT_SCALE = 0;

    /**
     * The minimum value of the {@code bigint} type, which is signed. The value is {@value}.
     *
     * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/data-types/int-bigint-smallint-and-tinyint-transact-sql">
     * int, bigint, smallint, and tinyint (Transact-SQL)</a>
     */
    public static final long BIGINT_MIN = Long.MIN_VALUE;

    /**
     * The maximum value of the {@code bigint} type, which is signed. The value is {@value}.
     *
     * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/data-types/int-bigint-smallint-and-tinyint-transact-sql">
     * int, bigint, smallint, and tinyint (Transact-SQL)</a>
     */
    public static final long BIGINT_MAX = Long.MAX_VALUE;

    // ---------------------------------------------------------------------------------- smallmoney

    /**
     * The precision of the {@code smallmoney} type, which DDL cannot change. The value is {@value}.
     *
     * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/data-types/money-and-smallmoney-transact-sql">
     * money and smallmoney (Transact-SQL)</a>
     */
    public static final int SMALLMONEY_PRECISION = 10;

    /**
     * The scale of the {@code smallmoney} type, which DDL cannot change. The value is {@value}.
     *
     * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/data-types/money-and-smallmoney-transact-sql">
     * money and smallmoney (Transact-SQL)</a>
     */
    public static final int SMALLMONEY_SCALE = 4;

    /**
     * The minimum value of the {@code smallmoney} type, of precision
     * {@value #SMALLMONEY_PRECISION} and scale {@value #SMALLMONEY_SCALE}. The value is {@value}.
     *
     * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/data-types/money-and-smallmoney-transact-sql">
     * money and smallmoney (Transact-SQL)</a>
     */
    public static final String SMALLMONEY_MIN = "-214748.3648";

    /**
     * The maximum value of the {@code smallmoney} type, of precision
     * {@value #SMALLMONEY_PRECISION} and scale {@value #SMALLMONEY_SCALE}. The value is {@value}.
     *
     * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/data-types/money-and-smallmoney-transact-sql">
     * money and smallmoney (Transact-SQL)</a>
     */
    public static final String SMALLMONEY_MAX = "214748.3647";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    private SqlServerConstants() {
        throw new AssertionError("instantiation is not allowed");
    }
}
