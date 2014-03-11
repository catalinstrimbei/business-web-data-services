/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.app.scrum.validare;

/**
 *
 * @author catalin
 */
public class StringInitCapValidation extends Validator {
    public StringInitCapValidation(String message) {
        super(message);
    }
    @Override
    public boolean validate(Object valueToValidate) throws ExceptieValidare {
        if (valueToValidate == null) {
            throw new ExceptieValidare("Validare valoare NULL !?!");
        }

        String value = (String) valueToValidate;
        if (value.substring(0, 1).toUpperCase().equals(value.substring(0,1))) {
            return true;
        }
        throw new ExceptieValidare(this.message);
    }
}
