/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.app.scrum.validare;

/**
 *
 * @author catalin
 */
public class IntegerIntervalValidation extends Validator{
    private Integer start;
    private Integer end;

    public IntegerIntervalValidation(String message, Integer start, Integer end) {
        super(message);
        this.start = start;
        this.end = end;
    }

    @Override
    public boolean validate(Object valueToValidate) throws ExceptieValidare {
        if (valueToValidate == null)
            throw new ExceptieValidare("Validare valoare NULL !?!");
        Integer value = (Integer) valueToValidate;
        if (value >= start && value <= end)
            return true;
        throw new ExceptieValidare(message);
    }

}
