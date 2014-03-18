/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.app.scrum.validation;

/**
 *
 * @author catalin
 */
public class StringInitCapValidation extends Validator {
    public StringInitCapValidation(String message) {
        super(message);
    }
    @Override
    public boolean validate(Object valueToValidate) throws ValidationException {
        if (valueToValidate == null) {
            throw new ValidationException("Validare valoare NULL !?!");
        }

        String value = (String) valueToValidate;
        if (value.substring(0, 1).toUpperCase().equals(value.substring(0,1))) {
            return true;
        }
        throw new ValidationException(this.message);
    }
}
