/*
 * $Id: MonoidFactory.java 4056 2012-07-26 17:44:13Z kredel $
 */

package edu.jas.structure;


/**
 * Monoid factory interface. Defines get one and tests for associativity and
 * commutativity.
 * @author Heinz Kredel
 */

public interface MonoidFactory<C extends MonoidElem<C>> extends ElemFactory<C> {


    /**
     * Get the constant one for the MonoidElem.
     * @return 1.
     */
    public C getONE();


    /**
     * Query if this monoid is commutative.
     * @return true if this monoid is commutative, else false.
     */
    public boolean isCommutative();


    /**
     * Query if this ring is associative.
     * @return true if this monoid is associative, else false.
     */
    public boolean isAssociative();


}
