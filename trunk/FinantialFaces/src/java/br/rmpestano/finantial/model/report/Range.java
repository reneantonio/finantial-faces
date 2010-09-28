/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.rmpestano.finantial.model.report;

/**
 *
 * @author rmpestano
 */
public class Range implements Comparable<Range>{
    int firstValue;
    int secondValue;
    boolean lastRange;

    public Range(int firstValue, int secondValue) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    public int getFirstValue() {
        return firstValue;
    }

    public void setFirstValue(int firstValue) {
        this.firstValue = firstValue;
    }

    public int getSecondValue() {
        return secondValue;
    }

    public void setSecondValue(int secondValue) {
        this.secondValue = secondValue;
    }

    public boolean isLastRange() {
        return lastRange;
    }

    public void setLastRange(boolean lastRange) {
        this.lastRange = lastRange;
    }


    @Override
    public String toString(){
        return isLastRange() ? (" > "+firstValue) : (firstValue + " -- "+secondValue);
    }

    /**
     * used to compare two ranges cause two ranges differs from its sum
     * @return
     */
    public Integer rangeSum(){
        return firstValue+secondValue;
    }


    @Override
    public int compareTo(Range o) {
        return this.rangeSum().compareTo(o.rangeSum());
    }




}
