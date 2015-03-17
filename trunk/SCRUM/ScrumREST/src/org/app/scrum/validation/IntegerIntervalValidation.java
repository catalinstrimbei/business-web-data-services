/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.app.scrum.validation;

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
    public boolean validate(Object valueToValidate) throws ValidationException {
        if (valueToValidate == null)
            throw new ValidationException("Validare valoare NULL !?!");
        Integer value = (Integer) valueToValidate;
        if (value >= start && value <= end)
            return true;
        throw new ValidationException(message);
    }

}
