/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ist.pkg440.requests;

/**
 *
 * @author chrismorris
 */
public class ErrorWrapper<T> {
    
    private T error;
    
    /**
     * @return the error
     */
    public T getError() {
        return error;
    }

    /**
     * @param error the error to set
     */
    public void setError(T error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return getError().toString();
    }

}